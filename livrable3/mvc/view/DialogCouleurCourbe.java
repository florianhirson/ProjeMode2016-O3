package mvc.view;

import java.util.ArrayList;
import java.util.Set;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import mvc.model.Courbe;

public class DialogCouleurCourbe extends Stage {

	private int numCourbe;
	private LineChart<Number, Number> copie;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DialogCouleurCourbe(ObservableList<Series<Number, Number>> observableList, LineChart<Number, Number> lineChart, StackPane pane) {
		super();
		ArrayList<XYChart.Series> l = new ArrayList();
		numCourbe = 0;
		copie = lineChart;
		Stage stage = new Stage();
		VBox root = new VBox(10);
		Scene scene = new Scene(root);
		stage.setResizable(false);
		stage.setScene(scene);

		HBox h = new HBox(10);
		Button ok = new Button("Ok");
		Button annuler = new Button("Annuler");

		h.getChildren().addAll(ok, annuler);
		ColorPicker couleur = new ColorPicker(Color.RED);
		ListView<Series<Number, Number>> list = new ListView<>(observableList);
		root.getChildren().addAll(couleur,list,h);


		list.setOnMouseClicked(e -> {
			numCourbe = list.getSelectionModel().getSelectedIndex();
			System.out.println("NumCourbe : "+ numCourbe++);
		});

		couleur.setOnAction(e -> {
			String hex = "#" + Integer.toHexString(couleur.getValue().hashCode());
			setColor(numCourbe, hex);
		});

		ok.setOnAction(e-> {
			ObservableList tmp = copie.getData();
			lineChart.getData().clear();
			lineChart.getData().addAll(tmp);

			//new ZoomManager(pane, lineChart, l);
			stage.close();
		});

		annuler.setOnAction(e -> {
			copie = null;
			stage.close();
		});
		stage.showAndWait();

	}

	public void setColor( int nbCourbe, String color){
		copie.setStyle("CHART_COLOR_"+ nbCourbe+": "+color+";");
	}



}
