import library.persistence.JDBCLibraryH2;
import library.service.ServiceLibrary;

public class Main {
    public static void main(String[] args) throws Exception {
        ServiceLibrary s = new ServiceLibrary(new JDBCLibraryH2());

        long bookId1 = s.saveBook("titrelivre", "auth", "edit","2004", 2, "manuel Scolaire");

        long bookId2 = s.saveBook("titrelivre", "auth", "edit","2004", 2, "manuel Scolaire");

        long cdId = s.saveCd("titrecd", "auth", "edit","2007",0, "good game");

        long dvdId = s.saveDvd("titredvd", "auth", "edit","2004", 0, "good movie");

        long clientId = s.createClient("phil", "vall", "add", "email", "bjd dri");
        System.out.println(s.getClient(clientId));

        long loanId1 = s.loanBookToCLient(bookId1,clientId);
        s.loanBookToCLient(bookId1,clientId);
        long loanId2 = s.loanCdToClient(cdId,clientId);
        long loanId3 = s.loanDvdToClient(dvdId,clientId);
        System.out.println(s.getClient(clientId));

        s.returnDocumentFromClient(loanId1);
        s.returnDocumentFromClient(loanId2);
        s.returnDocumentFromClient(loanId3);
        System.out.println(s.getClient(clientId));
        System.out.println(s.getBook(bookId1));
        System.out.println();
        System.out.println("done!");




    }



}
