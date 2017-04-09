package mvc.view;

import java.io.File;

import javafx.stage.FileChooser;

public class SelectFileChooser {

	public static File showSingleFileChooser() {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Charger un fichier CSV");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.csv"));
		File selectedFile = fileChooser.showOpenDialog(null);

		return selectedFile;
	}


}