package business;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BasisModel {
	
	EmuCheckConnection ecc;

	public String leseDatenAusDB(int messreihenId) throws ClassNotFoundException, SQLException{
	 	DbAktionen dbAktionen = new DbAktionen(verbindungAufbauen());
        Messung[] messungen = dbAktionen.leseMessungen(messreihenId); 
        String s = "";
        for(Messung m : messungen){
        	s+= " "+ m.gibAttributeAus() + " | ";
        }
        return s;
	}
	
	public ObservableList<Messreihe> leseMessreihenAusDB() throws SQLException, ClassNotFoundException{
		DbAktionen dbAktionen = new DbAktionen(verbindungAufbauen());
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
	
	public double fuehreMessungDurch(int messreihenId,int laufendenummer) throws Exception{
		ecc = new EmuCheckConnection();
		ecc.connect();
		Thread.sleep(1000);
		ecc.sendProgrammingMode();
		Thread.sleep(1000);
		ecc.sendRequest(MESSWERT.Leistung);
		Thread.sleep(1000);
		DatenEinfügen(ecc.gibErgebnisAus(),messreihenId,laufendenummer);
		ecc.disconnect();
		return ecc.gibErgebnisAus();
	}
	
	public void DatenEinfügen(double w, int messreihenId,int laufendeNummer) throws ClassNotFoundException, SQLException{
		Messung messung = new Messung(laufendeNummer,w);
		DbAktionen dbAktionen = new DbAktionen(verbindungAufbauen());
	    dbAktionen.fuegeMessungEin(messreihenId, messung);
	}
	
	public void speichereMessreihe(Messreihe messreihe) throws SQLException, ClassNotFoundException{
		DbAktionen dbAktionen = new DbAktionen(verbindungAufbauen());
        dbAktionen.fuegeMessreiheEin(messreihe);
	}
}
