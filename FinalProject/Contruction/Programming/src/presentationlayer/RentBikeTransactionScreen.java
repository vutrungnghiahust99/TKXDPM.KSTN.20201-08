package presentationlayer;

import entities.RentBikeTransaction;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RentBikeTransactionScreen {
    @FXML
    private TextField text1, text2, text3, text4, text5, text8, text9, text10;

    @FXML
    private Button OK;

    public void initData(RentBikeTransaction rentBikeTransaction){
        System.out.println("display rentbiketransaction info");
        System.out.println(rentBikeTransaction.getDetailInfo());
        text1.setText(rentBikeTransaction.getRentalCode());
        text2.setText(Integer.toString(rentBikeTransaction.getBikeCode()));
        text3.setText(rentBikeTransaction.getType());
        text4.setText(Integer.toString(rentBikeTransaction.getRentBikeCost()));
        text5.setText(rentBikeTransaction.getOwner());
        text8.setText(rentBikeTransaction.getRentTime());
        text9.setText(rentBikeTransaction.getReturnTime());
        text10.setText(Integer.toString(rentBikeTransaction.getDeposit()));
    }

    @FXML
    public void processOKClick(){
        Stage stage = (Stage)OK.getScene().getWindow();
        stage.close();
    }
}
