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
	 
	 // methode moyennemobile par rayan 
	 
	/**
	 * Moyenne desaisonnalisé
	 * @author florian barbet
	 * @param c
	 */
	public void MoyenneSansSaison(Courbe<Number,Number> c){
		Courbe<Number,Number> cmm = new Courbe<Number,Number>();
		this.MoyenneMobile(cmm);
		double moyennet = 0;
		double xt = 0;
		for(int i = 2; i < courbeData.sizeOfData()-2;i++){
			
			moyennet = (double) cmm.getY(i-2);
			xt = (double)courbeData.getY(i);
			c.addXY((double)courbeData.getX(i),xt-moyennet);
			
		}
	}


    /**
	 * TransfoLog transformation sur la courbe avec la fonction log
	 * @author Thomas
	 * @param c
	 */
	public void transfoLog4Num(Courbe<Number,Number> c){
		int taille = courbeData.sizeOfData();

		double dataX;
		double dataY;

		for(int i=0; i<taille; i++){


			if((double)courbeData.getY(i) < 0 ||(double)courbeData.getX(i)==0.0){

			}
			else{
				dataX = (double)courbeData.getX(i);
				dataY = Math.log((double)courbeData.getY(i));
				c.addXY(dataX,dataY);
			} 


		}


	}


	/**
	 * @see CourbeModel#transfoLog4Num(Courbe)
	 * @param c
	 */
	public void transfoLog4Cat(Courbe<String,String> c){
		int taille = c.sizeOfData();

		double dataX;
		double dataY;

		for(int i=0; i<taille; i++){
			if((double)courbeData.getY(i) < 0 ||(double)courbeData.getX(i)==0.0){

			}else{
				dataX=Math.log(Double.parseDouble(c.getX(i)));
				dataY=Math.log(Double.parseDouble(c.getY(i)));

				c.addXY(dataX+"",dataY+"");
			}
		}
	}

	/**
	 * @see CourbeModel#transfoLog4Num(Courbe)
	 * @param c
	 */
	public void transfoLog4CatNum(Courbe<Number,String> c){
		int taille = c.sizeOfData();

		double dataX;
		double dataY;

		for(int i=0; i<taille; i++){
			if((double)courbeData.getY(i) < 0 ||(double)courbeData.getX(i)==0.0){

			}else{
				dataX=Math.log((double)c.getX(i));
				dataY=Math.log(Double.parseDouble(c.getY(i)));

				c.addXY(dataX,dataY+"");
			}
		}
	}

/*	/**
	 * @see CourbeModel#transfoLog4Num(Courbe)
	 * @param c
	 *//*
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
	}*/
}
