package mvc.model;

import java.util.Observable;
/**
 * Model du MVC sur la Courbe
 * @author Florian Barbet
 * @author Thomas Mastalerz
 *
 * @param <X>
 * @param <Y>
 */
public class CourbeModel<X,Y> extends Observable {

	/**
	 * Courbe<X,Y> courbe sur laquelle toute transformation passera
	 */
	private Courbe<X,Y> courbeData = new Courbe<X,Y>();

	/**
	 * Renvoie la courbe accession
	 * @return courbeData
	 */
	public Courbe<X,Y> getCourbe(){
		return courbeData;
	}


	/**
	 * Permet de modifier la courbe et initialiser
	 * @param c
	 */
	public void setCourbe(Courbe<X,Y> c){
		courbeData = c;
		setChanged();
		notifyObservers();
	}


	public int sizeOfCourbe(){
		return courbeData.sizeOfData();
	}

	public X getDataX(int i){
		return courbeData.getX(i);
	}

	public Y getDataY(int i){
		return courbeData.getY(i);
	}
	/**
	 * 
	 * TODO Methode de transformation de la courbe
	 * 
	 **/

	/**
	 * TransfoLog transformation sur la courbe avec la fonction log
	 * @author Thomas
	 * @param c
	 */
	public void transfoLog4Double(Courbe<Double,Double> c){
		int taille = c.sizeOfData();

		double dataX;
		double dataY;

		for(int i=0; i<taille; i++){
			dataX = Math.log((double)c.getX(i));
			dataY = Math.log((double)c.getY(i));

			c.addXY(dataX,dataY);
		}


	}
	
	/**
	 * TransfoBoxCox transformation sur la courbe avec la fonction box cox
	 * @author Florian Hirson
	 * @param c
	 * @param lambda
	 */
	public void transfoBoxCox(Courbe<Double,Double> c, double lambda) {
		int taille = c.sizeOfData();

		double dataX;
		double dataY;
		
		if (lambda == 0.0) {
			transfoLog4Double(c);
		}

		if( lambda > 0.0) {
			for(int i=0; i<taille; i++){
				dataX = (Math.pow((double)c.getX(i), lambda)-1)/lambda;
				dataY = (Math.pow((double)c.getY(i), lambda)-1)/lambda;

				c.addXY(dataX,dataY);
			}
		}
	}


	/**
	 * @see CourbeModel#transfoLog4Double(Courbe)
	 * @param c
	 */
	public void transfoLog4String(Courbe<String,String> c){
		int taille = c.sizeOfData();

		double dataX;
		double dataY;

		for(int i=0; i<taille; i++){
			dataX=Math.log(Double.parseDouble(c.getX(i)));
			dataY=Math.log(Double.parseDouble(c.getY(i)));

			c.addXY(dataX+"",dataY+"");
		}
	}
	
	
	/**
	 * @see CourbeModel#transfoLog4Double(Courbe)
	 * @param c
	 */
	public void transfoLog4Mois(Courbe<Double,Mois> c){
		int taille = c.sizeOfData();
		Mois y=null;
		double dataX=0.0;
		int dataY=0;

		int test=0;
		for(int i=0; i<taille; i++){
			y = c.getY(i);
			dataX=Math.log(c.getX(i));
			dataY=(int) Math.log(c.getY(i).getData());
			test = Integer.valueOf(dataY);
			while(test>0){
				switch(test){
				case 1:y=Mois.JAN;test=0;break;
				case 2:y=Mois.FEV;test=0;break;
				case 3:y=Mois.MAR;test=0;break;
				case 4:y=Mois.AVR;test=0;break;
				case 5:y=Mois.MAI;test=0;break;
				case 6:y=Mois.JUIN;test=0;break;
				case 7:y=Mois.JUIL;test=0;break;
				case 8:y=Mois.AOUT;test=0;break;
				case 9:y=Mois.SEPT;test=0;break;
				case 10:y=Mois.OCT;test=0;break;
				case 11:y=Mois.NOV;test=0;break;
				case 12:y=Mois.DEC;test=0;break;
				default:test-=12;break;
				}			
			}
		}

		c.addXY(dataX,y);
	}

}
