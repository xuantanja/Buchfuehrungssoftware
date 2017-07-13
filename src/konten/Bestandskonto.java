package konten;

public class Bestandskonto extends Konto {

	private static final int KONTENART_ID = 1;
	private double anfangsbestand;
	private boolean aktivkonto;

	public Bestandskonto(String titel, String kuerzel, String verrechnungskonto, double anfangsbestand,
			boolean aktivkonto) {
		super(titel, kuerzel, verrechnungskonto);
		this.anfangsbestand = anfangsbestand;
		this.aktivkonto = aktivkonto;
		setBeschreibung(description());
	}

	public double getAnfangsbestand() {
		return anfangsbestand;
	}

	public void setAnfangsbestand(double anfangsbestand) {
		this.anfangsbestand = anfangsbestand;
	}

	public boolean isAktivkonto() {
		return aktivkonto;
	}

	public void setAktivkonto(boolean aktivkonto) {
		this.aktivkonto = aktivkonto;
	}

	public int getKontoart() {
		return KONTENART_ID;
	}

	@Override
	public String description() {
		String beschreibung = "";
		if(isAktivkonto()){
			beschreibung += "aktives ";
		} else{
			beschreibung += "pasives ";
		}
		beschreibung += "Bestandskonto; Anfangsbestand: " + getAnfangsbestand() + "; " + getTitel() + " (" + getKuerzel() + ") wird in das Konto " + getVerrechnungKonto() + " saldiert.";
		return beschreibung;
	}

}
