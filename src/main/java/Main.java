import library.model.borrowing.Borrowing;
import library.model.client.Client;
import library.model.document.Cd;
import library.model.document.Dvd;
import library.model.document.Book;
import library.persistence.JDBCLibraryH2;
import library.service.ServiceLibrary;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Book book = new Book(1,"titre", "auth", "ed", new Date(), 2, "manuel scolaire", 1, false);
        Cd cd = new Cd(1,"titre", "auth", "ed", new Date(), 2, "manuel scolaire", 1, false);
        Dvd dvd = new Dvd(1,"titre", "auth", "ed", new Date(), 2, "manuel scolaire", 1, false);

        Borrowing borrowing = new Borrowing(1, book);

        Client client = new Client(1, "phil", "val", "add", "s", "123", 0);
        Client client2 = new Client(2, "phil2", "val", "add", "s", "123", 0.25);
        client.addBorrowing(borrowing);

        ServiceLibrary s = new ServiceLibrary(new JDBCLibraryH2());

        //s.createTableClient();
        //
        //s.saveClient(client2);

        // System.out.println(s.getClient(2));

        //s.createTableBook();

        //s.saveBook(book);
        //System.out.println(s.getBook(1));
        //s.createTableCd();
        //s.saveCd(cd);
        //System.out.println(s.getCd(1));
       // s.createTablDvd();
       // s.saveDvd(dvd);
       // System.out.println(s.getDvd(1));



    }



}
