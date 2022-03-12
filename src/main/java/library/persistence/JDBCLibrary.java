package library.persistence;

import library.model.client.Client;
import library.model.document.Book;
import library.model.document.Cd;
import library.model.document.Document;
import library.model.document.Dvd;
import library.model.loan.Loan;

import java.util.Date;
import java.util.List;

public interface JDBCLibrary {


     long createBook(String title, String author, String editor, Date stringToDate, int nbPages, String genre);

     Book getBook(long bookId);

     long createCd(String title, String author, String editor, Date stringToDate, int nbScenes, String genre);

     long createDvd(String title, String author, String editor, Date stringToDate, int nbScenes, String genre);

     Cd getCd(long cdId);

     Dvd getDvd(long dvdId);

     long createClient(String firstName, String lastName, String address, String eMail, String postalCode);

     Client getClient(long clientId);

     long createLoan(Document document, Client client);

     void removeLoan(Loan loan);


     Loan getLoan(long loanId);

     List<Book> getBookByGenre(String genre);

     List<Cd> getCdByGenre(String genre);

     List<Dvd> getDvdByGenre(String genre);

     List<Book> getBookByPublishingYear(Date year);

     List<Cd> getCdByPublishingYear(Date year);

     List<Dvd> getDvdByPublishingYear(Date year);

     List<Book> getBookByAuthor(String author);

     List<Cd> getCdByAuthor(String author);

     List<Dvd> getDvdByAuthor(String author);

     List<Book> getBookByTitle(String title);

     List<Cd> getCdByTitle(String title);

     List<Dvd> getDvdByTitle(String title);
}
