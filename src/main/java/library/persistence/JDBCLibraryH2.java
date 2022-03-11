package library.persistence;


import library.model.loan.Loan;
import library.model.client.Client;
import library.model.document.Book;
import library.model.document.Cd;
import library.model.document.Document;
import library.model.document.Dvd;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.Date;

public class JDBCLibraryH2 implements JDBCLibrary{
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("library.exe");

    private <T> void save(T t){
        final EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(t);

        em.getTransaction().commit();
        em.close();

    }

    private  <T> void merge(T t) {
        final EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.merge(t);

        em.getTransaction().commit();
        em.close();

    }
    @Override
    public long createBook(String title, String author, String editor, Date publicationYear, int nbPages, String genre) {
        final Book book = new Book(title, author, editor, publicationYear, nbPages, genre);
        save(book);

        return book.getId();
    }


    @Override
    public Book getBook(long bookId) {
        final EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        final Book book = em.find(Book.class, bookId);

        em.getTransaction().commit();
        em.close();

        return book;
    }

    @Override
    public long createCd(String title, String author, String editor, Date publicationYear, int nbScenes, String genre) {
        Cd cd = new Cd(title,author,editor,publicationYear,nbScenes,genre);
        save(cd);

        return cd.getId();
    }

    @Override
    public long createDvd(String title, String author, String editor, Date publicationYear, int nbScenes, String genre) {
        Dvd dvd = new Dvd(title,author,editor,publicationYear,nbScenes,genre);
        save(dvd);

        return dvd.getId();
    }

    @Override
    public Cd getCd(long cdId) {

        final EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        final Cd cd = em.find(Cd.class, cdId);

        em.getTransaction().commit();
        em.close();

        return cd;
    }

    @Override
    public Dvd getDvd(long dvdId) {
        final EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        final Dvd dvd = em.find(Dvd.class, dvdId);

        em.getTransaction().commit();
        em.close();

        return dvd;
    }

    @Override
    public long createClient(String firstName, String lastName, String address, String eMail, String postalCode) {
        Client client = new Client(firstName,lastName,address,eMail, postalCode);
        save(client);

        return client.getId();
    }

    @Override
    public Client getClient(long clientId) {
        final EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        final Client client = em.find(Client.class, clientId);

        em.getTransaction().commit();
        em.close();

        return client;

    }

    @Override
    public long createLoan(Document document, Client client) {
        document.setLoaned(true);
        Loan loan = new Loan(document, client, java.sql.Timestamp.valueOf(LocalDateTime.now()));

        save(loan);
        merge(document);
        return loan.getId();
    }



    @Override
    public void removeLoan(Loan loan) {

        final EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.remove(em.contains(loan) ? loan: em.merge(loan));

        em.getTransaction().commit();
        em.close();
        loan.getDocument().setLoaned(false);
        merge(loan.getDocument());
    }

    @Override
    public Loan getLoan(long loanId) {
        final EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        final Loan loan = em.find(Loan.class,loanId);

        em.getTransaction().commit();
        em.close();

        return loan;

    }


}
