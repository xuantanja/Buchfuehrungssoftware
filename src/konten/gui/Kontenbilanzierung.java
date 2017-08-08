package konten.gui;

import java.io.Serializable;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

public class Kontenbilanzierung implements Serializable{

	private double refNameSwidth, refNameHwidth, refBetragSwidth, refBetragHwidth;

	public Kontenbilanzierung(double refNameSwidth, double refNameHwidth, double refBetragSwidth, double refBetragHwidth) {
		this.refNameSwidth = refNameSwidth;
		this.refNameHwidth = refNameHwidth;
		this.refBetragSwidth = refBetragSwidth;
		this.refBetragHwidth = refBetragHwidth;
	}

	public VBox getLayout(double bilanzwert) {
		Line hLineSoll = new Line(0, 0, refBetragSwidth, 0);
		Line hLineHaben = new Line(0, 0, refBetragHwidth, 0);
		Pane gapSoll = new Pane();
		Pane gapHaben = new Pane();
		gapSoll.resize(0, refNameSwidth);
		gapHaben.resize(0, refNameHwidth);
		HBox container1 = new HBox(gapSoll, hLineSoll, gapHaben, hLineHaben);

		Label sollbetrag = new Label(bilanzwert + "");
		Label habenbetrag = new Label(bilanzwert + "");
		sollbetrag.setPrefWidth(refNameSwidth + refBetragSwidth);
		habenbetrag.setPrefWidth(refNameHwidth + refBetragHwidth);
		Line vLine = new Line(0, 0, 0, 20);
		HBox container2 = new HBox(sollbetrag, vLine, habenbetrag);

		return new VBox(container1, container2);
	}
}
