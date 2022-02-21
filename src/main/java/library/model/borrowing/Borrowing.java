package library.model.borrowing;


import library.model.document.Document;


import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class Borrowing {

    private final int id;
    private final Date loanDate;
    private final Date returnDate;

    public Document getDocument() {
        return document;
    }

    private final Document document;

    public Borrowing(int id, Document document) {
        this.id = id;
        this.document = document;
        this.loanDate = java.sql.Timestamp.valueOf(LocalDateTime.now()) ;
        this.returnDate = findReturnDate();

    }

    private Date findReturnDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(loanDate);
        calendar.add(Calendar.DATE, document.getLOAN_DAYS());
        return calendar.getTime();
    }

    public double getCOST_PER_DAYS_LATE() {
        return 0.25;
    }

    public int getId() {
        return id;
    }

    public Date getLoanDate() {
        return loanDate;
    }



    public Date getReturnDate() {
        return returnDate;
    }
    @Override
    public String toString() {
        return "Borrowing{" +
                "id=" + id +
                ", loanDate=" + loanDate +
                ", returnDate=" + returnDate +
                ", document=" + document +
                '}';
    }

}
