package entities;

import dataaccesslayer.RentBikeTransactionDAO;

public class RentBikeTransaction {
    private String rentalCode;
    private int bikeCode;
    private String type;
    private int rentBikeCost;
    private String owner;
    private String rentTime;
    private String returnTime;
    private int deposit;

    public RentBikeTransaction(String rentalCode, int bikeCode, String type, int rentBikeCost, String owner, String rentTime, String returnTime, int deposit) {
        this.rentalCode = rentalCode;
        this.bikeCode = bikeCode;
        this.type = type;
        this.rentBikeCost = rentBikeCost;
        this.owner = owner;
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

    public int getBikeCode() {
        return bikeCode;
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

    public void setBikeCode(int bikeCode) {
        this.bikeCode = bikeCode;
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

    public void saveRentBikeTransaction(){
        RentBikeTransactionDAO.save(bikeCode, rentalCode, type, rentTime, returnTime,
                rentBikeCost, owner, deposit);
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
        String row2 = String.format("  %-50s%-30d", "Mã xe", bikeCode);
        String row3 = String.format("  %-50s%-30s", "Loại xe", type);
        String row4 = String.format("  %-50s%-30d", "Chi phí thuê", rentBikeCost);
        String row5 = String.format("  %-50s%-30s", "Chủ thẻ", owner);
        String row8 = String.format("  %-50s%-30s", "Thời gian thuê xe", rentTime);
        String row9 = String.format("  %-50s%-30s", "Thời gian trả xe", rentTime);
        String row10 = String.format("  %-50s%-30d", "Tiền đặt cọc", deposit);

        return  header + '\n' + row1 + '\n' + row2 + '\n' + row3 + '\n' + row4 + '\n' + row5 + '\n' +
                '\n' + row8 + '\n' + row9 + '\n' + row10;
    }
}
