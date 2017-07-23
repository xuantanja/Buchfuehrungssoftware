package konten;

import java.io.Serializable;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import utility.serialisierung.sHBox;
import utility.serialisierung.sLabel;
import utility.serialisierung.sLine;
import utility.serialisierung.sVBox;

public class KontoContainer implements Serializable{

	private sVBox layout;
	// Layouts für die Sollseite
	private sHBox container;
	private sVBox refNameS;
	private sVBox refBetragS;
	// Layouts für die Habenseite
	private sVBox refNameH;
	private sVBox refBetragH;
	private sLine hLine;
	private sLabel name;

	public KontoContainer(String kontoname) {
		name = new sLabel(kontoname);
		name.setFont(Font.font("System", FontWeight.BOLD, 14));
		hLine = new sLine(0, 0, 0, 100);
		Line vLine = new sLine(0, 0, 200, 0);

		refBetragS = new sVBox();
		refNameS = new sVBox();
		refBetragH = new sVBox();
		refNameH = new sVBox();
		container = new sHBox(refNameS, refBetragS, hLine, refNameH, refBetragH);
		container.setAlignment(Pos.CENTER);
		container.setSpacing(2);
		layout = new sVBox(name, vLine, container);
		layout.setPrefSize(200, 200);
		layout.setAlignment(Pos.CENTER);
		initialKontoLayout();
		
	}

	private void initialKontoLayout() {
		Label l1 = new Label("-------------------");
		Label l2 = new Label("-------------------");
		Label l3 = new Label("-----------------------------------------");
		Label l4 = new Label("-----------------------------------------");
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
		
		refNameS.heightProperty().addListener(e -> {
			if(refNameS.getHeight() > hLine.getEndY() - hLine.getStartY()){
				hLine.setEndY(refNameS.getHeight() + hLine.getStartY());
			}
		});
		
		refNameH.heightProperty().addListener(e -> {
			if(refNameH.getHeight() > hLine.getEndY() - hLine.getStartY()){
				hLine.setEndY(refNameH.getHeight() + hLine.getStartY());
			}
		});

	}

	public void refresh() {
		if (refNameS.getWidth() < refNameH.getWidth()) {
			refNameH.setPrefWidth(refNameS.getWidth());
		} else {
			refNameS.setPrefWidth(refNameH.getWidth());
		}
		if (refBetragS.getWidth() < refBetragH.getWidth()) {
			refBetragS.setPrefWidth(refBetragH.getWidth());
		} else {
			refBetragH.setPrefWidth(refBetragS.getWidth());
		}

	}

	public VBox getLayout() {
		return layout;
	}

	public void setLayout(sVBox layout) {
		this.layout = layout;
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
