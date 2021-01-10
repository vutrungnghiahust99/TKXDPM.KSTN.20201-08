package businesslogiclayer.costcalculator;

public class CostCalculator extends ICostCalculator{
    @Override
    public int calculateCost(int duration) {
        return this.costCalculationMethod.calculateCost(duration);
    }
}
