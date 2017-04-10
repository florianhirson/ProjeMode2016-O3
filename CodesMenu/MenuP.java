package mvc.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

public class MenuProjet extends Application{
	private TableView<CourbeModel> personTable;
	private TableColumn<CourbeModel, String> firstCol = new TableColumn<CourbeModel, String>("X");
	private TableColumn<CourbeModel, String> lastCol = new TableColumn<CourbeModel, String>("Y");
	
	public static void main(String[] args)
	{
		System.setProperty("http.proxyPort", "3128");
		System.setProperty("http.proxyHost", "proxy.univ-lille1.fr");
		Application.launch(args);
		
	}
	
	public void start(Stage stage){
		Scene scene = new Scene(ScenePrinc(), 200, 200);
        stage.setScene(scene);
        stage.setHeight(700.0);
        stage.setWidth(800.0);
        stage.show();
	}
	
	public static VBox ScenePrinc(){
		Stage stage = new Stage();
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root);
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
		menuBar.prefWidthProperty().bind(stage.widthProperty());

		MenuItem chargerCSV = new MenuItem("Charger un fichier CSV");
		MenuItem chargerCSVint = new MenuItem("Charger un fichier CSV par Internet");
		MenuItem save = new MenuItem("Sauvegarder les courbes");
		MenuItem exit = new MenuItem("Quitter");
		MenuItem aide = new MenuItem("Aide en ligne");
		MenuItem apropos = new MenuItem("A propos");

		menuF.getItems().addAll(chargerCSV,chargerCSVint,save,exit);
		menuH.getItems().addAll(aide,apropos);		

		exit.setOnAction(e ->{
			stage.close();
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

		for (int i = 0; i < 3; i++) {

			Tab tab = new Tab();
			tab.setText("Tab");
			final CategoryAxis xAxis = new CategoryAxis();
			final NumberAxis yAxis = new NumberAxis();
			xAxis.setLabel("Month");
			final LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);
			tab.setContent(lineChart);
			tabPane.getTabs().add(tab);

		}

		/***** DEBUT TRANSFORMATION *****/	
		transfo.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                transfo.getScene().setRoot(TransfoButton());             
            }
        });
		
		/***** FIN TRANSFORMATION *****/

		VBox vb = new VBox();
		vb.getChildren().addAll(root);
		return vb;
		
	}
	
	public static VBox TransfoButton(){

		Stage stage = new Stage();

		BorderPane root = new BorderPane();
		MenuBar menuBar = new MenuBar();
		int ch=0;
		VBox ajout = new VBox();
		ajout.setSpacing(10);
		ajout.setPadding(new Insets(10, 10, 10, 10));
		VBox ajoutRet = new VBox();
		ajoutRet.setSpacing(70);
		TabPane tabPane = new TabPane();

		root.setTop(menuBar);
		root.setLeft(ajoutRet);
		root.setRight(tabPane);
		root.setMaxSize(110000.0, 600.0);

		Label lAjouV = new Label("Stabilisé la variance de vos données : ");
		Label lAjouTS = new Label("Estimer la tendance et saisonnalité : ");

		VBox ajoutTot1 = new VBox();
		ajoutTot1.setSpacing(10);
		VBox ajoutTot2 = new VBox();
		ajoutTot2.setSpacing(10);


		Button log = new Button("Logarithme");
		log.setMaxSize(150, 150);
		Button boxcox = new Button("Box-Cox");
		boxcox.setMaxSize(150, 150);
		Button logis = new Button("Logistique");
		logis.setMaxSize(150, 150);
		Button mobile = new Button("Moyenne Mobile");
		mobile.setMaxSize(150, 150);
		Button pondere = new Button("Moyenne Mobile Pondéré");
		pondere.setMaxSize(150, 150);
		Button saiso = new Button("Saisonnalité");
		saiso.setMaxSize(150, 150);
		Button lineaire = new Button("Tendance lineaire");
		lineaire.setMaxSize(150, 150);
		Button diff = new Button("Différentiation");
		diff.setMaxSize(150, 150);
		Button retour = new Button("Retour");
		retour.setMaxSize(150, 150);
		//retour.setPadding(new Insets(10,0,0,0));
		
		ajoutTot1.getChildren().addAll(log,boxcox,logis);
		ajoutTot2.getChildren().addAll(mobile,pondere,saiso,lineaire,diff);
		ajout.getChildren().addAll(lAjouV,ajoutTot1,lAjouTS,ajoutTot2);
		ajoutRet.getChildren().addAll(ajout,retour);
		ajoutRet.setSpacing(50.0);

		Menu menuF = new Menu("Fichier");
		Menu menuH = new Menu("Aide");
		menuBar.getMenus().addAll(menuF,menuH);
		menuBar.prefWidthProperty().bind(stage.widthProperty());

		MenuItem chargerCSV = new MenuItem("Charger un fichier CSV");
		MenuItem chargerCSVint = new MenuItem("Charger un fichier CSV par Internet");
		MenuItem save = new MenuItem("Sauvegarder les courbes");
		MenuItem exit = new MenuItem("Quitter");
		MenuItem aide = new MenuItem("Aide en ligne");
		MenuItem apropos = new MenuItem("A propos");

		menuF.getItems().addAll(chargerCSV,chargerCSVint,save,exit);
		menuH.getItems().addAll(aide,apropos);		

		retour.setOnAction(e ->{
			retour.getScene().setRoot(ScenePrinc());
		});

		exit.setOnAction(e ->{
			stage.close();
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

		for (int i = 0; i < 3; i++) {

			Tab tab = new Tab();
			tab.setText("Tab");
			final CategoryAxis xAxis = new CategoryAxis();
			final NumberAxis yAxis = new NumberAxis();
			xAxis.setLabel("Month");
			final LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);
			tab.setContent(lineChart);
			tabPane.getTabs().add(tab);

		}

		
		VBox vb = new VBox();
		vb.getChildren().addAll(root);
		return vb;
		
		
	}
	
}
