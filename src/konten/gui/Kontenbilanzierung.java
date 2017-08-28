package konten.gui;

import java.io.Serializable;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

public class Kontenbilanzierung implements Serializable {

	private double refNameSwidth, refNameHwidth, refBetragSwidth, refBetragHwidth;

	public Kontenbilanzierung(double refNameSwidth, double refNameHwidth, double refBetragSwidth,
			double refBetragHwidth) {
		this.refNameSwidth = refNameSwidth;
		this.refNameHwidth = refNameHwidth;
		this.refBetragSwidth = refBetragSwidth;
		this.refBetragHwidth = refBetragHwidth;
	}

	public VBox getLayout(double bilanzwert) {
		Line hLineSoll = new Line(0, 0, refBetragSwidth, 0);
		Line hLineHaben = new Line(0, 0, refBetragHwidth, 0);
		Line gapSoll = new Line(0,0,refNameSwidth,0);
		Line gapHaben = new Line(0,0,refNameHwidth,0);
		gapSoll.setVisible(false);
		gapHaben.setVisible(false);
		HBox container1 = new HBox(gapSoll, hLineSoll, gapHaben, hLineHaben);
		container1.setPrefWidth(240);

		Label sollbetrag = new Label(bilanzwert + "");
		Label habenbetrag = new Label(bilanzwert + "");
		sollbetrag.setAlignment(Pos.TOP_RIGHT);
		habenbetrag.setAlignment(Pos.TOP_RIGHT);
		sollbetrag.setPrefWidth(refNameSwidth + refBetragSwidth);
		habenbetrag.setPrefWidth(refNameHwidth + refBetragHwidth);
		Line vLine = new Line(0, 0, 0, 20);
		HBox container2 = new HBox(sollbetrag, vLine, habenbetrag);
		container2.setPrefWidth(240);
		container2.setAlignment(Pos.CENTER);

		VBox layout = new VBox(container1, container2);
		layout.setPrefWidth(240);
		return layout;
	}
}
