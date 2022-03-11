package library.model.loan;


import library.model.document.Document;


import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class Loan {

    private  int id;
    private  Date loanDate;
    private  Date returnDate;

    public Document getDocument() {
        return document;
    }

    private final Document document;

    public Loan( Document document) {
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
