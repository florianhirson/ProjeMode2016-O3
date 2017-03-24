package mvc;

import java.io.BufferedReader;
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


	@Override
	public void start(Stage stage) throws Exception {

// Version ou on a plein de fenetres

		String data = "";
		String chemin = "";
		String chaine = "";
		int condition = 0;
		int scan = 0;
		int choixaction = -1;
		int indice = 0;
		int choix = 0;
		int i,j,k;
		Double x,y;

		BufferedReader fichier_source;


		Courbe<Number,Number> cmm = new Courbe<Number,Number>();
		Courbe<Number,Number> csr = new Courbe<Number,Number>();
		Courbe<Number,Number> cs = new Courbe<Number,Number>();
		Courbe<Number,Number> cmd = new Courbe<Number,Number>();

		Courbe<Number,Number> logis = new Courbe<Number,Number>();
		Courbe<Number,Number> log = new Courbe<Number,Number>();
		Courbe<Number,Number> bc = new Courbe<Number,Number>();

		ArrayList<Courbe<Number,Number>> listCourbe = new ArrayList<Courbe<Number,Number>>(); // permet d'indexer les courbes et donc de modifier la couleur d'une courbe visée
		ArrayList<Integer> choice = new ArrayList<Integer>();
		ArrayList<String[]> tabChaine = new ArrayList<String[]>();
		ArrayList<String[]> tabCh = new ArrayList<String[]>();


		CourbeModel<Number,Number> model; 				//	Modele MVC
		CourbeVue<Number,Number> vue;	                // en preparation pour Livrable 2
		CourbeController<Number,Number> control;        // structure OK

		CourbeModel<Number,Number> modelF = null; 				//	Modele MVC
		CourbeVue<Number,Number> vueF = null;	                // en preparation pour Livrable 2
		CourbeController<Number,Number> controlF = null;        // structure OK
		
		
		ArrayList<CourbeModel<Number,Number>> listModel = new ArrayList<CourbeModel<Number,Number>>();
		ArrayList<CourbeVue<Number,Number>> listView = new ArrayList<CourbeVue<Number,Number>>();
		ArrayList<CourbeController<Number,Number>> listControl = new ArrayList<CourbeController<Number,Number>>();



		System.out.println("Inserez le fichier de donnee a utiliser  : ");
		data = sc.nextLine();
		try
		{
			chemin = "data/"+data;
			fichier_source = new BufferedReader(new FileReader(chemin));
			while((chaine = fichier_source.readLine())!= null)
			{
				tabChaine.add(chaine.split(";"));
				indice++;
			}

			for( i = 0; i < indice ; i++)
				for( j = 0; j < tabChaine.get(i).length ; j++ )
				{
					tabCh.add(tabChaine.get(i)[j].split(","));
				}
			fichier_source.close();

			for(i = 0; i < indice ; i++)
			{
				x = Double.parseDouble(tabCh.get(i)[0]);
				y = Double.parseDouble(tabCh.get(i)[1]);
				c.addXY(x,y);

			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Le fichier est introuvable !");
		}


		model = new CourbeModel<Number,Number>();
		model.setCourbe(c);
		control = new CourbeController<Number,Number>(model);
		vue = new CourbeVueConcret<Number,Number>(model,control,new NumberAxis(),new NumberAxis(),data);
		control.addView(vue);



		listCourbe.add(c);
		System.out.println("Action :  Transformation = 1 Analyse : 2 ?"); 
		System.out.println(":>>");
		choixaction = sc.nextInt();


		if(choixaction == 1 || choixaction == 2){
				if(choixaction == 1){
					System.out.println("Voir resultat pour (number only): ");
					System.out.println("-> 1 : Logarithme Yt1 ");
					System.out.println("-> 2 : BoxCox BC ");
					System.out.println("-> 3 : Logistique Yt2 ");
					System.out.println("-> 4 : Moyenne Mobile (Mt) ");
					System.out.println("-> 5 : Xt-Mt ");
					System.out.println("-> 6 : St : saison");
					System.out.println("-> 7 : Xt-St desaisonnalisation ");
					System.out.println("-> 0 : Fin");
				}else{
					System.out.println("Voir resultat pour (un seul choix): ");
					System.out.println("-> 0 : Fin");
				}
			while(condition==0){
				
				System.out.println("0 = END :>>");
				scan = sc.nextInt();
				for( i = 0; i < choice.size();i++){
					if(choice.get(i)==scan){
						scan=-1;	
					}
				}
				if(scan == 0 )condition++;
				else if(scan == -1)
					System.out.println("Option déjà saisie");
				else{
					choice.add(scan);
					switch(scan){
					case 1 :
						if(choixaction == 1){
							model.transfoLog(log,1);
							listCourbe.add(log);
						}

						break;
					case 2 : 
						if(choixaction == 1){
							model.transfoBoxCox(bc,1);
							listCourbe.add(bc);

						}else{System.out.println("Option inexistante: "+scan);}
						break;
					case 3 :
						if(choixaction == 1){
							model.logistique(logis,1);
							listCourbe.add(logis);

						}else{System.out.println("Option inexistante: "+scan);}
						break;
					case 4 :

						if(choixaction == 1){
							model.moyenneMobile(cmm,1);
							listCourbe.add(cmm);
						}else{System.out.println("Option inexistante: "+scan);}
						break;

					case 5 : 
						if(choixaction == 1){
							model.saisonResidu(csr,1);
							listCourbe.add(csr);
						}else{System.out.println("Option inexistante: "+scan);}


						break;
					case 6 : 
						if(choixaction == 1){
							model.saison(cs,1);
							listCourbe.add(cs);
						}else{System.out.println("Option inexistante: "+scan);}
						break;
					case 7 : 
						if(choixaction == 1){
							model.desaisonaliser(cmd,1);
							listCourbe.add(cmd);
						}else{System.out.println("Option inexistante: "+scan);}
						break; 

					default : System.out.println("Option inexistante: "+scan);break;
					}
				}
			}
		}else{
			System.out.println("Ni analyse, ni transformation Affichage de la courbe de base !");
			vue.show();
			System.exit(0);
		}



		System.out.println("Afficher  ? Yes = 1 No = 0");
		choix = sc.nextInt();
		if(Integer.valueOf(choix)==1)
		{

			

			for(i = 0; i < choice.size();i++){
				
				
				listModel.add( new CourbeModel<Number,Number>());
				modelF = listModel.get(i);
				modelF.setCourbe(c);
				listControl.add(new CourbeController<Number,Number>(modelF));
				controlF = listControl.get(i);
				listView.add(new CourbeVueConcret<Number,Number>(modelF,controlF,new NumberAxis(),new NumberAxis(),data));
				vueF = listView.get(i);
				
				controlF.addView(vueF);
				
				switch(choice.get(i)){
				case 1 :
					vueF.addSeries(log, "Yt1");
					vueF.setTitle("Logarithme");
					break;
				case 2 : 
					vueF.addSeries(bc, "BC");
					vueF.setTitle("BoxCox");
					break;
				case 3 :
					vueF.addSeries(logis, "Yt2");
					vueF.setTitle("Logistique");
					break;
				case 4 :
					vueF.addSeries(cmm, "Mt");	
					vueF.setTitle("Moyenne Mobile");
					break;
				case 5 :
					vueF.addSeries(csr, "Xt-Mt");
					vueF.setTitle("Moyenne pondérée");
					break;
				case 6 :
					vueF.addSeries(cs, "St");
					vueF.setTitle("Saisonnalite");
					break;
				case 7 : 
					vueF.addSeries(cmd, "Xt-St");
					vueF.setTitle("Desaisonnalisation");
					break;
				}
				
				
					vueF.setColorSeries(c,listCourbe.indexOf(c), "blue");
					for(k = 1 ; k < listCourbe.size();k++){
						vueF.setColorSeries(listCourbe.get(k), listCourbe.indexOf(listCourbe.get(k)) , "red");
					}
				
				vueF.show();
			}
			
			
		}
		//System.exit(0);
	}

	public  static  void  main(String  args []) {
		launch(args);
	}


}	