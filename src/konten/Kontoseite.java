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
	
	private void aktualisieren(){
		
	}
	
	private double getBetragssumme(){
		return 0;
	}
	
}
