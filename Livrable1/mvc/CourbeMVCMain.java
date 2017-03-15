package mvc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.chart.Axis;
import javafx.scene.chart.NumberAxis;
import javafx.stage.Stage;
import mvc.control.CourbeController;
import mvc.model.Courbe;
import mvc.model.CourbeModel;
import mvc.view.CourbeVue;
import mvc.view.CourbeVueConcret;

public class CourbeMVCMain extends Application{
	Courbe<Double,Double> c = new Courbe<Double,Double>();
	public  static  void  main(String  args []) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		try
		{
			String chemin = "/home/infoetu/barbetf/Documents/workspace/Livrable1/src/data/Test.csv";
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
		
		CourbeModel<Double,Double> model = new CourbeModel<Double,Double>();
		model.setCourbe(c);
		CourbeController<Double,Double> control = new CourbeController<Double,Double>(model);
		CourbeVue<Double,Double> vue = new CourbeVueConcret<Double,Double>(model,control,new NumberAxis(),new NumberAxis(),"Test MVC");
		control.addView(vue);
		
	}
	
}	
	