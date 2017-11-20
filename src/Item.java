import java.time.LocalDate;

public class Item {
    private int id;
    private String title;
    private int code;
    private String producer;
    private LocalDate dateOfLastUpdate;

    Item() {
    }

    int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    int getCode() {
        return code;
    }

    void setCode(int code) {
        this.code = code;
    }

    String getProducer() {
        return producer;
    }

    void setProducer(String producer) {
        this.producer = producer;
    }

    LocalDate getDateOfLastUpdate() {
        return dateOfLastUpdate;
    }

    void setDateOfLastUpdate(LocalDate dateOfLastUpdate) {
        this.dateOfLastUpdate = dateOfLastUpdate;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", code=" + code +
                ", producer='" + producer + '\'' +
                ", dateOfLastUpdate=" + dateOfLastUpdate +
                '}';
    }
}
