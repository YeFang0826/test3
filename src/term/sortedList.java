package term;
import java.util.ArrayList;
import java.util.HashMap;

import agent.agentTemplate;

import expression.expression;
import object.*;
import statement.defineFunStatement;

public class sortedList extends term{
	public String lname;
	public ArrayList<expression> dimension; // only for list type
	
	public sortedList(String lname){
		this.type = "sortedList";
		this.lname = lname;
		this.dimension = new ArrayList<expression>();
	}
	
	public sortedList eval_0(HashMap<String, expression> globalVar){
		sortedList ret = new sortedList(this.lname);
		ArrayList<expression> temp = new ArrayList<expression>();
		
		for(int i=0; i<this.dimension.size(); i++){
			temp.add(this.dimension.get(i).eval_0(globalVar));
		}
		ret.dimension = temp;
		return ret;
	}
	
	public String eval_exe(HashMap<String, object> knownVars, HashMap<String, agentTemplate> agentTemplate, defineFunStatement mechanism, 
			ArrayList<String> existsVar, ArrayList<String> forallVar, HashMap<String,Double> prior_Info, boolean expected){
		String ret = null;
		ArrayList<object> indexes = new ArrayList<object>();
		
		for(int i=0; i<this.dimension.size();i++){
			indexes.add(this.dimension.get(i).eval_exe(knownVars, agentTemplate, mechanism, existsVar, forallVar, prior_Info, expected));
		}
		if(knownVars.containsKey(this.lname) && knownVars.get(this.lname).type.equals("list")){
			object sorted = knownVars.get(this.lname);
			ArrayList<String> sortedl = new ArrayList<String>();
			object temp;
			if(sorted.type.equals("list")){
				for(int i=0; i<((list)sorted).l.size(); i++){
					temp = ((list)sorted).l.get(i);
					if(temp.type.equals("tuple") && ((tuple)temp).t.get((int)((number)indexes.get(0)).n).type.equals("string")){
						sortedl.add(((string)((tuple)temp).t.get((int)((number)indexes.get(0)).n)).s); // NEEDS TO BE EDIT
					}
				}
			}
			if(sorted.type.equals("list")){
				String sortedCondition = "";
				
				for(int i=0; i< sortedl.size()-1; i++){
					if(i==0)
						sortedCondition = "(" + sortedl.get(i+1) + "<=" +sortedl.get(i) + ")";
					else
						sortedCondition ="("+sortedCondition + " and (" + sortedl.get(i+1) + "<=" +sortedl.get(i) + "))";
				}
				
				ret = sortedCondition;
			}
			/*
			if(sorted.type.equals("list")){
				String sortedCondition = "";
				
				for(int i=sortedl.size()-1; i> 0; i--){
					if(i==(sortedl.size()-1))
						sortedCondition = "(" + sortedl.get(i) + "<=" +sortedl.get(i-1) + ")";
					else
						sortedCondition ="("+sortedCondition + " and (" + sortedl.get(i) + "<=" +sortedl.get(i-1) + "))";
				}
				ret = sortedCondition;
			}
			*/
		}
		//System.out.println("************* sorted List:" + ret);
		return ret;
	}
}
