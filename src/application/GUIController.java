/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

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
    private Kontenverwaltung kontenverwaltung;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        T1_A.setSpacing(10);
        T1_P.setSpacing(10);
        kontenverwaltung = new Kontenverwaltung();
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
    
}
