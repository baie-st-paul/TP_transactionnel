package library.persistence;


import library.model.borrowing.Borrowing;
import library.model.client.Client;
import library.model.document.Book;
import library.model.document.Cd;
import library.model.document.Document;
import library.model.document.Dvd;

import java.sql.*;
import java.util.Calendar;

public class JDBCLibraryH2 implements JDBCLibrary{

    private final String DB_URL = "jdbc:h2:~/library";

    private final String USER = "username";
    private final String PASS = "password";

    private Connection conn = null;
    private Statement stmt = null;

    private int documentId = 0;

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

    public void dropAll(){

        try {
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            System.out.println("DROPING ALL TABLE");
            stmt = conn.createStatement();
            String sql =  "DROP TABLE CLIENT;";
            stmt.executeUpdate(sql);
            stmt = conn.createStatement();
            sql =  "DROP TABLE BOOK;";
            stmt.executeUpdate(sql);
            stmt = conn.createStatement();
            sql =  "DROP TABLE CD;";
            stmt.executeUpdate(sql);
            stmt = conn.createStatement();
            sql =  "DROP TABLE DVD;";
            stmt.executeUpdate(sql);
            stmt = conn.createStatement();
            sql =  "DROP TABLE DOCUMENT;";
            stmt.executeUpdate(sql);
            stmt = conn.createStatement();
            sql =  "DROP TABLE BORROWING;";
            stmt.executeUpdate(sql);
            System.out.println("DROPING FINNISHED");

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

    public void createTableClient() {
        try {
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            String sql =  "CREATE OR REPLACE TABLE CLIENT " +
                    "(clt_id INTEGER not NULL, " +
                    " clt_firstname VARCHAR(255), " +
                    " clt_lastname VARCHAR(255), " +
                    " clt_address VARCHAR(255), " +
                    " clt_email VARCHAR(255), " +
                    " clt_postalcode VARCHAR(255), " +
                    " clt_totalFees NUMBER, " +
                    " PRIMARY KEY ( clt_id ))";
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
            PreparedStatement ps = conn.prepareStatement("SELECT clt_id,clt_firstname, clt_lastname, clt_address, clt_email, clt_postalcode, clt_totalfees FROM client WHERE id = "+ clientId);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return new Client(rs.getInt("clt_id"),
                        rs.getString("clt_firstname"),
                        rs.getString("clt_lastname"),
                        rs.getString("clt_address"),
                        rs.getString("clt_email"),
                        rs.getString("clt_postalcode"),
                        rs.getDouble("clt_totalfees")

                        );
            }

        } catch (SQLException e) {
            handleException(e);
            return null;
        }
    }


    public void createTableBook() {
        try {
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            String sql =  "CREATE OR REPLACE TABLE BOOK " +
                    "(id INTEGER not NULL, " +
                    " bk_title VARCHAR(255) NOT NULL, " +
                    " bk_author VARCHAR(255) NOT NULL, " +
                    " bk_editor VARCHAR(255) NOT NULL, " +
                    " bk_publicationYear Date NOT NULL, " +
                    " bk_nbpages INTEGER NOT NULL, " +
                    " bk_genre VARCHAR(255) NOT NULL , " +
                    " bk_shelfnumber INTEGER NOT NULL , " +
                    " bk_isoutofstock BOOLEAN NOT NULL , " +
                    " doc_id INTEGER UNIQUE NOT NULL , " +
                    " PRIMARY KEY ( id ),"+
                    " FOREIGN KEY ( doc_id ) REFERENCES document(doc_id))";
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
            saveDocument("book");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(book.getPublicationYear());
            String year = Integer.toString(calendar.get(Calendar.YEAR)) ;

            String sql = "INSERT INTO BOOK VALUES ("+ book.getId() +
                    ",'"+ book.getTitle()+"'" +
                    ",'"+ book.getAuthor()+"'" +
                    ",'"+book.getEditor()+"'" +
                    ", parsedatetime("+ year +", 'yyyy')"+
                    ",'"+ book.getNbPages()+"'"+
                    ",'" + book.getGenre() + "'" +
                    "," + book.getShelfNumber() +
                    "," + book.isOutOfStock() +
                    "," + this.documentId +
                    ");";
            stmt.executeUpdate(sql);
            System.out.println("Inserted records into the table...");
        } catch (SQLException e) {
            handleException(e);
        }

    }


    public Book getBook(int bookId) {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            PreparedStatement ps = conn.prepareStatement("SELECT id,bk_title, bk_author, bk_editor, bk_publicationyear, bk_nbpages, bk_genre, bk_shelfnumber, bk_isoutofstock FROM book WHERE id = "+ bookId);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return new Book(rs.getInt("bk_id"),
                        rs.getString("title"),
                        rs.getString("bk_author"),
                        rs.getString("bk_editor"),
                        rs.getDate("bk_publicationyear"),
                        rs.getInt("bk_nbpages"),
                        rs.getString("bk_genre"),
                        rs.getInt("bk_shelfnumber"),
                        rs.getBoolean("bk_isoutofstock")
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
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            String sql =  "CREATE OR REPLACE TABLE CD " +
                    "(id INTEGER not NULL, " +
                    " cd_title VARCHAR(255) NOT NULL, " +
                    " cd_author VARCHAR(255) NOT NULL, " +
                    " cd_editor VARCHAR(255) NOT NULL, " +
                    " cd_publicationYear Date NOT NULL, " +
                    " cd_nbscenes INTEGER NOT NULL, " +
                    " cd_genre VARCHAR(255) NOT NULL , " +
                    " cd_shelfnumber INTEGER NOT NULL , " +
                    " cd_isoutofstock BOOLEAN NOT NULL , " +
                    " doc_id INTEGER UNIQUE NOT NULL , " +
                    " PRIMARY KEY ( id ), "+
                    " FOREIGN KEY ( doc_id ) REFERENCES document(doc_id))";
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
            saveDocument("cd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(cd.getPublicationYear());
            String year = Integer.toString(calendar.get(Calendar.YEAR)) ;

            String sql = "INSERT INTO CD VALUES ("+ cd.getId() +
                    ",'"+ cd.getTitle()+"'" +
                    ",'"+ cd.getAuthor()+"'" +
                    ",'"+cd.getEditor()+"'" +
                    ", parsedatetime("+ year +", 'yyyy')"+
                    ",'"+ cd.getNbScenes()+"'"+
                    ",'" + cd.getGenre() + "'" +
                    "," + cd.getShelfNumber() +
                    "," + cd.isOutOfStock() +
                    "," + this.documentId +
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
            PreparedStatement ps = conn.prepareStatement("SELECT id,cd_title, cd_author, cd_editor, cd_publicationyear, cd_nbscenes, cd_genre, cd_shelfnumber, cd_isoutofstock FROM CD WHERE id = "+ cdId);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return new Cd(rs.getInt("id"),
                        rs.getString("cd_title"),
                        rs.getString("cd_author"),
                        rs.getString("cd_editor"),
                        rs.getDate("cd_publicationyear"),
                        rs.getInt("cd_nbscenes"),
                        rs.getString("cd_genre"),
                        rs.getInt("cd_shelfnumber"),
                        rs.getBoolean("cd_isoutofstock")
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
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            String sql =  "CREATE OR REPLACE TABLE DVD " +
                    "(id INTEGER not NULL, " +
                    " dvd_title VARCHAR(255) NOT NULL, " +
                    " dvd_author VARCHAR(255) NOT NULL, " +
                    " dvd_editor VARCHAR(255) NOT NULL, " +
                    " dvd_publicationYear Date NOT NULL, " +
                    " dvd_nbscenes INTEGER NOT NULL, " +
                    " dvd_genre VARCHAR(255) NOT NULL , " +
                    " dvd_shelfnumber INTEGER NOT NULL , " +
                    " dvd_isoutofstock BOOLEAN NOT NULL , " +
                    " doc_id INTEGER UNIQUE NOT NULL , " +
                    " PRIMARY KEY ( id ),"+
                    " FOREIGN KEY ( doc_id ) REFERENCES document(doc_id))";
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
            saveDocument("dvd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dvd.getPublicationYear());
            String year = Integer.toString(calendar.get(Calendar.YEAR)) ;
            String sql = "INSERT INTO DVD VALUES ("+ dvd.getId() +
                    ",'"+ dvd.getTitle()+"'" +
                    ",'"+ dvd.getAuthor()+"'" +
                    ",'"+dvd.getEditor()+"'" +
                    ", parsedatetime("+ year +", 'yyyy')"+
                    ",'"+ dvd.getNbScenes()+"'"+
                    ",'" + dvd.getGenre() + "'" +
                    "," + dvd.getShelfNumber() +
                    "," + dvd.isOutOfStock() +
                    "," + this.documentId +
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
            PreparedStatement ps = conn.prepareStatement("SELECT id,dvd_title, dvd_author, dvd_editor, dvd_publicationyear, dvd_nbscenes, dvd_genre, dvd_shelfnumber, dvd_isoutofstock FROM DVD WHERE id = "+ dvdId);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return new Dvd(rs.getInt("id"),
                        rs.getString("dvd_title"),
                        rs.getString("dvd_author"),
                        rs.getString("dvd_editor"),
                        rs.getDate("dvd_publicationyear"),
                        rs.getInt("dvd_nbscenes"),
                        rs.getString("dvd_genre"),
                        rs.getInt("dvd_shelfnumber"),
                        rs.getBoolean("dvd_isoutofstock")
                );
            }

        } catch (SQLException e) {
            handleException(e);
            return null;
        }
    }

    public void createTableDocument(){
        try {
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            String sql =  "CREATE OR REPLACE TABLE DOCUMENT " +
                    "(doc_id INTEGER not NULL, " +
                    " doc_type VARCHAR(4) not NULL, " +
                    " PRIMARY KEY ( doc_id ),"+
                    " CHECK ( doc_type IN ('book','cd','dvd'))"+
                    ")";
            stmt.executeUpdate(sql);
            System.out.println("Created table DOCUMENT in given database...");
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
    private void incrementDocumentId(){
        this.documentId = documentId + 1;
    }
    private void saveDocument(String docType){
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement()
        ) {
            incrementDocumentId();

            String sql = "INSERT INTO DOCUMENT VALUES ("+documentId+", '"+docType+"');";
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            handleException(e);
        }
    }

    @Override
    public void createTableBorrowing() {
        try {
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            String sql =  "CREATE TABLE BORROWING(bo_id INTEGER NOT NULL, " +
                    " bo_loanDate DATE NOT NULL, " +
                    " bo_returnDate DATE NOT NULL, " +
                    " doc_id INTEGER NOT NULL, " +
                    " clt_id INTEGER NOT NULL,"+
                    " PRIMARY KEY ( bo_id ),"+
                    " FOREIGN KEY ( doc_id ) REFERENCES document(doc_id),"+
                    " FOREIGN KEY ( clt_id ) REFERENCES client(clt_id))";
            stmt.executeUpdate(sql);
            System.out.println("Created table BORROWING in given database...");
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
    public void save(Borrowing borrowing, int clientId) {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement()
        ) {
            incrementDocumentId();
            Calendar loanDate = Calendar.getInstance();
            loanDate.setTime(borrowing.getLoanDate());
            Calendar returnDate = Calendar.getInstance();
            returnDate.setTime(borrowing.getReturnDate());
            String sql = "INSERT INTO BORROWING VALUES ("+
                    ""+ borrowing.getId()+"" +
                    ", parsedatetime('"+ loanDate.get(Calendar.YEAR) +"-"+String.format("%02d",loanDate.get(Calendar.MONTH)) +"-"+String.format("%02d",loanDate.get(Calendar.DAY_OF_MONTH)) +"' ,'yyyy-mm-dd') " +
                    ", parsedatetime('"+ returnDate.get(Calendar.YEAR) +"-"+ String.format("%02d",returnDate.get(Calendar.MONTH))+"-"+ String.format("%02d",loanDate.get(Calendar.DAY_OF_MONTH))+" ','yyyy-mm-dd') " +
                    ","+ getDocumentId(borrowing.getDocument())+"" +
                    ","+clientId+"" +
                    ");";
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            handleException(e);
        }
    }

    private int getDocumentId(Document document){

        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            PreparedStatement ps = conn.prepareStatement("SELECT doc_id FROM "+document.getClass().getSimpleName()+" WHERE id = "+ document.getId());
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();

                return rs.getInt("doc_id");
            }


        } catch (SQLException e) {
            handleException(e);
            return 0;
        }
    }



    @Override
    public Borrowing getBorrowing(int borrowingId) {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {


            PreparedStatement ps = conn.prepareStatement("SELECT bo_id,doc_id FROM BORROWING WHERE bo_id = "+ borrowingId);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();

                PreparedStatement ps2 = conn.prepareStatement("SELECT doc_type FROM document WHERE doc_id=" + rs.getInt("doc_id"));

                ResultSet rs2 = ps2.executeQuery();

                rs2.next();
                PreparedStatement ps3 = conn.prepareStatement("SELECT id FROM "+ rs2.getString("doc_type")+" WHERE doc_id="+ rs.getInt("doc_id") );
                ResultSet rs3 = ps3.executeQuery();



                switch (rs2.getString("doc_type")) {
                    case "book":
                        return new Borrowing(rs.getInt("bo_id"),getBook(rs3.getInt("id")));
                    case "cd":
                        return new Borrowing(rs.getInt("bo_id"),getCd(rs3.getInt("id")));
                    case "dvd":
                        return new Borrowing(rs.getInt("bo_id"),getDvd(rs3.getInt("id")));
                }

            }

        } catch (SQLException e) {
            handleException(e);
            return null;
        }
        return null;
    }


}
