package konten;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import geschaeftsfall.Buchungssatz;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import konten.gui.Kontenbilanzierung;
import konten.gui.KontoContainer;

public abstract class Konto implements Serializable {

	private String titel;
	private String kuerzel;
	private Kontoseite sollSeite;
	private Kontoseite habenSeite;
	private String verrechnungKonto;
	private KontoContainer guiContainer;
	private String beschreibung;
	private double bilanzwert;

	public abstract int getKontoart();

	public abstract String description();

	public Konto(String titel, String kuerzel, String verrechnungskonto) {
		this.titel = titel;
		this.kuerzel = kuerzel;
		this.verrechnungKonto = verrechnungskonto;
		guiContainer = new KontoContainer(titel);
		sollSeite = new Kontoseite(true);
		habenSeite = new Kontoseite(false);
		bilanzwert = -1;
	}

	public Buchungssatz saldieren() {
		double sollBetrag = sollSeite.getBetragssumme();
		double habenBetrag = habenSeite.getBetragssumme();
		if(getKontoart() == 1){
			if(((Bestandskonto) this).isAktivkonto()){
				sollBetrag += ((Bestandskonto) this).getAnfangsbestand();
			} else{
				habenBetrag += ((Bestandskonto) this).getAnfangsbestand();
			}
		}
		System.out.println(sollBetrag + "\t\t" + habenBetrag +"\t\t" + titel);
		if (sollBetrag > habenBetrag) {
			bilanzwert = sollBetrag;
			return new Buchungssatz("", verrechnungKonto, kuerzel, sollBetrag - habenBetrag);
		} else if (sollBetrag < habenBetrag){
			bilanzwert = habenBetrag;
			return new Buchungssatz("", kuerzel, verrechnungKonto, habenBetrag - sollBetrag);
		} else{
			bilanzwert = 0;
		}
		return null;
		
	}

	public void buchung(Buchungssatz bsatz, boolean sollseite) {
		if (sollseite) {
			sollSeite.getBuchungen().put(bsatz.getID(), bsatz);
			guiContainer.getRefNameS().getChildren()
					.add(new Label(bsatz.getID() + " " + bsatz.getHabenKonto() + "    "));
			guiContainer.getRefBetragS().getChildren().add(new Label(Double.toString(bsatz.getBetrag()) + " €"));
		} else {
			habenSeite.getBuchungen().put(bsatz.getID(), bsatz);
			guiContainer.getRefNameH().getChildren()
					.add(new Label(bsatz.getID() + " " + bsatz.getSollKonto() + "    "));
			guiContainer.getRefBetragH().getChildren().add(new Label(Double.toString(bsatz.getBetrag()) + " €"));
		}
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

	public VBox getGUIComponents() {
		return guiContainer.getLayout(bilanzwert);
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public KontoContainer getGuiContainer() {
		return guiContainer;
	}
	
	public void setGuiContainer(KontoContainer guiContainer) {
		this.guiContainer = guiContainer;
	}

	public void setKuerzel(String kuerzel) {
		this.kuerzel = kuerzel;
	}

	public void newContainer() {
		guiContainer = new KontoContainer(titel);
		for (Buchungssatz bsatz : sollSeite.getArrayOfBuchungen()) {
			guiContainer.getRefNameS().getChildren()
					.add(new Label(bsatz.getID() + " " + bsatz.getHabenKonto() + "    "));
			guiContainer.getRefBetragS().getChildren().add(new Label(Double.toString(bsatz.getBetrag()) + "€"));
		}
		for (Buchungssatz bsatz : habenSeite.getArrayOfBuchungen()) {
			guiContainer.getRefNameH().getChildren()
					.add(new Label(bsatz.getID() + " " + bsatz.getSollKonto() + "    "));
			guiContainer.getRefBetragH().getChildren().add(new Label(Double.toString(bsatz.getBetrag()) + "€"));
		}
	}

}
