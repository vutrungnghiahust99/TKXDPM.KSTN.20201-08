package presentationlayer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {
    @FXML private ListView docks = new ListView();

    public void logInButtonClicked(){
        System.out.println("SHEETA");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        docks.getItems().addAll(
            "Sheeta", "Pazu", "Le The Nam",
                "Sheeta", "Pazu", "Le The Nam",
                "Sheeta", "Pazu", "Le The Nam",
                "Sheeta", "Pazu", "Le The Nam","Sheeta", "Pazu", "Le The Nam","Sheeta", "Pazu", "Le The Nam",
                "Sheeta", "Pazu", "Le The Nam","Sheeta", "Pazu", "Le The Nam","Sheeta", "Pazu", "Le The Nam",
                "Sheeta", "Pazu", "Le The Nam","Sheeta", "Pazu", "Le The Nam","Sheeta", "Pazu", "Le The Nam",
                "Sheeta", "Pazu", "Le The Nam","Sheeta", "Pazu", "Le The Nam","Sheeta", "Pazu", "Le The Nam",
                "Sheeta", "Pazu", "Le The Nam");
    }
}
