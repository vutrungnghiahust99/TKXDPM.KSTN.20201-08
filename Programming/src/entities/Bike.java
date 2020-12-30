package entities;

import dataaccesslayer.BikeDAO;

public class Bike {


    private int bikeCode;
    private boolean isInUse;
    private int value;
    private int dockID;
    private String licensePlate;

    public int getDockID() {
        return dockID;
    }

    public void setBikeCode(int bikeCode) {
        this.bikeCode = bikeCode;
    }

    public void setInUse(boolean inUse) {
        isInUse = inUse;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setDockID(int dockID) {
        this.dockID = dockID;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public boolean isInUse() {
        return isInUse;
    }

    public int getBikeCode() {
        return bikeCode;
    }

    public int getValue() {
        return value;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void updateInUseAndDockID(boolean isInUse, String dockID){
        this.isInUse = isInUse;
        BikeDAO.updateIsInUse(this.bikeCode, isInUse, dockID);
    }

    public double calculateDeposit(){return 0.4 * this.value;}
}
