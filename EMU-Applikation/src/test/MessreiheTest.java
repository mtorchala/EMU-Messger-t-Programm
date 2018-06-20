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
	
	@Test(expectedExceptions = {IllegalArgumentException.class},groups = { "erwartetexception" })
	public void Mkl0_Zkl15_V_M(){
		messreihe = new Messreihe(-3,10,null,"Arbeit");
	}
	
	@Test(expectedExceptions = {IllegalArgumentException.class},groups = { "erwartetexception" })
	public void Mkl0_Zgrgl15_V_M(){
		messreihe = new Messreihe(-3,40,"LED","");
		
	}
	
	@Test(expectedExceptions = {IllegalArgumentException.class},groups = { "erwartetexception" })
	public void Mgrgl0_Zkl15_V_M(){
		messreihe = new Messreihe(30,10,null,"");
		
	}
	
	@Test(expectedExceptions = {IllegalArgumentException.class},groups = { "erwartetexception" })
	public void Mgrgl0_Zgrgl15_V_M(){
		messreihe = new Messreihe(30,40,"","Leistung");
		
	}
	@Test(expectedExceptions = {IllegalArgumentException.class},groups = { "erwartetexception" })
	public void Mgrgl0_Zgrgl15_Vstrings_Mfalsch(){
		messreihe = new Messreihe(30,40,"LED","");
		
	}
	
	@Test
	public void Mgrgl0_Zgrgl15_Vstrings_Mkorrekt(){
		messreihe = new Messreihe(30,40,"LED","Leistung");
		Assert.assertTrue(30 == messreihe.getMessreihenId());
		Assert.assertTrue(40 == messreihe.getZeitintervall());
		Assert.assertTrue(messreihe.getVerbraucher().equals("LED"));
		Assert.assertTrue(messreihe.getMessgroesse().equals("Leistung"));
		
	}
}
