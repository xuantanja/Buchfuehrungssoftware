package application.menu.analyse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author tmanlik
 */
public class DiagrammErstellenController implements Initializable {

    /**
     * Initializes the controller class.
     */
	@FXML	
	private PieChart pieChart_Bestand;
	@FXML
	private PieChart pieChart_Ertrags;
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // für Bestandskonten
    	ObservableList<PieChart.Data> pieChartDataB =
                FXCollections.observableArrayList(
                new PieChart.Data("x", 60),
                new PieChart.Data("y", 10),
                new PieChart.Data("z", 10),
                new PieChart.Data("q", 10),
                new PieChart.Data("w", 10));;
    	pieChart_Bestand.setData(pieChartDataB);
    	// für Aufwandskonten
    	ObservableList<PieChart.Data> pieChartDataA =
                FXCollections.observableArrayList(
                new PieChart.Data("x", 60),
                new PieChart.Data("y", 10),
                new PieChart.Data("z", 10),
                new PieChart.Data("q", 10),
                new PieChart.Data("w", 10));;
    	pieChart_Bestand.setData(pieChartDataA);
    }    
    private HashMap berechneProzenteB(HashMap konten){
    	HashMap newKonten  = konten;
    	//TODO ins SBK berechnete Konten; deren in SBK dargestellte Summe (Verrechungsbetrag) wird hier genommen 
    	//und zu anteiligen Prozenten berechnet
    	return newKonten;
    	
    }
    private HashMap berechneProzenteA(HashMap konten){
    	HashMap newKonten  = konten;
    	//TODO ins SBK berechnete Konten; deren in SBK dargestellte Summe (Verrechungsbetrag) wird hier genommen 
    	//und zu anteiligen Prozenten berechnet
    	return newKonten;
    	
    }
}
