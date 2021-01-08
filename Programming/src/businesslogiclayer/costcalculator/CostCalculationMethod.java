package businesslogiclayer.costcalculator;

public interface CostCalculationMethod {
    /**
     *
     * @param duration: (s) - the amount of time user rent the bike
     * @return
     */
    public int calculateCost(int duration);
}
