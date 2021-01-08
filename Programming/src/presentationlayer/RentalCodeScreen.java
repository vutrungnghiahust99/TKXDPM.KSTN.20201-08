package presentationlayer;
import businesslogiclayer.controller.InitializeController;
import businesslogiclayer.controller.ReturnBikeController;
import entities.Dock;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import presentationlayer.box.NotificationBox;;import java.util.ArrayList;

public class RentalCodeScreen {
//    private String s;
    ArrayList<Dock> docks;
    @FXML
    private TextField rentalCode;

    @FXML
    void handleButtonSend(){
        String message = rentalCode.getText();
        if (message.isEmpty()){
            NotificationBox.display("NotificationBox", "Vui lòng nhập mã thuê xe!");
        }
        else{
            String s = rentalCode.getText();
            System.out.println(rentalCode);
            if(!ReturnBikeController.checkRentalCodeValid(s)){
                NotificationBox.display("NotificationBox", "Mã thuê xe không hợp lệ!");
            }
            else{
                Stage stage = (Stage) rentalCode.getScene().getWindow();
                stage.close();
                System.out.println("Display ReturnBikeScreen");
                showReturnBikeScreen();
            }
        }
    }

    public void initData(ArrayList<Dock> docks){
        this.docks = docks;
    }

    void showReturnBikeScreen(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReturnBikeScreen.fxml"));
            Parent root = loader.load();

            ReturnBikeScreen returnBikeScreen = loader.getController();
            returnBikeScreen.initData(this.docks, rentalCode.getText());
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.setTitle("ReturnBikeScreen");
            stage.showAndWait();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
