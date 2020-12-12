package businesslogiclayer.interbanksubsystem;

import presentationlayer.box.NotificationBox;

public class ProcessErrorCode {
    /**
     *
     * @param code : mã error code mà api gửi lại
     * Xử lý error code để đưa ra thông báo tương ứng.
     */
    public void process(String code){
        switch (code) {
            case "00": System.out.println("Giao dịch thành công");
            case "01": NotificationBox.display("Notification", "Thẻ không hợp lệ!");
            case "02": NotificationBox.display("Notification", "Thẻ không đủ số dư!");
            case "03": NotificationBox.display("Notification", "Internal Server Error!");
            case "04": NotificationBox.display("Notification", "Giao dịch bị nghi ngờ gian lận!");
            case "05": NotificationBox.display("Notification", "Không đủ thông tin giao dịch!");
            case "06": NotificationBox.display("Notification", "Thiếu thông tin version!");
            case "07": NotificationBox.display("Notification", "Amount không hợp lệ!");
            default: NotificationBox.display("Notification", "404 Not Found, bạn vui lòng liên hệ nhà phát hành!");
        }
    }
}
