package application;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utility.alertDialog.AlertDialogFrame;

/**
 * In der Klasse GUI wird das Programm gestartet.
 */
public class GUI extends Application {

	public static HostServices services;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		services = this.getHostServices();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("BuFue.fxml"));
		Scene scene = new Scene(loader.load());
		primaryStage.setScene(scene);
		primaryStage.setOnCloseRequest(e -> {
			try {
				boolean close = true;
						close = new AlertDialogFrame().showChoiseDialog("Programm beenden",
						"Beim Schließen des Programms kann ungespeicherter Fortschritt verloren gehen.\nSind Sie sicher, dass Sie das Programm beenden möchten?",
						"Ok", "Cancel", AlertDialogFrame.QUESTION_TYPE);
				if (close) {
					System.exit(0);
				} else {
					e.consume();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				System.exit(-1);
			}
		});
		primaryStage.setTitle("BuFü HWR Version");
		primaryStage.setResizable(false);
		primaryStage.show();
	}

}
