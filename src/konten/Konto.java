package konten;

import geschaeftsfall.Buchungssatz;

public class Konto {

	private int ID_T;
	private String titel;
	private Kontoseite sollSeite;
	private Kontoseite habenSeite;
	private String verrechnungKonto;

	public Konto(int ID_T, String titel, Kontoseite sollSeite, Kontoseite habenseite, String verrechnungskonto) {

		this.ID_T= ID_T;
		this.titel= titel;
		this.sollSeite = sollSeite;
		this.habenSeite = habenseite;
		this.verrechnungKonto = verrechnungskonto;
	}

	private void saldieren() {

	}

	private void buchung(Buchungssatz bsatz) {

	}

	private void rueckbuchung(Buchungssatz bsatz) {

	}

}
