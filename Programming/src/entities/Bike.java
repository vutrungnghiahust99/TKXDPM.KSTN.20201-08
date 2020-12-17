package entities;

import dataaccesslayer.BikeDAO;

public class Bike {
    private int bikeCode;
    private boolean isInUse;
    private String type;
    private int value;
    private int priceForFirst30Minutes;
    private int priceFor15MinutesAfter30Minutes;
    private int remainBattery;
    private float maxTime;
    private String licensePlate;

    public Bike(int bikeCode, boolean isInUse, String type, int value, int priceForFirst30Minutes, int priceFor15MinutesAfter30Minutes, int remainBattery, float maxTime, String licensePlate) {
        this.bikeCode = bikeCode;
        this.isInUse = isInUse;
        this.type = type;
        this.value = value;
        this.priceForFirst30Minutes = priceForFirst30Minutes;
        this.priceFor15MinutesAfter30Minutes = priceFor15MinutesAfter30Minutes;
        this.remainBattery = remainBattery;
        this.maxTime = maxTime;
        this.licensePlate = licensePlate;
    }

    public Bike(int bikeCode, String type) {
        this.bikeCode = bikeCode;
        this.type = type;
        value = 10000;
        priceFor15MinutesAfter30Minutes = 300000;
        priceForFirst30Minutes = 100000;
        remainBattery = 90;
        maxTime = 5.5f;
        licensePlate = "30A.19954";
    }

    @Override
    public String toString() {
        return Integer.toString(bikeCode) + " - " + type;
    }


    public boolean isInUse() {
        return isInUse;
    }

    public int getBikeCode() {
        return bikeCode;
    }

    public String getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public int getPriceForFirst30Minutes() {
        return priceForFirst30Minutes;
    }

    public int getPriceFor15MinutesAfter30Minutes() {
        return priceFor15MinutesAfter30Minutes;
    }

    public int getRemainBattery() {
        return remainBattery;
    }

    public float getMaxTime() {
        return maxTime;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPriceForFirst30Minutes(int priceForFirst30Minutes) {
        this.priceForFirst30Minutes = priceForFirst30Minutes;
    }

    public void setMaxTime(float maxTime) {
        this.maxTime = maxTime;
    }

    public void updateInUseAndDockID(boolean isInUse, String dockID){
        this.isInUse = isInUse;
        BikeDAO.updateIsInUse(this.bikeCode, isInUse, dockID);
    }

    public String getGeneralInfo(){
        return bikeCode + " - " + type;
    }

}
