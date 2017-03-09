package mvc.model;

import java.util.ArrayList;

public class Courbe<typeX,typeY> {

	protected ArrayList<typeX> x = new ArrayList<typeX>();
	protected ArrayList<typeY> y = new ArrayList<typeY>();


	private int check(){
		return x.size()-y.size();
	}

	public typeX getX(int i){
		return x.get(i);
	}

	public typeY getY(int i){
		return y.get(i);
	}

	public void addXY(typeX dataX,typeY dataY){
		x.add(dataX);
		y.add(dataY);
	}

	public void removeXY(int i){
		x.remove(i);
		y.remove(i);
	}

	public int indexOfXYbyX(typeX dataX){
		return x.indexOf(dataX);
	}

	public int indexOfXYbyY(typeY dataY){
		return y.indexOf(dataY);
	}

	public int sizeOfData(){
		if(this.check()==0){
			return x.size();
		}

		return -1;
	}


}
