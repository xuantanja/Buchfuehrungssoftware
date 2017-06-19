package application;

import geschaeftsfall.Buchungssatz;
import geschaeftsfall.Geschaeftsfall;
import javafx.application.Application;
import javafx.stage.Stage;
import konten.Konto;

public class GUI extends Application{

	public static void main(String[] args) {
		Kontenverwaltung kw = new Kontenverwaltung();
		Konto bank = new Konto("Bank","SBK",1);
		Konto verb = new Konto("Verb","SBK",1);
		Konto ford = new Konto("Ford","SBK",1);
		kw.addKonto(bank);
		kw.addKonto(verb);
		kw.addKonto(ford);
		
		Buchungssatz b1 = new Buchungssatz("Verkauf von etwas", "Bank", "Verb", 100.0);
		Buchungssatz b4 = new Buchungssatz("Verkauf von etwas", "Bank", "Verb", 300.0);
		Buchungssatz b2 = new Buchungssatz("Kauf von esffgfesg", "Verb", "Bank", 2.0);
		Buchungssatz b3 = new Buchungssatz("Neue Forderung", "Ford", "Bank", 50.0);
		Geschaeftsfall g1 = new Geschaeftsfall(1,"Eine groﬂe Sache", "Verkauf/Kauf/usw.");
		kw.addGeschaeftsfall(g1);
		kw.addBuchungssatz(g1, b1);
		kw.addBuchungssatz(g1, b2);
		kw.addBuchungssatz(g1, b3);
		kw.addBuchungssatz(g1, b4);
		
		kw.kontensaldierung();
		Application.launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
}
