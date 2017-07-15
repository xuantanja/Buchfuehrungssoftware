package konten;

import geschaeftsfall.Buchungssatz;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public abstract class Konto {

	private int ID_T; // ID in welchen Tab das Konto gehört
	private String titel;
	private String kuerzel;
	private Kontoseite sollSeite;
	private Kontoseite habenSeite;
	private String verrechnungKonto;
	private KontoContainer guiContainer;
	private String beschreibung;
	
	public abstract int getKontoart();
	public abstract String description();

	public Konto(String titel, String kuerzel, String verrechnungskonto) {
		this.ID_T = ID_T;
		this.titel = titel;
		this.kuerzel = kuerzel;
		this.verrechnungKonto = verrechnungskonto;
		guiContainer = new KontoContainer(titel);
		sollSeite = new Kontoseite(true);
		habenSeite = new Kontoseite(false);
	}

	public void saldieren() {
		double sollBetrag = sollSeite.getBetragssumme();
		double habenBetrag = habenSeite.getBetragssumme();

		if (sollBetrag > habenBetrag) {
			// habenSeite.setSalidierungsbetrag(verrechnungKonto, sollBetrag -
			// habenBetrag);
			// sollSeite.setBilanzsumme(sollBetrag);
			// habenSeite.setBilanzsumme(sollBetrag);
		} else if (sollBetrag < habenBetrag) {
			// sollSeite.setSalidierungsbetrag(verrechnungKonto, habenBetrag -
			// sollBetrag);
			// sollSeite.setBilanzsumme(habenBetrag);
			// habenSeite.setBilanzsumme(habenBetrag);
		} else {
			// sollSeite.setBilanzsumme(sollBetrag);
			// habenSeite.setBilanzsumme(sollBetrag);
		}
		System.out.println(titel);
		System.out.println("Sollseite: " + sollBetrag);
		System.out.println("Habenseite: " + habenBetrag);
		System.out.println(verrechnungKonto + " " + (sollBetrag - habenBetrag));
		System.out.println();
	}

	public void buchung(Buchungssatz bsatz, boolean sollseite) {
		if (sollseite) {
			sollSeite.getBuchungen().put(bsatz.getID(), bsatz);
			guiContainer.getRefNameS().getChildren().add(new Label(bsatz.getID() + " " + bsatz.getHabenKonto() + "    "));
			guiContainer.getRefBetragS().getChildren().add(new Label(Double.toString(bsatz.getBetrag()) + " €"));
		} else {
			habenSeite.getBuchungen().put(bsatz.getID(), bsatz);
			guiContainer.getRefNameH().getChildren().add(new Label(bsatz.getID() + " " + bsatz.getHabenKonto() + "    "));
			guiContainer.getRefBetragH().getChildren().add(new Label(Double.toString(bsatz.getBetrag())+ " €"));
		}
		//guiContainer.refresh();
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
	
	public String getKuerzel() {
		return kuerzel;
	}

	public VBox getGUIComponents(){
		return guiContainer.getLayout();
	}
	public String getBeschreibung() {
		return beschreibung;
	}
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	
	

}
