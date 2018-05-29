package business;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

class RWSAnbindung{
 
    Statement statement;
    Connection con;

       
    public RWSAnbindung(Connection conMySql)
        throws SQLException {
        this.con = conMySql;
        this.statement = conMySql.createStatement();
    }
    
    public Messung[] leseMessungen(int messreihenId) throws SQLException, JsonParseException, JsonMappingException, IOException{ 
    	 WebResource wrs = Client.create().resource("http://localhost:8080/EMU-Webserver/webapi/funktionen/leseMessungen/"+messreihenId);
         String jsonResponse = wrs.accept(MediaType.APPLICATION_JSON).get(String.class);
         
         ObjectMapper om = new ObjectMapper();
         Messung[] m = om.readValue(jsonResponse, Messung[].class);
        
         
         return m;
       
    }
    
    public Messreihe[] leseAlleMessreihen()
            throws SQLException, JsonParseException, JsonMappingException, IOException { 
           /* ResultSet ergebnis;
            ergebnis = this.statement.executeQuery(
            	"SELECT * FROM Messreihe");
            Vector<Messreihe> messreihen = new Vector<Messreihe>();
            while(ergebnis.next()) {
            	messreihen.add( 
                    new Messreihe( 
                   	Integer.parseInt(ergebnis.getString(1)),Integer.parseInt(ergebnis.getString(2)), 
                    ergebnis.getString(3),ergebnis.getString(4)));
             }
             ergebnis.close(); */
             
          /*   Client client = ClientBuilder.newClient();
             WebTarget webTarget = client.target("http://localhost:8080/EMU-Webserver/funktionen/leseAlleMessreihen");
           */
             
             WebResource wrs = Client.create().resource("http://localhost:8080/EMU-Webserver/webapi/funktionen/leseAlleMessreihen");
             String jsonResponse = wrs.accept(MediaType.APPLICATION_JSON).get(String.class);
             
             ObjectMapper om = new ObjectMapper();
             Messreihe[] m = om.readValue(jsonResponse, Messreihe[].class);
             
             /*for(Messreihe r : m){
            	 
            	 System.out.println(r.gibAttributeAus());
             }
             */
             
             return m;
             
        }
        
 
    public void fuegeMessungEin(int messreihenId, Messung messung)
        throws SQLException, UniformInterfaceException, ClientHandlerException, JsonProcessingException {
    	WebResource wrs = Client.create().resource("http://localhost:8080/EMU-Webserver/webapi/funktionen/fuegeMessungEin/"+messreihenId);
        wrs.type(MediaType.APPLICATION_JSON).post(new ObjectMapper().writeValueAsString(messung));
    }
    
    public void fuegeMessreiheEin(Messreihe messreihe)
            throws SQLException, UniformInterfaceException, ClientHandlerException, JsonProcessingException {
    	WebResource wrs = Client.create().resource("http://localhost:8080/EMU-Webserver/webapi/funktionen/fuegeMessreiheEin");
        wrs.type(MediaType.APPLICATION_JSON).post(new ObjectMapper().writeValueAsString(messreihe));
        }

	public int getAnzahlMessungen(int messreihenId) throws SQLException {
		 WebResource wrs = Client.create().resource("http://localhost:8080/EMU-Webserver/webapi/funktionen/getAnzahlMessungen/"+messreihenId);
         String jsonResponse = wrs.accept(MediaType.APPLICATION_JSON).get(String.class);
         return Integer.parseInt(jsonResponse);
	}
	
	public int getAnzahlMessreihen(int messreihenId) throws SQLException {
		WebResource wrs = Client.create().resource("http://localhost:8080/EMU-Webserver/webapi/funktionen/getAnzahlMessreihen/"+messreihenId);
        String jsonResponse = wrs.accept(MediaType.APPLICATION_JSON).get(String.class);
        return Integer.parseInt(jsonResponse);
	}
	
	public Connection verbindungAufbauen() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
    	Connection con = DriverManager.getConnection(
    	    "jdbc:mysql://192.168.66.16:3306/Torchala", "Torchala", 
			    "marcmarc");
		return con;
	}
}    

