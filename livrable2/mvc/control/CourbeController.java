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
	private CourbeVue<X,Y> view = null;
	private ModifieCourbe modcourbe = new ModifieCourbeForm ();

	public CourbeController( CourbeModel<Number,Number> m) {
		model = m;
	}
	

	public void fixeCourbes(ArrayList<Courbe<Number,Number>> listcourbe){
		model.setCourbes(listcourbe);
	}

	public void addView ( CourbeVue<X,Y> view ) {
		this.view = view ;
	}
	
	public void doMM(){
		model.addCourbe(modcourbe.doMM(new Courbe<Number,Number>(), model));
	}
	
	public void doSaisonResidu(){
		model.addCourbe(modcourbe.doSaisonResidu(new Courbe<Number,Number>(), model));
	}
	
	public void doSaison(){
		model.addCourbe(modcourbe.doSaison(new Courbe<Number,Number>(), model));
	}
	
	public void doDesaisonaliser(){
		model.addCourbe(modcourbe.doDesaisonaliser(new Courbe<Number,Number>(), model));
	}
	
	public void doLogistique(){
		model.addCourbe(modcourbe.doLogistique(new Courbe<Number,Number>(), model));
	}
	
	public void doLog(){
		model.addCourbe(modcourbe.doLog(new Courbe<Number,Number>(), model));
	}
	
	public void doBoxCox(){
		model.addCourbe(modcourbe.doBoxCox(new Courbe<Number,Number>(), model));
	}
	
	public void doRegLin(){
		model.addCourbe(modcourbe.doRegLin(new Courbe<Number,Number>(), model));
	}
	
	public void doLissageExp1(){
		model.addCourbe(modcourbe.doLissageExp1(new Courbe<Number,Number>(), model));
	}
	
	public void doLissageExp2(){
		model.addCourbe(modcourbe.doLissageExp2(new Courbe<Number,Number>(), model));
	}

}

