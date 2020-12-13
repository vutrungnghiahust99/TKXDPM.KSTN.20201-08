package presentationlayer;

import businesslogiclayer.RentBikeController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import presentationlayer.box.NotificationBox;
import presentationlayer.box.NotificationErrorCode;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DisplayBikeInfoScreenController implements Initializable {
    @FXML
    private TextField text1, text2, text3, text4, text5, text6, text7, text8, text9;
    @FXML
    private Button xacnhan, huy;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    /**
     *
     * @param bike : thông tin đầu vào xe ứng với barcode người dùng đã nhập
     * @param stage : màn hình hiển thị thông tin xe
     */
    public void init(ArrayList<String> bike, Stage stage){
        text1.setText(bike.get(0));
        text2.setText(bike.get(2));
        text3.setText(bike.get(3));
        text4.setText(bike.get(4));
        text5.setText(bike.get(5));
        text6.setText(bike.get(6));
        text7.setText(bike.get(7));
        text8.setText(bike.get(8));
        double deposit = Integer.parseInt(bike.get(3))*0.4;
        text9.setText(String.format("%s", (int) deposit));
        xacnhan.setOnAction(e -> {
            stage.close();
            RentBikeController rentBikeController = new RentBikeController();
            String code = rentBikeController.processRentBike();
            NotificationErrorCode.displayNotificationErrorCode(code, "pay");
        });
        huy.setOnAction(e ->  stage.close());
    }
}
