package presentationlayer;

import businesslogiclayer.RentBikeController;
import businesslogiclayer.ReturnBikeController;
import entities.Dock;
import entities.RentBikeTransaction;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import presentationlayer.box.ConfirmBox;
import presentationlayer.box.NotificationBox;
import presentationlayer.box.NotificationBoxWithSize;


import java.io.IOException;
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
                    System.out.println("user confirm to return bike");
                    assert dock != null;
                    newDockID = dock.getDockID();
                    RentBikeTransaction rentBikeTransaction = ReturnBikeController.processReturnBike();
                    if (rentBikeTransaction == null){
                        NotificationBox.display("Đã xảy ra lỗi", "Đã có lỗi xảy ra khi thực hiện giao dịch!");
                    }
                    else{
                        RentBikeController.rentalCode = "";
                        MainScreenController.reset = true;
                        showRentBikeTransactionInfo(rentBikeTransaction);
                        Stage stage = (Stage)docksView.getScene().getWindow();
                        stage.close();
                    }
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

    public void showRentBikeTransactionInfo(RentBikeTransaction rentBikeTransaction) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RentBikeTransactionScreen.fxml"));
            Parent root = (Parent) loader.load();

            RentBikeTransactionScreenController rentBikeTransactionScreenController = loader.getController();
            rentBikeTransactionScreenController.initData(rentBikeTransaction);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.setTitle("RentBikeTransactionInfo");
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
