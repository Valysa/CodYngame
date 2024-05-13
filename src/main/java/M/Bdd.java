package M;
import java.sql.*;




public class Bdd {



    // Connect to the database

    public static Connection start (){

        Connection con = null;

        try{
            con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","");
            Statement stmt = con.createStatement();

            String sql = "CREATE DATABASE IF NOT EXISTS Exercise";
            stmt.executeUpdate(sql);

            stmt.close();
            end(con);

            con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/Exercise","root","");

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
                    + "InputData TEXT,\n"
                    + "OutputData TEXT,\n"
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

                exo = new Exercise(
                        rs.getInt("Id"),
                        rs.getInt("ExoType"),
                        rs.getString("ExoName"),
                        rs.getString("Instruction"),
                        rs.getInt("SolutionLang"),
                        rs.getString("SolutionCode"),
                        rs.getString("InputData"),
                        rs.getString("OutputData"),
                        rs.getInt("NbTry"),
                        rs.getInt("NbSucess"),
                        rs.getInt("NbSessionSucess"),
                        rs.getInt("NbFirstTry")
                );
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

    public static void addEx(int ExoType, String ExoName, String Instruction, int SolutionLang, String SolutionCode, String InputData, String OutputData, int NbTry, int NbSucess, int NbSessionSucess, int NbFirstTry) {

        String sql = "INSERT INTO Question (ExoType, ExoName, Instruction, SolutionLang, SolutionCode, InputData, OutputData, NbTry, NbSucess, NbSessionSucess, NbFirstTry) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            Connection con = start();
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, ExoType);
            pstmt.setString(2, ExoName);
            pstmt.setString(3, Instruction);
            pstmt.setInt(4, SolutionLang);
            pstmt.setString(5, SolutionCode);
            pstmt.setString(6, InputData);
            pstmt.setString(7, OutputData);
            pstmt.setInt(8, NbTry);
            pstmt.setInt(9, NbSucess);
            pstmt.setInt(10, NbSessionSucess);
            pstmt.setInt(11, NbFirstTry);


            pstmt.executeUpdate();

            end(con);
            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Error during the addition of a new question : " + e.getMessage());
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


