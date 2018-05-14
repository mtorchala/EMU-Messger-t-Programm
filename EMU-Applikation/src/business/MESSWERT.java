package business;
import business.EMUZeichenketten;

public enum MESSWERT {
	
	Leistung(EMUZeichenketten.SOH + "R1" + EMUZeichenketten.STX + "1.7.1()" + EMUZeichenketten.ETX, "Leistung", "Watt"),
	Arbeit(EMUZeichenketten.SOH + "R1"+ EMUZeichenketten.STX + "1.8.1()" + EMUZeichenketten.ETX, "Arbeit", "W/h"),
	Strom(EMUZeichenketten.SOH + "R1"+ EMUZeichenketten.STX + "11.7()" + EMUZeichenketten.ETX, "Strom", "Ampere"),
	Spannung(EMUZeichenketten.SOH + "R1"+ EMUZeichenketten.STX + "12.7()" + EMUZeichenketten.ETX, "Spannung", "Volt"),
	Frequenz(EMUZeichenketten.SOH + "R1" + EMUZeichenketten.STX + "14.7()" + EMUZeichenketten.ETX, "Frequenz", "Hertz");
	

	private String obis, einheit,request;
	
	private MESSWERT(String request, String obis, String einheit){
		this.obis=obis;
		this.request=request;
		this.einheit=einheit;
		
	}

	public String getRequest() {
		return request;
	}

	public String getObis() {
		return obis;
	}

	public String getEinheit() {
		return einheit;
	}
}
