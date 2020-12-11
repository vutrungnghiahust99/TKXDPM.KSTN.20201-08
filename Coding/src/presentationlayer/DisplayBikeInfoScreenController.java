package presentationlayer;

import businesslogiclayer.RentBikeController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import presentationlayer.box.NotificationBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DisplayBikeInfoScreenController implements Initializable {

    @FXML
    private TextField text3;

    @FXML
    private TextField text4;

    @FXML
    private TextField text1;

    @FXML
    private TextField text2;

    @FXML
    private TextField text7;

    @FXML
    private Button xacnhan;

    @FXML
    private TextField text8;

    @FXML
    private TextField text5;

    @FXML
    private TextField text6;

    @FXML
    private Button huy;

    @FXML
    private TextField text9;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

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
        text9.setText(String.format("%s", deposit));

        xacnhan.setOnAction(e -> {
            stage.close();
            RentBikeScreenController.rent = true;
            RentBikeController rentBikeController = new RentBikeController();
            MainScreenController.reset = true;
            rentBikeController.processRentBike();
            NotificationBox.display("NotificationBox", "Thuê xe thành công!");
        });
        huy.setOnAction(e -> {
            stage.close();
        });
    }
}
