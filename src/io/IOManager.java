package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import geschaeftsfall.Geschaeftsfall;
import konten.Konto;

public class IOManager {
	
	public static DataStorage readFile(File destination) {
		try {
			ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(destination));
			DataStorage ds = ((DataStorage) fileReader.readObject());
			fileReader.close();
			return ds;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void saveFile(HashMap<String,Konto> faelle, ArrayList<Geschaeftsfall> konten, File destination, LocalDate gjBeginn) {
		try {
			ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(destination));
			fileWriter.writeObject(new DataStorage(faelle, konten, gjBeginn));
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
