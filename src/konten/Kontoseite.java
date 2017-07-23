package konten;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

import geschaeftsfall.Buchungssatz;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Kontoseite implements Serializable{

	private boolean sollseite;
	private HashMap<String, Buchungssatz> buchungen;

	public Kontoseite(boolean sollseite) {
		this.sollseite = sollseite;
		buchungen = new HashMap<>();
	}

	public double getBetragssumme() {
		// Durchlaufen aller Buchungssätze, die auf diese Kontoseite gebucht
		// worden sind.
		Iterator<Buchungssatz> it = buchungen.values().iterator();
		double betrag = 0;
		while (it.hasNext()) {
			Buchungssatz buchung = it.next();
			betrag += buchung.getBetrag();
		}
		return betrag;
	}
	
	public void setBilanzsumme(double betrag){
		
	}
	
	public void setSalidierungsbetrag(String verrechnungskonto, double betrag){
		
	}

	public HashMap<String, Buchungssatz> getBuchungen() {
		return buchungen;
	}

	public void setBuchungen(HashMap<String, Buchungssatz> buchungen) {
		this.buchungen = buchungen;
	}
	
	

}
