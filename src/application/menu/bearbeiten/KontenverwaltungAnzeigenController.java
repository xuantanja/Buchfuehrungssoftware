package application.menu.bearbeiten;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import konten.Konto;

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

	@FXML
	void handle_PDFSpeichern(ActionEvent event) {
		try {
		    BufferedImage bufImage = SwingFXUtils.fromFXImage(exportNodes.get(0).snapshot(new SnapshotParameters(), null), null);
		    FileOutputStream out = new FileOutputStream(new File(STANDARD_PATH + "/temp.jpg"));
		    javax.imageio.ImageIO.write(bufImage, "jpg", out);
		    out.flush();
		    out.close();


		}
		catch(Exception e)
		{
		     e.printStackTrace();
		}
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
	
	public void setExportNodes(Node ... node){
		for (int i = 0; i < node.length; i++) {
			exportNodes.add(node[i]);
		}
	}

	public ObservableList<Konto> getNeueKonten() {
		return neueKonten;
	}

	public boolean isKontenErstellt(){
		return isErstellt;
	}

}