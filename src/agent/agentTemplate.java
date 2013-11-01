package agent;

import java.util.ArrayList;
import java.util.HashMap;

import object.list;
import object.number;
import object.object;
import object.tuple;

import statement.*;
import expression.expression;

public class agentTemplate {
	public String name;
	public HashMap<String, object> vars; //local variables
	public HashMap<String, defineFunStatement> funs; //local functions
	public ArrayList<statement> code;
	public ArrayList<statement> bp; // no if statement in agentTemplate
	
	public agentTemplate(String name){
		this.name = name; 
		this.vars = new HashMap<String, object>();
		this.funs = new HashMap<String, defineFunStatement> ();
		this.code = new ArrayList<statement>();
		this.bp = new ArrayList<statement>(); 
	}
	
	public void addS(statement s){
		this.code.add(s);
	}
	// has no info of the environment other than public info and the result get from decision maker.
	public void eval(HashMap<String, expression> globalVar,ArrayList<String> existsVar, ArrayList<String> forallVar){ 
		HashMap<String, expression> tempglobalVar = new HashMap<String, expression>();
		for(int i=0 ; i<globalVar.size(); i++)
			tempglobalVar.put((String)globalVar.keySet().toArray()[i], (expression)globalVar.values().toArray()[i]);
	
		statement s;
		for(int i=0; i<this.code.size(); i++){
			this.code.get(i).eval_0(tempglobalVar);
		}
		for(int i=0; i<this.code.size(); i++){
			s = this.code.get(i);
			if(s.type.equals("defineFunStatement")){
				this.bp.add(s);
				this.funs.put(((defineFunStatement)s).name, (defineFunStatement) s);
				((defineFunStatement)s).basicPath(tempglobalVar);
			}
			else if(!s.type.equals("forStatement") && !s.type.equals("ifStatement") ){
				this.bp.add(s);
			}
			else if(s.type.equals("forStatement")){
				ArrayList<statement> temps = ((forStatement)s).unroll(tempglobalVar);
				if(temps!=null){
					for(int j=0; j<temps.size(); j++)
						this.bp.add(temps.get(j));
				}
			}
			else{
				System.out.println("IfStatement is not supported in agentTemplate.");
			}
		}
		
		HashMap<String, object> knownVar = new HashMap<String, object>();
		
		for(int i=0 ; i<globalVar.size(); i++)
			knownVar.put((String)globalVar.keySet().toArray()[i], ((expression)globalVar.values().toArray()[i]).eval_agent_expr(knownVar));
	
		object e;
		
		for(int i=0; i<this.bp.size(); i++){
			s = this.bp.get(i);
			if(s.type.equals("defVarStatement")){
				if(((defVarStatement)s).init!=null){
					e = ((defVarStatement)s).init.eval_agent_expr(knownVar);
					knownVar.put(((defVarStatement)s).name, e);
				}
				
				else if(((defVarStatement)s).vType.equals("tuple")){
					ArrayList<object> t = new ArrayList<object>();
					tuple addt = new tuple(t);
					knownVar.put(((defVarStatement)s).name, addt);
				}
				else if(((defVarStatement)s).vType.equals("list")){
					list addl = ((defVarStatement)s).createNestedList(((defVarStatement)s).dimension);
					knownVar.put(((defVarStatement)s).name,addl);
				}
				else
					knownVar.put(((defVarStatement)s).name, null);
			}
			else if(s.type.equals("defineFunStatement")){
				((defineFunStatement)s).isPrivate = this.name;
				this.funs.put(((defineFunStatement)s).name, (defineFunStatement)s);
			}
			else if(s.type.equals("assignStatement")){
				e = ((assignStatement)s).assignment.eval_agent_expr(knownVar); // return will not be a map;
				
				if(((assignStatement)s).name!=null && ((assignStatement)s).assignment!= null){
					knownVar.put(((assignStatement)s).name, e);
				}
				/******************************************************************************/
				else if(((assignStatement)s).lhs != null && ((assignStatement)s).assignment!= null){ 
					String name = ((assignStatement)s).lhs.tlname; 
					if(knownVar.containsKey(name)){
						object temptl = knownVar.get(name);
						object x;
						if(temptl.type.equals("tuple")){
							x = ((assignStatement)s).lhs.indexes.get(0).eval_agent_expr(knownVar);
							if(x.type.equals("number")){
								((tuple)temptl).t.add(e);
							}
						}
						else if(temptl.type.equals("list")){
							object lhs = (list)temptl;
							for(int k=0; k<((assignStatement)s).lhs.indexes.size(); k++){
								x = ((assignStatement)s).lhs.indexes.get(k).eval_agent_expr(knownVar);
								if(lhs.type.equals("tuple")){
									if(((tuple)lhs).t.size() == ((int)((number)x).n+1)){
										((tuple)lhs).t.remove((int)((number)x).n);
										((tuple)lhs).t.add((int)((number)x).n, e);
									}
									else if(((tuple)lhs).t.size()> (int)((number)x).n)
										lhs = ((tuple)lhs).t.get((int)((number)x).n);
									else
										((tuple)lhs).t.add(e);
								}
								else if(lhs.type.equals("list")){
									if(((list)lhs).l.size()==((int)((number)x).n+1)){
										((list)lhs).l.remove((int)((number)x).n);
										((list)lhs).l.add((int)((number)x).n, e);
									}
									else if(((list)lhs).l.size()> (int)((number)x).n)
										lhs = ((list)lhs).l.get((int)((number)x).n);
									else
										((list)lhs).l.add(e);
								}
								else{
									System.out.println("error in fetch list.");
								}
							}
						}
    				}
					
					else 
						System.out.println("bad access");
				}
				/**********************************************************************************/
				
			}
		}
		
		for(int i=0; i<knownVar.size(); i++){
			if(!globalVar.containsKey((String)knownVar.keySet().toArray()[i])){
				this.vars.put((String)knownVar.keySet().toArray()[i], (object)knownVar.values().toArray()[i]);
			}
		}
	}
	
}
			

