package entities;

import dataaccesslayer.BikeDAO;

public class Bike {
    private int bikeCode;
    private boolean isInUse;
    private int value;
    private int numPedals;
    private int numSaddle;
    private int numBicycleSeat;
    private String dockID;
    private String licensePlate;

    // SETTER
    public void setNumPedals(int numPedals) { this.numPedals = numPedals; }
    public void setNumSaddle(int numSaddle) { this.numSaddle = numSaddle; }
    public void setNumBicycleSeat(int nBicycleSeat) { this.numBicycleSeat = nBicycleSeat; }
    public void setBikeCode(int bikeCode) {
        this.bikeCode = bikeCode;
    }
    public void setInUse(boolean inUse) {
        isInUse = inUse;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public void setDockID(String dockID) {
        this.dockID = dockID;
    }
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    // GETTER
    public boolean isInUse() {
        return isInUse;
    }
    public int getBikeCode() {
        return bikeCode;
    }
    public int getValue() {
        return value;
    }
    public int getNumPedals() { return numPedals; }
    public int getNumSaddle() { return numSaddle; }
    public int getNumBicycleSeat() { return numBicycleSeat; }
    public String getDockID() {
        return dockID;
    }
    public String getLicensePlate() { return licensePlate;
    }


    public void updateInUseAndDockID(boolean isInUse, String dockID){
        this.isInUse = isInUse;
        BikeDAO.updateIsInUse(this.bikeCode, isInUse, dockID);
    }

    public double calculateDeposit(){ return 1;}

    public String getMoreDetails(){ return "";}

    public String getType(){ return "";}
}
