package mvc.view;

import java.util.Observer;

import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import mvc.control.CourbeController;
import mvc.model.CourbeModel;

public abstract class CourbeVue<X,Y> extends Stage implements Observer {
	protected CourbeModel<X,Y> model ;
	protected CourbeController<X,Y> controller ;
	final Axis<X> xAxis;
	final Axis<Y> yAxis;
	final LineChart<X,Y> lineChart;
	@SuppressWarnings("rawtypes")
	XYChart.Series series = new XYChart.Series();


	@SuppressWarnings({"unchecked", "rawtypes" })
	public CourbeVue(CourbeModel<X,Y> mod, CourbeController<X,Y> cont,Axis<X> xAx,Axis<Y> yAx,String t){
		super();
		super.setTitle("Projet Modelisation");
		model = mod;
		controller = cont;
		xAxis=xAx;
		yAxis=yAx;
		xAxis.setLabel("Abcisse");
		yAxis.setLabel("Ordonnee");
		lineChart = new LineChart<X,Y>(xAxis,yAxis);
		lineChart.setTitle("Fonction f(x)= "+t);
		//definition de la serie
		series.setName(t);
		// remplir la serie de donnees
		for(int i = 0; i < model.sizeOfCourbe()  ; i++)
		{
			series.getData().add(new XYChart.Data(model.getDataX(i), model.getDataY(i)));
		}

		Scene scene  = new Scene(lineChart,800,600);
		lineChart.getData().add(series);

		this.setScene(scene);

	}

    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  void  setDisplay(Courbe<X,Y> c) {
		for(int i=0;i<c.sizeOfData();i++){
			series.getData().add(new XYChart.Data(c.getX(i),c.getY(i)));
		}
	}

	protected CourbeModel<X,Y> model (){
		return model ;
	}

}
