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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import konten.Bestandskonto;
import konten.Erfolgskonto;
import konten.Konto;

/**
 * FXML Controller class
 *
 * @author Marc
 */
public class GUIController implements Initializable {

	@FXML
	private GridPane t1_A;
	@FXML
	private GridPane t1_P;
	@FXML
	private VBox t2;
	@FXML
	private VBox t3;

	private GridPane t2_Ertragskonten;
	private GridPane t2_Aufwandskonten;
	private GridPane t3_Steuerkonten;
	private int count_t2_Ertragskonten, count_t2_Aufwandskonten, count_t3_Steuerkonten, count_t1_A, count_t1_P;
	private Kontenverwaltung kontenverwaltung;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		t2_Aufwandskonten = new GridPane();
		t2_Ertragskonten = new GridPane();
		t3_Steuerkonten = new GridPane();
		kontenverwaltung = new Kontenverwaltung();
		initalHeadings();

		t2.getChildren().addAll(t2_Ertragskonten, t2_Aufwandskonten);
		t3.getChildren().add(t3_Steuerkonten);
		GridPane[] gpList = new GridPane[] { t1_A, t1_P, t2_Ertragskonten, t2_Aufwandskonten, t3_Steuerkonten };
		for (int i = 0; i < gpList.length; i++) {
			gpList[i].setVgap(10);
			gpList[i].setHgap(40);
			gpList[i].setPadding(new Insets(10));
		}
	}

	private void initalHeadings() {
		count_t2_Aufwandskonten = 5;
		count_t2_Ertragskonten = 5;
		count_t3_Steuerkonten = 5;
		count_t1_A = 2;
		count_t1_P = 2;
		Label labelErtrag = new Label("\tErtragskonten");
		labelErtrag.setFont(Font.font("System", FontWeight.BOLD, 18));
		Label labelAktiva = new Label("\tAktiva");
		labelAktiva.setFont(Font.font("System", FontWeight.BOLD, 18));
		Label labelPassiva = new Label("\tPassiva");
		labelPassiva.setFont(Font.font("System", FontWeight.BOLD, 18));
		Label labelAufwand = new Label("\tAufwandskonten");
		labelAufwand.setFont(Font.font("System", FontWeight.BOLD, 18));
		Label labelSteuerkonto = new Label("\tSteuerkonten");
		labelSteuerkonto.setFont(Font.font("System", FontWeight.BOLD, 18));
		t2_Ertragskonten.add(labelErtrag, 0, 0, 5, 1);
		t2_Aufwandskonten.add(labelAufwand, 0, 0, 5, 1);
		t3_Steuerkonten.add(labelSteuerkonto, 0, 0, 5, 1);
		t1_A.add(labelAktiva, 0, 0, 2, 1);
		t1_P.add(labelPassiva, 0, 0, 2, 1);
	}

	public GridPane getT1_A() {
		return t1_A;
	}

	public GridPane getT1_P() {
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
			if (controller.isNeueBilanzErstellt()) {
				kontenverwaltung = controller.getNeueBilanz();
				ladeKonten();
			}
			kontenverwaltung = controller.getNeueBilanz();
		} catch (IOException e) {
			// TODO
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO: Wenn das Fenster geschlossen wird, dann wird eine
			// NullPointerException geworfen.
		}

	}

	private void ladeKonten() {
		System.out.println("[GUIController] Lade Konten...");
		GridPane[] gpList = new GridPane[] { t1_A, t1_P, t2_Ertragskonten, t2_Aufwandskonten, t3_Steuerkonten };
		for (int i = 0; i < gpList.length; i++) {
			gpList[i].getChildren().clear();
		}
		initalHeadings();
		Iterator<Konto> it = kontenverwaltung.getKonten();
		while (it.hasNext()) {
			Konto konto = it.next();
			System.out.println(konto.getTitel());
			switch (konto.getKontoart()) {
			case (1):
				Bestandskonto bkonto = (Bestandskonto) konto;
				if (bkonto.isAktivkonto()) {
					t1_A.add(bkonto.getGUIComponents(), count_t1_A % 2, count_t1_A / 2);
					count_t1_A++;
				} else {
					t1_P.add(bkonto.getGUIComponents(), count_t1_P % 2, count_t1_P / 2);
					count_t1_P++;
				}
				break;
			case (2):
				Erfolgskonto ekonto = (Erfolgskonto) konto;
				if (ekonto.isErtragskonto()) {
					t2_Ertragskonten.add(ekonto.getGUIComponents(), count_t2_Ertragskonten % 4, count_t2_Ertragskonten / 4);
					count_t2_Ertragskonten++;
				} else {
					t2_Aufwandskonten.add(ekonto.getGUIComponents(), count_t2_Aufwandskonten % 4, count_t2_Aufwandskonten / 4);
					count_t2_Aufwandskonten++;
				}
				break;
			case (3):
				t3_Steuerkonten.add(konto.getGUIComponents(), count_t3_Steuerkonten % 4, count_t3_Steuerkonten / 4);
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
			Stage diagrammErstellenStage = new Stage();
			diagrammErstellenStage.setScene(scene);
			diagrammErstellenStage.setTitle("BuFü HWR Version");
			diagrammErstellenStage.show();

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
