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

public class CourbeModelTest {
	
	Courbe<Number,Number> courbeData = new Courbe<Number,Number>();
	CourbeModel<Number,Number> model = new CourbeModel<Number,Number>();
	CourbeModel<Number,Number> modelPaire = new CourbeModel<Number,Number>();
	CourbeModel<Number,Number> modelImpair = new CourbeModel<Number,Number>();
	/*CourbeModel<Number,Number> lambdaPaire = new CourbeModel<Number,Number>();
	CourbeModel<Number,Number> lambdaImpair = new CourbeModel<Number,Number>();*/

	@Before
	public void setUp() throws Exception {

		String chemin = "";
		String chaine = "";


		int i,j,indice=0;
		Double x,y;
		BufferedReader fichier_source;
		ArrayList<String[]> tabChaine = new ArrayList<String[]>();
		ArrayList<String[]> tabCh = new ArrayList<String[]>();


		try
		{
			chemin = "data/Test.csv";
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
				courbeData.addXY(x,y);

			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Le fichier est introuvable !");
		}

		modelImpair.setCourbe(courbeData);
		modelPaire.setCourbe(courbeData);
		modelPaire.setOrdre(4);
		modelImpair.setOrdre(3);
		/*lambdaPaire.setLambda(4);
		lambdaImpair.setOrdre(3);*/

	}
	@Test
	public void moyenneMobilePaireTest(){
		Courbe<Number,Number> cmm = new Courbe<Number,Number>();
		modelPaire.moyenneMobile(cmm,0);
		assertEquals(courbeData.getX(2), cmm.getX(0));
		assertEquals(4.475, (double) cmm.getY(0),0.001);
	}

	@Test
	public void moyenneMobileImpairTest(){
		Courbe<Number,Number> cmm = new Courbe<Number,Number>();
		modelImpair.moyenneMobile(cmm,0);
		assertEquals(courbeData.getX(2), cmm.getX(0));
		assertEquals(4.3, (double) cmm.getY(0),0.1);
	}

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

	@Test
	public void setCourbeTest(){
		Courbe<Number, Number> tmp = new Courbe<Number, Number>();
		Courbe<Number, Number> c = new Courbe<Number,Number>();
		c.addXY(1, 5.0);
		model.setCourbe(c);
		tmp = model.getCourbe();
		int tmpx = (int)tmp.getX(0);
		double tmpy = (double)tmp.getY(0);
		double txy = tmpx+tmpy;
		assertEquals(3,txy,0.001);
	}

	@Test
	public void logistiqueTest(){
		Courbe<Number,Number> cmm = new Courbe<Number,Number>();
		model.logistique(cmm,7);
		assertEquals(courbeData.getX(2), cmm.getX(0));

		assertEquals(4.475, (double) cmm.getY(0),0.001);
	}

	@Test
	public void transfoLogTest(){
		Courbe<Number,Number> cmm = new Courbe<Number,Number>();
		model.transfoLog(cmm,6);
		assertEquals(courbeData.getX(2), cmm.getX(0));

		assertEquals(4.475, (double) cmm.getY(0),0.001);
	}

	@Test
	public void residuTest(){
		Courbe<Number,Number> cmm = new Courbe<Number,Number>();
		model.residu(cmm,8);
		assertEquals(courbeData.getX(2), cmm.getX(0));

		assertEquals(4.475, (double) cmm.getY(0),0.001);
	}

	@Test
	public void desaisonaliserTest(){
		Courbe<Number,Number> cmm = new Courbe<Number,Number>();
		model.desaisonaliser(cmm,3);
		assertEquals(courbeData.getX(2), cmm.getX(0));

		assertEquals(4.475, (double) cmm.getY(0),0.001);
	}

	@Test
	public void saisonResiduTest(){
		Courbe<Number,Number> cmm = new Courbe<Number,Number>();
		model.desaisonaliser(cmm,3);
		assertEquals(courbeData.getX(2), cmm.getX(0));

		assertEquals(4.475, (double) cmm.getY(0),0.001);
	}








}