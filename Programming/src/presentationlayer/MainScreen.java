package presentationlayer;

import businesslogiclayer.controller.InitializeController;
import businesslogiclayer.controller.RentBikeController;
import entities.Dock;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import presentationlayer.box.NotificationBox;

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

    /**
     * khởi tạo giá trị mặc định cho giao diện
     * @param url: thông số mặc định của hàm initialize
     * @param rb: thông số mặc định của hàm initialize
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
     * Khi người dùng click vào một bãi xe trong danh sách bãi xe, hàm sẽ khởi tạo một giao diện thông tin bãi xe
     * và đợi cho đến khi giao diện thông tin bãi xe được tắt đi, tùy theo kết quả trả về có thể cập nhật lại danh
     * sách bãi xe từ cơ sở dữ liệu hoặc không
     */
    public void handleReturnButtonClick() {
        if (RentBikeController.rentalCode.equals("")){
            System.out.println("User is not renting a bike");
            NotificationBox.display("", "Không có xe đang thuê!");
//            Stage stage = (Stage)docksView.getScene().getWindow();
//            stage.close();
        }
        else{
            try {
                System.out.println("user click ReturnBikeButton");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ReturnBikeScreen.fxml"));
                Parent root = loader.load();

                ReturnBikeScreen returnBikeController = loader.getController();

                returnBikeController.initData(docks);

                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root));
                stage.setTitle("ReturnBikeScreen");
                stage.showAndWait();
                if(MainScreen.reset){
                    System.out.println("Reset and reload data from database");
                    docks = InitializeController.getDocks();
                    docksView.getItems().clear();
                    //show list of docks
                    for (Dock dock : docks) {
                        docksView.getItems().add(dock.getGeneralInfo());
                    }
                    MainScreen.reset = false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void handleRentButtonClick() {
        try {
            System.out.println("user click RentBikeButton");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RentBikeScreen.fxml"));
            Parent root = (Parent) loader.load();

//            RentBikeController rentBikeController = loader.getController();
//
//            rentBikeController.initData(docks);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.setTitle("RentBikeScreen");
            stage.showAndWait();

            if(MainScreen.reset){
                System.out.println("Reset and reload data from database");
                docks = InitializeController.getDocks();
                docksView.getItems().clear();
                //show list of docks
                for (Dock dock : docks) {
                    docksView.getItems().add(dock.getGeneralInfo());
                }
                MainScreen.reset = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleLockBikeButtonClick(){}
}
