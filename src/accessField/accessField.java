package accessField;

import java.util.ArrayList;
import java.util.HashMap;

import agent.agentTemplate;

import statement.defineFunStatement;
import term.accessT;
import term.tl;
import expression.expression;
import functionCall.listCall;
import object.*;

public class accessField {
	public String object;
	public String field;
	public tl objectl;
	
	public accessField(String o, String f){
		this.object = o;
		this.objectl =null;
		this.field = f;
	}
	public accessField(tl ol, String f){
		this.objectl = ol;
		this.object = null;
		this.field = f;
	}
	
	public accessField eval_0(HashMap<String, expression> globalVar){
		if(this.objectl!=null){
			tl tempc = new tl(this.objectl.tlname);
			ArrayList<expression> input = new ArrayList<expression>();
			
			for(int i=0; i< this.objectl.indexes.size(); i++){
				input.add(this.objectl.indexes.get(i).eval_0(globalVar));
			}
			tempc.indexes = input;
			return new accessField(tempc, this.field);
		}
		
		else
			return this;
	}
	
	public accessField eval_1(String index, int value){
		if(this.objectl!=null){
			tl tempc = new tl(this.objectl.tlname);
			ArrayList<expression> input = new ArrayList<expression>();
			
			for(int i=0; i< this.objectl.indexes.size(); i++){
				input.add(this.objectl.indexes.get(i).eval_1(index, value));
			}
			tempc.indexes = input;
			return new accessField(tempc, this.field);
		}
		else
			return this;
	}

	public object exe(HashMap<String, object> knownVars, HashMap<String, agentTemplate> agentTemplate, defineFunStatement mechanism, ArrayList<String> exsitsVar, ArrayList<String> forallVar){
		if(this.object!=null){
			if(knownVars.containsKey(this.object) && knownVars.get(this.object).type.equals("agent")){
				agentO a= (agentO)knownVars.get(this.object);
				if(a.vars.containsKey(this.field))
					return a.vars.get(this.field);
				else if(a.funs.containsKey(this.field)){
					return a.funs.get(this.field);
				}
				else{
					System.out.println("no other field can be accessed in agent");
				}
			}
		}
		else if(this.objectl!= null){
			object a = this.objectl.access(knownVars, agentTemplate, mechanism, exsitsVar, forallVar);
			if(a.type.equals("agent")){
				if(((agentO)a).vars.containsKey(this.field))
					return ((agentO)a).vars.get(this.field);
				else if(((agentO)a).funs.containsKey(this.field)){
					return ((agentO)a).funs.get(this.field);
				}
				else{
					System.out.println("no other field can be accessed in agent");
				}
			}
		}
		return null;
	}

}
