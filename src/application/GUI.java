package application;

import geschaeftsfall.Buchungssatz;
import geschaeftsfall.Geschaeftsfall;

public class GUI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Geschaeftsfall g1 = new Geschaeftsfall(1, "Titel", "Beschr");
		Buchungssatz b1 = new Buchungssatz();
		Buchungssatz b2 = new Buchungssatz();
		g1.addBuchung(b1);
		g1.addBuchung(b2);
		System.out.println(b1.getID());
		System.out.println(b2.getID());
	}
	
}
