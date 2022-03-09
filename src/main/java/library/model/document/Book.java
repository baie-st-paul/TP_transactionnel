package library.model.document;

import java.util.Date;

public class Book extends Document{

    private final int nbPages;

    public Book(int id, String title, String author, String editor, Date publicationYear, int nbPages, String genre) {
        super(id, title, author, editor, publicationYear, genre);
        this.nbPages = nbPages;
    }

    public int getNbPages() {
        return nbPages;
    }
}
