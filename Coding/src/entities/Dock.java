package entities;

import java.util.ArrayList;

public class Dock {
    private String dockID;
    private String name;
    private String address;
    private String area;
    private int numberOfDockingPoints;
    private ArrayList<Bike> bikes;

    public Dock(String dockID, String name, String address, String area, int numberOfDockingPoints, ArrayList<Bike> bikes) {
        this.dockID = dockID;
        this.name = name;
        this.address = address;
        this.area = area;
        this.numberOfDockingPoints = numberOfDockingPoints;
        this.bikes = bikes;
    }

    public ArrayList<Bike> getBikes() {
        return bikes;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getArea() {
        return area;
    }

    public String getDockID() {
        return dockID;
    }

    public void setDockID(String dockID) {
        this.dockID = dockID;
    }

    public int getNumberOfDockingPoints() {
        return numberOfDockingPoints;
    }


    public int getNumberOfBikes(){
        return this.bikes.size();
    }

    public float getDistance(){
        return -1f;
    }

    public String getGeneralInfo() {
        return name + " - " + address;
    }

}
