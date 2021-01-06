package entities;

import dataaccesslayer.CardDAO;

import java.util.ArrayList;

public class Card {
    private String cardCode;
    private String owner;
    private String CVV;
    private String expiredDate;

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public void saveCardInfo(){
        CardDAO.saveCardInfo(cardCode, owner, CVV, expiredDate);
    }
}
