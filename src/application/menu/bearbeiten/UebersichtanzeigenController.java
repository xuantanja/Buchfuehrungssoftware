package application.menu.bearbeiten;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import geschaeftsfall.Geschaeftsfall;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

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
