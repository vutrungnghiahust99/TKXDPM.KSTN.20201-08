package presentationlayer;

import entities.Bike;
import entities.Dock;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewDockController implements Initializable {
    private Dock dock;

    @FXML
    private ListView<String> bikesView;

    @FXML
    private Text dockInfo;

    @FXML
    private Button returnMainScreenButton;

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

    public void handleReturnMainScreenButton(ActionEvent event){
        System.out.println("User click return to Main Screen");
        Stage stage = (Stage) returnMainScreenButton.getScene().getWindow();
        stage.close();
    }

    public void showViewBikeScreen(Bike bike){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewBikeScreen.fxml"));
            Parent root = (Parent) loader.load();

            ViewBikeController viewBikeController = loader.getController();

            viewBikeController.initData(bike);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
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
