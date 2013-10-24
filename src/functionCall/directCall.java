package functionCall;

import java.util.ArrayList;
import java.util.HashMap;

import agent.agentTemplate;
import statement.defineFunStatement;
import term.functionCallT;
import expression.expression;
import object.*;

public class directCall extends functionCall{
	
	public String name;
	
	public directCall(String name){
		this.name = name;
		this.inputs = new ArrayList<expression>();
		this.type = "direct";
	}
	public expression eval_0(HashMap<String, expression> globalVar){
		directCall tempc = new directCall(this.name);
		ArrayList<expression> input = new ArrayList<expression>();
		
		for(int i=0; i< this.inputs.size(); i++){
			input.add(this.inputs.get(i).eval_0(globalVar));
		}
		tempc.inputs = input;
		return new expression(new functionCallT(tempc));
		
	}
	
	public expression eval_1(String index, int value){
		directCall tempc = new directCall(this.name);
		ArrayList<expression> input = new ArrayList<expression>();
		
		for(int i=0; i< this.inputs.size(); i++){
			input.add(this.inputs.get(i).eval_1(index, value));
		}
		tempc.inputs = input;
		return new expression(new functionCallT(tempc));
		
	}
}
