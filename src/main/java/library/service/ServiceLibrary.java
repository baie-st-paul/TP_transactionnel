package library.service;

import library.model.client.Client;
import library.model.document.Book;
import library.model.document.Cd;
import library.model.document.Dvd;
import library.persistence.JDBCLibrary;


public class ServiceLibrary {
    private final JDBCLibrary jdbcLibrary;

    public ServiceLibrary(JDBCLibrary jdbcLibrary) {
        this.jdbcLibrary = jdbcLibrary;
    }

    public void createTableClient(){
        jdbcLibrary.createTableClient();
    }

    public void saveClient(Client client){
        jdbcLibrary.save(client);
    }

    public Client getClient(int clientId){
       return jdbcLibrary.getClient(clientId);
    }

    public void createTableBook(){
        jdbcLibrary.createTableBook();
    }

    public void saveBook(Book book){
        jdbcLibrary.save(book);
    }

    public Book getBook(int bookId){
        return jdbcLibrary.getBook(bookId);
    }

    public void createTableCd(){
        jdbcLibrary.createTableCd();
    }
    public void saveCd(Cd cd){
        jdbcLibrary.save(cd);
    }
    public Cd getCd(int cdId){
       return jdbcLibrary.getCd(cdId);
    }

    public void createTablDvd(){
        jdbcLibrary.createTableDvd();
    }
    public void saveDvd(Dvd dvd){
        jdbcLibrary.save(dvd);
    }
    public Dvd getDvd(int dvdId){
        return jdbcLibrary.getDvd(dvdId);
    }
}
