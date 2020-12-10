package presentationlayer;

import businesslogiclayer.RentBikeController;
import businesslogiclayer.ReturnBikeController;
import entities.Dock;
import entities.RentBikeTransaction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import presentationlayer.box.ConfirmBox;
import presentationlayer.box.NotificationBox;
import presentationlayer.box.NotificationBoxWithSize;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReturnBikeScreenController implements Initializable {
    public static String newDockID = null;
    private ArrayList<Dock> docks;

    @FXML
    private ListView<String> docksView;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        System.out.println("default initialize ReturnBikeScreen");

        //listen when user double click on a bike in listview => show ConfirmReturnBikeScreen
        docksView.setOnMouseClicked(click -> {
            if (click.getClickCount() == 2) {
                System.out.println("User double on a dock");
                String dockInfo = docksView.getSelectionModel().getSelectedItem();
                Dock dock = getDockFromString(dockInfo);  // for further usage when we want to update dock
                boolean confirmReturnBike = ConfirmBox.display("ConfirmBox", "Xác nhận trả xe?");
                System.out.println(confirmReturnBike);
                if(confirmReturnBike){
                    assert dock != null;
                    newDockID = dock.getDockID();
                    RentBikeTransaction rentBikeTransaction = ReturnBikeController.processReturnBike();
                    RentBikeController.rentalCode = "";
                    MainScreenController.reset = true;

                    System.out.println("user confirm to return bike");
                    NotificationBoxWithSize.display("NotificationBox", rentBikeTransaction.getDetailInfo() + "\n  Trả xe thành công!", 400);
                    Stage stage = (Stage)docksView.getScene().getWindow();
                    stage.close();
                }
            }
        });
    }

    public void initData(ArrayList<Dock> docks){
        System.out.println("initialize dockInfo and bikesInfo ");
        this.docks = docks;
        for(Dock dock : docks)
            docksView.getItems().add(dock.getGeneralInfo());
    }


    private Dock getDockFromString(String dockInfo){

        for(Dock dock: docks){
            String s = dock.getGeneralInfo();
            if(dockInfo.equals(s)){
                return dock;
            }
        }
        return null;
    }
}
