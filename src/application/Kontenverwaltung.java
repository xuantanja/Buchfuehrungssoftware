package application;

import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import geschaeftsfall.*;
import konten.*;
import utility.comparator.KontoComparator;

public class Kontenverwaltung {

	private HashMap<String, Konto> konten;
	private ArrayList<Geschaeftsfall> faelle;
	private File speicherort;
	private LocalDate geschaeftsjahrBeginn;

	public Kontenverwaltung() {
		faelle = new ArrayList<>();
		konten = new HashMap<>();
	}

	public Kontenverwaltung(File file, ArrayList<Konto> kontenListe, LocalDate gfBeginn) {
		speicherort = file;
		faelle = new ArrayList<>();
		konten = new HashMap<>();
		geschaeftsjahrBeginn = gfBeginn;
		for (Konto konto : kontenListe) {
			konten.put(konto.getKuerzel(), konto);
		}
	}

	public Kontenverwaltung(File file, HashMap<String, Konto> kontenListe, ArrayList<Geschaeftsfall> faelle,
			LocalDate gfBeginn) {
		speicherort = file;
		this.faelle = new ArrayList<>(faelle);
		konten = new HashMap<>(kontenListe);
		geschaeftsjahrBeginn = gfBeginn;
	}

	// Konto wird der HashMap hinzugefügt
	public void addKonto(Konto myKonto) {
		konten.put(myKonto.getKuerzel(), myKonto);
	}

	// Geschäftsfälle werden der ArrayList hinzugefügt
	public void addGeschaeftsfall(Geschaeftsfall myGFall) {
		faelle.add(myGFall);
	}

	// dem Geschäftsfall wird ein Buchungssatz hinzugefügt
	public void addBuchungssatz(Geschaeftsfall gfall, Buchungssatz bsatz) {
		gfall.addBuchung(bsatz);
		if (bsatz.getSollKonto() != null && konten.get(bsatz.getSollKonto()) != null) {
			konten.get(bsatz.getSollKonto()).buchung(bsatz, true);
		}
		if (bsatz.getHabenKonto() != null && konten.get(bsatz.getHabenKonto()) != null) {
			konten.get(bsatz.getHabenKonto()).buchung(bsatz, false);
		}

	}

	public void addBuchungssatz(Geschaeftsfall gfall, ArrayList<Buchungssatz> bsatz) {
		gfall.addMultipleBuchung(bsatz);
		for (Buchungssatz b : bsatz) {
			konten.get(b.getSollKonto()).buchung(b, true);
			konten.get(b.getHabenKonto()).buchung(b, false);
		}

	}

	public void kontensaldierung() {

		for (Iterator<Konto> iterator = konten.values().iterator(); iterator.hasNext();) {
			System.out.println(iterator.next().getTitel());
		}
		System.out.println("-----");

		PriorityQueue<Konto> pqk = new PriorityQueue<>(new KontoComparator());
		pqk.addAll(konten.values());
		Konto[] steuerkonten = new Konto[2];
		int i = 0;
		Geschaeftsfall jahresabschluss = new Geschaeftsfall(faelle.size()+1, "Jahresabschluss",
				"Alle Buchungssätze, die zum Abschluss des Geschäftsjahres automatisch gebucht worden sind.");
		addGeschaeftsfall(jahresabschluss);
		while (!pqk.isEmpty()) {
			Konto konto = pqk.poll();
			Buchungssatz bs = konto.saldieren();
			if (konto.getKontoart() != 3) {
				if (bs != null) {
					bs.setID_B(" ");
					addBuchungssatz(jahresabschluss, bs);
				}
			} else {
				steuerkonten[i++] = konto;
			}
		}
		steuerkontenSaldierung(jahresabschluss, steuerkonten);
		for (Buchungssatz bsatz : jahresabschluss.getSaetze()) {
			bsatz.setID_B("");
		}
	}

	private void steuerkontenSaldierung(Geschaeftsfall jahresabschluss, Konto[] steuerkonten) {
		Buchungssatz saldierung, sbkBuchung;
		Konto konto1 = null, konto2 = null;

		if (steuerkonten[0].getBilanzwert() > steuerkonten[1].getBilanzwert()) {
			konto1 = steuerkonten[0];
			konto2 = steuerkonten[1];
		} else if (steuerkonten[0].getBilanzwert() < steuerkonten[1].getBilanzwert()) {
			konto1 = steuerkonten[1];
			konto2 = steuerkonten[0];
		}
		if (konto1 != null && konto2 != null) {
			saldierung = konto2.saldieren();
			if (saldierung != null) {
				if (saldierung.getSollKonto().equals("SBK")) {
					saldierung.setSollKonto(konto1.getKuerzel());
				} else {
					saldierung.setHabenKonto(konto1.getKuerzel());
				}
				addBuchungssatz(jahresabschluss, saldierung);
			}
			sbkBuchung = konto1.saldieren();
			addBuchungssatz(jahresabschluss, sbkBuchung);
		}

	}

	public Iterator<Konto> getKontenIterator() {
		return konten.values().iterator();
	}
	
	public ArrayList<Konto> getKontenArraylist() {
		return new ArrayList<>(konten.values());
	}

	public HashMap<String, Konto> getKonten() {
		return konten;
	}

	public ArrayList<Geschaeftsfall> getFaelle() {
		return faelle;
	}

	public File getSpeicherort() {
		return speicherort;
	}

	public LocalDate getGeschaeftsjahrBeginn() {
		return geschaeftsjahrBeginn;
	}
	
	public boolean isAlleErfolgskontenMitBilanzwertNull() {
		boolean alleNull = true;
		for(Konto konto : konten.values()) {
			if(konto.getKontoart() == 2 && konto.getBilanzwert() != 0) {
				alleNull = false;
			}
		}
		return alleNull;
	}
}
