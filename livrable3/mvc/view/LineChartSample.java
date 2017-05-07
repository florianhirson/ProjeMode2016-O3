package mvc.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LineChartSample extends Application {

    public static void main(final String[] args) {
	launch(args);
    }

    @Override
    public void start(final Stage stage) {
    	TabPane tabPane = new TabPane();
    	Tab tab = new Tab();
	stage.setTitle("Line Chart Sample");
	// defining the axes
	final NumberAxis xAxis = new NumberAxis();
	final NumberAxis yAxis = new NumberAxis();
	xAxis.setAutoRanging(true);
	xAxis.setForceZeroInRange(false);
	yAxis.setAutoRanging(true);
	yAxis.setForceZeroInRange(false);
	xAxis.setLabel("Number of Month");
	// creating the chart
	final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);

	lineChart.setTitle("Stock Monitoring, 2010");
	// defining a series
	final XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
	series.setName("My portfolio");
	// populating the series with data
	series.getData().add(new XYChart.Data<Number, Number>(1, 23));
	series.getData().add(new XYChart.Data<Number, Number>(2, 14));
	series.getData().add(new XYChart.Data<Number, Number>(3, 15));
	series.getData().add(new XYChart.Data<Number, Number>(4, 24));
	series.getData().add(new XYChart.Data<Number, Number>(5, 34));
	series.getData().add(new XYChart.Data<Number, Number>(6, 36));
	series.getData().add(new XYChart.Data<Number, Number>(7, 22));
	series.getData().add(new XYChart.Data<Number, Number>(8, 45));
	series.getData().add(new XYChart.Data<Number, Number>(9, 43));
	series.getData().add(new XYChart.Data<Number, Number>(10, 17));
	series.getData().add(new XYChart.Data<Number, Number>(11, 29));
	series.getData().add(new XYChart.Data<Number, Number>(12, 25));

	// DO NOT ADD DATA TO CHART
	// bc.getData().addAll(series1, series2, series3);
	final StackPane pane = new StackPane();
	pane.getChildren().add(lineChart);
	final Scene scene = new Scene(tabPane);
	new ZoomManager(pane, lineChart, series);

	tab.setText("Blablabla");
	tab.setContent(pane);
    tabPane.getTabs().add(tab);
	stage.setScene(scene);
	stage.show();
    }
}
