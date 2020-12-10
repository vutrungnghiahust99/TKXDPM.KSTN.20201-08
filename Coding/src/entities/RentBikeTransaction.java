package entities;

import dataaccesslayer.RentBikeTransactionDAO;

import java.util.Date;

public class RentBikeTransaction {
    private String rentalCode;
    private int barcode;
    private String type;
    private int rentBikeCost;
    private String owner;
    private int priceForFirst30Minutes;
    private int priceFor15MinutesAfter30Minutes;
    private String rentTime;
    private String returnTime;

    public RentBikeTransaction(String rentalCode, int barcode, String type, int rentBikeCost, String owner, int priceForFirst30Minutes, int priceFor15MinutesAfter30Minutes, String rentTime, String returnTime) {
        this.rentalCode = rentalCode;
        this.barcode = barcode;
        this.type = type;
        this.rentBikeCost = rentBikeCost;
        this.owner = owner;
        this.priceForFirst30Minutes = priceForFirst30Minutes;
        this.priceFor15MinutesAfter30Minutes = priceFor15MinutesAfter30Minutes;
        this.rentTime = rentTime;
        this.returnTime = returnTime;
    }

    public int getBarcode() {
        return barcode;
    }

    public String getRentalCode() {
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

    public void setRentalCode(String rentalCode) {
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

    public void updateReturnTimeAndCost(String returnTime, int rentBikeCost){
        RentBikeTransactionDAO.updateReturnTimeAndCost(rentalCode, rentBikeCost, returnTime);
    }

    // undone
    public String getRentBikeTransaction(int rentalCode){
        return "";
    }
}
