package businesslogiclayer.entities;


public class InterbankTransaction {
    private String cardCode;
    private String owner;
    private String cvvCode;
    private String dateExpired;
    private String command;
    private String transactionContent;
    private int amount;
    private String createdAt;  //format: yyyy-mm-đd hour:minute:second

    public InterbankTransaction(){

    }

    public String getCardCode() {
        return cardCode;
    }

    public int getAmount() {
        return amount;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setCvvCode(String cvvCode) {
        this.cvvCode = cvvCode;
    }

    public void setDateExpired(String dateExpired) {
        this.dateExpired = dateExpired;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setTransactionContent(String transactionContent) {
        this.transactionContent = transactionContent;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getOwner() {
        return owner;
    }

    public String getCvvCode() {
        return cvvCode;
    }

    public String getDateExpired() {
        return dateExpired;
    }

    public String getCommand() {
        return command;
    }

    public String getTransactionContent() {
        return transactionContent;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
