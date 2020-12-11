package presentationlayer.box;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DisplayBikeInfoScreenController implements Initializable {
    @FXML
    private static Label label1;

    @FXML
    private static TextField text3;

    @FXML
    private static TextField text4;

    @FXML
    private static TextField text1;

    @FXML
    private static TextField text2;

    @FXML
    private static TextField text7;

    @FXML
    private static Button xacnhan;

    @FXML
    private static TextField text8;

    @FXML
    private static TextField text5;

    @FXML
    private static TextField text6;

    @FXML
    private static Button huy;

    @FXML
    private static TextField text9;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        text1.setText("");
//        text2.setText("");
//        text3.setText("");
//        text4.setText("");
//        text5.setText("");
//        text6.setText("");
//        text7.setText("");
//        text8.setText("");
//
//        text9.setText("");
        label1.setText("");
    }

    public static void setControl(Stage stage, ArrayList<String> bike){
//        text1.setText(bike.get(0));
//        text2.setText(bike.get(2));
//        text3.setText(bike.get(3));
//        text4.setText(bike.get(4));
//        text5.setText(bike.get(5));
//        text6.setText(bike.get(6));
//        text7.setText(bike.get(7));
//        text8.setText(bike.get(8));
//        double deposit = Integer.parseInt(bike.get(3))*0.4;
//        text9.setText(String.format("%s", deposit));
        label1.setText("Hello Nam!");
//        xacnhan.setOnAction(e -> {
//            stage.close();
//            NotificationBox.display("NotificationBox", "Thuê xe thành công!");
//        });
//        huy.setOnAction(e -> {
//            stage.close();
//        });
    }

    public static void display(ArrayList<String> bike) {
        try {
            FXMLLoader loader = FXMLLoader.load(DisplayBikeInfoScreenController.class.getResource("DisplayBikeInfoScreen.fxml"));
            loader.setController(this);
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("EcoBike");
            stage.setScene(new Scene(root));
            stage.show();
            setControl(stage, bike);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
