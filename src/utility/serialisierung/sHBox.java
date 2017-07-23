package utility.serialisierung;

import java.io.Serializable;

import javafx.scene.Node;

public class sHBox extends javafx.scene.layout.HBox implements Serializable{

	public sHBox() {
		super();
		// TODO Auto-generated constructor stub
	}

	public sHBox(double spacing, Node... children) {
		super(spacing, children);
		// TODO Auto-generated constructor stub
	}

	public sHBox(double spacing) {
		super(spacing);
		// TODO Auto-generated constructor stub
	}

	public sHBox(Node... children) {
		super(children);
		// TODO Auto-generated constructor stub
	}

}
