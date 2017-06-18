package application;

import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

import geschaeftsfall.*;
import konten.*;

public class Kontenverwaltung {

	private HashMap<String, Konto> konten;
	private ArrayList<Geschaeftsfall> faelle;

	public Kontenverwaltung() {
		faelle = new ArrayList<>();
		konten = new HashMap<>();

	}

	// Konto wird der HashMap hinzugefügt
	public void addKonto(Konto myKonto) {
		konten.put(myKonto.getTitel(), myKonto);
	}
	//Geschäftsfälle werden der ArrayList hinzugefügt
	public void addGeschaeftsfall(Geschaeftsfall myGFall) {
		faelle.add(myGFall);
	}
	//dem Geschäftsfall wird ein Buchungssatz hinzugefügt	
	public void addBuchungssatz(Geschaeftsfall gfall, Buchungssatz bsatz) {
		gfall.addBuchung(bsatz);
		konten.get(bsatz.getSollKonto()).buchung(bsatz, true);
		konten.get(bsatz.getHabenKonto()).buchung(bsatz, false);
	}

	public void removeGeschaeftsfall() {

	}

	public void removeBuchungssatz() {

	}
	
	public void kontensaldierung(){
		Iterator<Konto> it = konten.values().iterator();
		while (it.hasNext()) {
			Konto konto = it.next();
			konto.saldieren();
		}
	}

}
