package presentationlayer;

import entities.Dock;
import entities.RentBikeTransaction;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class RentBikeTransactionScreenController {
    @FXML
    private TextField text1, text2, text3, text4, text5, text6, text7, text8, text9, text10;

    @FXML
    private Button OK;

    public void initData(RentBikeTransaction rentBikeTransaction){
        System.out.println("display rentbiketransaction info");
        text1.setText(rentBikeTransaction.getRentalCode());
        text2.setText(Integer.toString(rentBikeTransaction.getBarcode()));
        text3.setText(rentBikeTransaction.getType());
        text4.setText(Integer.toString(rentBikeTransaction.getRentBikeCost()));
        text5.setText(rentBikeTransaction.getOwner());
        text6.setText(Integer.toString(rentBikeTransaction.getPriceForFirst30Minutes()));
        text7.setText(Integer.toString(rentBikeTransaction.getPriceFor15MinutesAfter30Minutes()));
        text8.setText(rentBikeTransaction.getRentTime());
        text9.setText(rentBikeTransaction.getReturnTime());
        text10.setText(Integer.toString(rentBikeTransaction.getDeposit()));
    }

    public void processOKClick(){
        Stage stage = (Stage)OK.getScene().getWindow();
        stage.close();
    }
}
