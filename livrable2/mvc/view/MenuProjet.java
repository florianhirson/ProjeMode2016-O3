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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuProjet extends Application{

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

		@SuppressWarnings("rawtypes")
		ChoiceBox cAjoutT = new ChoiceBox();
		cAjoutT.setPrefWidth(150);

		@SuppressWarnings("rawtypes")
		ChoiceBox cAjoutA = new ChoiceBox();
		cAjoutA.setPrefWidth(150);

		@SuppressWarnings("rawtypes")
		ChoiceBox cAjoutP = new ChoiceBox();
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
		menuBar.getMenus().addAll(menuF,menuH);
	    menuBar.prefWidthProperty().bind(primaryStage.widthProperty());

		menuF.getItems().add(new MenuItem("Charger un fichier CSV"));
		menuF.getItems().add(new MenuItem("Charger un fichier CSV par Internet"));
		menuF.getItems().add(new MenuItem("Sauvegarder les courbes"));
		menuF.getItems().add(new SeparatorMenuItem());
		menuF.getItems().add(new MenuItem("Exit"));
		menuH.getItems().add(new MenuItem("Aide en ligne"));



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
