package presentationlayer;
import businesslogiclayer.*;
import dataaccesslayer.BikeDAO;
import entities.Bike;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Pair;
import presentationlayer.box.*;

import java.io.IOException;
import java.util.ArrayList;

public class RentBikeScreenController {
    private RentBikeController rentBikeController = new RentBikeController();
    ArrayList<ArrayList<String>> listBike = BikeDAO.getBikes();
    @FXML
    private TextField barcode;
    static boolean rent = false;

    @FXML
    void handleButtonThueXe(ActionEvent event){
        String message = barcode.getText();
        if (message.isEmpty()){
            NotificationBox.display("NotificationBox", "Vui lòng nhập barcode của xe!");
        }
        else{
            if (rent == false) {
                int inputCode = Integer.parseInt(message);
                Pair<Boolean, ArrayList<String>> p = rentBikeController.checkBarcodeAndGetBikeIfTrue(inputCode);
                if (p.getKey()) {
                    System.out.println(p.getValue());
                    display(p.getValue());

                } else {
                    NotificationBox.display("NotificationBox", "Barcode không hợp lệ!");
                }
            }
            else{
                NotificationBox.display("NotificationBox", "Bạn vui lòng trả xe đang thuê để thuê xe mới!");
            }
        }
    }

    public void display(ArrayList<String> bike) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DisplayBikeInfoScreen.fxml"));
            Parent root = loader.load();
            DisplayBikeInfoScreenController dp = (DisplayBikeInfoScreenController) loader.getController();
            Stage stage = new Stage();
            stage.setTitle("EcoBike");
            dp.init(bike, stage);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
