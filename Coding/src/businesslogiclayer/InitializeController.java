package businesslogiclayer;

import connection.DBConnection;
import dataaccesslayer.BikeDAO;
import dataaccesslayer.DockDAO;
import entities.Bike;
import entities.Dock;

import java.util.ArrayList;

public class InitializeController {
    public static ArrayList<Dock> getDocks(){
        ArrayList<Dock> docks = new ArrayList<>();
        ArrayList<ArrayList<String>> dockTable = DockDAO.getAllDocks();
        for(ArrayList<String> row: dockTable){
            String dockID = row.get(0);
            String name = row.get(1);
            String address = row.get(2);
            String area = row.get(3);
            int numberOfDockingPoints = Integer.parseInt(row.get(4));
            ArrayList<Bike> bikes = getBikes(dockID);
            Dock dock = new Dock(dockID, name, address, area, numberOfDockingPoints, bikes);
            docks.add(dock);
        }
        return docks;
    }

    private static ArrayList<Bike> getBikes(String dockID){
        ArrayList<Bike> bikes = new ArrayList<>();
        ArrayList<ArrayList<String>> bikeTable = BikeDAO.queryWithDockID(dockID);
        return InitializeController.tableToBikes(bikeTable);
    }

    private static ArrayList<Bike> tableToBikes(ArrayList<ArrayList<String>> bikeTable){
        ArrayList<Bike> bikes = new ArrayList<>();
        for(ArrayList<String> row: bikeTable){
            int barcode = Integer.parseInt(row.get(0));
            boolean isInUse = Boolean.parseBoolean(row.get(1));
            String type = row.get(2);
            int value = Integer.parseInt(row.get(3));
            int priceForFirst30Minutes = Integer.parseInt(row.get(4));
            int priceFor15MinutesAfter30Minutes = Integer.parseInt(row.get(5));
            int remainBattery = Integer.parseInt(row.get(6));
            float maxTime = Float.parseFloat(row.get(7));
            String licensePlate = row.get(8);
            Bike bike = new Bike(barcode, isInUse, type, value, priceForFirst30Minutes, priceFor15MinutesAfter30Minutes, remainBattery, maxTime, licensePlate);
            bikes.add(bike);
        }
        return bikes;
    }
}
