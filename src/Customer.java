import java.time.LocalDate;

public class Customer {
    private String name;
    private LocalDate dateOfBirth;
    private String address;
    private String gender;
    private String phoneNumber;
    private int [] lastPurchases;
    private LocalDate dateOflastPurchase;

    Customer() {
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int[] getLastPurchases() {
        return lastPurchases;
    }

    void setLastPurchases(int[] lastPurchases) {
        this.lastPurchases = lastPurchases;
    }

    public LocalDate getDateOflastPurchase() {
        return dateOflastPurchase;
    }

    public void setDateOflastPurchase(LocalDate dateOflastPurchase) {
        this.dateOflastPurchase = dateOflastPurchase;
    }
}
