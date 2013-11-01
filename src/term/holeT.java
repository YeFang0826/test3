package term;
import java.util.ArrayList;
import java.util.HashMap;

import object.*;
import statement.defineFunStatement;
import agent.agentTemplate;

import expression.expression;

public class holeT extends term{
	public String name;  // unknown real
	public ArrayList<expression> lable;
	
	public holeT(String name){
		this.name = name;
		this.type = "hole";
		this.lable = new ArrayList<expression>();
	}
	
	public holeT eval_0(HashMap<String, expression> globalVar){
		ArrayList<expression> newlable = new ArrayList<expression>();
		for(int i=0; i<this.lable.size(); i++){
			newlable.add(this.lable.get(i).eval_0(globalVar));
		}
		holeT ret = new holeT(this.name);
		ret.lable = newlable;
		return ret;
	}

	public object eval_exe(HashMap<String, object> knownVars, HashMap<String, agentTemplate> agentTemplate,
			defineFunStatement mechanism, ArrayList<String> existsVar, ArrayList<String> forallVar){
		String ret = this.name; 
		object temp = null;
		for(int i=0; i<this.lable.size(); i++){
			temp = this.lable.get(i).eval_exe(knownVars, agentTemplate, mechanism, existsVar, forallVar);
			if(temp.type.equals("string"))
				ret = ret + "_"+ ((string)temp).s;
			else if(temp.type.equals("number"))
				ret = ret + "_"+ (int)((number)temp).n;
		}
		//System.out.println("evaluating hole: " + ret);
		if(!existsVar.contains(ret))
			existsVar.add(ret);
		return new string(ret);
	}
	
}
