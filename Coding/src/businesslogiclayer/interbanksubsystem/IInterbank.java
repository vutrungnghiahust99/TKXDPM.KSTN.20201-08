package businesslogiclayer.interbanksubsystem;

public interface IInterbank {
    public abstract String processTransaction(int cost, String command, String content); // command = {"refund", "pay"}

    public abstract int viewBalance();  //

    public abstract void reset();

//    IInterbank s = new InterbankSubsysController();
}
