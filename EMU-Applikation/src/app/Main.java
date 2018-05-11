package app;
	
import business.BasisModel;
import gui.BasisControl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		BasisModel bm = new BasisModel();
		BasisControl bc = new BasisControl();
		
		try {
			primaryStage.setTitle("EMU-Messgerät Anwendung");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("../gui/BasisView.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root, 600,600);
			scene.getStylesheets().add(getClass().getResource("../gui/application.css")
			.toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
