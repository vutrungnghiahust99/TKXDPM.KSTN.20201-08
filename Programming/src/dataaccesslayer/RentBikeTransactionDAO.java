package dataaccesslayer;

import java.util.ArrayList;

/**
 * Lưu, cập nhật và truy vấn giao dịch thuê xe trong cơ sở dữ liệu
 */
public class RentBikeTransactionDAO {
    /**
     * Lưu giao dịch thuê xe vào cơ sở dữ liệu
     * @param bikeCode: bikeCode của xe
     * @param rentalCode: mã thuê xe
     * @param type: loại xe
     * @param rentTime: thời điểm thuê xe
     * @param returnTime: thời điểm trả xe
     * @param rentBikeCost: Chi phí thuê xe
     * @param owner: Người thuê
     * @param priceForFirst30Minutes: Giá thuê xe trong 30 phút đầu tiên
     * @param priceFor15MinutesAfter30Minutes: Giá thuê trong 15 phút sau 30 phút đầu tiên
     * @param deposit: tiền đặt cọc
     */
    public static void save(int bikeCode, String rentalCode, String type, String rentTime, String returnTime,
                            int rentBikeCost, String owner, int priceForFirst30Minutes,
                            int priceFor15MinutesAfter30Minutes, int deposit){
        String command = "INSERT INTO rentbiketransaction " +
                "(rentalCode, bikeCode, type, rentBikeCost, owner, priceFor30FirstMinutes, priceFor15MinutesAfter30Minutes, rentTime, returnTime, deposit) VALUES " +
                "(" + '\'' + rentalCode + '\'' + ", " +
                bikeCode + ", " +
                '\'' + type + '\'' + ", " +
                rentBikeCost + ", " +
                '\'' + owner + '\'' + ", " +
                priceForFirst30Minutes + ", " +
                priceFor15MinutesAfter30Minutes + ", " +
                '\'' + rentTime + '\'' + ", " +
                '\'' + returnTime + '\'' + ", " +
                deposit + ")";

        DBConnection.execute(command);
    }

    /**
     * Lấy thông tin giao dịch thuê xe dựa trên mã thuê xe
     * @param rentalCode: mã thuê xe
     * @return: Thông tin giao dịch thuê xe lưu dưới dạng mảng hai chiều có 1 dòng
     */
    public static ArrayList<ArrayList<String>> queryByRentalCode(String rentalCode){
        String command = "SELECT * FROM rentbiketransaction WHERE rentalCode=" + '\'' + rentalCode + '\'';
        System.out.println(command);
        return DBConnection.query(command);
    }

    /**
     * Cập nhật thời gian trả xe và chi phí thuê xe của hóa đơn thuê xe sau khi người dùng trả xe
     * @param rentalCode: mã thuê xe
     * @param rentBikeCost: chi phí thuê xe
     * @param returnTime: thời điểm trả xe
     */
    public static void updateReturnTimeAndCost(String rentalCode, int rentBikeCost, String returnTime){
        String command = "UPDATE rentbiketransaction SET rentBikeCost=" + rentBikeCost + ", returnTime=" + '\'' + returnTime + '\'' + " WHERE rentalCode=" + '\'' + rentalCode + '\'';
        DBConnection.execute(command);
    }
}
