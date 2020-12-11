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
    private int deposit;

    public RentBikeTransaction(String rentalCode, int barcode, String type, int rentBikeCost, String owner, int priceForFirst30Minutes, int priceFor15MinutesAfter30Minutes, String rentTime, String returnTime, int deposit) {
        this.rentalCode = rentalCode;
        this.barcode = barcode;
        this.type = type;
        this.rentBikeCost = rentBikeCost;
        this.owner = owner;
        this.priceForFirst30Minutes = priceForFirst30Minutes;
        this.priceFor15MinutesAfter30Minutes = priceFor15MinutesAfter30Minutes;
        this.rentTime = rentTime;
        this.returnTime = returnTime;
        this.deposit = deposit;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
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
                priceFor15MinutesAfter30Minutes, deposit);
    }

    public void updateReturnTimeAndCost(String returnTime, int rentBikeCost){
        this.returnTime = returnTime;
        this.rentBikeCost = rentBikeCost;
        RentBikeTransactionDAO.updateReturnTimeAndCost(rentalCode, rentBikeCost, returnTime);
    }

    // undone
    public String getRentBikeTransaction(int rentalCode){
        return "";
    }

    public String getDetailInfo(){
        String header = "         Thông tin thuê và trả xe!"
;        String row1 = String.format("  %-50s%-30s", "Mã thuê xe", rentalCode);
        String row2 = String.format("  %-50s%-30d", "Barcode", barcode);
        String row3 = String.format("  %-50s%-30s", "Loại xe", type);
        String row4 = String.format("  %-50s%-30d", "Chi phí thuê", rentBikeCost);
        String row5 = String.format("  %-50s%-30s", "Chủ thẻ", owner);
        String row6 = String.format("  %-50s%-30d", "Giá thuê 30 phút đầu", priceForFirst30Minutes);
        String row7 = String.format("  %-50s%-30d", "Giá thuê 15 phút sau 30 phút đầu", priceFor15MinutesAfter30Minutes);
        String row8 = String.format("  %-50s%-30s", "Thời gian thuê xe", rentTime);
        String row9 = String.format("  %-50s%-30s", "Thời gian trả xe", rentTime);
        String row10 = String.format("  %-50s%-30d", "Tiền đặt cọc", deposit);

        return  header + '\n' + row1 + '\n' + row2 + '\n' + row3 + '\n' + row4 + '\n' + row5 + '\n' + row6 + '\n' + row7 +
                '\n' + row8 + '\n' + row9 + '\n' + row10;
    }
}
