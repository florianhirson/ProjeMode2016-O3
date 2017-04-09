package mvc.view;

import java.io.File;

import javafx.stage.FileChooser;

public class SelectFileChooser {

	public static File showSingleFileChooser() {

		FileChooser fileChooser = new FileChooser();
		File selectedFile = fileChooser.showOpenDialog(null);

		return selectedFile;
	}


}