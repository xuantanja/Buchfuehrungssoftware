package application.menu.datei;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * ABController dient zur Ausgabe und Änderung der Anfangsbestände.
 */
public class ABController implements Initializable {
	@FXML
	private TextField textfieldBank;
	@FXML
	private TextField textfieldKasse;
	@FXML
	private TextField textfieldVerb;
	@FXML
	private TextField textfieldFord;
	@FXML
	private TextField textfieldDar;
	@FXML
	private TextField textfieldBGA;
	@FXML
	private TextField textfieldEK;
	@FXML
	private Button buttonConfirm;

	/**
	 * <i><b>Ereignisbehandlung: bei Bestätigung von Eingaben</b></i><br>
	 * <br>
	 * Schließen des Fensters bei Button-Event zum Bestätigen.
	 * Event Listener on Button[#buttonConfirm].onAction <br>
	 * 
	 * @param event
	 * 			- Nutzeraktion
	 */
	@FXML
	public void handleConfirmButton(ActionEvent event) {
		((Stage) buttonConfirm.getScene().getWindow()).close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO
		
	}

	public double getTextfieldBankValue() {
		return Double.parseDouble(textfieldBank.getText());
	}

	public double getTextfieldKasseValue() {
		return Double.parseDouble(textfieldKasse.getText());
	}

	public double getTextfieldVerbValue() {
		return Double.parseDouble(textfieldVerb.getText());
	}

	public double getTextfieldFordValue() {
		return Double.parseDouble(textfieldFord.getText());
	}

	public double getTextfieldDarValue() {
		return Double.parseDouble(textfieldDar.getText());
	}

	public double getTextfieldBGAValue() {
		return Double.parseDouble(textfieldBGA.getText());
	}

	public double getTextfieldEKValue() {
		return Double.parseDouble(textfieldEK.getText());
	}

	public void setTextfieldBankValue(double value) {
		this.textfieldBank.setText(value + "");
	}

	public void setTextfieldKasseValue(double value) {
		this.textfieldKasse.setText(value + "");
	}

	public void setTextfieldVerbValue(double value) {
		this.textfieldVerb.setText(value + "");
	}

	public void setTextfieldFordValue(double value) {
		this.textfieldFord.setText(value + "");
	}

	public void setTextfieldDarValue(double value) {
		this.textfieldDar.setText(value + "");
	}

	public void setTextfieldBGAValue(double value) {
		this.textfieldBGA.setText(value + "");
	}

	public void setTextfieldEKValue(double value) {
		this.textfieldEK.setText(value + "");
	}
	
	
	
}
