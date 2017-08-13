package application.menu.analyse;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import konten.Bestandskonto;
import konten.Konto;

public class EroeffnungsbilanzEinsehenController implements Initializable {

	@FXML
	private HBox HB_Cont;

	@FXML
	private GridPane GP_Aktiv;

	@FXML
	private GridPane GP_Passiv;

	private ObservableList<Bestandskonto> bKontoListe;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		bKontoListe = FXCollections.observableArrayList();

	}

	public void setKonten(HashMap<String, Konto> kntList) {
		ObservableList<Konto> keyListe = null;
		for (String key : kntList.keySet()) {
			keyListe.add(kntList.get(key));
		}

		for (Konto konto : keyListe) {

				// "1" entspricht der Kontoart Bestandskonto
				if (konto.getKontoart() == 1) {
					bKontoListe.add((Bestandskonto) konto);
				}

		}

	}

	public void befuelleGP(ObservableList<Konto> BkontoListe) {

		int j = 0;

		for (Bestandskonto konto : bKontoListe) {
			if (konto.isAktivkonto()) {
				Label kontoName = new Label(konto.getTitel());
				GP_Aktiv.add(kontoName, 1, j);
				
				Label kontoAB = new Label(Double.toString(konto.getAnfangsbestand()));
				GP_Aktiv.add(kontoAB, 2, j);
				
				
				//GP_Aktiv.setVgap(10);
				//GP_Aktiv.setHgap(40);
				//GP_Aktiv.setPadding(new Insets(10));
			}
			else{
				Label kontoName = new Label(konto.getTitel());
				GP_Passiv.add(kontoName, 1, j);
				
				Label kontoAB = new Label(Double.toString(konto.getAnfangsbestand()));
				GP_Passiv.add(kontoAB, 2, j);
			}
		}
	}

}
