package library.model.document;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "doc_id")
    private long id;

    private String title;
    private String author;
    private String editor;
    private Date publicationYear;
    private String genre;
    private boolean isLoaned;

    public long getId() {
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
        this.isLoaned = loaned;
    }

    public Document( String title, String author, String editor, Date publicationYear, String genre) {
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
            default -> 0;
        };
    }


}
