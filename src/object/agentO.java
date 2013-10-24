package object;

import java.util.HashMap;

import agent.agentTemplate;


import statement.defineFunStatement;
import term.agentT;

import expression.expression;

public class agentO extends object{
	public String a;
	public expression index;
	public expression lable;
	public String name;
	public HashMap<String, object> vars; //local variables
	public HashMap<String, object> funs; //local functions
	
	public agentO(String a){
		this.a = a;
		this.type = "agent";
		this.vars = new HashMap<String, object>();
		this.funs = new HashMap<String, object>();
	}
	//------------------------ AFTER PARSING---------------------------------------------------//
	
}
