package businesslogiclayer.interbanksubsystem;

public interface IInterbank {
    String processTransaction(int cost, String command);
    int viewBalance();
}
