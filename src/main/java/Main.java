import library.model.loan.Loan;
import library.model.client.Client;
import library.model.document.Cd;
import library.model.document.Dvd;
import library.model.document.Book;
import library.persistence.JDBCLibraryH2;
import library.service.ServiceLibrary;

import java.text.ParseException;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws Exception {
        ServiceLibrary s = new ServiceLibrary(new JDBCLibraryH2());

        long bookId = s.saveBook("titre", "auth", "edit","2004", 2, "manuel Scolaire");
        System.out.println(s.getBook(bookId));




        System.out.println("done!");




    }



}
