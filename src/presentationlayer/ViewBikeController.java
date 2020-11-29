package presentationlayer;

import entities.Bike;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

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
