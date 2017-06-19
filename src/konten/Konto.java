package konten;

import geschaeftsfall.Buchungssatz;

public class Konto {

	private int ID_T; // ID in welchen Tab das Konto gehört
	private String titel;
	private Kontoseite sollSeite;
	private Kontoseite habenSeite;
	private String verrechnungKonto;

	public Konto(String titel, String verrechnungskonto,int ID_T) {
		this.ID_T = ID_T;
		this.titel = titel;
		this.verrechnungKonto = verrechnungskonto;
		
		
		sollSeite = new Kontoseite(true);
		habenSeite = new Kontoseite(false);
	}

	public void saldieren() {
		double sollBetrag = sollSeite.getBetragssumme();
		double habenBetrag = habenSeite.getBetragssumme();

		if (sollBetrag > habenBetrag) {
			habenSeite.setSalidierungsbetrag(verrechnungKonto, sollBetrag - habenBetrag);
			sollSeite.setBilanzsumme(sollBetrag);
			habenSeite.setBilanzsumme(sollBetrag);
		} else if (sollBetrag < habenBetrag) {
			sollSeite.setSalidierungsbetrag(verrechnungKonto, habenBetrag - sollBetrag);
			sollSeite.setBilanzsumme(habenBetrag);
			habenSeite.setBilanzsumme(habenBetrag);
		} else {
			sollSeite.setBilanzsumme(sollBetrag);
			habenSeite.setBilanzsumme(sollBetrag);
		}
		System.out.println(titel);
		System.out.println("Sollseite: " + sollBetrag);
		System.out.println("Habenseite: " + habenBetrag);
		System.out.println(verrechnungKonto + " " + (sollBetrag - habenBetrag));
		System.out.println();
	}

	public void buchung(Buchungssatz bsatz, boolean sollseite) {
		if (sollseite) {
			sollSeite.addBuchungssatz(bsatz);
		} else {
			habenSeite.addBuchungssatz(bsatz);
		}
	}

	// isi hier die Buchung im Verrechnungskonto gemeint?
	public void rueckbuchung(Buchungssatz bsatz) {

	}

	public int getID_T() {
		return ID_T;
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

	public Kontoseite getHabenSeite() {
		return habenSeite;
	}

	public String getVerrechnungKonto() {
		return verrechnungKonto;
	}

	public void setVerrechnungKonto(String verrechnungKonto) {
		this.verrechnungKonto = verrechnungKonto;
	}

}
