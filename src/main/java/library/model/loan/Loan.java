package library.model.loan;


import library.model.client.Client;
import library.model.document.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
@Entity
@Data
@NoArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "loan_id")
    private  int id;
    private  Date loanDate;
    private  Date returnDate;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @ToString.Exclude
    private Client client;

    public Document getDocument() {
        return document;
    }

    @OneToOne
    private Document document;

    public Loan( Document document, Client client) {
        this.client = client;
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
