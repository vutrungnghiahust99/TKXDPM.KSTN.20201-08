package entities;

import java.util.ArrayList;

public class Dock {
    private ArrayList<Bike> bikes;
    private String name;
    private String address;
    private String area;
    private int numberOfDockingPoints;


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

    public Dock(ArrayList<Bike> bikes, String name, String address, String area, int numberOfDockingPoints) {
        this.bikes = bikes;
        this.name = name;
        this.address = address;
        this.area = area;
        this.numberOfDockingPoints = numberOfDockingPoints;
    }

    public void setBikes(ArrayList<Bike> bikes) {
        this.bikes = bikes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setNumberOfDockingPoints(int numberOfDockingPoints) {
        this.numberOfDockingPoints = numberOfDockingPoints;
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

    public int getNumberOfDockingPoints() {
        return numberOfDockingPoints;
    }

    public void addBike(Bike bike){

    }

    public void removeBike(Bike bike){

    }

    public int getNumberOfBikes(){
        return -1;
    }

    public float getDistance(){
        return -1f;
    }

    public float walkingTime(){
        return -1;
    }
}
