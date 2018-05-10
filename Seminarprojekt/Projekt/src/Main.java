import java.io.IOException;
import java.sql.*;

import business.*;
import gui.BasisControl;
import gui.BasisView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

		BasisModel bm = new BasisModel();
		BasisControl bc = new BasisControl(primaryStage);
		BasisView bv = new BasisView(bm,bc,primaryStage);		
		
	}

}
