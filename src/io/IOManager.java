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
import utility.alertDialog.AlertDialogFrame;
/**
 * 
 * Der IOManager stellt Methoden für das Speichern und Lesen von Dateien zur Verfügung.
 *
 */
public class IOManager {
	/**
	 * <i><b>Lesen einer Datei</b></i><br>
	 * <br>
	 * Es wird eine Datei von einem übergebenen Pfad eingelesen. <br>
	 * 
	 * @param destination
	 * 			- Ordner der einzulesenden Datei
	 * @return eine Datei mit Konten, Fällen und Geschäftsjahr
	 * @throws IOException
	 */
	public static DataStorage readFile(File destination) throws IOException {
		try {
			ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(destination));
			DataStorage ds = ((DataStorage) fileReader.readObject());
			fileReader.close();
			return ds;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		throw new IOException();
	}
	/**
	 * <i><b>Speichern einer Datei</b></i><br>
	 * <br>
	 * In eine Datei werden übergebene Informationen geschrieben und unter einem übergebenen Pfad gespeichert. <br>
	 * 
	 * @param faelle
	 * 			- alle Geschäftsfälle
	 * @param konten
	 * 			- alle Konten
	 * @param destination
	 * 			- Ordner der einzulesenden Datei
	 * @param gjBeginn
	 * 			- Beginn des Geschäftsjahres
	 * @return ob die Datei erfolgreich erstellt wurde
	 */
	public static boolean saveFile(HashMap<String,Konto> faelle, ArrayList<Geschaeftsfall> konten, File destination, LocalDate gjBeginn) {
		try {
			ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(destination));
			fileWriter.writeObject(new DataStorage(faelle, konten, gjBeginn));
			fileWriter.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
