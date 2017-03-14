package Test;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainTest extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		SelectFileChooserView s = new SelectFileChooserView();
		//System.out.println(s.chemin);
	}
	
	public static void main(String [] args) {
		Application.launch(args);
	}

}
