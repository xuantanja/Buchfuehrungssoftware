package konten.gui;

import java.io.Serializable;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import utility.serialisierung.sLabel;
import utility.serialisierung.sLine;
import utility.serialisierung.sVBox;

public class KontoContainer implements Serializable {

	private sLine hLine;
	private sLabel name;
	// Layouts für die Sollseite
	private sVBox refNameS;
	private sVBox refBetragS;
	// Layouts für die Habenseite
	private sVBox refNameH;
	private sVBox refBetragH;

	public KontoContainer(String kontoname) {
		name = new sLabel(kontoname);
		name.setFont(Font.font("System", FontWeight.BOLD, 14));
		hLine = new sLine(0, 0, 0, 120);

		refBetragS = new sVBox();
		refNameS = new sVBox();
		refBetragH = new sVBox();
		refNameH = new sVBox();

		initialKontoLayout();
	}

	private void initialKontoLayout() {
		Label l1 = new Label("-----------------------------------------");
		Label l2 = new Label("-----------------------------------------");
		Label l3 = new Label("--------------------------------------");
		Label l4 = new Label("--------------------------------------");
		l1.setVisible(false);
		l2.setVisible(false);
		l3.setVisible(false);
		l4.setVisible(false);
		l1.setFont(new Font(5));
		l2.setFont(new Font(5));
		l3.setFont(new Font(5));
		l4.setFont(new Font(5));
		refNameS.getChildren().add(l1);
		refNameH.getChildren().add(l2);
		refBetragS.getChildren().add(l3);
		refBetragH.getChildren().add(l4);
		refBetragS.setAlignment(Pos.TOP_RIGHT);
		refBetragH.setAlignment(Pos.TOP_RIGHT);

		refNameS.heightProperty().addListener(e -> {
			if (refNameS.getHeight() > hLine.getEndY() - hLine.getStartY()) {
				hLine.setEndY(refNameS.getHeight() + hLine.getStartY());
			}
		});

		refNameH.heightProperty().addListener(e -> {
			if (refNameH.getHeight() > hLine.getEndY() - hLine.getStartY()) {
				hLine.setEndY(refNameH.getHeight() + hLine.getStartY());
			}
		});

	}

	public VBox getLayout(double bilanzwert) {
		Line vLine = new sLine(0, 0, 240, 0);
		HBox container = new HBox(refNameS, refBetragS, hLine, refNameH, refBetragH);
		container.setAlignment(Pos.CENTER);
		container.setSpacing(2);
		VBox layout = new sVBox(name, vLine, container);
		layout.setPrefSize(220, 200);
		layout.setAlignment(Pos.CENTER);

		if (bilanzwert != -1) {
			System.out.println("[KontoContainer] "+ bilanzwert);
			Kontenbilanzierung kb = new Kontenbilanzierung(60,60,60,60);
			layout.getChildren().add(kb.getLayout(bilanzwert));
		}
		return layout;
	}

	public VBox getRefNameS() {
		return refNameS;
	}

	public void setRefNameS(sVBox refNameS) {
		this.refNameS = refNameS;
	}

	public VBox getRefBetragS() {
		return refBetragS;
	}

	public void setRefBetragS(sVBox refBetragS) {
		this.refBetragS = refBetragS;
	}

	public VBox getRefNameH() {
		return refNameH;
	}

	public void setRefNameH(sVBox refNameH) {
		this.refNameH = refNameH;
	}

	public VBox getRefBetragH() {
		return refBetragH;
	}

	public void setRefBetragH(sVBox refBetragH) {
		this.refBetragH = refBetragH;
	}

	public void setName(String name) {
		this.name.setText(name);
	}

}
