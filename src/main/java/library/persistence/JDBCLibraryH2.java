package library.persistence;


import library.model.client.Client;
import library.model.document.Book;
import library.model.document.Cd;
import library.model.document.Dvd;

import java.sql.*;
import java.util.Calendar;

public class JDBCLibraryH2 implements JDBCLibrary{

    private final String DB_URL = "jdbc:h2:~/library";

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

    private void handleException(Exception exception) {
        if (exception instanceof SQLException sqlException) {
            System.out.println("Error Code: " + sqlException.getErrorCode());
            System.out.println("SQL State: " + sqlException.getSQLState());
        }
        System.out.println("SQLException message: " + exception.getMessage());
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

            stmt.close();
            conn.close();
        } catch(Exception e) {
            handleException(e);
        } finally {

            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
                handleException(se2);
            }
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                handleException(se);
            }
        }

    }

    public void save(Client client) {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement()
        ) {
            System.out.println("Inserting records into the table...");
            String sql = "INSERT INTO CLIENT VALUES ("+ client.getId() +
                    ",'"+ client.getFirstName()+"'" +
                    ",'"+ client.getLastName()+"'" +
                    ",'"+ client.getAddress()+"'" +
                    ",'"+ client.getEMail()+"'"+
                    ",'"+ client.getPostalCode()+"'"+
                    "," + client.getTotalFees() +
                    ");";
            stmt.executeUpdate(sql);
            System.out.println("Inserted records into the table...");
        } catch (SQLException e) {
            handleException(e);
        }
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


    public void createTableBook() {
        try {
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            System.out.println("Creating table Book in given database...");
            stmt = conn.createStatement();
            String sql =  "CREATE TABLE BOOK " +
                    "(id INTEGER not NULL, " +
                    " title VARCHAR(255) NOT NULL, " +
                    " author VARCHAR(255) NOT NULL, " +
                    " editor VARCHAR(255) NOT NULL, " +
                    " publicationYear Date NOT NULL, " +
                    " nbpages INTEGER NOT NULL, " +
                    " genre VARCHAR(255) NOT NULL , " +
                    " shelfnumber INTEGER NOT NULL , " +
                    " isoutofstock BOOLEAN NOT NULL , " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(sql);
            System.out.println("Created table Book in given database...");
        } catch(Exception e) {
            handleException(e);
        } finally {
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
                handleException(se2);
            }
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                handleException(se);
            }
        }

    }


    public void save(Book book) {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement()
        ) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(book.getPublicationYear());
            String year = Integer.toString(calendar.get(Calendar.YEAR)) ;

            System.out.println("Inserting records into the table...");
            String sql = "INSERT INTO BOOK VALUES ("+ book.getId() +
                    ",'"+ book.getTitle()+"'" +
                    ",'"+ book.getAuthor()+"'" +
                    ",'"+book.getEditor()+"'" +
                    ", parsedatetime("+ year +", 'yyyy')"+
                    ",'"+ book.getNbPages()+"'"+
                    ",'" + book.getGenre() + "'" +
                    "," + book.getShelfNumber() +
                    "," + book.isOutOfStock() +
                    ");";
            stmt.executeUpdate(sql);
            System.out.println("Inserted records into the table...");
        } catch (SQLException e) {
            handleException(e);
        }

    }


    public Book getBook(int bookId) {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            PreparedStatement ps = conn.prepareStatement("SELECT id,title, author, editor, publicationyear, nbpages, genre, shelfnumber, isoutofstock FROM book WHERE id = "+ bookId);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return new Book(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("editor"),
                        rs.getDate("publicationyear"),
                        rs.getInt("nbpages"),
                        rs.getString("genre"),
                        rs.getInt("shelfnumber"),
                        rs.getBoolean("isoutofstock")
                );
            }

        } catch (SQLException e) {
            handleException(e);
            return null;
        }
    }

    @Override
    public void createTableCd() {
        try {
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            System.out.println("Creating table CD in given database...");
            stmt = conn.createStatement();
            String sql =  "CREATE TABLE CD " +
                    "(id INTEGER not NULL, " +
                    " title VARCHAR(255) NOT NULL, " +
                    " author VARCHAR(255) NOT NULL, " +
                    " editor VARCHAR(255) NOT NULL, " +
                    " publicationYear Date NOT NULL, " +
                    " nbscenes INTEGER NOT NULL, " +
                    " genre VARCHAR(255) NOT NULL , " +
                    " shelfnumber INTEGER NOT NULL , " +
                    " isoutofstock BOOLEAN NOT NULL , " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(sql);
            System.out.println("Created table CD in given database...");
        } catch(Exception e) {
            handleException(e);
        } finally {
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
                handleException(se2);
            }
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                handleException(se);
            }
        }
    }

    @Override
    public void save(Cd cd) {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement()
        ) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(cd.getPublicationYear());
            String year = Integer.toString(calendar.get(Calendar.YEAR)) ;

            System.out.println("Inserting records into the table...");
            String sql = "INSERT INTO CD VALUES ("+ cd.getId() +
                    ",'"+ cd.getTitle()+"'" +
                    ",'"+ cd.getAuthor()+"'" +
                    ",'"+cd.getEditor()+"'" +
                    ", parsedatetime("+ year +", 'yyyy')"+
                    ",'"+ cd.getNbScenes()+"'"+
                    ",'" + cd.getGenre() + "'" +
                    "," + cd.getShelfNumber() +
                    "," + cd.isOutOfStock() +
                    ");";
            stmt.executeUpdate(sql);
            System.out.println("Inserted records into the table...");
        } catch (SQLException e) {
            handleException(e);
        }

    }

    @Override
    public Cd getCd(int cdId) {

        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            PreparedStatement ps = conn.prepareStatement("SELECT id,title, author, editor, publicationyear, nbscenes, genre, shelfnumber, isoutofstock FROM CD WHERE id = "+ cdId);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return new Cd(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("editor"),
                        rs.getDate("publicationyear"),
                        rs.getInt("nbscenes"),
                        rs.getString("genre"),
                        rs.getInt("shelfnumber"),
                        rs.getBoolean("isoutofstock")
                );
            }

        } catch (SQLException e) {
            handleException(e);
            return null;
        }
    }

    @Override
    public void createTableDvd() {
        try {
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            System.out.println("Creating table DVD in given database...");
            stmt = conn.createStatement();
            String sql =  "CREATE TABLE DVD " +
                    "(id INTEGER not NULL, " +
                    " title VARCHAR(255) NOT NULL, " +
                    " author VARCHAR(255) NOT NULL, " +
                    " editor VARCHAR(255) NOT NULL, " +
                    " publicationYear Date NOT NULL, " +
                    " nbscenes INTEGER NOT NULL, " +
                    " genre VARCHAR(255) NOT NULL , " +
                    " shelfnumber INTEGER NOT NULL , " +
                    " isoutofstock BOOLEAN NOT NULL , " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(sql);
            System.out.println("Created table DVD in given database...");
        } catch(Exception e) {
            handleException(e);
        } finally {
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
                handleException(se2);
            }
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                handleException(se);
            }
        }
    }

    @Override
    public void save(Dvd dvd) {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement()
        ) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dvd.getPublicationYear());
            String year = Integer.toString(calendar.get(Calendar.YEAR)) ;

            System.out.println("Inserting records into the table...");
            String sql = "INSERT INTO DVD VALUES ("+ dvd.getId() +
                    ",'"+ dvd.getTitle()+"'" +
                    ",'"+ dvd.getAuthor()+"'" +
                    ",'"+dvd.getEditor()+"'" +
                    ", parsedatetime("+ year +", 'yyyy')"+
                    ",'"+ dvd.getNbScenes()+"'"+
                    ",'" + dvd.getGenre() + "'" +
                    "," + dvd.getShelfNumber() +
                    "," + dvd.isOutOfStock() +
                    ");";
            stmt.executeUpdate(sql);
            System.out.println("Inserted records into the table...");
        } catch (SQLException e) {
            handleException(e);
        }

    }

    @Override
    public Dvd getDvd(int dvdId) {

        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            PreparedStatement ps = conn.prepareStatement("SELECT id,title, author, editor, publicationyear, nbscenes, genre, shelfnumber, isoutofstock FROM DVD WHERE id = "+ dvdId);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return new Dvd(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("editor"),
                        rs.getDate("publicationyear"),
                        rs.getInt("nbscenes"),
                        rs.getString("genre"),
                        rs.getInt("shelfnumber"),
                        rs.getBoolean("isoutofstock")
                );
            }

        } catch (SQLException e) {
            handleException(e);
            return null;
        }
    }

}
