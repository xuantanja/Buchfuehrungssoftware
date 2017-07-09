package konten;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class KontoContainer {

	private VBox layout;
	// Layouts für die Sollseite
	private HBox container;
	private VBox refNameS;
	private VBox refBetragS;
	// Layouts für die Habenseite
	private VBox refNameH;
	private VBox refBetragH;
	private Line hLine;
	private Label name;

	public KontoContainer(String kontoname) {
		name = new Label(kontoname);
		name.setFont(Font.font("System", FontWeight.BOLD, 14));
		hLine = new Line(0, 0, 0, 100);
		Line vLine = new Line(0, 0, 200, 0);

		refBetragS = new VBox();
		refNameS = new VBox();
		refBetragH = new VBox();
		refNameH = new VBox();
		container = new HBox(refNameS, refBetragS, hLine, refNameH, refBetragH);
		container.setAlignment(Pos.CENTER);
		container.setSpacing(2);
		layout = new VBox(name, vLine, container);
		layout.setPrefSize(200, 200);
		layout.setAlignment(Pos.CENTER);
		initialKontoLayout();
	}

	private void initialKontoLayout() {
		Label l1 = new Label("\t\t\t\t");
		Label l2 = new Label("\t\t\t\t");
		Label l3 = new Label("\t\t\t\t");
		Label l4 = new Label("\t\t\t\t");
		l1.setFont(new Font(5));
		l2.setFont(new Font(5));
		l3.setFont(new Font(5));
		l4.setFont(new Font(5));
		refNameS.getChildren().add(l1);
		refNameH.getChildren().add(l2);
		refBetragS.getChildren().add(l3);
		refBetragH.getChildren().add(l4);
		
	}
	
	public void refresh(){
		if(refNameS.getWidth() < refNameH.getWidth()){
			refNameH.setPrefWidth(refNameS.getWidth());
		} else {
			refNameS.setPrefWidth(refNameH.getWidth());
		}
		if(refBetragS.getWidth() < refBetragH.getWidth()){
			refBetragS.setPrefWidth(refBetragH.getWidth());
		} else {
			refBetragH.setPrefWidth(refBetragS.getWidth());
		}

	}

	public VBox getLayout() {
		return layout;
	}

	public void setLayout(VBox layout) {
		this.layout = layout;
	}

	public VBox getRefNameS() {
		return refNameS;
	}

	public void setRefNameS(VBox refNameS) {
		this.refNameS = refNameS;
	}

	public VBox getRefBetragS() {
		return refBetragS;
	}

	public void setRefBetragS(VBox refBetragS) {
		this.refBetragS = refBetragS;
	}

	public VBox getRefNameH() {
		return refNameH;
	}

	public void setRefNameH(VBox refNameH) {
		this.refNameH = refNameH;
	}

	public VBox getRefBetragH() {
		return refBetragH;
	}

	public void setRefBetragH(VBox refBetragH) {
		this.refBetragH = refBetragH;
	}
	
}
