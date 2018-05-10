package gui;
import java.io.IOException;

import business.BasisModel;
import business.Timer;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class BasisView {

	private BasisModel basisModel;
	private BasisControl basisControl;
	private Pane pane;
	
	private final int guispalte1 = 20;
	private final int guispalte2 = 200;
	private final int guispalte3 = 450;
	private final int guireihe1 = 25;
	private final int guireihe2 = 75;
	private final int guireihe3 = 150;
	private final int guireihe4 = 225;
	private final int guireihe5 = 275;
	
	private int messreihe;
	private int flnummer;
	private Timer timer;
	
	@FXML TextField txtID;
	@FXML TextField txtVerbraucher;
	@FXML TextField txtMessgroesse;
	@FXML TextField txtZeitintervall;
	
	public BasisView(BasisModel bm, BasisControl bc, Stage primaryStage){
		
		try {
			primaryStage.setTitle("EMU-Messgerät Anwendung");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(
			"/gui/BasisView.fxml"));
			// BorderPane root = new BorderPane();
			Pane root = loader.load();
			// root.setStyle("-fx-background-color:#FFAA00;");
			Scene scene = new Scene(root, 600,600);
			scene.getStylesheets().add(getClass().getResource(
			"/gui/application.css")
			.toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		basisModel = bm;
		basisControl = bc;
		initComponents(primaryStage);
		
	}
	
	
	
	private void initComponents(Stage primaryStage){
		
		/*Button bttnDBLesen = new Button("Messungen aus DB lesen");
		bttnDBLesen.setLayoutX(guispalte3);
		bttnDBLesen.setLayoutY(guireihe1);
		bttnDBLesen.setPrefWidth(200);
		pane.getChildren().add(bttnDBLesen);
		
		Button bttnEMUMessung = new Button("Messung aus EMU holen");
		bttnEMUMessung.setLayoutX(guispalte3);
		bttnEMUMessung.setLayoutY(guireihe2);
		bttnEMUMessung.setPrefWidth(200);
		pane.getChildren().add(bttnEMUMessung);
		
		Button bttnMessungStarten = new Button("Start: Messreihe aufnehmen");
		bttnMessungStarten.setLayoutX(guispalte3);
		bttnMessungStarten.setLayoutY(guireihe4);
		bttnMessungStarten.setPrefWidth(200);
		pane.getChildren().add(bttnMessungStarten);
		
		Button bttnMessungStoppen = new Button("Stopp: Messreihe aufnehmen");
		bttnMessungStoppen.setLayoutX(guispalte3);
		bttnMessungStoppen.setLayoutY(guireihe5);
		bttnMessungStoppen.setPrefWidth(200);
		pane.getChildren().add(bttnMessungStoppen);
		
		
		TextField txfLaufendeNummer = new TextField();
		txfLaufendeNummer.setLayoutX(guispalte2);
		txfLaufendeNummer.setLayoutY(guireihe2);
		txfLaufendeNummer.setPrefWidth(200);
		pane.getChildren().add(txfLaufendeNummer);
		
		TextField txfMessreihenNummer = new TextField();
		txfMessreihenNummer.setLayoutX(guispalte2);
		txfMessreihenNummer.setLayoutY(guireihe1);
		txfMessreihenNummer.setPrefWidth(200);
		pane.getChildren().add(txfMessreihenNummer);
		
		Label lblMessreihenId = new Label("Messreihen ID:");
		lblMessreihenId.setLayoutX(guispalte1);
		lblMessreihenId.setLayoutY(guireihe1);
		lblMessreihenId.setPrefWidth(200);
		pane.getChildren().add(lblMessreihenId);
		
		Label lblFortlaufendeNummer = new Label("Fortlaufende Nummer:");
		lblFortlaufendeNummer.setLayoutX(guispalte1);
		lblFortlaufendeNummer.setLayoutY(guireihe2);
		lblFortlaufendeNummer.setPrefWidth(200);
		pane.getChildren().add(lblFortlaufendeNummer);
		
		TextField txfAusgeleseneWerte = new TextField();
		txfAusgeleseneWerte.setLayoutX(guispalte1);
		txfAusgeleseneWerte.setLayoutY(guireihe3);
		txfAusgeleseneWerte.setPrefWidth(625);
		pane.getChildren().add(txfAusgeleseneWerte);
		
		bttnDBLesen.setOnAction(new EventHandler(){

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				int messreihenId = Integer.parseInt(txfMessreihenNummer.getText());
				txfAusgeleseneWerte.setText(basisControl.leseDatenAusDB(messreihenId));
			}});
		
		bttnEMUMessung.setOnAction(new EventHandler(){

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				int messreihenId = Integer.parseInt(txfMessreihenNummer.getText());
				int laufendeNummer = Integer.parseInt(txfLaufendeNummer.getText());
				try {
					txfAusgeleseneWerte.setText(""+ basisControl.fuehreMessungDurch(messreihenId, laufendeNummer));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}});
		
		bttnMessungStarten.setOnAction(new EventHandler(){
			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				timer = new Timer(basisModel,Integer.parseInt(txfMessreihenNummer.getText()));
				timer.start();
			
			}});
		
		bttnMessungStoppen.setOnAction(new EventHandler(){
			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				timer.setStopp(true);
			}});*/

	}
	
	private void ausDBLesen(){
		int messreihenId = Integer.parseInt(txtID.getText());
		//txfAusgeleseneWerte.setText(basisControl.leseDatenAusDB(messreihenId));
	}
	
	/*private void einzelneMessungDurchführen(){
		int messreihenId = Integer.parseInt(txtID.getText());
		//int laufendeNummer = Integer.parseInt(txfLaufendeNummer.getText());
		try {
			txfAusgeleseneWerte.setText(""+ basisControl.fuehreMessungDurch(messreihenId, laufendeNummer));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	private void mehrereMessungenStarten(){
		timer = new Timer(basisModel,Integer.parseInt(txtID.getText()));
		timer.start();
	}
	
	private void mehrereMessungenStoppen(){
		timer.setStopp(true);
	}

	private void messreihenLesen(){
		int messreihenId = Integer.parseInt(txtID.getText());
		//txfAusgeleseneWerte.setText(basisControl.leseDatenAusDB(messreihenId));
	}
	private void messreiheStarten(){
		int messreihenId = Integer.parseInt(txtID.getText());
		//txfAusgeleseneWerte.setText(basisControl.leseDatenAusDB(messreihenId));
	}
	private void messreiheStoppen(){
		int messreihenId = Integer.parseInt(txtID.getText());
		//txfAusgeleseneWerte.setText(basisControl.leseDatenAusDB(messreihenId));
	}
	private void messreiheSpeichern(){
		int messreihenId = Integer.parseInt(txtID.getText());
		//txfAusgeleseneWerte.setText(basisControl.leseDatenAusDB(messreihenId));
	}
	
	
}
			
	
