package statement;

import java.util.ArrayList;
import java.util.HashMap;

import expression.*;
import object.*;

public class defVarStatement extends statement{
	public String vType;
	public int dimension;
	public String name;
	public expression init;
	
	
	public defVarStatement(String type, String name){
		this.vType = type;
		this.type="defVarStatement";
		this.name = name;
	}
	
	public list createNestedList(int iteration){
		if(iteration == 1){
			ArrayList<object> add = new ArrayList<object>(30);
			list retl =  new list(add);
			retl.dimension = 1;
			return retl;
		}
		else{
			ArrayList<object> ret = new ArrayList<object>(30);
			ret.add(createNestedList(iteration -1));
			list retl = new list(ret);
			retl.dimension++;
			return retl;
		}
	}
	
	public void eval_0(HashMap<String, expression> globalVar){
		if(this.init!=null){
			this.init = this.init.eval_0(globalVar);
		}
	}
}
