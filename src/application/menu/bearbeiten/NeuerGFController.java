package application.menu.bearbeiten;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import geschaeftsfall.Geschaeftsfall;
import javafx.event.ActionEvent;

import javafx.scene.control.TextArea;

public class NeuerGFController {
	@FXML
	private TextField textfieldTitel;
	@FXML
	private TextArea textfieldBeschreibung;
	@FXML
	private Button buttonHinzufügen;
	@FXML
	private Button buttonSchliessen;

	// Event Listener on Button[#buttonHinzufügen].onAction
	@FXML
	public void handleHinzufügen(ActionEvent event) {
		((Stage) buttonHinzufügen.getScene().getWindow()).close();
	}
	
	// Event Listener on Button[#buttonSchliessen].onAction
	@FXML
	public void handleSchließen(ActionEvent event) {
		((Stage) buttonSchliessen.getScene().getWindow()).close();
	}
	
	public Geschaeftsfall getGeschaeftsfall(int ID){
		return new Geschaeftsfall(ID, textfieldTitel.getText(), textfieldBeschreibung.getText());
	}
}
