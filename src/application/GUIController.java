/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Marc
 */
public class GUIController implements Initializable {

	@FXML
	private VBox T1_A;
	@FXML
	private VBox T1_P;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		T1_A.setSpacing(10);
		T1_P.setSpacing(10);
	}

	public VBox getT1_A() {
		return T1_A;
	}

	public VBox getT1_P() {
		return T1_P;
	}

	@FXML
	private void handle_Datei_NeueBilanzErstellen(ActionEvent event) {
	}

	@FXML
	private void handle_Datei_Oeffnen(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		configureFileChooser(fileChooser);
		File file = fileChooser.showOpenDialog(new Stage());
		if (file != null) {
			// Auslesen der Daten
		}
	}

	@FXML
	private void handle_Datei_Speichern(ActionEvent event) {
	}

	@FXML
	private void handle_Datei_Einstellungen(ActionEvent event) {
	}

	@FXML
	private void handle_Datei_Beenden(ActionEvent event) {
		System.exit(0);
	}

	@FXML
	private void handle_Bearbeiten_SchrittZurück(ActionEvent event) {
	}

	@FXML
	private void handle_Bearbeiten_SchrittVorwärts(ActionEvent event) {
	}

	@FXML
	private void handle_Bearbeiten_Kontenverwaltung(ActionEvent event) {
	}

	@FXML
	private void handle_Bearbeiten_GF_Uebersicht(ActionEvent event) {
	}

	@FXML
	private void handle_Bearbeiten_GF_neuerGF(ActionEvent event) {
	}

	@FXML
	private void handle_Bearbeiten_GF_BSEintragen(ActionEvent event) {
	}

	@FXML
	private void handle_Analyse_EBEinsehen(ActionEvent event) {
	}

	@FXML
	private void handle_Analyse_DiagrammeBerechnen(ActionEvent event) {
	}

	@FXML
	private void handle_Analyse_SBErstellen(ActionEvent event) {
	}

	@FXML
	private void handle_Hilfe_Produktinformationen(ActionEvent event) {
	}

	@FXML
	private void handle_Hilfe_Handbuch(ActionEvent event) {
	}

	@FXML
	private void handle_Hilfe_FAQ(ActionEvent event) {
	}

	private static void configureFileChooser(final FileChooser fileChooser) {
		fileChooser.setTitle("Öffne Bilanzdatei");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
	}

}
