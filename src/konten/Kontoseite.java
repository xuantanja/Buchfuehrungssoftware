package konten;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import geschaeftsfall.Buchungssatz;
import utility.map.IDMap;

public class Kontoseite implements Serializable{

	private boolean sollseite;
	private IDMap<String, Buchungssatz> buchungen;

	public Kontoseite(boolean sollseite) {
		this.sollseite = sollseite;
		buchungen = new IDMap<>();
	}

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
	
	public void setBilanzsumme(double betrag){
		
	}
	
	public void setSalidierungsbetrag(String verrechnungskonto, double betrag){
		
	}

	public IDMap<String, Buchungssatz> getBuchungen() {
		return buchungen;
	}
	
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
