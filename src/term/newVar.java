package term;
import java.util.ArrayList;

import expression.expression;
import java.util.HashMap;
import object.*;

public class newVar extends term {
	public expression name;
	public expression lable;
	
	public newVar(expression name, expression lable){
		this.type = "newVar";
		this.name = name;
		this.lable = lable;
	}
	public newVar eval_0(HashMap<String, expression> globalVar){
		newVar newv = new newVar(this.name.eval_0(globalVar), this.lable.eval_0(globalVar));
		return newv;
	}
	
	public string eval_in_agent(HashMap<String, object> knownVar){
		if(this.name.eval_agent_expr(knownVar).type.equals("string") && this.lable.eval_agent_expr(knownVar).type.equals("string")){
			return new string(((string)this.name.eval_agent_expr(knownVar)).s +"_"+((string)this.lable.eval_agent_expr(knownVar)).s);
		}
		else if(this.name.eval_agent_expr(knownVar).type.equals("string") && this.lable.eval_agent_expr(knownVar).type.equals("number")){
			return new string(((string)this.name.eval_agent_expr(knownVar)).s +"_"+(int)((number)this.lable.eval_agent_expr(knownVar)).n);
		}
		else 
			return null;
	}
	
}
