package presentationlayer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import presentationlayer.box.ConfirmBox;
import presentationlayer.box.NotificationBox;

public class Main extends Application{
    @Override
    public void start (Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        primaryStage.setTitle("EcoBike");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
//        ConfirmBox.display("ConfirmBox", "Xác nhận trả xe?");
//        NotificationBox.display("ERROR", "Bãi xe không còn chỗ trống!");
//        NotificationBox.display("Notification", "Bạn đã trả xe thành công!");
//        NotificationBox.display("Notification", "Bạn đã thuê xe thành công, \nEcoBike chúc bạn có chuyến đi an toàn và vui vẻ!");
//        NotificationBox.display("Notification", "Thẻ không hợp lệ!");
//        NotificationBox.display("Notification", "Thẻ không đủ số dư!");
//        NotificationBox.display("Notification", "Internal Server Error!");
//        NotificationBox.display("Notification", "Giao dịch bị nghi ngờ gian lận!");
//        NotificationBox.display("Notification", "Không đủ thông tin giao dịch!");
//        NotificationBox.display("Notification", "Thiếu thông tin version!");
//        NotificationBox.display("Notification", "Amount không hợp lệ!");
//        NotificationBox.display("Notification", "404 Not Found, bạn vui lòng liên hệ nhà phát hành!");
    }
    public static void main(String[] args) {
        launch(args);
    }
}