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
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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

    @Override
    public List<Book> getBookByGenre(String genre) {
        final EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        final TypedQuery<Book> query = em.createQuery(
                "select" +
                        " book "+
                    "from" +
                        " Book book " +
                    "where" +
                      " book.genre = :genre"
                , Book.class
        );
        query.setParameter("genre", genre);
        List<Book> bookList= query.getResultList();
        em.getTransaction().commit();
        em.close();
        return bookList;
    }

    @Override
    public List<Cd> getCdByGenre(String genre) {
        final EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        final TypedQuery<Cd> query = em.createQuery(
                "select" +
                        " cd "+
                        "from" +
                        " Cd cd " +
                        "where" +
                        " cd.genre = :genre"
                , Cd.class
        );
        query.setParameter("genre", genre);
        List<Cd> cdList= query.getResultList();
        em.getTransaction().commit();
        em.close();
        return cdList;
    }

    @Override
    public List<Dvd> getDvdByGenre(String genre) {
        final EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        final TypedQuery<Dvd> query = em.createQuery(
                "select" +
                        " dvd "+
                        "from" +
                        " Dvd dvd " +
                        "where" +
                        " dvd.genre = :genre"
                , Dvd.class
        );
        query.setParameter("genre", genre);
        List<Dvd> dvdList= query.getResultList();
        em.getTransaction().commit();
        em.close();
        return dvdList;
    }

    @Override
    public List<Book> getBookByPublishingYear(Date year) {
        final EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        final TypedQuery<Book> query = em.createQuery(
                "select" +
                        " book "+
                        "from" +
                        " Book book " +
                        "where" +
                        " book.publicationYear = :year"
                , Book.class
        );
        query.setParameter("year", year);
        List<Book> bookList= query.getResultList();
        em.getTransaction().commit();
        em.close();
        return bookList;
    }

    @Override
    public List<Cd> getCdByPublishingYear(Date year) {
        final EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        final TypedQuery<Cd> query = em.createQuery(
                "select" +
                        " cd "+
                        "from" +
                        " Cd cd " +
                        "where" +
                        " cd.publicationYear = :year"
                , Cd.class
        );
        query.setParameter("year", year);
        List<Cd> cdList= query.getResultList();
        em.getTransaction().commit();
        em.close();
        return cdList;
    }

    @Override
    public List<Dvd> getDvdByPublishingYear(Date year) {
        final EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        final TypedQuery<Dvd> query = em.createQuery(
                "select" +
                        " dvd "+
                        "from" +
                        " Dvd dvd " +
                        "where" +
                        " dvd.publicationYear = :year"
                , Dvd.class
        );
        query.setParameter("year", year);
        List<Dvd> dvdList= query.getResultList();
        em.getTransaction().commit();
        em.close();
        return dvdList;
    }

    @Override
    public List<Book> getBookByAuthor(String author) {
        final EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        final TypedQuery<Book> query = em.createQuery(
                "select" +
                        " book "+
                        "from" +
                        " Book book " +
                        "where" +
                        " book.author = :author"
                , Book.class
        );
        query.setParameter("author", author);
        List<Book> bookList= query.getResultList();
        em.getTransaction().commit();
        em.close();
        return bookList;
    }

    @Override
    public List<Cd> getCdByAuthor(String author) {
        final EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        final TypedQuery<Cd> query = em.createQuery(
                "select" +
                        " cd "+
                        "from" +
                        " Cd cd " +
                        "where" +
                        " cd.author = :author"
                , Cd.class
        );
        query.setParameter("author", author);
        List<Cd> cdBook= query.getResultList();
        em.getTransaction().commit();
        em.close();
        return cdBook;
    }

    @Override
    public List<Dvd> getDvdByAuthor(String author) {
        final EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        final TypedQuery<Dvd> query = em.createQuery(
                "select" +
                        " dvd "+
                        "from" +
                        " Dvd dvd " +
                        "where" +
                        " dvd.author = :author"
                , Dvd.class
        );
        query.setParameter("author", author);
        List<Dvd> dvdList= query.getResultList();
        em.getTransaction().commit();
        em.close();
        return dvdList;
    }

    @Override
    public List<Book> getBookByTitle(String title) {
        final EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        final TypedQuery<Book> query = em.createQuery(
                "select" +
                        " book "+
                        "from" +
                        " Book book " +
                        "where" +
                        " book.title LIKE :title"
                , Book.class
        );
        query.setParameter("title", "%"+title+"%");
        List<Book> bookList= query.getResultList();
        em.getTransaction().commit();
        em.close();
        return bookList;
    }

    @Override
    public List<Cd> getCdByTitle(String title) {
        final EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        final TypedQuery<Cd> query = em.createQuery(
                "select" +
                        " cd "+
                        "from" +
                        " Cd cd " +
                        "where" +
                        " cd.title LIKE :title"
                , Cd.class
        );
        query.setParameter("title", "%"+title+"%");
        List<Cd> cdList= query.getResultList();
        em.getTransaction().commit();
        em.close();
        return cdList;
    }

    @Override
    public List<Dvd> getDvdByTitle(String title) {
        final EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        final TypedQuery<Dvd> query = em.createQuery(
                "select" +
                        " dvd "+
                        "from" +
                        " Dvd dvd " +
                        "where" +
                        " dvd.title LIKE :title"
                , Dvd.class
        );
        query.setParameter("title", "%"+title+"%");
        List<Dvd> dvdList= query.getResultList();
        em.getTransaction().commit();
        em.close();
        return dvdList;
    }


}
