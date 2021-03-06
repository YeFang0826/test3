package statement;

import java.util.ArrayList;
import java.util.HashMap;

import object.agentO;

import term.*;
import expression.*;
import functionCall.listCall;

public class forStatement extends statement{
	public expression lbound;
	public expression ubound;
	public String iterator;
	public ArrayList<statement> body;
	
	public forStatement(){
		this.type = "forStatement";
		this.body = new ArrayList<statement>();
	}
	
	public void addS(statement s){
		this.body.add(s);
	}
	
	public void eval_0(HashMap<String, expression> globalVar){
		this.lbound = this.lbound.eval_0(globalVar);
		this.ubound = this.ubound.eval_0(globalVar);
		for(int i=0;i<this.body.size();i++){
			this.body.get(i).eval_0(globalVar);
		}
	}
	
	public ArrayList<statement> unroll(HashMap<String,expression> knownVar){
		
		ArrayList<statement> ret = null;
		expression l = this.lbound.eval_0(knownVar);
		expression u = this.ubound.eval_0(knownVar);
		
		if((this.lbound.leaf == null || (l.leaf!=null && !l.leaf.type.equals("num")))
				||
				(this.ubound.leaf== null || (u.leaf!=null && !u.leaf.type.equals("num")))){
					
					System.out.println("error! Unable to unroll forloop");
					return null;
		}
		else{
			statement s;
			ret = new ArrayList<statement>();
			
			for (int i = (int)(((num)l.leaf).value); i<= (int)(((num)u.leaf).value); i++){
				
				knownVar.put(this.iterator, new expression(new num(i)));
				
				for(int j =0; j<this.body.size(); j++){
					s = this.body.get(j);
					
					if(s.type.equals("defVarStatement")){
						if(((defVarStatement)s).init!=null){
							defVarStatement news = new defVarStatement(((defVarStatement)s).type,((defVarStatement)s).name);
							news.init = ((defVarStatement)s).init.eval_0(knownVar);
							ret.add(news);
						}
						else ret.add(s);
					}
					else if(s.type.equals("assignStatement")){
						assignStatement news = null;
						if(((assignStatement)s).lhs!=null){
								news = new assignStatement(((assignStatement)s).lhs.eval_0(knownVar));
								news.name = null;
							}
						else{
							news = new assignStatement(((assignStatement)s).name);
							news.lhs = null;
						}
						
						if(((assignStatement)s).assignment!=null){
							news.assignment = ((assignStatement)s).assignment.eval_0(knownVar);
						}
						ret.add(news);
					}
					else if(s.type.equals("priorInfo")){
						priorInfo news = null;
						news = new priorInfo();
						inputPrior x;
						for(int k=0; k<((priorInfo)s).prior.size(); k++){
							x = new inputPrior(((priorInfo)s).prior.get(k).name.eval_0(knownVar));
							x.intervals = ((priorInfo)s).prior.get(k).intervals;
							news.prior.add(x);
						}
						ret.add(news);
					}
					else if(s.type.equals("forStatement")){
						ArrayList<statement> nested = ((forStatement)s).unroll(knownVar); // recursive call
		
						statement nesteds=null;
						for(int k=0; k<nested.size(); k++){
							ret.add(nested.get(k));
						}
					}
					else{
						System.out.println("this type of statement is not supported in forloop***IFSTATEMENT IS NOT SUPPORTED IN FORLOOP***");
					}
				}
			}
		}
		
		return ret;
	}
	
	public void replace_hole(){
		
		statement s;
		for(int i=0; i<this.body.size(); i++){
			s = this.body.get(i);
			if(s.type.equals("defVarStatement") && ((defVarStatement)s).init!=null)
				((defVarStatement)s).init = ((defVarStatement)s).init.replace_hole(this.iterator);
			else if(s.type.equals("assignStatement") && ((assignStatement)s).assignment!=null)
				((assignStatement)s).assignment = ((assignStatement)s).assignment.replace_hole(this.iterator);
			else if(s.type.equals("ifStatement")){
				((ifStatement)s).replace_hole(this.iterator);
			}
			else if(s.type.equals("forStatement")){
				((forStatement)s).replace_hole();
				statement s1;
				for(int j=0; j<((forStatement)s).body.size();j++){
					s1 = ((forStatement)s).body.get(j);
					if(s1.type.equals("defVarStatement") && ((defVarStatement)s1).init!=null)
						((defVarStatement)s1).init = ((defVarStatement)s1).init.replace_hole(this.iterator);
					else if(s1.type.equals("assignStatement") && ((assignStatement)s1).assignment!=null)
						((assignStatement)s1).assignment = ((assignStatement)s1).assignment.replace_hole(this.iterator);
					else if(s1.type.equals("ifStatement")){
						((ifStatement)s1).replace_hole(this.iterator);
					}
				}
			}
			else if(!s.type.equals("priorInfo")){
				System.out.println("statement not supported in forloop!");
			}
		}
		
	}
}


