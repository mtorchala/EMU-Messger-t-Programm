package business;

public class Timer extends Thread {
	
    private boolean stopp = false;
    private BasisModel bm;
	private int messreihenId;
	private int laufendenummer = 0;
	
	public Timer(BasisModel besitzer, int m){
	    this.bm = besitzer;
	    this.messreihenId = m;
	}		 
    	
	public void setStopp(boolean stopp){
		this.stopp = stopp;
	}

    public void run() {
        while(! this.stopp) {
            try {
                sleep(1000);
                laufendenummer++;
				bm.fuehreMessungDurch(messreihenId, laufendenummer);
            }
            catch(Exception exc) {
                exc.printStackTrace();
            }
        }
    }
}
