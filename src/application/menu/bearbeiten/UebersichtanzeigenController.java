package application.menu.bearbeiten;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import geschaeftsfall.Geschaeftsfall;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import konten.Konto;

/**
 * FXML Controller class
 *
 * @author tmanlik
 */
public class UebersichtanzeigenController implements Initializable {
	/**
	 * Initializes the controller class.
	 */
	private ArrayList<Geschaeftsfall> faelle;
	@FXML
	private ComboBox<String> CB_GF_auswaehlen;

	@FXML
	private Button BTN_alle_GF_anzeigen;

	@FXML
	private VBox VBox_Anzeige;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	@FXML
	void handle_alle_GF_anzeigen(ActionEvent event) {

		for (int i = 0; i < faelle.size(); i++) {
			Geschaeftsfall fall = faelle.get(i);
			// Titel der Geschäftsfälle in Label speichern
			Label lb1 = new Label(fall.getID() + ". " + fall.getTitel());
			lb1.setFont(Font.font("System", FontWeight.BOLD, 18));
			// Beschreibung der Geschäftsfälle in Label speichern
			Label lb2 = new Label(fall.getBeschreibung());

			VBox_Anzeige.getChildren().add(lb1);
			VBox_Anzeige.getChildren().add(lb2);

			/*
			 * if (!fall.getSaetze().equals(null)) { // Informationen zu den BS:
			 * Label lb3 = new Label(fall.getSaetze().get(i).getSollKonto());
			 * Label lb4 = new Label(fall.getSaetze().get(i).getHabenKonto());
			 * VBox_Anzeige.getChildren().add(lb3);
			 * VBox_Anzeige.getChildren().add(lb4); }
			 */

		}
	}

	public void setFaelle(ArrayList<Geschaeftsfall> gfall) {
		faelle = gfall;

		ArrayList<String> geschaeftsfaelle = new ArrayList<>();

		for (Geschaeftsfall fall : faelle) {
			geschaeftsfaelle.add(fall.getTitel());
		}
		CB_GF_auswaehlen.setItems((ObservableList<String>) FXCollections.observableArrayList(geschaeftsfaelle));
	}

}
