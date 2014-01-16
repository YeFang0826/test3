package term;

import java.util.ArrayList;
import java.util.HashMap;

import object.*;
import statement.defineFunStatement;
import agent.agentTemplate;
import expression.*;

public class expectedValue extends term{
	public expression name;
	
	public expectedValue (expression name){
		this.type = "expectedValue";
		this.name = name;
	}
	
	public object eval_exe(HashMap<String, object> knownVars, HashMap<String, agentTemplate> agentTemplate,
			defineFunStatement mechanism, ArrayList<String> existsVar, ArrayList<String> forallVar, HashMap<String,Double> prior_Info, boolean expected){
		
		return name.eval_exe(knownVars, agentTemplate, mechanism, existsVar, forallVar, prior_Info, expected);
		
	}
	
}
