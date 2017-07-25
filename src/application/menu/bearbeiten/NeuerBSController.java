package application.menu.bearbeiten;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import konten.Konto;
import utility.converter.TypeConverter;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.ResourceBundle;

import geschaeftsfall.Buchungssatz;
import geschaeftsfall.Geschaeftsfall;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;

public class NeuerBSController implements Initializable {

	private ObservableList<String> comboItemsList;
	private ArrayList<ComboBox<String>> comboListSoll, comboListHaben;
	private ArrayList<TextField> betragListSoll, betragListHaben;
	private ArrayList<Double> betragSoll, betragHaben;
	private HashMap<String, Buchungssatz> buchungss‰tze;
	private int rowSoll, rowHaben;
	@FXML
	private TextField textfieldTitel;
	@FXML
	private ComboBox<String> comboSoll;
	@FXML
	private ComboBox<String> comboHaben;
	@FXML
	private TextField textfieldBetragHaben;
	@FXML
	private TextField textfieldBetragSoll;
	@FXML
	private Button buttonPlusSoll;
	@FXML
	private Button buttonPlusHaben;
	@FXML
	private ComboBox<String> comboGF;
	@FXML
	private CheckBox checkboxBetragGleichsetzen;
	@FXML
	private Button buttonHinzuf¸gen;
	@FXML
	private Button buttonSchliessen;
	@FXML
	private GridPane gridpaneBS;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		buttonPlusSoll.setOnAction(e -> handleAddRow(true));
		buttonPlusHaben.setOnAction(e -> handleAddRow(false));
		comboListSoll = new ArrayList<>();
		comboListHaben = new ArrayList<>();
		betragListSoll = new ArrayList<>();
		betragListHaben = new ArrayList<>();
		buchungss‰tze = new HashMap<>();
		rowSoll = 1;
		rowHaben = 1;

		comboListSoll.add(comboSoll);
		comboListHaben.add(comboHaben);
		betragListSoll.add(textfieldBetragSoll);
		betragListHaben.add(textfieldBetragHaben);
	}

	private void handleAddRow(boolean soll) {
		ComboBox<String> combobox = new ComboBox<>(comboItemsList);
		TextField textfield = new TextField();
		Label euro = new Label();
		euro.setFont(new Font(15));
		textfield.setPromptText("Betrag");
		combobox.setPrefWidth(125);
		GridPane.setMargin(combobox, new Insets(10, 0, 0, 0));
		if (soll) {
			comboListSoll.add(combobox);
			betragListSoll.add(textfield);
			euro.setText("Ä  an");

			gridpaneBS.add(combobox, 0, rowSoll);
			gridpaneBS.add(textfield, 1, rowSoll);
			gridpaneBS.add(euro, 3, rowSoll++);
			gridpaneBS.getChildren().remove(buttonPlusSoll);
			gridpaneBS.add(buttonPlusSoll, 0, rowSoll);

		} else {
			comboListHaben.add(combobox);
			betragListHaben.add(textfield);
			euro.setText("Ä");

			gridpaneBS.add(combobox, 4, rowHaben);
			gridpaneBS.add(textfield, 5, rowHaben);
			gridpaneBS.add(euro, 7, rowHaben++);
			gridpaneBS.getChildren().remove(buttonPlusHaben);
			gridpaneBS.add(buttonPlusHaben, 4, rowHaben);
		}
	}

	public void setParameter(HashMap<String, Konto> konten, ArrayList<Geschaeftsfall> faelle) {
		comboItemsList = FXCollections
				.observableArrayList(new TypeConverter<String, Konto>().hashmapkeysToArrayList(konten));
		comboSoll.setItems(comboItemsList);
		comboHaben.setItems(comboItemsList);

		ArrayList<String> geschaeftsfaelle = new ArrayList<>();
		for (Geschaeftsfall fall : faelle) {
			geschaeftsfaelle.add(fall.getTitel());
		}
		comboGF.setItems(FXCollections.observableArrayList(geschaeftsfaelle));
	}

	// Event Listener on Button[#buttonHinzuf¸gen].onAction
	@FXML
	public void handleHinzuf¸gen(ActionEvent event) {
		String ID = comboGF.getSelectionModel().getSelectedIndex() + ".";
		betragSoll = toDoubleArraylist(betragListSoll);
		betragHaben = toDoubleArraylist(betragListHaben);
		System.out.println("Size: " + betragSoll.size() + "    " + betragHaben.size());
		int sollPos = 0, habenPos = 0;
		boolean isSollBigger = betragSoll.get(sollPos) > betragHaben.get(habenPos);
		putBuchungssatz(sollPos, habenPos, isSollBigger);
		test();
	}

	private void putBuchungssatz(int sollPos, int habenPos, boolean isSollBigger) {
		double buchungsbetrag;
		String sollKonto, habenKonto;
		isSollBigger = betragSoll.get(sollPos) > betragHaben.get(habenPos);
		if (isSollBigger) {
			buchungsbetrag = betragHaben.get(habenPos);
			betragSoll.set(sollPos, betragSoll.get(sollPos) - betragHaben.get(habenPos));
			
			sollKonto = comboListSoll.get(sollPos).getSelectionModel().getSelectedItem();
			habenKonto = comboListHaben.get(habenPos).getSelectionModel().getSelectedItem();
			if (!isSollBigger) {
				sollPos++;
			}
			habenPos++;

		} else {
			buchungsbetrag = betragSoll.get(sollPos);
			betragHaben.set(habenPos, betragHaben.get(habenPos) - betragSoll.get(sollPos));
			
			sollKonto = comboListSoll.get(sollPos).getSelectionModel().getSelectedItem();
			habenKonto = comboListHaben.get(habenPos).getSelectionModel().getSelectedItem();
			if (isSollBigger) {
				habenPos++;
			}
			sollPos++;
		}

		Buchungssatz bs = new Buchungssatz(textfieldTitel.getText(), sollKonto, habenKonto, buchungsbetrag);
		buchungss‰tze.put("0", bs);
		System.out.println(
				"Buchungssatz:  " + bs.getSollKonto() + " an " + bs.getHabenKonto() + "  " + bs.getBetrag() + " Ä");
		if (sollPos == rowSoll - 1 && habenPos == rowHaben - 1) {
			Buchungssatz bs2 = new Buchungssatz(textfieldTitel.getText(),
					comboListSoll.get(sollPos).getSelectionModel().getSelectedItem(),
					comboListHaben.get(habenPos).getSelectionModel().getSelectedItem(), betragSoll.get(sollPos));
			System.out.println("Buchungssatz:  " + bs2.getSollKonto() + " an " + bs2.getHabenKonto() + "  "
					+ bs2.getBetrag() + " Ä");
			buchungss‰tze.put("0", bs2);
		} else if(!(rowSoll == 1 && rowHaben == 1)) {
			putBuchungssatz(sollPos, habenPos, isSollBigger);
		}
	}

	private void test() {
		System.out.println("---------------------------------------------------");
		Iterator<Buchungssatz> it = buchungss‰tze.values().iterator();
		while (it.hasNext()) {
			Buchungssatz b = it.next();
			System.out.println(
					"Buchungssatz:  " + b.getSollKonto() + " an " + b.getHabenKonto() + "  " + b.getBetrag() + " Ä");
		}
		System.out.println("---------------------------------------------------");
	}

	private ArrayList<Double> toDoubleArraylist(ArrayList<TextField> list) {
		ArrayList<Double> doublearraylist = new ArrayList<>();
		for (TextField tf : list) {
			doublearraylist.add(Double.parseDouble(tf.getText()));

		}
		return doublearraylist;
	}

	// Event Listener on Button[#buttonSchliessen].onAction
	@FXML
	public void handleSchlieﬂen(ActionEvent event) {
		((Stage) buttonSchliessen.getScene().getWindow()).close();
	}

	// Event Listener on Button[#checkboxBetragGleichsetzen].onAction
	@FXML
	public void handleSelectionBetragGleichsetzen(ActionEvent event) {
		if (checkboxBetragGleichsetzen.isSelected()) {
			// TODO
		}
	}

}
