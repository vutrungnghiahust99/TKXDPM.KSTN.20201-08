package businesslogiclayer.costcalculator;

public class EBikeCostCalculationMethod implements CostCalculationMethod{
    @Override
    public int calculateCost(int duration) {
        return 300000;
    }
}
