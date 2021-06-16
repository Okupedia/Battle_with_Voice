import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.layout.AnchorPane;
import javafx.animation.Timeline;
import javafx.animation.KeyValue;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;

public class ClearController {

    static int score = 0;
    static int chara = 0;
    static int gender = 0;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchor;

    @FXML
    private ImageView charaView;

    @FXML
    private Button startButton;

    @FXML
    private Label yourLabel;

    @FXML
    private Label bestLabel;

    @FXML
    void onStartButtonAction(ActionEvent event) {
	Bgm.stopClear();
	Stage startStage = (Stage) startButton.getScene().getWindow();
	try{
	    Scene startScene = new Scene(FXMLLoader.load(getClass().getResource("Start.fxml")));
	    startStage.setScene(startScene);
	    startStage.show();
	}catch(Exception e){
	    e.printStackTrace();
	}
    }

    @FXML
    void initialize() {
	assert anchor != null : "fx:id=\"anchor\" was not injected: check your FXML file 'Clear.fxml'.";
	assert charaView != null : "fx:id=\"charaView\" was not injected: check your FXML file 'Clear.fxml'.";
	assert yourLabel != null : "fx:id=\"yourLabel\" was not injected: check your FXML file 'Clear.fxml'.";
	assert bestLabel != null : "fx:id=\"bestLabel\" was not injected: check your FXML file 'Clear.fxml'.";

	Bgm.playClear();
	Image charaImage = new Image("jpg/" + BattleController.getChara() + BattleController.getGender() + "2.png");
	charaView.setImage(charaImage);

	yourLabel.setText("Score:" + BattleController.getScore());
	bestLabel.setText("Best Score:" + ScoreManager.getScoreList(0));

	Timeline t = new Timeline();
	KeyValue kv = new KeyValue(anchor.translateYProperty(), -480);
	KeyFrame kf = new KeyFrame(Duration.seconds(5), kv);
	t.getKeyFrames().add(kf);
	t.play();
    }
}
