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
    public static void main(String[] args) {
        ServiceLibrary s = new ServiceLibrary(new JDBCLibraryH2());
/*
        long bookId = s.saveBook("titre", "auth", "edit","2004", 2, "manuel Scolaire");

        long cdId = s.saveCd("titre", "auth", "edit","2007",0, "good game");

        long dvdId = s.saveDvd("titre", "auth", "edit","2004", 0, "good movie");

*/
        long clientId = s.createClient("phil", "vall", "add", "email", "bjd dri");
        System.out.println(s.getClient(clientId));


        System.out.println("done!");




    }



}
