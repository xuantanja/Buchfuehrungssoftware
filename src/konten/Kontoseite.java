package konten;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import geschaeftsfall.Buchungssatz;
import utility.Collection.IDMap;
/**
 * 
 * Die Klasse Kontoseite ist für die beiden Seiten (Soll-und Haben) eines Kontos zuständig.
 *
 */
public class Kontoseite implements Serializable{

	private boolean sollseite;
	private IDMap<String, Buchungssatz> buchungen;

	public Kontoseite(boolean sollseite) {
		this.sollseite = sollseite;
		buchungen = new IDMap<>();
	}
	
	/**
	 * <i><b>Berechnung der Summe einer Kontoseite</b></i><br>
	 * <br>
	 * Rechnet alle Werte einer Kontoseite zu einem Wert zusammen. <br>
	 *  
	 * @return aufsummierter Wert der Beträge einer Kontoseite
	 */
	public double getBetragssumme() {
		// Durchlaufen aller Buchungssätze, die auf diese Kontoseite gebucht
		// worden sind.
		Iterator<ArrayList<Buchungssatz>> it = buchungen.values().iterator();
		double betrag = 0;
		while (it.hasNext()) {
			ArrayList<Buchungssatz> buchung = it.next();
			for (Buchungssatz bsatz : buchung) {
				betrag += bsatz.getBetrag();
			}
		}
		return betrag;
	}
	

	public IDMap<String, Buchungssatz> getBuchungen() {
		return buchungen;
	}
	
	/**
	 * <i><b>Umwandlung in ArrayList vom Typ Buchungssatz</b></i><br>
	 * <br>
	 * Es wird eine ArrayList mit allen Buchungssätzen erstellt <br>
	 *
	 * @return alle Buchungssätze
	 */
	public ArrayList<Buchungssatz> getArrayOfBuchungen() {
		ArrayList<Buchungssatz> bs = new ArrayList<>();
		for (Iterator<ArrayList<Buchungssatz>> iterator = buchungen.values().iterator(); iterator.hasNext();) {
			bs.addAll(iterator.next());
		}
		return bs;
	}

	public void setBuchungen(IDMap<String, Buchungssatz> buchungen) {
		this.buchungen = buchungen;
	}
	
	

}
