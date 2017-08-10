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

	public void removeGeschaeftsfall() {

	}

	public void removeBuchungssatz() {

	}

	public void kontensaldierung() {
		
		for (Iterator<Konto> iterator = konten.values().iterator(); iterator.hasNext();) {
			System.out.println(iterator.next().getTitel());
		}
		System.out.println("-----");
		
		PriorityQueue<Konto> pqk = new PriorityQueue<>(new KontoComparator());
		pqk.addAll(konten.values());
		Geschaeftsfall jahresabschluss = new Geschaeftsfall(faelle.size(), "Jahresabschluss",
				"Alle Buchungssätze, die zum Abschluss des Geschäftsjahres automatisch gebucht worden sind.");
		addGeschaeftsfall(jahresabschluss);
		while (!pqk.isEmpty()) {
			Konto konto = pqk.poll();
				Buchungssatz bs = konto.saldieren();
				if (bs != null) {
					bs.setID_B(" ");
					addBuchungssatz(jahresabschluss, bs);
				}
		}
		for (Buchungssatz bsatz : jahresabschluss.getSaetze()) {
			bsatz.setID_B("");
		}
	}

	public Iterator<Konto> getKontenIterator() {
		return konten.values().iterator();
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
}
