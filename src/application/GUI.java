package application;

import geschaeftsfall.Buchungssatz;
import geschaeftsfall.Geschaeftsfall;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import konten.Bestandskonto;
import konten.Konto;

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
		primaryStage.setOnCloseRequest(e -> System.exit(0));
		primaryStage.setTitle("BuFü HWR Version");
		primaryStage.setResizable(false);
		primaryStage.show();
	}

}
