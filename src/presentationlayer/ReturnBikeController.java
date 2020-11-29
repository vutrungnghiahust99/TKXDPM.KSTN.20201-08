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

public class ReturnBikeController implements Initializable {
    private ArrayList<Dock> docks;

    @FXML
    private ListView<String> docksView;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        System.out.println("default initialize ReturnBikeScreen");

//        //listen when user double click on a bike in listview => show ConfirmReturnBikeScreen
//        docksView.setOnMouseClicked(click -> {
//            if (click.getClickCount() == 2) {
//                System.out.println("User double on a dock");
//                String dockInfo = docksView.getSelectionModel().getSelectedItem();
//                Dock dock = getDockFromString(dockInfo);  // for further usage when we want to update dock
////                Bike bike =
////
////                showViewBikeScreen(bike);
//            }
//        });
    }

    public void initData(ArrayList<Dock> docks){
        System.out.println("initialize dockInfo and bikesInfo ");
        this.docks = docks;
        for(Dock dock : docks)
            docksView.getItems().add(dock.toString());
    }

//    public void showViewBikeScreen(Bike bike){
//        try{
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewBikeScreen.fxml"));
//            Parent root = (Parent) loader.load();
//
//            ViewBikeController viewBikeController = loader.getController();
//
//            viewBikeController.initData(bike);
//
//            Stage stage = new Stage();
//            stage.initModality(Modality.APPLICATION_MODAL);
//            stage.setScene(new Scene(root));
//            stage.setTitle("ViewBikeScreen");
//            stage.show();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//    }
//
    private Dock getDockFromString(String dockInfo){

        for(Dock dock: docks){
            String s = dock.toString();
            if(dockInfo.equals(s)){
                return dock;
            }
        }
        return null;
    }
}
