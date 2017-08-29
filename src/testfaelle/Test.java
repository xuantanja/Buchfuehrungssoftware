package testfaelle;

import java.util.ArrayList;

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
	 * Testfall, ob ein Konto hinzugefügt wird
	 */
	@org.junit.Test
	public void test_Konto() {
		Kontenverwaltung kv = new Kontenverwaltung();
		
		Bestandskonto a = new Bestandskonto("y", "y", "x", 0, false);
		Bestandskonto b = new Bestandskonto("x", "x", "y", 0, false);
		
		kv.addKonto(b);
		kv.addKonto(a);
		
		//Wieviele Konten gibt es?
		int anzahl = kv.getKontenArraylist().size();
		//Erwartetes Ergebnis: zwei Werte
		org.junit.Assert.assertEquals(2, anzahl);
	}
	/*
	 * Testfall, ob ein Geschäftsfall hinzugefügt wird
	 */
	@org.junit.Test
	public void test_Geschaeftsfall() {
		Kontenverwaltung kv = new Kontenverwaltung();
		
		Geschaeftsfall g = new Geschaeftsfall(0, "gs1", "");
		kv.addGeschaeftsfall(g);
		
		//Wieviele Geschäftsfalle gibt es?
		int anzahl = kv.getFaelle().size();
		//Erwartetes Ergebnis: ein Wert
		org.junit.Assert.assertEquals(1, anzahl);
	}
	/*
	 * Testfall, ob ein Buchungssatz einem Geschäftsfall hinzugefügt wird
	 */
	@org.junit.Test
	public void test_einfacheBuchung() {
		Kontenverwaltung kv = new Kontenverwaltung();
		
		Bestandskonto a = new Bestandskonto("y", "y", "x", 0, false);
		Bestandskonto b = new Bestandskonto("x", "x", "y", 0, false);
		kv.addKonto(a);
		kv.addKonto(b);
		
		Geschaeftsfall g = new Geschaeftsfall(0, "gs1", "");
		Buchungssatz bsatz = new Buchungssatz("", "x", "y", 7.0);
		kv.addGeschaeftsfall(g);
		//ein Buchungssatz wird dem Geschäftfall g zugeordnet
		kv.addBuchungssatz(g, bsatz);
		
		//Wieviele Buchungssätze sind im ersten Geschäftsfall (g = get(0))?
		int anzahl = kv.getFaelle().get(0).getSaetze().size();
		//Erwartetes Ergebnis: ein Wert
		org.junit.Assert.assertEquals(1, anzahl);
		
	}
	
	/*
	 * Testfall, ob mehrere Buchungssatz einem Geschäftsfall hinzugefügt werden
	 */
	@org.junit.Test
	public void test_multipleBuchung() {
		Kontenverwaltung kv = new Kontenverwaltung();
		
		Bestandskonto c = new Bestandskonto("y", "y", "x", 0, true);
		Bestandskonto d = new Bestandskonto("x", "x", "y", 0, false);

		Geschaeftsfall f = new Geschaeftsfall(0, "gs1", "");
		Buchungssatz bsatz1 = new Buchungssatz("", "x", "y", 7.0);
		Buchungssatz bsatz2 = new Buchungssatz("", "y", "x", 2.0);

		ArrayList<Buchungssatz> bsList = new ArrayList<Buchungssatz>();
		bsList.add(bsatz1);
		bsList.add(bsatz2);
		
		kv.addKonto(c);
		kv.addKonto(d);
		kv.addGeschaeftsfall(f);
		//ein Buchungssatz wird dem Geschäftfall g zugeordnet
		kv.addBuchungssatz(f, bsList);
		
		//Wieviele Buchungssätze sind im ersten Geschäftsfall (f = get(0))?
		int anzahl = kv.getFaelle().get(0).getSaetze().size();
		//Erwartetes Ergebnis: zwei Werte
		org.junit.Assert.assertEquals(2, anzahl);
		
	}

}
