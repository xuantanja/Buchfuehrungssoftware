package application.menu.bearbeiten;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import konten.Bestandskonto;
import konten.Erfolgskonto;
import konten.Konto;

public class KontoHinzufuegenController implements Initializable{


    @FXML
    private TextField textfieldKontenname;

    @FXML
    private TextField textfieldKuerzel;

    @FXML
    private Button buttonAddKonto;

    @FXML
    private TextField textfieldAB;

    @FXML
    private RadioButton radioAktivkonto;

    @FXML
    private RadioButton radioPassivkonto;

    @FXML
    private RadioButton radioErtragskonto;

    @FXML
    private RadioButton radioAufwandskonto;

    @FXML
    private RadioButton radioBestandskonto;

    @FXML
    private RadioButton radioErfolgskonto;

    @FXML
    private ComboBox<String> verrechnungskonto;
    
    private ObservableList<String> kontoListe;
    private ObservableList<Konto>  kontenListe;

    @FXML
    void handle_KontoHinzufuegen(ActionEvent event) {
		String fehlermeldung = "";
		if (textfieldKuerzel.getText().length() > 6) {
			fehlermeldung += "Das Kürzel besitzt mehr als 6 Zeichen!";
		}
		if (textfieldKontenname.getText().length() == 0) {
			fehlermeldung += "Keinen Kontonamen angegeben!";
		}
		// Fehlerüberprüfung abgeschlossen
		if (fehlermeldung.equals("")) {
			if (radioBestandskonto.isSelected()) {
				Bestandskonto newBKonto = new Bestandskonto(textfieldKontenname.getText(), textfieldKuerzel.getText(),
						"SBK", Double.parseDouble(textfieldAB.getText()), radioAktivkonto.isSelected());
				kontenListe.add(newBKonto);
			} else if (radioErfolgskonto.isSelected()) {
				Erfolgskonto newEKonto = new Erfolgskonto(textfieldKontenname.getText(), textfieldKuerzel.getText(),
						verrechnungskonto.getValue(), radioErtragskonto.isSelected());
				kontenListe.add(newEKonto);
			}
		}
    }

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		kontoListe =  FXCollections.observableArrayList();
		
	}
	public void setKonten(ObservableList<Konto>  kntList) {

		 kontenListe =   kntList;
		 
		//ObservableList<Konto> zu ObservableList<String> mithilfe Iterator
		  Iterator<Konto> iter = kntList.iterator(); 
		  int x = 0;
	        while (iter.hasNext()) { 	        	
	        	kontoListe.add(kntList.get(x).getTitel());	        
	        	x++;
	        	iter.next();
	        }
	        //ComboBox verrechnungsKonto werden die bereits bestehenden Konten übergeben
			verrechnungskonto.setItems((ObservableList<String>) FXCollections.observableList(kontoListe));

		}
}

