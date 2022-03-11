package library.model.document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@PrimaryKeyJoinColumn(name ="book_id")
public class Book extends Document{

    private int nbPages;

    public Book(String title, String author, String editor, Date publicationYear, int nbPages, String genre) {
        super(title, author, editor, publicationYear, genre);
        this.nbPages = nbPages;
    }

    public int getNbPages() {
        return nbPages;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", author='" + getAuthor() + '\'' +
                ", editor='" + getEditor() + '\'' +
                ", publicationYear=" + getPublicationYear() +
                ", genre='" + getGenre() + '\'' +
                ", isLoaned=" + isLoaned() +
                ", nbPages" + nbPages +
                '}';
    }
}
