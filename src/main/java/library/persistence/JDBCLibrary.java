package library.persistence;

import library.model.borrowing.Borrowing;
import library.model.client.Client;
import library.model.document.Book;
import library.model.document.Cd;
import library.model.document.Document;
import library.model.document.Dvd;

public interface JDBCLibrary {
     void createTableClient();
     void save(Client client);
     Client getClient(int clientId);
     void createTableBook();
     void save(Book book);
     Book getBook(int bookId);
     void createTableCd();
     void save(Cd cd);
     Cd getCd(int cdId);
     void createTableDvd();
     void save(Dvd dvd);
     Dvd getDvd(int dvdId);
     void createTableDocument();


     void createTableBorrowing();
     void save(Borrowing borrowing, int clientId);
     Borrowing getBorrowing(int borrowingId);
     void dropAll();

}
