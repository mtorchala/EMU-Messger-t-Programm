package business;

public enum EMUZeichenketten {
	SOH(0x01),
	STX(0x02),
	ETX(0x03),
	ACK(0x06),
	LF(0x0A),
	CR(0x0D);
	
	private char value;
	
	private EMUZeichenketten(int value){
		this.value = (char) value;
	}
	@Override
	public String toString(){
		return String.valueOf(value);
	}

}