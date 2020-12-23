package businesslogiclayer.controller;

import dataaccesslayer.BikeDAO;
import dataaccesslayer.DockDAO;
import entities.Bike;
import entities.Dock;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Khi bắt đầu chạy chương trình, giao diện chính lấy danh sách bãi xe thông qua class InitializeController
 */
public class InitializeController {
    /**
     * Lấy tất cả các bãi xe từ cơ sở dữ liệu và chuyển thành đối tượng bãi xe tương ứng
     * @return danh sách các bãi xe
     */
    public static ArrayList<Dock> getDocks(){
        ArrayList<Dock> docks = new ArrayList<>();
        ArrayList<ArrayList<String>> dockTable = Dock.getDockTale();
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

    /**
     * Lấy danh sách các xe trong một bãi xe cho trước từ cơ sở dữ liệu
     * @param dockID: ID của bãi xe
     * @return danh sách xe
     */
    public static ArrayList<Bike> getBikes(String dockID){
        ArrayList<ArrayList<String>> bikeTable = BikeDAO.queryWithDockID(dockID);
        return InitializeController.tableToBikes(bikeTable);
    }

    /**
     * chuyển kết quả dạng bảng sau khi query về danh sách các xe
     * @param bikeTable: mảng hai chiều các String
     * @return danh sách các xe
     */
    public static ArrayList<Bike> tableToBikes(ArrayList<ArrayList<String>> bikeTable){
        ArrayList<Bike> bikes = new ArrayList<>();
        for(ArrayList<String> row: bikeTable){
            int bikeCode = Integer.parseInt(row.get(0));
            boolean isInUse;
            if(row.get(1).equals("1"))
                 isInUse = true;
            else if(row.get(1).equals("0"))
                isInUse = false;
            else{
                System.out.println("Something wrong happen");
                System.exit(1);
                isInUse = false;
            }
            String type = row.get(2);
            int value = Integer.parseInt(row.get(3));
            int priceForFirst30Minutes = Integer.parseInt(row.get(4));
            int priceFor15MinutesAfter30Minutes = Integer.parseInt(row.get(5));
            int remainBattery = Integer.parseInt(row.get(6));
            float maxTime = Float.parseFloat(row.get(7));
            String licensePlate = row.get(8);
            Bike bike = new Bike(bikeCode, isInUse, type, value, priceForFirst30Minutes, priceFor15MinutesAfter30Minutes, remainBattery, maxTime, licensePlate);
            bikes.add(bike);
        }
        return bikes;
    }

}
