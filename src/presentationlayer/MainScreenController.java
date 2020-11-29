package presentationlayer;

import entities.Bicycle;
import entities.Dock;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {
    @FXML
    private ListView<String> docksView;

    ArrayList<Dock> docks;
    @Override
    public void initialize(URL url, ResourceBundle rb){
        System.out.println("Initialize main screen");
        // generate fake docks dataset
        docks = Dock.getRandomDocks();

        //show list of docks
        for(Dock dock: docks){
            docksView.getItems().add(dock.toString());
        }

        //listen when user double click on the dock in listview => show ViewDockScreen
        docksView.setOnMouseClicked(click -> {
            if (click.getClickCount() == 2) {
                System.out.println("User double on a dock");
                String dockInfo = docksView.getSelectionModel().getSelectedItem();
                ArrayList<String> bicycles = getBicyclesInfo(dockInfo);

                showViewDockScreen(dockInfo, bicycles);
            }
        });
    }

    public void showViewDockScreen(String dockInfo, ArrayList<String> bicycles){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewDockScreen.fxml"));
            Parent root = (Parent) loader.load();

            ViewDockController viewDockController = loader.getController();

            viewDockController.initData(dockInfo, bicycles);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private ArrayList<String> getBicyclesInfo(String dockInfo){

        for(Dock dock: docks){
            String s = dock.toString();
            if(dockInfo.equals(s)){
                ArrayList<String> info = new ArrayList<>();
                for(Bicycle bicycle: dock.getBicycles())
                    info.add(bicycle.toString());
                return info;
            }
         }
        return null;
    }
}
