package library.persistence;

import library.model.document.Book;
import library.model.document.Cd;
import library.model.document.Dvd;

import java.util.Date;

public interface JDBCLibrary {


     long createBook(String title, String author, String editor, Date stringToDate, int nbPages, String genre);

     Book getBook(long bookId);

     long createCd(String title, String author, String editor, Date stringToDate, int nbScenes, String genre);

     long createDvd(String title, String author, String editor, Date stringToDate, int nbScenes, String genre);

     Cd getCd(long cdId);

     Dvd getDvd(long dvdId);
}
