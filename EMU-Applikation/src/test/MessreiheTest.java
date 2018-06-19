package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import business.Messreihe;

public class MessreiheTest {
	
	
	
	@Test
	public void konstruktorTest(){
		Messreihe messreihe = new Messreihe(1,20,"LED","Leistung");
		Assert.assertTrue(1 == messreihe.getMessreihenId());
		Assert.assertTrue(20 == messreihe.getZeitintervall());
		Assert.assertTrue(messreihe.getVerbraucher().equals("LED"));
		Assert.assertTrue(messreihe.getMessgroesse().equals("Leistung"));
	}
	
	@Test
	public void Mkl0_Z15_Snull_MaMl(){
		Messreihe messreihe = new Messreihe(-1,20,null,"Arbeit");
	}
	
	@Test
	public void Mkl0_Z15_Sleer_MaMl(){
		Messreihe messreihe = new Messreihe(-1,20,"","Leistung");
		
	}
	
	@Test
	public void Mkl0_Z15_Sverbraucher_MaMl(){
		Messreihe messreihe = new Messreihe(-1,20,"LED","Arbeit");
		
	}
	
	@Test
	public void Mgrgl0_Z15_Snull_MaMl(){
		Messreihe messreihe = new Messreihe(1,20,null,"Leistung");
		
	}
	@Test
	public void Mgrgl0_Z15_Sleer_MaMl(){
		Messreihe messreihe = new Messreihe(1,20,"","Arbeit");
		
	}
	
	@Test
	public void Mgrgl0_Z15_Sverbraucher_MaMl(){
		Messreihe messreihe = new Messreihe(1,20,"LED","Leistung");
		
	}
}
