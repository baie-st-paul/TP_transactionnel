import library.model.loan.Loan;
import library.model.client.Client;
import library.model.document.Cd;
import library.model.document.Dvd;
import library.model.document.Book;
import library.persistence.JDBCLibraryH2;
import library.service.ServiceLibrary;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Book book = new Book(1,"titre", "auth", "ed", new Date(), 2, "genre" );
        Cd cd = new Cd(1,"titre", "auth", "ed", new Date(), 2, "manuel scolaire");
        Dvd dvd = new Dvd(1,"titre", "auth", "ed", new Date(), 2, "manuel scolaire");

        Loan loan = new Loan(1, book);

        Client client = new Client(1, "phil", "val", "add", "s", "123");
        Client client2 = new Client(2, "phil2", "val", "add", "s", "123");
        client.addLoan(loan);


/*
        ServiceLibrary s = new ServiceLibrary(new JDBCLibraryH2());

        s.dropAll();

        s.createTableClient();
        s.createTableDocument();
        s.createTableBorrowing();





        s.createTableBook();
        s.saveBook(book);
        //System.out.println(s.getBook(1));
        s.createTableCd();
        s.saveCd(cd);
        //System.out.println(s.getCd(1));
        s.createTableDvd();
        s.saveDvd(dvd);
        s.saveClient(client);

        // System.out.println(s.getClient(2));
       // System.out.println(s.getDvd(1));
        System.out.println(s.getBorrowing(1));
*/

    }



}
