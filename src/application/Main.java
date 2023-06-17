package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Main extends Application {
	
	private static Stage historyStage = null;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/gui/Calculator.fxml"));
			primaryStage.setResizable(false);
			primaryStage.setTitle("Calculator");
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
			
			createHistoryStage();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createHistoryStage() {
		historyStage = new Stage();
		historyStage.setTitle("Calculation history");
		historyStage.setResizable(false);
		historyStage.setAlwaysOnTop(true);
		historyStage.initModality(Modality.APPLICATION_MODAL);
	}
	
	public static Stage getHistoryStage() {
		return historyStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
