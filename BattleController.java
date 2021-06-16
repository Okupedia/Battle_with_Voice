import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.*;
import java.util.Random;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class BattleController {
    Random random = new Random();
    static double start = 0;
    static double end = 0;
    static int time = 0;
    static int score = 0;
    public static int yourMaxHp = 50;
    public static int enemyMaxHp = 30;
    public static int yourCurrentHp = 0;
    public int enemyCurrentHp = enemyMaxHp;
    public int pose = 0;
    private static int stage = 2;
    private int yourAction = 0;
    private int enemyAction = 0;
    static int enemyChara = 0;
    static int yourChara = 0;
    static int gender = 0;
    static int yourFeeling = 3;
    static int enemyFeeling = 3;
    private static int mode = 0;
    private String enemyName = "name";

    public static void setChara(int c){
	yourChara = c;
    }

   public static void setChara(){
	yourChara = enemyChara;
    }

    public static int getChara(){
      return yourChara;
    }
    public static void setGender(int g){
	gender = g;
    }

    public static int getGender(){
      return gender;
    }

    public static void setTime(double t){
	time = time + 10;
    }

    public static void setHp(int hp){
	yourCurrentHp = yourCurrentHp + hp;
	if(yourCurrentHp > yourMaxHp){
	    yourCurrentHp = yourMaxHp;
	}else if(yourCurrentHp <= 0){
	    yourCurrentHp = 0;
	}
    }

    public static int getStage(){
	return stage;
    }

    public static int getScore(){
	return score;
    }

    public static void setMode(int n){
	mode = n;
	if(mode == 0){//hard
	    yourMaxHp = 30;
	}else if(mode == 1){//easy
	    yourMaxHp = 30;
	    enemyMaxHp = 10;
	}
    }

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private ImageView backgroundView;
    @FXML
    private ProgressBar yourHP;
    @FXML
    private ProgressBar enemyHP;
    @FXML
    private Label enemyChaaraLabel;
    @FXML
    private ImageView yourView;
    @FXML
    private ImageView enemyView;
    @FXML
    private Label yourHp;
    @FXML
    private Label enemyHp;
    @FXML
    private Label timeLabel;
    @FXML
    private RadioButton attackButton;
    @FXML
    private ToggleGroup Action;
    @FXML
    private RadioButton defenseButton;
    @FXML
    private RadioButton cureButton;
    @FXML
    private Button okButton;
    @FXML
    private ImageView yourActionView;
    @FXML
    private ImageView enemyActionView;
    @FXML
    private ImageView judgeView;
    @FXML
    void onAttackButton(ActionEvent event) {
	yourAction = 0;
    }
    @FXML
    void onCureButtonAction(ActionEvent event) {
	yourAction = 1;
    }
    @FXML
    void onDefenseButtonAction(ActionEvent event) {
	yourAction = 2;
    }
    @FXML
    void onOkButtonAction(ActionEvent event) {
	if(yourCurrentHp == 0){
		Bgm.stopBgm2();
		Bgm.stopBgm3();
		Bgm.stopBgm4();
	    Stage startStage = (Stage) okButton.getScene().getWindow();
	    try{
		Scene startScene = new Scene(FXMLLoader.load(getClass().getResource("Start.fxml")));
		startStage.setScene(startScene);
		startStage.show();
	    }catch(Exception e){
		e.printStackTrace();
	    }
		if(mode == 3){
	    Bgm.playVoice1();
		}
	}else if(pose == 2){//new stage
	switch(stage){
	    case 2:
		Bgm.stopBgm2();
		Bgm.playBgm3();
		break;
	    case 3:
		Bgm.stopBgm3();
		Bgm.playBgm4();
		break;
	    case 4:
		Bgm.stopBgm4();
		//game finish
		end = Time.end();
		score = (int)(end - start);
		score = score + time;
		ScoreManager sm = new ScoreManager(score);
		Stage clearStage = (Stage) okButton.getScene().getWindow();
		try{
		    Scene clearScene = new Scene(FXMLLoader.load(getClass().getResource("Clear.fxml")));
		    clearStage.setScene(clearScene);
		    clearStage.show();
		}catch(Exception e){
		    e.printStackTrace();
		}
		return;
	}
	    newStage();
	    judgeView.setVisible(false);
		pose = 1;
	}else if(enemyCurrentHp == 0){//item get
	if(mode == 3){

	    Bgm.playVoice2();
	}
	//アイテム獲得処理
	try{
	    showItemWindow();
	}catch(Exception e){
	    e.printStackTrace();
	}
	pose = 2;
	}
	if(pose == 1){//reset
	    pose = 0;
	    yourFeeling = 3;
	    enemyFeeling = 3;
	}else if(pose == 0){ //action
	    pose = 1;
	    enemyAction = random.nextInt(3);
	    if(mode == 1){
		enemyAction = 1;
	    }else if(mode == 0){
			enemyAction = 0;
		}
	    actionJudge(yourAction,enemyAction);
	    if(yourFeeling == 0 && mode == 3){
		Bgm.playVoice0();
	    }else if(yourFeeling == 1 && mode == 3){
		Bgm.playVoice1();
	    }
	}
	changeView();
    }
    void changeView(){
	yourHp.setText(yourCurrentHp + "/" + yourMaxHp);
	enemyHp.setText(enemyCurrentHp + "/" + enemyMaxHp);
	Image yourImage = new Image("jpg/" + yourChara + gender + yourFeeling + ".png");
	yourView.setImage(yourImage);
double yourBar = (double)yourCurrentHp/yourMaxHp;
yourHP.setProgress(yourBar);
double enemyBar = (double)enemyCurrentHp/enemyMaxHp;
enemyHP.setProgress(enemyBar);
	Image enemyImage = new Image("jpg/e" + enemyChara + enemyFeeling + ".png");
	enemyView.setImage(enemyImage);
	yourActionView.setVisible(true);
	enemyActionView.setVisible(true);
	Image yourActionImage = new Image("jpg/action" + yourAction + ".png");
	yourActionView.setImage(yourActionImage);
	Image enemyActionImage = new Image("jpg/action" + enemyAction + ".png");
	enemyActionView.setImage(enemyActionImage);
	switch(enemyChara){
	    case 0:
		enemyName = "Saber";
		break;
	    case 1:
		enemyName = "Gunner";
		break;
	    case 2:
		enemyName = "fairy";
		break;
	}
	enemyChaaraLabel.setText(enemyName);
	if(pose == 0){
	    yourActionView.setVisible(false);
	    enemyActionView.setVisible(false);
	}
    }
    void actionJudge(int you,int enemy){
	//ここにactionに対する判定を入れる
	if(yourChara == 0  && enemyChara == 0){
	    if(you == 1){
		if(you > enemy){

		    yourCurrentHp = yourCurrentHp - 8;
			enemyCurrentHp = enemyCurrentHp;
		    yourFeeling = 1;
		    enemyFeeling = 0;
		}else if(you < enemy){
			enemyCurrentHp = enemyCurrentHp;
		    yourCurrentHp = yourCurrentHp + 2;
		    yourFeeling = 0;
		    enemyFeeling = 1;
		}else if(you == enemy){
		    yourCurrentHp = yourCurrentHp + 2;
		    enemyCurrentHp = enemyCurrentHp + 2;
		    yourFeeling = 0;
		    enemyFeeling = 0;
		}
	    }else if(you == 2 ){
		if(enemy == 1){
			yourCurrentHp = yourCurrentHp;
		    enemyCurrentHp = enemyCurrentHp + 2;
		    yourFeeling = 1;
		    enemyFeeling = 0;
		}else if(enemy == 0){
			yourCurrentHp = yourCurrentHp;
			enemyCurrentHp = enemyCurrentHp;
		    yourFeeling = 0;
		    enemyFeeling = 1;
		}else if(you == enemy){
			yourCurrentHp = yourCurrentHp;
			enemyCurrentHp = enemyCurrentHp;
		    yourFeeling = 0;
		    enemyFeeling = 0;
		}
	    }else if(you == 0){
		if(you == enemy){
		    yourCurrentHp = yourCurrentHp - 4;
		    enemyCurrentHp = enemyCurrentHp - 4;
		    yourFeeling = 0;
		    enemyFeeling = 0;
		}else if(enemy == 1){
			enemyCurrentHp = enemyCurrentHp -8;
			yourCurrentHp = yourCurrentHp;

		    yourFeeling = 0;
		    enemyFeeling = 1;
		}else if(enemy == 2){
			yourCurrentHp = yourCurrentHp;
			enemyCurrentHp = enemyCurrentHp;
		    yourFeeling = 1;
		    enemyFeeling = 0;
		}
	    }
	}
	if(yourChara == 0 && enemyChara == 1){
	    if(you == 1){
		if(you > enemy){
		    yourCurrentHp = yourCurrentHp - 2;
			enemyCurrentHp = enemyCurrentHp;
		    yourFeeling = 1;
		    enemyFeeling = 0;
		}else if(you < enemy){
		    yourCurrentHp = yourCurrentHp + 2;
			enemyCurrentHp = enemyCurrentHp;
		    yourFeeling = 0;
		    enemyFeeling = 1;
		}else if(you == enemy){
		    yourCurrentHp = yourCurrentHp + 2;
		    enemyCurrentHp = enemyCurrentHp + 2;
		    yourFeeling = 0;
		    enemyFeeling = 0;
		}
	    }else if(you == 2){
		if(you == enemy){
		    yourCurrentHp = yourCurrentHp - 2;
			enemyCurrentHp = enemyCurrentHp;
		    yourFeeling = 0;
		    enemyFeeling = 0;
		}else if(enemy == 0){
			yourCurrentHp = yourCurrentHp;
			enemyCurrentHp = enemyCurrentHp;
		    yourFeeling = 0;
		    enemyFeeling =1;
		}else if(enemy == 1){
			yourCurrentHp = yourCurrentHp;
		    enemyCurrentHp = enemyCurrentHp + 2;
		    yourFeeling = 1;
		    enemyFeeling = 0;
		}
	    }else if(you == 0){
		if(you == enemy){
		    yourCurrentHp = yourCurrentHp - 2;
		    enemyCurrentHp = enemyCurrentHp - 4;
		    yourFeeling = 0;
		    enemyFeeling = 0;
		}else if(enemy == 1){
			yourCurrentHp = yourCurrentHp;
		    enemyCurrentHp = enemyCurrentHp - 8;
		    yourFeeling = 0;
		    enemyFeeling = 1;
		}else if(enemy == 2){
		    yourCurrentHp = yourCurrentHp - 2;
		    yourFeeling = 1;
		    enemyFeeling = 0;
		}
	    }
	}
	if(yourChara == 0 && enemyChara == 2){
	    if(you == 1){
		if(you > enemy){
		    yourCurrentHp = yourCurrentHp - 4;
		    yourFeeling = 1;
		    enemyFeeling = 0;
		}else if(you < enemy){
		    yourCurrentHp = yourCurrentHp + 2;
		    yourFeeling = 0;
		    enemyFeeling = 1;
		}else if(you == enemy){
		    yourCurrentHp = yourCurrentHp + 2;
		    enemyCurrentHp = enemyCurrentHp + 4;
		    yourFeeling = 0;
		    enemyFeeling = 0;
		}
	    }else if(you == 2){
		if(you == enemy){
		    yourFeeling = 0;
		    enemyFeeling = 0;
		}else if(enemy == 0){
		    yourFeeling = 0;
		    enemyFeeling = 1;
		}else if(enemy == 1){
		    enemyCurrentHp = enemyCurrentHp + 4;
		    yourFeeling = 1;
		    enemyFeeling =0;
		}
	    }else if(you == 0){
		if(you == enemy){
		    yourCurrentHp = yourCurrentHp - 2;
		    enemyCurrentHp = enemyCurrentHp - 4;
		    yourFeeling = 0;
		    enemyFeeling = 0;
		}else if(enemy == 1){
		    enemyCurrentHp = enemyCurrentHp - 8;
		    yourFeeling = 0;
		    enemyFeeling = 1;
		}else if(enemy == 2){
		    yourFeeling = 1;
		    enemyFeeling =0;
		}
	    }
	}
	if(yourChara == 1 && enemyChara == 0){
	    if(you == 1){
		if(you > enemy){
		    yourCurrentHp = yourCurrentHp - 8;
		    yourFeeling = 1;
		    enemyFeeling = 0;
		}else if(you < enemy){
		    yourCurrentHp = yourCurrentHp + 2;
		    yourFeeling = 0;
		    enemyFeeling = 1;
		}else if(you == enemy){
		    yourCurrentHp = yourCurrentHp + 2;
		    enemyCurrentHp = enemyCurrentHp + 2;
		    yourFeeling = 0;
		    enemyFeeling = 0;
		}
	    }else if(you == 2){
		if(you == enemy){
		    enemyCurrentHp = enemyCurrentHp - 2;
		    yourFeeling = 0;
		    enemyFeeling = 0;
		}else if(enemy == 0){
		    enemyCurrentHp = enemyCurrentHp - 2;
		    yourFeeling = 0;
		    enemyFeeling = 1;
		}else if(enemy == 1){
		    enemyCurrentHp = enemyCurrentHp + 2;
		    yourFeeling = 1;
		    enemyFeeling = 0;
		}
	    }else if(you == 0){
		if(you == enemy){
		    yourCurrentHp = yourCurrentHp - 4;
		    enemyCurrentHp = enemyCurrentHp - 2;
		    yourFeeling = 0;
		    enemyFeeling = 0;
		}else if(enemy == 1){
		    enemyCurrentHp = enemyCurrentHp - 4;
		    yourFeeling = 0;
		    enemyFeeling = 1;
		}else if(enemy == 2){
		    yourFeeling = 1;
		    enemyFeeling = 0;
		}
	    }
	}
	if(yourChara == 1 && enemyChara == 1){
	    if(you == 1){
		if(you > enemy){
		    yourCurrentHp = yourCurrentHp - 4;
		    yourFeeling = 1;
		    enemyFeeling = 0;
		}else if(you < enemy){
		    yourCurrentHp = yourCurrentHp + 2;
		    yourFeeling = 0;
		    enemyFeeling = 1;
		}else if(you == enemy){
		    yourCurrentHp = yourCurrentHp + 2;
		    enemyCurrentHp = enemyCurrentHp + 2;
		    yourFeeling = 0;
		    enemyFeeling = 0;
		}
	    }else if(you == 2){
		if(you == enemy){
		    yourCurrentHp = yourCurrentHp - 2;
		    enemyCurrentHp = enemyCurrentHp - 2;
		    yourFeeling = 0;
		    enemyFeeling = 0;
		}else if(enemy == 0){
		    enemyCurrentHp = enemyCurrentHp - 2;
		    yourFeeling = 0;
		    enemyFeeling = 1;
		}else if(enemy == 1){
		    enemyCurrentHp = enemyCurrentHp + 2;
		    yourFeeling = 1;
		    enemyFeeling = 0;
		}
	    }else if(you == 0){
		if(you == enemy){
		    yourCurrentHp = yourCurrentHp - 2;
		    enemyCurrentHp = enemyCurrentHp - 2;
		    yourFeeling = 0;
		    enemyFeeling = 0;
		}else if(enemy == 1){
		    enemyCurrentHp = enemyCurrentHp - 4;
		    yourFeeling = 0;
		    enemyFeeling = 1;
		}else if(enemy == 2){
		    yourCurrentHp = yourCurrentHp - 2;
		    yourFeeling = 1;
		    enemyFeeling = 0;
		}
	    }
	}
	if(yourChara == 1 && enemyChara == 2){
	    if(you == 1){
		if(you > enemy){
		    yourCurrentHp = yourCurrentHp - 4;
		    yourFeeling = 1;
		    enemyFeeling = 0;
		}else if(you < enemy){
		    yourCurrentHp = yourCurrentHp + 2;
		    yourFeeling = 0;
		    enemyFeeling = 1;
		}else if(you == enemy){
		    yourCurrentHp = yourCurrentHp + 2;
		    enemyCurrentHp = enemyCurrentHp + 4;
		    yourFeeling = 0;
		    enemyFeeling = 0;
		}
	    }else if(you == 2){
		if(you == enemy){
		    enemyCurrentHp = enemyCurrentHp - 2;
		    yourFeeling = 0;
		    enemyFeeling = 0;
		}else if(enemy == 0){
		    enemyCurrentHp = enemyCurrentHp - 2;
		    yourFeeling = 0;
		    enemyFeeling = 1;
		}else if(enemy == 1){
		    enemyCurrentHp = enemyCurrentHp + 4;
		    yourFeeling = 1;
		    enemyFeeling = 0;
		}
	    }else if(you == 0){
		if(you == enemy){
		    yourCurrentHp = yourCurrentHp - 2;
		    enemyCurrentHp = enemyCurrentHp - 2;
		    yourFeeling = 0;
		    enemyFeeling = 0;
		}else if(enemy == 1){
		    enemyCurrentHp = enemyCurrentHp - 4;
		    yourFeeling = 0;
		    enemyFeeling = 1;
		}else if(enemy == 2){
		    yourFeeling = 1;
		    enemyFeeling = 0;
		}
	    }
	}
	if(yourChara == 2 && enemyChara == 0){
	    if(you == 1){
		if(you > enemy){
		    yourCurrentHp = yourCurrentHp - 8;
		    yourFeeling = 1;
		    enemyFeeling = 0;
		}else if(you < enemy){
		    yourCurrentHp = yourCurrentHp + 4;
		    yourFeeling = 0;
		    enemyFeeling = 1;
		}else if(you == enemy){
		    yourCurrentHp = yourCurrentHp + 4;
		    enemyCurrentHp = enemyCurrentHp + 2;
		    yourFeeling = 0;
		    enemyFeeling = 0;
		}
	    }else if(you == 2){
		if(you == enemy){
		    yourFeeling = 0;
		    enemyFeeling = 0;
		}else if(enemy == 0){
		    yourFeeling =0;
		    enemyFeeling = 1;
		}else if(enemy == 1){
		    enemyCurrentHp = enemyCurrentHp + 2;
		    yourFeeling = 1;
		    enemyFeeling = 0;
		}
	    }else if(you == 0){
		if(you == enemy){
		    yourCurrentHp = yourCurrentHp - 4;
		    enemyCurrentHp = enemyCurrentHp - 2;
		    yourFeeling = 0;
		    enemyFeeling = 0;
		}else if(enemy == 1){
		    enemyCurrentHp = enemyCurrentHp - 4;
		    yourFeeling = 0;
		    enemyFeeling = 1;
		}else if(enemy == 2){
		    yourFeeling = 1;
		    enemyFeeling = 0;
		}
	    }
	}
	if(yourChara == 2 && enemyChara == 1){
	    if(you == 1){
		if(you > enemy){
		    yourCurrentHp = yourCurrentHp - 4;
		    yourFeeling = 1;
		    enemyFeeling = 0;
		}else if(you < enemy){
		    yourCurrentHp = yourCurrentHp + 4;
		    yourFeeling = 0;
		    enemyFeeling = 1;
		}else if(you == enemy){
		    yourCurrentHp = yourCurrentHp + 4;
		    enemyCurrentHp = enemyCurrentHp + 2;
		    yourFeeling = 0;
		    enemyFeeling = 0;
		}
	    }else if(you == 2){
		if(you == enemy){
		    yourCurrentHp = yourCurrentHp - 2;
		    yourFeeling = 0;
		    enemyFeeling = 0;
		}else if(enemy == 0){
		    yourFeeling = 0;
		    enemyFeeling = 1;
		}else if(enemy == 1){
		    enemyCurrentHp = enemyCurrentHp + 2;
		    yourFeeling = 1;
		    enemyFeeling = 0;
		}
	    }else if(you == 0){
		if(you == enemy){
		    yourCurrentHp = yourCurrentHp - 2;
		    enemyCurrentHp = enemyCurrentHp - 2;
		    yourFeeling = 0;
		    enemyFeeling = 0;
		}else if(enemy == 1){
		    enemyCurrentHp = enemyCurrentHp - 4;
		    yourFeeling = 0;
		    enemyFeeling = 1;
		}else if(enemy == 2){
		    yourCurrentHp = yourCurrentHp - 2;
		    yourFeeling = 1;
		    enemyFeeling = 0;
		}
	    }
	}
	if(yourChara == 2 && enemyChara == 2){
	    if(you == 1){
		if(you > enemy){
		    yourCurrentHp = yourCurrentHp - 4;
		    yourFeeling = 1;
		    enemyFeeling = 0;
		}else if(you < enemy){
		    yourCurrentHp = yourCurrentHp + 4;
		    yourFeeling = 0;
		    enemyFeeling = 1;
		}else if(you == enemy){
		    yourCurrentHp = yourCurrentHp + 4;
		    enemyCurrentHp = enemyCurrentHp + 4;
		    yourFeeling = 0;
		    enemyFeeling = 0;
		}
	    }else if(you == 2){
		if(you == enemy){
		    yourFeeling = 0;
		    enemyFeeling = 0;
		}else if(enemy == 0){
		    yourFeeling = 0;
		    enemyFeeling = 1;
		}else if(enemy == 1){
		    enemyCurrentHp = enemyCurrentHp + 4;
		    yourFeeling = 1;
		    enemyFeeling = 0;
		}
	    }else if(you == 0){
		if(you == enemy){
		    yourCurrentHp = yourCurrentHp - 2;
		    enemyCurrentHp = enemyCurrentHp - 2;
		    yourFeeling = 0;
		    enemyFeeling = 0;
		}else if(enemy == 1){
		    enemyCurrentHp = enemyCurrentHp - 4;
		    yourFeeling = 0;
		    enemyFeeling = 1;
		}else if(enemy == 2){
		    yourFeeling = 1;
		    enemyFeeling = 0;
		}
	    }
	}
	//hpの判定をします
	hpJudge(yourCurrentHp,enemyCurrentHp);
    }
   void hpJudge(int yourHP,int enemyHP){

	if(yourHP <= 0){
	    yourFeeling = 1;
	    enemyFeeling = 2;
	    yourHP = 0;
	    judgeView.setVisible(true);
	    Image judgeImage = new Image("jpg/loseView.png");
	    judgeView.setImage(judgeImage);
	}else if(enemyHP <= 0){
	    enemyHP = 0;
	    yourFeeling = 2;
	    enemyFeeling = 1;
	    judgeView.setVisible(true);
	    Image judgeImage = new Image("jpg/winView.png");
	    judgeView.setImage(judgeImage);
	}
	if(yourHP > yourMaxHp){
	    yourHP = yourMaxHp;
	}
	if(enemyHP > enemyMaxHp){
	    enemyHP = enemyMaxHp;		
	}
	yourCurrentHp = yourHP;
	enemyCurrentHp = enemyHP;
    }
    void showItemWindow() throws IOException{
	FXMLLoader loader = new FXMLLoader(getClass().getResource("Item.fxml"));
	AnchorPane root = (AnchorPane) loader.load();
	Scene scene = new Scene(root);
	Stage stage = new Stage();
	stage.setScene(scene);
	stage.showAndWait();
    }
    void newStage(){
	stage++;
	yourFeeling = 3;
	enemyFeeling = 3;
	enemyMaxHp += 10;
	enemyCurrentHp = enemyMaxHp;
	//敵をランダムで決めています
	enemyChara = random.nextInt(3);
	changeView();
	//背景を表示させています
	Image backGroundImage = new Image("jpg/field" + stage + ".jpg");
	backgroundView.setImage(backGroundImage);
    }
    @FXML
    void initialize() {
	assert backgroundView != null : "fx:id=\"backgroundView\" was not injected: check your FXML file 'Battle.fxml'.";
	assert yourHP != null : "fx:id=\"yourHP\" was not injected: check your FXML file 'Battle.fxml'.";
	assert enemyHP != null : "fx:id=\"enemyHP\" was not injected: check your FXML file 'Battle.fxml'.";
	assert timeLabel != null : "fx:id=\"timeLabel\" was not injected: check your FXML file 'Battle.fxml'.";
	assert attackButton != null : "fx:id=\"attackButton\" was not injected: check your FXML file 'Battle.fxml'.";
	assert Action != null : "fx:id=\"Action\" was not injected: check your FXML file 'Battle.fxml'.";
	assert defenseButton != null : "fx:id=\"defenseButton\" was not injected: check your FXML file 'Battle.fxml'.";
	assert cureButton != null : "fx:id=\"cureButton\" was not injected: check your FXML file 'Battle.fxml'.";
	assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'Battle.fxml'.";
	assert yourView != null : "fx:id=\"yourView\" was not injected: check your FXML file 'Battle.fxml'.";
	assert enemyView != null : "fx:id=\"enemyView\" was not injected: check your FXML file 'Battle.fxml'.";
	assert yourHp != null : "fx:id=\"yourHp\" was not injected: check your FXML file 'Battle.fxml'.";
	assert enemyHp != null : "fx:id=\"enemyHp\" was not injected: check your FXML file 'Battle.fxml'.";
	assert yourActionView != null : "fx:id=\"yourActionView\" was not injected: check your FXML file 'Battle.fxml'.";
	assert enemyActionView != null : "fx:id=\"enemyActionView\" was not injected: check your FXML file 'Battle.fxml'.";

	Bgm.playBgm2();
	enemyCurrentHp = enemyMaxHp;
	//timeLabel.setText("Time" + time);
  start = Time.start();
  //敵をランダムで決めています
	enemyChara = random.nextInt(3);
	stage = 2;
	yourCurrentHp = yourMaxHp;
	enemyCurrentHp = enemyMaxHp;
	yourChara = StartController.getChara();
	gender = StartController.getGender();
	Image backGroundImage = new Image("jpg/field" + stage + ".jpg");
	backgroundView.setImage(backGroundImage);
	changeView();
	yourActionView.setVisible(false);
	enemyActionView.setVisible(false);
    }
}
