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
	
	public tupleCall eval_1(String index, int value){ // unrolling forloops

		tupleCall tempc = new tupleCall(this.method);
		ArrayList<expression> input = new ArrayList<expression>();
		
		for(int i=0; i<this.inputs.size(); i++){
			input.add(this.inputs.get(i).eval_1(index, value));
		}
		if(this.method.equals("access-tuple"))
			input.add(this.inputs.get(this.inputs.size()-1));
		else
			input.add(this.inputs.get(this.inputs.size()-1).eval_1(index, value));
		
		tempc.inputs = input;
		return tempc;
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
