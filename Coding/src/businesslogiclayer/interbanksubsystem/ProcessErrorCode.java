package businesslogiclayer.interbanksubsystem;

import presentationlayer.box.NotificationBox;

import javax.management.Notification;

public class ProcessErrorCode {
    public void process(String code){
        if (code.equals("00")){
            System.out.println("Giao dịch thành công");
        }
        else if (code.equals("01")){
            NotificationBox.display("Notification", "Thẻ không hợp lệ!");
        }
        else if (code.equals("02")){
            NotificationBox.display("Notification", "Thẻ không đủ số dư!");
        }
        else if (code.equals("03")){
            NotificationBox.display("Notification", "Internal Server Error!");
        }
        else if (code.equals("04")){
            NotificationBox.display("Notification", "Giao dịch bị nghi ngờ gian lận!");
        }
        else if (code.equals("05")){
            NotificationBox.display("Notification", "Không đủ thông tin giao dịch!");
        }
        else if (code.equals("06")){
            NotificationBox.display("Notification", "Thiếu thông tin version!");
        }
        else if (code.equals("07")){
            NotificationBox.display("Notification", "Amount không hợp lệ!");
        } else {
            NotificationBox.display("Notification", "404 Not Found, bạn vui lòng liên hệ nhà phát hành!");
        }
    }
}
