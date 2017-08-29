package konten;

import java.io.Serializable;
/**
 * Die Klasse Erfolgskonto repräsentiert ein Konto, welches Aufwände und Erträge hat.
 */

public class Erfolgskonto extends Konto implements Serializable{

	private static final int KONTENART_ID = 2;
	private boolean ertragskonto;

	public Erfolgskonto(String titel, String kuerzel, String verrechnungskonto, boolean ertragskonto) {
		super(titel, kuerzel, verrechnungskonto);
		this.ertragskonto = ertragskonto;
		setBeschreibung(description());
	}

	public boolean isErtragskonto() {
		return ertragskonto;
	}

	public void setErtragskonto(boolean ertragskonto) {
		this.ertragskonto = ertragskonto;
	}

	public int getKontoart() {
		return KONTENART_ID;
	}
	
	/**
	 * <i><b>Vergabe der Beschreibung</b></i><br>
	 * <br>
	 * Es wird eine Beschreibung je nach Kontoart vergeben. <br>
	 *
	 * @return Beschreibung für das Konto
	 */
	@Override
	public String description() {
		String beschreibung = "";
		if(isErtragskonto()){
			beschreibung += "Ertragskonto ";
		} else{
			beschreibung += "Aufwandskonto ";
		}
		beschreibung += "; " + getTitel() + " (" + getKuerzel() + ") wird in das Konto " + getVerrechnungKonto() + " saldiert.";
		return beschreibung;
	}

}
