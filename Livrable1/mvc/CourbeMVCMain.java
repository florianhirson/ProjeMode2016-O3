package mvc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.chart.NumberAxis;
import javafx.stage.Stage;
import mvc.control.CourbeController;
import mvc.model.Courbe;
import mvc.model.CourbeModel;
import mvc.view.CourbeVue;
import mvc.view.CourbeVueConcret;

public class CourbeMVCMain extends Application{
	Courbe<Number,Number> c = new Courbe<Number,Number>();
	public  static  void  main(String  args []) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		Courbe<Number,Number> log = new Courbe<Number,Number>();
		try
		{
			String chemin = "data/Test.csv";
			File fr = new File(chemin);
			System.out.println(fr.getAbsolutePath());
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
				Double x = Double.parseDouble(tabCh.get(i)[0]);
				Double y = Double.parseDouble(tabCh.get(i)[1]);
				c.addXY(x, y);

			}
			
			
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Le fichier est introuvable !");
		}
		
		CourbeModel<Number,Number> model = new CourbeModel<Number,Number>();
		model.setCourbe(c);
		CourbeController<Number,Number> control = new CourbeController<Number,Number>(model);
		CourbeVue<Number,Number> vue = new CourbeVueConcret<Number,Number>(model,control,new NumberAxis(),new NumberAxis(),"Test MVC");
		control.addView(vue);
		
		model.transfoLog4Num(log);
		CourbeModel<Number,Number> modellog = new CourbeModel<Number,Number>();
		modellog.setCourbe(log);
		CourbeController<Number,Number> controllog = new CourbeController<Number,Number>(modellog);
		CourbeVue<Number,Number> vuelog = new CourbeVueConcret<Number,Number>(modellog,controllog,new NumberAxis(),new NumberAxis(),"Test Log");
		controllog.addView(vuelog);
		
		CourbeVue<Number,Number> vueFusion = new CourbeVueConcret<Number,Number>(modellog,controllog,new NumberAxis(),new NumberAxis(),"Test Fusion");

		vueFusion.addSeries(model, "test");
	}
	
}	
	