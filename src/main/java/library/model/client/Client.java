package library.model.client;

import library.model.loan.Loan;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class Client {
    private  int id;
    private  String firstName;
    private  String lastName;
    private  String address;
    private  String eMail;
    private  String postalCode;

    private  List<Loan> loanList = new ArrayList<>();

    public List<Loan> getLoanList() {
        return loanList;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", eMail='" + eMail + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", totalFees=" + getTotalFees() +
                ", borrowingList=" + loanList +
                '}';
    }

    public Client( String firstName, String lastName, String address, String eMail, String postalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.eMail = eMail;
        this.postalCode = postalCode;

    }

    public void addLoan(Loan loan){
        loanList.add(loan);

    }
    public void removeLoan(int loanId){
        for(int i = 0; i < loanList.size(); i++){
            if(loanList.get(i).getId() == loanId){
                loanList.remove(i);
                return;
            }
        }
    }

    public double getTotalFees(){
        double totalFees = 0;
        Date today = java.sql.Timestamp.valueOf(LocalDateTime.now());
        for (Loan loan : loanList) {
            if (loan.getReturnDate().after(today)){
                long nbDaysLate = DAYS.between(LocalDateTime.now(),new java.sql.Date(loan.getReturnDate().getTime()).toLocalDate() );

                totalFees += loan.getCOST_PER_DAYS_LATE() * nbDaysLate;
            }
        }
        return totalFees;
    }


    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getEMail() {
        return eMail;
    }

    public String getPostalCode() {
        return postalCode;
    }



}
