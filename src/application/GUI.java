package application;

import geschaeftsfall.Buchungssatz;
import geschaeftsfall.Geschaeftsfall;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import konten.Bestandskonto;
import konten.Konto;

public class GUI extends Application {

	public static HostServices services;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		services = this.getHostServices();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("BuFue.fxml"));
		Scene scene = new Scene(loader.load());
		primaryStage.setScene(scene);
		primaryStage.setTitle("BuFü HWR Version");
		primaryStage.show();

//		Kontenverwaltung kw = new Kontenverwaltung();
//		Konto bank = new Bestandskonto("Bank", "Bank", "SBK", 1000, true);
//		Konto kasse = new Bestandskonto("Kasse", "Kasse", "SBK", 300, true);
//		Konto verb = new Bestandskonto("Verbindlichkeiten", "Verb", "SBK", 400, false);
//		Konto ford = new Bestandskonto("Forderungen", "Ford", "SBK", 2000, true);
//		kw.addKonto(bank);
//		kw.addKonto(verb);
//		kw.addKonto(ford);
//		kw.addKonto(kasse);
//
//		Buchungssatz b1 = new Buchungssatz("Verkauf von etwas", "Bank", "Verb", 100.0);
//		Buchungssatz b4 = new Buchungssatz("Verkauf von etwas", "Bank", "Verb", 300.0);
//		Buchungssatz b2 = new Buchungssatz("Kauf von esffgfesg", "Verb", "Bank", 2.0);
//		Buchungssatz b5 = new Buchungssatz("Kauf von abc", "Verb", "Kasse", 105.99);
//		Buchungssatz b3 = new Buchungssatz("Neue Forderung", "Ford", "Bank", 50.0);
//		Geschaeftsfall g1 = new Geschaeftsfall(1, "Eine große Sache", "Verkauf/Kauf/usw.");
//		kw.addGeschaeftsfall(g1);
//		kw.addBuchungssatz(g1, b1);
//		kw.addBuchungssatz(g1, b2);
//		kw.addBuchungssatz(g1, b3);
//		kw.addBuchungssatz(g1, b4);
//		kw.addBuchungssatz(g1, b5);
//		controller.getT1_P().getChildren().add(bank.getGUIComponents());
//		controller.getT1_P().getChildren().add(verb.getGUIComponents());
//		controller.getT1_A().getChildren().add(ford.getGUIComponents());
//		controller.getT1_A().getChildren().add(kasse.getGUIComponents());
//		kw.kontensaldierung();
	}

}
