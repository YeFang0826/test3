package functionCall;

import java.util.ArrayList;
import java.util.HashMap;

import term.functionCallT;
import expression.expression;
import object.*;

public class listCall extends functionCall{
	public String method;
	
	public listCall(String method){
		this.method = method;
		this.inputs = new ArrayList<expression>();
		this.type = "listCall";
	}
	
	public listCall eval_0(HashMap<String, expression> globalVar){ // replace global vars
		listCall ret = new listCall(this.method);
		ArrayList<expression> input = new ArrayList<expression>();
		
		for(int i=0; i<this.inputs.size(); i++){
			input.add(this.inputs.get(i).eval_0(globalVar));
		}
		ret.inputs = input;
		return ret;
	}
	

	public int length(object name, HashMap<String, object> knownVars){
		if(name.type.equals("string") && knownVars.get(name).type.equals("list")){
			return ((list)knownVars.get(name)).l.size();
		}
		return -1;
	}

	public list construct(ArrayList<object> elements){
		return new list(elements);
	}
	
	public map InsertSorted(ArrayList<object> in){
		HashMap<object, object> ret = new HashMap<object, object>();
		ArrayList<String> s = new ArrayList<String>();
		if(in.size()>1){
			object toInsert = in.get(0);
			object sorted = in.get(1);
			int index =-1;
			object test;
			object test1;
			if(sorted.type.equals("list")){
				if(in.size()==3 && in.get(2).type.equals("number")){
					index = (int)((number)in.get(2)).n;
					for(int i=0; i<((list)sorted).l.size(); i++){
						test = ((list)sorted).l.get(i);
						if(((list)sorted).l.get(i).type.equals("list") && ((list)((list)sorted).l.get(i)).l.get(index).type.equals("string")){
							s.add(((string)((list)((list)sorted).l.get(i)).l.get(index)).s);
						}
						else if(((list)sorted).l.get(i).type.equals("tuple") && ((tuple)((list)sorted).l.get(i)).t.get(index).type.equals("string")){
							s.add(((string)((tuple)((list)sorted).l.get(i)).t.get(index)).s);
						}
						else if(((list)sorted).l.get(i).type.equals("string") ){
							s.add(((string)((list)sorted).l.get(i)).s);
						}
					}
				}
				String insertS=null;
				if(toInsert.type.equals("string")){
					insertS = ((string)toInsert).s;
				}
				else if(toInsert.type.equals("tuple") && index >=0 && ((tuple)toInsert).t.get(index).type.equals("string")){
					insertS = ((string)((tuple)toInsert).t.get(index)).s;
				}
				else if(toInsert.type.equals("list") && index >=0 && ((list)toInsert).l.get(index).type.equals("string")){
					insertS = ((string)((list)toInsert).l.get(index)).s;
				}
				
				ArrayList<String> condition = conditions(insertS,s);
				ArrayList<object> templ;
				for(int i=0; i<condition.size(); i++){
					templ = new ArrayList<object>();
					for(int j=0; j<((list)sorted).l.size();j++){
						if(i==j)
							templ.add(toInsert);
						templ.add(((list)sorted).l.get(j));
					}
					if(i==(condition.size()-1))
						templ.add(toInsert);
					
					ret.put(new string(condition.get(i)),new list(templ));
				}
			}
			
		}
		return new map(ret);
		
	}
	
	ArrayList<String> conditions(String insert, ArrayList<String> l){
		ArrayList<String> ret = new ArrayList<String>();
		String c = "";
		for(int i=0; i<l.size(); i++){
			c = "";
			for(int j=0; j<l.size();j++){ 
				if(j==0){
					if(i==j)
						c = "("+l.get(j)+ "<=" +insert+ ")";
				}
				else{
					if(i==j && !c.equals(""))
						c = "(" + c + " and ((" + l.get(j) + "<="+ insert +") and ("+ insert+ "<="+ l.get(j-1)+")))";
					else if(i==j && c.equals(""))
						c = "((" + l.get(j) + "<="+ insert +") and ("+ insert+ "<="+ l.get(j-1)+"))";
					else if(i!=j && c.equals(""))
						c = "("+l.get(j)+ "<=" +l.get(j-1)+ ")";
					else if( i!=j && !c.equals(""))
						c =  "(" + c + " and ("+l.get(j)+ "<=" +l.get(j-1)+ "))";
					
				}
			}
			ret.add(c);
		}
		for(int i=0; i<l.size()-1; i++){
			if(i==0)
				c = "("+l.get(i+1)+"<="+l.get(i)+")";
			else
				c = "("+c + " and "+"("+l.get(i+1)+"<="+l.get(i)+"))";
			
		}
		c = "("+c + " and ("+insert+"<="+l.get(l.size()-1)+"))";
		ret.add(c);
		return ret;
	}
	
	
}
