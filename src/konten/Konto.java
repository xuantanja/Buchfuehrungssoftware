package konten;

import geschaeftsfall.Buchungssatz;

public class Konto {

	private int ID_T;
	private String titel;
	public int getID_T() {
		return ID_T;
	}
	
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


	public void setID_T(int iD_T) {
		ID_T = iD_T;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public Kontoseite getSollSeite() {
		return sollSeite;
	}

	public void setSollSeite(Kontoseite sollSeite) {
		this.sollSeite = sollSeite;
	}

	public Kontoseite getHabenSeite() {
		return habenSeite;
	}

	public void setHabenSeite(Kontoseite habenSeite) {
		this.habenSeite = habenSeite;
	}

	public String getVerrechnungKonto() {
		return verrechnungKonto;
	}

	public void setVerrechnungKonto(String verrechnungKonto) {
		this.verrechnungKonto = verrechnungKonto;
	}

	private void saldieren() {

	}

	private void buchung(Buchungssatz bsatz) {
		
	}
	
	//isi hier die Buchung im Verrechnungskonto gemeint?
	private void rueckbuchung(Buchungssatz bsatz) {

	}

}
