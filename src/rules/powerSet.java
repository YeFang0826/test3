package rules;
import java.io.IOException;
import java.util.ArrayList;

import com.microsoft.z3.Z3Exception;


public class powerSet {
	public static void main(String args[]){
		 int n = 5;
		 ArrayList<ArrayList<Integer>> s = new ArrayList<ArrayList<Integer>>(2^5);
		 s = superSet(n, s);
		 for(int i=0; i< s.size(); i++){
			 s.get(i).toArray();
		 }
	}
	
	 public static ArrayList<ArrayList<Integer>> superSet(int n, ArrayList<ArrayList<Integer>> s){ 
	    	int size = 0;
	    	if(n== 0){
	    		ArrayList<Integer> base = new ArrayList<Integer>(1);
	    		base.add(0);
	    		s.add(base);
	    		return s;
	    	}
	    	
	    	else{
	    		ArrayList<ArrayList<Integer>> tempS = superSet(n-1, s);
	    		size = tempS.size();
	    		
	    		for(int i=0; i< size; i++){
	    			ArrayList<Integer> temp = new ArrayList<Integer>(5);
	    			for(int j=0; j< tempS.get(i).size(); j++){
	    				temp.add(tempS.get(i).get(j));
	    			}
	    			temp.add(n);
	    			s.add(temp);
	    		}
	    	}
	    	return s;
	    }
	   
}
