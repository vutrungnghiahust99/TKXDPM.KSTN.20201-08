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
import javafx.util.Pair;
import presentationlayer.box.ConfirmBox;
import presentationlayer.box.NotificationBox;
import presentationlayer.box.NotificationErrorCode;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Quản lý giao diện trả xe của người dùng
 */
public class ReturnBikeScreenController implements Initializable {
    public static String newDockID = null;
    private ArrayList<Dock> docks;

    @FXML
    private ListView<String> docksView;

    /**
     * Khởi tạo mặc định giao diện trả xe gồm danh sách các bãi xe đông thời lắng nghe xem người dùng có double click
     * vào một dòng trong dánh sách, nếu có double click thì hiển thị thông báo yêu cầu xác nhận và thực hiện giao dịch
     * sau khi người dùng xác nhận, cuối cùng lưu các kết quả vào cơ sở dữ liệu và về màn hình chính
     * @param url
     * @param rb
     */
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
                    Pair<String, RentBikeTransaction> s = ReturnBikeController.processReturnBike();
                    String respondCode = s.getKey();
                    RentBikeTransaction rentBikeTransaction = s.getValue();

                    NotificationErrorCode.displayNotificationErrorCode(respondCode, "refund");

                    if (respondCode.equals("00")){
                        RentBikeController.rentalCode = "";
                        //set rent = false, tức là set trạng thái người dùng thành chưa thuê xe
                        RentBikeScreenController.rent = false;
                        MainScreenController.reset = true;
                        showRentBikeTransactionInfo(rentBikeTransaction);
                        Stage stage = (Stage)docksView.getScene().getWindow();
                        stage.close();
                    }
                }
            }
        });
    }

    /**
     * Khởi tạo giao diện trả xe với danh sách các bãi xe
     * @param docks: danh sách các bãi xe
     */
    public void initData(ArrayList<Dock> docks){
        System.out.println("initialize dockInfo and bikesInfo ");
        this.docks = docks;
        for(Dock dock : docks)
            docksView.getItems().add(dock.getGeneralInfo());
    }

    /**
     * Lấy đối tượng bãi xe tương ứng trong dánh các bãi xe từ phản hồi double clicks của người dùng từ giao diện
     * vào một dòng trong danh sách
     * @param dockInfo: string phản hồi từ giao diện
     * @return: bãi xe tương ứng hoặc null nếu không tìm được
     */
    private Dock getDockFromString(String dockInfo){

        for(Dock dock: docks){
            String s = dock.getGeneralInfo();
            if(dockInfo.equals(s)){
                return dock;
            }
        }
        return null;
    }

    /**
     * Hiển thị thông tin chi tiết hóa đơn thuê xe
     * @param rentBikeTransaction: hóa đơn thuê xe
     */
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
