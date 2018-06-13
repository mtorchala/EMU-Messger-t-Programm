package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import business.Messreihe;

public class MessreiheTest {
	
	Messreihe messreihe = new Messreihe(1,20,"LED","Leistung");
	
	@Test
	public void konstruktorTest(){
		Assert.assertTrue(1 == messreihe.getMessreihenId());
		Assert.assertTrue(20 == messreihe.getZeitintervall());
		Assert.assertTrue(messreihe.getVerbraucher().equals("LED"));
		Assert.assertTrue(messreihe.getMessgroesse().equals("Leistung"));
	}
}
