package Maths;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FenetreCourbe extends Application{
	
	public void start(Stage stage) {
		
		BorderPane bp = new BorderPane();
		
		Scene scenecourbe = new Scene(bp);
		stage.setTitle("projet");
		stage.setWidth(498);
		stage.setHeight(650);
		stage.setScene(scenecourbe);
		stage.show();
	}

}
