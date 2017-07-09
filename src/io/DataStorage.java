package io;

import java.util.ArrayList;
import java.util.HashMap;

import geschaeftsfall.Geschaeftsfall;
import konten.Konto;

public class DataStorage {
	
	private HashMap<String, Konto> konten;
	private ArrayList<Geschaeftsfall> faelle;

	public DataStorage(HashMap<String, Konto> konten, ArrayList<Geschaeftsfall> faelle) {
		this.konten = new HashMap<>(konten);
		this.faelle = new ArrayList<>(faelle);
	}

	public HashMap<String, Konto> getKonten() {
		return konten;
	}

	public ArrayList<Geschaeftsfall> getFaelle() {
		return faelle;
	}
	
}
