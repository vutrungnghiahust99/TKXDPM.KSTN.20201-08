package presentationlayer;

import entities.Bike;
import entities.Dock;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewDockScreenController implements Initializable {
    private Dock dock;

    @FXML
    private ListView<String> bikesView;

    @FXML
    private Text dockInfo;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        System.out.println("default initialize ViewDockScreen");

        //listen when user double click on a bike in listview => show ViewBikeScreen
        bikesView.setOnMouseClicked(click -> {
            if (click.getClickCount() == 2) {
                System.out.println("User double on a dock");
                String dockInfo = bikesView.getSelectionModel().getSelectedItem();
                Bike bike = getBikeFromString(dockInfo);

                showViewBikeScreen(bike);
            }
        });
    }

    public void initData(Dock dock){
        System.out.println("initialize dockInfo and bikesInfo ");
        this.dock = dock;
        dockInfo.setText(dock.getDetailInfo());
        ArrayList<Bike> bikes = dock.getBikes();
        for(Bike bike : bikes)
            bikesView.getItems().add(bike.toString());
    }

    public void showViewBikeScreen(Bike bike){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewBikeScreen.fxml"));
            Parent root = (Parent) loader.load();

            ViewBikeScreenController viewBikeController = loader.getController();

            viewBikeController.initData(bike);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.setTitle("ViewBikeScreen");
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private Bike getBikeFromString(String bikeInfo){

        for(Bike bike: dock.getBikes()){
            String s = bike.toString();
            if(bikeInfo.equals(s)){
                return bike;
            }
        }
        return null;
    }
}
