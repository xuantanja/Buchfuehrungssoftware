package konten;

import java.util.HashMap;
import java.util.Iterator;

import geschaeftsfall.Buchungssatz;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Kontoseite {

	private boolean sollseite;
	private HBox container;
	private VBox refName;
	private VBox refBetrag;
	private HashMap<String, Buchungssatz> buchungen;

	public Kontoseite(boolean sollseite) {
		this.sollseite = sollseite;
		container = new HBox();
		refBetrag = new VBox();
		refName = new VBox();
		container.getChildren().addAll(refName, refBetrag);
		buchungen = new HashMap<>();
	}

	public void aktualisieren() {
		refName.getChildren().clear();
		refBetrag.getChildren().clear();

		// Durchlaufen aller Buchungssätze, die auf diese Kontoseite gebucht
		// worden sind.
		Iterator<Buchungssatz> it = buchungen.values().iterator();
		while (it.hasNext()) {
			Buchungssatz buchung = it.next();
			// Hinzufügen des Buchungssatzes zu den Komponenten der GUI
			if (sollseite) {
				refName.getChildren().add(new Label(buchung.getID() + " " + buchung.getHabenKonto()));
			} else {
				refName.getChildren().add(new Label(buchung.getID() + " " + buchung.getSollKonto()));
			}
			refBetrag.getChildren().add(new Label(Double.toString(buchung.getBetrag())));
		}
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
		refBetrag.getChildren().add(new Label(Double.toString(betrag)));
	}
	
	public void setSalidierungsbetrag(String verrechnungskonto, double betrag){
		refName.getChildren().add(new Label(verrechnungskonto));
		refBetrag.getChildren().add(new Label(Double.toString(betrag)));
	}

	//Falls wird die methode noch brauchen
//	public void addBuchungssatz(Buchungssatz bsatz) {
//		buchungen.put(bsatz.getID(), bsatz);
//		aktualisieren();
//	}

	public HashMap<String, Buchungssatz> getBuchungen() {
		return buchungen;
	}

	public void setBuchungen(HashMap<String, Buchungssatz> buchungen) {
		this.buchungen = buchungen;
	}
	
	

}
