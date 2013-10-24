package term;

import java.util.ArrayList;
import java.util.HashMap;

import object.*;
import statement.defineFunStatement;
import agent.agentTemplate;

import expression.expression;

public class agentT extends term{
	public String atype;
	public expression index;
	public expression lable;
	
	public agentT(String atype){
		this.type = "agent";
		this.atype = atype;
	}
	
	public agentT eval_0(HashMap<String, expression> globalVar){
		expression newindex = this.index.eval_0(globalVar);
		expression newlable = this.lable.eval_0(globalVar);
		agentT ret = new agentT(this.atype);
		ret.index = newindex;
		ret.lable = newlable;
		return ret;
	}
	public expression eval_1(String index, int value){
		expression newindex = this.index.eval_1(index, value);
		expression newlable = this.lable.eval_1(index, value);
		agentT newa = new agentT(this.atype);
		newa.index = newindex;
		newa.lable = newlable;
		return new expression(newa);
	}
	public agentO exe(HashMap<String, agentTemplate> agentTemplate, HashMap<String, object> knownVars, defineFunStatement mechanism, ArrayList<String> existsVar, ArrayList<String> forallVar){
	
		agentTemplate template = agentTemplate.get(this.atype);
		object index = this.index.eval_exe(knownVars, agentTemplate, mechanism, existsVar, forallVar);
		object lable = this.lable.eval_exe(knownVars, agentTemplate, mechanism, existsVar, forallVar);
		agentO a = new agentO(this.atype);
		
		for (int i =0; i< template.vars.size(); i++){
			String newVar = "";
			if(template.vars.values().toArray()[i]!=null){
				if(((object)template.vars.values().toArray()[i]).type.equals("tuple")){
					ArrayList<object> newtuple = new ArrayList<object>();
					for(int j=0; j<((tuple)template.vars.values().toArray()[i]).t.size(); j++){
						if(((tuple)template.vars.values().toArray()[i]).t.get(j).type.equals("string")){
							newVar = ((string)((tuple)template.vars.values().toArray()[i]).t.get(j)).s  +"_"+ ((string)lable).s +"_"+ Integer.toString((int)((number)index).n);
							newtuple.add(new string(newVar));
							if(!forallVar.contains(newVar))
								forallVar.add(newVar);
						}
					}
					a.vars.put((String) template.vars.keySet().toArray()[i], new tuple(newtuple));
				}
				else if(((object)template.vars.values().toArray()[i]).type.equals("list")){
					//not support more than one dimension at this time
					ArrayList<object> newlist = new ArrayList<object>();
					for(int j=0; j<((list)template.vars.values().toArray()[i]).l.size(); j++){
						if(((list)template.vars.values().toArray()[i]).l.get(j).type.equals("string")){
							newVar = ((string)((list)template.vars.values().toArray()[i]).l.get(j)).s  +"_"+ ((string)lable).s +"_"+ Integer.toString((int)((number)index).n);
							newlist.add(new string(newVar));
							if(!forallVar.contains(newVar))
								forallVar.add(newVar);
						}
					}
					list addl = new list(newlist);
					addl.dimension = 1;
					a.vars.put((String) template.vars.keySet().toArray()[i], new list(newlist));
				}
			}
			else{
				a.vars.put((String) template.vars.keySet().toArray()[i],
						new string((String) template.vars.keySet().toArray()[i] +"_"+ ((string)lable).s +"_"+ Integer.toString((int)((number)index).n)) );
				forallVar.add((String) template.vars.keySet().toArray()[i] +"_"+ ((string)lable).s +"_"+ Integer.toString((int)((number)index).n));
			}
			
		}
		a.vars.put("self", new string(((string)lable).s +"_"+ Integer.toString((int)((number)index).n)));
		for(int i=0; i< template.funs.size(); i++){
			a.funs.put((String) template.funs.keySet().toArray()[i], new functionO((defineFunStatement)template.funs.values().toArray()[i]));
		}
		return a;
	}
}
