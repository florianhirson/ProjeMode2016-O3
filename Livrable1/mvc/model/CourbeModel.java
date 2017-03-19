package mvc.model;

import java.util.Observable;
import java.util.Scanner;
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
	Scanner sc = new Scanner(System.in);
	private int ordre=0;

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
	
	private void setOrdre(){
		System.out.print("Ordre : ");
		ordre = sc.nextInt();
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
	 * @author Rayan
	 * @param c
	 */
	public void moyenneMobile(Courbe<Number,Number> c,int a){
		
		if(this.ordre == 0)setOrdre();
		
		double tabX[]=new double[courbeData.sizeOfData()];
		double moyenne = 0;
		
		for(int i=2; i<courbeData.sizeOfData()-2;++i){
			
				tabX[i]=(double)courbeData.getX(i);
				if(ordre%2==0){
					moyenne=((0.5*(double)courbeData.getY(i-2))+(double)courbeData.getY(i-1)+(double)courbeData.getY(i)+(double)courbeData.getY(i+1)+((double)courbeData.getY(i+2)*0.5));
					moyenne = moyenne/ordre;
					
				}
				else{
					moyenne=((double)courbeData.getY(i-1)+(double)courbeData.getY(i)+(double)courbeData.getY(i+1));
					moyenne = moyenne/ordre;
				}
				if(a==1)System.out.println("Mht: "+Double.valueOf(moyenne));
				c.addXY(tabX[i],moyenne);
			
		}
		
	}
	
	/**
	 * Xt-Mht soit St+Residu
	 * @author florian barbet
	 * @param c
	 */
	public void saisonResidu(Courbe<Number,Number> c, int a){
		Courbe<Number,Number> cmm = new Courbe<Number,Number>();
		this.moyenneMobile(cmm,0);
		double moyennet = 0;
		double xt = 0;
		for(int i = 2; i < courbeData.sizeOfData()-2;i++){
			
			moyennet = (double) cmm.getY(i-2);
			xt = (double)courbeData.getY(i);
			c.addXY((double)courbeData.getX(i),xt-moyennet);
			if(a==1)System.out.println("Xt-MhT : "+Double.valueOf(xt-moyennet));
		}
	}
	
	/**
	 * St calcul de la saisonalitee
	 * @author florian barbet
	 * @param c
	 */
	public void saison(Courbe<Number,Number> c, int a){
		Courbe<Number, Number> cmd = new Courbe<Number,Number>();
		this.saisonResidu(cmd,0);
		double s1 = 0;
		double s2=0;
		double s3=0;
		double s4=0;
		int tourS1 =0;
		int tourS2 =0;
		int tourS3 =0;
		int tourS4 =0;
		double surplus=0.0;
		for(int i = 0;i<cmd.sizeOfData();i++){
			if((double)cmd.getX(i)%4==0){
				s4+=(double)cmd.getY(i);
				tourS4++;
			}else if((double)cmd.getX(i)%2==0){
				s2+=(double)cmd.getY(i);
				tourS2++;
			}else if((double)cmd.getX(i-1)%2==0){
				s3+=(double)cmd.getY(i);
				tourS3++;
			}else{
				s1+=(double)cmd.getY(i);
				tourS1++;
				
			}
			
		}
		s1=s1/tourS1;
		s2=s2/tourS2;
		s3=s3/tourS3;
		s4=s4/tourS4;
	
		
		if(s1+s2+s3+s4!=0){
			surplus = s1+s2+s3+s4;
			s1-=surplus/4;
			s2-=surplus/4;
			s3-=surplus/4;
			s4-=surplus/4;
		}
		if(a==1)System.out.println("St : s1: "+s1+" s2: "+s2+" s3: "+s3+" s4: "+s4+"\n Surplus :"+surplus);
		for(int i=0;i<courbeData.sizeOfData();i++){
			if((double)courbeData.getX(i)%4==0){
				c.addXY((double)courbeData.getX(i), s4);
			}else if((double)courbeData.getX(i-1)%2==0){
				c.addXY((double)courbeData.getX(i), s3);
			}else if((double)courbeData.getX(i)%2==0){
				c.addXY((double)courbeData.getX(i), s2);
			}else{
				c.addXY((double)courbeData.getX(i), s1);
			}
		}
		
		
	}
	
	/**
	 * Moyenne desaisonnalise
	 * @author florian barbet
	 * @param c
	 */
	public void desaisonaliser(Courbe<Number,Number> c,int a){
		Courbe<Number,Number> st = new Courbe<Number,Number>();
		this.saison(st,0);
		double des = 0;
		for(int i = 0; i < courbeData.sizeOfData();i++){
			
			des =(double)courbeData.getY(i);
			des-=(double)st.getY(i);
			if(a==1)System.out.println("Xt-St : "+des);
			c.addXY((double)courbeData.getX(i), des);
		}
	}
	
	
	/**
	 * @author florian barbet
	 * @param c
	 */
	public void logistique(Courbe<X,Number> c, int a){
		int taille = courbeData.sizeOfData();

		X dataX;
		double dataY;
		double tmpY = 0;
		X tmpX;
		double tmpForm = 0;
		for(int i=0; i<taille; i++){

			tmpY = (double)courbeData.getY(i);
			tmpX = courbeData.getX(i);
			tmpForm = tmpY/(1-tmpY);
			if((tmpY > 0&&  tmpY<1)){
				dataX = tmpX;
				dataY = Math.log(tmpForm);
				c.addXY(dataX,dataY);
				if(a==1)System.out.println("Yt2 : "+dataY);
			}
			


		}
	}
	
	
	
	/**
	 * TransfoLog transformation sur la courbe avec la fonction log
	 * @author Thomas
	 * @param c
	 */
	public void transfoLog(Courbe<X,Number> c, int a){
		int taille = courbeData.sizeOfData();

		X dataX;
		double dataY;

		for(int i=0; i<taille; i++){


			if((double)courbeData.getY(i) < 0 ||(double)courbeData.getX(i)==0){

			}
			else{
				dataX = courbeData.getX(i);
				dataY = Math.log((double)courbeData.getY(i));
				c.addXY(dataX,dataY);
				if(a==1)System.out.println("Yt1 : "+dataY);

			} 


		}


	}
	
}


