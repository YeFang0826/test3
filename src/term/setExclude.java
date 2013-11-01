package term;
import java.util.ArrayList;
import java.util.HashMap;

import object.*;
import statement.defineFunStatement;
import agent.agentTemplate;

import expression.expression;

public class setExclude extends term{
	String set;
	expression exclude;
	
	public setExclude(String set, expression exclude){
		this.type = "setExclude";
		this.set = set;
		this.exclude = exclude;
	}
	public setExclude eval_0(HashMap<String,expression> globalVar){
		setExclude ret = new setExclude(this.set,this.exclude.eval_0(globalVar));
		return ret;
	}
	
	
	public object eval_exe(HashMap<String, object> knownVars, HashMap<String, agentTemplate> agentTemplate, defineFunStatement mechanism, ArrayList<String>existsVar, ArrayList<String> forallVar){
		ArrayList<object> ret = new ArrayList<object>();
		object remove = this.exclude.eval_exe(knownVars, agentTemplate, mechanism, existsVar, forallVar);
		if(remove.type.equals("number") && knownVars.containsKey(this.set) && knownVars.get(this.set).type.equals("list")){
			for(int i=0; i<((list)knownVars.get(this.set)).l.size(); i++)
				ret.add(((list)knownVars.get(this.set)).l.get(i));
			ret.remove((int)((number)remove).n);
		}
		return new list(ret);
	}
}
