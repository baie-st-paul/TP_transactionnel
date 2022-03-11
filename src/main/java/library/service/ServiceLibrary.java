package library.service;

import library.model.document.Book;
import library.persistence.JDBCLibrary;

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

}
