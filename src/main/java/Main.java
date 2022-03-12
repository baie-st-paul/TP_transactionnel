import library.persistence.JDBCLibraryH2;
import library.service.ServiceLibrary;

public class Main {
    public static void main(String[] args) throws Exception {
        ServiceLibrary s = new ServiceLibrary(new JDBCLibraryH2());

        long bookId1 = s.saveBook("Les parapluies sont disparus", "Phil", "mouse","2004", 2, "manuel Scolaire");

        long bookId2 = s.saveBook("Les parapluies sont disparus", "Phil", "mouse","2004", 2, "manuel Scolaire");

        long cdId = s.saveCd("titrecd", "diego", "ubi","2007",0, "good game");

        long dvdId = s.saveDvd("titredvd", "pewdiepie", "disney","2006", 0, "good movie");

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

        System.out.println(s.getBook(bookId2));
        System.out.println(s.getBookByGenre("manuel Scolaire").toString());
        System.out.println(s.getCdByGenre("good game").toString());
        System.out.println(s.getDvdByGenre("good movie").toString());

        System.out.println(s.getBookByPublishingYear("2004"));
        System.out.println(s.getCdByPublishingYear("2007"));
        System.out.println(s.getDvdByPublishingYear("2006"));

        System.out.println(s.getBookByAuthor("Phil"));
        System.out.println(s.getCdByAuthor("diego"));
        System.out.println(s.getDvdByAuthor("pewdiepie"));

        System.out.println(s.getBookByTitle("ParaPluies"));
        System.out.println(s.getCdByTitle("titrecd"));
        System.out.println(s.getDvdByTitle("titredvd"));

        System.out.println();
        System.out.println("done!");




    }



}
