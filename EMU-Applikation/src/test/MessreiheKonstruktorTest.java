package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import business.Messreihe;

public class MessreiheKonstruktorTest {
	
	Messreihe messreihe;
	
	@Test
	public void Knoten125(){
		messreihe = new Messreihe(1,20);
		Assert.assertTrue(1 == messreihe.getMessreihenId());
		Assert.assertTrue(20 == messreihe.getZeitintervall());  
	}
	
	@Test(expectedExceptions = {IllegalArgumentException.class})
	public void Knoten135(){
		messreihe = new Messreihe(1,14);
		
	}
	
	@Test(expectedExceptions = {IllegalArgumentException.class})
	public void Knoten145(){
		messreihe = new Messreihe(1,3601);
		
	}
	
	
}
