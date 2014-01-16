package execute;
import java.util.ArrayList;
import java.util.HashMap;

import agent.agentTemplate;
import object.*;
import expression.expression;
import statement.*;
import term.inputPrior;


/*
Execution code will include agent generation 
and
mechanism execution
currently

No ifStatement is needed for now 
therefore not supported

*/
 
public class executeCode {
	public ArrayList<statement> s;
	public HashMap<object, HashMap<String, object>> vars;
	public ArrayList<ArrayList<statement>> bp;
	public HashMap<object, object> programReturn;
	boolean hasIf;
	
	public executeCode(){
		this.s = new ArrayList<statement>();
		this.bp = new ArrayList<ArrayList<statement>>();
		this.hasIf = false;
		this.vars = new HashMap<object, HashMap<String, object>>();
		this.programReturn = new HashMap<object, object>();
	}
	
	public void eval_0(HashMap<String, expression> globalVar){
		for(int i=0; i<this.s.size(); i++){
			this.s.get(i).eval_0(globalVar);
		}
	}
	
	public void basicPath(HashMap<String, expression> globalVar){
		HashMap<String, expression> tempglobalVar = new HashMap<String, expression>();
		for(int i=0 ; i<globalVar.size(); i++)
			tempglobalVar.put((String)globalVar.keySet().toArray()[i], (expression)globalVar.values().toArray()[i]);
		
		statement c;
    	for(int i=0; i<this.s.size(); i++){
    		 c = this.s.get(i);
        	
    		  if(!this.hasIf && !c.type.equals("ifStatement")){
        		if(this.bp.size()==0){
        			ArrayList<statement> temp = new ArrayList<statement>();
        			this.bp.add(temp);
        		}
        	
        		if(!c.type.equals("forStatement"))
        			this.bp.get(0).add(c);
				else{
					((forStatement)c).replace_hole();
					ArrayList<statement> temps = ((forStatement)c).unroll(tempglobalVar);
					if(temps!=null){
						for(int r=0; r<temps.size(); r++)
							this.bp.get(0).add(temps.get(r));
					}
				}
        	}
        	else if(!this.hasIf && c.type.equals("ifStatement")){
        		this.hasIf = true;
        		ArrayList<ArrayList<statement>> tempbp = ((ifStatement)c).basicPath(tempglobalVar);
        		for(int j=0; j< tempbp.size(); j++){
        			this.bp.add(tempbp.get(j));
        		}
        	}
        	else if(this.hasIf && !c.type.equals("ifStatement")){
        		
        			if(!c.type.equals("forStatement")){
        				for(int j=0; j< this.bp.size(); j++)
        					this.bp.get(j).add(c);
        			}
            			
    				else{
    					((forStatement)c).replace_hole();
    					ArrayList<statement> temps = ((forStatement)c).unroll(tempglobalVar);
    					for(int j=0; j< this.bp.size(); j++){
    						if(temps!=null){
    							for(int r=0; r<temps.size(); r++)
    								this.bp.get(j).add(temps.get(r));
    						}
    					}
    				}
        		
        	}
        	
        	else{
        		ArrayList<statement> temp;
        		ArrayList<ArrayList<statement>> templ = new ArrayList<ArrayList<statement>>();
        		ArrayList<ArrayList<statement>> tempbp;
        		for(int j=0; j< this.bp.size(); j++){
        			tempbp = ((ifStatement)c).basicPath(tempglobalVar);
        			for(int k=0; k<tempbp.size(); k++){
        				temp = new ArrayList<statement>();
            			for(int m=0; m< this.bp.get(j).size(); m++){
            				temp.add(this.bp.get(j).get(m));
            			}
            			for(int m=0; k<tempbp.get(k).size(); k++){
            				temp.add(tempbp.get(k).get(m));
            			}
            			templ.add(temp);
        			}
        		}
        		this.bp = templ;
        	}
    	}
    	
    }
	

	public void exe(HashMap<String, expression> globalVar, HashMap<String, agentTemplate> agentTemplate, defineFunStatement mechanism, 
			ArrayList<String> existsVar, ArrayList<String> forallVar, HashMap<String,Double> prior_Info){
		// return statement is the value to return
		
		HashMap<String, object> knownVars = null; 
    	statement s;
    	boolean hasMap = false;
    	HashMap<object, HashMap<String, object>> knownVarMap = null; // assume expr -> x = expr
		HashMap<object, object> out = new HashMap<object, object>();
		
    	
    	for(int i=0; i<this.bp.size(); i++){
    		knownVars = new HashMap<String, object>();
    		for(int j= 0; j<globalVar.size(); j++){
    			knownVars.put((String)globalVar.keySet().toArray()[j], ((expression)globalVar.values().toArray()[i]).eval_exe(knownVars, agentTemplate, mechanism, existsVar, forallVar, prior_Info, false));
    		}
    		
    		hasMap = false;
    		object e=null;
    		/*************************************************************  for each basic path *************************************************************************/
    		for(int j=0; j<this.bp.get(i).size();j++){
    			
    			s = this.bp.get(i).get(j);
    			
    			if(s.type.equals("priorInfo")){
    				object lhs;
    				if(!hasMap){
    					for(int k=0; k<((priorInfo)s).prior.size(); k++){ // store prior info
    						lhs = ((priorInfo)s).prior.get(k).name.eval_exe(knownVars, agentTemplate, mechanism, existsVar, forallVar, prior_Info, false);
    						if(lhs.type.equals("string")){
    							double expected_value =0;
    							for(int l=0; l<((priorInfo)s).prior.get(k).intervals.size(); l= l+3)
    								expected_value = expected_value 
    									+ (((priorInfo)s).prior.get(k).intervals.get(l) + ((priorInfo)s).prior.get(k).intervals.get(l+1))*((priorInfo)s).prior.get(k).intervals.get(l+2)/2;
 
    							prior_Info.put(((string)lhs).s, expected_value);
    						}
    						else
    							System.out.println("prior info parameter is not defined.");
    					}
    				}
    				else{
    					System.out.println("map is not supported in prior info");
    				}
    				
    			}
    			
    			else if(s.type.equals("defVarStatement")){
    				
    				if(((defVarStatement)s).init!= null){
    					e = ((defVarStatement)s).init.eval_exe(knownVars,agentTemplate, mechanism, existsVar, forallVar, prior_Info, false);
    				}
    				else {
    					if(((defVarStatement)s).vType.equals("tuple")){
    						ArrayList<object> t = new ArrayList<object>();
    						e = new tuple(t);
    					}
    					else if(((defVarStatement)s).vType.equals("list")){ // list of i dimemsions
    						e = ((defVarStatement)s).createNestedList(((defVarStatement)s).dimension);
    					}
    				}
    				
    				if(!hasMap && (e==null || ( e!=null && !e.type.equals("map")))){
    					knownVars.put(((defVarStatement)s).name, e);
    				}
    				else if(!hasMap && e.type.equals("map")){
    					
    					knownVarMap = new HashMap<object, HashMap<String, object>>();
    					hasMap = true;
    					
    					HashMap<object, object> tempm = ((map)e).m;
    					HashMap<String, object> tempv;
    					
    					for(int k=0; k<tempm.size(); k++){
    						tempv = new HashMap<String, object>();
    						for(int m=0; m<knownVars.size(); m++){
    							if(((object)knownVars.values().toArray()[m]).type.equals("tuple"))
    								tempv.put((String)knownVars.keySet().toArray()[m], ((tuple)knownVars.values().toArray()[m]).copy());
    							else if(((object)knownVars.values().toArray()[m]).type.equals("list"))
    								tempv.put((String)knownVars.keySet().toArray()[m], ((list)knownVars.values().toArray()[m]).copy());
    							else
    								tempv.put((String)knownVars.keySet().toArray()[m], (object)knownVars.values().toArray()[m]);
    						}
    						tempv.put(((defVarStatement)s).name, (object)tempm.values().toArray()[k]);
    						knownVarMap.put((object)tempm.keySet().toArray()[k], tempv);
    					}
    					
    				}
    				else if(hasMap && (e==null || ( e!=null && !e.type.equals("map")))){
    					for(int k=0; k<knownVarMap.size(); k++){
    						((HashMap<String, object>)knownVarMap.values().toArray()[k]).put(((defVarStatement)s).name, e);
    					}
    				}
    				else if(hasMap && e.type.equals("map")){
    					HashMap<object, HashMap<String, object>> newknownVarMap = new HashMap<object, HashMap<String, object>>();
    					HashMap<object, object> tempm = ((map)e).m;
    					HashMap<String, object> tempv;
    					HashMap<String, object> parent;
    					string condition;
    					
    					for(int k=0; k<knownVarMap.size(); k++){
    						parent = (HashMap<String,object>)knownVarMap.values().toArray()[k];
    						tempv = new HashMap<String, object>();
    						
    						for(int m=0; m<tempm.size(); m++){
    							tempv.put(((defVarStatement)s).name, (object)tempm.values().toArray()[m]);
    							for(int n=0; n<parent.size(); n++){
    								if(((object)parent.values().toArray()[n]).type.equals("tuple"))
        								tempv.put((String)parent.keySet().toArray()[n], ((tuple)parent.values().toArray()[n]).copy());
        							else if(((object)parent.values().toArray()[n]).type.equals("list"))
        								tempv.put((String)parent.keySet().toArray()[n], ((list)parent.values().toArray()[n]).copy());
        							else
        								tempv.put((String)parent.keySet().toArray()[n], (object)parent.values().toArray()[n]);
        						}
    							condition = new string("("+((string)tempm.keySet().toArray()[m]).s +" and " + ((string)knownVarMap.keySet().toArray()[k]).s + ")"); 
    							newknownVarMap.put(condition, tempv);
    						}
    					}
    					knownVarMap = newknownVarMap;
    				}
    			}
    			else if(s.type.equals("assignStatement")){
    				 e = ((assignStatement)s).assignment.eval_exe(knownVars,agentTemplate, mechanism, existsVar, forallVar, prior_Info, false);
    				
    				if(((assignStatement)s).name!=null && ((assignStatement)s).assignment!= null){
        			
    					if(!hasMap && (e==null || ( e!=null && !e.type.equals("map")))){
        					knownVars.put(((assignStatement)s).name, e);
        				}
        				else if(!hasMap && e.type.equals("map")){
        					
        					knownVarMap = new HashMap<object, HashMap<String, object>>();
        					hasMap = true;
        					
        					HashMap<object, object> tempm = ((map)e).m;
        					HashMap<String, object> tempv;
        					
        					for(int k=0; k<tempm.size(); k++){
        						tempv = new HashMap<String, object>();
        						for(int m=0; m<knownVars.size(); m++){
        							if(((object)knownVars.values().toArray()[m]).type.equals("tuple"))
        								tempv.put((String)knownVars.keySet().toArray()[m], ((tuple)knownVars.values().toArray()[m]).copy());
        							else if(((object)knownVars.values().toArray()[m]).type.equals("list"))
        								tempv.put((String)knownVars.keySet().toArray()[m], ((list)knownVars.values().toArray()[m]).copy());
        							else
        								tempv.put((String)knownVars.keySet().toArray()[m], (object)knownVars.values().toArray()[m]);
        						}
        						tempv.put(((assignStatement)s).name, (object)tempm.values().toArray()[k]);
        						knownVarMap.put((object)tempm.keySet().toArray()[k], tempv);
        					}
        					
        				}
        				else if(hasMap && (e==null || ( e!=null && !e.type.equals("map")))){
        					
        					for(int k=0; k<knownVarMap.size(); k++){
        						((HashMap<String, object>)knownVarMap.values().toArray()[k]).put(((assignStatement)s).name, e);
        					}
        				}
        				else if(hasMap && e.type.equals("map")){
        					HashMap<object, HashMap<String, object>> newknownVarMap = new HashMap<object, HashMap<String, object>>();
        					HashMap<object, object> tempm = ((map)e).m;
        					HashMap<String, object> tempv;
        					HashMap<String, object> parent;
        					string condition;
        					
        					for(int k=0; k<knownVarMap.size(); k++){
        						parent = (HashMap<String,object>)knownVarMap.values().toArray()[k];
        						tempv = new HashMap<String, object>();
        						
        						for(int m=0; m<tempm.size(); m++){
        							tempv.put(((assignStatement)s).name, (object)tempm.values().toArray()[m]);
        							for(int n=0; n<parent.size(); n++){
        								if(((object)parent.values().toArray()[n]).type.equals("tuple"))
            								tempv.put((String)parent.keySet().toArray()[n], ((tuple)parent.values().toArray()[n]).copy());
            							else if(((object)parent.values().toArray()[n]).type.equals("list"))
            								tempv.put((String)parent.keySet().toArray()[n], ((list)parent.values().toArray()[n]).copy());
            							else
            								tempv.put((String)parent.keySet().toArray()[n], (object)parent.values().toArray()[n]);
            						}
        							condition = new string("("+((string)tempm.keySet().toArray()[m]).s +" and " + ((string)knownVarMap.keySet().toArray()[k]).s + ")"); 
        							newknownVarMap.put(condition, tempv);
        						}
        					}
        					knownVarMap = newknownVarMap;
        				}
    					
    				}
    				//
    				else if(((assignStatement)s).lhs != null && ((assignStatement)s).assignment!= null){ 
    					String name = ((assignStatement)s).lhs.tlname; //tuple or list name
    					
    					if(!hasMap && (e==null || ( e!=null && !e.type.equals("map")))){
        					if(knownVars.containsKey(name)){
        						object temptl = knownVars.get(name);
        						object x;
        						if(temptl.type.equals("tuple")){
        							x = ((assignStatement)s).lhs.indexes.get(0).eval_exe(knownVars,agentTemplate, mechanism, existsVar, forallVar, prior_Info, false);
        							if(x.type.equals("number")){
        								((tuple)temptl).t.add(e);
        							}
        						}
        						else if(temptl.type.equals("list")){
        							object lhs = (list)temptl;
        							for(int k=0; k<((assignStatement)s).lhs.indexes.size(); k++){
            							x = ((assignStatement)s).lhs.indexes.get(k).eval_exe(knownVars, agentTemplate, mechanism, existsVar, forallVar, prior_Info, false);
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
        				}
        				else if(!hasMap && e.type.equals("map")){
        					
        					knownVarMap = new HashMap<object, HashMap<String, object>>();
        					hasMap = true;
        					
        					HashMap<object, object> tempm = ((map)e).m;
        					HashMap<String, object> tempv;
        					
        					for(int k=0; k<tempm.size(); k++){
        						tempv = new HashMap<String, object>();
        						for(int m=0; m<knownVars.size(); m++){
        							if(((object)knownVars.values().toArray()[m]).type.equals("tuple"))
        								tempv.put((String)knownVars.keySet().toArray()[m], ((tuple)knownVars.values().toArray()[m]).copy());
        							else if(((object)knownVars.values().toArray()[m]).type.equals("list"))
        								tempv.put((String)knownVars.keySet().toArray()[m], ((list)knownVars.values().toArray()[m]).copy());
        							else
        								tempv.put((String)knownVars.keySet().toArray()[m], (object)knownVars.values().toArray()[m]);
        						}
        						
        						
        						if(tempv.containsKey(name)){
            						object temptl = tempv.get(name);
            						object x;
            						if(temptl.type.equals("tuple")){
            							x = ((assignStatement)s).lhs.indexes.get(0).eval_exe(tempv,agentTemplate, mechanism, existsVar, forallVar, prior_Info, false);
            							if(x.type.equals("number")){
            								((tuple)temptl).t.add((object)tempm.values().toArray()[k]);
            							}
            						}
            						else if(temptl.type.equals("list")){
            							object lhs = (list)temptl;
            							for(int m=0; m<((assignStatement)s).lhs.indexes.size(); m++){
                							x = ((assignStatement)s).lhs.indexes.get(m).eval_exe(tempv, agentTemplate, mechanism, existsVar, forallVar, prior_Info, false);
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
        						knownVarMap.put((object)tempm.keySet().toArray()[k], tempv);
        					}
        					
        				}
        				else if(hasMap && (e==null || ( e!=null && !e.type.equals("map")))){
        					object temptl;
        					object x;
        					for(int k=0; k<knownVarMap.size(); k++){
        						temptl = ((HashMap<String, object>)knownVarMap.values().toArray()[k]).get(name);
        						if(temptl.type.equals("tuple")){
        							x = ((assignStatement)s).lhs.indexes.get(0).eval_exe((HashMap<String, object>)knownVarMap.values().toArray()[k],agentTemplate, mechanism, existsVar, forallVar, prior_Info, false);
        							if(x.type.equals("number")){
        								((tuple)temptl).t.add(e);
        							}
        						}
        						else if(temptl.type.equals("list")){
        							object lhs = (list)temptl;
        							for(int m=0; m<((assignStatement)s).lhs.indexes.size(); m++){
            							x = ((assignStatement)s).lhs.indexes.get(m).eval_exe((HashMap<String, object>)knownVarMap.values().toArray()[k], agentTemplate, mechanism, existsVar, forallVar, prior_Info, false);
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
        				}
        				else if( hasMap && e.type.equals("map")){
        					HashMap<object, HashMap<String, object>> newknownVarMap = new HashMap<object, HashMap<String, object>>();
        					HashMap<object, object> tempm = ((map)e).m;
        					HashMap<String, object> tempv;
        					HashMap<String, object> parent;
        					string condition;
        					object temptl;
        					object x;
        					for(int k=0; k<knownVarMap.size(); k++){
        						parent = (HashMap<String,object>)knownVarMap.values().toArray()[k];
        						tempv = new HashMap<String, object>();
        						
        						for(int m=0; m<tempm.size(); m++){
        							tempv.put(((assignStatement)s).name, (object)tempm.values().toArray()[m]);
        							for(int n=0; n<parent.size(); n++){
        								if(((object)parent.values().toArray()[n]).type.equals("tuple"))
            								tempv.put((String)parent.keySet().toArray()[n], ((tuple)parent.values().toArray()[n]).copy());
            							else if(((object)parent.values().toArray()[n]).type.equals("list"))
            								tempv.put((String)parent.keySet().toArray()[n], ((list)parent.values().toArray()[n]).copy());
            							else
            								tempv.put((String)parent.keySet().toArray()[n], (object)parent.values().toArray()[n]);
            						
            						}
        							temptl = tempv.get(name);
        							if(temptl.type.equals("tuple")){
            							x = ((assignStatement)s).lhs.indexes.get(0).eval_exe(tempv, agentTemplate, mechanism, existsVar, forallVar, prior_Info, false);
            							if(x.type.equals("number")){
            								((tuple)temptl).t.add(e);
            							}
            						}
        							else if(temptl.type.equals("list")){
            							object lhs = (list)temptl;
            							for(int n=0; n<((assignStatement)s).lhs.indexes.size(); n++){
                							x = ((assignStatement)s).lhs.indexes.get(n).eval_exe(tempv, agentTemplate, mechanism, existsVar, forallVar, prior_Info, false);
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
        							condition = new string("("+((string)tempm.keySet().toArray()[m]).s +" and " + ((string)knownVarMap.keySet().toArray()[k]).s + ")"); 
        							newknownVarMap.put(condition, tempv);
        						}
        					}
        					knownVarMap = newknownVarMap;
        				}
    					
    				}
    			}
    			else if(s.type.equals("assumeStatement")){ // ?
    				if(!hasMap){
    					hasMap = true;
    					knownVarMap = new HashMap<object, HashMap<String, object>>();
    					e = ((assumeStatement)s).e.eval_exe(knownVars, agentTemplate, mechanism, existsVar, forallVar, prior_Info, false);
    					if(!e.type.equals("map")){
    						knownVarMap.put(e, knownVars);
    					}
    					else if(e.type.equals("map")){
    						
    						HashMap<String, object> tempv;
    						
    						for(int k=0; k<((map)e).m.size(); k++){
								tempv = new HashMap<String,object>();
								for(int m=0; m<knownVars.size(); m++){
									if(((object)knownVars.values().toArray()[m]).type.equals("tuple"))
        								tempv.put((String)knownVars.keySet().toArray()[m], ((tuple)knownVars.values().toArray()[m]).copy());
        							else if(((object)knownVars.values().toArray()[m]).type.equals("list"))
        								tempv.put((String)knownVars.keySet().toArray()[m], ((list)knownVars.values().toArray()[m]).copy());
        							else
        								tempv.put((String)knownVars.keySet().toArray()[m], (object)knownVars.values().toArray()[m]);
								}
								
								knownVarMap.put((string)((map)e).m.keySet().toArray()[k], tempv);  
							}
    					}
    				}
    				else{
						boolean returnMap = false;
						HashMap<object, HashMap<String, object>> newknownVarMap=null;
						
						for(int k=0; k< knownVarMap.size();k++){
							
							e = ((assumeStatement)s).e.eval_exe((HashMap<String, object>) knownVarMap.values().toArray()[k], agentTemplate, mechanism, existsVar, forallVar, prior_Info, false);
							string condition = null;
	    					if(e.type.equals("expression")){
	    						condition = new string("("+((string)e).s +" and " + ((string)knownVarMap.keySet().toArray()[k]).s + ")"); 
	    						knownVarMap.put(condition, ((HashMap<String, object>)knownVarMap.values().toArray()[k]));
	    						knownVarMap.remove((object)knownVarMap.keySet().toArray()[k]);
	    					}
	    					
	    					else if(e.type.equals("map")){
	    						if(!returnMap)
	    							returnMap = true;
								newknownVarMap = new HashMap<object, HashMap<String,object>>();
    							HashMap<String,object> addv;
    							HashMap<String, object> tempv;
        						for(int m=0; m<((map)e).m.size(); m++){
    								addv = new HashMap<String,object>();
    								tempv = (HashMap<String, object>)knownVarMap.values().toArray()[k]; 
    								for(int n=0; n<tempv.size(); n++){
    									if(((object)tempv.values().toArray()[n]).type.equals("tuple"))
            								addv.put((String)tempv.keySet().toArray()[n], ((tuple)tempv.values().toArray()[n]).copy());
            							else if(((object)tempv.values().toArray()[n]).type.equals("list"))
            								addv.put((String)tempv.keySet().toArray()[n], ((list)tempv.values().toArray()[n]).copy());
            							else
            								addv.put((String)tempv.keySet().toArray()[n], (object)tempv.values().toArray()[n]);
    								}
    								condition = new string("(("+((string)knownVarMap.keySet().toArray()[k]).s + " and " + ((string)((map)e).m.keySet().toArray()[k]).s 
    										+ ") and (" + ((string)((map)e).m.values().toArray()[k]).s+"))");
        							newknownVarMap.put(condition, addv);
    							}
							}
						}
						if(returnMap)
							knownVarMap = newknownVarMap;
    				}
    			} 				
    			else{
    				System.out.println("error");
    			}
    		}
    		
    		/*************************************************************  for each basic path *************************************************************************/
    		if(!hasMap){
    			this.vars.put(new string("true"), knownVars);
    			for(int j=0; j<knownVars.size(); j++){
    				if(((String)knownVars.keySet().toArray()[j]).equals("return")){
    					this.programReturn.put(new string("true"), (object)knownVars.values().toArray()[j]);
    				}
    			}
    		}
    		else{
    			for(int j=0; j<knownVarMap.size(); j++){
    				this.vars.put((object) knownVarMap.keySet().toArray()[j], (HashMap<String, object>) knownVarMap.values().toArray()[j]);
    				for(int k=0; k<((HashMap<String, object>)knownVarMap.values().toArray()[j]).size(); k++){
    					if(((String)((HashMap<String, object>)knownVarMap.values().toArray()[j]).keySet().toArray()[k]).equals("return")){
    						this.programReturn.put((object)knownVarMap.keySet().toArray()[j],(object)((HashMap<String, object>)knownVarMap.values().toArray()[j]).values().toArray()[k]);
    					}
    				}
    			}
    		}
    	} // end 
    	
	} 
	
	
	
}
