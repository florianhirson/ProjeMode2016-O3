package projet;

import java.util.Scanner;

public class Menu {

	Scanner sc = new Scanner(System.in);

	private int choix;

	void erreurChoix() {

		System.out.println();
		System.out.println("##########   ERREUR   ##########");
		System.out.println("#                              #");
		System.out.println("#           Ce choix           #");
		System.out.println("#         n'existe pas         #");
		System.out.println("#                              #");
		System.out.println("################################");
	}


	void menuLancement(){

		System.out.println();
		System.out.println("##########  PROJET   ###########");
		System.out.println("#                              #");
		System.out.println("#     Voulez-vous importer     #");
		System.out.println("#     une série de données ?   #");
		System.out.println("#                              #");
		System.out.println("################################");
		System.out.println("#                              #");
		System.out.println("#  1 : Charger fichier CSV     #");
		System.out.println("#                              #");					
		System.out.println("#  9 : Quitter PROJET          #");		
		System.out.println("#                              #");
		System.out.println("################################");

		choix = Integer.parseInt(sc.next());

		switch(choix){
		case 1 : 
			//méthode charger fichier CSV
		case 9 :
			//menu quitter et sauvegarde
		default:
			erreurChoix();
			System.out.println();
			menuLancement();
		}
	}

	void menuChoixActions(){

		System.out.println();
		System.out.println("##########  PROJET   ###########");
		System.out.println("#                              #");
		System.out.println("#     quelles actions voulez   #");
		System.out.println("#        vous effectuer        #");
		System.out.println("#                              #");
		System.out.println("################################");
		System.out.println("#                              #");
		System.out.println("#  1 : Une transformation      #");
		System.out.println("#                              #");	
		System.out.println("#  2 : Une analyse             #");
		System.out.println("#                              #");	
		System.out.println("#  3 : une prévision           #");
		System.out.println("#                              #");	
		System.out.println("#  0 : Retour menu précédent   #");
		System.out.println("#                              #");	
		System.out.println("#  9 : Quitter PROJET          #");		
		System.out.println("#                              #");
		System.out.println("################################");

		choix = Integer.parseInt(sc.next());

		switch(choix){
		case 1 :
			//menu choix transformations
		case 2 :
			//menu choix analyse
		case 3 :
			//menu choix prévision
		case 0 :
			menuLancement();
		case 9 :
			//menu quitter et sauvegarde
		default:
			erreurChoix();
			System.out.println();
		}
	}
	
	void menuChoixTransformation(){
		
		System.out.println();
		System.out.println("##############   PROJET   ############");
		System.out.println("#                                    #");
		System.out.println("#  quelles transformations voulez    #");
		System.out.println("#          vous effectuer ?          #");
		System.out.println("#                                    #");
		System.out.println("######################################");
		System.out.println("#                                    #");
		System.out.println("#  1 : Logarithme                    #");
		System.out.println("#                                    #");	
		System.out.println("#  2 : Box-Cox                       #");
		System.out.println("#                                    #");	
		System.out.println("#  3 : Logistique                    #");
		System.out.println("#                                    #");	
		System.out.println("#  4 : Moyenne mobile                #");
		System.out.println("#                                    #");
		System.out.println("#  5 : Moyenne mobile pondérée       #");
		System.out.println("#                                    #");
		System.out.println("#  6 : Saisonnalité                  #");
		System.out.println("#                                    #");	
		System.out.println("#  7 : Tendance linéaire             #");
		System.out.println("#                                    #");
		System.out.println("#  8 : Opérateur de différenciation  #");
		System.out.println("#                                    #");
		System.out.println("#  0 : Retour menu précédent         #");
		System.out.println("#                                    #");
		System.out.println("#  9 : Quitter PROJET                #");		
		System.out.println("#                                    #");
		System.out.println("######################################");
		
		choix = Integer.parseInt(sc.next());
		
		switch(choix){
		case 1 :
			//méthode logarithme
		case 2 :
			//méthode box-cox
		case 3 :
			//méthode logistique
		case 4 :
			//méthode moyenne mobile
		case 5 :
			//méthode moyenne pondérée
		case 6 :
			//méthode saisonnalité
		case 7 :
			//méthode tendance linéaire
		case 8 :
			//méthode opérateur de différenciation
		case 0 :
			menuChoixActions();
		case 9 :
			//menu quitter et sauvegarde
	    default :
	    	erreurChoix();
			System.out.println();
		}
	}
	
	void menuChoixPrevisions(){
		
		System.out.println();
		System.out.println("##############   PROJET   ############");
		System.out.println("#                                    #");
		System.out.println("#     quelles prévisions voulez      #");
		System.out.println("#        vous effectuer ?            #");
		System.out.println("#                                    #");
		System.out.println("######################################");
		System.out.println("#                                    #");
		System.out.println("#  1 : Lissage exponentiel simple    #");
		System.out.println("#                                    #");	
		System.out.println("#  2 : Lissage exponentiel double    #");
		System.out.println("#                                    #");	
		System.out.println("#  3 : Holt-Winters                  #");
		System.out.println("#                                    #");
		System.out.println("#  0 : Retour menu précédent         #");
		System.out.println("#                                    #");
		System.out.println("#  9 : Quitter PROJET                #");		
		System.out.println("#                                    #");
		System.out.println("######################################");
		
		choix = Integer.parseInt(sc.next());
		
		switch(choix){
		case 1:
			//méthode lissage exponentiel simple
		case 2 :
			//méthode lissage exponentiel double
		case 3 :
			//méthode holt-winters
		case 0 :
			menuChoixActions();
		case 9 :
			//menu quitter et sauvegarde
		default :
			erreurChoix();
			System.out.println();
		}
	}
	
	void menuChoixAnalyse(){
		
		System.out.println();
		System.out.println("##############   PROJET   ############");
		System.out.println("#                                    #");
		System.out.println("#     quelles analyses voulez        #");
		System.out.println("#        vous effectuer ?            #");
		System.out.println("#                                    #");
		System.out.println("######################################");
		System.out.println("#                                    #");
		System.out.println("#  1 : Lissage exponentiel simple    #");
		System.out.println("#                                    #");	
		System.out.println("#  2 : Lissage exponentiel double    #");
		System.out.println("#                                    #");	
		System.out.println("#  3 : Autocorrélations des résidus  #");
		System.out.println("#                                    #");
		System.out.println("#  0 : Retour menu précédent         #");
		System.out.println("#                                    #");
		System.out.println("#  9 : Quitter PROJET                #");		
		System.out.println("#                                    #");
		System.out.println("######################################");
		
		choix = Integer.parseInt(sc.next());
		
		switch(choix){
		case 1:
			//méthode lissage exponentiel simple
		case 2 :
			//méthode lissage exponentiel double
		case 3 :
			//méthode autocorrélation
		case 0 :
			menuChoixActions();
		case 9 :
			//menu quitter et sauvegarde
		default :
			erreurChoix();
			System.out.println();
		}
	}
	
	











}
