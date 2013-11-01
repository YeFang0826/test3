package logicExpression;

import java.util.ArrayList;
import java.util.HashMap;

import object.*;
import agent.agentTemplate;

import quantifier.*;

import deparen.deparen;


import execute.executeCode;
import expression.expression;
import functionCall.directCall;
import statement.*;
import term.expressionT;
import term.functionCallT;
import term.holeT;
import term.num;
import term.sortedList;
import term.var;

public class logicExpression {
	public ArrayList<quantifier> q;
	public logicExpression l;
	public logicExpression r;
	public String operator; // and, or, not
	public expression leaf; 
	public ArrayList<ArrayList<logicExpression>> f;
	
	public logicExpression(){
		this.q = new ArrayList<quantifier>();
		this.f = null;
	}
	
	public logicExpression eval_0(HashMap<String, expression> globalVar){
		logicExpression ret = new logicExpression();
		ret.q = this.q;
		if(this.leaf!=null){
			ret.leaf = this.leaf.eval_0(globalVar);
		}
		else if(this.l != null && this.r !=null && !this.operator.equals("")){
			logicExpression left = this.l.eval_0(globalVar);
			logicExpression right = this.r.eval_0(globalVar);
			ret.l = left;
			ret.r = right;
			ret.operator = this.operator;
		}
		else if(this.r!=null && !this.operator.equals("")){
			logicExpression right = this.r.eval_0(globalVar);
			ret.r = right;
			ret.operator = this.operator;
		}
		return ret;
	}
	
	public ArrayList<ArrayList<logicExpression>> qe(HashMap<String, expression> globalVar){ // eliminate quantifier by unrolling
		ArrayList<ArrayList<logicExpression>> ret = null;
		ArrayList<ArrayList<logicExpression>> rettemp;
		
		if(this.leaf!=null){
			if(this.q.size()>0){
				ret = this.sub_unroll(0, globalVar);
			}
			else{
				ArrayList<logicExpression> templ = new ArrayList<logicExpression>();
				templ.add(this);
				ret = new ArrayList<ArrayList<logicExpression>>();
				ret.add(templ);
			}
		}
		else if(this.l!=null && this.r!=null){
			ArrayList<ArrayList<logicExpression>> left = this.l.qe(globalVar);
			ArrayList<ArrayList<logicExpression>> right = this.r.qe(globalVar);
			ArrayList<logicExpression> templ;
			ret = new ArrayList<ArrayList<logicExpression>>();
			rettemp = new ArrayList<ArrayList<logicExpression>>();
			
			if(this.operator.equals("and")){
				logicExpression l1 = null;
				logicExpression l2;
				
				for(int i=0; i<left.size(); i++){
					for(int j=0; j<right.size(); j++){
						templ = new ArrayList<logicExpression>();
						for(int k=0; k<left.get(i).size(); k++){
							if(k==0)
								l1 = left.get(i).get(k);
							else{
								l2 = l1;
								l1 = new logicExpression();
								l1.l = l2;
								l1.r = left.get(i).get(k);
								l1.operator = "and";
							}
						}
						for(int k=0; k<right.get(j).size(); k++){
							l2 = l1;
							l1 = new logicExpression();
							l1.l = l2;
							l1.r = right.get(j).get(k);
							l1.operator = "and";
						}
						l1.q = this.q;
						templ.add(l1);
						rettemp.add(templ);
					}
				}
			}
			else if(this.operator.equals("or")){
				logicExpression l1 = null;
				logicExpression l2;
				logicExpression l3 = null;
				logicExpression l4;
				logicExpression l5;
				
				for(int i=0; i<left.size(); i++){
					for(int j=0; j<right.size(); j++){
						templ = new ArrayList<logicExpression>();
						for(int k=0; k<left.get(i).size(); k++){
							if(k==0)
								l1 = left.get(i).get(k);
							else{
								l2 = l1;
								l1 = new logicExpression();
								l1.l = l2;
								l1.r = left.get(i).get(k);
								l1.operator = "and"; // l1
							}
						}
						for(int k=0; k<right.get(j).size(); k++){
							if(k==0)
								l3 = right.get(j).get(k);
							else{
								l4 = l3;
								l3 = new logicExpression();
								l3.l = l4;
								l3.r = right.get(j).get(k);
								l3.operator = "and"; //l3
							}
						}
						l5 = new logicExpression();
						l5.l = l1;
						l5.r = l3;
						l5.operator = "or";
						l5.q = this.q;
						templ.add(l5);
						rettemp.add(templ);
					}
				}
					
			}
			
			else if(this.operator.equals("implies")){
				logicExpression condition = null;
				logicExpression conditiontemp = null;
				logicExpression consq =null;
				logicExpression consqtemp = null;
				logicExpression retle;
				
				for(int i=0; i<left.size(); i++){
					condition = new logicExpression();
					for(int k=0; k<left.get(i).size(); k++){
						if(k==0)
							condition = left.get(i).get(k);
						else{
							conditiontemp = new logicExpression();
							conditiontemp.l = condition;
							conditiontemp.r = left.get(i).get(k);
							conditiontemp.operator = "and";
							condition = conditiontemp;
						}
					}
					for(int j=0; j<right.size(); j++){
						templ = new ArrayList<logicExpression>();
						retle = new logicExpression();
						retle.l = condition;
						for(int k=0; k<right.get(j).size(); k++){
							if(k==0)
								consq = right.get(j).get(k);
							else{
								consqtemp = new logicExpression();
								consqtemp.l = condition;
								consqtemp.r = right.get(j).get(k);
								consqtemp.operator = "and";
								consq = consqtemp;
							}
						}
						retle.r = consq;
						retle.operator = "implies";
						retle.q = this.q;
						templ.add(retle);
						rettemp.add(templ);
					}
					
				}
				
			}
			else{
				System.out.println("logic operator is not supported!");
			}
			
			/******************************* eval***********************************************/
			if(this.q.size()>0){
				ArrayList<ArrayList<logicExpression>> retholder; 
				ArrayList<ArrayList<logicExpression>> r1 = null;
				ArrayList<ArrayList<logicExpression>> r2 = null;
				
				for(int i=0; i<rettemp.size(); i++){ // or clauses
					r1 = new ArrayList<ArrayList<logicExpression>>();
					for(int j=0; j<rettemp.get(i).size();j++){ // and clauses
						retholder = rettemp.get(i).get(j).sub_unroll(0,globalVar);
						if(r1.size()==0){
							for(int m=0; m<retholder.size(); m++){
								templ = new ArrayList<logicExpression>();
								for(int n=0; n<retholder.get(m).size(); n++){
									templ.add(retholder.get(m).get(n));
								}
								r1.add(templ);
							}
						}
						else{
							r2 = new ArrayList<ArrayList<logicExpression>>();
							for(int m=0; m<retholder.size(); m++){
								for(int n=0; n<r1.size(); n++){
									templ = new ArrayList<logicExpression>();
									for(int k=0; k<retholder.get(m).size(); k++)
										templ.add(retholder.get(m).get(k));
									for(int k=0; k<r1.get(n).size(); k++)
										templ.add(r1.get(n).get(k));
									r2.add(templ);
								}
							}
							r1 = r2;
						}
					}
					if(ret.size()==0)
						ret = r1;
					else{
						for(int j=0; j<r1.size(); j++){
							templ = new ArrayList<logicExpression>();
							for(int k=0; k<r1.get(j).size(); k++)
								templ.add(r1.get(j).get(k));
							ret.add(templ);
						}
					}
				}
			}
			else
				ret = rettemp;
			
		}
		else if(this.r!=null){
			ArrayList<ArrayList<logicExpression>> right = this.r.qe(globalVar);
			System.out.println("Not is currently not coded up ----- in progress!");
		}
		return ret;
	}
	
	public ArrayList<ArrayList<logicExpression>> sub_unroll(int depth, HashMap<String,expression> globalVar){
		ArrayList<ArrayList<logicExpression>> formulas = null;
		HashMap<String, expression> tempv = new HashMap<String, expression>();
		
		for(int i=0; i<globalVar.size(); i++){
			tempv.put((String)globalVar.keySet().toArray()[i], (expression) globalVar.values().toArray()[i]);
		}
		
		ArrayList<logicExpression> templ;
		
		if(depth==this.q.size()-1){
			quantifier tempq;
			int lb = -1;
			int ub = -1;
			String iterator = null;
			tempq = this.q.get(depth);
			
			
			if(tempq.type.equals("exist")){ // or clauses 
				expression testl = ((exist)tempq).lbound.eval_0(tempv);
				expression testr = ((exist)tempq).ubound.eval_0(tempv);
				
				if(testl.leaf.type.equals("num") && testr.leaf.type.equals("num")){
					lb = (int)((num)(testl.leaf)).value;
					ub = (int)((num)(testr.leaf)).value;
				}
				iterator = ((exist)tempq).i;
				
				if(formulas == null){
					formulas = new ArrayList<ArrayList<logicExpression>>();
					for(int j=lb; j<ub; j++){
						tempv.put(iterator, new expression(new num(j)));
						templ = new ArrayList<logicExpression>();
						templ.add(this.eval_0(tempv));
						formulas.add(templ);
					}
				}
			}
			else if(tempq.type.equals("forall")){
				expression testl = ((forall)tempq).lbound.eval_0(globalVar);
				expression testr = ((forall)tempq).ubound.eval_0(globalVar);
					
					
				if(testl.leaf.type.equals("num") && testr.leaf.type.equals("num")){
					lb = (int)((num)(testl.leaf)).value;
					ub = (int)((num)(testr.leaf)).value;
				}
				
				iterator = ((forall)tempq).i;
				
				if(formulas == null){
					formulas = new ArrayList<ArrayList<logicExpression>>();
					templ = new ArrayList<logicExpression>();
					for(int j=lb; j<ub; j++){
						tempv.put(iterator, new expression(new num(j)));
						templ.add(this.eval_0(tempv));
					}
					formulas.add(templ);
				}
			}
		}
		
		else{
			quantifier tempq;
			int lb = -1;
			int ub = -1;
			String iterator = null;
			tempq = this.q.get(depth);
			ArrayList<ArrayList<logicExpression>> retf= null;
			
			if(tempq.type.equals("exist")){ // or clauses 
				expression testl = ((exist)tempq).lbound.eval_0(tempv);
				expression testr = ((exist)tempq).ubound.eval_0(tempv);
				
				if(testl.leaf.type.equals("num") && testr.leaf.type.equals("num")){
					lb = (int)((num)(testl.leaf)).value;
					ub = (int)((num)(testr.leaf)).value;
				}
				iterator = ((exist)tempq).i;
				
				for(int i=lb; i<ub; i++){
					tempv.put(iterator, new expression(new num(i)));
					retf = this.sub_unroll(depth+1, tempv);
					if(formulas == null && retf!=null){
						formulas = new ArrayList<ArrayList<logicExpression>>();
						for(int j=0; j<retf.size(); j++){
							templ = new ArrayList<logicExpression>();
							for(int k=0; k<retf.get(j).size(); k++)
								templ.add(retf.get(j).get(k));
							formulas.add(templ);
						}
					}
					else if(formulas!=null && retf!=null){
						for(int j=0; j<retf.size(); j++){
							templ = new ArrayList<logicExpression>();
							for(int k=0; k<retf.get(j).size(); k++)
								templ.add(retf.get(j).get(k));
							formulas.add(templ);
						}
					}
					else{
						System.out.println("ERROR IN UNROLLING QUANTIFIERS!");
					}	
				}
			}
			else if(tempq.type.equals("forall")){
				expression testl = ((forall)tempq).lbound.eval_0(tempv);
				expression testr = ((forall)tempq).ubound.eval_0(tempv);
				
				if(testl.leaf.type.equals("num") && testr.leaf.type.equals("num")){
					lb = (int)((num)(testl.leaf)).value;
					ub = (int)((num)(testr.leaf)).value;
				}
				iterator = ((forall)tempq).i;
				
				for(int i=lb; i<ub; i++){
					tempv.put(iterator, new expression(new num(i)));
					retf = this.sub_unroll(depth+1, tempv);
					if(formulas == null && retf!=null){
						formulas = new ArrayList<ArrayList<logicExpression>>();
						for(int j=0; j<retf.size(); j++){
							templ = new ArrayList<logicExpression>();
							for(int k=0; k<retf.get(j).size(); k++)
								templ.add(retf.get(j).get(k));
							formulas.add(templ);
						}
					}
					else if(formulas!=null && retf!=null){
						ArrayList<ArrayList<logicExpression>> newformulas = new ArrayList<ArrayList<logicExpression>>();
						for(int j=0; j<formulas.size(); j++){
							for(int k=0; k<retf.size(); k++){
								templ = new ArrayList<logicExpression>();
								for(int m=0; m<formulas.get(j).size(); m++)
									templ.add(formulas.get(j).get(m));
								for(int m=0; m<retf.get(k).size(); m++)
									templ.add(retf.get(k).get(m));
								newformulas.add(templ);
							}
						}
						formulas = newformulas;
					}
					else{
						System.out.println("ERROR IN UNROLLING QUANTIFIERS!");
					}	
				}
			}
		}
		
		return formulas;
			
	}
	
	public void unroll(HashMap<String,expression> globalVar){
		this.f = this.qe(globalVar);
	}
	
	public String pre_eval_exe(HashMap<String, object> knownVars, HashMap<String, agentTemplate> agentTemplate, defineFunStatement mechanism,
			ArrayList<String> existsVar, ArrayList<String> forallVar){
		if(this.f!=null){
			String f1;
			String f2;
			String f3="";
			object r;
			for(int i=0; i<this.f.size();i++){
				f2="";
				for(int j=0; j<this.f.get(i).size();j++){
					r = this.f.get(i).get(j).pre_eval_exesub(knownVars, agentTemplate, mechanism, existsVar, forallVar);
					f1=""; 
					if(r.type.equals("map")){
						for(int k= 0; k<((map)r).m.size(); k++){
							if(k==0)
								f1="("+ ((string)((map)r).m.keySet().toArray()[k]).s + " implies " + ((string)((map)r).m.values().toArray()[k]).s +")";
							else
								f1 = "("+f1 + " and " +"("+ ((string)((map)r).m.keySet().toArray()[k]).s + " implies " + ((string)((map)r).m.values().toArray()[k]).s +"))";
						}
					}
					else
						f1 = "("+((string)r).s+")";
					
					
					if(j==0)
						f2 = f1;
					else
						f2 = "("+f2 + " and " + f1+")";
				}
				if(i==0)
					f3 = f2;
				else
					f3 = "("+f3 + " or " + f2+")";
				
			}
			return f3;
		}
		else{
			object r;
			String f1="";
			r = this.pre_eval_exesub(knownVars, agentTemplate, mechanism, existsVar, forallVar);
			if(r.type.equals("map")){
				for(int k= 0; k<((map)r).m.size(); k++){
					if(k==0)
						f1="("+ ((string)((map)r).m.keySet().toArray()[k]).s + " implies " + ((string)((map)r).m.values().toArray()[k]).s +")";
					else
						f1 = "("+f1 + " and " +"("+ ((string)((map)r).m.keySet().toArray()[k]).s + " implies " + ((string)((map)r).m.values().toArray()[k]).s +"))";
				}
			}
			else
				f1 = ((string)r).s;
			return f1;
		}
		
		
	}
	
	public object pre_eval_exesub(HashMap<String, object> knownVars, HashMap<String, agentTemplate> agentTemplate, defineFunStatement mechanism,
			ArrayList<String> existsVar, ArrayList<String> forallVar){
		object ret = null;
		if(this.leaf!=null){
			ret = this.leaf.eval_exe(knownVars, agentTemplate, mechanism, existsVar, forallVar);
		}
		else if(this.l!=null && this.r != null){
			object left = this.l.pre_eval_exesub(knownVars, agentTemplate, mechanism, existsVar, forallVar);
			object right = this.r.pre_eval_exesub(knownVars, agentTemplate, mechanism, existsVar, forallVar);
			if(left.type.equals("string") && right.type.equals("string")){
				return new string(((string)left).s +" "+ this.operator +" "+ ((string)right).s);
			}
			else if(left.type.equals("string") && right.type.equals("number")){
				return new string(((string)left).s +" "+ this.operator +" "+ Double.toString(((number)right).n));
			}
			else if(left.type.equals("number") && right.type.equals("string")){
				return new string(Double.toString(((number)left).n) +" "+ this.operator +" "+ ((string)right).s);
			}
			else if(left.type.equals("number") && right.type.equals("number")){
				return new string(Double.toString(((number)left).n) +" "+ this.operator +" "+ Double.toString(((number)right).n));
			}
			else{
				System.out.println("expression operation not supported right now in execution code");
			}
		}
		else if (this.r!=null && !this.operator.equals("")){
			object right = this.r.pre_eval_exesub(knownVars, agentTemplate, mechanism, existsVar, forallVar);
			if(right.type.equals("string")){
				return new string(this.operator + ((string)right).s); 
			}
			else if(right.type.equals("number")){
				return new string(this.operator + Double.toString(((number)right).n)); 
			}
		}
		return ret;
	}
	
	public String post_eval_exe(HashMap<String, agentTemplate> agentTemplate, defineFunStatement mechanism, executeCode code, ArrayList<String> existsVar, ArrayList<String> forallVar){
		
		if(this.f!=null){
			String f1;
			String f2;
			String f3="";
			object r;
			for(int i=0; i<this.f.size();i++){
				f2="";
				for(int j=0; j<this.f.get(i).size();j++){
					r = this.f.get(i).get(j).post_eval_exesub(agentTemplate, mechanism, code, existsVar, forallVar);
					f1=""; 
					if(r.type.equals("map")){
						for(int k= 0; k<((map)r).m.size(); k++){
							if(k==0)
								f1="("+ ((string)((map)r).m.keySet().toArray()[k]).s + " implies " + ((string)((map)r).m.values().toArray()[k]).s +")";
							else
								f1 = "("+f1 + " and " +"("+ ((string)((map)r).m.keySet().toArray()[k]).s + " implies " + ((string)((map)r).m.values().toArray()[k]).s +"))";
						}
					}
					else
						f1 = ((string)r).s;
					
					
					if(j==0)
						f2 = f1;
					else
						f2 = "("+f2 + " and " + f1+")";
				}
				if(i==0)
					f3 = f2;
				else
					f3 = "("+f3 + " or " + f2+")";
				
			}
			return f3;
		}
		else{
			object r;
			String f1="";
			r = this.post_eval_exesub(agentTemplate, mechanism, code, existsVar, forallVar);
			if(r.type.equals("map")){
				for(int k= 0; k<((map)r).m.size(); k++){
					if(k==0)
						f1="("+ ((string)((map)r).m.keySet().toArray()[k]).s + " implies " + ((string)((map)r).m.values().toArray()[k]).s +")";
					else
						f1 = "("+f1 + " and " +"("+ ((string)((map)r).m.keySet().toArray()[k]).s + " implies " + ((string)((map)r).m.values().toArray()[k]).s +"))";
				}
			}
			else
				f1 = ((string)r).s;
			return f1;
		}
		
		
	}
	
	public object post_eval_exesub(HashMap<String, agentTemplate> agentTemplate, defineFunStatement mechanism, executeCode code,ArrayList<String> existsVar, ArrayList<String> forallVar){
		
		HashMap<String, object> knownVars;
		object ret =null;
		
		for(int i=0; i<code.vars.size(); i++){
			knownVars = (HashMap<String, object>)code.vars.values().toArray()[i];
			if(this.leaf!=null){
				ret = this.leaf.eval_exe(knownVars, agentTemplate, mechanism, existsVar, forallVar);
			}
			else if(this.l!=null && this.r != null){
				
				object left = this.l.post_eval_exesub(agentTemplate, mechanism, code, existsVar, forallVar);
				object right = this.r.post_eval_exesub(agentTemplate, mechanism, code, existsVar, forallVar);
				
				if(left.type.equals("string") && right.type.equals("string")){
					return new string("(" + ((string)left).s +" "+ this.operator +" "+ ((string)right).s + ")");
				}
				else if(left.type.equals("string") && right.type.equals("number")){
					return new string("(" + ((string)left).s +" "+ this.operator +" "+ Double.toString(((number)right).n)+ ")");
				}
				else if(left.type.equals("number") && right.type.equals("string")){
					return new string("(" + Double.toString(((number)left).n) +" "+ this.operator +" "+ ((string)right).s+ ")");
				}
				else if(left.type.equals("number") && right.type.equals("number")){
					return new string("(" + Double.toString(((number)left).n) +" "+ this.operator +" "+ Double.toString(((number)right).n)+ ")");
				}
				else{
					System.out.println("expression operation not supported right now in execution code");
				}
			}
			else if (this.r!=null && !this.operator.equals("")){
				object right = this.r.post_eval_exesub(agentTemplate, mechanism, code, existsVar, forallVar);
				if(right.type.equals("string")){
					return new string("(" + this.operator + ((string)right).s+ ")"); 
				}
				else if(right.type.equals("number")){
					return new string("(" + this.operator + Double.toString(((number)right).n)+ ")"); 
				}
			}
			
		}
		return ret;
		
	}
	
}