package application.menu.bearbeiten;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import konten.Konto;
/**
 * KontenverwaltungAnzeigen dient zum Einsehen aller bestehenden Konten und zum möglichen Hinzufügen von Konten.
 */
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

	private ObservableList<Konto> neueKonten;
	
	private boolean isErstellt;
	
	private static final String STANDARD_PATH = System.getProperty("user.home")
			+ "\\AppData\\Roaming\\BuFü-HWRVersion\\";
	
	private ArrayList<Node> exportNodes;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		kontoListe = FXCollections.observableArrayList();
		neueKonten = FXCollections.observableArrayList();
		exportNodes = new ArrayList<>();
		isErstellt = false;
		tabelleAktualisieren();
	}
	/**
	 * <i><b>Ereignisbehandlung: Konto Hinzufügen</b></i><br>
	 * <br>
	 * Aus einem Ereignis wird eine FXML-Datei geladen und aus dem Controller dieser FXML-Datei wird die neue Kontenliste übergeben <br>
	 * 
	 * @param event
	 * 			- Nutzeraktion
	 */
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
			KontoHinzufuegen.showAndWait();
			
			//Ebene 2: Die beim "Konto hinzufügen"-Fenster hinzugefügte Konten werden der "kontenverwaltung Anzeigen"- Fenster übergeben
			if (controller.isNeueKontenErstellt()) {
				ObservableList<Konto> temp = controller.getNeueKonten();
				isErstellt = true;
				
				for(Konto konto : temp){
					neueKonten.add(konto);
				}

			}
		} catch (IOException e) {
			// TODO
			e.printStackTrace();
		}
	}

	/**
	 * <i><b>Aktualisierung der Tabelle</b></i><br>
	 * <br>
	 * Die Tabelle wird mit akuellen Werten der Kontenliste aktualisiert. <br>
	 * 
	 */
	private void tabelleAktualisieren() {
		TW_Kontenliste.setItems(kontoListe);
		columnKonto.setCellValueFactory(new PropertyValueFactory<Konto, String>("titel"));
		columnBeschreibung.setCellValueFactory(new PropertyValueFactory<Konto, String>("beschreibung"));
	}

	/**
	 * <i><b>Übergabe/Setzen der Konten</b></i><br>
	 * <br>
	 * Alle Bestandskonten einer Kontenliste werden dem Controller übergeben. <br>
	 * 
	 * @param kntList
	 * 			- ist eine Liste von Konten
	 */
	public void setKonten(HashMap<String, Konto> kntList) {

		for (String key : kntList.keySet()) {
			kontoListe.add(kntList.get(key));
		}
	}
	


	public ObservableList<Konto> getNeueKonten() {
		return neueKonten;
	}

	public boolean isKontenErstellt(){
		return isErstellt;
	}

}