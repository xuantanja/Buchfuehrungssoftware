package konten;

public class Bestandskonto extends Konto{

	private double anfangsbestand;
	private boolean aktivkonto;

	public Bestandskonto(String titel, String kuerzel, String verrechnungskonto, int ID_T, double anfangsbestand, boolean aktivkonto) {
		super(titel,kuerzel, verrechnungskonto, ID_T);
		this.anfangsbestand= anfangsbestand;
		this.aktivkonto = aktivkonto;
	}

}
