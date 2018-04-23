package application;	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	
	private static Stage stage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("view/MainView.fxml"));
			primaryStage.setScene(new Scene(root, 1000, 1000));
			primaryStage.setTitle("\tSnapper");
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		Main.stage = primaryStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static Stage getStage() {
		return stage;
	}
}
