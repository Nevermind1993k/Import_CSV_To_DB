import java.time.LocalDate;

public class Customer {
    private String name;
    private LocalDate dateOfBirth;
    private String address;
    private String gender;
    private String phoneNumber;
    private int [] lastPurchases;
    private LocalDate dateOfLastPurchase;

    Customer() {
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    String getAddress() {
        return address;
    }

    void setAddress(String address) {
        this.address = address;
    }

    String getGender() {
        return gender;
    }

    void setGender(String gender) {
        this.gender = gender;
    }

    String getPhoneNumber() {
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

    LocalDate getDateOfLastPurchase() {
        return dateOfLastPurchase;
    }

    void setDateOfLastPurchase(LocalDate dateOfLastPurchase) {
        this.dateOfLastPurchase = dateOfLastPurchase;
    }
}
