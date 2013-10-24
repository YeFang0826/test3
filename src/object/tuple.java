package object;

import java.util.ArrayList;

public class tuple extends object{
	public ArrayList<object> t;
	public tuple(ArrayList<object> t){
		this.type = "tuple";
		this.t = t;
	}
	
	public tuple copy(){
		ArrayList<object> ret = new ArrayList<object>();
		for(int i=0; i<this.t.size(); i++){
			if(this.t.get(i).type.equals("tuple"))
				ret.add(((tuple)this.t.get(i)).copy());
			else if(this.t.get(i).type.equals("list"))
				ret.add(((list)this.t.get(i)).copy());
			else
				ret.add(this.t.get(i));
		}
		return new tuple(ret);
	}
	
	
}
