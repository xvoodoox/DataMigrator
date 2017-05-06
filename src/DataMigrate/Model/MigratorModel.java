package DataMigrate.Model;

import DataMigrate.FileHandler.FileHandler;
import DataMigrate.TransferObjects.EstimationObject;
import DataMigrate.TransferObjects.RequirementObject;
import DataMigrate.TransferObjects.SCICRObject;
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
    public static Connection conn = null;
    public static String dbPath = "jdbc:ucanaccess://";

    private static FileHandler fileHandler = new FileHandler();

    public static File databaseFile;
    public static File estimationCSVFile;
    public static File scicrCSVFile;
    public static File valCodeCSVFile;
    public static File requirementsCSVFile;

    public static String[] estimationHeaders = {};
    public static String[] scicrHeaders = {};
    public static String[] requirementsHeaders = {};
    public static String[] valTypes = { "Capability", "CSU", "CSC", "Program",
                                        "RI", "Rommer", "Build"  };


    public static ArrayList<String[]> estimationBaseData = new ArrayList<>();
    public static HashMap<String, HashSet<String>> valcodeMap;
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


    public static boolean findDatabaseFile()
    {
        databaseFile = fileHandler.useFileChooser("*.mdb");
        dbPath = dbPath + databaseFile.getPath();

        if (attemptConnection()) {
            return true;
        } else {
            return false;
        }
    }

    public static void findEstimationCSV()
    {
        estimationCSVFile = fileHandler.useFileChooser();
        estimationHeaders = readColumnNames(estimationCSVFile);

    }

    public static void findSCICRCSV()
    {
        scicrCSVFile = fileHandler.useFileChooser();
        scicrHeaders = readColumnNames(scicrCSVFile);
    }

    public static void findValCodeCSV()
    {
        valCodeCSVFile = fileHandler.useFileChooser();
    }

    public static void findRequirementsCSV()
    {
        requirementsCSVFile = fileHandler.useFileChooser();
        requirementsHeaders = readColumnNames(requirementsCSVFile);
    }

    private static boolean attemptConnection()
    {
        try {
            conn = DriverManager.getConnection(dbPath);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }



    private static String[] readColumnNames(File file)
    {
        String csvFile = file.getPath();
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String[] headers = {};

        try {

            br = new BufferedReader(new FileReader(csvFile));
            line = br.readLine();
            headers = line.split(cvsSplitBy);
            System.out.println(headers[0]);
//            while ((line = br.readLine()) != null) {
//
//                // use comma as separator
//                String[] country = line.split(cvsSplitBy);
//
//                //System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");
//
//            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return headers;
    }


    public static void performROMTransfer( int baseline, int cprs, int month, int day, int doc, int upgrade, int maint,
                                           int ddr, int unit, int integ, int design, int code, int estDefault, int date) throws SQLException
    {
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        ArrayList<EstimationObject> estObjCollection = new ArrayList<>();

        try {

            br = new BufferedReader(new FileReader(estimationCSVFile));
            line = br.readLine();


            while ((line = br.readLine()) != null)
            {
                // use comma as separator
                String[] nextLine = line.split(cvsSplitBy);

                EstimationObject newEstObj = new EstimationObject(
                        nextLine[baseline], nextLine[day], nextLine[month], nextLine[cprs], nextLine[estDefault], nextLine[doc],
                        nextLine[date], nextLine[upgrade], nextLine[maint], nextLine[ddr], Double.parseDouble(nextLine[design]),
                        Double.parseDouble(nextLine[code]), Double.parseDouble(nextLine[integ]), Double.parseDouble(nextLine[unit])
                        );

                estObjCollection.add( newEstObj );
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        transferROMData( estObjCollection );
    }


    private static void transferROMData(ArrayList<EstimationObject> collection) throws SQLException
    {
            // The query to insert the data from the fields.
            String insertQuery =    "INSERT INTO EstimationBaseData ([baseline], [cprs], [slocsday], [slocsmonth], " +
                                                                    "[defaultSLOCS], [CPDDdocument], [CPDDdate], " +
                                                                    "[budgetUpgrade], [budgetMaintenance], [ddrcwtslocs]," +
                                                                    "[designWeight], [codeWeight], [integrationWeight], " +
                                                                    "[unitTestingWeight]) " +
                                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // Create a new statement.
            PreparedStatement st = conn.prepareStatement(insertQuery);

            int size = collection.size();
            for (int i = 0; i < size; i++)
            {
                EstimationObject curr = collection.get(i);

                /* Parse all of the information and stage for writing. */
                st.setString(1, curr.getBaseline());
                st.setString(2, curr.getCprs());
                st.setString(3, curr.getSlocsDay());
                st.setString(4, curr.getSlocsMonth());
                st.setString(5, curr.getDefaultSlocs());
                st.setString(6, curr.getCpddDoc());
                st.setString(7, curr.getCpddDate());
                st.setString(8, curr.getBudUpgrade());
                st.setString(9, curr.getBudMaint());
                st.setString(10, curr.getDdrcwtSlocs());
                st.setDouble(11, curr.getDesign());
                st.setDouble(12, curr.getCode());
                st.setDouble(13, curr.getInteg());
                st.setDouble(14, curr.getUnitTest());


                // Perform the update inside of the table of the database.
                st.executeUpdate();
            }
    }


    public static void performSCICRTransfer(int type, int number, int title, int build, int baseline) throws SQLException
    {
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        ArrayList<SCICRObject> scicrObjCollection = new ArrayList<>();

        try {

            br = new BufferedReader(new FileReader(scicrCSVFile));
            line = br.readLine();


            while ((line = br.readLine()) != null)
            {
                // use comma as separator
                String[] nextLine = line.split(cvsSplitBy);

                SCICRObject newScicrObj = new SCICRObject(nextLine[type], nextLine[number], nextLine[title], nextLine[build], nextLine[baseline]);


                scicrObjCollection.add( newScicrObj );
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        transferSCICRData( scicrObjCollection );
    }


    private static void transferSCICRData(ArrayList<SCICRObject> collection) throws SQLException
    {
        // The query to insert the data from the fields.
        String insertQuery =    "INSERT INTO SCICRData " +
                                            "([Type], [Number], [Title], [Build], [Baseline]) " +
                                "VALUES (?, ?, ?, ?, ?)";

        // Create a new statement.
        PreparedStatement st = conn.prepareStatement(insertQuery);

        int size = collection.size();
        for (int i = 0; i < size; i++)
        {
            SCICRObject curr = collection.get(i);

            /* Parse all of the information and stage for writing. */
            String currType = (curr.getType().equals("0")) ? "SC" : "ICR";
            st.setString(1, currType);
            st.setString(2, curr.getNumber());
            st.setString(3, curr.getTitle());
            st.setString(4, curr.getBuild());
            st.setString(5, curr.getBaseline());


            // Perform the update inside of the table of the database.
            st.executeUpdate();
        }
    }

    public static void performReqTransfer(int csc,      int csu,     int doors,       int paragraph,
                                          int baseline, int build,   int scicr,       int capability,
                                          int add,      int change,  int delete,      int unitTest,
                                          int design,   int code,    int integration, int ri,
                                          int rommer,   int program) throws SQLException
    {
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        ArrayList<RequirementObject> reqObjCollection = new ArrayList<>();

        try {

            br = new BufferedReader(new FileReader(requirementsCSVFile));
            line = br.readLine();


            while ((line = br.readLine()) != null)
            {
                // use comma as separator
                String[] nextLine = line.split(cvsSplitBy);

                RequirementObject newReqObj = new RequirementObject(
                                                    nextLine[csc],      nextLine[csu],    nextLine[doors],       nextLine[paragraph],
                                                    nextLine[baseline], nextLine[build],  nextLine[scicr],       nextLine[capability],
                                                    nextLine[add],      nextLine[change], nextLine[delete],      nextLine[unitTest],
                                                    nextLine[design],   nextLine[code],   nextLine[integration], nextLine[ri],
                                                    nextLine[rommer],   nextLine[program]);


                reqObjCollection.add( newReqObj );


                valcodeMap.get("Capability").add(nextLine[capability]);
                valcodeMap.get("CSU").add(nextLine[csu]);
                valcodeMap.get("CSC").add(nextLine[csc]);
                valcodeMap.get("Program").add(nextLine[program]);
                valcodeMap.get("RI").add(nextLine[ri]);
                valcodeMap.get("Rommer").add(nextLine[rommer]);
                valcodeMap.get("Build").add(nextLine[build]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        transferValCodeData();
        transferReqData( reqObjCollection );
    }


    private static void transferValCodeData() throws SQLException
    {
        // The query to insert the data from the fields.
        String insertQuery =    "INSERT INTO Val_Codes ([Field_Name], [Field_Value], [Order_Id]) " +
                                "VALUES (?, ?, ?)";

        // Create a new statement.
        PreparedStatement st = conn.prepareStatement(insertQuery);

        Iterator<String> iter;
        int orderID = 1;

        iter = valcodeMap.get("Capability").iterator();
        while (iter.hasNext())
        {
            st.setString(1, "capability");
            st.setString(2, iter.next());
            st.setInt(3, orderID++);

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

        orderID = 1;
        iter = valcodeMap.get("Build").iterator();
        while (iter.hasNext())
        {
            st.setString(1, "build");
            st.setString(2, iter.next());
            st.setInt(3, orderID++);

            st.executeUpdate();
        }
    }

    private static void transferReqData(ArrayList<RequirementObject> collection) throws SQLException
    {
        // The query to insert the data from the fields.
        String insertQuery =    "INSERT INTO RequirementsData " +
                                            "([csc], [csu], [doors_id], [paragraph], [baseline], [scicr], " +
                                            "[capability], [add], [change], [delete], [design], [code], " +
                                            "[unitTest], [integration], [ri], [rommer], [program], [build]) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Create a new statement.
        PreparedStatement st = conn.prepareStatement(insertQuery);

        int size = collection.size();
        for (int i = 0; i < size; i++)
        {
            RequirementObject curr = collection.get(i);

            /* Parse all of the information and stage for writing. */
            st.setString(1, curr.getCsc());
            st.setString(2, curr.getCsu());
            st.setString(3, curr.getDoors());
            st.setString(4, curr.getParagraph());
            st.setString(5, curr.getBaseline());
            st.setString(6, curr.getScicr());
            st.setString(7, curr.getCapability());
            st.setString(8, curr.getAdd());
            st.setString(9, curr.getChange());
            st.setString(10, curr.getDelete());
            st.setString(11, curr.getDesign());
            st.setString(12, curr.getCode());
            st.setString(13, curr.getUnitTest());
            st.setString(14, curr.getIntegration());
            st.setString(15, curr.getRi());
            st.setString(16, curr.getRommer());
            st.setString(17, curr.getProgram());
            st.setString(18, curr.getBuild());

            // Perform the update inside of the table of the database.
            st.executeUpdate();
        }
    }
}
