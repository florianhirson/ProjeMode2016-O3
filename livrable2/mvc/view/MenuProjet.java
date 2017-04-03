package testProjet;

import javafx.application.Application;
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
		TabPane tabPane = new TabPane();
		root.setTop(menuBar);
		root.setLeft(ajout);
		root.setCenter(tabPane);
		
		HBox ajoutT = new HBox();
		ChoiceBox cAjoutT = new ChoiceBox();
		Button bAjoutT = new Button("Ajouter");
		ajoutT.getChildren().addAll(cAjoutT,bAjoutT);
		ajout.getChildren().add(ajoutT);
		
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
		primaryStage.show();
		
		
	}

}
