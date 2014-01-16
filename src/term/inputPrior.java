package term;

import java.util.ArrayList;

import expression.*;

public class inputPrior extends term{
	public expression name;
	public ArrayList<Double> intervals;
	
	//Pr(lower< x < higher)
	public inputPrior(expression name){
		this.name = name;
		this.type = "inputPrior";
		this.intervals = new ArrayList<Double>();
	}
	
	public double calculate_expect(){
		
		if(this.intervals.size()%3 == 0){
			double ret = 0;
		
			for(int i=0; i<this.intervals.size(); i++){
				ret = ret + ((this.intervals.get(i)+this.intervals.get(i+1))/2) * this.intervals.get(i+2);
			}
		
			return ret;
		}
		else{
			System.out.print("Error in prior info");
			return 0;
		}
	}
	
}
