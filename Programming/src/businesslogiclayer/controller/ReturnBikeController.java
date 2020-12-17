package businesslogiclayer.controller;

import businesslogiclayer.interbanksubsystem.IInterbank;
import businesslogiclayer.interbanksubsystem.InterbankSubsysController;
import dataaccesslayer.BikeDAO;
import dataaccesslayer.RentBikeTransactionDAO;
import entities.Bike;
import entities.Card;
import entities.PaymentTransaction;
import entities.RentBikeTransaction;
import javafx.util.Pair;
import presentationlayer.ReturnBikeScreen;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

/**
 * Thực hiện xử lý yêu cầu trả xe của người dùng
 *
 */
public class ReturnBikeController {
    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * Xử lý yêu cầu trả xe của người dùng
     * @return RentBikeTransaction nếu thành công và null nếu thất bại
     */
    public static Pair<String, RentBikeTransaction> processReturnBike(){
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
        if (!respondCode.equals("00"))
            return new Pair<>(respondCode, null);
        // create new transaction and save
        PaymentTransaction paymentTransaction = new PaymentTransaction(
                RentBikeController.rentalCode, card.getCardCode(), card.getOwner(),
                "Castle in the sky", refundAmount,
                getCurrentLocalDateTimeStamp("HH:mm:ss"), getCurrentLocalDateTimeStamp("yyyy-MM-dd"));
        paymentTransaction.savePaymentTransaction();
        String returnTime = getCurrentLocalDateTimeStamp(PATTERN);

        // update rentBikeTransaction
        rentBikeTransaction.updateReturnTimeAndCost(returnTime, rentBikeCost);

        // update Bike
        Bike bike = getBike(rentBikeTransaction.getBikeCode());
        bike.updateInUseAndDockID(false, ReturnBikeScreen.newDockID);
        return new Pair<>(respondCode, rentBikeTransaction);
    }

    /**
     * Lấy thông tin giao dịch tử cơ sở dữ liệu dưa trên mã thuê xe
     *
     * @param rentalCode: mã thuê xe
     * @return Đối tượng RentBikeTransaction
     */
    private static RentBikeTransaction getRentBikeTransaction(String rentalCode){
        ArrayList<ArrayList<String>> rentBikeTransactions = RentBikeTransactionDAO.queryByRentalCode(rentalCode);
        assert rentBikeTransactions.size() == 1;
        ArrayList<String> s = rentBikeTransactions.get(0);
        int bikeCode = Integer.parseInt(s.get(1));
        String bikeType = s.get(2);
        int rentBikeCost = Integer.parseInt(s.get(3));
        String owner = s.get(4);
        int priceForFirst30Minutes = Integer.parseInt(s.get(5));
        int priceFor15MinutesAfter30Minutes = Integer.parseInt(s.get(6));
        String rentTime = s.get(7);
        String returnTime = s.get(8);
        int deposit = Integer.parseInt(s.get(9));
        return new RentBikeTransaction(
                rentalCode, bikeCode, bikeType, rentBikeCost, owner,
                priceForFirst30Minutes, priceFor15MinutesAfter30Minutes, rentTime, returnTime, deposit);
    }

    /**
     * Tính toán chi phí thuê xe từ thông tin trong giao dịch thuê xe
     *
     * @param rentBikeTransaction: Giao dịch thuê xe
     * @return: Chi phí tính toán
     */
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

    /**
     * Lấy thông tin của xe từ cơ sở dữ liệu dựa theo bikeCode
     *
     * @param bikeCode: bikeCode của xe
     * @return: ArrayList<String> là một mảng các thuộc tính của xe
     */
    private static Bike getBike(int bikeCode){
        ArrayList<ArrayList<String>> bikeTable = BikeDAO.queryWithBikeCode(bikeCode);
        ArrayList<Bike> s = InitializeController.tableToBikes(bikeTable);
        assert s.size() == 1;
        return s.get(0);
    }

    /**
     * Lấy thời gian hiện tại và chuyển đổi thành format
     *
     * @param pattern: format thời gian
     * @return: Thời gian hiện tại theo format thời gian phía trê
     */
    public static String getCurrentLocalDateTimeStamp(String pattern) {
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern(pattern));
    }
}
