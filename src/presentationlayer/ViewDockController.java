package presentationlayer;

import entities.Bicycle;
import entities.Dock;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewDockController implements Initializable {
    private Dock dock;
    @FXML
    private ListView<String> bicycles;

    @FXML
    private Text dockInfo;

    @FXML
    private Button returnMainScreenButton;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        System.out.println("default initialize ViewDockScreen");
    }

    public void initData(Dock dock){
        System.out.println("initialize dockInfo and bikesInfo ");
        this.dock = dock;
        dockInfo.setText(dock.toString());
        ArrayList<Bicycle> bikes = dock.getBicycles();
        for(Bicycle bicycle: bikes)
            bicycles.getItems().add(bicycle.toString());
    }

    public void handleReturnMainScreenButton(ActionEvent event){
        System.out.println("User click return to Main Screen");
        Stage stage = (Stage) returnMainScreenButton.getScene().getWindow();
        stage.close();
    }
}
