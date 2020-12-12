package presentationlayer;

import businesslogiclayer.InitializeController;
import businesslogiclayer.RentBikeController;
import entities.Dock;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import presentationlayer.box.NotificationBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {
    public static boolean reset = false;
    ArrayList<Dock> docks;

    @FXML
    private ListView<String> docksView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Initialize main screen");
        // generate fake docks dataset
        docks = InitializeController.getDocks();

        //show list of docks
        for (Dock dock : docks) {
            docksView.getItems().add(dock.getGeneralInfo());
        }

        //listen when user double click on the dock in listview => show ViewDockScreen
        docksView.setOnMouseClicked(click -> {
            if (click.getClickCount() == 2) {
                System.out.println("User double on a dock");
                String dockInfo = docksView.getSelectionModel().getSelectedItem();
                Dock dock = getDockFromString(dockInfo);

                showViewDockScreen(dock);
            }
        });
    }

    public void showViewDockScreen(Dock dock) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewDockScreen.fxml"));
            Parent root = (Parent) loader.load();

            ViewDockScreenController viewDockController = loader.getController();

            viewDockController.initData(dock);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.setTitle("ViewDockScreen");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Dock getDockFromString(String dockInfo) {

        for (Dock dock : docks) {
            String s = dock.getGeneralInfo();
            if (dockInfo.equals(s)) {
                return dock;
            }
        }
        return null;
    }

    public void handleReturnButtonClick() {
        if (RentBikeController.rentalCode.equals("")){
            System.out.println("User is not renting a bike");
            NotificationBox.display("", "Không có xe đang thuê!");
//            Stage stage = (Stage)docksView.getScene().getWindow();
//            stage.close();
        }
        else{
            try {
                System.out.println("user click ReturnBikeButton");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ReturnBikeScreen.fxml"));
                Parent root = loader.load();

                ReturnBikeScreenController returnBikeController = loader.getController();

                returnBikeController.initData(docks);

                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root));
                stage.setTitle("ReturnBikeScreen");
                stage.showAndWait();
                if(MainScreenController.reset){
                    System.out.println("Reset and reload data from database");
                    docks = InitializeController.getDocks();
                    docksView.getItems().clear();
                    //show list of docks
                    for (Dock dock : docks) {
                        docksView.getItems().add(dock.getGeneralInfo());
                    }
                    MainScreenController.reset = false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void handleRentButtonClick() {
        try {
            System.out.println("user click RentBikeButton");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RentBikeScreen.fxml"));
            Parent root = (Parent) loader.load();

//            RentBikeController rentBikeController = loader.getController();
//
//            rentBikeController.initData(docks);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.setTitle("RentBikeScreen");
            stage.showAndWait();

            if(MainScreenController.reset){
                System.out.println("Reset and reload data from database");
                docks = InitializeController.getDocks();
                docksView.getItems().clear();
                //show list of docks
                for (Dock dock : docks) {
                    docksView.getItems().add(dock.getGeneralInfo());
                }
                MainScreenController.reset = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
