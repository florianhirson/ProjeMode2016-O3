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
		Courbe<Number,Number> logis = new Courbe<Number,Number>();

		System.out.println("Inserez le fichier de donnee a utiliser  : ");
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






	

		int condition = 0;
		int scan = 0;
		ArrayList<Integer> choice = new ArrayList<Integer>();

		System.out.println("Voir resultat pour (number only): ");
		System.out.println("-> 1 : Moyenne Mobile (Mt) ");
		System.out.println("-> 2 : Xt-Mt ");
		System.out.println("-> 3 : St : saison");
		System.out.println("-> 4 : Xt-St desaisonnalisation ");
		System.out.println("-> 5 : Logarithme Yt1 ");
		System.out.println("-> 6 : Logistique Yt2 ");
		System.out.println("-> 0 : Fin");

		while(condition==0){
			System.out.println("Votre choix[0 = Fin]:>>");
			scan = sc.nextInt();

			for(int i = 0; i < choice.size();i++){
				if(choice.get(i)==scan){
					scan=-1;	
				}

			}

			if(scan == 0)condition++;
			else if(scan == -1)
				System.out.println("Option déjà saisie");
			else{
				choice.add(scan);
				switch(scan){
				case 1 :
					model.moyenneMobile(cmm,1);
					break;
				case 2 : 
					model.saisonResidu(csr,1);
					break;
				case 3 :
					model.saison(cs,1);
					break;
				case 4 :
					model.desaisonaliser(cmd,1);
					break;
				case 5 :
					model.transfoLog(log,1);
					break;
				case 6 : 
					model.logistique(logis,1);
					break;
				default : System.out.println("Option inexistante: "+scan);break;
				}
			}
			


		}



		int choix = 0;
		System.out.println("Afficher  ? Yes = 1 No = 0");
		choix = sc.nextInt();
		if(Integer.valueOf(choix)==1)
		{

			CourbeModel<Number,Number> modelFusion = new CourbeModel<Number,Number>();
			modelFusion.setCourbe(c);
			CourbeController<Number,Number> controlF = new CourbeController<Number,Number>(modelFusion);
			CourbeVue<Number,Number> vueF = new CourbeVueConcret<Number,Number>(modelFusion,controlF,new NumberAxis(),new NumberAxis(),"Fusion : "+data);

			controlF.addView(vueF);

			for(int i = 0; i < choice.size();i++){
				switch(choice.get(i)){
				case 1 :
					vueF.addSeries(cmm, "Mt");
					break;
				case 2 : 
					vueF.addSeries(csr, "Xt-Mt");
					break;
				case 3 :
					vueF.addSeries(cs, "St");
					break;
				case 4 :
					vueF.addSeries(cmd, "Xt-St");
					break;
				case 5 :
					vueF.addSeries(log, "Yt1");
					break;
				case 6 : 
					vueF.addSeries(logis, "Yt2");
					break;

				}
			}
			vue.show();
			vueF.show();
		}




	}

}	
