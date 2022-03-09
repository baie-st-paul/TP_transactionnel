package library.model.document;

import java.util.Date;

public class Document {

    private final int id;
    private final String title;
    private final String author;
    private final String editor;
    private final Date publicationYear;
    private final String genre;
    private boolean isLoaned;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getEditor() {
        return editor;
    }

    public Date getPublicationYear() {
        return publicationYear;
    }

    public String getGenre() {
        return genre;
    }

    public boolean isLoaned() {
        return isLoaned;
    }

    public void setLoaned(boolean loaned) {
        isLoaned = loaned;
    }

    public Document(int id, String title, String author, String editor, Date publicationYear, String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.editor = editor;
        this.publicationYear = publicationYear;
        this.genre = genre;
        this.isLoaned = false;
    }


    public int getLOAN_DAYS() {
        return switch (this.getClass().getSimpleName()) {
            case "Book" -> 21;
            case "Cd" -> 14;
            case "Dvd" -> 7;
        };
    }
}
