package entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Bike {
    private int barcode;
    private String type;
    private int value;
    private int priceForFirst30Minutes;
    private int priceFor15MinutesAfter30Minutes;
    private int remainBattery;
    private float maxTime;
    private String licensePlate;

    public Bike(int barcode, String type) {
        this.barcode = barcode;
        this.type = type;
        value = 10000;
        priceFor15MinutesAfter30Minutes = 300000;
        priceForFirst30Minutes = 100000;
        remainBattery = 90;
        maxTime = 5.5f;
        licensePlate = "30A.19954";
    }

    public static ArrayList<Bike> getRandomBicycles(){
        ArrayList<Bike> bikes = new ArrayList<>();
        Random random = new Random();
        int n = random.nextInt(30) + 1;
        ArrayList<Integer> s = new ArrayList<>();
        for(int i = 0; i < n; ++i)
            s.add(i);
        Collections.shuffle(s);
        for(int i: s){
            bikes.add(new Bike(i, "Lamboghini"));
        }
        return bikes;
    }

    @Override
    public String toString() {
        return Integer.toString(barcode) + " - " + type;
    }

    public String getDetailInfo(){
        return Integer.toString(barcode) + " - " + type + " - " + Integer.toString(value) + " - " +
                Integer.toString(priceForFirst30Minutes) + " - " + Integer.toString(priceFor15MinutesAfter30Minutes) + " - " +
                Integer.toString(remainBattery) + " - " + Float.toString(maxTime) + " - " + licensePlate;
    }
}
