package program;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import object.agentO;

import agent.agentTemplate;


import logicExpression.*;

import execute.executeCode;
import expression.expression;

import parameter.parameter;
import statement.*;
import term.*;
import functionCall.*;

public class CopyOfprogram {
	
	public HashMap<String, expression> globalvar;
	public defineFunStatement mechanism; 
	public HashMap<String, agentTemplate> agentTemplate; 
	public logicExpression pre = null;
	public logicExpression post = null;
	public executeCode code;
	
	public ArrayList<String> existsVar;
	public ArrayList<String> forallVar;
	public HashMap<String,agentO> agents; 
	
	public CopyOfprogram(){
		this.globalvar = new HashMap<String, expression>();
		this.agentTemplate = new HashMap<String, agentTemplate>();

		this.agents = new HashMap<String,agentO>();
		this.existsVar = new ArrayList<String>();
		this.forallVar = new ArrayList<String>();
	}
	
	public void inputVar(ArrayList<parameter> in){
		for(int i=0; i<in.size(); i++){
			this.globalvar.put(in.get(i).name, new expression(new var(in.get(i).name)));  // If the program has input
		}
	}
	
	public void addD(defVarStatement d){
		if(((defVarStatement)d).init != null){
			this.globalvar.put(((defVarStatement)d).name,((defVarStatement)d).init); //should var has to have assignment here?
		}
		else
			this.globalvar.put(((defVarStatement)d).name, null);
	}

	//--------------------------------------------AFTER PARSING----------------------------------------------------------//
	
	public String playGame(){
		// Replace global constants
		this.mechanism.eval_0(this.globalvar);
		this.code.eval_0(this.globalvar);
		if(this.post!=null)
			this.post = this.post.eval_0(this.globalvar);
		
		
		// unrolling forStatement / straighten ifStatement 
		
		this.mechanism.basicPath(this.globalvar);
		for(int i=0; i<this.agentTemplate.size(); i++){
			((agentTemplate)this.agentTemplate.values().toArray()[i]).eval(this.globalvar, existsVar, forallVar);
		}
		this.code.basicPath(this.globalvar); // basic paths for functions
		
		// execution 
		this.code.exe(this.globalvar, this.agentTemplate, this.mechanism,this.existsVar, this.forallVar); // knownVars, agent info
		
	
		/// varification / synthesis
		
		if(this.post!=null)
			this.post.unroll(this.globalvar); // f for logic formulas
		
		String formula = this.post.post_eval_exe(agentTemplate, mechanism, this.code, this.existsVar, this.forallVar);
		String quantifier = "";
		for(int i=0; i<this.existsVar.size(); i++){
			if(i==0)
				quantifier = quantifier + "Exists{ " + this.existsVar.get(i);
			else
				quantifier = quantifier + " "+ this.existsVar.get(i);
			if(i== this.existsVar.size()-1)
				quantifier = quantifier + " }, ";
		}
		for(int i=0; i<this.forallVar.size(); i++){
			if(i==0)
				quantifier = quantifier + "ForAll{ " + this.forallVar.get(i);
			else
				quantifier = quantifier + " "+ this.forallVar.get(i);
			if(i== this.forallVar.size()-1)
				quantifier = quantifier + " }, ";
		}
		formula = quantifier + formula;
		return formula;
	}
	
}
/*
public void addf(defineFunStatement f){
	((defineFunStatement)f).isPrivate = "global";
	//this.funs.put(f.name, f);
}
*/

