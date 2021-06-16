import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class AskController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button voiceButton;

    @FXML
    private Button normalButton;

    @FXML
    private Button easyButton;

    @FXML
    private Button hardButton;

    @FXML
    void onEasyButtonAction(ActionEvent event) {
	BattleController.setMode(1);
	Bgm.stopStart();
	Stage battleStage = (Stage) easyButton.getScene().getWindow();
	try{
	    Scene battleScene = new Scene(FXMLLoader.load(getClass().getResource("Battle.fxml")));
	    battleStage.setScene(battleScene);
	}catch(Exception e){
	    e.printStackTrace();
	}
    }

    @FXML
    void onHardButtonAction(ActionEvent event) {
	BattleController.setMode(0);
	Bgm.stopStart();
	Stage battleStage = (Stage) hardButton.getScene().getWindow();
	try{
	    Scene battleScene = new Scene(FXMLLoader.load(getClass().getResource("Battle.fxml")));
	    battleStage.setScene(battleScene);
	}catch(Exception e){
	    e.printStackTrace();
	}
    }

    @FXML
    void onNormalButtonAction(ActionEvent event) {
	BattleController.setMode(2);
	Bgm.stopStart();
	Stage battleStage = (Stage) normalButton.getScene().getWindow();
	try{
	    Scene battleScene = new Scene(FXMLLoader.load(getClass().getResource("Battle.fxml")));
	    battleStage.setScene(battleScene);
	}catch(Exception e){
	    e.printStackTrace();
	}
    }

    @FXML
    void onVoiceButtonAction(ActionEvent event) {
	Bgm.stopStart();
	BattleController.setMode(3);
	Stage voiceStage = (Stage) voiceButton.getScene().getWindow();
	try{
	    Scene voiceScene = new Scene(FXMLLoader.load(getClass().getResource("Voice.fxml")));
	    voiceStage.setScene(voiceScene);
	}catch(Exception e){
	    e.printStackTrace();
	}
    }


    @FXML
    void initialize() {
        assert voiceButton != null : "fx:id=\"voiceButton\" was not injected: check your FXML file 'Ask.fxml'.";
        assert normalButton != null : "fx:id=\"normalButton\" was not injected: check your FXML file 'Ask.fxml'.";
        assert easyButton != null : "fx:id=\"easyButton\" was not injected: check your FXML file 'Ask.fxml'.";
        assert hardButton != null : "fx:id=\"hardButton\" was not injected: check your FXML file 'Ask.fxml'.";

    }
}
