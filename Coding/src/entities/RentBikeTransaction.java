package entities;

import dataaccesslayer.RentBikeTransactionDAO;

import java.util.Date;

public class RentBikeTransaction {
    private int barcode;
    private int rentalCode;
    private String type;
    private String rentTime;
    private String returnTime;
    private int rentBikeCost;
    private String owner;
    private int priceForFirst30Minutes;
    private int priceFor15MinutesAfter30Minutes;

    public RentBikeTransaction(int barcode, int rentalCode, String type, String rentTime, String returnTime, int rentBikeCost, String owner, int priceForFirst30Minutes, int priceFor15MinutesAfter30Minutes) {
        this.barcode = barcode;
        this.rentalCode = rentalCode;
        this.type = type;
        this.rentTime = rentTime;
        this.returnTime = returnTime;
        this.rentBikeCost = rentBikeCost;
        this.owner = owner;
        this.priceForFirst30Minutes = priceForFirst30Minutes;
        this.priceFor15MinutesAfter30Minutes = priceFor15MinutesAfter30Minutes;
    }

    public int getBarcode() {
        return barcode;
    }

    public int getRentalCode() {
        return rentalCode;
    }

    public String getType() {
        return type;
    }

    public String getRentTime() {
        return rentTime;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public int getRentBikeCost() {
        return rentBikeCost;
    }

    public String getOwner() {
        return owner;
    }

    public int getPriceForFirst30Minutes() {
        return priceForFirst30Minutes;
    }

    public int getPriceFor15MinutesAfter30Minutes() {
        return priceFor15MinutesAfter30Minutes;
    }

    public void setBarcode(int barcode) {
        this.barcode = barcode;
    }

    public void setRentalCode(int rentalCode) {
        this.rentalCode = rentalCode;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRentTime(String rentTime) {
        this.rentTime = rentTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public void setRentBikeCost(int rentBikeCost) {
        this.rentBikeCost = rentBikeCost;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setPriceForFirst30Minutes(int priceForFirst30Minutes) {
        this.priceForFirst30Minutes = priceForFirst30Minutes;
    }

    public void setPriceFor15MinutesAfter30Minutes(int priceFor15MinutesAfter30Minutes) {
        this.priceFor15MinutesAfter30Minutes = priceFor15MinutesAfter30Minutes;
    }

    public void saveRentBikeTransaction(){
        RentBikeTransactionDAO.save(barcode, rentalCode, type, rentTime, returnTime,
                rentBikeCost, owner, priceForFirst30Minutes,
                priceFor15MinutesAfter30Minutes);
    }

    // undone
    public String getRentBikeTransaction(int rentalCode){
        return "";
    }
}
