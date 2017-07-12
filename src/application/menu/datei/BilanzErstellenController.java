/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.menu.datei;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Kontenverwaltung;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import konten.Bestandskonto;
import konten.Erfolgskonto;
import konten.Konto;

/**
 * FXML Controller class
 *
 * @author mtukendo
 */
public class BilanzErstellenController implements Initializable {

	private static final String STANDARD_PATH = System.getProperty("Appdata") + "\\BuFü-HWRVersion\\";
	@FXML
	private TextField textfieldBilanzname;
	@FXML
	private TextField textfieldSteuersatz;
	@FXML
	private TextField textfieldKontenname;
	@FXML
	private TextField textfieldKuerzel;
	@FXML
	private Button buttonAddKonto;
	@FXML
	private TextField textfieldAB;
	@FXML
	private DatePicker datepickerGJBeginn;
	@FXML
	private TableView<?> tableKonto;
	@FXML
	private TableColumn<?, ?> columnKonto;
	@FXML
	private TableColumn<?, ?> columnBeschreibung;
	@FXML
	private RadioButton radioBestandskonto;
	@FXML
	private RadioButton radioErfolgskonto;
	@FXML
	private Button buttonBilanzErstellen;
	@FXML
	private CheckBox checkboxProduzierendesU;
	@FXML
	private ComboBox<String> verrechnungskonto;
	@FXML
	private RadioButton radioAktivkonto;
	@FXML
	private RadioButton radioPassivkonto;
	@FXML
	private RadioButton radioErtragskonto;
	@FXML
	private RadioButton radioAufwandskonto;

	private ArrayList<Konto> kontenListe;
	
	private Kontenverwaltung neueBilanz;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		kontenListe = new ArrayList<>();
		ToggleGroup group1 = new ToggleGroup();
		ToggleGroup group2 = new ToggleGroup();
		ToggleGroup group3 = new ToggleGroup();

		radioBestandskonto.setToggleGroup(group1);
		radioErfolgskonto.setToggleGroup(group1);
		radioErtragskonto.setToggleGroup(group2);
		radioAufwandskonto.setToggleGroup(group2);
		radioAktivkonto.setToggleGroup(group3);
		radioPassivkonto.setToggleGroup(group3);

	}

	@FXML
	private void handle_KontoHinzufuegen(ActionEvent event) {
		String fehlermeldung = "";
		if (textfieldKuerzel.getText().length() > 6) {
			fehlermeldung += "Das Kürzel besitzt mehr als 6 Zeichen!";
		}
		double ab = 0;
		try {
			ab = Double.parseDouble(textfieldSteuersatz.getText());
			if (ab < 0 || ab > 100) {
				throw new Exception();
			}
		} catch (Exception e) {
			fehlermeldung += "Der angegebene Steuersatz ist keine gültige Zahl!";
		}
		// Fehlerüberprüfung abgeschlossen
		if (fehlermeldung.equals("")) {
			if (radioBestandskonto.isSelected()) {
				Bestandskonto newBKonto = new Bestandskonto(textfieldKontenname.getText(), textfieldKuerzel.getText(),
						"SBK", 1, ab, radioAktivkonto.isSelected());
				kontenListe.add(newBKonto);
			} else if (radioErfolgskonto.isSelected()) {
				Erfolgskonto newEKonto = new Erfolgskonto(textfieldKontenname.getText(), textfieldKuerzel.getText(),
						verrechnungskonto.getPromptText(), 1, radioErtragskonto.isSelected());
				kontenListe.add(newEKonto);
			}
		}
	}

	@FXML
	private void handleKontoLoeschen(KeyEvent event) {
	}

	@FXML
	private void handleBilanzErstellen(ActionEvent event) {
		String fehlermeldung = "";
		double steuersatz;
		try {
			steuersatz = Double.parseDouble(textfieldSteuersatz.getText());
			if (steuersatz < 0 || steuersatz > 100) {
				throw new Exception();
			}
		} catch (Exception e) {
			fehlermeldung += "Der angegebene Steuersatz ist keine gültige Zahl!";
		}
		// Fehlerüberprüfung abgeschlossen
		neueBilanz = new Kontenverwaltung(new File(STANDARD_PATH + textfieldBilanzname.getText() + ".bil"), kontenListe);
		
	}

	public Kontenverwaltung getNeueBilanz() {
		return neueBilanz;
	}
	
	
	
	
}
