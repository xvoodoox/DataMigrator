package DataMigrate.Model;

import DataMigrate.FileHandler.FileHandler;
import DataMigrate.TransferObjects.EstimationObject;
import DataMigrate.TransferObjects.RequirementObject;
import DataMigrate.TransferObjects.SCICRObject;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * This class perform all of the operations associated with transferring
 * data from one database file to another database file via using .csv files.
 *
 * Created by Anthony Orio on 4/24/2017.
 */
public class MigratorModel
{
    public static Connection conn = null;                               /* Database connection */
    public static String dbPath = "jdbc:ucanaccess://";                 /* Database path */

    private static FileHandler fileHandler = new FileHandler();         /* Handles file utilties */

    public static File databaseFile;                                    /* Database file */
    public static File estimationCSVFile;                               /* Estimation file */
    public static File scicrCSVFile;                                    /* SC/ICR file */
    public static File valCodeCSVFile;                                  /* ValCodes file */
    public static File requirementsCSVFile;                             /* Requirements file */

    public static String[] estimationHeaders = {};                      /* Column names of estimation base */
    public static String[] scicrHeaders = {};                           /* Column names of SC/ICR */
    public static String[] requirementsHeaders = {};                    /* Column names of requirements*/
    public static String[] valTypes = { "Capability", "CSU", "CSC", "Program",
                                        "RI", "Rommer", "Build"  };     /* Val Code data types */


    public static ArrayList<String[]> estimationBaseData = new ArrayList<>();       /* Will store estimation base data */
    public static HashMap<String, Integer> baselineIDMap = new HashMap<>();         /* Baseline with their ID number */
    public static HashMap<String, Integer> scicrIDMap = new HashMap<>();            /* SC/ICR name with their ID number */
    public static HashMap<String, Integer> valcodeIDMap = new HashMap<>();          /* Each ValCodes table value with their ID number */

    public static HashMap<String, HashSet<String>> valcodeMap;                      /* One of each type of ValCodes data type */
        static
        {
            valcodeMap = new HashMap<>();

            valcodeMap.put("Capability",    new HashSet<>());
            valcodeMap.put("CSU",           new HashSet<>());
            valcodeMap.put("CSC",           new HashSet<>());
            valcodeMap.put("Program",       new HashSet<>());
            valcodeMap.put("RI",            new HashSet<>());
            valcodeMap.put("Rommer",        new HashSet<>());
            valcodeMap.put("Build",         new HashSet<>());
        }

    /**
     * Uses a file chooser to retrieve the database file that the CSV files
     * will be transferred into.
     * @return True if the connection was able to be established to the database.
     */
    public static boolean findDatabaseFile()
    {
        /* Retrieve with file chooser. */
        databaseFile = fileHandler.useFileChooser("*.mdb");

        /* Create the full path. */
        dbPath = dbPath + databaseFile.getPath();

        /* Attempt to connect to DB. */
        if (attemptConnection()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Uses a file chooser to locate the CSV file for estimation base data.
     */
    public static void findEstimationCSV()
    {
        /* Call file chooser. */
        estimationCSVFile = fileHandler.useFileChooser();

        /* Read the first line to get column names. */
        estimationHeaders = readColumnNames(estimationCSVFile);
    }

    /**
     * Uses a file chooser to locate the CSV file for SC/ICR data.
     */
    public static void findSCICRCSV()
    {
        /* Call file chooser. */
        scicrCSVFile = fileHandler.useFileChooser();

         /* Read the first line to get column names. */
        scicrHeaders = readColumnNames(scicrCSVFile);
    }

    /**
     * Uses a file chooser to locate the CSV file for Requirement data.
     */
    public static void findRequirementsCSV()
    {
        /* Call file chooser. */
        requirementsCSVFile = fileHandler.useFileChooser();

         /* Read the first line to get column names. */
        requirementsHeaders = readColumnNames(requirementsCSVFile);
    }

    /**
     * Attempts to establish a connection to the database.
     * @return True if the connection was established with the path used.
     */
    private static boolean attemptConnection()
    {
        try {
            /* Attempt connection with path. */
            conn = DriverManager.getConnection(dbPath);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    /**
     * Read the first line of the CSV file to retrieve the name of the
     * columns for the table.
     * @param file The CSV file that will be used to get the column names from the first line.
     * @return A string array containing each column name.
     */
    private static String[] readColumnNames(File file)
    {
        String csvFile = file.getPath();        /* Get the CSV file path. */
        BufferedReader br = null;               /* Used to read the file. */
        String line = "";                       /* The next line to be read in the file. */
        String cvsSplitBy = ",";                /* The split by value to make the array. */
        String[] headers = {};                  /* The array of column names. */

        try { /* Attempt to read the file. */
            br = new BufferedReader(new FileReader(csvFile));

            /* Read the first line. */
            line = br.readLine();

            /* Split this line based on the comma into an array of Strings. */
            headers = line.split(cvsSplitBy);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return headers;
    }

    /**
     * Creates a collection of EstimationObject that will contain all of the information
     * pertaining to one row in the Baseline table. These objects will be used to write
     * to the database after creating them.
     * @param baseline The baseline name.
     * @param cprs The CPRS.
     * @param month The staff month.
     * @param day The staff day.
     * @param doc The CPDD Document.
     * @param upgrade The budget upgrade.
     * @param maint The budget maintenance.
     * @param ddr DDR.
     * @param unit Unit test weight.
     * @param integ Integration weight.
     * @param design Design weight.
     * @param code Code weight.
     * @param estDefault Default.
     * @param date CPPD date.
     * @throws SQLException If the SQL query could not be completed.
     */
    public static void performROMTransfer( int baseline, int cprs, int month, int day, int doc, int upgrade, int maint,
                                           int ddr, int unit, int integ, int design, int code, int estDefault, int date) throws SQLException
    {
        BufferedReader br = null;       /* Used to read the file. */
        String line = "";               /* The next line to be read in the file. */
        String cvsSplitBy = ",";        /* The split by value to make the array. */
        ArrayList<EstimationObject> estObjCollection = new ArrayList<>();   /* The collection of EstimationObject. */

        int errCount = 0;
        ArrayList<EstimationObject> errCollection = new ArrayList<>();

        try { /* Attempt to read the file. */

            br = new BufferedReader(new FileReader(estimationCSVFile));

            /* Do nothing with the first line, which contains column names. */
            line = br.readLine();

            /* While we didn't hit the end of the file. */
            while ((line = br.readLine()) != null)
            {
                // use comma as separator
                String[] nextLine = line.split(cvsSplitBy);
                EstimationObject newEstObj;

                try
                {
                /* Create the EstimationObject with the parameters. */
                     newEstObj = new EstimationObject(
                            nextLine[baseline], Double.parseDouble(nextLine[day]), Double.parseDouble(nextLine[month]), nextLine[cprs], Double.parseDouble(nextLine[estDefault]), nextLine[doc],
                            nextLine[date], Double.parseDouble(nextLine[upgrade]), Double.parseDouble(nextLine[maint]), Double.parseDouble(nextLine[ddr]), Double.parseDouble(nextLine[design]),
                            Double.parseDouble(nextLine[code]), Double.parseDouble(nextLine[integ]), Double.parseDouble(nextLine[unit])
                    );

                    newEstObj.setOrigLine(line);
                } catch (Exception e) {
                    newEstObj = new EstimationObject();
                    newEstObj.setOrigLine(line);
                    errCollection.add(newEstObj);
                    errCount++;
                }
                /* Add to the collection. */
                estObjCollection.add( newEstObj );
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* Pass the collection to be written to the database. */
        transferROMData( estObjCollection, errCount, errCollection );
    }

    /**
     * Will write the collection of EstimationObject to the database.
     * @param collection The collection of EstimationObjects
     * @throws SQLException If the query could not be completed.
     */
    private static void transferROMData(ArrayList<EstimationObject> collection, int errCount, ArrayList<EstimationObject> errCollection) throws SQLException
    {
        /* Create the query for the write. */
        String insertBaselineQuery = "INSERT INTO Baseline ([baseline_desc], [cprs], [slocs_per_day], " +
                                                           "[slocs_per_month], [slocs_default], [slocs_ddr_cwt], " +
                                                           "[cpdd_document], [cpdd_date], [budget_upgrade], " +
                                                           "[budget_maintenance], [design_weight], [code_weight], " +
                                                           "[integration_weight], [unit_test_weight]) " +
                                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Create a new statement.
        PreparedStatement st = conn.prepareStatement(insertBaselineQuery);


        /* Loop through the collection. */
        int size = collection.size();
        for (int i = 0; i < size; i++)
        {
            EstimationObject curr = collection.get(i);  /* The current EstimationObject being evaluated. */
            try {
                /* Parse all of the information and stage for writing. */
                st.setString(1, curr.getBaseline());
                st.setString(2, curr.getCprs());
                st.setDouble(3, curr.getSlocsDay());
                st.setDouble(4, curr.getSlocsMonth());
                st.setDouble(5, curr.getDefaultSlocs());
                st.setDouble(6, curr.getDdrcwtSlocs());
                st.setString(7, curr.getCpddDoc());
                st.setString(8, curr.getCpddDate());
                st.setDouble(9, curr.getBudUpgrade());
                st.setDouble(10, curr.getBudMaint());
                st.setDouble(11, curr.getDesign());
                st.setDouble(12, curr.getCode());
                st.setDouble(13, curr.getInteg());
                st.setDouble(14, curr.getUnitTest());

                // Perform the update inside of the table of the database.
                st.executeUpdate();
            } catch (Exception e) {
                if(!errCollection.contains(curr)) {
                    errCount++;
                    errCollection.add(curr);
                }
            }
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, errCount + " errors found.", ButtonType.OK);
        alert.showAndWait();

        if (!errCollection.isEmpty())
        {
            writeEstErrors(errCollection);
        }

        /* Place the Baseline with their ID numbers into memory
        *  so that we don't have to read the database each time. */
        moveBaseIDToMem();
    }

    /** Places the baselines and their ID numbers into a hash map so that we reference them
     * from memory instead of the database to increase performance.
     */
    private static void moveBaseIDToMem() throws SQLException
    {
        /* Set up a query to retrieve baseline name and its ID number. */
        String query = "SELECT [baseline_id], [baseline_desc] FROM Baseline";
        PreparedStatement st = conn.prepareStatement(query);    /* Create a new statement. */
        ResultSet rs = st.executeQuery();                       /* The result set returned. */

        /* Loop through the result set. */
        while (rs.next())
        {
            /* Place into hash map.*/
            baselineIDMap.put(rs.getString("baseline_desc"), rs.getInt("baseline_id"));
        }
    }

    /**
     * Creates a collection of SCICRObjects that will be written to the database table.
     * @param type SC or ICR.
     * @param number The number of the SC/ICR.
     * @param title The SC/ICR's title.
     * @param build The build belonging to it.
     * @param baseline The baseline it is under.
     * @throws SQLException If the query could not be completed.
     */
    public static void performSCICRTransfer(int type, int number, int title, int build, int baseline) throws SQLException
    {
        BufferedReader br = null;           /* Used to read the file. */
        String line = "";                   /* The next line to be read in the file. */
        String cvsSplitBy = ",";            /* The split by value to make the array. */
        ArrayList<SCICRObject> scicrObjCollection = new ArrayList<>();  /* The collection of SCICRObjects. */

        int errCount = 0;
        ArrayList<SCICRObject> errCollection = new ArrayList<>();


        try { /* Attempt to read the file. */

            br = new BufferedReader(new FileReader(scicrCSVFile));

            /* Do nothing with the first line, which contains column names. */
            line = br.readLine();

            /* While we didn't hit the end of the file. */
            while ((line = br.readLine()) != null)
            {
                // use comma as separator
                String[] nextLine = line.split(cvsSplitBy);
                SCICRObject newScicrObj;

                try {
                    /* Create the SCICRObject. */
                    newScicrObj = new SCICRObject(nextLine[type], nextLine[number], nextLine[title], nextLine[build], nextLine[baseline]);

                    valcodeMap.get("Build").add(nextLine[build]);

                    newScicrObj.setOrigLine(line);
                    /* Place it into the collection. */
                    scicrObjCollection.add( newScicrObj );

                } catch (Exception e)
                {
                    newScicrObj = new SCICRObject();
                    newScicrObj.setOrigLine(line);

                    errCollection.add(newScicrObj);
                    errCount++;
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        transferBuildToValCode();
        moveValcodeToMem();
        /* Call to method that will write the collection to the database. */
        transferSCICRData( scicrObjCollection, errCount, errCollection );
    }

    /**
     * Writes the collection of SCICRObjects to the database table.
     * @param collection The collection of SCICRObjects to write.
     * @throws SQLException If the query was unable to be completed.
     */
    private static void transferSCICRData(ArrayList<SCICRObject> collection, int errCount, ArrayList<SCICRObject> errCollection) throws SQLException
    {
        // The query to insert the data from the fields.
        String insertQuery =    "INSERT INTO SCICR " +
                                            "([number], [type], [title], [build_val_code_id], [baseline_id]) " +
                                "VALUES (?, ?, ?, ?, ?)";

        /* Create a new statement. */
        PreparedStatement st = conn.prepareStatement(insertQuery);

        /* Loop through the collection. */
        int size = collection.size();
        for (int i = 0; i < size; i++)
        {
            /* The current object being evaluated. */
            SCICRObject curr = collection.get(i);
            try {
                /* Parse all of the information and stage for writing. */
                st.setString(1, curr.getNumber());

                /* We need to check if it is an SC or ICR based on value from old database table. */
                String currType = (curr.getType().equals("0")) ? "SC" : "ICR";
                st.setString(2, currType);

                st.setString(3, curr.getTitle());
                //st.setString(4, curr.getBuild());
                st.setInt(4, valcodeIDMap.get(curr.getBuild()));
                st.setInt(5, baselineIDMap.get(curr.getBaseline()));


                // Perform the update inside of the table of the database.
                st.executeUpdate();
            } catch (Exception e) {
                if (!errCollection.contains(curr)) {
                    errCount++;
                    errCollection.add(curr);
                }
            }
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, errCount + " errors found.", ButtonType.OK);
        alert.showAndWait();

        if (!errCollection.isEmpty())
        {
            writeSCICRErrors(errCollection);
        }


        /* Move the SC/ICR number and its ID to memory. */
        moveScicrIDToMem();
    }

    /**
     * Moves each SC/ICR number with its ID to a hash map so that we can get the
     * performance boost without having to read from the database each time.
     * @throws SQLException If the query could not be completed.
     */
    private static void moveScicrIDToMem() throws SQLException
    {
        /* Set up the SQL query. */
        String query = "SELECT [scicr_id], [number] FROM SCICR";

        // Create a new statement.
        PreparedStatement st = conn.prepareStatement(query);
        ResultSet rs = st.executeQuery();

        /* While the result has another item in it. */
        while (rs.next())
        {
            scicrIDMap.put(rs.getString("number"), rs.getInt("scicr_id"));
        }
    }

    /**
     * Creates a collection RequirementObjects that will be written into the database.
     * @param csc CSC index.
     * @param csu CSU index.
     * @param doors Doors ID index.
     * @param paragraph Paragraph index.
     * @param baseline Baseline index.
     * @param build Build index.
     * @param scicr SCICR index.
     * @param capability Capability index.
     * @param add Add index.
     * @param change Change index.
     * @param delete Delete index.
     * @param unitTest Unit test index.
     * @param design Design index.
     * @param code Code index.
     * @param integration Integration index.
     * @param ri RI index.
     * @param rommer Rommer index.
     * @param program Program index.
     * @throws SQLException If the query could not be completed.
     */
    public static void performReqTransfer(int csc,      int csu,     int doors,       int paragraph,
                                          int baseline, int build,   int scicr,       int capability,
                                          int add,      int change,  int delete,      int unitTest,
                                          int design,   int code,    int integration, int ri,
                                          int rommer,   int program) throws SQLException
    {
        BufferedReader br = null;           /* Used to read the file. */
        String line = "";                   /* The next line to be read in the file. */
        String cvsSplitBy = ",";            /* The split by value to make the array. */
        ArrayList<RequirementObject> reqObjCollection = new ArrayList<>();  /* The collection of RequirementObjects */

        int errCount = 0;
        ArrayList<RequirementObject> errCollection = new ArrayList<>();

        try {

            br = new BufferedReader(new FileReader(requirementsCSVFile));

            /* Do nothing with the first line, which contains column names. */
            line = br.readLine();

            /* While there is still another line to read in the file. */
            while ((line = br.readLine()) != null)
            {
                // use comma as separator
                String[] nextLine = line.split(cvsSplitBy);
                RequirementObject newReqObj;

                try {
                /* Create a new RequirementObject. */
                    newReqObj = new RequirementObject(
                            nextLine[csc], nextLine[csu], nextLine[doors], nextLine[paragraph],
                            nextLine[baseline], nextLine[build], nextLine[scicr], nextLine[capability],
                            nextLine[add], nextLine[change], nextLine[delete], nextLine[unitTest],
                            nextLine[design], nextLine[code], nextLine[integration], nextLine[ri],
                            nextLine[rommer], nextLine[program]);

                    newReqObj.setOrigLine(line);

                    /* Add it to the collection. */
                    reqObjCollection.add( newReqObj );

                } catch (Exception e) {
                    newReqObj = new RequirementObject();
                    newReqObj.setOrigLine(line);

                    errCollection.add(newReqObj);
                    errCount++;
                }

                 /* Let's create the ValCode map while we are observing the data
                    * for the RequirementObject. */
                valcodeMap.get("Capability").add(nextLine[capability]);
                valcodeMap.get("CSU").add(nextLine[csu]);
                valcodeMap.get("CSC").add(nextLine[csc]);
                valcodeMap.get("Program").add(nextLine[program]);
                valcodeMap.get("RI").add(nextLine[ri]);
                valcodeMap.get("Rommer").add(nextLine[rommer]);
                //valcodeMap.get("Build").add(nextLine[build]);
            }

        } catch (Exception e) {

        }

        /* Write the ValCode data to its table.
        * It is vital that is is completed before writing Req data. */
        transferValCodeData();

        /* Write the collection of RequirementObjects to the table. */
        transferReqData( reqObjCollection, errCount, errCollection);
    }

    /**
     * Writes the ValCode map values to the database.
     */
    private static void transferValCodeData() throws SQLException
    {
        // The query to insert the data from the fields.
        String insertQuery =    "INSERT INTO ValCodes ([field_name], [field_value], [order_id]) " +
                                "VALUES (?, ?, ?)";

        // Create a new statement.
        PreparedStatement st = conn.prepareStatement(insertQuery);

        Iterator<String> iter;          /* To iterate through each hash set in the hash map. */
        int orderID = 1;                /* Writes the order_ID number. It's a count of the values. */

        iter = valcodeMap.get("Capability").iterator();
        while (iter.hasNext())
        {
            st.setString(1, "capability");
            st.setString(2, iter.next());
            st.setInt(3, orderID++);

            st.executeUpdate();
        }

        orderID = 1;
        iter = valcodeMap.get("CSU").iterator();
        while (iter.hasNext())
        {
            st.setString(1, "csu");
            st.setString(2, iter.next());
            st.setInt(3, orderID++);

            st.executeUpdate();
        }

        orderID = 1;
        iter = valcodeMap.get("CSC").iterator();
        while (iter.hasNext())
        {
            st.setString(1, "csc");
            st.setString(2, iter.next());
            st.setInt(3, orderID++);

            st.executeUpdate();
        }

        orderID = 1;
        iter = valcodeMap.get("Program").iterator();
        while (iter.hasNext())
        {
            st.setString(1, "program");
            st.setString(2, iter.next());
            st.setInt(3, orderID++);

            st.executeUpdate();
        }

        orderID = 1;
        iter = valcodeMap.get("RI").iterator();
        while (iter.hasNext())
        {
            st.setString(1, "responsible_individual");
            st.setString(2, iter.next());
            st.setInt(3, orderID++);

            st.executeUpdate();
        }

        orderID = 1;
        iter = valcodeMap.get("Rommer").iterator();
        while (iter.hasNext())
        {
            st.setString(1, "rommer");
            st.setString(2, iter.next());
            st.setInt(3, orderID++);

            st.executeUpdate();
        }


        /* Move the ValCode values into memory for the performance boost. */
        moveValcodeToMem();
    }

    private static void transferBuildToValCode() throws SQLException
    {
        // The query to insert the data from the fields.
        String insertQuery =    "INSERT INTO ValCodes ([field_name], [field_value], [order_id]) " +
                "VALUES (?, ?, ?)";

        // Create a new statement.
        PreparedStatement st = conn.prepareStatement(insertQuery);

        int orderID = 1;
        Iterator<String> iter = valcodeMap.get("Build").iterator();

        while (iter.hasNext())
        {
            st.setString(1, "build");
            st.setString(2, iter.next());
            st.setInt(3, orderID++);

            st.executeUpdate();
        }
    }

    /**
     * Writes the ValCodes into memory for better performance.
     */
    private static void moveValcodeToMem() throws SQLException
    {
        // The query to insert the data from the fields.
        String query = "SELECT [field_value], [val_id] FROM ValCodes";

        // Create a new statement.
        PreparedStatement st = conn.prepareStatement(query);
        ResultSet rs = st.executeQuery();


        valcodeIDMap = new HashMap<>();
        while (rs.next())
        {
            valcodeIDMap.put(rs.getString("field_value"), rs.getInt("val_id"));
        }
    }

    /**
     * Writes the collection of RequirementObjects to the database.
     * @param collection The collection of RequirementObjects.
     * @throws SQLException If the query could not be completed.
     */
    private static void transferReqData(ArrayList<RequirementObject> collection, int errCount, ArrayList<RequirementObject> errCollection) throws SQLException
    {
        // The query to insert the data from the fields.
        String insertQuery =    "INSERT INTO Requirement ([scicr_id], [csc_val_code_id], [csu_val_code_id], [doors_id], " +
                                             "[paragraph], [capability_val_code_id], [num_lines_added], [num_lines_changed], " +
                                             "[num_lines_deleted], [design_percentage], [code_percentage], [unit_test_percentage], " +
                                             "[integration_percentage], [responsible_individual_val_code_id], [rommer_val_code_id], [program_val_code_id]) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Create a new statement.
        PreparedStatement st = conn.prepareStatement(insertQuery);

        /* Loop through the collection. */
        int size = collection.size();
        for (int i = 0; i < size; i++) {
            RequirementObject curr = collection.get(i);
            try {
                /* Parse all of the information and stage for writing. */
                st.setInt(1, scicrIDMap.get(curr.getScicr()));
                st.setInt(2, valcodeIDMap.get(curr.getCsc()));
                st.setInt(3, valcodeIDMap.get(curr.getCsu()));
                st.setString(4, curr.getDoors());
                st.setString(5, curr.getParagraph());
                st.setInt(6, valcodeIDMap.get(curr.getCapability()));
                st.setDouble(7, Double.parseDouble(curr.getAdd()));
                st.setDouble(8, Double.parseDouble(curr.getChange()));
                st.setDouble(9, Double.parseDouble(curr.getDelete()));
                st.setDouble(10, Double.parseDouble(curr.getDesign()));
                st.setDouble(11, Double.parseDouble(curr.getCode()));
                st.setDouble(12, Double.parseDouble(curr.getUnitTest()));
                st.setDouble(13, Double.parseDouble(curr.getIntegration()));
                st.setInt(14, valcodeIDMap.get(curr.getRi()));
                st.setInt(15, valcodeIDMap.get(curr.getRommer()));
                st.setInt(16, valcodeIDMap.get(curr.getProgram()));
                //st.setInt(17, valcodeIDMap.get(curr.getBuild()));

                // Perform the update inside of the table of the database.
                st.executeUpdate();
            } catch (Exception e) {
                if (!errCollection.contains(curr)) {
                    errCount++;
                    errCollection.add(curr);
                }
            }
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, errCount + " errors found.", ButtonType.OK);
        alert.showAndWait();

        if (!errCollection.isEmpty())
        {
            writeReqErrors(errCollection);
        }
    }

    private static void writeEstErrors(ArrayList<EstimationObject> errCollection)
    {
        String path = fileHandler.getPathWithFileChooser();

        // Get the new file.
        File file = new File(path + "/EstimationErrors.txt");

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));

            for (EstimationObject curr : errCollection)
            {
                bw.write(curr.getOrigLine());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (Exception e) {

        }
    }

    private static void writeSCICRErrors(ArrayList<SCICRObject> errCollection)
    {
        String path = fileHandler.getPathWithFileChooser();

        // Get the new file.
        File file = new File(path + "/SCICRErrors.txt");

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));

            for (SCICRObject curr : errCollection)
            {
                bw.write(curr.getOrigLine());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (Exception e) {

        }
    }

    private static void writeReqErrors(ArrayList<RequirementObject> errCollection)
    {
        String path = fileHandler.getPathWithFileChooser();

        // Get the new file.
        File file = new File(path + "/RequirementErrors.txt");

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));

            for (RequirementObject curr : errCollection)
            {
                bw.write(curr.getOrigLine());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (Exception e) {

        }
    }

}
