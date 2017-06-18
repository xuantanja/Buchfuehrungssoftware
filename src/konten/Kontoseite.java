package konten;

import java.util.HashMap;

import geschaeftsfall.Buchungssatz;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Kontoseite {
	
	private boolean sollseite;
	private HBox container;
	private VBox refName;
	private VBox refBetrag;
	private HashMap<String, Buchungssatz> buchungen;
	// String steht doch hier für Kontotitel. Es könnnen doch für ein Konto mehrere Buchungssätze existieren?! Warum dann schlüssel?
	
	private void aktualisieren(){
	
	}
	
	//zB buchungen.getBetragssumme(..
	private double getBetragssumme(Konto myKonto){
		//String titel = myKonto.getTitel();
		//buchungen.get(titel).getBetrag();
		return 0;
	}
	
}
