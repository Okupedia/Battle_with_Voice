import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;

import javafx.application.Platform;
import javafx.stage.Window;
import javafx.scene.*;
import java.util.Random;

public class ItemController {

    private int item = 0;
    int stage = 0;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView backGroundView;

    @FXML
    private Button item1Button;

    @FXML
    private Button item2Button;

    @FXML
    private Button item3Button;

    @FXML
    private Button item4Button;

    @FXML
    private ImageView coverImageView;

    @FXML
    private ImageView gotItemView;

    @FXML
    private Label gotItemLabel;

    @FXML
    private Button okButton;

    @FXML
    void onItem1ButtonAction(ActionEvent event) {
	boxDecided();
    }

    @FXML
    void onItem2ButtonAction(ActionEvent event) {
	boxDecided();
    }

    @FXML
    void onItem3ButtonAction(ActionEvent event) {
	boxDecided();
    }

    @FXML
    void onItem4ButtonAction(ActionEvent event) {
	boxDecided();
    }

    void boxDecided(){
	Random random = new Random();
	item = random.nextInt(4);
	String str = "You couldn't find any item...";
	switch(item){
	    case 0:
		str = "You got more time!";
		BattleController.setTime(20);
		break;
	    case 1:
		str = "You got recovery item!";
		BattleController.setHp(10);
		break;
	    case 2:
		str = "You took suspicious drug..."; 
		BattleController.setHp(-10);
		break;
	    case 3:
		str = "You learned enemy's skill!";
		BattleController.setChara();
		break;
	}
	Image coverImage = new Image("jpg/field" + stage + ".jpg");
	backGroundView.setImage(coverImage);
	Image itemImage = new Image("item/" + item + ".png");
	gotItemView.setImage(itemImage);
	gotItemLabel.setText(str);
	coverImageView.setVisible(true);
	gotItemView.setVisible(true);
	gotItemLabel.setVisible(true);
	okButton.setVisible(true);
    }

    @FXML
    void onOkButtonAction(ActionEvent event) {
        Scene scene = ((Node) event.getSource()).getScene();
	Window window = scene.getWindow();
	window.hide();
    }

    @FXML
    void initialize() {
	assert backGroundView != null : "fx:id=\"backGroundView\" was not injected: check your FXML file 'Item.fxml'.";
	assert item1Button != null : "fx:id=\"item1Button\" was not injected: check your FXML file 'Item.fxml'.";
	assert item2Button != null : "fx:id=\"item2Button\" was not injected: check your FXML file 'Item.fxml'.";
	assert item3Button != null : "fx:id=\"item3Button\" was not injected: check your FXML file 'Item.fxml'.";
	assert item4Button != null : "fx:id=\"item4Button\" was not injected: check your FXML file 'Item.fxml'.";
	assert coverImageView != null : "fx:id=\"coverImageView\" was not injected: check your FXML file 'Item.fxml'.";
	assert gotItemView != null : "fx:id=\"gotItemView\" was not injected: check your FXML file 'Item.fxml'.";
	assert gotItemLabel != null : "fx:id=\"gotItenLabel\" was not injected: check your FXML file 'Item.fxml'.";
	assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'Item.fxml'.";

	coverImageView.setVisible(false);
	gotItemView.setVisible(false);
	gotItemLabel.setVisible(false);
	okButton.setVisible(false);

	stage = BattleController.getStage();
	Image backGroundImage = new Image("jpg/field" + stage + ".jpg");
	backGroundView.setImage(backGroundImage);

    }
}
