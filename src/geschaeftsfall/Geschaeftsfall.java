package geschaeftsfall;

import java.io.Serializable;
import java.util.ArrayList;

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

	/**
	 * <i><b>Hinzufügen einer Buchung</b></i><br>
	 * <br>
	 * Der Buchungssatz wird zu einem Geschäftsfall zugeordnet.  <br>
	 * 
	 * @param bsatz
	 *            - der Buchungssatz, der gebucht werden soll
	 */
	public void addBuchung(Buchungssatz bsatz) {
		bsatz.setID_B(ID + "." + (char) (97 + anzahlBuchungen));
		saetze.add(bsatz);
		anzahlBuchungen++;
	}

	/**
	 * <i><b>Hinzufügen mehrerer Buchungssätze</b></i><br>
	 * <br>
	 * Bucht den Buchungssatz auf die entsprechende Seite des Kontos. <br>
	 * 
	 * @param bsList
	 *            - die Buchungssätze, die gebucht werden sollen
	 */
	public void addMultipleBuchung(ArrayList<Buchungssatz> bsList) {
		for (int i = 0; i < bsList.size(); i++) {
			Buchungssatz bsatz = bsList.get(i);
			bsatz.setID_B(ID + "." + (char) (97 + anzahlBuchungen));
			saetze.add(bsatz);
		}
		anzahlBuchungen++;
	}

}
