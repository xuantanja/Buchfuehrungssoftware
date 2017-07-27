package utility.alertDialog;

import java.io.IOException;
import java.io.InputStream;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AlertDialogFrame {

	public static final int INFORMATION_TYPE = 0;
	public static final int WARNING_TYPE = 1;
	public static final int QUESTION_TYPE = 2;
	public static final int ERROR_TYPE = 3;
	private Image[] types;

	public AlertDialogFrame() {
		types = new Image[4];
		try{
		types[0] = new Image(getClass().getResourceAsStream("/utility/alertDialog/images/information.png"));
		types[1] = new Image(getClass().getResourceAsStream("/utility/alertDialog/images/warning.png"));
		types[2] = new Image(getClass().getResourceAsStream("/utility/alertDialog/images/question.png"));
		types[3] = new Image(getClass().getResourceAsStream("/utility/alertDialog/images/error.png"));
		} catch(NullPointerException ex){
			ex.printStackTrace();
		}
	}

	public void showConfirmDialog(String message, String details, String rightButtonText, int type) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/utility/alertDialog/alertDialog.fxml"));
			Scene scene = new Scene(loader.load());
			AlertDialogController adc = loader.getController();
			Stage dialogStage = new Stage();
			dialogStage.setScene(scene);
			adc.setText(message, details, rightButtonText, "");
			adc.getLeftButton().setVisible(false);
			adc.setImage(types[type]);
			dialogStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public boolean showAndWaitMessageDialog(String message, String details, String rightButtonText,
			String leftButtonText) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/utility/alertDialog/alertDialog.fxml"));
			AlertDialogController adc = loader.getController();
			Scene scene = new Scene(loader.load());
			Stage dialogStage = new Stage();
			dialogStage.setScene(scene);
			adc.getMessageLabel().setText(message);
			adc.getDetailsLabel().setText(details);
			adc.getRightButton().setText(rightButtonText);
			adc.getLeftButton().setText(leftButtonText);
			dialogStage.showAndWait();
			return adc.isRightButtonClicked();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}
