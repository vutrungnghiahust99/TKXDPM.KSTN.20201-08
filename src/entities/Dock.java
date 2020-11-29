package entities;

import java.util.ArrayList;

public class Dock {
    private String name;
    private String address;
    private String area;
    private int numberOfDockingPoints;
    private ArrayList<Bike> bikes;

    public Dock(String name, String address, String area, int numberOfDockingPoints) {
        this.name = name;
        this.address = address;
        this.area = area;
        this.numberOfDockingPoints = numberOfDockingPoints;
        this.bikes = Bike.getRandomBicycles();
    }

    public static ArrayList<Dock> getRandomDocks(){
        ArrayList<Dock> s = new ArrayList<>();
        for(int i = 0; i < 30; ++i){
            s.add(new Dock(Integer.toString(i), "HUST", "Hai Ba Trung", 100));
        }
        return s;
    }

    @Override
    public String toString() {
        return name + " - " + address;
    }

    public String getDetailInfo(){
        return name + " - " + address + " - " + area + " - " + Integer.toString(numberOfDockingPoints);
    }

    public ArrayList<Bike> getBikes() {
        return bikes;
    }
}
