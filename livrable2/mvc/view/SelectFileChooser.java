package mvc.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class SelectFileChooser {

	public static String showSingleFileChooser() {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Charger un fichier CSV");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.csv"));
		File selectedFile = fileChooser.showOpenDialog(null);

		return selectedFile.getAbsolutePath();
	}

	public static String showDirChooser() {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setInitialDirectory(new File("data/"));
        File selectedDirectory = directoryChooser.showDialog(null);

		return selectedDirectory.getAbsolutePath();
	}

	public static void csvDownload(String urlStr, String file) throws IOException {
		//System.setProperty("http.proxyPort", "3128");
		//System.setProperty("http.proxyHost", "proxy.univ-lille1.fr");
		URL url = new URL(urlStr);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
        System.out.println("Success !");
	}


}