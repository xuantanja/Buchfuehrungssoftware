package konten;

public class Steuerkonto extends Konto {

	private static final int KONTENART_ID = 3;
	private double steuersatz;

	public Steuerkonto(String titel, String kuerzel, String verrechnungskonto, double steuersatz) {
		super(titel, kuerzel, verrechnungskonto);
		this.steuersatz = steuersatz;
		setBeschreibung(description());
	}

	public int getKontoart() {
		return KONTENART_ID;
	}

	@Override
	public String description() {
		return "standardm‰ﬂiger Steuersatz: " + steuersatz;
	}
}
