/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.menu.datei;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Kontenverwaltung;
import io.IOManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import konten.Abschlusskonto;
import konten.Bestandskonto;
import konten.Erfolgskonto;
import konten.Konto;
import konten.Steuerkonto;
import utility.alertDialog.AlertDialogFrame;

/**
 * FXML Controller class
 *
 * @author mtukendo
 */
public class BilanzErstellenController implements Initializable {

	private static final String STANDARD_PATH = System.getProperty("user.home")
			+ "\\AppData\\Roaming\\BuFü-HWRVersion\\";
	@FXML
	private TextField textfieldBilanzname;
	@FXML
	private TextField textfieldKontenname;
	@FXML
	private TextField textfieldKuerzel;
	@FXML
	private Button buttonAddKonto;
	@FXML
	private Button buttonAB;
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

	private Bestandskonto bank, kasse, verb, ford, dar, bga, ek;

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

		ContextMenu contextMenu = new ContextMenu();
		MenuItem item1 = new MenuItem("Bearbeiten");
		MenuItem item2 = new MenuItem("Löschen");
		item1.setOnAction(ev -> {
			bearbeiteKonto(tableKonto.getSelectionModel().getSelectedItem());
		});
		item2.setOnAction(ev -> {
			loescheKonto(tableKonto.getSelectionModel().getSelectedItems());
		});
		contextMenu.getItems().addAll(item1, item2);
		tableKonto.setContextMenu(contextMenu);

		LocalDate ld = LocalDate.of(LocalDate.now().getYear(), 1, 1);
		datepickerGJBeginn.setValue(ld);

		standardkontenHinzufuegen();
	}

	@FXML
	private void handle_KontoHinzufuegen(ActionEvent event) {
		String fehlermeldung = "";
		if (textfieldKuerzel.getText().length() > 5 || textfieldKuerzel.getText().length() == 0) {
			fehlermeldung += "- Das Kürzel ist bezüglich seiner Länge ungültig\n";
		}
		if (textfieldKontenname.getText().length() == 0) {
			fehlermeldung += "- Keinen Kontonamen angegeben\n";
		}
		if(radioBestandskonto.isSelected() && (textfieldAB.getText().length() == 0 || !isStringANumber(textfieldAB.getText()))){
			fehlermeldung += "- Bitte geben Sie einen gültigen Anfangsbestand für das Konto an\n";
		}
		if(radioErfolgskonto.isSelected() && verrechnungskonto.getSelectionModel().isEmpty()){
			fehlermeldung += "- Bitte geben Sie ein Verrechnungskonto für das Konto an\n";
		}
		// Fehlerüberprüfung abgeschlossen
		if (fehlermeldung.equals("")) {
			if (radioBestandskonto.isSelected()) {
				Bestandskonto newBKonto = new Bestandskonto(textfieldKontenname.getText(), textfieldKuerzel.getText(),
						"SBK", Double.parseDouble(textfieldAB.getText()), radioAktivkonto.isSelected());
				kontenListe.add(newBKonto);
			} else if (radioErfolgskonto.isSelected()) {
				Erfolgskonto newEKonto = new Erfolgskonto(textfieldKontenname.getText(), textfieldKuerzel.getText(),
						verrechnungskonto.getValue(), radioErtragskonto.isSelected());
				kontenListe.add(newEKonto);
			}
			tabelleAktualisieren();
			new AlertDialogFrame().showConfirmDialog("\""+ textfieldKontenname.getText() +"\" hinzugefügt!",
					"Das Konto wurde erfolgreich angelegt.", "Ok",
					AlertDialogFrame.INFORMATION_TYPE);
			textfieldKontenname.setText("");
			textfieldAB.setText("");
			textfieldKuerzel.setText("");
		} else {
			new AlertDialogFrame().showConfirmDialog("Das Konto \""+ textfieldKontenname.getText() +"\" konnte aus folgenden Gründen nicht hinzugefügt werden:",
					fehlermeldung, "Ok",
					AlertDialogFrame.WARNING_TYPE);
		}
		
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
		neueBilanz = new Kontenverwaltung(new File(STANDARD_PATH + textfieldBilanzname.getText() + ".bil"), kontenListe,
				datepickerGJBeginn.getValue());
		File standardPath = new File(STANDARD_PATH);
		if (!standardPath.exists()) {
			standardPath.mkdirs();
		}
		boolean ersetzeDatei = true;
		if (textfieldBilanzname.getText().length() == 0) {
			new AlertDialogFrame().showConfirmDialog("Bilanz konnte nicht angelegt werden",
					"Bitte geben sie einen Bilanznamen an, der ebenfalls als Dateinamen dienen wird.", "Ok",
					AlertDialogFrame.ERROR_TYPE);
			return;
		}
		if (neueBilanz.getSpeicherort().exists()) {
			ersetzeDatei = new AlertDialogFrame().showChoiseDialog("Die Datei " + textfieldBilanzname.getText() + ".bil existiert bereits",
					"Soll die Datei mit der neuen Bilanz überschrieben werden?", "Ok", "Abbrechen",
					AlertDialogFrame.QUESTION_TYPE);
		}
		if (ersetzeDatei) {
			boolean erfolgreich = IOManager.saveFile(neueBilanz.getKonten(), neueBilanz.getFaelle(),
					neueBilanz.getSpeicherort(), neueBilanz.getGeschaeftsjahrBeginn());
			if (!erfolgreich) {
				new AlertDialogFrame().showConfirmDialog("Bilanz konnte nicht angelegt werden",
						"Möglicherweise ist der angegebene Dateiname (Bilanzname) nicht gültig.", "Ok",
						AlertDialogFrame.ERROR_TYPE);
			} else {
				bilanzHinzugefuegt = true;
			}
		}
		if (bilanzHinzugefuegt) {
			Stage stage = (Stage) buttonBilanzErstellen.getScene().getWindow();
			stage.close();
		}

	}

	@FXML
	private void handleABAendern(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("AB.fxml"));
			Scene scene = new Scene(loader.load());
			ABController controller = loader.getController();
			controller.setTextfieldBankValue(bank.getAnfangsbestand());
			controller.setTextfieldKasseValue(kasse.getAnfangsbestand());
			controller.setTextfieldVerbValue(verb.getAnfangsbestand());
			controller.setTextfieldFordValue(ford.getAnfangsbestand());
			controller.setTextfieldDarValue(dar.getAnfangsbestand());
			controller.setTextfieldBGAValue(bga.getAnfangsbestand());
			controller.setTextfieldEKValue(ek.getAnfangsbestand());
			Stage abStage = new Stage();
			abStage.setScene(scene);
			abStage.showAndWait();
			bank.setAnfangsbestand(controller.getTextfieldBankValue());
			kasse.setAnfangsbestand(controller.getTextfieldKasseValue());
			verb.setAnfangsbestand(controller.getTextfieldVerbValue());
			ford.setAnfangsbestand(controller.getTextfieldFordValue());
			dar.setAnfangsbestand(controller.getTextfieldDarValue());
			bga.setAnfangsbestand(controller.getTextfieldBGAValue());
			ek.setAnfangsbestand(controller.getTextfieldEKValue());
			tabelleAktualisieren();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void standardkontenHinzufuegen() {
		bank = new Bestandskonto("Bank", "Bank", "SBK", 0, true);
		bga = new Bestandskonto("Büro- und Geschäftsausstattung", "BGA", "SBK", 0, true);
		kasse = new Bestandskonto("Kasse", "Kasse", "SBK", 0, true);
		verb = new Bestandskonto("Verbindlichkeiten a.L.L.", "Verb", "SBK", 0, false);
		dar = new Bestandskonto("Darlehen", "Dar", "SBK", 0, false);
		ek = new Bestandskonto("Eigenkapital", "EK", "SBK", 0, false);
		ford = new Bestandskonto("Forderungen a.L.L.", "Ford", "SBK", 0, true);
		Konto ust = new Steuerkonto("Umsatzsteuer", "USt", "Vorst");
		Konto vorst = new Steuerkonto("Vorsteuer", "Vorst", "USt");
		Konto uerl = new Erfolgskonto("Umsatzerlöse", "UErl", "GuV", true);
		Konto privat = new Erfolgskonto("Privat", "Privat", "EK", true);
		Konto efpz = new Erfolgskonto("Entnahme f. priv. Zwecke", "EfpZ", "Privat", false);
		Konto guv = new Abschlusskonto("Gewinn- und Verlustkonto", "GuV", "EK");
		Konto sbk = new Abschlusskonto("Schlussbilanzkonto", "SBK", "");

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
		kontenListe.add(guv);
		kontenListe.add(sbk);

		if (checkboxProduzierendesU.isSelected()) {
			Konto fe = new Bestandskonto("Fertige Erzeugnisse", "FE", "BV", 0, true);
			Konto ue = new Bestandskonto("Unfertige Erzeugnisse", "UE", "BV", 0, true);
			Konto bv = new Bestandskonto("Bestandsveränderungen", "BV", "GuV", 0, true);
			kontenListe.add(fe);
			kontenListe.add(ue);
			kontenListe.add(bv);
		}
		tabelleAktualisieren();
	}

	private void tabelleAktualisieren() {
		tableKonto.getItems().clear();
		ObservableList<Konto> obsList = FXCollections.observableArrayList(kontenListe);
		tableKonto.setItems(obsList);
		columnKonto.setCellValueFactory(new PropertyValueFactory<Konto, String>("titel"));
		columnBeschreibung.setCellValueFactory(new PropertyValueFactory<Konto, String>("beschreibung"));
		ArrayList<String> kontenKuerzel = new ArrayList<>();
		for (Konto konto : kontenListe) {
			kontenKuerzel.add(konto.getKuerzel());
		}
		verrechnungskonto.setItems(FXCollections.observableArrayList(kontenKuerzel));
	}

	private void loescheKonto(ObservableList<Konto> selectedKonten) {
		
		System.out.println("Löschen....");
		for (int i = 0; i < selectedKonten.size(); i++) {
			if (kontenListe.contains(selectedKonten.get(i)) && selectedKonten.get(i).getKontoart() != 3 && selectedKonten.get(i).getKontoart() != 4) {
				kontenListe.remove(selectedKonten.get(i));
			}
		}
		tabelleAktualisieren();
	}

	private void bearbeiteKonto(Konto selectedKonto) {
		if(selectedKonto.getKontoart() != 3 && selectedKonto.getKontoart() != 4){
			try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("KontoBearbeiten.fxml"));
			Scene scene = new Scene(loader.load());
			KontoBearbeitenController controller = loader.getController();
			ArrayList<String> kontenKuerzel = new ArrayList<>();
			for (Konto konto : kontenListe) {
				kontenKuerzel.add(konto.getKuerzel());
			}
			controller.setChangeKonto(selectedKonto, FXCollections.observableArrayList(kontenKuerzel));

			Stage stage = new Stage();
			stage.setScene(scene);
			stage.showAndWait();
			tabelleAktualisieren();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
		

	}
	
	private boolean isStringANumber(String text) {
		try {
			Double.parseDouble(text);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
		
	}

	public Kontenverwaltung getNeueBilanz() {
		return neueBilanz;
	}

	public boolean isNeueBilanzErstellt() {
		return bilanzHinzugefuegt;
	}

}
