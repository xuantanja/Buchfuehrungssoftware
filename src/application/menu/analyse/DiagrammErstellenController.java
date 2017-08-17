package application.menu.analyse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;

import geschaeftsfall.Buchungssatz;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.stage.Stage;
import konten.Bestandskonto;
import konten.Erfolgskonto;
import konten.Konto;

/**
 * FXML Controller class
 *
 * @author tmanlik
 */
public class DiagrammErstellenController {

	/**
	 * Initializes the controller class.
	 */
	private PieChart pieChart_Bestand_alt, pieChart_Bestand_neu, pieChart_Ertrag, pieChart_Aufwand;
	private ArrayList<Konto> konten;

	public DiagrammErstellenController(ArrayList<Konto> konten) {
		this.konten = konten;
		// für Bestandskonten
		pieChart_Bestand_alt = new PieChart(getChartDataA());
		pieChart_Bestand_alt.setTitle("Anfangsbestände der Bestandskonten");
		// für Aufwandskonten
		pieChart_Ertrag = new PieChart(getChartDataB());
		pieChart_Ertrag.setTitle("Erträge der Erfolgskonten");
		// für Bestandskonten
		pieChart_Bestand_neu = new PieChart(getChartDataC());
		pieChart_Bestand_neu.setTitle("Schlussbestände der Bestandskonten");
		
		pieChart_Aufwand = new PieChart(getChartDataD());
		pieChart_Aufwand.setTitle("Aufwände der Erfolgskonten");
		
	}

	private HashMap berechneProzenteB(HashMap konten) {
		HashMap newKonten = konten;
		// TODO ins SBK berechnete Konten; deren in SBK dargestellte Summe
		// (Verrechungsbetrag) wird hier genommen
		// und zu anteiligen Prozenten berechnet
		return newKonten;

	}

	private HashMap berechneProzenteA(HashMap konten) {
		HashMap newKonten = konten;
		// TODO ins SBK berechnete Konten; deren in SBK dargestellte Summe
		// (Verrechungsbetrag) wird hier genommen
		// und zu anteiligen Prozenten berechnet
		return newKonten;

	}

	private ObservableList<Data> getChartDataA() {
		ObservableList<PieChart.Data> kontenData = FXCollections.observableArrayList();
		for (Konto konto : konten) {
			// "1" entspricht der Kontoart Bestandskonto
			if (konto.getKontoart() == 1) {
				kontenData.add(new PieChart.Data(konto.getTitel(), ((Bestandskonto) konto).getAnfangsbestand()));
			}
		}
		return kontenData;
	}

	private ObservableList<Data> getChartDataB() {
		ObservableList<PieChart.Data> kontenData = FXCollections.observableArrayList();
		for (Konto konto : konten) {
			// "1" entspricht der Kontoart Bestandskonto
			if (konto.getKontoart() == 2) {
				if(((Erfolgskonto) konto).isErtragskonto()){
					kontenData.add(new PieChart.Data(konto.getTitel(), konto.getBilanzwert()));
				}
			}
		}
		return kontenData;

	}
	private ObservableList<Data> getChartDataC() {
		ObservableList<PieChart.Data> kontenData = FXCollections.observableArrayList();
		for (Konto konto : konten) {
			// "1" entspricht der Kontoart Bestandskonto
			if (konto.getKontoart() == 1) {
				kontenData.add(new PieChart.Data(konto.getTitel(), konto.getBilanzwert()));
			}
		}
		return kontenData;
	}
	
	private ObservableList<Data> getChartDataD() {
		ObservableList<PieChart.Data> kontenData = FXCollections.observableArrayList();
		for (Konto konto : konten) {
			// "1" entspricht der Kontoart Bestandskonto
			if (konto.getKontoart() == 2) {
				if(!((Erfolgskonto) konto).isErtragskonto()){
					kontenData.add(new PieChart.Data(konto.getTitel(), konto.getBilanzwert()));
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
}
