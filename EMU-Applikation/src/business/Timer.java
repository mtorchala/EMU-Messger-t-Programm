package business;

public class Timer extends Thread {
	
    private boolean stopp = false;
    private BasisModel bm;
    private Messreihe messreihe;
    private int laufendenummer;
    private MESSWERT messwert;
	
	public Timer(BasisModel besitzer, Messreihe m){
	    this.bm = besitzer;
	    this.messreihe = m;
	}		 
    	
	

	public void setStopp(boolean stopp){
		this.stopp = stopp;
	}

    public void run() {
    	messwert = findeZugehoerigenMesswert();
    	laufendenummer = 0;
    	if(messwert != null){
	        while(! this.stopp) {
	            try {
	                sleep(messreihe.getZeitintervall());
					bm.fuehreMessungDurch(messreihe.getMessreihenId(), laufendenummer, messwert);
					laufendenummer++;
	            }
	            catch(Exception exc) {
	                exc.printStackTrace();
	            }
	        }
    	}
    }



	private MESSWERT findeZugehoerigenMesswert() {
	
		switch(messreihe.getMessgroesse()){
			case "Leistung":
				
				messwert = MESSWERT.Leistung;
				break;
				
			case "Arbeit":
				messwert = MESSWERT.Arbeit;
				break;
				
			case "Frequenz":
				messwert = MESSWERT.Frequenz;
				break;
				
			case "Strom":
				messwert = MESSWERT.Strom;
				break;
				
			case "Spannung":
				messwert = MESSWERT.Spannung;
				break;
				
			default:
				messwert = null;
				break;
		}
		return messwert;
		
	}
}
