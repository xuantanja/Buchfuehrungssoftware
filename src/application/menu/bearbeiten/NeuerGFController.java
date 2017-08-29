package application.menu.bearbeiten;

import java.net.URL;
import java.util.ResourceBundle;

import geschaeftsfall.Geschaeftsfall;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NeuerGFController implements Initializable {
	private boolean closeButtonPressed;
	@FXML
	private TextField textfieldTitel;
	@FXML
	private TextArea textfieldBeschreibung;
	@FXML
	private Button buttonHinzufügen;
	@FXML
	private Button buttonSchliessen;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		closeButtonPressed = false;
	}
	/**
	 * <i><b>Ereignisbehandlung: bei Hinzufügen Geschäftsfall</b></i><br>
	 * <br>
	 * Schließen des Fensters bei Button-Event zum Hinzufügen..
	 * Event Listener on Button[#buttonHinzufügen].onAction <br>
	 * 
	 * @param event
	 * 			- Nutzeraktion
	 */
	@FXML
	public void handleHinzufügen(ActionEvent event) {
		((Stage) buttonHinzufügen.getScene().getWindow()).close();
	}
	/**
	 * <i><b>Ereignisbehandlung: Fenster Schließen</b></i><br>
	 * <br>
	 * Schließen des Fensters bei Button-Event zum Schließen.
	 * Event Listener on Button[#buttonSchliessen].onAction <br>
	 * 
	 * @param event
	 * 			- Nutzeraktion
	 */
	@FXML
	public void handleSchließen(ActionEvent event) {
		closeButtonPressed = true;
		((Stage) buttonSchliessen.getScene().getWindow()).close();
	}

	/**
	 * <i><b>Erstellen eines neuen Geschäftsfalles</b></i><br>
	 * <br>
	 * Mit der übergebenen ID und den Nutzereingaben wird ein neuer Geschäftsfall erstellt. <br>
	 * 
	 * @param ID
	 * 			- die ID, die der Geschäftsfall erhält
	 * @return ein neuer Geschäftfall
	 */
	public Geschaeftsfall getGeschaeftsfall(int ID) {
		return new Geschaeftsfall(ID, textfieldTitel.getText(), textfieldBeschreibung.getText());
	}

	public boolean isCloseButtonPressed() {
		return closeButtonPressed;
	}
}
