package konten;

import java.io.Serializable;

public class Steuerkonto extends Konto implements Serializable{

	private static final int KONTENART_ID = 3;

	public Steuerkonto(String titel, String kuerzel, String verrechnungskonto) {
		super(titel, kuerzel, verrechnungskonto);
		setBeschreibung(description());
	}

	public int getKontoart() {
		return KONTENART_ID;
	}

	@Override
	public String description() {
		return "Steuerkonto: " + getKuerzel();
	}
}
