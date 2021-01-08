package presentationlayer;

import entities.Bike;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Hiển thị thông tin chi tiết của xe sau khi người dùng ấn vào một xe trong danh sách xe trong bãi xe
 */
public class BikeDetailScreen implements Initializable {
    @FXML
    private TextField text1, text2, text3, text4, text5, text6, text8, text9, text10;

    @FXML
    private TextArea textArea;

    @FXML
    private Button OK;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    /**
     * khởi tạo giá trị các trường của giao diện theo thông tin của xe
     * @param bike: đối tượng bãi xe để khởi tạo giao diện chi tiết xe
     */
    public void init(Bike bike){
        text1.setText(String.valueOf(bike.getBikeCode()));
        text2.setText(bike.getType());
        text3.setText(String.valueOf(bike.getValue()));
        text4.setText(String.valueOf(bike.getNumPedals()));
        text5.setText(String.valueOf(bike.getNumSaddle()));
        text6.setText(String.valueOf(bike.getNumBicycleSeat()));
        textArea.setText(bike.getMoreDetails());
        text8.setText(bike.getDockID());
        text9.setText(bike.getLicensePlate());
        System.out.println("deposit: " + bike.calculateDeposit());
        text10.setText(String.valueOf(bike.calculateDeposit()));

    }

    /**
     * Đóng giao diện xem thông tin chi tiết xe khi người dùng ấn OK
     */
    public void processOKClick(){
        System.out.println("User click on OK button");
        Stage stage = (Stage) OK.getScene().getWindow();
        stage.close();
    }
}
