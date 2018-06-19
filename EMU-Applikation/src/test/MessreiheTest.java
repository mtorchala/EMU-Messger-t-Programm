package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import business.Messreihe;

public class MessreiheTest {
	
	Messreihe messreihe;
	
	@Test
	public void konstruktorTest(){
		messreihe = new Messreihe(1,20,"LED","Leistung");
		Assert.assertTrue(1 == messreihe.getMessreihenId());
		Assert.assertTrue(20 == messreihe.getZeitintervall());
		Assert.assertTrue(messreihe.getVerbraucher().equals("LED"));
		Assert.assertTrue(messreihe.getMessgroesse().equals("Leistung"));
	}
	
	@Test(expectedExceptions = {IllegalArgumentException.class})
	public void Mkl0_Z15_Snull_MaMl(){
		messreihe = new Messreihe(-1,20,null,"Arbeit");
	}
	
	@Test(expectedExceptions = {IllegalArgumentException.class})
	public void Mkl0_Z15_Sleer_MaMl(){
		messreihe = new Messreihe(-1,20,"","Leistung");
		
	}
	
	@Test(expectedExceptions = {IllegalArgumentException.class})
	public void Mkl0_Z15_Sverbraucher_MaMl(){
		messreihe = new Messreihe(-1,20,"LED","Arbeit");
		
	}
	
	@Test(expectedExceptions = {IllegalArgumentException.class})
	public void Mgrgl0_Z15_Snull_MaMl(){
		messreihe = new Messreihe(1,20,null,"Leistung");
		
	}
	@Test(expectedExceptions = {IllegalArgumentException.class})
	public void Mgrgl0_Z15_Sleer_MaMl(){
		messreihe = new Messreihe(1,20,"","Arbeit");
		
	}
	
	public void Mgrgl0_Z15_Sverbraucher_MaMl(){
		messreihe = new Messreihe(1,20,"LED","Leistung");
		
	}
}
