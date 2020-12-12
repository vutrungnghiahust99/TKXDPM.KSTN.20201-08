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
            case "00": System.out.println("Giao dịch thành công"); break;
            case "01": NotificationBox.display("Notification", "Thẻ không hợp lệ!"); break;
            case "02": NotificationBox.display("Notification", "Thẻ không đủ số dư!"); break;
            case "03": NotificationBox.display("Notification", "Internal Server Error!"); break;
            case "04": NotificationBox.display("Notification", "Giao dịch bị nghi ngờ gian lận!"); break;
            case "05": NotificationBox.display("Notification", "Không đủ thông tin giao dịch!"); break;
            case "06": NotificationBox.display("Notification", "Thiếu thông tin version!"); break;
            case "07": NotificationBox.display("Notification", "Amount không hợp lệ!"); break;
            default: NotificationBox.display("Notification", "404 Not Found, bạn vui lòng liên hệ nhà phát hành!");
        }
    }
}
