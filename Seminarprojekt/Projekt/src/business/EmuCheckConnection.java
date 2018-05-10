package business;

import com.ftdichip.ftd2xx.*;

public class EmuCheckConnection extends Thread{
	
	private final int EMU = 49632;
	private Device device = null;
	private Port port = null;
	
     // Attribut zur Regelung des Threads, siehe unten
	private boolean connected = true;

	private String ergebnis = "";
	private boolean ergSchreiben = false;
	
	public boolean getErgSchreiben() {
		return ergSchreiben;
	}

	public void setErgSchreiben(boolean ergSchreiben) {
		this.ergSchreiben = ergSchreiben;
	}

	public double gibErgebnisAus() {
		if(ergebnis.contains("*")){
			int a = ergebnis.indexOf("(");
			int m = ergebnis.indexOf("*");
			ergebnis = ergebnis.substring(a+1,m);
		}
		return Double.parseDouble(ergebnis);
	}
	
	public EmuCheckConnection() throws Exception{	
		for(Device dev : Service.listDevices()){
			if(dev.getDeviceDescriptor().getProductID() == EMU){
				device = dev;
			}
		}
		if(device == null){
			throw new Exception("Device not found");
		}
		
		this.device.open();
		port = device.getPort();	
		port.setDataCharacteristics(DataBits.DATA_BITS_7, StopBits.STOP_BITS_1, Parity.EVEN);
		port.setBaudRate(300);	
 		

		System.out.println("Device gefunden: " 
 			+ device.getDeviceDescriptor().getProductDescription());
		
		start();
	}
	
	public void connect() throws FTD2xxException{
		byte[] start 
              = new byte[]{0x2F, 0x3F, 0x21, 0x0D, 0x0A};
		port.setBaudRate(300);
		this.device.write(start);
		System.out.println(
              "Einleitung Kommunikation USBCheck");
	}

	
	public void disconnect() throws FTD2xxException{
		this.connected = false;
		byte[] ende = new byte[]{0x01, 0x42, 0x30, 0x03};
		this.device.write(ende);
		this.device.close();
		System.out.println("Ende Kommunikation USBCheck");
	}
	
	public void sendProgrammingMode() throws FTD2xxException{
		byte[] nachricht = new byte[]
 			{0x06, 0x30, 0x30, 0x31, 0x0D, 0x0A};
		this.device.write(nachricht);
		System.out.println("Programming Mode");
	}

	public void sendRequest(MESSWERT m)throws FTD2xxException{
		device.write(m.getRequest());
		ergSchreiben = true;
		System.out.println("Request " + m.getObis() + " " + m.toString());
	}
	
	// Damit das Device staendig liest, muss die Klasse
 	// EmuCheckConnection ein Thread sein, also von der Klasse
 	// Thread ableiten. Der Thread wird am Ende des Konstruktors
 	// gestartet. Weiterhin muss die Methode run
 	// ueberschrieben werden.
	
	 
	@Override
	public void run() {
		int b;
		while(connected){
			try {
				b = device.read();
				if(b != 0xffffffff){
					System.out.print((char)b);
					if(ergSchreiben){
						ergebnis = ergebnis + (char)b;
					}
				}
			} catch (FTD2xxException e) {
				System.out.println("Lesen fehlgeschlagen");
				e.printStackTrace();
				
			}
			
			try {
				sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
}

