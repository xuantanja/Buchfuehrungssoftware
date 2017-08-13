/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import application.menu.bearbeiten.KontenverwaltungAnzeigenController;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import konten.Bestandskonto;
import konten.Erfolgskonto;
import konten.Konto;
import utility.Collection.IDMap;
import utility.Collection.Tuple;
import utility.alertDialog.AlertDialogFrame;
import utility.converter.TypeConverter;

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
	private VBox t4Container;
	@FXML
	private Menu menuBearbeiten, menuAnalyse;
	@FXML
	private MenuItem menuitemSpeichern, menuitemJAB, menuitemAddGF, menuitemAddBS;
	@FXML
	private Separator vSeparator;
	@FXML
	private HBox abschlusskontoContainer;
	@FXML
	private HBox chartContainer;

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
		t1_A.heightProperty().addListener(e -> {
			if (t1_A.getHeight() > t1_P.getHeight())
				vSeparator.setPrefHeight(t1_A.getHeight());
		});
		t1_P.heightProperty().addListener(e -> {
			if (t1_P.getHeight() > t1_A.getHeight())
				vSeparator.setPrefHeight(t1_P.getHeight());
		});
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
			bilanzErstellenStage.setResizable(false);
			bilanzErstellenStage.showAndWait();
			if (controller.isNeueBilanzErstellt()) {
				kontenverwaltung = controller.getNeueBilanz();
				ladeKonten(true);
				enableMenuBar(true);
			}
		} catch (IOException e) {
			new AlertDialogFrame().showConfirmDialog("Interner Fehler",
					"Menüpunkt \"Neue Bilanz erstellen\" konnte nicht durchgeführt werden.", "Ok",
					AlertDialogFrame.ERROR_TYPE);
			e.printStackTrace();
		} catch (NullPointerException e) {
			// Wenn das Fenster geschlossen wird, dann wird eine
			// NullPointerException geworfen. Exception wird ignoriert.
		}

	}

	private void ladeKonten(boolean neueBilanz) {
		GridPane[] gpList = new GridPane[] { t1_A, t1_P, t2_Ertragskonten, t2_Aufwandskonten, t3_Steuerkonten };
		for (int i = 0; i < gpList.length; i++) {
			gpList[i].getChildren().clear();
		}
		abschlusskontoContainer.getChildren().clear();
		chartContainer.getChildren().clear();
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
			case (4):
				if (!neueBilanz) {
					konto.newContainer();
				}
				abschlusskontoContainer.getChildren().add(konto.getGUIComponents());
				break;
			}
		}
		if (kontenverwaltung.getKonten().get("SBK").getBilanzwert() != -1) {
			// TODO für Tanja: Hier müssen die Diagramme der HBox
			// "chartContainer" hinzugefügt werden.
			menuitemJAB.setDisable(true);
			menuitemAddGF.setDisable(true);
			menuitemAddBS.setDisable(true);
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
				enableMenuBar(true);
				ladeKonten(false);
			} catch (IOException e) {
				e.printStackTrace();
				new AlertDialogFrame().showConfirmDialog("Die ausgewählte Datei konnte nicht geöffnet werden",
						"Die Datei ist nicht kompatibel mit dieser Version!", "Ok", AlertDialogFrame.ERROR_TYPE);
			}
		}
	}

	@FXML
	private void handle_Datei_Speichern(ActionEvent event) {
		boolean erfolgreich = IOManager.saveFile(kontenverwaltung.getKonten(), kontenverwaltung.getFaelle(),
				kontenverwaltung.getSpeicherort(), kontenverwaltung.getGeschaeftsjahrBeginn());
		if (erfolgreich) {
			new AlertDialogFrame().showConfirmDialog("Die Bilanz wurde erfolgreich gespeichert",
					"Die Bilanz wurde unter der Datei " + kontenverwaltung.getSpeicherort().getName() + " gespeichert.",
					"Ok", AlertDialogFrame.INFORMATION_TYPE);
		} else {
			new AlertDialogFrame().showConfirmDialog("Speichern nicht erfolgreich",
					"Beim Speichern der Bilanz ist ein Fehler aufgetreten. Die Bilanz konnte nicht gespeichert werden",
					"Ok", AlertDialogFrame.ERROR_TYPE);
		}
	}

	@FXML
	private void handle_Datei_Beenden(ActionEvent event) {
		System.exit(0);
	}

	@FXML
	private void handle_Bearbeiten_Kontenverwaltung(ActionEvent event) {
		try {

			System.out.println(kontenverwaltung.getKonten().size());

			FXMLLoader loader = new FXMLLoader(getClass().getResource("menu/bearbeiten/KontenverwaltungAnzeigen.fxml"));
			Scene scene = new Scene(loader.load());
			Stage KontoverwaltungAnzeigen = new Stage();
			KontenverwaltungAnzeigenController controller = loader.getController();
			KontoverwaltungAnzeigen.setResizable(false);
			KontoverwaltungAnzeigen.setScene(scene);
			controller.setKonten(kontenverwaltung.getKonten());

			KontoverwaltungAnzeigen.setTitle(kontenverwaltung.getSpeicherort().getName());
			KontoverwaltungAnzeigen.showAndWait();
			
			//Ebene 1: neu erstellte Konten werden der Kontenverwaltung übergeben
			if (controller.isKontenErstellt()) {
				for (Konto konto : controller.getNeueKonten()) {
					kontenverwaltung.addKonto(konto);
					System.out.println(kontenverwaltung.getKonten().size());
				}
			ladeKonten(false);
			}

		} catch (IOException e) {
			new AlertDialogFrame().showConfirmDialog("Interner Fehler",
					"Menüpunkt \"Kontoverwaltung\" konnte nicht durchgeführt werden.", "Ok",
					AlertDialogFrame.ERROR_TYPE);
			e.printStackTrace();
		}
	}

	@FXML
	private void handle_Bearbeiten_GF_Uebersicht(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("menu/bearbeiten/UebersichtAnzeigen.fxml"));
			Scene scene = new Scene(loader.load());
			UebersichtanzeigenController controller = loader.getController();
			controller.setFaelle(kontenverwaltung.getFaelle());

			Stage bilanzErstellenStage = new Stage();
			bilanzErstellenStage.setResizable(false);
			bilanzErstellenStage.setScene(scene);
			bilanzErstellenStage.setTitle(kontenverwaltung.getSpeicherort().getName());
			bilanzErstellenStage.showAndWait();
		} catch (IOException e) {
			new AlertDialogFrame().showConfirmDialog("Interner Fehler",
					"Menüpunkt \"Übersicht anzeigen\" konnte nicht durchgeführt werden.", "Ok",
					AlertDialogFrame.ERROR_TYPE);
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
			stage.setResizable(false);
			stage.setTitle("Neuen Geschäftsfall erstellen");
			stage.setScene(scene);
			stage.showAndWait();
			if (!controller.isCloseButtonPressed()) {
				kontenverwaltung
						.addGeschaeftsfall(controller.getGeschaeftsfall(kontenverwaltung.getFaelle().size() + 1));
			}

		} catch (IOException e) {
			new AlertDialogFrame().showConfirmDialog("Interner Fehler",
					"Menüpunkt \"Neuen Geschäftsfall hinzufügen\" konnte nicht durchgeführt werden.", "Ok",
					AlertDialogFrame.ERROR_TYPE);
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
			stage.setResizable(false);
			stage.setTitle("Buchungssatz erstellen");
			stage.setScene(scene);
			stage.showAndWait();
			for (Tuple<Integer, ArrayList<Buchungssatz>> gf : controller.getNeueBuchungssaetze()) {
				kontenverwaltung.addBuchungssatz(kontenverwaltung.getFaelle().get(gf.getX()), gf.getY());
			}
		} catch (IOException e) {
			new AlertDialogFrame().showConfirmDialog("Interner Fehler",
					"Menüpunkt \"Buchungssatz einem Geschäftsfall hinzufügen\" konnte nicht durchgeführt werden.", "Ok",
					AlertDialogFrame.ERROR_TYPE);
			e.printStackTrace();
		}
	}

	@FXML
	private void handle_Analyse_EBEinsehen(ActionEvent event) {
		try {
			System.out.println(getClass().getResource(""));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("menu/analyse/EroeffnungsbilanzEinsehen.fxml"));
			Scene scene = new Scene(loader.load());
			Stage BEEinsehenStage = new Stage();
			BEEinsehenStage.setResizable(false);
			BEEinsehenStage.setScene(scene);
			BEEinsehenStage.setTitle("BuFü HWR Version");
			BEEinsehenStage.show();

		} catch (IOException e) {
			new AlertDialogFrame().showConfirmDialog("Interner Fehler",
					"Menüpunkt \"Diagramme berechnen\" konnte nicht durchgeführt werden.", "Ok",
					AlertDialogFrame.ERROR_TYPE);
			e.printStackTrace();
		}
	}

	@FXML
	private void handle_Analyse_DiagrammeBerechnen(ActionEvent event) {
		try {
			System.out.println(getClass().getResource(""));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("menu/analyse/DiagrammErstellen.fxml"));
			Scene scene = new Scene(loader.load());
			Stage diagrammErstellenStage = new Stage();
			diagrammErstellenStage.setResizable(false);
			diagrammErstellenStage.setScene(scene);
			diagrammErstellenStage.setTitle("BuFü HWR Version");
			diagrammErstellenStage.show();

		} catch (IOException e) {
			new AlertDialogFrame().showConfirmDialog("Interner Fehler",
					"Menüpunkt \"Diagramme berechnen\" konnte nicht durchgeführt werden.", "Ok",
					AlertDialogFrame.ERROR_TYPE);
			e.printStackTrace();
		}
	}

	@FXML
	private void handle_Analyse_SBErstellen(ActionEvent event) {
		kontenverwaltung.kontensaldierung();
		ladeKonten(false);
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
		menuitemJAB.setDisable(!enable);
		menuitemAddGF.setDisable(!enable);
		menuitemAddBS.setDisable(!enable);
	}

}
