import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;


public class LineChartSample extends Application {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void start(Stage stage) throws IOException {
		stage.setTitle("Line Chart Sample");
		//defining the axes
		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Number of Month");
		//creating the chart
		final LineChart<Number,Number> lineChart = 
				new LineChart<Number,Number>(xAxis,yAxis);

		lineChart.setTitle("Stock Monitoring, 2010");
		//defining a series
		XYChart.Series series = new XYChart.Series();
		series.setName("My portfolio");
		//populating the series with data



		try
		{
			String chemin = "C:/Users/florian/Desktop/Recherche/Java/ProjetS3/Test.csv";
			BufferedReader fichier_source = new BufferedReader(new FileReader(chemin));
			String chaine;

			ArrayList<String[]> tabChaine = new ArrayList<String[]>();
			ArrayList<String[]> tabCh = new ArrayList<String[]>();

			int indice = 0;
			while((chaine = fichier_source.readLine())!= null)
			{


				tabChaine.add(chaine.split(";"));

				indice++;

			}
			for(int i = 0; i < indice ; i++)
				for(int j = 0; j < tabChaine.get(i).length ; j++ )
				{
					tabCh.add(tabChaine.get(i)[j].split(","));
				}
			fichier_source.close();

			for(int i = 0; i < indice ; i++)
			{
				int x = Integer.parseInt(tabCh.get(i)[0]);
				int y = Integer.parseInt(tabCh.get(i)[1]);
				series.getData().add(new XYChart.Data(x, y));

			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Le fichier est introuvable !");
		}



		Scene scene  = new Scene(lineChart,800,600);
		lineChart.getData().add(series);

		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}