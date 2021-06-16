import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.PerspectiveCamera;

import javafx.scene.layout.AnchorPane;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import javafx.animation.Timeline;
import javafx.animation.KeyValue;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import java.io.IOException;

public class StartController {

    static ScoreManager sm;

    private static int gender = 0;//性別を判断するための変数
    private static int chara = 0;//キャラを判別するための変数

    //ここからボタンの定義
    @FXML
    private AnchorPane anchor;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button startButton;

    @FXML
    private Button charaSelectButton;

    @FXML
    private Button bestScoreButton;

    @FXML
    private ImageView charaView;

    @FXML
    private RadioButton saberButton;

    @FXML
    private ToggleGroup charaSelect;

    @FXML
    private RadioButton gunnerButton;

    @FXML
    private RadioButton fairyButton;

    @FXML
    private RadioButton maleButton;

    @FXML
    private ToggleGroup genderSelect;

    @FXML
    private RadioButton femaleButton;

    @FXML
    private Button okButton;

    @FXML
    private Button rturnButton;

    @FXML
    private Label firstLabel;

    @FXML
    private Label secondLabel;

    @FXML
    private Label thirdLabel;

    @FXML
    private Label fourthLabel;

    @FXML
    private Label fifthLabel;

    @FXML
    private Button helpButton;

    @FXML
    void onMaleButtonAction(ActionEvent event) {
	gender = 0;
	changeView();
    }

    @FXML
    void onFemaleButtonAction(ActionEvent event) {
	gender = 1;
	changeView();
    }

    @FXML
    void onSaberButtonAction(ActionEvent event) {
	chara = 0;
	changeView();
    }

    @FXML
    void onGunnerButtonAction(ActionEvent event) {
	chara = 1;
	changeView();
    }

    @FXML
    void onFairyButton(ActionEvent event) {
	chara = 2;
	changeView();
    }

    void changeView(){
	Image charaImage = new Image("jpg/" + chara + gender + "2.png");
	charaView.setImage(charaImage);
    }

    @FXML
    void onOkButtonAction(ActionEvent event) {
	Timeline t = new Timeline();
	KeyValue kv = new KeyValue(anchor.translateXProperty(), 0);
	KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
	t.getKeyFrames().add(kf);
	t.play();
    }

    @FXML
    void onReturnButtonAction(ActionEvent event){
	Timeline t = new Timeline();
	KeyValue kv = new KeyValue(anchor.translateXProperty(), 0);
	KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
	t.getKeyFrames().add(kf);
	t.play();
    }

    @FXML
    void onStartButtonAction(ActionEvent event) {
	BattleController.setGender(gender);
	BattleController.setChara(chara);
	Stage askStage = (Stage) startButton.getScene().getWindow();
	try{
	    Scene askScene = new Scene(FXMLLoader.load(getClass().getResource("Ask.fxml")));
	    askStage.setScene(askScene);
	}catch(Exception e){
	    e.printStackTrace();
	}
    }

    @FXML
    void onBestScoreButtonAction(ActionEvent event) {
	Timeline t = new Timeline();
	KeyValue kv = new KeyValue(anchor.translateXProperty(), 640);
	KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
	t.getKeyFrames().add(kf);
	t.play();
    }


    @FXML
    void onChararSelectButtonAction(ActionEvent event) {
	Timeline t = new Timeline();
	KeyValue kv = new KeyValue(anchor.translateXProperty(), -640);
	KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
	t.getKeyFrames().add(kf);
	t.play();
    }

    static int getChara(){
	return chara;
    }

    static int getGender(){
	return gender;
    }

    @FXML
    void onHelpButtonAction(ActionEvent event) {
	try{
	    showHelpWindow();
	}catch(Exception e){
	    e.printStackTrace();
	}
    }

    void showHelpWindow() throws IOException{
	FXMLLoader loader = new FXMLLoader(getClass().getResource("Help.fxml"));
	AnchorPane root = (AnchorPane) loader.load();
	Scene scene = new Scene(root);
	Stage stage = new Stage();
	stage.setScene(scene);
	stage.showAndWait();   
    }


    @FXML
    void initialize() {
	assert startButton != null : "fx:id=\"startButton\" was not injected: check your FXML file 'start.fxml'.";
	assert charaView != null : "fx:id=\"charaView\" was not injected: check your FXML file 'start.fxml'.";
	assert saberButton != null : "fx:id=\"saberButton\" was not injected: check your FXML file 'start.fxml'.";
	assert charaSelect != null : "fx:id=\"charaSelect\" was not injected: check your FXML file 'start.fxml'.";
	assert gunnerButton != null : "fx:id=\"gunner\" was not injected: check your FXML file 'start.fxml'.";
	assert fairyButton != null : "fx:id=\"magicianButton\" was not injected: check your FXML file 'start.fxml'.";
	assert maleButton != null : "fx:id=\"maleButton\" was not injected: check your FXML file 'start.fxml'.";
	assert genderSelect != null : "fx:id=\"genderSelect\" was not injected: check your FXML file 'start.fxml'.";
	assert femaleButton != null : "fx:id=\"femaleButton\" was not injected: check your FXML file 'start.fxml'.";
	assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'start.fxml'.";
	assert rturnButton != null : "fx:id=\"rturnButton\" was not injected: check your FXML file 'start.fxml'.";

	changeView();

	if(sm == null) {
	    sm = new ScoreManager(1000000000);
	    sm.reading();
	}

	Bgm bgm = new Bgm();
	Bgm.playStart();

	firstLabel.setText( "First      : " + String.valueOf(sm.getScoreList(0)));
	secondLabel.setText("Second : " + String.valueOf(sm.getScoreList(1)));
	thirdLabel.setText( "Third     : " + String.valueOf(sm.getScoreList(2)));
	fourthLabel.setText("Fourth   : " + String.valueOf(sm.getScoreList(3)));
	fifthLabel.setText( "Fifth      : " + String.valueOf(sm.getScoreList(4)));
    }
}
