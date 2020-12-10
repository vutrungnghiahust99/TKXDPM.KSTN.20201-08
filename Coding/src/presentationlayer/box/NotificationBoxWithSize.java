import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

class NotificationBoxWithSize {
    public static void display(String title, String message, int width){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label label = new Label();
        label.setText(message);

        Button button = new Button("OK");
        button.setOnAction(e -> {
            window.close();
        });

        Region region = new Region();
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, button, region);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
//        return answer;
    }
}