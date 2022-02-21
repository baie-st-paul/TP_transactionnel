package library.model.document;

import java.util.Date;

public class Cd implements Document{

    private final int id;
    private final String title;
    private final String author;
    private final String editor;
    private final Date publicationYear;
    private final int nbScenes;
    private final String genre;
    private int shelfNumber;
    private boolean isOutOfStock;


    public Cd(int id, String title, String author, String editor, Date publicationYear, int nbScenes, String genre, int shelfNumber, boolean isOutOfStock) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.editor = editor;
        this.publicationYear = publicationYear;
        this.nbScenes = nbScenes;
        this.genre = genre;
        this.shelfNumber = shelfNumber;
        this.isOutOfStock = isOutOfStock;
    }

    public void setShelfNumber(int shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    public void setOutOfStock(boolean outOfStock) {
        isOutOfStock = outOfStock;
    }

    public int getLOAN_DAYS() {
        return 14;
    }

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

    public int getNbScenes() {
        return nbScenes;
    }

    public String getGenre() {
        return genre;
    }

    public int getShelfNumber() {
        return shelfNumber;
    }

    public boolean isOutOfStock() {
        return isOutOfStock;
    }


    public int getNbPages() {
        return 0;
    }

    @Override
    public String toString() {
        return "Cd{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", editor='" + editor + '\'' +
                ", publicationYear=" + publicationYear +
                ", nbScenes=" + nbScenes +
                ", genre='" + genre + '\'' +
                ", shelfNumber=" + shelfNumber +
                ", isOutOfStock=" + isOutOfStock +
                '}';
    }
}
