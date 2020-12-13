package businesslogiclayer;
import businesslogiclayer.interbanksubsystem.*;
import dataaccesslayer.*;
import entities.*;
import entities.PaymentTransaction;
import entities.RentBikeTransaction;
import javafx.util.Pair;
import presentationlayer.MainScreenController;
import presentationlayer.RentBikeScreenController;
import presentationlayer.box.NotificationBox;

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
    final Card card = Card.getInstance();
    ArrayList<ArrayList<String>> listBike = BikeDAO.getBikes();
    public static ArrayList<String> bikeIsRented;

    /**
     *
     * @param barcode : mã xe
     * @return  <mã check barcode, thông tin xe (nếu barcode đúng)>
     */
    public Pair<Boolean, ArrayList<String>> checkBarcodeAndGetBikeIfTrue(int barcode){
        boolean check = false;
        ArrayList<String> _bike = new ArrayList<>();
        for (ArrayList<String> bike : listBike){
            if (barcode == Integer.parseInt(bike.get(0)) && Integer.parseInt(bike.get(1)) == 0){
                check = true;
                _bike = bike;
                bikeIsRented = bike;
            }
        }
        if (check){
            rentalCode = convertBarcodeToRentalCode(barcode);
        }
        return new Pair<>(check, _bike);
    }

    /**
     * Xử lý giao dịch thuê xe
     * Nếu giao dịch thành công thì sẽ tiến hành lưu lại giao dịch thanh toán,
     * thông tin phiên thuê xe và cập nhật xe thành đang sử dụng.
     *
     * Nếu giao dịch thất bại thì sẽ đưa ra thông báo lỗi và không lưu lại thông tin.
     */
    public String processRentBike(){
        IInterbank interbank = new InterbankSubsysController();
        int cost = (int) calculateDeposit();
        System.out.println(cost);
        String code = interbank.processTransaction(cost, "pay", "Trừ tiền cọc");
        if (code.equals("00")){
            System.out.println("Đã trừ: " + cost + "VNĐ");
//            NotificationBox.display("Notification", "Bạn đã thuê xe thành công, EcoBike chúc bạn có chuyến đi an toàn và vui vẻ!");
            MainScreenController.reset = true;
            RentBikeScreenController.rent = true;
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            DateFormat t = new SimpleDateFormat("HH:mm:ss");
            DateFormat d = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat td = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
             * Lưu lại thông tin phiên thuê xe
             */
            RentBikeTransaction rentBikeTransaction = new RentBikeTransaction(
                    rentalCode,
                    Integer.parseInt(bikeIsRented.get(0)),
                    bikeIsRented.get(2),
                    -1,
                    card.getOwner(),
                    Integer.parseInt(bikeIsRented.get(4)),
                    Integer.parseInt(bikeIsRented.get(5)),
                    td.format(date),
                    "",
                    cost);
            rentBikeTransaction.saveRentBikeTransaction();
            /**
             * Cập nhật trạng thái xe
             */
            Bike bike = new Bike(
                    Integer.parseInt(bikeIsRented.get(0)),
                    false,
                    bikeIsRented.get(2),
                    Integer.parseInt(bikeIsRented.get(3)),
                    Integer.parseInt(bikeIsRented.get(4)),
                    Integer.parseInt(bikeIsRented.get(5)),
                    Integer.parseInt(bikeIsRented.get(6)),
                    Float.parseFloat(bikeIsRented.get(7)),
                    bikeIsRented.get(8));
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
     * @param barcode
     * Sinh ra rental code cho phiên thuê xe
     * @return rental code
     */
    public String convertBarcodeToRentalCode(int barcode){
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = df.format(date);
        return barcode + dateString;
    }

    /**
     * @return giá trị tiền đặt cọc  = 40% giá trị xe
     */
    public double calculateDeposit(){
        return Integer.parseInt(bikeIsRented.get(3)) * 0.4;
    }
}
