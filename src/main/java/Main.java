import library.model.borrowing.Borrowing;
import library.model.client.Client;
import library.model.document.Cd;
import library.model.document.Dvd;
import library.model.document.Livre;
import library.persistence.JDBCLibraryH2;
import library.service.ServiceLibrary;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Livre livre = new Livre(1,"titre", "auth", "ed", new Date(), 2, "manuel scolaire", 1, false);
        Cd cd = new Cd(1,"titre", "auth", "ed", new Date(), 2, "manuel scolaire", 1, false);
        Dvd dvd = new Dvd(1,"titre", "auth", "ed", new Date(), 2, "manuel scolaire", 1, false);

        Borrowing borrowing = new Borrowing(1, livre);

        Client client = new Client(1, "phil", "val", "add", "s", "123", 0);

        client.addBorrowing(borrowing);

        ServiceLibrary s = new ServiceLibrary(new JDBCLibraryH2());

        //s.createTableClient();
        //
        //s.saveClient(client);

        System.out.println(s.getClient(1));


    }



}
