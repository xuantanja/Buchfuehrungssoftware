package utility.serialisierung;

import java.io.Serializable;

import javafx.scene.Node;

public class sLabel extends javafx.scene.control.Label implements Serializable{

	public sLabel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public sLabel(String text, Node graphic) {
		super(text, graphic);
		// TODO Auto-generated constructor stub
	}

	public sLabel(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}
}
