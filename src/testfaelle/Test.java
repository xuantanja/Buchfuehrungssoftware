package testfaelle;

import application.GUI;
import application.Kontenverwaltung;
import geschaeftsfall.Buchungssatz;
import geschaeftsfall.Geschaeftsfall;
import javafx.application.Application;
import konten.Bestandskonto;

public class Test {
	
	//GUI startet in einem Thread
	@org.junit.BeforeClass
	public static void beforeClass() throws InterruptedException {
		Thread t = new Thread("JavaFX Testing Thread") {
			public void run() {
				Application.launch(GUI.class, new String[]{});
			}
		};
		t.setDaemon(true);
		t.start();
		Thread.sleep(1000);
	}
	
	/*
	 * Testfall, ob ein Buchungssatz einem Geschäftsfall hinzugefügt wird
	 */
	@org.junit.Test
	public void test_BSHinzufuegen() {
		Kontenverwaltung kv = new Kontenverwaltung();
		
		Bestandskonto a = new Bestandskonto("y", "y", "x", 0, false);
		Bestandskonto b = new Bestandskonto("x", "x", "y", 0, false);
		
		Geschaeftsfall g = new Geschaeftsfall(0, "gs1", "");
		Buchungssatz bsatz = new Buchungssatz("", "a", "b", 7.0);
		kv.addGeschaeftsfall(g);
		kv.addBuchungssatz(g, bsatz);
		
		//Wieviele Bucungssätze sind im ersten Geschäftsfall (g = get(0))?
		int anzahl = kv.getFaelle().get(0).getSaetze().size();
		org.junit.Assert.assertEquals(1, anzahl);
	}

}
