package application;

import java.util.HashMap;
import java.util.ArrayList;

import geschaeftsfall.*;
import konten.*;

public class Kontenverwaltung {

	private HashMap<String, Konto> konten;
	private ArrayList<Geschaeftsfall> faelle;

	public Kontenverwaltung() {

	}

	// Konto wird der HashMap hinzugefügt
	private void addKonto(Konto myKonto) {
		String titel = myKonto.getTitel();
		konten.put(titel, myKonto);
	}
	//Geschäftsfälle werden der ArrayList hinzugefügt
	public void addGeschaeftsfall(Geschaeftsfall myGFall) {
		faelle.add(myGFall);
	}
	//dem Geschäftsfall wird ein Buchungssatz hinzugefügt	
	public void addBuchungssatz(Geschaeftsfall gfall, Buchungssatz bsatz) {
		gfall.getSaetze().add(bsatz);
	}

	public void removeGeschaeftsfall() {

	}

	public void removeBuchungssatz() {

	}

}
