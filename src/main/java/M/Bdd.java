package M;
import launcher.Main;

import java.sql.*;




public class Bdd {

    static String port ;
    static String user;
    static String password;

    // Connect to the database

    public static Connection start (){

        Connection con = null;

        try{
            con =  DriverManager.getConnection("jdbc:mysql://localhost:" + port + "/",user,password);
            Statement stmt = con.createStatement();

            String sql = "CREATE DATABASE IF NOT EXISTS Exercise";
            stmt.executeUpdate(sql);

            stmt.close();
            end(con);

            con =  DriverManager.getConnection("jdbc:mysql://localhost:" + port + "/Exercise",user,password);

        } catch (SQLException e) {
            System.out.println("Error during the connexion to the database : " + e.getMessage());
        }

        return con ;
    }



    // Disconnect to the database

    public static void end(Connection con){

        try {
            con.close();

        } catch (SQLException e) {
            System.out.println("Error during the disconnexion to the database : " + e.getMessage());
        }

    }

    public static void idBdd(String p,String u,String pa){
        port = p;
        user = u;
        password = pa;
    }



    // Create the table in the database

    public static void create() {


        try {

            Connection con = start();
            Statement stmt = con.createStatement();


            String sql =
                      "CREATE TABLE IF NOT EXISTS Question (\n"
                    + "Id INT AUTO_INCREMENT PRIMARY KEY,\n"
                    + "ExoType TINYINT,\n"
                    + "ExoName VARCHAR(255),\n"
                    + "Instruction TEXT,\n"
                    + "SolutionLang TINYINT,\n"
                    + "SolutionCode TEXT,\n"
                    + "GeneratorCode TEXT,\n"
                    + "MainCode TEXT,\n"
                    + "NbTry INT,\n"
                    + "NbSucess INT,\n"
                    + "NbSessionSucess INT,\n"
                    + "NbFirstTry INT\n"
                    + ")AUTO_INCREMENT = 1;\n";

            stmt.executeUpdate(sql);

            stmt.close();
            end(con);

        } catch (SQLException e) {
            System.out.println("Error during the creation of the table : " + e.getMessage());
        }

        basicExo();

    }

    public static void basicExo(){

        addEx(0,"Exercise 1","You will be given in entry a list of 10 integer and we ask you to give back their values multiplied by two",0,"int main(){\n\tint entry;\n\tint exit;\n\tfor(int i=0; i<10; i++){\n\t\tscanf(\"%d\",&entry);\n\t\texit = entry*2;\n\t\tprintf(\"%d\\n\",exit);\n\t}\n\treturn 0;\n}","","");
        addEx(0,"Exercise 2","You will be given in entry a list of 10 integer and we ask you to give back their values multiplied by ten",0,"int main(){\n\tint entry;\n\tint exit;\n\tfor(int i=0; i<10; i++){\n\t\tscanf(\"%d\",&entry);\n\t\texit = entry*10;\n\t\tprintf(\"%d\\n\",exit);\n\t}\n\treturn 0;\n}","","");
        addEx(1,"Exercise 3","You will be create a function that calculates the sum of an array",0,"int array_sum1(int array[], int size){\n\tint sum = 0;\n\tfor(int i = 0; i < size; i++){\n\t\tsum += array[i];\n\t}\n\treturn sum;\n}","int array_sum(int array[], int size){\n\n}","#include <stdio.h>\n#include <stdlib.h>\n#include <time.h>\n#include \"soluceExo.c\"\n#include \"userExo.c\"\n\nint run_test(int array[], int size){\n\tint result1 = array_sum1(array, size);\n\tint result2 = array_sum(array, size);\n\tputs(\"\");\n\tif(result1 == result2){\n\t\tprintf(\"Test passed\");\n\t\treturn 0;\n\t}\n\telse{\n\t\tprintf(\"Test failed\");\n\t\treturn 1;\n\t}\n}\n\nint main(){\n\tint failed = 0;\n\tint array1[] = {1,2,3,4,5};\n\tfailed |= run_test(array1,5);\n\tint array2[] = {-1,-2,-3,-4,-5,-6};\n\tfailed |= run_test(array2,5);\n\tint array3[] = {0,0,0};\n\tfailed |= run_test(array3,3);\n\treturn failed;\n}");
        addEx(1,"Exercise 4","You will be create a function that calculates the sum of an array",1,"def array_sum1(array, size):\n\tsum = 0\n\tfor i in range(size):\n\t\tsum += array[i]\n\treturn sum","def array_sum(array, size):\n\t","import random\nimport soluceExo\nimport userExo\n\ndef run_test(array, size):\n\tresult1 = soluceExo.array_sum1(array, size)\n\tresult2 = userExo.array_sum(array, size)\n\tif(result1 == result2):\n\t\tprint(\"Test passed\")\n\t\treturn 0\n\telse:\n\t\tprint(\"Test failed\")\n\t\treturn 1\n\ndef main():\n\tfailed = 0\n\tminSize = 5\n\tmaxSize = 100\n\tminValue = -1000\n\tmaxValue = 1000\n\tarraySize1 = random.randint(minSize, maxSize)\n\tarray1 = [random.randint(minValue, maxValue) for _ in range(arraySize1)]\n\tfailed |= run_test(array1,arraySize1)\n\tarraySize2 = random.randint(minSize, maxSize)\n\tarray2 = [random.randint(minValue, maxValue) for _ in range(arraySize2)]\n\tfailed |= run_test(array2,arraySize2)\n\tarraySize3 = random.randint(minSize, maxSize)\n\tarray3 = [0 for _ in range(arraySize3)]\n\tfailed |= run_test(array3,arraySize3)\n\tarray4 = []\n\tfailed |= run_test(array4,0)\n\treturn failed\n\nmain()");
    }

    // Take a Question (Id) from the database and stock it in the class Excercise
    
    public static Exercise take(int Id){

        Exercise exo = null;
        String sql = "SELECT * FROM Question WHERE Id = ?";

        Connection con = start();

        try {

            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, Id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                if (rs.getInt("ExoType") == 0) {
                    exo = new ExerciseStdinStdout(
                            rs.getInt("Id"),
                            rs.getInt("ExoType"),
                            rs.getString("ExoName"),
                            rs.getString("Instruction"),
                            rs.getInt("SolutionLang"),
                            rs.getString("SolutionCode"),
                            rs.getString("GeneratorCode"),
                            rs.getInt("NbTry"),
                            rs.getInt("NbSucess"),
                            rs.getInt("NbSessionSucess"),
                            rs.getInt("NbFirstTry")
                    );
                }
                else {
                    exo = new ExerciseInclude(
                            rs.getInt("Id"),
                            rs.getInt("ExoType"),
                            rs.getString("ExoName"),
                            rs.getString("Instruction"),
                            rs.getInt("SolutionLang"),
                            rs.getString("SolutionCode"),
                            rs.getString("GeneratorCode"),
                            rs.getString("MainCode"),
                            rs.getInt("NbTry"),
                            rs.getInt("NbSucess"),
                            rs.getInt("NbSessionSucess"),
                            rs.getInt("NbFirstTry")
                    );
                }
            } else {
                System.out.println("No question find with this ID : " + Id);
            }

            end(con);
            pstmt.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Error during the recuperation of the table : " + e.getMessage());
        }

        return exo;
    }



    // Add an exercise to the database (question)

    public static void addEx(int ExoType, String ExoName, String Instruction, int SolutionLang, String SolutionCode, String GeneratorCode, String MainCode) {

        int NbTry = 0;
        int NbSucess = 0;
        int NbSessionSucess = 0;
        int NbFirstTry = 0;

        String checkSql = "SELECT COUNT(*) FROM Question WHERE ExoName = ?";
        String insertSql = "INSERT INTO Question (ExoType, ExoName, Instruction, SolutionLang, SolutionCode, GeneratorCode, MainCode, NbTry, NbSucess, NbSessionSucess, NbFirstTry) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


        try {
            Connection con = start();

            PreparedStatement checkStmt = con.prepareStatement(checkSql);
            checkStmt.setString(1, ExoName);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && rs.getInt(1) == 0) {
                PreparedStatement insertStmt = con.prepareStatement(insertSql);

                insertStmt.setInt(1, ExoType);
                insertStmt.setString(2, ExoName);
                insertStmt.setString(3, Instruction);
                insertStmt.setInt(4, SolutionLang);
                insertStmt.setString(5, SolutionCode);
                insertStmt.setString(6, GeneratorCode);
                insertStmt.setString(7, MainCode);
                insertStmt.setInt(8, NbTry);
                insertStmt.setInt(9, NbSucess);
                insertStmt.setInt(10, NbSessionSucess);
                insertStmt.setInt(11, NbFirstTry);
                insertStmt.executeUpdate();

                rs.close();
                checkStmt.close();
                insertStmt.close();
                end(con);
            }
        } catch (SQLException e) {
            System.out.println("Error during the addition of a new question: " + e.getMessage());
        }
    }






    // Increment all the number of try/sucess of the wanted number in the database

    public static void update(int Id,int NbTry,int NbSucess,int NbSessionSucess,int NbFirstTry) {

        String sql =
                "UPDATE Question SET " +
                "NbTry = NbTry + ?, " +
                "NbSucess = NbSucess + ?, " +
                "NbSessionSucess = NbSessionSucess + ?, " +
                "NbFirstTry = NbFirstTry + ? " +
                " WHERE Id = ?";

        Connection con = start();

        try{
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, NbTry);
            pstmt.setInt(2, NbSucess);
            pstmt.setInt(3, NbSessionSucess);
            pstmt.setInt(4, NbFirstTry);
            pstmt.setInt(5, Id);

            pstmt.executeUpdate();

            end(con);
            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Error during the update of the numbers : " + e.getMessage());
        }
    }



    // Count the number of question in the database

    public static int count(){

        int count = 0;

        String sql = "SELECT COUNT(*) AS count FROM Question";

        Connection con = start();

        try{
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt("count");
            }

            end(con);
            pstmt.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Error during the count : " + e.getMessage());
        }

        return count;
    }



    // Delete the wanted question and remake the id to be correct

    public static void delete(int Id){

        String sqlDelete = "DELETE FROM Question WHERE Id = ?";
        String sqlUpdateIds = "UPDATE Question SET Id = Id - 1 WHERE Id > ?";

        Connection con = start();

        try{
            con.setAutoCommit(false);

            PreparedStatement pstmtDelete = con.prepareStatement(sqlDelete);
            pstmtDelete.setInt(1, Id);
            pstmtDelete.executeUpdate();

            PreparedStatement pstmtUpdateIds = con.prepareStatement(sqlUpdateIds);
            pstmtUpdateIds.setInt(1, Id);
            pstmtUpdateIds.executeUpdate();

            con.commit();

            end(con);
            pstmtDelete.close();
            pstmtUpdateIds.close();

        } catch (SQLException e) {
            System.out.println("Error during the delete of the question : " + e.getMessage());

            try {
                con.rollback();
            } catch (SQLException ex) {
                System.out.println("Error during rollback : " + ex.getMessage());
            }
        }
    }



}

