/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import application.menu.analyse.DiagrammErstellenController;
import application.menu.datei.BilanzErstellenController;
import io.DataStorage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import konten.Bestandskonto;
import konten.Erfolgskonto;
import konten.Konto;
import konten.Steuerkonto;

/**
 * FXML Controller class
 *
 * @author Marc
 */
public class GUIController implements Initializable {

	@FXML
	private VBox t1_A;
	@FXML
	private VBox t1_P;
	@FXML
	private VBox t2;
	@FXML
	private VBox t3;

	private GridPane t2_Ertragskonten;
	private GridPane t2_Aufwandskonten;
	private GridPane t3_Steuerkonten;
	private int count_t2_Ertragskonten, count_t2_Aufwandskonten, count_t3_Steuerkonten;
	private Kontenverwaltung kontenverwaltung;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		count_t2_Aufwandskonten = 5;
		count_t2_Ertragskonten = 5;
		count_t3_Steuerkonten = 0;
		t2_Aufwandskonten = new GridPane();
		t2_Ertragskonten = new GridPane();
		t3_Steuerkonten = new GridPane();

		t1_A.setSpacing(10);
		t1_P.setSpacing(10);
		Label labelErtrag = new Label("\tErtragskonten");
		labelErtrag.setFont(Font.font("System", FontWeight.BOLD, 18));
		Label labelAufwand = new Label("\tAufwandskonten");
		labelAufwand.setFont(Font.font("System", FontWeight.BOLD, 18));
		kontenverwaltung = new Kontenverwaltung();
		t2_Ertragskonten.add(labelErtrag, 0, 0 , 2, 1);
		t2_Aufwandskonten.add(labelAufwand, 0, 0, 2, 1);
		t2.getChildren().addAll(t2_Ertragskonten, t2_Aufwandskonten);
		t3.getChildren().add(t3_Steuerkonten);
		GridPane[] gpList = new GridPane[] { t2_Ertragskonten, t2_Aufwandskonten, t3_Steuerkonten };
		for (int i = 0; i < gpList.length; i++) {
			gpList[i].setVgap(10);
			gpList[i].setHgap(40);
			gpList[i].setPadding(new Insets(10));
		}

	}

	public VBox getT1_A() {
		return t1_A;
	}

	public VBox getT1_P() {
		return t1_P;
	}

	@FXML
	private void handle_Datei_NeueBilanzErstellen(ActionEvent event) {

		try {
			System.out.println(getClass().getResource(""));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("menu/datei/BilanzErstellen.fxml"));
			Scene scene = new Scene(loader.load());
			BilanzErstellenController controller = loader.getController();
			Stage bilanzErstellenStage = new Stage();
			bilanzErstellenStage.setScene(scene);
			bilanzErstellenStage.setTitle("BuFü HWR Version");
			bilanzErstellenStage.showAndWait();
			kontenverwaltung = controller.getNeueBilanz();
			ladeKonten();
		} catch (IOException e) {
			// TODO
			e.printStackTrace();
		}

	}

	private void ladeKonten() {
		System.out.println("[GUIController] Lade Konten...");
		t1_P.getChildren().clear();
		t1_A.getChildren().clear();

		Iterator<Konto> it = kontenverwaltung.getKonten();
		while (it.hasNext()) {
			Konto konto = it.next();
			switch (konto.getKontoart()) {
			case (1):
				Bestandskonto bkonto = (Bestandskonto) konto;
				if (bkonto.isAktivkonto()) {
					t1_A.getChildren().add(bkonto.getGUIComponents());
				} else {
					t1_P.getChildren().add(bkonto.getGUIComponents());
				}
				break;
			case (2):
				Erfolgskonto ekonto = (Erfolgskonto) konto;
				if (ekonto.isErtragskonto()) {
					t2_Ertragskonten.add(ekonto.getGUIComponents(), count_t2_Ertragskonten % 4,
							count_t2_Ertragskonten / 4);
					count_t2_Ertragskonten++;
				} else {
					t2_Aufwandskonten.add(ekonto.getGUIComponents(), count_t2_Aufwandskonten % 4,
							count_t2_Aufwandskonten / 4);
					count_t2_Aufwandskonten++;
				}
				break;
			case (3):
				t3_Steuerkonten.add(konto.getGUIComponents(), count_t3_Steuerkonten, 0);
				count_t3_Steuerkonten++;
				break;
			}
		}

	}

	@FXML
	// Typ DataStorage als Rückgabewert, damit bestehende Fälle und Konten auf
	// konten und faelle geschrieben werden können
	private DataStorage handle_Datei_Oeffnen(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		configureFileChooser(fileChooser);
		File file = fileChooser.showOpenDialog(new Stage());
		DataStorage myStorage = null;
		if (file != null) {
			myStorage = io.IOManager.readFile(file);
			// TODO
			// hier Fall beachten, wenn falscher Filetyp geöffnet wird
		}
		return myStorage;

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
		try {
			System.out.println(getClass().getResource(""));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("menu/analyse/DiagrammErstellen.fxml"));
			Scene scene = new Scene(loader.load());
			//DiagrammErstellenController controller = loader.getController();
			Stage diagrammErstellenStage = new Stage();
			diagrammErstellenStage.setScene(scene);
			diagrammErstellenStage.setTitle("BuFü HWR Version");
			diagrammErstellenStage.show();
			
			//ladeKonten();
		} catch (IOException e) {
			// TODO
			e.printStackTrace();
		}
	}

	@FXML
	private void handle_Analyse_SBErstellen(ActionEvent event) {
	}

	@FXML
	private void handle_Hilfe_Produktinformationen(ActionEvent event) {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("application/specs.pdf").getFile());
		GUI.services.showDocument(file.toURI().toString());
	}

	@FXML
	private void handle_Hilfe_Handbuch(ActionEvent event) {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("application/Handbuch.pdf").getFile());
		GUI.services.showDocument(file.toURI().toString());
	}

	@FXML
	private void handle_Hilfe_FAQ(ActionEvent event) {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("application/FAQ.pdf").getFile());
		GUI.services.showDocument(file.toURI().toString());
	}

	private static void configureFileChooser(final FileChooser fileChooser) {
		fileChooser.setTitle("Öffne Bilanzdatei");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
	}

}
