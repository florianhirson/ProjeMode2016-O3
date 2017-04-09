package mvc.view;

import java.io.File;

import com.sun.webkit.ContextMenu.ShowContext;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuProjet extends Application{
	static String choixT = "";
	static String choixA = "";
	static String choixP = "";
	static String chemin = "";

	public static void main(String[] args)
	{
		System.setProperty("http.proxyPort", "3128");
		System.setProperty("http.proxyHost", "proxy.univ-lille1.fr");
		Application.launch(args);
	}

	@Override

	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		MenuBar menuBar = new MenuBar();

		VBox ajout = new VBox();
		ajout.setSpacing(10);
		ajout.setPadding(new Insets(10, 10, 10, 10));

		TabPane tabPane = new TabPane();

		root.setTop(menuBar);
		root.setLeft(ajout);
		root.setCenter(tabPane);

		Label lAjouT = new Label("Ajouter une transformation : ");
		Label lAjouA = new Label("Ajouter une analyse : ");
		Label lAjouP = new Label("Ajouter une prévision : ");

		HBox ajoutT = new HBox();
		ajoutT.setSpacing(10);

		HBox ajoutA = new HBox();
		ajoutA.setSpacing(10);

		HBox ajoutP = new HBox();
		ajoutP.setSpacing(10);

		ChoiceBox<String> cAjoutT = new ChoiceBox<String>(FXCollections.observableArrayList("Logarithme Yt1", "BoxCox BC", "Logistique Yt2", "Moyenne Mobile (Mt)", "Xt-Mt", "St : saison", "Xt-St desaisonnalisation"));
		cAjoutT.setPrefWidth(150);

		ChoiceBox<String> cAjoutA = new ChoiceBox<String>(FXCollections.observableArrayList("First", "Second", "Third"));
		cAjoutA.setPrefWidth(150);

		ChoiceBox<String> cAjoutP = new ChoiceBox<String>(FXCollections.observableArrayList("First", "Second", "Third"));
		cAjoutP.setPrefWidth(150);

		Button bAjoutT = new Button("Ajouter");
		Button bAjoutA = new Button("Ajouter");
		Button bAjoutP = new Button("Ajouter");



		ajoutT.getChildren().addAll(cAjoutT,bAjoutT);
		ajoutA.getChildren().addAll(cAjoutA,bAjoutA);
		ajoutP.getChildren().addAll(cAjoutP,bAjoutP);

		ajout.getChildren().addAll(lAjouT,ajoutT,lAjouA,ajoutA,lAjouP,ajoutP);

		Menu menuF = new Menu("File");
		Menu menuH = new Menu("Aide");

		MenuItem chargerCSV = new MenuItem("Charger un fichier CSV");
		MenuItem chargerCSVInternet = new MenuItem("Charger un fichier CSV par internet");
		MenuItem saveCourbes = new MenuItem("Sauvegarder les courbes");
		MenuItem exit = new MenuItem("Exit");
		MenuItem aide = new MenuItem("Aide en ligne");
		MenuItem apropos = new MenuItem("A propos");

		menuBar.getMenus().addAll(menuF,menuH);
	    menuBar.prefWidthProperty().bind(primaryStage.widthProperty());

		menuF.getItems().addAll(chargerCSV,chargerCSVInternet,saveCourbes);
		menuF.getItems().add(new SeparatorMenuItem());
		menuF.getItems().add(exit);
		menuH.getItems().addAll(aide,apropos);

		cAjoutT.getSelectionModel()
	    .selectedItemProperty()
	    .addListener( (ObservableValue<? extends String> observable, String oldValue, String newValue) -> choixT = newValue );

		chargerCSV.setOnAction(e -> {
			File courbe = SelectFileChooser.showSingleFileChooser();
			System.out.println(courbe.getAbsolutePath());
		});

		exit.setOnAction(e ->{
			primaryStage.close();
		});

		apropos.setOnAction(e ->{
			BorderPane bp = new BorderPane();

			Label lInfo = new Label("Projet du DUT Informatique\n	Membre du groupe :\n        - Barbet Florian\n        - Mastalerz Thomas\n        - Haddad Rayan\n        - Hirson Florian");
			bp.setCenter(lInfo);

			Scene sInfo = new Scene(bp);
			Stage info = new Stage();
			info.setScene(sInfo);
			info.setHeight(500.0);
			info.setWidth(500.0);
			info.show();
		});



		for (int i = 0; i < 5; i++) {
            Tab tab = new Tab();
            tab.setText("Tab" + i);
            final CategoryAxis xAxis = new CategoryAxis();
            final NumberAxis yAxis = new NumberAxis();
             xAxis.setLabel("Month");
            final LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);

            tab.setContent(lineChart);
            tabPane.getTabs().add(tab);

        }

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setHeight(700);
		primaryStage.setWidth(1000);
		primaryStage.show();


	}

}
