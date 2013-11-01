package functionCall;

import java.util.ArrayList;
import java.util.HashMap;

import term.functionCallT;

import expression.expression;
import accessField.accessField;

public class indirectCall extends functionCall{
    public accessField af;
    
    public indirectCall(accessField af){
    	this.af = af;
    	this.inputs = new ArrayList<expression>();
    	this.type = "indirect";
    }
    public expression eval_0(HashMap<String, expression> globalVar){
    	indirectCall tempc = new indirectCall(this.af.eval_0(globalVar));
    	ArrayList<expression> input = new ArrayList<expression>();
		
		for(int i=0; i< this.inputs.size(); i++){
			input.add(this.inputs.get(i).eval_0(globalVar));
		}
		tempc.inputs = input;
		return new expression(new functionCallT(tempc));
    }
   
    
}
