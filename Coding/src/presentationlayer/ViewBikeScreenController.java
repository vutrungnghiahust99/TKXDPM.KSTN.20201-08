package presentationlayer;

import businesslogiclayer.RentBikeController;
import entities.Bike;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewBikeScreenController implements Initializable {
    @FXML
    private TextField text1, text2, text3, text4, text5, text6, text7, text8;
    @FXML
    private Button OK;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void init(Bike bike){
        text1.setText(Integer.toString(bike.getBarcode()));
        text2.setText(bike.getType());
        text3.setText(Integer.toString(bike.getValue()));
        text4.setText(Integer.toString(bike.getPriceForFirst30Minutes()));
        text5.setText(Integer.toString(bike.getPriceFor15MinutesAfter30Minutes()));
        text6.setText(Integer.toString(bike.getRemainBattery()));
        text7.setText(Float.toString(bike.getMaxTime()));
        text8.setText(bike.getLicensePlate());
    }

    public void processOKClick(){
        System.out.println("User click on OK button");
        Stage stage = (Stage) OK.getScene().getWindow();
        stage.close();
    }
}
