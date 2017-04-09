package mvc.model.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mvc.model.Courbe;
import mvc.model.CourbeModel;

public class CourbeModelPrevisionTest {

	Courbe<Number,Number> courbeData = new Courbe<Number,Number>();
	CourbeModel<Number,Number> model = new CourbeModel<Number,Number>();
	Courbe<Number,Number> c1 = new Courbe<Number,Number>();
	Courbe<Number,Number> c2 = new Courbe<Number,Number>();
	
	@Before
	public void setUp() throws Exception {
		
		courbeData.addXY(1,3.0);
		courbeData.addXY(2,4.0);
		courbeData.addXY(3,4.5);
		courbeData.addXY(4,4.0);
		model.setCourbe(courbeData);
		model.setBeta(0.5);
		model.setOrdre(4);

		
		model.lissage_exp1et2(c1, c2);
	}

	@Test
	public void lissageExpSimpleTest() {
		assertEquals(3.8125,(double)c1.getY(3),0.0001);	
	}

	@Test
	public void lissageExpDoubleTest(){
		assertEquals(3.375,(double)c2.getY(4),0.001);	
		assertEquals(3.375,(double)c2.getY(5),0.001);	
	}
	
}
