package term;
import java.util.ArrayList;
import java.util.HashMap;

import agent.agentTemplate;

import object.*;
import statement.defineFunStatement;

import expression.*;

public class tl extends term{
	public String tlname;
	public ArrayList<expression> indexes;
	
	public tl(String tlname){
		this.type = "tlaccess";
		this.tlname = tlname;
		this.indexes = null;
	}
	
	public tl eval_0(HashMap<String, expression> globalVar){
		tl ret = new tl(this.tlname);
		ArrayList<expression> e = new ArrayList<expression>();
		for(int i=0; i<this.indexes.size(); i++){
			e.add(this.indexes.get(i).eval_0(globalVar));
		}
		ret.indexes = e;
		return ret;
	}
	
	public object access_in_agent(HashMap<String, object> knownVar){
		object ret = null;
		object lhs = null;
		if(knownVar.containsKey(this.tlname)){
			if(knownVar.get(this.tlname).type.equals("tuple")){
				object index = (this.indexes.get(0)).eval_agent_expr(knownVar);
				if(index.type.equals("number"))
					return ((tuple)(knownVar.get(this.tlname))).t.get((int)((number)index).n);
			}
			else if(knownVar.get(this.tlname).type.equals("list")){
				object index;
				lhs = ((list)knownVar.get(this.tlname));
				for(int k=0; k< this.indexes.size(); k++){
					index = (this.indexes.get(k)).eval_agent_expr(knownVar);
					if(lhs.type.equals("tuple")){
						lhs = ((tuple)lhs).t.get((int)((number)index).n);
					}
					else if(lhs.type.equals("list")){
						lhs = ((list)lhs).l.get((int)((number)index).n);
					}
					else{
						System.out.print("error in list/tuple access");
					}
				}
			}
		}
		else{
			System.out.println("bad access!");
		}
		return ret = lhs;
	}
	
	public object access(HashMap<String, object> knownVars, HashMap<String, agentTemplate> agentTemplate, 
			defineFunStatement mechanism, ArrayList<String> exsitsVar, ArrayList<String> forallVar, HashMap<String,Double> prior_Info,boolean expected){
		object ret = null;
		object lhs = null;
		if(knownVars.containsKey(this.tlname)){
			if(knownVars.get(this.tlname).type.equals("tuple")){
				object index = (this.indexes.get(0)).eval_exe(knownVars, agentTemplate, mechanism, exsitsVar, forallVar, prior_Info, expected);
				if(index.type.equals("number"))
					ret  = ((tuple)(knownVars.get(this.tlname))).t.get((int)((number)index).n);
				
				if(expected && ret.type.equals("string") && prior_Info.containsKey(((string)ret).s))
					return new number(prior_Info.get(((string)ret).s));
				else
					return ret;
			}
			else if(knownVars.get(this.tlname).type.equals("list")){
				object index;
				lhs = ((list)knownVars.get(this.tlname));
				for(int k=0; k< this.indexes.size(); k++){
					index = (this.indexes.get(k)).eval_exe(knownVars, agentTemplate, mechanism, exsitsVar, forallVar, prior_Info, expected);
					if(lhs.type.equals("tuple")){
						lhs = ((tuple)lhs).t.get((int)((number)index).n);
						if(expected && lhs.equals("string") && prior_Info.containsKey(((string)lhs).s))
							return new number(prior_Info.get(((string)lhs).s));
					}
					else if(lhs.type.equals("list")){
						lhs = ((list)lhs).l.get((int)((number)index).n);
						if(expected && lhs.equals("string") && prior_Info.containsKey(((string)lhs).s))
							return new number(prior_Info.get(((string)lhs).s));
					}
					else{
						System.out.print("error in list/tuple access");
					}
				}
				return lhs;
			}
		}
		return null;
	}
	
}
