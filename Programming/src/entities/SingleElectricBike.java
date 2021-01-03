package entities;

public class SingleElectricBike extends Bike {
    private String type;
    private String moreDetails;

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getMoreDetails() {
        return moreDetails;
    }

    public void setMoreDetails(String moreDetails) {
        this.moreDetails = moreDetails;
    }

    public String getGeneralInfo(){
        return getBikeCode() + " - " + type;
    }

    @Override
    public String toString() {
        return Integer.toString(getBikeCode()) + " - " + type;
    }

    @Override
    public double calculateDeposit() {
        return 0.4 * getValue();
    }

    @Override
    public String getType(){ return type;}
}
