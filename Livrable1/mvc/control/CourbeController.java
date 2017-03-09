package mvc.control;

import mvc.model.CourbeModel;
import mvc.view.CourbeVue;

public class CourbeController<X,Y> {


	private CourbeModel<X,Y> model ;
	private CourbeVue<X,Y> view = null ;
	private ModifieCourbe<X,Y> modcourbe = new ModifieCourbeLog<X,Y> ();

	public CourbeController ( CourbeModel m) {
		model = m;
	}
	
	/**
	 * 
	 * TODO quand log sera fait faire les controller
	 * 
	 */

	public void addView ( CourbeVue<X,Y> view ) {
		this.view = view ;
	}
}
