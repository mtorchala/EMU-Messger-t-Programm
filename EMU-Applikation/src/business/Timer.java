package business;

public class Timer extends Thread {
	
    private boolean stopp = false;
    private BasisModel bm;
	private int messreihenId;
	private int zeitintervall;
	private int laufendenummer = 0;
	
	public Timer(BasisModel besitzer, int m, int z){
	    this.bm = besitzer;
	    this.messreihenId = m;
	    this.zeitintervall = z*1000;
	}		 
    	
	public void setStopp(boolean stopp){
		this.stopp = stopp;
	}

    public void run() {
        while(! this.stopp) {
            try {
                sleep(zeitintervall);
                laufendenummer++;
				bm.fuehreMessungDurch(messreihenId, laufendenummer);
            }
            catch(Exception exc) {
                exc.printStackTrace();
            }
        }
    }
}
