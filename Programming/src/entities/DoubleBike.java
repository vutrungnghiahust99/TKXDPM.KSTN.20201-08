package entities;

public class DoubleBike extends Bike{
    private String type;

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGeneralInfo(){
        return getBikeCode() + " - " + type;
    }
    @Override
    public String toString() {
        return Integer.toString(getBikeCode()) + " - " + type;
    }

    @Override
    public int calculateDeposit() {
        return (int)0.4 * getValue();
    }
}
