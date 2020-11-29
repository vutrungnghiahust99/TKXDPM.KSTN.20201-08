package entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Bicycle {
    private int barcode;
    private String type;

    public Bicycle(int barcode, String type) {
        this.barcode = barcode;
        this.type = type;
    }

    public static ArrayList<Bicycle> getRandomBicycles(){
        ArrayList<Bicycle> bicycles = new ArrayList<>();
        Random random = new Random();
        int n = random.nextInt(30) + 1;
        ArrayList<Integer> s = new ArrayList<>();
        for(int i = 0; i < n; ++i)
            s.add(i);
        Collections.shuffle(s);
        for(int i: s){
            bicycles.add(new Bicycle(i, "Lamboghini"));
        }
        return bicycles;
    }

    @Override
    public String toString() {
        return Integer.toString(barcode) + " - " + type;
    }
}
