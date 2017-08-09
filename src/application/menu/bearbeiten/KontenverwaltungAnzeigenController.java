package application.menu.bearbeiten;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import konten.Konto;
import application.menu.datei.BilanzErstellenController;

public class KontenverwaltungAnzeigenController {




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
	
	private ArrayList<Konto> kontoListe;

	public void initialize(URL location, ResourceBundle resources) {
		//tabelleAktualisieren();
	}
    @FXML
    void handle_KontoHinzufuegen(ActionEvent event) {

    }

    @FXML
    void handle_PDFSpeichern(ActionEvent event) {

    }
/*	private void tabelleAktualisieren() {
		TW_Kontenliste.getItems().clear();
		ObservableList<Konto> obsList = FXCollections.observableArrayList(kontoListe);
		TW_Kontenliste.setItems(obsList);
		columnKonto.setCellValueFactory(new PropertyValueFactory<Konto, String>("titel"));
		columnBeschreibung.setCellValueFactory(new PropertyValueFactory<Konto, String>("beschreibung"));
		ArrayList<String> kontenKuerzel = new ArrayList<>();
		kontenKuerzel.add("SBK");
		kontenKuerzel.add("GuV");
		for (Konto konto : kontoListe) {
			kontenKuerzel.add(konto.getKuerzel());
		}
	//	verrechnungskonto.setItems(FXCollections.observableArrayList(kontenKuerzel));
	}
	*/
    
	//Kontenliste übergeben lassen und in einer ArrayListe speichern
	public void getKonten(HashMap<String,Konto> kntList){
	    for(String key : kntList.keySet())
	    {
	      kontoListe.add(kntList.get(key)); 
	    }
	}


}