package businesslogiclayer.controller;
import businesslogiclayer.interbanksubsystem.*;
import businesslogiclayer.barcodconvertersubsystem.*;
import dataaccesslayer.*;
import businesslogiclayer.entities.*;
import businesslogiclayer.entities.PaymentTransaction;
import businesslogiclayer.entities.RentBikeTransaction;
import javafx.util.Pair;
import presentationlayer.MainScreenController;
import presentationlayer.RentBikeScreenController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 */
public class RentBikeController {
    /**
     * rentalCode : Mã thuê xe được dùng cho toàn bộ những giao dịch và phiên thuê xe hiện tại.
     *              Phiên thuê xe khác nhau sẽ có rentalCode khác nhau.
     */
    public static String rentalCode = "";
    private static final Card card = Card.getInstance();
    private static ArrayList<ArrayList<String>> listBike = BikeDAO.getBikes();
    private static ArrayList<String> bikeIsRented;

    /**
     *
     * @param barcode : mã xe
     * @return  <mã check bikeCode, thông tin xe (nếu bikeCode đúng)>
     */
    public static Pair<Boolean, Bike> checkBarcodeAndGetBikeIfTrue(int barcode){
        IBarcodeConverter bc = new BarcodeConverterController();
        int bikeCode = bc.convertBarcodeToBikeCode(barcode);
        boolean check = false;
        Bike bike = null;

        for (ArrayList<String> b : listBike){
            if (bikeCode == Integer.parseInt(b.get(0)) && Integer.parseInt(b.get(1)) == 0){
                check = true;
                bikeIsRented = b;
                break;
            }
        }
        if (check){
            bike = new Bike(
                    Integer.parseInt(bikeIsRented.get(0)),
                    false,
                    bikeIsRented.get(2),
                    Integer.parseInt(bikeIsRented.get(3)),
                    Integer.parseInt(bikeIsRented.get(4)),
                    Integer.parseInt(bikeIsRented.get(5)),
                    Integer.parseInt(bikeIsRented.get(6)),
                    Float.parseFloat(bikeIsRented.get(7)),
                    bikeIsRented.get(8));
            rentalCode = convertBikeCodeToRentalCode(bikeCode);
        }
        return new Pair<>(check, bike);
    }

    /**
     * Xử lý giao dịch thuê xe
     * Nếu giao dịch thành công thì sẽ tiến hành lưu lại giao dịch thanh toán,
     * thông tin phiên thuê xe và cập nhật xe thành đang sử dụng.
     *
     * Nếu giao dịch thất bại thì sẽ đưa ra thông báo lỗi và không lưu lại thông tin.
     */
    public static String processRentBike(Bike bike){
        IInterbank interbank = new InterbankSubsysController();
        int cost = (int) calculateDeposit();
        System.out.println(cost);
        interbank.reset();
        String code = interbank.processTransaction(cost, "pay", "Trừ tiền cọc");
        if (code.equals("00")){
            System.out.println("Đã trừ: " + cost + "VNĐ");
            MainScreenController.reset = true;
            RentBikeScreenController.rent = true;
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            DateFormat t = new SimpleDateFormat("HH:mm:ss");
            DateFormat d = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat td = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            /**
             * Lưu lại thông tin phiên thuê xe
             */
            RentBikeTransaction rentBikeTransaction = new RentBikeTransaction(
                    rentalCode,
                    bike.getBikeCode(),
                    bike.getType(),
                    -1,
                    card.getOwner(),
                    bike.getPriceForFirst30Minutes(),
                    bike.getPriceFor15MinutesAfter30Minutes(),
                    td.format(date),
                    "",
                    cost);
            rentBikeTransaction.saveRentBikeTransaction();
            /**
             * Lưu lại giao dịch thanh toán
             */
            PaymentTransaction paymentTransaction = new PaymentTransaction(
                    rentalCode,
                    card.getCardCode(),
                    card.getOwner(),
                    "Trừ tiền cọc",
                    cost,
                    t.format(date),
                    d.format(date));
            paymentTransaction.savePaymentTransaction();
            /**
             * Cập nhật trạng thái xe
             */
            bike.updateInUseAndDockID(true, bikeIsRented.get(9));
        }
        else{
            rentalCode = "";
            System.out.println("Giao dịch thất bại");
        }
        return code;
    }

    /**
     *
     * @param bikeCode
     * Sinh ra rental code cho phiên thuê xe
     * @return rental code
     */
    public static String convertBikeCodeToRentalCode(int bikeCode){
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = df.format(date);
        return bikeCode + dateString;
    }

    /**
     * @return giá trị tiền đặt cọc  = 40% giá trị xe
     */
    public static double calculateDeposit(){
        return Integer.parseInt(bikeIsRented.get(3)) * 0.4;
    }
}
