package presentationlayer;
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
import java.util.ResourceBundle;

/**
 * Giao diện chính sau khi người dùng khởi động ứng dụng
 */
public class MainScreen implements Initializable {
    public static boolean reset = false;
//    ArrayList<Dock> docks;

    @FXML
    private ListView<String> docksView;



    public void handleRentButtonClick() {
        try {
            System.out.println("user click RentBikeButton");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RentBikeScreen.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.setTitle("RentBikeScreen");
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Khởi tạo");
    }
}
