package presentationlayer;

import businesslogiclayer.controller.CheckCardInUseController;
import businesslogiclayer.controller.RentBikeController;
import entities.Bike;
import entities.Card;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import presentationlayer.box.NotificationBox;
import presentationlayer.box.NotificationErrorCode;

import java.net.URL;
import java.util.ResourceBundle;

public class CardInfoScreen implements Initializable {
    public static Card card = new Card();

    @FXML
    private TextField text1, text2, text3, text4;

    @FXML
    private Button xacnhan, huy;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    /**
     *
     * @param bike : thông tin đầu vào xe ứng với barcode người dùng đã nhập
     * @param bikeInfoStage : màn hình hiển thị thông tin xe
     * @param cardInfoStage : màn hình hiển thị thông tin thẻ
     */
    public void getInfo(Bike bike, Stage cardInfoStage, Stage bikeInfoStage){
        xacnhan.setOnAction(e -> {

            CheckCardInUseController checkCardInUseController = new CheckCardInUseController();
            if (text1.getText().isEmpty() ||text2.getText().isEmpty() || text3.getText().isEmpty() || text4.getText().isEmpty()){
                NotificationBox.display("Notification", "Bạn vui lòng nhập đầy đủ thông tin thẻ!");
            }
            else{
                boolean check = checkCardInUseController.checkCardInUse(text1.getText());
                if (!check){
                    card.setCardCode(text1.getText());
                    card.setOwner(text2.getText());
                    card.setCVV(text3.getText());
                    card.setExpiredDate(text4.getText());
                    String code = RentBikeController.processRentBike(card, bike);
                    NotificationErrorCode.displayNotificationErrorCode(code, "pay");
                    if (code.equals("00")){
                        cardInfoStage.close();
                        bikeInfoStage.close();
                    }

                }
                else{
                    NotificationBox.display("NotificationBox", "Thẻ đang được sử dụng để thuê xe!");
                }
            }
        });
        huy.setOnAction(e ->  cardInfoStage.close());
    }

}
