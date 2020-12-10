package businesslogiclayer.interbanksubsystem;

public interface IInterbank {
    public abstract String processTransaction(int cost, String command, String content);

    public abstract int viewBalance();

    public abstract String reset();
}
