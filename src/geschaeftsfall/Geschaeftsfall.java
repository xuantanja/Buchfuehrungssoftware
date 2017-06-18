package geschaeftsfall;
import java.util.ArrayList;

public class Geschaeftsfall {

	private long ID;
	private String titel;
	private String beschreibung;
	private ArrayList<Buchungssatz> saetze;
	
	public Geschaeftsfall(String titel, String beschreibung){
		this.titel = titel; 
		this.beschreibung = beschreibung;
	}
	
	
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public String getBeschreibung() {
		return beschreibung;
	}
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	public ArrayList<Buchungssatz> getSaetze() {
		return saetze;
	}
	public void setSaetze(ArrayList<Buchungssatz> saetze) {
		this.saetze = saetze;
	}
	
	
	
}
