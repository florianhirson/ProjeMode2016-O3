package mvc.control;
/**
 * Controller et ajout des courbes par methode
 * @author florian barbet
 */
import java.util.ArrayList;

import mvc.model.Courbe;
import mvc.model.CourbeModel;
import mvc.view.CourbeVue;

public class CourbeController<X,Y> {


	private CourbeModel<Number,Number> model;
	@SuppressWarnings("unused")
	private CourbeVue<Number,Number> view = null;
	private ModifieCourbe modcourbe = new ModifieCourbeForm ();

	public CourbeController( CourbeModel<Number,Number> m) {
		model = m;
	}
	

	public void fixeCourbes(ArrayList<Courbe<Number,Number>> listcourbe){
		
		model.setCourbes(listcourbe);
	
	}

	public void addView ( CourbeVue<Number,Number> view ) {
		this.view = view ;
	}
	
	public void doMM(){
		model.addCourbe(modcourbe.doMM(new Courbe<Number,Number>(), model));
		view.addSeries(modcourbe.doMM(new Courbe<Number,Number>(), model),"Moyenne mobile");
	}
	
	
	
	public void doSaisonResidu(){
		model.addCourbe(modcourbe.doSaisonResidu(new Courbe<Number,Number>(), model));
		view.addSeries(modcourbe.doSaisonResidu(new Courbe<Number,Number>(), model),"Saison Residu");

	}
	
	
	
	public void doSaison(){
		model.addCourbe(modcourbe.doSaison(new Courbe<Number,Number>(), model));
		view.addSeries(modcourbe.doSaison(new Courbe<Number,Number>(), model),"Saison");
	}
	
	
	
	public void doDesaisonaliser(){
		model.addCourbe(modcourbe.doDesaisonaliser(new Courbe<Number,Number>(), model));
		view.addSeries(modcourbe.doDesaisonaliser(new Courbe<Number,Number>(), model),"Desaisonaliser");
	}
	
	
	
	public void doLogistique(){
		model.addCourbe(modcourbe.doLogistique(new Courbe<Number,Number>(), model));
		view.addSeries(modcourbe.doLogistique(new Courbe<Number,Number>(), model),"Logistique");
	}
	
	public void doLog(){
		model.addCourbe(modcourbe.doLog(new Courbe<Number,Number>(), model));
		view.addSeries(modcourbe.doLog(new Courbe<Number,Number>(), model),"Logarithme");
	}
	
	public void doBoxCox(){
		model.addCourbe(modcourbe.doBoxCox(new Courbe<Number,Number>(), model));
		view.addSeries(modcourbe.doBoxCox(new Courbe<Number,Number>(), model),"Box Cox");
	}
	
	public void doRegLin(){
		model.addCourbe(modcourbe.doRegLin(new Courbe<Number,Number>(), model));
		view.addSeries(modcourbe.doRegLin(new Courbe<Number,Number>(), model),"Regression Lineaire");
	}
	
	public void doLissageExp1(){
		model.addCourbe(modcourbe.doLissageExp1(new Courbe<Number,Number>(), model));
		view.addSeries(modcourbe.doLissageExp1(new Courbe<Number,Number>(), model),"Lissage exponentiel simple");
	}
	
	public void doLissageExp2(){
		model.addCourbe(modcourbe.doLissageExp2(new Courbe<Number,Number>(), model));
		view.addSeries(modcourbe.doLissageExp2(new Courbe<Number,Number>(), model),"Lissage exponentiel double");
	}

}

