import library.persistence.JDBCLibraryH2;
import library.service.ServiceLibrary;

public class Main {
    public static void main(String[] args) throws Exception {
        ServiceLibrary s = new ServiceLibrary(new JDBCLibraryH2());

        long bookId = s.saveBook("titre", "auth", "edit","2004", 2, "manuel Scolaire");

        long cdId = s.saveCd("titre", "auth", "edit","2007",0, "good game");

        long dvdId = s.saveDvd("titre", "auth", "edit","2004", 0, "good movie");

        long clientId = s.createClient("phil", "vall", "add", "email", "bjd dri");
        System.out.println(s.getClient(clientId));

        long loanId1 = s.loanBookToCLient(bookId,clientId);
        long loanId2 = s.loanCdToClient(cdId,clientId);
        long loanId3 = s.loanDvdToClient(dvdId,clientId);
        System.out.println(s.getClient(clientId));



        System.out.println("done!");




    }



}
