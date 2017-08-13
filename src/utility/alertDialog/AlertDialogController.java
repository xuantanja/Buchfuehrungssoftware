/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.alertDialog;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Marc
 */
public class AlertDialogController implements Initializable {

	@FXML
	private Label messageLabel;
	@FXML
	private Label detailsLabel;
	@FXML
	private Button leftButton;
	@FXML
	private HBox okParent;
	@FXML
	private Button rightButton;
	@FXML
	private ImageView image;
	@FXML
	private GridPane container;

	private boolean rightButtonClicked;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		rightButtonClicked = false;
	}

	public void setText(String message, String details, String rightButtonText, String leftButtonText) {
		messageLabel.setText(message);
		detailsLabel.setText(details);
		rightButton.setText(rightButtonText);
		leftButton.setText(leftButtonText);
		
	}

	@FXML
	private void handle_Left(ActionEvent event) {
		rightButtonClicked = false;
		Stage stage = (Stage) leftButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	private void handle_Right(ActionEvent event) {
		rightButtonClicked = true;
		Stage stage = (Stage) rightButton.getScene().getWindow();
		stage.close();
	}

	public Label getMessageLabel() {
		return messageLabel;
	}

	public void setMessageLabel(Label messageLabel) {
		this.messageLabel = messageLabel;
	}

	public Label getDetailsLabel() {
		return detailsLabel;
	}

	public void setDetailsLabel(Label detailsLabel) {
		this.detailsLabel = detailsLabel;
	}

	public Button getLeftButton() {
		return leftButton;
	}

	public void setLeftButton(Button leftButton) {
		this.leftButton = leftButton;
	}

	public Button getRightButton() {
		return rightButton;
	}

	public void setRightButton(Button rightButton) {
		this.rightButton = rightButton;
	}

	public boolean isRightButtonClicked() {
		return rightButtonClicked;
	}

	public void setRightButtonClicked(boolean rightButtonClicked) {
		this.rightButtonClicked = rightButtonClicked;
	}
	
	public GridPane getContainer() {
		return container;
	}

	public void setContainer(GridPane container) {
		this.container = container;
	}

	public void setImage(Image img){
		image.setImage(img);
		image.prefWidth(60);
		image.prefHeight(60);
		
	}
}
