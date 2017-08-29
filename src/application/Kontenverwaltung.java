package application;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import geschaeftsfall.Buchungssatz;
import geschaeftsfall.Geschaeftsfall;
import konten.Konto;

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
	/**
	 * <i><b>Hinzufügen eines Kontos</b></i><br>
	 * <br>
	 * Ein Konto wird der Kontenverwaltung hinzugefügt.  <br>
	 * 
	 * @param myKonto
	 *            - das Konto, welches hinzugefügt werden sollen
	 */
	public void addKonto(Konto myKonto) {
		konten.put(myKonto.getKuerzel(), myKonto);
	}

	/**
	 * <i><b>Hinzufügen eines Geschäftsfalles</b></i><br>
	 * <br>
	 * Ein Geschäftsfall wird der Kontenverwaltung hinzugefügt.  <br>
	 * 
	 * @param myGFall
	 *            - der Geschäftsfall, der hinzugefügt werden sollen
	 */
	public void addGeschaeftsfall(Geschaeftsfall myGFall) {
		faelle.add(myGFall);
	}

	/**
	 * <i><b>Hinzufügen eines Buchungssatzes</b></i><br>
	 * <br>
	 * Der Buchungssatz wird dem Geschäftsfall der Kontenverwaltung hinzugefügt und gebucht.  <br>
	 * 
	 * @param bsatz
	 *            - der Buchungssatz, der gebucht werden sollen
	 * @param gfall
	 *            - der Geschäftsfall, auf den der Buchungssatz hinzugefügt werden
	 */
	public void addBuchungssatz(Geschaeftsfall gfall, Buchungssatz bsatz) {
		gfall.addBuchung(bsatz);
		if (bsatz.getSollKonto() != null && konten.get(bsatz.getSollKonto()) != null) {
			konten.get(bsatz.getSollKonto()).buchung(bsatz, true);
		}
		if (bsatz.getHabenKonto() != null && konten.get(bsatz.getHabenKonto()) != null) {
			konten.get(bsatz.getHabenKonto()).buchung(bsatz, false);
		}

	}
	/**
	 * <i><b>Hinzufügen mehrerer Buchungssätze</b></i><br>
	 * <br>
	 * Die Buchungssätze werden dem Geschäftsfall der Kontenverwaltung hinzugefügt und gebucht.  <br>
	 * 
	 * @param bsatz
	 *            - die Buchungssätze, die gebucht werden sollen
	 * @param gfall
	 *            - der Geschäftsfall, auf den die Buchungssätze hinzugefügt werden
	 */
	public void addBuchungssatz(Geschaeftsfall gfall, ArrayList<Buchungssatz> bsatz) {
		gfall.addMultipleBuchung(bsatz);
		for (Buchungssatz b : bsatz) {
			konten.get(b.getSollKonto()).buchung(b, true);
			konten.get(b.getHabenKonto()).buchung(b, false);
		}

	}
	
	/**
	 * <i><b>Saldierung aller Konten</b></i><br>
	 * <br>
	 * Die vorhandenen Konten in der Kontenverwaltung werden einer Reihenfolge nach saldiert. <br>
	 * 
	 */
	public void kontensaldierung() {
		LinkedList<Konto> sortedKontoList = new LinkedList<Konto>();
		int pos = 0;
		for (Konto konto : konten.values()) {
			for (int j = 0; j < sortedKontoList.size(); j++) {
				if (konto.getVerrechnungKonto().equals(sortedKontoList.get(j).getKuerzel())) {
					pos = j;
				}
			}
			if(pos == -1){
				pos = sortedKontoList.size();
			}
			System.out.println("[KONTENVERWALTUNG] "+ pos + " = " + konto.getTitel());
			if(!konto.getKuerzel().equals("SBK")){
			sortedKontoList.add(pos, konto);	
			}
			
			pos = -1;
		}

		Konto[] steuerkonten = new Konto[2];
		int i = 0;
		Geschaeftsfall jahresabschluss = new Geschaeftsfall(faelle.size() + 1, "Jahresabschluss",
				"Alle Buchungssätze, die zum Abschluss des Geschäftsjahres automatisch gebucht worden sind.");
		addGeschaeftsfall(jahresabschluss);
		while (!sortedKontoList.isEmpty()) {
			Konto konto = sortedKontoList.poll();
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
		konten.get("SBK").saldieren();
		for (Buchungssatz bsatz : jahresabschluss.getSaetze()) {
			bsatz.setID_B("");
		}
	}
	/**
	 * <i><b>Saldierung von Steuerkonten</b></i><br>
	 * <br>
	 * Die Steuerkonten werden miteinander je nach Größe des Bilanzwertes verbucht. <br>
	 * 
	 * @param jahresabschluss
	 *            - der Geschäftsfall, der bei der einem Jahresabschluss erstellt wird und auf den die Saldierung gebucht wird
	 * @param steuerkonten
	 *            - beinhaltet die beiden Steuerkonten Vorsteuer und Umsatzsteuer
	 */
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
		for (Konto konto : konten.values()) {
			if (konto.getKontoart() == 2 && konto.getBilanzwert() != 0) {
				alleNull = false;
			}
		}
		return alleNull;
	}
}
