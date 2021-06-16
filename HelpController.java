import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.stage.Window;
import javafx.scene.*;

public class HelpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    void onBackButtonAction(ActionEvent event) {
        Scene scene = ((Node) event.getSource()).getScene();
	Window window = scene.getWindow();
	window.hide();
    }

    @FXML
    void initialize() {
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'Help.fxml'.";

    }
}

