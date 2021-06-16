import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.*;
import javafx.scene.Scene;


public class BattleGame extends Application {

    private static Scene startScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
	primaryStage.setWidth(640);
	primaryStage.setHeight(480);
	primaryStage.setTitle("battleGame");
	startScene = new Scene(FXMLLoader.load(getClass().getResource("Start.fxml")));
	primaryStage.setScene(startScene);
	primaryStage.setResizable(false);
	primaryStage.show();
    }

    public static void main(String args[]) {
	launch(args);
    }

}
