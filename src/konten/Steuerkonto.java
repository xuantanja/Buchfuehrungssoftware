package konten;

public class Steuerkonto extends Konto{

	private boolean steuersatz;

	public Steuerkonto(String titel, String kuerzel, String verrechnungskonto, int ID_T, boolean steuersatz) {
		super(titel, kuerzel, verrechnungskonto, ID_T);
		this.steuersatz = steuersatz;
	}

}
