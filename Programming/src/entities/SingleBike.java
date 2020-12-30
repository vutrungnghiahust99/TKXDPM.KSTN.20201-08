package entities;

public class SingleBike extends Bike {
    private String type;
    private int nPedals;
    private int nSaddles;
    private int nBicycleSeat;

    public int getNBicycleSeat() {
        return nBicycleSeat;
    }

    public void setNBicycleSeat(int nBicycleSeat) {
        this.nBicycleSeat = nBicycleSeat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNPedals() {
        return nPedals;
    }

    public void setNPedals(int nPedals) {
        this.nPedals = nPedals;
    }

    public int getNSaddles() {
        return nSaddles;
    }

    public void setNSaddles(int nSaddles) {
        this.nSaddles = nSaddles;
    }

    @Override
    public double calculateDeposit(){
        return 0.4 * getValue();
    }

    public String getGeneralInfo(){
        return getBikeCode() + " - " + type;
    }
    @Override
    public String toString() {
        return Integer.toString(getBikeCode()) + " - " + type;
    }
}
