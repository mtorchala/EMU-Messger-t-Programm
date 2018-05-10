package business;
import java.sql.*;
import java.util.Vector;

class DbAktionen{
 
    Statement statement;
    Connection con;

       
    public DbAktionen(Connection conMySql)
        throws SQLException {
        this.con = conMySql;
        this.statement = conMySql.createStatement();
    }
    
    public Messung[] leseMessungen(int messreihenId)
        throws SQLException { 
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
}    

