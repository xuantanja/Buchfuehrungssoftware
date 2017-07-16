package utility.alertDialog;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AlertDialogFrame {

	public void showConfirmDialog(String message, String details, String rightButtonText) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/utility/alertDialog/alertDialog.fxml"));
			Scene scene = new Scene(loader.load());
			AlertDialogController adc = loader.getController();
			Stage dialogStage = new Stage();
			dialogStage.setScene(scene);
			dialogStage.setTitle(message);
			adc.setText(message, details, rightButtonText, "");
			adc.getLeftButton().setVisible(false);
			dialogStage.showAndWait();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public boolean showAndWaitMessageDialog(String message, String details, String rightButtonText, String leftButtonText) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/utility/alertDialog/alertDialog.fxml"));
			AlertDialogController adc = loader.getController();
			Scene scene = new Scene(loader.load());
			Stage dialogStage = new Stage();
			dialogStage.setScene(scene);
			dialogStage.setTitle(message);
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
