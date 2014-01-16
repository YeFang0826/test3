package statement;

import java.util.ArrayList;
import java.util.HashMap;

import expression.expression;

import term.inputPrior;

public class priorInfo extends statement{
	public ArrayList<inputPrior> prior;
	public priorInfo(){
		this.type = "priorInfo";
		this.prior = new ArrayList<inputPrior>();
	}
	public void eval_0(HashMap<String, expression> globalVar){
		for(int i=0; i<this.prior.size(); i++){
			this.prior.get(i).name.eval_0(globalVar);
		}
	}
}
