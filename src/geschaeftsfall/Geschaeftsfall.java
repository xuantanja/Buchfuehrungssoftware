package geschaeftsfall;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Geschaeftsfall implements Serializable {

	private long ID;
	private String titel;
	private String beschreibung;
	private ArrayList<Buchungssatz> saetze;
	private int anzahlBuchungen;

	public Geschaeftsfall(long iD, String titel, String beschreibung) {
		ID = iD;
		this.titel = titel;
		this.beschreibung = beschreibung;
		anzahlBuchungen = 0;
		saetze = new ArrayList<>();
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public ArrayList<Buchungssatz> getSaetze() {
		return saetze;
	}

	public void addBuchung(Buchungssatz bsatz) {
		bsatz.setID_B(ID + "." + (char) (97 + anzahlBuchungen) + "." + 1);
		saetze.add(bsatz);
		anzahlBuchungen++;
	}

	public void addMultipleBuchung(ArrayList<Buchungssatz> bsList) {
		for (int i = 0; i < bsList.size(); i++) {
			Buchungssatz bsatz = bsList.get(i);
			bsatz.setID_B(ID + "." + (char) (97 + saetze.size()) + "." + (i+1));
			saetze.add(bsatz);
		}
		anzahlBuchungen++;
	}

}
