package object;

import java.util.ArrayList;
import java.util.HashMap;

import term.tl;

public class list extends object{
	public ArrayList<object> l;
	public int dimension;
	
	public list(ArrayList<object> l){
		this.type = "list";
		this.l = l;
	}
	
	public list copy(){ // copy this list
		if(this.dimension == 1){
			ArrayList<object> temp = new ArrayList<object>(this.l.size());
			for(int i=0; i< this.l.size(); i++)
				temp.add(this.l.get(i));
			list ret = new list(temp);
			ret.dimension = this.dimension;
			return ret;
		}
		else{
			ArrayList<object> temp = new ArrayList<object>(this.l.size());
			for(int i=0; i<((list)this).l.size(); i++){
				temp.add(((list)((list)this).l.get(i)).copy());
			}
			list ret = new list(temp);
			ret.dimension = this.dimension;
			return ret;
		}
	}
	
}
