package dataaccesslayer;

import connection.DBConnection;

import java.util.ArrayList;

/**
 * Lấy thông tin của xe từ cơ sở dữ liệu theo yêu cầu
 * Cập nhật thông tin của xe trong cơ sở dữ liệu theo yêu cầu
 */
public class BikeDAO {
    /**
     * Cập nhật trạng thái có đang được sử dụng hay không của xe và vị trí bãi xe mới của xe
     * @param barcode: barcode của xe
     * @param isInUse: trạng thái mới của xe
     * @param dockID: ID bãi xe mới của xe
     */
    public static void updateIsInUse(int barcode, boolean isInUse, String dockID){
        int flag;
        if (isInUse)
            flag = 1;
        else
            flag = 0;
        String command = "UPDATE bike SET isInUse=" + flag + ", dockID=" + '\'' + dockID + '\'' + " WHERE barcode=" + barcode;
        DBConnection.execute(command);
    }

    /**
     * Lấy danh sách xe tương ứng với địa chỉ bãi xe
     * @param dockID: ID của bãi xe
     * @return: Mảng hai chiều các xe lưu dưới dạng ArrayList<ArrayList<String>>
     */
    public static ArrayList<ArrayList<String>> queryWithDockID(String dockID){
        ArrayList<ArrayList<String>> s = new ArrayList<>();
        String command = "SELECT * from bike WHERE dockID=" + '\'' + dockID + '\'';
        s = DBConnection.query(command);
        return s;
    }

    /**
     * Lấy thông tin của xe dựa theo barcode
     * @param barcode: barcode của xe
     * @return: Thông tin của xe lưu dưới dạng mảng một chiều
     */
    public static ArrayList<ArrayList<String>> queryWithBarcode(int barcode){
        ArrayList<ArrayList<String>> s = new ArrayList<>();
        String command = "SELECT * from bike WHERE barcode=" + '\'' + barcode + '\'';
        s = DBConnection.query(command);
        return s;
    }

    /**
     * Lấy tất cả các xe trong cơ sở dữ liệu
     * @return: Mảng hai chiều các bike ứng với bảng bike trong cơ sở dữ liệu lưu dưới dạng ArrayLis<ArrayList<String>>
     */
    public static ArrayList<ArrayList<String>> getBikes(){
        ArrayList<ArrayList<String>> s = new ArrayList<>();
        String command = "SELECT * from bike";
        s = DBConnection.query(command);
        return s;
    }
}
