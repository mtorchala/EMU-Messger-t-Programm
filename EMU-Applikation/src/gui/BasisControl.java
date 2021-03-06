package gui;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;

import business.BasisModel;
import business.Messreihe;
import business.Messung;
import business.Timer;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BasisControl implements Initializable{
	private BasisModel basisModel;
	
	@FXML
	private TextField txtID;
	
	@FXML
	private TextField txtZeitintervall;
	
	@FXML
	private TextField txtVerbraucher;
	
	@FXML
	private TextField txtMessgroesse;
	
	@FXML
	private Button bttnMessreiheSpeichern;
	
	@FXML
	private Button bttnMessreiheStarten;
	
	@FXML
	private Button bttnMessreiheStoppen;
	
	@FXML
	private Button bttnMessreiheLesen;
	
	@FXML
	private TableView tableviewAnzeige;
	
	Timer timer;
	
	

	
	public BasisControl() throws Exception{
		
		basisModel = new BasisModel();
		
	
	}
	
	public String leseDatenAusDB(int messreihenId){
		try {
			return basisModel.leseMessungenAlsStringAusDB(messreihenId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/*public double fuehreMessungDurch(int messreihenId, int laufendeNummer) throws Exception {
		try {
			return basisModel.fuehreMessungDurch(messreihenId, laufendeNummer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0.0;
	}*/
	
	@FXML
	private void messreihenLesen(){
		try {
			ObservableList<Messreihe> ausgeleseneMessreihen = basisModel.leseMessreihenAusDB();
			
			tableviewAnzeige.setItems(ausgeleseneMessreihen);
			
			TableColumn colID = new TableColumn("ID");
			colID.setMinWidth(50);
			colID.setCellValueFactory(new PropertyValueFactory<Messreihe, Integer>("messreihenId"));
	        
			TableColumn colZeitintervall = new TableColumn("Zeitintervall");
	        colZeitintervall.setMinWidth(170);        
	        colZeitintervall.setCellValueFactory(new PropertyValueFactory<Messreihe, Integer>("zeitintervall"));
	        
	        TableColumn colVerbraucher = new TableColumn("Verbraucher");
	        colVerbraucher.setMinWidth(180);        
	        colVerbraucher.setCellValueFactory(new PropertyValueFactory<Messreihe, String>("verbraucher"));    
	        
	        TableColumn colMessgroesse = new TableColumn("Messgroesse");
	        colMessgroesse.setMinWidth(181);
	        colMessgroesse.setCellValueFactory(new PropertyValueFactory<Messreihe, String>("messgroesse"));
	        
	        tableviewAnzeige.getColumns().clear();
	        tableviewAnzeige.getColumns().addAll(colID,colZeitintervall,colVerbraucher,colMessgroesse);
	       
	        tableviewAnzeige.setItems(ausgeleseneMessreihen);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		bttnMessreiheStarten.setDisable(false);
		bttnMessreiheStoppen.setDisable(false);
	}
	
	@FXML
	private void messreiheStarten(){
		Messreihe messreihe = (Messreihe) tableviewAnzeige.getSelectionModel().getSelectedItem();
		
		if(messreihe != null){
			try {
				if(basisModel.getAnzahlMessungenZuMessreihe(messreihe.getMessreihenId()) == 0){
					timer = new Timer(basisModel,messreihe);
					timer.start();
				}
				else{
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Fehler");
					alert.setHeaderText("Ein Fehler ist aufgetreten!");
					alert.setContentText("Zu der ausgewählten Messreihe existieren bereits zugehörige Messungen.");
					alert.showAndWait();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
	
				e.printStackTrace();
			}
		}
		else{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Fehler");
			alert.setHeaderText("Ein Fehler ist aufgetreten!");
			alert.setContentText("Es wurde keine Messung ausgew�hlt!!");
			alert.showAndWait();
		}
	}
	
	@FXML
	private void messreiheStoppen(){
		timer.setStopp(true);
	}
	
	@FXML
	private void messreiheSpeichern(){
		int messreiheId = Integer.parseInt(txtID.getText());
		int zeitintervall = Integer.parseInt(txtZeitintervall.getText());
		String verbraucher = txtVerbraucher.getText();
		String messgroesse = txtMessgroesse.getText();
		try {
			if(basisModel.getExistiertMessreiheSchon(messreiheId) == 0){
				Messreihe mr = new Messreihe(messreiheId,zeitintervall,verbraucher,messgroesse);
				basisModel.speichereMessreihe(mr);
			}
			else{
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Fehler");
				alert.setHeaderText("Ein Fehler ist aufgetreten!");
				alert.setContentText("Es existiert schon eine Messreihe mit dieser MessreihenID !");
				alert.showAndWait();
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UniformInterfaceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientHandlerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		messreihenLesen();
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bttnMessreiheStarten.setDisable(true);
		bttnMessreiheStoppen.setDisable(true);
		
		ContextMenu contextmenu = new ContextMenu();
		MenuItem menueDarstellung = new MenuItem("Darstellung im Diagramm");
		contextmenu.getItems().add(menueDarstellung);
		
		menueDarstellung.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Messreihe messreihe = (Messreihe) tableviewAnzeige.getSelectionModel().getSelectedItem();
				Messung[] ausgeleseneMessungen = null;
				try {
					ausgeleseneMessungen = basisModel.leseMessungenAusDB(messreihe.getMessreihenId());
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (JsonParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Stage stage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("../gui/LineChartGUI.fxml"));
				Parent root = null;
				try {
					root = loader.load();
					LineChartDarstellung lcd = loader.getController();
					lcd.initialisiereDiagramm(messreihe,ausgeleseneMessungen);
				} catch (IOException e) {
					e.printStackTrace();
				}
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			}
			
		});	

		tableviewAnzeige.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

		    @Override
		    public void handle(MouseEvent t) {
		        if(t.getButton() == MouseButton.SECONDARY) {
		        	contextmenu.show(tableviewAnzeige, t.getScreenX(), t.getScreenY());
		        }
		    }
		});
	}
	
	
}
