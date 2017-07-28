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

import application.menu.bearbeiten.NeuerBSController;
import application.menu.bearbeiten.NeuerGFController;
import application.menu.bearbeiten.UebersichtanzeigenController;
import application.menu.datei.BilanzErstellenController;
import geschaeftsfall.Buchungssatz;
import io.DataStorage;
import io.IOManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import konten.Bestandskonto;
import konten.Erfolgskonto;
import konten.Konto;
import utility.alertDialog.AlertDialogFrame;
import utility.converter.TypeConverter;
import utility.map.IDMap;

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
	@FXML
	private Menu menuBearbeiten, menuAnalyse;
	@FXML
	private MenuItem menuitemSpeichern;
	@FXML
	private Separator vSeparator;

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

		t2.getChildren().addAll(t2_Ertragskonten, t2_Aufwandskonten);
		t3.getChildren().add(t3_Steuerkonten);
		GridPane[] gpList = new GridPane[] { t1_A, t1_P, t2_Ertragskonten, t2_Aufwandskonten, t3_Steuerkonten };
		for (int i = 0; i < gpList.length; i++) {
			gpList[i].setVgap(10);
			gpList[i].setHgap(40);
			gpList[i].setPadding(new Insets(10));
		}
		enableMenuBar(false);
		t1_A.heightProperty().addListener(e -> {if(t1_A.getHeight() > t1_P.getHeight()) vSeparator.setPrefHeight(t1_A.getHeight());});
		t1_P.heightProperty().addListener(e -> {if(t1_P.getHeight() > t1_A.getHeight()) vSeparator.setPrefHeight(t1_P.getHeight());});
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
			bilanzErstellenStage.setTitle("Neue Bilanz erstellen");
			bilanzErstellenStage.showAndWait();
			if (controller.isNeueBilanzErstellt()) {
				kontenverwaltung = controller.getNeueBilanz();
				ladeKonten(true);
				enableMenuBar(true);
			}
		} catch (IOException e) {
			// TODO
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO: Wenn das Fenster geschlossen wird, dann wird eine
			// NullPointerException geworfen.
		}

	}

	private void ladeKonten(boolean neueBilanz) {
		GridPane[] gpList = new GridPane[] { t1_A, t1_P, t2_Ertragskonten, t2_Aufwandskonten, t3_Steuerkonten };
		for (int i = 0; i < gpList.length; i++) {
			gpList[i].getChildren().clear();
		}
		initalHeadings();
		Iterator<Konto> it = kontenverwaltung.getKontenIterator();
		while (it.hasNext()) {
			Konto konto = it.next();
			System.out.println(konto.getTitel());
			switch (konto.getKontoart()) {
			case (1):
				Bestandskonto bkonto = (Bestandskonto) konto;
				bkonto.confirmAB();
				if (!neueBilanz) {
					bkonto.newContainer();
				}
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
				if (!neueBilanz) {
					ekonto.newContainer();
				}
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
				if (!neueBilanz) {
					konto.newContainer();
				}
				t3_Steuerkonten.add(konto.getGUIComponents(), count_t3_Steuerkonten % 4, count_t3_Steuerkonten / 4);
				count_t3_Steuerkonten++;
				break;
			}
		}
		System.out.println("------------------------------INIT-DONE------------------------------");
	}

	@FXML
	// Typ DataStorage als Rückgabewert, damit bestehende Fälle und Konten auf
	// konten und faelle geschrieben werden können
	private void handle_Datei_Oeffnen(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Öffne Bilanzdatei");
		fileChooser.setInitialDirectory(
				new File(System.getProperty("user.home") + "\\AppData\\Roaming\\BuFü-HWRVersion\\"));
		File file = fileChooser.showOpenDialog(new Stage());
		DataStorage myStorage = null;
		if (file != null) {
			try {
				myStorage = io.IOManager.readFile(file);
				kontenverwaltung = new Kontenverwaltung(file, myStorage.getKonten(), myStorage.getFaelle(),
						myStorage.getGeschaeftsjahrBeginn());
				ladeKonten(false);
				enableMenuBar(true);
			} catch (IOException e) {
				e.printStackTrace();
				new AlertDialogFrame().showConfirmDialog("Die ausgewählte Datei konnte nicht geöffnet werden", "Die Datei ist nicht kompatibel mit dieser Version!", "Ok", AlertDialogFrame.ERROR_TYPE);
			}
		}
	}

	@FXML
	private void handle_Datei_Speichern(ActionEvent event) {
		boolean erfolgreich = IOManager.saveFile(kontenverwaltung.getKonten(), kontenverwaltung.getFaelle(),
				kontenverwaltung.getSpeicherort(), kontenverwaltung.getGeschaeftsjahrBeginn());
		if(erfolgreich){
				new AlertDialogFrame().showConfirmDialog("Die Bilanz wurde erfolgreich gespeichert", "Die Bilanz wurde unter der Datei " + kontenverwaltung.getSpeicherort().getName() + " gespeichert.", "Ok", AlertDialogFrame.INFORMATION_TYPE);
		} else {
			new AlertDialogFrame().showConfirmDialog("Speichern nicht erfolgreich", "Beim Speichern der Bilanz ist ein Fehler aufgetreten. Die Bilanz konnte nicht gespeichert werden", "Ok", AlertDialogFrame.ERROR_TYPE);
		}
	}

	@FXML
	private void handle_Datei_Beenden(ActionEvent event) {
		System.exit(0);
	}

	@FXML
	private void handle_Bearbeiten_SchrittZurück(ActionEvent event) {
		for (Iterator<Konto> iterator = kontenverwaltung.getKontenIterator(); iterator.hasNext();) {
			System.out.println(iterator.next().description());

		}
	}

	@FXML
	private void handle_Bearbeiten_SchrittVorwärts(ActionEvent event) {
	}

	@FXML
	private void handle_Bearbeiten_Kontenverwaltung(ActionEvent event) {
	}

	@FXML
	private void handle_Bearbeiten_GF_Uebersicht(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("menu/bearbeiten/UebersichtAnzeigen.fxml"));
			Scene scene = new Scene(loader.load());
			UebersichtanzeigenController controller = loader.getController();
			controller.setFaelle(kontenverwaltung.getFaelle());

			Stage bilanzErstellenStage = new Stage();
			bilanzErstellenStage.setScene(scene);
			bilanzErstellenStage.setTitle("XX");
			bilanzErstellenStage.showAndWait();
		} catch (IOException e) {
			// TODO
			e.printStackTrace();
		}
	}

	@FXML
	private void handle_Bearbeiten_GF_neuerGF(ActionEvent event) {
		try {
			System.out.println(getClass().getResource(""));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("menu/bearbeiten/NeuerGF.fxml"));
			Scene scene = new Scene(loader.load());
			NeuerGFController controller = loader.getController();
			Stage stage = new Stage();
			stage.setTitle("Neuen Geschäftsfall erstellen");
			stage.setScene(scene);
			stage.showAndWait();
			kontenverwaltung.addGeschaeftsfall(controller.getGeschaeftsfall(kontenverwaltung.getFaelle().size() + 1));

		} catch (IOException e) {
			// TODO
			e.printStackTrace();
		}
	}

	@FXML
	private void handle_Bearbeiten_GF_BSEintragen(ActionEvent event) {
		try {
			System.out.println(getClass().getResource(""));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("menu/bearbeiten/NeuerBS.fxml"));
			Scene scene = new Scene(loader.load());
			NeuerBSController controller = loader.getController();
			controller.setParameter(kontenverwaltung.getKonten(), kontenverwaltung.getFaelle());
			Stage stage = new Stage();
			stage.setTitle("Buchungssatz erstellen");
			stage.setScene(scene);
				stage.showAndWait();
				IDMap<Integer, Buchungssatz> map = controller.getNeueBuchungssaetze();
				for (int gf : map.keySet()) {
					kontenverwaltung.addBuchungssatz(kontenverwaltung.getFaelle().get(gf), map.getAll(gf));
				}
		} catch (IOException e) {
			// TODO
			e.printStackTrace();
		}
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

	private void enableMenuBar(boolean enable) {
		menuBearbeiten.setDisable(!enable);
		menuAnalyse.setDisable(!enable);
		menuitemSpeichern.setDisable(!enable);
	}

}
