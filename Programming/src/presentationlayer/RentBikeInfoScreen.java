package presentationlayer;

import businesslogiclayer.controller.RentBikeController;
import entities.Bike;
import entities.Card;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import presentationlayer.box.NotificationErrorCode;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RentBikeInfoScreen implements Initializable {
    @FXML
    private TextField text1, text2, text3, text4, text5, text6, text8, text9,text10;

    @FXML
    private TextArea textArea;

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
        text4.setText(String.valueOf(bike.getNumPedals()));
        text5.setText(String.valueOf(bike.getNumSaddle()));
        text6.setText(String.valueOf(bike.getNumBicycleSeat()));
        textArea.setText(bike.getMoreDetails());
        text8.setText(bike.getDockID());
        text9.setText(bike.getLicensePlate());
        System.out.println("deposit: " + bike.calculateDeposit());
        text10.setText(String.valueOf(bike.calculateDeposit()));
        xacnhan.setOnAction(e -> {
            displayCardScreen(bike, stage);
        });
        huy.setOnAction(e ->  stage.close());
    }

    public void displayCardScreen(Bike bike, Stage stage){
        try {
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("CardInfoScreen.fxml"));
            Parent root = loader1.load();
            CardInfoScreen cardInfoScreen = loader1.getController();
            Stage stageCard = new Stage();
            stageCard.setTitle("EcoBike");
            stageCard.setScene(new Scene(root));
            cardInfoScreen.getInfo(bike, stageCard, stage);
            stageCard.showAndWait();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
