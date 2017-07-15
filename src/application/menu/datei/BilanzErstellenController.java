/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.menu.datei;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import application.Kontenverwaltung;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import konten.Bestandskonto;
import konten.Erfolgskonto;
import konten.Konto;
import konten.Steuerkonto;

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
	private RadioButton radioAktivkonto;
	@FXML
	private RadioButton radioPassivkonto;
	@FXML
	private RadioButton radioErtragskonto;
	@FXML
	private RadioButton radioAufwandskonto;
	@FXML
	private DatePicker datepickerGJBeginn;
	@FXML
	private TableView<Konto> tableKonto;
	@FXML
	private TableColumn<Konto, String> columnKonto;
	@FXML
	private TableColumn<Konto, String> columnBeschreibung;
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

	private ArrayList<Konto> kontenListe;

	private Kontenverwaltung neueBilanz;

	private boolean bilanzHinzugefuegt;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		bilanzHinzugefuegt = false;
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
		radioBestandskonto.setOnAction(e -> {
			radioAufwandskonto.setDisable(!(radioErfolgskonto.isSelected()));
			radioErtragskonto.setDisable(!(radioErfolgskonto.isSelected()));
			radioAktivkonto.setDisable(!(radioBestandskonto.isSelected()));
			radioPassivkonto.setDisable(!(radioBestandskonto.isSelected()));
		});
		radioErfolgskonto.setOnAction(e -> {
			radioAufwandskonto.setDisable(!(radioErfolgskonto.isSelected()));
			radioErtragskonto.setDisable(!(radioErfolgskonto.isSelected()));
			radioAktivkonto.setDisable(!(radioBestandskonto.isSelected()));
			radioPassivkonto.setDisable(!(radioBestandskonto.isSelected()));
		});
		standardkontenHinzufuegen();
		handle_KontoHinzufuegen(null);
	}

	@FXML
	private void handle_KontoHinzufuegen(ActionEvent event) {
		String fehlermeldung = "";
		if (textfieldKuerzel.getText().length() > 6) {
			fehlermeldung += "Das Kürzel besitzt mehr als 6 Zeichen!";
		}
		if(textfieldKontenname.getText().length() == 0){
			fehlermeldung += "Keinen Kontonamen angegeben!";
		}
		// Fehlerüberprüfung abgeschlossen
		if (fehlermeldung.equals("")) {
			if (radioBestandskonto.isSelected()) {
				Bestandskonto newBKonto = new Bestandskonto(textfieldKontenname.getText(), textfieldKuerzel.getText(),
						"SBK",Double.parseDouble(textfieldAB.getText()), radioAktivkonto.isSelected());
				kontenListe.add(newBKonto);
			} else if (radioErfolgskonto.isSelected()) {
				Erfolgskonto newEKonto = new Erfolgskonto(textfieldKontenname.getText(), textfieldKuerzel.getText(),
						verrechnungskonto.getValue(), radioErtragskonto.isSelected());
				kontenListe.add(newEKonto);
			}
		}
		ObservableList<Konto> obsList = FXCollections.observableArrayList(kontenListe);
		tableKonto.setItems(obsList);
		columnKonto.setCellValueFactory(new PropertyValueFactory<Konto, String>("titel"));
		columnBeschreibung.setCellValueFactory(new PropertyValueFactory<Konto, String>("beschreibung"));
		ArrayList<String> kontenKuerzel = new ArrayList<>();
		kontenKuerzel.add("SBK");
		kontenKuerzel.add("GuV");
		for (Konto konto : kontenListe) {
			kontenKuerzel.add(konto.getKuerzel());
		}
		verrechnungskonto.setItems(FXCollections.observableArrayList(kontenKuerzel));
	}

	@FXML
	private void handleKontoLoeschen(KeyEvent event) {
		// (optinal) TODO: Löschung eines Kontos aus der Tabelle mit ENFT (und
		// optinal mit rechte Maus -> Konto/Konten löschen).
	}

	@FXML
	private void handleBilanzErstellen(ActionEvent event) {
		if (checkboxProduzierendesU.isSelected()) {
			Konto fe = new Bestandskonto("Fertige Erzeugnisse", "FE", "BV", 0, true);
			Konto ue = new Bestandskonto("Unfertige Erzeugnisse", "UE", "BV", 0, true);
			Konto bv = new Bestandskonto("Bestandsveränderungen", "BV", "GuV", 0, true);
			kontenListe.add(fe);
			kontenListe.add(ue);
			kontenListe.add(bv);
		}

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
		neueBilanz = new Kontenverwaltung(new File(STANDARD_PATH + textfieldBilanzname.getText() + ".bil"),
				kontenListe);
		bilanzHinzugefuegt = true;
		Stage stage = (Stage) buttonBilanzErstellen.getScene().getWindow();
		stage.close();
	}

	private void standardkontenHinzufuegen() {
		Konto bank = new Bestandskonto("Bank", "Bank", "SBK", 0, true);
		Konto bga = new Bestandskonto("Büro- und Geschäftsausstattung", "BGA", "SBK", 0, true);
		Konto kasse = new Bestandskonto("Kasse", "Kasse", "SBK", 0, true);
		Konto verb = new Bestandskonto("Verbindlichkeiten a.L.L.", "Verb", "SBK", 0, false);
		Konto dar = new Bestandskonto("Darlehen", "Dar", "SBK", 0, false);
		Konto ek = new Bestandskonto("Eigenkapital", "EK", "SBK", 0, false);
		Konto ford = new Bestandskonto("Forderungen a.L.L.", "Ford", "SBK", 0, true);
		Konto ust = new Steuerkonto("Umsatzsteuer", "USt", "Vorst", 19);
		Konto vorst = new Steuerkonto("Vorsteuer", "Vorst", "USt", 19);
		Konto uerl = new Erfolgskonto("Umsatzerlöse", "UErl", "GuV", true);
		Konto privat = new Erfolgskonto("Privat", "Privat", "EK", true);
		Konto efpz = new Erfolgskonto("Entnahme f. priv. Zwecke", "EfpZ", "Privat", false);
		
		kontenListe.add(bga);
		kontenListe.add(bank);
		kontenListe.add(kasse);
		kontenListe.add(ford);
		kontenListe.add(ek);
		kontenListe.add(verb);
		kontenListe.add(dar);
		kontenListe.add(ust);
		kontenListe.add(vorst);
		kontenListe.add(uerl);
		kontenListe.add(privat);
		kontenListe.add(efpz);
		
		if(checkboxProduzierendesU.isSelected()){
			Konto fe = new Bestandskonto("Fertige Erzeugnisse", "FE", "BV", 0, true);
			Konto ue = new Bestandskonto("Unfertige Erzeugnisse", "UE", "BV", 0, true); 
			Konto bv = new Bestandskonto("Bestandsveränderungen", "BV", "GuV", 0, true); 
			kontenListe.add(fe);
			kontenListe.add(ue);
			kontenListe.add(bv);
		}

	}

	public Kontenverwaltung getNeueBilanz() {
		return neueBilanz;
	}

	public boolean isNeueBilanzErstellt() {
		return bilanzHinzugefuegt;
	}

}
