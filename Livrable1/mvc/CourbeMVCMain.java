package mvc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

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
	Courbe<Number,Number> logis = new Courbe<Number,Number>();
	Scanner sc = new Scanner(System.in);
	public  static  void  main(String  args []) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		Courbe<Number,Number> log = new Courbe<Number,Number>();
		Courbe<Number,Number> cmm = new Courbe<Number,Number>();
		Courbe<Number,Number> csr = new Courbe<Number,Number>();
		Courbe<Number,Number> cs = new Courbe<Number,Number>();
		Courbe<Number,Number> cmd = new Courbe<Number,Number>();
		System.out.println("Inserez le fichier de donnee a utiliser : ");
		String data = sc.nextLine();
		try
		{
			
			String chemin = "data/"+data;
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
		CourbeVue<Number,Number> vue = new CourbeVueConcret<Number,Number>(model,control,new NumberAxis(),new NumberAxis(),data);
		control.addView(vue);
		
		
		
		
		
	
		CourbeModel<Number,Number> modelFusion = new CourbeModel<Number,Number>();
		modelFusion.setCourbe(c);
		CourbeController<Number,Number> controlF = new CourbeController<Number,Number>(modelFusion);
		CourbeVue<Number,Number> vueF = new CourbeVueConcret<Number,Number>(modelFusion,controlF,new NumberAxis(),new NumberAxis(),"Fusion : "+data);
		
		controlF.addView(vueF);
		
		int couAff = 0;
		
		
		
		couAff = 0;
		System.out.println("Voir resultat Mt : Yes = 1 No = 0");
		couAff=sc.nextInt();
		if(Integer.valueOf(couAff)==1){
			model.moyenneMobile(cmm,couAff);
			vueF.addSeries(cmm, "Mt");
		}
		
		couAff = 0;
		System.out.println("Voir resultat Xt-Mt : Yes = 1 No = 0");
		couAff=sc.nextInt();
		if(Integer.valueOf(couAff)==1){
			model.saisonResidu(csr,couAff);
			vueF.addSeries(csr, "Xt-Mt");
		}
		
		
		couAff = 0;
		System.out.println("Voir resultat St : Yes = 1 No = 0");
		couAff=sc.nextInt();
		if(Integer.valueOf(couAff)==1){
			model.saison(cs,couAff);
			vueF.addSeries(cs, "St");
		}
		
		couAff = 0;
		System.out.println("Voir resultat Xt-St : Yes = 1 No = 0");
		couAff=sc.nextInt();
		if(Integer.valueOf(couAff)==1){
			model.desaisonaliser(cmd,couAff);
			vueF.addSeries(cmd, "Xt-St");
		}
		
		couAff = 0;
		System.out.println("Voir resultat Log(Xt) >> Yt1 : Yes = 1 No = 0");
		couAff=sc.nextInt();
		if(Integer.valueOf(couAff)==1){
			model.transfoLog4Num(log,couAff);
			vueF.addSeries(log, "Yt1");
		}
		
		
		couAff = 0;
		System.out.println("Voir resultat Log(Xt/(Xt-1)) >> Yt2 : Yes = 1 No = 0");
		couAff=sc.nextInt();
		if(Integer.valueOf(couAff)==1){
			model.logistique(logis,couAff);
			vueF.addSeries(logis, "Yt2");
		}
		
		
		
		
		
		int choix = 0;
		System.out.println("Afficher  ? Yes = 1 No = 0");
		choix = sc.nextInt();
		if(Integer.valueOf(choix)==1)
		{
			vue.show();
			vueF.show();
		}
		
		
		
			
	}
	
}	
	