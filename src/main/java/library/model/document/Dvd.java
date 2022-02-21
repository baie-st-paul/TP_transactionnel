package library.model.document;

import java.util.Date;

public class Dvd implements Document{

    private final int id;
    private final String title;
    private final String author;
    private final String editor;
    private final Date publicationYear;
    private final int nbScenes;
    private final String genre;
    private int shelfNumber;
    private boolean isOutOfStock;

    public Dvd(int id, String title, String author, String editor, Date publicationYear, int nbScenes, String genre, int shelfNumber, boolean isOutOfStock) {
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

    @Override
    public void setShelfNumber(int shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    @Override
    public void setOutOfStock(boolean outOfStock) {
        this.isOutOfStock = outOfStock;
    }

    @Override
    public int getLOAN_DAYS() {
        return 7;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public String getEditor() {
        return editor;
    }

    @Override
    public Date getPublicationYear() {
        return publicationYear;
    }

    @Override
    public int getNbScenes() {
        return nbScenes;
    }

    @Override
    public String getGenre() {
        return genre;
    }

    @Override
    public int getShelfNumber() {
        return shelfNumber;
    }

    @Override
    public boolean isOutOfStock() {
        return isOutOfStock;
    }

    @Override
    public int getNbPages() {
        return 0;
    }

    @Override
    public String toString() {
        return "Dvd{" +
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
