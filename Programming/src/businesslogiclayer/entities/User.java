package businesslogiclayer.entities;

public class User {
    private Card card;
    private String fullName;
    private int phoneNumber;
    private String dataOfBirth;
    private String province;
    private String address;
    private String email;
    private String password;
    private static User user = new User(Card.getInstance(), "Alan Walker", 9999999, "1/1/1999",
            "Ha noi", "Tran Duy Hung", "nghia.vt@google.com", "10001010101");

    public static User getInstance(){
        return user;
    }

    public Card getCard() {
        return card;
    }

    public String getFullName() {
        return fullName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getDataOfBirth() {
        return dataOfBirth;
    }

    public String getProvince() {
        return province;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public User(Card card, String fullName, int phoneNumber, String dataOfBirth, String province, String address, String email, String password) {
        this.card = card;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.dataOfBirth = dataOfBirth;
        this.province = province;
        this.address = address;
        this.email = email;
        this.password = password;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDataOfBirth(String dataOfBirth) {
        this.dataOfBirth = dataOfBirth;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
