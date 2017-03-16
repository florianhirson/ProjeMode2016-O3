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
	public void SaisonResidu(Courbe<Number,Number> c){
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
	 * @author florian barbet
	 * @param c
	 */
	public void Saison(Courbe<Number,Number> c){
		Courbe<Number, Number> cmd = new Courbe<Number,Number>();
		this.SaisonResidu(cmd);
		double s1 = 0;
		double s2=0;
		double s3=0;
		double s4=0;
		int tourS1 =0;
		int tourS2 =0;
		int tourS3 =0;
		int tourS4 =0;
		for(int i = 0;i<cmd.sizeOfData();i++){
			if((double)cmd.getX(i)%4==0){
				s4+=(double)cmd.getY(i);
				tourS4++;
			}else if((double)cmd.getX(i)%2==0){
				s2+=(double)cmd.getY(i);
				tourS2++;
			}else if((double)cmd.getX(i+1)%4==0){
				s3+=(double)cmd.getY(i);
				tourS3++;
			}else{
				s1+=(double)cmd.getY(i);
				tourS1++;
				System.out.println("fr : s1: "+s1+" ajout : "+(double)cmd.getY(i));
			}
			
		}
		s1=s1/tourS1;
		s2=s2/tourS2;
		s3=s3/tourS3;
		s4=s4/tourS4;
		System.out.println(">>s1: "+s1+" s2: "+s2+" s3: "+s3+" s4: "+s4);
		
		if(s1+s2+s3+s4!=0){
			double surplus = s1+s2+s3+s4;
			s1-=surplus/4;
			s2-=surplus/4;
			s3-=surplus/4;
			s4-=surplus/4;
		}
		System.out.println("s1: "+s1+" s2: "+s2+" s3: "+s3+" s4: "+s4);
		for(int i=0;i<courbeData.sizeOfData();i++){
			if((double)courbeData.getX(i)%4==0){
				c.addXY((double)courbeData.getX(i), s4);
			}else if((double)courbeData.getX(i)%3==0){
				c.addXY((double)courbeData.getX(i), s3);
			}else if((double)courbeData.getX(i)%2==0){
				c.addXY((double)courbeData.getX(i), s2);
			}else{
				c.addXY((double)courbeData.getX(i), s1);
			}
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
