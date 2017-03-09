package Test;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.io.File;

public class SelectFileChooserExample extends Application {

	private Text actionStatus;
	private static final String titleTxt = "JavaFX File Chooser Example 1";

	public static void main(String [] args) {

		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
	
		primaryStage.setTitle(titleTxt);	

		// Window label
		Label label = new Label("Select File Choosers");
		label.setTextFill(Color.DARKBLUE);
		label.setFont(Font.font("Calibri", FontWeight.BOLD, 36));
		HBox labelHb = new HBox();
		labelHb.setAlignment(Pos.CENTER);
		labelHb.getChildren().add(label);

		// Buttons
		Button btn1 = new Button("Choisissez un fichier...");
		btn1.setOnAction(new SingleFcButtonListener());
		HBox buttonHb1 = new HBox(10);
		buttonHb1.setAlignment(Pos.CENTER);
		buttonHb1.getChildren().addAll(btn1);


		// Status message text
		actionStatus = new Text();
		actionStatus.setFont(Font.font("Calibri", FontWeight.NORMAL, 20));
		actionStatus.setFill(Color.FIREBRICK);

		// Vbox
		VBox vbox = new VBox(30);
		vbox.setPadding(new Insets(25, 25, 25, 25));;
		vbox.getChildren().addAll(labelHb, buttonHb1, actionStatus);

		// Scene
		Scene scene = new Scene(vbox, 500, 300); // w x h
		primaryStage.setScene(scene);
		primaryStage.show();


	}

	private class SingleFcButtonListener implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent e) {

			showSingleFileChooser();
		}
	}

	private void showSingleFileChooser() {
	
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Fichiers CSV", "*.csv"));
		File selectedFile = fileChooser.showOpenDialog(null);
		

		if (selectedFile != null) {

			actionStatus.setText("Fichier choisis: " + selectedFile.getName());
			String chemin = selectedFile.getAbsolutePath();
			System.out.println(chemin);
		}
		else {
			actionStatus.setText("Selection de fichier annulee.");
		}
	}
}