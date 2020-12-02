package presentationlayer;

import entities.Bike;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import presentationlayer.box.NotificationBox;
import javafx.scene.control.TextField;

public class RentBikeScreenController {
    public Bike bike;
    @FXML
    private TextField barcode;

    @FXML
    void handleButtonThueXe(ActionEvent event){
        String message = barcode.getText();
        int bar = Integer.parseInt(message);
        System.out.println(bar);
        if (!message.isEmpty()){
            bike = new Bike(bar, "Xe điện");
            display("Thuê xe", "Xác nhận thuê xe?", bike);
            NotificationBox.display("NotificationBox", "Thuê xe thành công!");
        }
        else{
            NotificationBox.display("NotificationBox", "Nhập barcode của xe.");
        }

    }

    public static void display(String title, String message, Bike bike){
        String bikeInfo = bike.getDetailInfo();
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        javafx.scene.control.Label label = new javafx.scene.control.Label();
        label.setText(message);

        javafx.scene.control.Label label2 = new javafx.scene.control.Label();
        label.setText(bikeInfo);
        javafx.scene.control.Button button = new Button("Xác nhận");
        button.setOnAction(e -> {
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label2, label, button);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
//        return answer;
    }
}
