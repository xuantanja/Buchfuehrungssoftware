package io;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import geschaeftsfall.Geschaeftsfall;
import konten.Konto;

public class DataStorage implements Serializable{

	private static final long serialVersionUID = 8441138232055888468L;
	private HashMap<String, Konto> konten;
	private ArrayList<Geschaeftsfall> faelle;
	private LocalDate geschaeftsjahrBeginn;

	public DataStorage(HashMap<String, Konto> konten, ArrayList<Geschaeftsfall> faelle, LocalDate geschaeftsjahrBeginn) {
		this.konten = new HashMap<>(konten);
		this.faelle = new ArrayList<>(faelle);
		this.geschaeftsjahrBeginn = geschaeftsjahrBeginn;
	}

	public HashMap<String, Konto> getKonten() {
		return konten;
	}

	public ArrayList<Geschaeftsfall> getFaelle() {
		return faelle;
	}

	public LocalDate getGeschaeftsjahrBeginn() {
		return geschaeftsjahrBeginn;
	}

}
