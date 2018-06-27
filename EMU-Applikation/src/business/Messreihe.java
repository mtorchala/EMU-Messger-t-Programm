package business;
public class Messreihe {
	
	private int messreihenId;
	private int zeitintervall;
	private String verbraucher;
	private String messgroesse;
	private Messung[] messungen;
	
	public Messreihe(){
		
	}
	
	public Messreihe(int messreihenId, int zeitintervall,
		String verbraucher, String messgroesse) {
		super();
		
		if(messreihenId < 0 || zeitintervall < 15){
			throw new IllegalArgumentException("Fehler bei der Erstellung des Konstruktors! Messreihen-ID ist mit den falschen Werten besetzt!");
		}
		
		else if(verbraucher == null){
			throw new IllegalArgumentException("Fehler bei der Erstellung des Konstruktors! Übergebener Parameter verbraucher ist null!");
		}
		else if(verbraucher.equals("")){
			throw new IllegalArgumentException("Fehler bei der Erstellung des Konstruktors! Übergebener Parameter verbraucher ist als leerer String gesetzt!");
		}
		
		else if(!(messgroesse.equals("Arbeit") || messgroesse.equals("Leistung"))){
			throw new IllegalArgumentException("Fehler bei der Erstellung des Konstruktors!! Übergebener Parameter messgroesse ist mit den falschen Werten besetzt!");
		}
		else{
			this.messreihenId = messreihenId;
			this.zeitintervall = zeitintervall;
			this.verbraucher = verbraucher;
			this.messgroesse = messgroesse;
		}
	}
	
	public Messreihe(int messreihenId, int zeitintervall) {
			this.messreihenId = messreihenId;
			if(zeitintervall >= 15 && zeitintervall <= 3600){
				this.zeitintervall = zeitintervall;
			}
			else if(zeitintervall < 15){
				throw new IllegalArgumentException("Das Zeitintervall muss mindestens 15 Sekunden lang sein.");
			}
			else if(zeitintervall > 3600){
				throw new IllegalArgumentException("Das Zeitintervall darf maximal 3600 Sekunden lang sein.");
			}
	}

	public int getMessreihenId() {
		return messreihenId;
	}
	
	public void setMessreihenId(int messreihenId) {
		this.messreihenId = messreihenId;
	}
	
	public int getZeitintervall() {
		return zeitintervall;
	}
	
	public void setZeitintervall(int zeitintervall) {
		this.zeitintervall = zeitintervall;
	}
	public String getVerbraucher() {
		return verbraucher;
	}
	
	public void setVerbraucher(String verbraucher) {
		this.verbraucher = verbraucher;
	}
	
	public String getMessgroesse() {
		return messgroesse;
	}

	public void setMessgroesse(String messgroesse) {
		this.messgroesse = messgroesse;
	}
	
	public Messung[] getMessungen() {
		return messungen;
	}
	
	public void setMessungen(Messung[] messungen) {
		this.messungen = messungen;
	}
	
	public String gibAttributeAus(){
		return this.messreihenId + " " 
	 	    + this.zeitintervall + " " + this.verbraucher + " " + this.messgroesse;
	}

}
