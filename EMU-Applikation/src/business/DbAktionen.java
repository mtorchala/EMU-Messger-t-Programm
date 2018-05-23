package business;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

class DbAktionen{
 
    Statement statement;
    Connection con;

       
    public DbAktionen(Connection conMySql)
        throws SQLException {
        this.con = conMySql;
        this.statement = conMySql.createStatement();
    }
    
    public Messung[] leseMessungen(int messreihenId) throws SQLException{ 
        ResultSet ergebnis;
        ergebnis = this.statement.executeQuery(
        	"SELECT * FROM Messung WHERE MessreihenId = " + messreihenId);
        Vector<Messung> messungen = new Vector<Messung>();
        while(ergebnis.next()) {
            messungen.add( 
                new Messung( 
               	Integer.parseInt(ergebnis.getString(1)), 
                Double.parseDouble(ergebnis.getString(2))));
         }
         ergebnis.close();
         return messungen.toArray(new Messung[0]); 
    	
       
    }
    
    public Messreihe[] leseAlleMessreihen()
            throws SQLException, JsonParseException, JsonMappingException, IOException { 
            ResultSet ergebnis;
            ergebnis = this.statement.executeQuery(
            	"SELECT * FROM Messreihe");
            Vector<Messreihe> messreihen = new Vector<Messreihe>();
            while(ergebnis.next()) {
            	messreihen.add( 
                    new Messreihe( 
                   	Integer.parseInt(ergebnis.getString(1)),Integer.parseInt(ergebnis.getString(2)), 
                    ergebnis.getString(3),ergebnis.getString(4)));
             }
             ergebnis.close();
             
          /*   Client client = ClientBuilder.newClient();
             WebTarget webTarget = client.target("http://localhost:8080/EMU-Webserver/funktionen/leseAlleMessreihen");
           */
             
             WebResource wrs = Client.create().resource("http://localhost:8080/EMU-Webserver/funktionen/leseAlleMessreihen");
             String jsonResponse = wrs.accept(MediaType.APPLICATION_JSON).get(String.class);
             
             ObjectMapper om = new ObjectMapper();
             Messreihe[] m = om.readValue(jsonResponse, Messreihe[].class);
             
             for(Messreihe r : m){
            	 
            	 r.gibAttributeAus();
             }
             
             return messreihen.toArray(new Messreihe[0]);
             
        }
        
 
    public void fuegeMessungEin(int messreihenId, Messung messung)
        throws SQLException {
    	String insertMessungStatement = "INSERT INTO Messung "
            + "(LaufendeNummer, Wert, MessreihenId) "
            + "VALUES(" + 
            + messung.getLaufendeNummer() + ", " + messung.getWert() + ", "
            + messreihenId+ ")";
    	System.out.println(insertMessungStatement);
    	this.statement.executeUpdate(insertMessungStatement);
    }
    
    public void fuegeMessreiheEin(Messreihe messreihe)
            throws SQLException {
        	String insertMessreiheStatement = "INSERT INTO Messreihe "
                + "(MessreihenId, Zeitintervall, Verbraucher, Messgroesse) "
                + "VALUES(" + 
                + messreihe.getMessreihenId() + ", " +messreihe.getZeitintervall() + ", '" + messreihe.getVerbraucher()+"',"
                + "'" +messreihe.getMessgroesse()+ "')";
        	System.out.println(insertMessreiheStatement);
        	this.statement.executeUpdate(insertMessreiheStatement);
        }

	public int getAnzahlMessungen(int messreihenId) throws SQLException {
		ResultSet ergebnis;
        ergebnis = this.statement.executeQuery(
        	"SELECT COUNT(*) FROM Messung WHERE MessreihenId = " + messreihenId);
        int anzahl = -1;
        while(ergebnis.next()) {
            anzahl = Integer.parseInt(ergebnis.getString(1));
         }
         ergebnis.close();
         return anzahl;
	}
	
	public int getAnzahlMessreihen(int messreihenId) throws SQLException {
		ResultSet ergebnis;
        ergebnis = this.statement.executeQuery(
        	"SELECT COUNT(*) FROM Messreihe WHERE MessreihenId = " + messreihenId);
        int anzahl = -1;
        while(ergebnis.next()) {
            anzahl = Integer.parseInt(ergebnis.getString(1));
         }
         ergebnis.close();
         return anzahl;
	}
	
	public Connection verbindungAufbauen() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
    	Connection con = DriverManager.getConnection(
    	    "jdbc:mysql://192.168.66.16:3306/Torchala", "Torchala", 
			    "marcmarc");
		return con;
	}
}    

