package geschaeftsfall;

import java.io.Serializable;
import java.util.ArrayList;

public class Geschaeftsfall implements Serializable{

	private long ID;
	private String titel;
	private String beschreibung;
	private ArrayList<Buchungssatz> saetze;

	public Geschaeftsfall(long iD, String titel, String beschreibung) {
		ID = iD;
		this.titel = titel;
		this.beschreibung = beschreibung;
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
		bsatz.setID_B(ID + "." + (char) (97 + saetze.size()));
		saetze.add(bsatz);
	}

}
