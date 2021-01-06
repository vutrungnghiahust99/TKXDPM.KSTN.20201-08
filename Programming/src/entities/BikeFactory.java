package entities;

import java.util.ArrayList;

public class BikeFactory {
    private BikeFactory() {
    }

    public static final Bike getBike(ArrayList<String> bike) {
        switch (bike.get(5)) {
            case "Xe đạp đơn thường":
                SingleBike singleBike = new SingleBike();
                singleBike.setBikeCode(Integer.parseInt(bike.get(0)));
                singleBike.setInUse(Boolean.parseBoolean(bike.get(1)));
                singleBike.setValue(Integer.parseInt(bike.get(2)));
                singleBike.setDockID(bike.get(4));
                singleBike.setLicensePlate(bike.get(3));
                singleBike.setType(bike.get(5));
                singleBike.setNumPedals(Integer.parseInt(bike.get(7)));
                singleBike.setNumSaddle(Integer.parseInt(bike.get(6)));
                singleBike.setNumBicycleSeat(Integer.parseInt(bike.get(8)));
                return singleBike;

            case "Xe đạp đôi thường":
                DoubleBike doubleBike = new DoubleBike();
                doubleBike.setBikeCode(Integer.parseInt(bike.get(0)));
                doubleBike.setInUse(Boolean.parseBoolean(bike.get(1)));
                doubleBike.setValue(Integer.parseInt(bike.get(2)));
                doubleBike.setDockID(bike.get(4));
                doubleBike.setLicensePlate(bike.get(3));
                doubleBike.setType(bike.get(5));
                doubleBike.setNumPedals(Integer.parseInt(bike.get(7)));
                doubleBike.setNumSaddle(Integer.parseInt(bike.get(6)));
                doubleBike.setNumBicycleSeat(Integer.parseInt(bike.get(8)));
                return doubleBike;

            case "Xe đạp đơn điện":
                SingleElectricBike singleElectricBike = new SingleElectricBike();
                singleElectricBike.setBikeCode(Integer.parseInt(bike.get(0)));
                singleElectricBike.setInUse(Boolean.parseBoolean(bike.get(1)));
                singleElectricBike.setValue(Integer.parseInt(bike.get(2)));
                singleElectricBike.setDockID(bike.get(4));
                singleElectricBike.setLicensePlate(bike.get(3));
                singleElectricBike.setType(bike.get(5));
                singleElectricBike.setNumPedals(Integer.parseInt(bike.get(7)));
                singleElectricBike.setNumSaddle(Integer.parseInt(bike.get(6)));
                singleElectricBike.setNumBicycleSeat(Integer.parseInt(bike.get(8)));
                singleElectricBike.setRemainBattery(Double.parseDouble(bike.get(9)));
                singleElectricBike.setMaxTime(Double.parseDouble(bike.get(10)));
                singleElectricBike.setMotor(bike.get(11));
                return singleElectricBike;
            default:
                throw new IllegalArgumentException("This bank type is unsupported");
        }
    }
}
