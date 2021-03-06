package business;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BasisModel {
	
	EmuCheckConnection ecc;

	public String leseMessungenAlsStringAusDB(int messreihenId) throws ClassNotFoundException, SQLException, JsonParseException, JsonMappingException, IOException{
	 	RWSAnbindung dbAktionen = new RWSAnbindung(verbindungAufbauen());
        Messung[] messungen = dbAktionen.leseMessungen(messreihenId); 
        String s = "";
        for(Messung m : messungen){
        	s+= " "+ m.gibAttributeAus() + " | ";
        }
        return s;
	}
	
	public Messung[] leseMessungenAusDB(int messreihenId) throws ClassNotFoundException, SQLException, JsonParseException, JsonMappingException, IOException{
	 	RWSAnbindung dbAktionen = new RWSAnbindung(verbindungAufbauen());
        Messung[] messungen = dbAktionen.leseMessungen(messreihenId); 
        return messungen;
	}
	
	public int getAnzahlMessungenZuMessreihe(int messreihenId) throws ClassNotFoundException, SQLException{
	 	RWSAnbindung dbAktionen = new RWSAnbindung(verbindungAufbauen());
        int anzahlMessungen = dbAktionen.getAnzahlMessungen(messreihenId); 
        return anzahlMessungen;
	}
	
	public int getExistiertMessreiheSchon(int messreihenId) throws ClassNotFoundException, SQLException{
	 	RWSAnbindung dbAktionen = new RWSAnbindung(verbindungAufbauen());
        int anzahlMessreihen = dbAktionen.getAnzahlMessreihen(messreihenId); 
        return anzahlMessreihen;
	}
	
	public ObservableList<Messreihe> leseMessreihenAusDB() throws SQLException, ClassNotFoundException, JsonParseException, JsonMappingException, IOException{
		RWSAnbindung dbAktionen = new RWSAnbindung(verbindungAufbauen());
        Messreihe[] messreihen = dbAktionen.leseAlleMessreihen();
        
        ObservableList<Messreihe> ol = FXCollections.observableArrayList(messreihen);
		return ol; 
		
	}
	
	public Connection verbindungAufbauen() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
    	Connection con = DriverManager.getConnection(
    	    "jdbc:mysql://192.168.66.16:3306/Torchala", "Torchala", 
			    "marcmarc");
		return con;
	}
	
	public double fuehreMessungDurch(int messreihenId,int laufendenummer, MESSWERT messgroesse) throws Exception{
		ecc = new EmuCheckConnection();
		ecc.connect();
		Thread.sleep(1000);
		ecc.sendProgrammingMode();
		Thread.sleep(1000);
		ecc.sendRequest(messgroesse);
		Thread.sleep(1000);
		DatenEinfuegen(ecc.gibErgebnisAus(),messreihenId,laufendenummer);
		ecc.disconnect();
		return ecc.gibErgebnisAus();
	}
	
	public void DatenEinfuegen(double w, int messreihenId,int laufendeNummer) throws ClassNotFoundException, SQLException, UniformInterfaceException, ClientHandlerException, JsonProcessingException{
		Messung messung = new Messung(laufendeNummer,w);
		RWSAnbindung dbAktionen = new RWSAnbindung(verbindungAufbauen());
	    dbAktionen.fuegeMessungEin(messreihenId, messung);
	}
	
	public void speichereMessreihe(Messreihe messreihe) throws SQLException, ClassNotFoundException, UniformInterfaceException, ClientHandlerException, JsonProcessingException{
		RWSAnbindung dbAktionen = new RWSAnbindung(verbindungAufbauen());
        dbAktionen.fuegeMessreiheEin(messreihe);
	}
}
