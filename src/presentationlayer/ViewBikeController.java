package presentationlayer;

import entities.Bike;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;

public class ViewBikeController implements Initializable {
    private Bike bike;

    @FXML
    private Text bikeInfo;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        System.out.println("default initialize ViewBikeScreen");
    }

    public void initData(Bike bike){
        System.out.println("initialize bikeInfo");
        this.bike = bike;
        bikeInfo.setText(bike.getDetailInfo());
    }

}
