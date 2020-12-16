package dataaccesslayer;

import java.util.ArrayList;

/**
 * Lấy thông tin của bãi xe từ cơ sở dữ liệu theo yêu cầu
 */
public class DockDAO {
    /**
     * Lấy tất cả các bãi xe trong cơ sở dữ liệu
     * @return: Mảng hai chiều lưu dưới dạng ArrayList<ArrayList<String>>
     */
    public static ArrayList<ArrayList<String>> getAllDocks(){
        String command = "SELECT * FROM dock";
        return DBConnection.query(command);
    }
}
