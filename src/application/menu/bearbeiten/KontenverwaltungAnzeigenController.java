package application.menu.bearbeiten;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import konten.Konto;
import application.menu.datei.BilanzErstellenController;

public class KontenverwaltungAnzeigenController implements Initializable {

	@FXML
	private Button BTN_KontoHinzufuegen;

	@FXML
	private Button BTN_PDFSpeichern;

	@FXML
	private TableView<Konto> TW_Kontenliste;

	@FXML
	private TableColumn<Konto, String> columnKonto;

	@FXML
	private TableColumn<Konto, String> columnBeschreibung;

	private ObservableList<Konto> kontoListe;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		kontoListe =  FXCollections.observableArrayList();
		tabelleAktualisieren();
		
	}

	@FXML
	void handle_KontoHinzufuegen(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("KontoHinzufuegen.fxml"));
			Scene scene = new Scene(loader.load());
			Stage KontoHinzufuegen = new Stage();
			KontoHinzufuegenController controller = loader.getController();
			KontoHinzufuegen.setResizable(false);
			KontoHinzufuegen.setScene(scene);
			controller.setKonten(kontoListe);
			KontoHinzufuegen.setTitle("BuFü HWR Version");
			KontoHinzufuegen.show();
		} catch (IOException e) {
			// TODO
			e.printStackTrace();
		}
	}

	@FXML
	void handle_PDFSpeichern(ActionEvent event) {

	}

	private void tabelleAktualisieren() {
		TW_Kontenliste.setItems(kontoListe);
		columnKonto.setCellValueFactory(new PropertyValueFactory<Konto, String>("titel"));
		columnBeschreibung.setCellValueFactory(new PropertyValueFactory<Konto, String>("beschreibung"));
	}

	// Kontenliste übergeben lassen und in einer ArrayListe speichern
	public void setKonten(HashMap<String, Konto> kntList) {

		for (String key : kntList.keySet()) {
			kontoListe.add(kntList.get(key));
		}
	}

}