package application.menu.analyse;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
import konten.Bestandskonto;
import konten.Erfolgskonto;
import konten.Konto;

/**
 * DiagrammErstellen dient beim Jahresabschluss zum Erstellen der Übersicht über die Zustände der Konten zu Beginn und zum Ende des Geschäftsjahres.
 */
public class DiagrammErstellen {

	private PieChart pieChart_Bestand_alt, pieChart_Bestand_neu, pieChart_Ertrag, pieChart_Aufwand;
	private BarChart<String, Number> barChartGuV;
	private ArrayList<Konto> konten;

	public DiagrammErstellen(ArrayList<Konto> konten) {
		this.konten = konten;
		// für Bestandskonten
		pieChart_Bestand_alt = new PieChart(getBestandDiaAlt());
		pieChart_Bestand_alt.setTitle("Anfangsbestände der Bestandskonten");
		// für Aufwandskonten
		pieChart_Ertrag = new PieChart(getErtragDia());
		pieChart_Ertrag.setTitle("Erträge der Erfolgskonten");
		// für Bestandskonten
		pieChart_Bestand_neu = new PieChart(getBestandDiaNeu());
		pieChart_Bestand_neu.setTitle("Schlussbestände der Bestandskonten");

		pieChart_Aufwand = new PieChart(getAufwandDia());
		pieChart_Aufwand.setTitle("Aufwände der Erfolgskonten");

		final NumberAxis xAxis = new NumberAxis();
		final CategoryAxis yAxis = new CategoryAxis();

		xAxis.setLabel("Betraggröße");

		barChartGuV = new BarChart<String, Number>(yAxis, xAxis);
		barChartGuV.setTitle("Verteilung von Gewinn-und Verluste");

		XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
		series1.setName("Gewinn");
		series1.getData().add(new XYChart.Data<String, Number>("", getGewinnWert()));

		XYChart.Series<String, Number> series2 = new XYChart.Series<String, Number>();
		series2.setName("Verlust");
		series2.getData().add(new XYChart.Data<String, Number>("", getVerlustWert()));
		barChartGuV.getData().add(series1);
		barChartGuV.getData().add(series2);
	}

	/**
	 * <i><b>Berechnen des alten Bestandswertes (in der
	 * Eröffnungsbilanz)</b></i><br>
	 * <br>
	 * Liefert den aufsummierten Betrag aus der Eröffnungsbilanz aller
	 * Bestandskonten.<br>
	 * 
	 * @return Aufsummierter Betrag der Anfangsbestäne aller Bestandskonten
	 */
	private ObservableList<Data> getBestandDiaAlt() {
		ObservableList<PieChart.Data> kontenData = FXCollections.observableArrayList();
		for (Konto konto : konten) {
			// "1" entspricht der Kontoart Bestandskonto
			if (konto.getKontoart() == 1 && konto.getBilanzwert() != 0) {
				kontenData.add(new PieChart.Data(konto.getKuerzel(), ((Bestandskonto) konto).getAnfangsbestand()));
			}
		}
		return kontenData;
	}

	/**
	 * <i><b>Berechnen des Gewinnwertes</b></i><br>
	 * <br>
	 * Liefert den aufsummierten Betrag aller Ertragskonten, der Gewinnwert
	 * genannt wird. <br>
	 * 
	 * @return Aufsummierter Betrag aller Ertragskonten
	 */
	private ObservableList<Data> getErtragDia() {
		ObservableList<PieChart.Data> kontenData = FXCollections.observableArrayList();
		for (Konto konto : konten) {
			if (konto.getKontoart() == 2) {
				if (((Erfolgskonto) konto).isErtragskonto()) {
					kontenData.add(new PieChart.Data(konto.getKuerzel(), konto.getBilanzwert()));
				}
			}
		}
		return kontenData;

	}

	/**
	 * <i><b>Berechnen des neuen Bestandswertes</b></i><br>
	 * <br>
	 * Liefert den aufsummierten Betrag aller Bestandskonten.<br>
	 * 
	 * @return Aufsummierter Betrag aller Bestandskonten
	 */
	private ObservableList<Data> getBestandDiaNeu() {
		ObservableList<PieChart.Data> kontenData = FXCollections.observableArrayList();
		for (Konto konto : konten) {
			// "1" entspricht der Kontoart Bestandskonto
			if (konto.getKontoart() == 1 && konto.getBilanzwert() != 0) {
				kontenData.add(new PieChart.Data(konto.getKuerzel(), konto.getBilanzwert()));
			}
		}
		return kontenData;
	}

	/**
	 * <i><b>Berechnen des Verlustwertes</b></i><br>
	 * <br>
	 * Liefert den aufsummierten Betrag aller Verlustkonten, der Verlustwert
	 * genannt wird. <br>
	 * 
	 * @return Aufsummierter Betrag aller Verlustkonten
	 */
	private ObservableList<Data> getAufwandDia() {
		ObservableList<PieChart.Data> kontenData = FXCollections.observableArrayList();
		for (Konto konto : konten) {
			// "2" entspricht der Kontoart Erfolgskonto
			if (konto.getKontoart() == 2) {
				if (!((Erfolgskonto) konto).isErtragskonto()) {
					kontenData.add(new PieChart.Data(konto.getKuerzel(), konto.getBilanzwert()));
				}

			}
		}
		return kontenData;
	}

	/**
	 * <i><b>Berechnen des Gewinnwertes</b></i><br>
	 * <br>
	 * Liefert den aufsummierten Betrag aller Ertragskonten, der Gewinnwert
	 * genannt wird. <br>
	 * 
	 * @return Aufsummierter Betrag aller Ertragskonten
	 */
	private double getGewinnWert() {
		double kontenData = 0.0;
		for (Konto konto : konten) {
			if (konto.getKontoart() == 2) {
				if (((Erfolgskonto) konto).isErtragskonto()) {
					kontenData = +konto.getBilanzwert();
				}
			}
		}
		return kontenData;

	}

	/**
	 * <i><b>Berechnen des Verlustwertes</b></i><br>
	 * <br>
	 * Liefert den aufsummierten Betrag aller Verlustkonten, der Verlustwert
	 * genannt wird. <br>
	 * 
	 * @return Aufsummierter Betrag aller Verlustkonten
	 */
	private double getVerlustWert() {
		double kontenData = 0.0;
		for (Konto konto : konten) {
			if (konto.getKontoart() == 2) {
				if (!((Erfolgskonto) konto).isErtragskonto()) {
					kontenData = +konto.getBilanzwert();
				}
			}
		}
		return kontenData;

	}

	public PieChart getPieChart_BestandAlt() {
		return pieChart_Bestand_alt;
	}

	public PieChart getPieChart_Ertrag() {
		return pieChart_Ertrag;
	}

	public PieChart getPieChart_BestandNeu() {
		return pieChart_Bestand_neu;
	}

	public PieChart getPieChart_Aufwand() {
		return pieChart_Aufwand;
	}

	public BarChart<String, Number> getBarChart_GuV() {
		return barChartGuV;
	}
}
