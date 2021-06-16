import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import javax.sound.sampled.*;
import java.io.*;



public class VoiceController {

    RecordTest recorder;
    Thread stopper;
    BufferedReader in;
    private int imageNumber = 0;
    private int voiceNumber = 0;
    private int pose = 0;
    private String str = "waiting...";

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView charaView;

    @FXML
    private Label explainLabel;

    @FXML
    private ImageView doingView;

    @FXML
    private Label toDoLabel;

    @FXML
    private Button recordButton;

    @FXML
    private Button checkButton;

    @FXML
    private Button nextButton;

    @FXML
    void onCheckButtonAction(ActionEvent event) {
	toDoLabel.setText("please recording");
	Bgm Bgm = new Bgm(voiceNumber,new File("voice" + voiceNumber + ".wav"));
	if(pose >= 3){
	    imageNumber = 1;
	    changeView();
	    //再生
	    switch(voiceNumber){
		case 0:
		    Bgm.playVoice0();
		    break;
		case 1:
		    Bgm.playVoice1();
		    break;
		case 2:
		    Bgm.playVoice2();
		    break;
	    }
	    toDoLabel.setText("finished playing");
	    imageNumber = 0;
	    changeView();
	    pose = 4;
	}else if(pose == 4){
	    Bgm.stopVoice0();
	    Bgm.stopVoice1();
	    Bgm.stopVoice2();
	}
    }

    @FXML
    void onNextButtonAction(ActionEvent event) {
	if(pose == 4){
	    voiceNumber++;
	    if(voiceNumber == 3){
		Stage battleStage = (Stage) nextButton.getScene().getWindow();
		try{
		    Scene battleScene = new Scene(FXMLLoader.load(getClass().getResource("Battle.fxml")));
		    battleStage.setScene(battleScene);
		}catch(Exception e){
		    e.printStackTrace();
		}
	    }
	    pose = 0;
	    explainLabel.setText("voice number:" + voiceNumber);
	    toDoLabel.setVisible(false);
	    try{
		recorder = new RecordTest(new File("voice" + voiceNumber + ".wav"));
		stopper = new Thread(recorder);
		in = new BufferedReader(new InputStreamReader(System.in));
	    }catch(Exception e){
	    }
	}else if (pose == 3){
	    toDoLabel.setText("please check your voice");
	}else{
	    toDoLabel.setText("please recording");
	}
	imageNumber = 0;
	changeView();
    }


    @FXML
    void onRecordButtonAction(ActionEvent event) throws Exception{

	if(pose < 3){
	    switch(pose){
		case 0:
		    toDoLabel.setVisible(true);
		    toDoLabel.setText("click same button to start recording >>");
		    imageNumber  = 3;
		    break;
		case 1:
		    stopper.start();
		    toDoLabel.setText("click same button to stop recording >>");
		    imageNumber = 2;
		    break;
		case 2:
		    recorder.stopRecording();
		    toDoLabel.setText("finished");
		    imageNumber = 0;
		    break;
	    }
	    pose++;
	}else if(pose == 4){
	    toDoLabel.setText("Click next button");

	}else{
	    toDoLabel.setText("please check your voice");
	}
	changeView();
    }

    void changeView(){
	Image icon = new Image("icon/" + imageNumber + ".png");
	doingView.setImage(icon);
	int gender = BattleController.getGender();
	int yourChara = BattleController.getChara();
	Image yourImage = new Image("jpg/" + yourChara + gender + voiceNumber + ".png");
	charaView.setImage(yourImage);
	switch(voiceNumber){
	    case 0:
		explainLabel.setText("when your action is effective...");
		break;
	    case 1:
		explainLabel.setText("When your action isn't effective...");
		break;
	    case 2:
		explainLabel.setText("when you cleared the stage...");
	}
    }


    @FXML
    void initialize() {
	assert charaView != null : "fx:id=\"charaView\" was not injected: check your FXML file 'Voice.fxml'.";
	assert explainLabel != null : "fx:id=\"explainLabel\" was not injected: check your FXML file 'Voice.fxml'.";
	assert doingView != null : "fx:id=\"doingView\" was not injected: check your FXML file 'Voice.fxml'.";
	assert toDoLabel != null : "fx:id=\"toDoLabel\" was not injected: check your FXML file 'Voice.fxml'.";
	assert recordButton != null : "fx:id=\"recordButton\" was not injected: check your FXML file 'Voice.fxml'.";
	assert checkButton != null : "fx:id=\"checkButton\" was not injected: check your FXML file 'Voice.fxml'.";
	assert nextButton != null : "fx:id=\"nextButton\" was not injected: check your FXML file 'Voice.fxml'.";

	changeView();
	try{
	    recorder = new RecordTest(new File("voice" + voiceNumber + ".wav"));
	    stopper = new Thread(recorder);
	    in = new BufferedReader(new InputStreamReader(System.in));
	}catch(Exception e){
	}
    }
}
