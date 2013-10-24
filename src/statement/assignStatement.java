package statement;

import java.util.HashMap;

import object.agentO;

import expression.*;
import functionCall.*;
import term.tl;

public class assignStatement extends statement{
	public String name;
	public tl lhs;
	public expression assignment;
	
	public assignStatement(String name){
		this.type="assignStatement";
		this.name = name;
		this.lhs= null;
		this.assignment =null;
	}
	public assignStatement(tl lhs){
		this.type="assignStatement";
		this.lhs = lhs;
		this.name= null;
		this.assignment =null;
	}
	
	public void eval_0(HashMap<String, expression> globalVar){
		if(this.lhs!=null){
			this.lhs.eval_0(globalVar);
		}
		if(this.assignment!=null){
			this.assignment = this.assignment.eval_0(globalVar);
		}
	}
	
}
