package mvc.model.test;

/*
 * @author Rayan Haddad
 * */

import static org.junit.Assert.assertEquals;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import mvc.model.Courbe;
import mvc.model.CourbeModel;

// TEST POUR COURBE MODEL

public class CourbeTest {
	
	Courbe<Number,Number> courbeData = new Courbe<Number,Number>();
	CourbeModel<Number,Number> model = new CourbeModel<Number,Number>();
	/*CourbeModel<Number,Number> lambdaPaire = new CourbeModel<Number,Number>();
	CourbeModel<Number,Number> lambdaImpair = new CourbeModel<Number,Number>();*/

	@Test
	public void getXtest() {
		Courbe<Integer,Integer> c = new Courbe<Integer,Integer>();
		c.addXY(5,2);
		int x = c.getX(0);
		assertEquals(5,x);
	}

	@Test
	public void getYtest() {	
		Courbe<Integer,Integer> c = new Courbe<Integer,Integer>();
		c.addXY(2,5);
		int y = c.getY(0);
		assertEquals(2,y);
	}








}