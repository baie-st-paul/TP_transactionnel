package library.persistence;

import library.model.document.Book;

import java.util.Date;

public interface JDBCLibrary {


     long createBook(String title, String author, String editor, Date stringToDate, int nbPages, String genre);

     Book getBook(long bookId);
}
