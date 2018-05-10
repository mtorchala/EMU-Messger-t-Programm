package gui;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import business.BasisModel;
import business.Messreihe;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BasisControl{
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
	
	

	
	public BasisControl() throws Exception{
		
		basisModel = new BasisModel();
	}
	
	public String leseDatenAusDB(int messreihenId){
		try {
			return basisModel.leseDatenAusDB(messreihenId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
	}
	
	public double fuehreMessungDurch(int messreihenId, int laufendeNummer) throws Exception {
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
	}
	
	@FXML
	private void messreihenLesen(){
		try {
			ObservableList<Messreihe> ausgeleseneMessreihen = basisModel.leseMessreihenAusDB();
			tableviewAnzeige.setItems(ausgeleseneMessreihen);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void messreiheStarten(){
		//int messreihenId = Integer.parseInt(txtID.getText());
		//txfAusgeleseneWerte.setText(basisControl.leseDatenAusDB(messreihenId));
		
	}
	
	@FXML
	private void messreiheStoppen(){
		//int messreihenId = Integer.parseInt(txtID.getText());
		//txfAusgeleseneWerte.setText(basisControl.leseDatenAusDB(messreihenId));
		
	}
	
	@FXML
	private void messreiheSpeichern(){
		//int messreihenId = Integer.parseInt(txtID.getText());
		//txfAusgeleseneWerte.setText(basisControl.leseDatenAusDB(messreihenId));
		
	}
	
	
}
