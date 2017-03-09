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
	 * @see CourbeModel#transfoLog4Double(Courbe)
	 * @param c
	 */
	public void transfoLog4Integer(Courbe<Integer,Integer> c){
		int taille = c.sizeOfData();

		int dataX;
		int dataY;

		for(int i=0; i<taille; i++){
			dataX = (int) Math.log((double)c.getX(i));
			dataY = (int) Math.log((double)c.getY(i));

			c.addXY(dataX,dataY);
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

}
