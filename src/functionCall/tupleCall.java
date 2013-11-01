package functionCall;

import java.util.ArrayList;
import java.util.HashMap;

import agent.agentTemplate;

import term.functionCallT;
import expression.expression;
import object.*;

public class tupleCall extends functionCall{
	public String method;
	
	public tupleCall(String method){
		this.method = method;
		this.inputs = new ArrayList<expression>();
		this.type = "tupleCall";
	}
	public tupleCall eval_0(HashMap<String, expression> globalVar){ // replace global vars
		tupleCall ret = new tupleCall(this.method);
		ArrayList<expression> input = new ArrayList<expression>();
		
		for(int i=0; i<this.inputs.size(); i++){
			input.add(this.inputs.get(i).eval_0(globalVar));
		}
		
		ret.inputs = input;
		return ret;
	}
	
	public int size(object name, HashMap<String, object> knownVars){
		if(name.type.equals("string") && knownVars.get(name).type.equals("tuple")){
			return ((tuple)knownVars.get(name)).t.size();
		}
		return -1;
	}
	
	public tuple construct(ArrayList<object> t){
		return new tuple(t);
	}
}
