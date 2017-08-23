package testfaelle;

import static org.junit.Assert.*;

import application.Kontenverwaltung;

public class Test {

	@org.junit.Test
	public void test() {
		Kontenverwaltung kv = new Kontenverwaltung();
		//kv.addBuchungssatz(gfall, bsatz);
		
		int anzahl = kv.getFaelle().get(0).getSaetze().size();
		org.junit.Assert.assertEquals(1, anzahl);
	}

}
