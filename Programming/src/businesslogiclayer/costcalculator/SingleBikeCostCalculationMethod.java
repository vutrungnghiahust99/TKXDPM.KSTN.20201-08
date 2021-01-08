package businesslogiclayer.costcalculator;

public class SingleBikeCostCalculationMethod implements CostCalculationMethod{
    @Override
    public int calculateCost(int duration) {
        return 100000;
    }
}
