package entities;

public class Card {
    private String cardCode;
    private String owner;
    private int CVV;
    private String expiredDate;
    private static Card card = new Card("118131_group8_2020", "Group 8", 427, "1125");

    public Card(String cardCode, String owner, int CVV, String expiredDate) {
        this.cardCode = cardCode;
        this.owner = owner;
        this.CVV = CVV;
        this.expiredDate = expiredDate;
    }

    public static Card getInstance(){
        return card;
    }

    public String getCardCode() {
        return cardCode;
    }

    public String getOwner() {
        return owner;
    }

    public int getCVV() {
        return CVV;
    }

    public String getExpiredDate() {
        return expiredDate;
    }
}
