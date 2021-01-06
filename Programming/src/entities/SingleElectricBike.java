package entities;

public class SingleElectricBike extends Bike {
    private String type;
    private Double remainBattery;
    private Double maxTime;
    private String motor;

    public Double getRemainBattery() {
        return remainBattery;
    }

    public void setRemainBattery(Double remainBattery) {
        this.remainBattery = remainBattery;
    }

    public Double getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Double maxTime) {
        this.maxTime = maxTime;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getMoreDetails() {
        return "Lượng pin còn lại: " + getRemainBattery()  + "\n" + "Thời gian sử dụng tối đa: " + getMaxTime() + "\n"
                + "Động cơ xe: " + getMotor();
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
        return (int) 0.4 * getValue();
    }

    @Override
    public String getType(){ return type;}
}
