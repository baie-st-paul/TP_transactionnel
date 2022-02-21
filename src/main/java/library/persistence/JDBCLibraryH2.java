package library.persistence;


import library.model.client.Client;

import java.sql.*;

public class JDBCLibraryH2 implements JDBCLibrary{

    private final String DB_URL = "jdbc:h2:~/library";

    //  Database credentials
    private final String USER = "username";
    private final String PASS = "password";

    private Connection conn = null;
    private Statement stmt = null;

    {
        try {
            String JDBC_DRIVER = "org.h2.Driver";
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void createTableClient() {
        try {
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            System.out.println("Creating table Client in given database...");
            stmt = conn.createStatement();
            String sql =  "CREATE TABLE CLIENT " +
                    "(id INTEGER not NULL, " +
                    " firstname VARCHAR(255), " +
                    " lastname VARCHAR(255), " +
                    " address VARCHAR(255), " +
                    " email VARCHAR(255), " +
                    " postalcode VARCHAR(255), " +
                    " totalFees NUMBER, " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(sql);
            System.out.println("Created table Client in given database...");

            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();
        } catch(Exception e) {
            handleException(e);
        } finally {
            //finally block used to close resources
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
                handleException(se2);
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                handleException(se);
            } //end finally try
        } //end try

    }

    public void save(Client client) {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement()
        ) {
            System.out.println("Inserting records into the table...");
            String sql = "INSERT INTO CLIENT VALUES ("+ client.getId() +
                    ",\'"+ client.getFirstName()+"\'" +
                    ",\'"+ client.getLastName()+"\'" +
                    ",\'"+ client.getAddress()+"\'" +
                    ",\'"+ client.getEMail()+"\'"+
                    ",\'"+ client.getPostalCode()+"\'"+
                    "," + client.getTotalFees() +
                    ");";
            stmt.executeUpdate(sql);
            System.out.println("Inserted records into the table...");
        } catch (SQLException e) {
            handleException(e);
        }
    }

    private void handleException(Exception exception) {
        if (exception instanceof SQLException sqlException) {
            System.out.println("Error Code: " + sqlException.getErrorCode());
            System.out.println("SQL State: " + sqlException.getSQLState());
        }
        System.out.println("SQLException message: " + exception.getMessage());
        System.out.println("Stacktrace: ");
        exception.printStackTrace();
    }

    public Client getClient(int clientId){
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            PreparedStatement ps = conn.prepareStatement("SELECT id,firstname, lastname, address, email, postalcode, totalfees FROM client WHERE id = "+ clientId);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return new Client(rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("address"),
                        rs.getString("email"),
                        rs.getString("postalcode"),
                        rs.getDouble("totalfees")
                        );
            }

        } catch (SQLException e) {
            handleException(e);
            return null;
        }
    }

}
