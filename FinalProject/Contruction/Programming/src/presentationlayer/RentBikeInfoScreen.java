package presentationlayer;

import businesslogiclayer.controller.RentBikeController;
import entities.Bike;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import presentationlayer.box.NotificationErrorCode;
import java.net.URL;
import java.util.ResourceBundle;

public class RentBikeInfoScreen implements Initializable {
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
    public void init(Bike bike, Stage stage){
        text1.setText(String.valueOf(bike.getBikeCode()));
        text2.setText(bike.getType());
        text3.setText(String.valueOf(bike.getValue()));
        text4.setText(String.valueOf(bike.getPriceForFirst30Minutes()));
        text5.setText(String.valueOf(bike.getPriceFor15MinutesAfter30Minutes()));
        text6.setText(String.valueOf(bike.getRemainBattery()));
        text7.setText(String.valueOf(bike.getMaxTime()));
        text8.setText(bike.getLicensePlate());
        double deposit = bike.getValue()*0.4;
        text9.setText(String.format("%s", (int) deposit));
        xacnhan.setOnAction(e -> {
            stage.close();
            String code = RentBikeController.processRentBike(bike);
            NotificationErrorCode.displayNotificationErrorCode(code, "pay");
        });
        huy.setOnAction(e ->  stage.close());
    }
}
