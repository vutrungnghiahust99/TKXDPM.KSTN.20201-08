package businesslogiclayer;

import businesslogiclayer.interbanksubsystem.IInterbank;
import businesslogiclayer.interbanksubsystem.InterbankSubsysController;
import dataaccesslayer.BikeDAO;
import dataaccesslayer.RentBikeTransactionDAO;
import entities.Bike;
import entities.Card;
import entities.PaymentTransaction;
import entities.RentBikeTransaction;
import presentationlayer.ReturnBikeScreenController;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ReturnBikeController {
    private static final String PATTERN = "yyyy-MM-dd hh:mm:ss";

    public static RentBikeTransaction processReturnBike(){
        RentBikeTransaction rentBikeTransaction = ReturnBikeController.getRentBikeTransaction(RentBikeController.rentalCode);
        System.out.println(rentBikeTransaction.getDetailInfo());
        Card card = Card.getInstance();
        int rentBikeCost = ReturnBikeController.estimateCost(rentBikeTransaction);
        int refundAmount = rentBikeTransaction.getDeposit() -  rentBikeCost;
        System.out.println("Refund amount: " + refundAmount);
        assert refundAmount > 0;
        IInterbank interbank = new InterbankSubsysController();
        String respondCode = interbank.processTransaction(refundAmount, "refund", "Castle in the sky");
        System.out.println("respond code: " + respondCode);

        // create new transaction and save
        PaymentTransaction paymentTransaction = new PaymentTransaction(
                RentBikeController.rentalCode, card.getCardCode(), card.getOwner(),
                "Castle in the sky", refundAmount,
                getCurrentLocalDateTimeStamp("hh:mm:ss"), getCurrentLocalDateTimeStamp("yyyy-MM-dd"));
        paymentTransaction.savePaymentTransaction();
        String returnTime = getCurrentLocalDateTimeStamp(PATTERN);

        // update rentBikeTransaction
        rentBikeTransaction.updateReturnTimeAndCost(returnTime, rentBikeCost);

        // update Bike
        Bike bike = getBike(rentBikeTransaction.getBarcode());
        bike.updateInUseAndDockID(false, ReturnBikeScreenController.newDockID);

        return rentBikeTransaction;
    }

    private static RentBikeTransaction getRentBikeTransaction(String rentalCode){
        ArrayList<ArrayList<String>> rentBikeTransactions = RentBikeTransactionDAO.queryByRentalCode(rentalCode);
        assert rentBikeTransactions.size() == 1;
        ArrayList<String> s = rentBikeTransactions.get(0);
        int barcode = Integer.parseInt(s.get(1));
        String bikeType = s.get(2);
        int rentBikeCost = Integer.parseInt(s.get(3));
        String owner = s.get(4);
        int priceForFirst30Minutes = Integer.parseInt(s.get(5));
        int priceFor15MinutesAfter30Minutes = Integer.parseInt(s.get(6));
        String rentTime = s.get(7);
        String returnTime = s.get(8);
        int deposit = Integer.parseInt(s.get(9));
        return new RentBikeTransaction(
                rentalCode, barcode, bikeType, rentBikeCost, owner,
                priceForFirst30Minutes, priceFor15MinutesAfter30Minutes, rentTime, returnTime, deposit);
    }

    public static int estimateCost(RentBikeTransaction rentBikeTransaction){
        System.out.println("Estimating cost...");
        String rentTime = rentBikeTransaction.getRentTime();  //
        SimpleDateFormat df = new SimpleDateFormat(PATTERN);
        try {
            Date date = df.parse(rentTime);
            long epoch = date.getTime();
            long start = epoch / 1000;
//            long now = System.currentTimeMillis() / 1000;
            long now = start + 3600;  // for dev
            System.out.println("RentTime: " + start);
            System.out.println("ReturnTime: " + now);
            float rentTimeMinutes = (float) (now - start) / 60;
            if (rentTimeMinutes > 30){
                int p1 = rentBikeTransaction.getPriceForFirst30Minutes();
                float p2 = rentBikeTransaction.getPriceFor15MinutesAfter30Minutes() * ((rentTimeMinutes -  30) / 15);
                return p1 + (int) p2;
            }
            else{
                return rentBikeTransaction.getPriceForFirst30Minutes();
            }
        }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
            return -1;
        }
    }

    private static Bike getBike(int barcode){
        ArrayList<ArrayList<String>> bikeTable = BikeDAO.queryWithBarcode(barcode);
        ArrayList<Bike> s = InitializeController.tableToBikes(bikeTable);
        assert s.size() == 1;
        return s.get(0);
    }

    public static String getCurrentLocalDateTimeStamp(String pattern) {
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern(pattern));
    }
}
