import java.time.LocalDate;
import java.util.Date;

public class Customer {
    String name;
    LocalDate dateOfBirth;
    String address;
    String gender;
    String phoneNumber;
    int [] lastPurchases;
    Date dateOflastPurchase;

    public Customer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int[] getLastPurchases() {
        return lastPurchases;
    }

    public void setLastPurchases(int[] lastPurchases) {
        this.lastPurchases = lastPurchases;
    }

    public Date getDateOflastPurchase() {
        return dateOflastPurchase;
    }

    public void setDateOflastPurchase(Date dateOflastPurchase) {
        this.dateOflastPurchase = dateOflastPurchase;
    }
}
