package mvc.view;

import javafx.application.Application;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import mvc.model.CourbeModel;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Menu2 extends Application{

	private TableView<CourbeModel> personTable;
	private TableColumn<CourbeModel, String> firstCol = new TableColumn<CourbeModel, String>("X");
	private TableColumn<CourbeModel, String> lastCol = new TableColumn<CourbeModel, String>("Y");

	public static void main(String[] args)
	{
		System.setProperty("http.proxyPort", "3128");
		System.setProperty("http.proxyHost", "proxy.univ-lille1.fr");
		Application.launch(args);
	}

	@SuppressWarnings("rawtypes")
	@Override

	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		MenuBar menuBar = new MenuBar();
		int ch=0;
		VBox ajout = new VBox();
		ajout.setSpacing(10);
		ajout.setPadding(new Insets(10, 10, 10, 10));

		TabPane tabPane = new TabPane();

		root.setTop(menuBar);
		root.setLeft(ajout);
		root.setRight(tabPane);
		root.setMaxSize(110000.0, 600.0);

		Label lAjouT = new Label("Ajouter une transformation : ");
		Label lAjouA = new Label("Ajouter une analyse : ");
		Label lAjouP = new Label("Ajouter une prévision : ");

		HBox ajoutT = new HBox();
		ajoutT.setSpacing(10);

		HBox ajoutA = new HBox();
		ajoutA.setSpacing(10);

		HBox ajoutP = new HBox();
		ajoutP.setSpacing(10);

		Button transfo = new Button("Transformation");
		transfo.setMaxSize(150, 150);
		Button prevision = new Button("Prevision");
		prevision.setMaxSize(150, 150);
		Button analyse = new Button("Analyse");
		analyse.setMaxSize(150, 150);

		ajoutT.getChildren().addAll(transfo);
		ajoutA.getChildren().addAll(analyse);
		ajoutP.getChildren().addAll(prevision);

		ajout.getChildren().addAll(lAjouT,transfo,lAjouA,prevision,lAjouP,analyse);

		Menu menuF = new Menu("Fichier");
		Menu menuH = new Menu("Aide");
		menuBar.getMenus().addAll(menuF,menuH);
		menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
		
		MenuItem chargerCSV = new MenuItem("Charger un fichier CSV");
		MenuItem chargerCSVint = new MenuItem("Charger un fichier CSV par Internet");
		MenuItem save = new MenuItem("Sauvegarder les courbes");
		MenuItem exit = new MenuItem("Exit");
		MenuItem aide = new MenuItem("Aide en ligne");
		MenuItem apropos = new MenuItem("A propos");

		menuF.getItems().addAll(chargerCSV,chargerCSVint,save,exit);
		menuH.getItems().addAll(aide,apropos);		

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
		
		for (int i = 0; i < 1 + ch; i++) {
			Tab tab = new Tab();
			tab.setText("Tab" + i);
			final CategoryAxis xAxis = new CategoryAxis();
			final NumberAxis yAxis = new NumberAxis();
			xAxis.setLabel("Month");
			final LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);
			tab.setContent(lineChart);
			tabPane.getTabs().add(tab);
			
			transfo.setOnAction(e ->{
				//++ch;
			});
		}
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setHeight(700);
		primaryStage.setWidth(1000);
		primaryStage.show();
	}

}

