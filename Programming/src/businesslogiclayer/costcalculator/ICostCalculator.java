package businesslogiclayer.costcalculator;

import entities.Bike;
import entities.DoubleBike;
import entities.SingleBike;

public abstract class ICostCalculator {
    public CostCalculationMethod costCalculationMethod;

    public abstract int calculateCost(int duration);

    public void setCostCalculationMethod(Bike bike){
        if(bike instanceof SingleBike)
            this.costCalculationMethod = new SingleBikeCostCalculationMethod();
        else if(bike instanceof DoubleBike)
            this.costCalculationMethod = new DoubleBikeCostCalculationMethod();
        else
            this.costCalculationMethod = new EBikeCostCalculationMethod();
    }
}
