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
@PrimaryKeyJoinColumn(name ="dvd_id")
public class Dvd extends Document{

    private int nbScenes;

    public Dvd( String title, String author, String editor, Date publicationYear, int nbScenes, String genre) {
        super( title, author, editor, publicationYear, genre);
        this.nbScenes = nbScenes;
    }

    public int getNbScenes() {
        return nbScenes;
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
                ", nbScenes" + nbScenes +
                '}';
    }
}
