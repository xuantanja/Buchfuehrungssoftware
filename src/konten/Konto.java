package konten;

import java.io.Serializable;
import geschaeftsfall.Buchungssatz;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import konten.gui.KontoContainer;

/**
 * Die Klasse Konto representiert ein Konto innerhalb einer Bilanz.
 */
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

	/**
	 * <i><b>Kontensaldierung</b></i><br>
	 * <br>
	 * Saldiert das Konto und gibt den Buchungssatz zurück, in dem der
	 * Saldierungsbetrag in das Verrechnungskonto gebucht wird. <br>
	 * 
	 * @return Buchungsatz mit dem Saldierungsbetrag
	 */
	public Buchungssatz saldieren() {
		double sollBetrag = sollSeite.getBetragssumme();
		double habenBetrag = habenSeite.getBetragssumme();
		if (getKontoart() == 1) {
			if (((Bestandskonto) this).isAktivkonto()) {
				sollBetrag += ((Bestandskonto) this).getAnfangsbestand();
			} else {
				habenBetrag += ((Bestandskonto) this).getAnfangsbestand();
			}
		}
		System.out.println("[ABSCHLUSS]" + getTitel());
		if (sollBetrag > habenBetrag) {
			bilanzwert = sollBetrag;
			return new Buchungssatz("", verrechnungKonto, kuerzel, sollBetrag - habenBetrag);
		} else if (sollBetrag < habenBetrag) {
			bilanzwert = habenBetrag;
			return new Buchungssatz("", kuerzel, verrechnungKonto, habenBetrag - sollBetrag);
		} else {
			bilanzwert = sollBetrag;
		}
		return null;
	}

	/**
	 * <i><b>Buchung eines Buchungssatzes</b></i><br>
	 * <br>
	 * Bucht den Buchungssatz auf die entsprechende Seite des Kontos. <br>
	 * 
	 * @param bsatz
	 *            - der Buchungssatz, der gebucht werden soll
	 * @param sollseite
	 *            - die Seite auf der der Buchungssatz gebucht wird
	 * 
	 */
	public void buchung(Buchungssatz bsatz, boolean sollseite) {
		if (sollseite) {
			sollSeite.getBuchungen().put(bsatz.getID(), bsatz);
			addBuchungssatzToContainer(bsatz, true);
		} else {
			habenSeite.getBuchungen().put(bsatz.getID(), bsatz);
			addBuchungssatzToContainer(bsatz, false);
		}
	}

	/**
	 * <i><b>Erstellung eines neuen Containers für die GUI</b></i><br>
	 * <br>
	 * Die Benutzeroberfläche des Kontos wird neu erstellt. Wird für das Öffnen
	 * einer Bilanz benötigt. <br>
	 */
	public void newContainer() {
		guiContainer = new KontoContainer(titel);
		for (Buchungssatz bsatz : sollSeite.getArrayOfBuchungen()) {
			addBuchungssatzToContainer(bsatz, true);
		}
		for (Buchungssatz bsatz : habenSeite.getArrayOfBuchungen()) {
			addBuchungssatzToContainer(bsatz, false);
		}
	}

	/**
	 * <i><b>Buchungssatz zur GUI hinzufügen</b></i><br>
	 * <br>
	 * Fügt den Buchungssatz der GUI hinzu <br>
	 * 
	 * @param bsatz
	 *            - der Buchungssatz, der gebucht werden soll
	 * @param sollseite
	 *            - die Seite auf der der Buchungssatz gebucht wird
	 */
	protected void addBuchungssatzToContainer(Buchungssatz bsatz, boolean sollseite) {
		if (sollseite) {
			guiContainer.getRefNameS().getChildren()
					.add(new Label(bsatz.getID() + " " + bsatz.getHabenKonto()));
			guiContainer.getRefBetragS().getChildren().add(new Label(Double.toString(bsatz.getBetrag()) + "€"));
		} else {
			guiContainer.getRefNameH().getChildren()
					.add(new Label(bsatz.getID() + " " + bsatz.getSollKonto()));
			guiContainer.getRefBetragH().getChildren().add(new Label(Double.toString(bsatz.getBetrag()) + "€"));
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

	public double getBilanzwert() {
		return bilanzwert;
	}

	public void setBilanzwert(double bilanzwert) {
		this.bilanzwert = bilanzwert;
	}

}
