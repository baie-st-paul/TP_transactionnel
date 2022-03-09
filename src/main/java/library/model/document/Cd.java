package library.model.document;

import java.util.Date;

public class Cd extends Document{



    private final int nbScenes;

    public Cd(int id, String title, String author, String editor, Date publicationYear, int nbScenes, String genre) {
        super(id, title, author, editor, publicationYear, genre);
        this.nbScenes = nbScenes;
    }

    public int getNbScenes() {
        return nbScenes;
    }

}
