package application.menu.datei;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import konten.Bestandskonto;
import konten.Erfolgskonto;
import konten.Konto;

public class KontoBearbeitenController implements Initializable {
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
	private RadioButton radioBestandskonto;
	@FXML
	private RadioButton radioErfolgskonto;
	@FXML
	private ComboBox<String> verrechnungskonto;

	private Konto changedKonto;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ToggleGroup group2 = new ToggleGroup();
		ToggleGroup group3 = new ToggleGroup();

		radioErtragskonto.setToggleGroup(group2);
		radioAufwandskonto.setToggleGroup(group2);
		radioAktivkonto.setToggleGroup(group3);
		radioPassivkonto.setToggleGroup(group3);
	}

	// Event Listener on Button[#buttonAddKonto].onAction
	@FXML
	public void handle_KontoHinzufuegen(ActionEvent event) {
		changedKonto.setTitel(textfieldKontenname.getText());
		changedKonto.setKuerzel(textfieldKuerzel.getText());
		changedKonto.getGuiContainer().setName(textfieldKontenname.getText());

		if (radioBestandskonto.isSelected()) {
			Bestandskonto newKonto = (Bestandskonto) changedKonto;
			newKonto.setVerrechnungKonto("SBK");
			newKonto.setAnfangsbestand(Double.parseDouble(textfieldAB.getText()));
			newKonto.setAktivkonto(radioAktivkonto.isSelected());
		} else if (radioErfolgskonto.isSelected()) {
			Erfolgskonto newKonto = (Erfolgskonto) changedKonto;
			newKonto.setVerrechnungKonto(verrechnungskonto.getValue());
			newKonto.setErtragskonto(radioErtragskonto.isSelected());
		}
		((Stage) buttonAddKonto.getScene().getWindow()).close();
	}

	public void setChangeKonto(Konto konto, ObservableList<String> kontenListe) {
		textfieldKontenname.setText(konto.getTitel());
		textfieldKuerzel.setText(konto.getKuerzel());
		switch (konto.getKontoart()) {
		case (1):
			radioErfolgskonto.setDisable(true);
			radioErfolgskonto.setSelected(false);
			radioAufwandskonto.setDisable(true);
			radioErtragskonto.setDisable(true);
			verrechnungskonto.setDisable(true);

			Bestandskonto bkonto = (Bestandskonto) konto;
			textfieldAB.setText(bkonto.getAnfangsbestand() + "");
			if (bkonto.isAktivkonto()) {
				radioAktivkonto.setSelected(true);
			} else {
				radioPassivkonto.setSelected(true);
			}
			break;
		case (2):
			radioBestandskonto.setDisable(true);
			radioBestandskonto.setSelected(false);
			radioAktivkonto.setDisable(true);
			radioPassivkonto.setDisable(true);
			textfieldAB.setDisable(true);

			Erfolgskonto ekonto = (Erfolgskonto) konto;
			verrechnungskonto.setItems(kontenListe);
			verrechnungskonto.getSelectionModel().select(ekonto.getVerrechnungKonto());
			if (ekonto.isErtragskonto()) {
				radioErtragskonto.setSelected(true);
			} else {
				radioAufwandskonto.setSelected(true);
			}
			break;
		default:
			((Stage) buttonAddKonto.getScene().getWindow()).close();
		}
		changedKonto = konto;
	}
}
