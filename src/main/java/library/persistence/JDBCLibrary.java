package library.persistence;

import library.model.client.Client;
import library.model.document.Book;

public interface JDBCLibrary {
     void createTableClient();
     void save(Client client);
     Client getClient(int clientId);
     void createTableBook();
     void save(Book book);
     Book getBook(int bookId);

}
