package library.model.document;

import java.util.Date;

public interface Document {



    void setShelfNumber(int shelfNumber);

    void setOutOfStock(boolean outOfStock);

    int getLOAN_DAYS();

    int getId();

    String getTitle();

    String getAuthor();

    String getEditor();

    Date getPublicationYear();

    int getNbScenes();

    String getGenre();

    int getShelfNumber();

    boolean isOutOfStock() ;

    int getNbPages();


}
