package CodesMenu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mvc.model.Courbe;
import mvc.view.InputDialogs;
import mvc.view.SelectFileChooser;

public class MenuProjet extends Application{
	Courbe<Number,Number> c = new Courbe<Number,Number>();
	static String choixT = "";
	static String choixA = "";
	static String choixP = "";
	static String chemin = "";
	static String chaine = "";
	static double lambda = 0;
	static int ordre = 0;
	static BufferedReader fichier_source = null;
	ArrayList<Courbe> choix = new ArrayList<Courbe>();
    static private TableView valCsv = new TableView();
	static private TableView valModif = new TableView();

	// load the stylesheets
	String styleMetroD = getClass().getResource("/styles/JMetroDarkTheme.css").toExternalForm();
	String styleMetroL = getClass().getResource("/styles/JMetroLightTheme.css").toExternalForm();
	String styleBrume = getClass().getResource("/styles/brume.css").toExternalForm();

	public static void main(String[] args)
	{
		System.setProperty("http.proxyPort", "3128");
		System.setProperty("http.proxyHost", "proxy.univ-lille1.fr");
		Application.launch(args);
	}

	@Override

	public void start(Stage primaryStage) throws Exception {
		Courbe<Number,Number> donnee = new Courbe<Number,Number>();
		Courbe<Number,Number> cmm = new Courbe<Number,Number>();
		Courbe<Number,Number> csr = new Courbe<Number,Number>();
		Courbe<Number,Number> cs = new Courbe<Number,Number>();
		Courbe<Number,Number> cmd = new Courbe<Number,Number>();
		Courbe<Number,Number> logis = new Courbe<Number,Number>();
		Courbe<Number,Number> log = new Courbe<Number,Number>();
		Courbe<Number,Number> bc = new Courbe<Number,Number>();

		Courbe<Number,Number> res = new Courbe<Number,Number>();

		ArrayList<String> listTitle = new ArrayList<String>();
		ArrayList<Courbe<Number,Number>> listCourbe = new ArrayList<Courbe<Number,Number>>(); // permet d'indexer les courbes et donc de modifier la couleur d'une courbe vis�e
		ArrayList<Integer> choice = new ArrayList<Integer>();
		ArrayList<String[]> tabChaine = new ArrayList<String[]>();
		ArrayList<String[]> tabCh = new ArrayList<String[]>();

		BorderPane root = new BorderPane();
		Scene scene = new Scene(root);
		MenuBar menuBar = new MenuBar();

		Label CsvLab = new Label("Valeur initial");

		Label ModifLab = new Label("Valeur modifier");

		VBox valCsvLabel = new VBox();
		VBox.setMargin(valCsvLabel, new Insets(200,0,0,0));
		valCsvLabel.getChildren().addAll(CsvLab,valCsv);

		VBox valModifLabel = new VBox();
		VBox.setMargin(valModifLabel, new Insets(200,0,0,0));
		valModifLabel.getChildren().addAll(ModifLab,valModif);

		HBox table = new HBox();
		HBox.setMargin(table, new Insets(200,0,0,0));
		table.getChildren().addAll(valCsvLabel,valModifLabel);
		table.setSpacing(200.0);

		TableColumn ColX = new TableColumn("X");
		TableColumn ColY = new TableColumn("Y");
		ColX.prefWidthProperty().bind(table.widthProperty().multiply(0.17));
		ColY.prefWidthProperty().bind(table.widthProperty().multiply(0.17));
		valCsv.getColumns().addAll(ColX, ColY);
		valCsv.setMaxSize(200.0, 200.0);

		TableColumn ColXmodif = new TableColumn("X");
		TableColumn ColYmodif = new TableColumn("Y");
		ColXmodif.prefWidthProperty().bind(table.widthProperty().multiply(0.17));
		ColYmodif.prefWidthProperty().bind(table.widthProperty().multiply(0.17));
		valModif.getColumns().addAll(ColXmodif, ColYmodif);
		valModif.setMaxSize(200.0, 200.0);

		AnchorPane ap = new AnchorPane();
		ap.getChildren().add(table);
		AnchorPane.setBottomAnchor(table, 20.0);
		AnchorPane.setLeftAnchor(table, 150.0);

		VBox ajout = new VBox();
		ajout.setSpacing(10);
		ajout.setPadding(new Insets(10, 10, 10, 10));

		TabPane tabPane = new TabPane();

		root.setTop(menuBar);
		root.setLeft(ajout);
		root.setCenter(tabPane);
		root.setBottom(ap);

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

		ChoiceBox<String> cAjoutA = new ChoiceBox<String>(FXCollections.observableArrayList("Graphe des résidus", "Variance résiduelle", "Autocorrélation des résidus"));
		cAjoutA.setPrefWidth(150);

		ChoiceBox<String> cAjoutP = new ChoiceBox<String>(FXCollections.observableArrayList("Lissage exponentiel simple", "Lissage exponentiel double", "Holt-Winters"));
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
		Menu menuS = new Menu("Styles");

		MenuItem chargerCSV = new MenuItem("Charger un fichier CSV");
		MenuItem chargerCSVInternet = new MenuItem("Charger un fichier CSV par internet");
		MenuItem saveCourbes = new MenuItem("Sauvegarder les courbes");

		MenuItem exit = new MenuItem("Exit");
		MenuItem aide = new MenuItem("Aide en ligne");
		MenuItem apropos = new MenuItem("A propos");

		MenuItem vanilla = new MenuItem("Vanilla");
		MenuItem metroD = new MenuItem("Metro foncé");
		MenuItem metroL = new MenuItem("Metro clair");
		MenuItem brume = new MenuItem("Brume");

		menuBar.getMenus().addAll(menuF,menuS,menuH);
	    menuBar.prefWidthProperty().bind(primaryStage.widthProperty());

		menuF.getItems().addAll(chargerCSV,chargerCSVInternet,saveCourbes);
		menuF.getItems().add(new SeparatorMenuItem());
		menuF.getItems().add(exit);
		menuH.getItems().addAll(aide,apropos);
		menuS.getItems().addAll(vanilla,metroD,metroL,brume);

		cAjoutT.getSelectionModel()
	    .selectedItemProperty()
	    .addListener( (ObservableValue<? extends String> observable, String oldValue, String newValue) -> choixT = newValue );

	    cAjoutA.getSelectionModel()
		.selectedItemProperty()
		.addListener( (ObservableValue<? extends String> observable, String oldValue, String newValue) -> choixA = newValue );

		cAjoutP.getSelectionModel()
		.selectedItemProperty()
		.addListener( (ObservableValue<? extends String> observable, String oldValue, String newValue) -> choixP = newValue );

		bAjoutT.setOnAction(e -> {
			switch(choixT) {
			case "Logarithme Yt1":
				choix = InputDialogs.saisieChoixCourbe(listCourbe);
				if(choix == null)
					break;
				System.out.println(choixT);
				break;
			case "BoxCox BC":
				choix = InputDialogs.saisieChoixCourbe(listCourbe);
				if(choix == null)
					break;
				System.out.println(choixT);
				lambda = InputDialogs.saisieLambda();
				System.out.println("Lambda :"+lambda);
				break;
			case "Logistique Yt2":
				choix = InputDialogs.saisieChoixCourbe(listCourbe);
				if(choix == null)
					break;
				System.out.println(choixT);
				break;
			case "Moyenne Mobile (Mt)":
				choix = InputDialogs.saisieChoixCourbe(listCourbe);
				if(choix == null)
					break;
				System.out.println(choixT);
				ordre = InputDialogs.saisieOrdre();
				System.out.println("Ordre :"+ordre);
				break;
			case "Xt-Mt":
				choix = InputDialogs.saisieChoixCourbe(listCourbe);
				if(choix == null)
					break;
				System.out.println(choixT);
				break;
			case "St : saison":
				choix = InputDialogs.saisieChoixCourbe(listCourbe);
				if(choix == null)
					break;
				System.out.println(choixT);
				break;
			case "Xt-St desaisonnalisation":
				choix = InputDialogs.saisieChoixCourbe(listCourbe);
				if(choix == null)
					break;
				System.out.println(choixT);
				break;
			}
		});

		bAjoutA.setOnAction(e -> {
			switch(choixA) {
			case "Graphe des résidus":
				choix = InputDialogs.saisieChoixCourbe(listCourbe);
				if(choix == null)
					break;
				System.out.println(choixA);
				break;
			case "Variance résiduelle":
				choix = InputDialogs.saisieChoixCourbe(listCourbe);
				if(choix == null)
					break;
				System.out.println(choixA);
				lambda = InputDialogs.saisieLambda();
				System.out.println("Lambda :"+lambda);
				break;
			case "Autocorrélation des résidus":
				choix = InputDialogs.saisieChoixCourbe(listCourbe);
				if(choix == null)
					break;
				System.out.println(choixA);
				break;
			}
		});

		bAjoutP.setOnAction(e -> {
			switch(choixP) {
			case "Lissage exponentiel simple":
				choix = InputDialogs.saisieChoixCourbe(listCourbe);
				if(choix == null)
					break;
				System.out.println(choixP);
				break;
			case "Lissage exponentiel double":
				choix = InputDialogs.saisieChoixCourbe(listCourbe);
				if(choix == null)
					break;
				System.out.println(choixP);
				lambda = InputDialogs.saisieLambda();
				System.out.println("Lambda :"+lambda);
				break;
			case "Holt-Winters":
				choix = InputDialogs.saisieChoixCourbe(listCourbe);
				if(choix == null)
					break;
				System.out.println(choixP);
				break;
			}
		});

		vanilla.setOnAction(e -> {
			scene.getStylesheets().clear();
			System.out.println("vanilla !");
		});

		metroD.setOnAction(e -> {
			// apply stylesheet to the scene graph
			scene.getStylesheets().clear();
			scene.getStylesheets().add(styleMetroD);
			System.out.println("metroDark !");
		});

		metroL.setOnAction(e -> {
			// apply stylesheet to the scene graph
			scene.getStylesheets().clear();
			scene.getStylesheets().add(styleMetroL);
			System.out.println("metrolight !");
		});

		brume.setOnAction(e -> {
			// apply stylesheet to the scene graph
			scene.getStylesheets().clear();
			scene.getStylesheets().add(styleBrume);
			System.out.println("brume !");
		});

		chargerCSV.setOnAction(e -> {
			int indice = 0;
			int i,j = 0;
			Double x,y;

			try {
				chemin = SelectFileChooser.showSingleFileChooser();
			}
			catch(Exception ex) {
				System.out.println(ex);
			}



			try {
				System.out.println(chemin);
				fichier_source = new BufferedReader(new FileReader(chemin));
			} catch (FileNotFoundException e1) {
				SelectFileChooser.error(e1);
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				while((chaine = fichier_source.readLine())!= null)
				{
					tabChaine.add(chaine.split(";"));
					indice++;
				}
			} catch (IOException e1) {
				SelectFileChooser.error(e1);
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			for( i = 0; i < indice ; i++)
				for( j = 0; j < tabChaine.get(i).length ; j++ )
				{
					tabCh.add(tabChaine.get(i)[j].split(","));
				}
			try {
				fichier_source.close();
			} catch (IOException e1) {
				SelectFileChooser.error(e1);
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			for(i = 0; i < indice ; i++)
			{
				x = Double.parseDouble(tabCh.get(i)[0]);
				y = Double.parseDouble(tabCh.get(i)[1]);
				c.addXY(x,y);
			}


		});

		chargerCSVInternet.setOnAction(e -> {
			TextInputDialog dialog = new TextInputDialog("Download");
			dialog.setTitle("Téléchargement d'un CSV par internet");
			dialog.setContentText("Veuillez entrer l'url : ");
			Optional<String> result = dialog.showAndWait();
			try {
				result.ifPresent(url -> {
					String fileName = url.substring(url.lastIndexOf('/') + 1);
					SelectFileChooser.csvDownload(url, "data/"+fileName);
					System.out.println("Success !");
				});
			}
			catch (Exception e1) {
				System.out.println(e1);
			}


		});

		saveCourbes.setOnAction( e -> {
			chemin = SelectFileChooser.showDirChooser();
			System.out.println(chemin);
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
			info.setTitle("A propos");
			info.show();
		});

		Tab tab = new Tab();
        tab.setText("Tab 1");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
         xAxis.setLabel("Month");
        final LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);

        tab.setContent(lineChart);
        tabPane.getTabs().add(tab);

		primaryStage.setScene(scene);
		primaryStage.setHeight(700);
		primaryStage.setWidth(1000);
		primaryStage.show();

	}

}
