package entities;

public class SingleElectricBike extends Bike {
    private String type;
    private int nPedals;
    private int nSaddle;
    private double maxTime;
    private double remainBattery;
    private String motor;
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

    public int getNSaddle() {
        return nSaddle;
    }

    public void setNSaddle(int nSaddle) {
        this.nSaddle = nSaddle;
    }

    public double getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(double maxTime) {
        this.maxTime = maxTime;
    }

    public double getRemainBattery() {
        return remainBattery;
    }

    public void setRemainBattery(double remainBattery) {
        this.remainBattery = remainBattery;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
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
        return 0.4*getValue();
    }
}
