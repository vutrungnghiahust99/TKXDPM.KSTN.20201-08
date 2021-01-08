package businesslogiclayer.costcalculator;

public class DoubleBikeCostCalculationMethod implements CostCalculationMethod{
    @Override
    public int calculateCost(int duration) {
        return 200000;
    }
}
