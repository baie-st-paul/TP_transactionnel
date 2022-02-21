package library.model.client;

import library.model.borrowing.Borrowing;
import library.model.document.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Client {
    private final int id;
    private String firstName;
    private String lastName;
    private String address;
    private String eMail;
    private String postalCode;
    private double totalFees;
    private final List<Borrowing> borrowingList = new ArrayList<>();

    public String geteMail() {
        return eMail;
    }

    public List<Borrowing> getBorrowingList() {
        return borrowingList;
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
                ", totalFees=" + totalFees +
                ", borrowingList=" + borrowingList +
                '}';
    }

    public Client(int id, String firstName, String lastName, String address, String eMail, String postalCode, double totalFees) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.eMail = eMail;
        this.postalCode = postalCode;
        this.totalFees = totalFees;

    }

    public void addBorrowing(Borrowing borrowing){
        borrowingList.add(borrowing);

    }
    public void removeBorrowing(int borrowingId){
        for(int i = 0 ; i < borrowingList.size(); i++){
            if(borrowingList.get(i).getId() == borrowingId){
                borrowingList.remove(i);
                return;
            }
        }
    }

    public void removeBorrowing(Borrowing borrowing){
        borrowingList.remove(borrowing);
    }

    public void checkForFees(){
        Date today = java.sql.Timestamp.valueOf(LocalDateTime.now());
        for (Borrowing borrowing : borrowingList) {
            if (borrowing.getReturnDate().after(today)) totalFees = totalFees + borrowing.getCOST_PER_DAYS_LATE();
        }
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

    public double getTotalFees() {
        return totalFees;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
