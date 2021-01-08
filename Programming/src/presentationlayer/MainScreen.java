package presentationlayer;
import businesslogiclayer.controller.InitializeController;
import entities.Dock;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Giao diện chính sau khi người dùng khởi động ứng dụng
 */
public class MainScreen implements Initializable {
    public static boolean reset = false;
    ArrayList<Dock> docks;

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
                handleDoubleClickOnDockList();
            }
        });
    }

    private void handleDoubleClickOnDockList() {
        System.out.println("User double on a dock");
        String dockInfo = docksView.getSelectionModel().getSelectedItem();
        Dock dock = getDockFromString(dockInfo);

        showViewDockScreen(dock);
    }

    /**
     * Khi người dùng click vào một dòng trong danh sách bãi xe, hàm sẽ tiến hành tìm kiếm đối tượng bãi xe tương
     * ứng bằng cách so sánh string từ giao diện gửi về và string của bãi xe trong danh sách bãi xe docks
     * @param dockInfo: string của bãi xe gửi về từ giao diện
     * @return: bãi xe tương ứng hoặc null trong trường hợp không tồn tại
     */
    private Dock getDockFromString(String dockInfo) {

        for (Dock dock : docks) {
            String s = dock.getGeneralInfo();
            if (dockInfo.equals(s)) {
                return dock;
            }
        }
        return null;
    }

    /**
     * Hiển thị giao diện xem thông tin bãi xe
     * @param dock: đối tượng bãi xe chứa thông tin cần hiển thị
     */
    public void showViewDockScreen(Dock dock) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DockScreen.fxml"));
            Parent root = loader.load();

            DockScreen viewDockController = loader.getController();

            viewDockController.initData(dock);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.setTitle("ViewDockScreen");
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
