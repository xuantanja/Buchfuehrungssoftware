package konten;

public class Erfolgskonto extends Konto{

	private boolean ertragskonto;

	public Erfolgskonto(String titel, String kuerzel, String verrechnungskonto, int ID_T, boolean ertragskonto) {
		super(titel, kuerzel, verrechnungskonto, ID_T);
		this.ertragskonto = ertragskonto;
	}

}
