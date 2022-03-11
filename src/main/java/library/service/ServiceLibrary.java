package library.service;

import library.model.client.Client;
import library.model.document.Book;
import library.model.document.Cd;
import library.model.document.Dvd;
import library.model.loan.Loan;
import library.persistence.JDBCLibrary;
import library.persistence.JDBCLibraryH2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ServiceLibrary {



    private final JDBCLibrary jdbcLibrary;

    public ServiceLibrary(JDBCLibrary jdbcLibrary) {
        this.jdbcLibrary = jdbcLibrary;
    }


    public long saveBook(String title, String author, String editor,String year, int nbPages, String genre ) throws Exception {

       return jdbcLibrary.createBook(title, author, editor, stringToDate(year), nbPages, genre);
    }

    public Book getBook(long bookId){
        return jdbcLibrary.getBook(bookId);
    }


    private Date stringToDate(String stringDate) throws ParseException {
        return new SimpleDateFormat("yyyy").parse(stringDate);

    }

    public long saveCd(String title, String author, String editor, String year, int nbScenes, String genre) throws Exception {
        return jdbcLibrary.createCd(title, author, editor, stringToDate(year), nbScenes, genre);

    }

    public long saveDvd(String title, String author, String editor, String year, int nbScenes, String genre) throws Exception {
        return jdbcLibrary.createDvd(title, author, editor, stringToDate(year), nbScenes, genre);

    }


    public Cd getCd(long cdId) {
        return jdbcLibrary.getCd(cdId);

    }

    public Dvd getDvd(long dvdId) {
        return jdbcLibrary.getDvd(dvdId);

    }

    public long createClient(String firstName, String lastName, String address, String eMail, String postalCode) {
        return jdbcLibrary.createClient(firstName,lastName,address,eMail,postalCode);


    }

    public Client getClient(long clientId) {
        return jdbcLibrary.getClient(clientId);
    }

    public long loanBookToCLient(long bookId, long clientId){
        try {
            Client client = getClient(clientId);
            Book book = getBook(bookId);
            if(client.getTotalFees() > 0)throw new Exception("Client has fees");
            if(book.isLoaned())throw new Exception("Book is already loaned");
            return jdbcLibrary.createLoan(book, client);
        }
        catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            return 0;
        }


    }
    public long loanCdToClient(long cdId, long clientId){
        try {
            Client client = getClient(clientId);
            Cd cd = getCd(cdId);
            if(client.getTotalFees() > 0)throw new Exception("Client has fees");
            if(cd.isLoaned())throw new Exception("Cd is already loaned");
            return jdbcLibrary.createLoan(cd,client);
        }
        catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            return 0;
        }
    }
    public long loanDvdToClient(long dvdId, long clientId){
        try{
            Client client = getClient(clientId);
            Dvd dvd = getDvd(dvdId);
            if(client.getTotalFees() > 0)throw new Exception("Client has fees");
            if(dvd.isLoaned())throw new Exception("dvd is already loaned");
            return jdbcLibrary.createLoan(getDvd(dvdId),client);
        }
        catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            return 0;
        }
    }


    public void returnDocumentFromClient(long loanId) {
        jdbcLibrary.removeLoan(getLoan(loanId));


    }

    private Loan getLoan(long loanId) {
        return jdbcLibrary.getLoan(loanId);
    }


}
