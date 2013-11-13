package statement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import agent.agentTemplate;

import logicExpression.*;

import execute.executeCode;
import expression.*;

import parameter.*;
import term.*;
import object.*;

public class defineFunStatement extends statement{
	public String isPrivate;
	public String name;
	public ArrayList<parameter> inputs;
	public parameter output;
	public ArrayList<statement> code;
	public ArrayList<ArrayList<statement>> bp;
	public logicExpression pre;
	public logicExpression post;
	
	boolean hasIf;
	public object rv;
	
	public defineFunStatement(String name){
		this.type="defineFunStatement";
		this.name = name;
		this.code = new ArrayList<statement>();
		this.bp = new ArrayList<ArrayList<statement>>(); //basic paths with assumeStatement
		this.hasIf = false;
		this.isPrivate = "";
	}
	
	public void input(ArrayList<parameter> in){
		this.inputs = in;
	}
	
	public void output(parameter out){
		this.output = out;
	}
		
    public void addS(statement c){	
    	this.code.add(c);
    }
    
    
    public void eval_0(HashMap<String, expression> globalVar){ // replace id with global constants
    	if(this.pre!=null)
    		this.pre = this.pre.eval_0(globalVar);
    	
    	for(int i=0; i<this.code.size(); i++){
    		this.code.get(i).eval_0(globalVar);
    	}
    	
    }
    
    public void basicPath(HashMap<String, expression> globalVar){
    	HashMap<String, expression> tempglobalVar = new HashMap<String, expression>();
		for(int i=0 ; i<globalVar.size(); i++)
			tempglobalVar.put((String)globalVar.keySet().toArray()[i], (expression)globalVar.values().toArray()[i]);
		
    	statement c;
    	
    	if(this.pre!=null){
    		this.pre.unroll(tempglobalVar);
    	}
    	
    	for(int i=0; i<this.code.size(); i++){
    		 c = this.code.get(i);
        	
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
   
    public object eval_exe(HashMap<String, agentTemplate> agentTemplate, 
    		defineFunStatement mechanism, ArrayList<object> in, ArrayList<String> existsVar, ArrayList<String> forallVar){ // f(rv, inputs)
    	
    	HashMap<String, object> knownVars = null; 
    	statement s;
    	boolean hasMap = false;
    	HashMap<object, HashMap<String, object>> ret = null; // assume expr -> x = expr
		HashMap<object, object> out = new HashMap<object, object>();
		
    	//HashMap<String,object> temp = new HashMap<String, object>();
    	
    	for(int i=0; i<this.bp.size(); i++){
    		// for each basic path
    		
    		knownVars = new HashMap<String, object>();
    		for(int j=0; j< this.inputs.size(); j++){
        		knownVars.put(this.inputs.get(j).name, in.get(j)); // input as knownVars for a function
        	}
    		
    		hasMap = false;
    		object e=null;
    		String precondition = null;
    		if(this.pre!=null){
    			precondition = this.pre.pre_eval_exe(knownVars, agentTemplate, mechanism, existsVar, forallVar);
    			hasMap = true;
    			ret = new HashMap<object, HashMap<String, object>>();
    			ret.put(new string(precondition), knownVars);
    		}
    		
    		for(int j=0; j<this.bp.get(i).size();j++){
    			
    			s = this.bp.get(i).get(j);
    			
    			if(s.type.equals("defVarStatement")){
    				
    				if(((defVarStatement)s).init!= null){
    					e = ((defVarStatement)s).init.eval_exe(knownVars, agentTemplate, this, existsVar, forallVar);
    				}
    				else {
    					if(((defVarStatement)s).vType.equals("tuple")){
    						ArrayList<object> t = new ArrayList<object>();
    						e = new tuple(t);
    					}
    					else if(((defVarStatement)s).vType.equals("list")){ // list of i dimemsions
    						e = ((defVarStatement)s).createNestedList(((defVarStatement)s).dimension);
    					}    					//else e=null;
    				}
    				
    				if(!hasMap && (e==null || ( e!=null && !e.type.equals("map")))){
    					knownVars.put(((defVarStatement)s).name, e);
    				}
    				else if(!hasMap && e.type.equals("map")){
    					
    					ret = new HashMap<object, HashMap<String, object>>();
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
    						ret.put((object)tempm.keySet().toArray()[k], tempv);
    					}
    					
    				}
    				else if(hasMap && (e==null || ( e!=null && !e.type.equals("map")))){
    					for(int k=0; k<ret.size(); k++){
    						((HashMap<String, object>)ret.values().toArray()[k]).put(((defVarStatement)s).name, e);
    					}
    				}
    				else if(hasMap && e.type.equals("map")){
    					HashMap<object, HashMap<String, object>> newret = new HashMap<object, HashMap<String, object>>();
    					HashMap<object, object> tempm = ((map)e).m;
    					HashMap<String, object> tempv;
    					HashMap<String, object> parent;
    					string condition;
    					
    					for(int k=0; k<ret.size(); k++){
    						parent = (HashMap<String,object>)ret.values().toArray()[k];
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
    							condition = new string("(" + ((string)tempm.keySet().toArray()[m]).s + " and " + ((string)ret.keySet().toArray()[k]).s + ")");
    							newret.put(condition, tempv);
    						}
    					}
    					ret = newret;
    				}
    			}
    			else if(s.type.equals("assignStatement")){
    				 e = ((assignStatement)s).assignment.eval_exe(knownVars, agentTemplate, this, existsVar, forallVar);
    				
    				if(((assignStatement)s).name!=null && ((assignStatement)s).assignment!= null){
        			
    					if(!hasMap && (e==null || ( e!=null && !e.type.equals("map")))){
        					knownVars.put(((assignStatement)s).name, e);
        				}
        				else if(!hasMap && e.type.equals("map")){
        					
        					ret = new HashMap<object, HashMap<String, object>>();
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
        						ret.put((object)tempm.keySet().toArray()[k], tempv);
        					}
        					
        				}
        				else if(hasMap && (e==null || ( e!=null && !e.type.equals("map")))){
        					
        					for(int k=0; k<ret.size(); k++){
        						((HashMap<String, object>)ret.values().toArray()[k]).put(((assignStatement)s).name, e);
        					}
        				}
        				else if(hasMap && e.type.equals("map")){
        					HashMap<object, HashMap<String, object>> newret = new HashMap<object, HashMap<String, object>>();
        					HashMap<object, object> tempm = ((map)e).m;
        					HashMap<String, object> tempv;
        					HashMap<String, object> parent;
        					string condition;
        					
        					for(int k=0; k<ret.size(); k++){
        						parent = (HashMap<String,object>)ret.values().toArray()[k];
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
        							condition = new string("(" + ((string)tempm.keySet().toArray()[m]).s + " and " + ((string)ret.keySet().toArray()[k]).s + ")"); 
        							newret.put(condition, tempv);
        						}
        					}
        					ret = newret;
        				}
    					
    				}
    				
    				else if(((assignStatement)s).lhs != null && ((assignStatement)s).assignment!= null){ 
    					String name = ((assignStatement)s).lhs.tlname; //tuple or list name
    					
    					if(!hasMap && (e==null || ( e!=null && !e.type.equals("map")))){
        					if(knownVars.containsKey(name)){
        						object temptl = knownVars.get(name);
        						object x;
        						if(temptl.type.equals("tuple")){
        							x = ((assignStatement)s).lhs.indexes.get(0).eval_exe(knownVars, agentTemplate, this, existsVar, forallVar);
        							if(x.type.equals("number")){
        								((tuple)temptl).t.add(e);
        							}
        						}
        						else if(temptl.type.equals("list")){
        							object lhs = (list)temptl;
        							for(int k=0; k<((assignStatement)s).lhs.indexes.size(); k++){
            							x = ((assignStatement)s).lhs.indexes.get(k).eval_exe(knownVars, agentTemplate, this, existsVar, forallVar);
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
        					
        					ret = new HashMap<object, HashMap<String, object>>();
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
            							x = ((assignStatement)s).lhs.indexes.get(0).eval_exe(knownVars, agentTemplate, this, existsVar, forallVar);
            							if(x.type.equals("number")){
            								((tuple)temptl).t.add((object)tempm.values().toArray()[k]);
            							}
            						}
            				 /*****************************************************************************************/
            						else if(temptl.type.equals("list")){
            							object lhs = (list)temptl;
            							for(int m=0; m<((assignStatement)s).lhs.indexes.size(); m++){
                							x = ((assignStatement)s).lhs.indexes.get(m).eval_exe(knownVars, agentTemplate, this, existsVar, forallVar);
                							if(lhs.type.equals("tuple")){
                								if(((tuple)lhs).t.size() == ((int)((number)x).n+1)){
                									((tuple)lhs).t.remove((int)((number)x).n);
                									((tuple)lhs).t.add((int)((number)x).n, (object)tempm.values().toArray()[k]);
                								}
                									
                								else if(((tuple)lhs).t.size()> (int)((number)x).n)
                									lhs = ((tuple)lhs).t.get((int)((number)x).n);
                								else
                									((tuple)lhs).t.add((object)tempm.values().toArray()[k]);
            								}
            								else if(lhs.type.equals("list")){
            									if(((list)lhs).l.size()==((int)((number)x).n+1)){
            										((list)lhs).l.remove((int)((number)x).n);
            										((list)lhs).l.add((int)((number)x).n, (object)tempm.values().toArray()[k]);
            									}
            										
            									else if(((list)lhs).l.size()> (int)((number)x).n)
                									lhs = ((list)lhs).l.get((int)((number)x).n);
                								else
                									((list)lhs).l.add((object)tempm.values().toArray()[k]);
            								}
            								else{
            									System.out.println("error in fetch list.");
            								}
                						}
            						}
            				/*********************************************************************************************************/
                				}
        						ret.put((object)tempm.keySet().toArray()[k], tempv);
        					}
        					
        				}
        				else if(hasMap && (e==null || ( e!=null && !e.type.equals("map")))){
        					object temptl;
        					object x;
        					for(int k=0; k<ret.size(); k++){
        						temptl = ((HashMap<String, object>)ret.values().toArray()[k]).get(name);
        						if(temptl.type.equals("tuple")){
        							x = ((assignStatement)s).lhs.indexes.get(0).eval_exe((HashMap<String, object>)ret.values().toArray()[k], agentTemplate, this, existsVar, forallVar);
        							if(x.type.equals("number")){
        								((tuple)temptl).t.add(e);
        							}
        						}
        						else if(temptl.type.equals("list")){
        							object lhs = (list)temptl;
        							for(int m=0; m<((assignStatement)s).lhs.indexes.size(); m++){
            							x = ((assignStatement)s).lhs.indexes.get(m).eval_exe(knownVars, agentTemplate, this, existsVar, forallVar);
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
        					HashMap<object, HashMap<String, object>> newret = new HashMap<object, HashMap<String, object>>();
        					HashMap<object, object> tempm = ((map)e).m;
        					HashMap<String, object> tempv;
        					HashMap<String, object> parent;
        					string condition;
        					object temptl;
        					object x;
        					for(int k=0; k<ret.size(); k++){
        						parent = (HashMap<String,object>)ret.values().toArray()[k];
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
            							x = ((assignStatement)s).lhs.indexes.get(0).eval_exe((HashMap<String, object>)ret.values().toArray()[k], agentTemplate, this, existsVar, forallVar);
            							if(x.type.equals("number")){
            								((tuple)temptl).t.add(e);
            							}
            						}
        							else if(temptl.type.equals("list")){
            							object lhs = (list)temptl;
            							for(int n=0; n<((assignStatement)s).lhs.indexes.size(); n++){
                							x = ((assignStatement)s).lhs.indexes.get(n).eval_exe(knownVars, agentTemplate, this, existsVar, forallVar);
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
        							condition = new string("(" + ((string)tempm.keySet().toArray()[m]).s + " and " + ((string)ret.keySet().toArray()[k]).s + ")");
        							newret.put(condition, tempv);
        						}
        					}
        					ret = newret;
        				}
    					
    				}
    			}
    			else if(s.type.equals("assumeStatement")){ // ?
    				if(!hasMap){
    					hasMap = true;
    					ret = new HashMap<object, HashMap<String, object>>();
    					e = ((assumeStatement)s).e.eval_exe(knownVars, agentTemplate, this, existsVar, forallVar);
    					if(!e.type.equals("map")){
    						ret.put(e, knownVars);
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
								string condition = new string("(" + ((string)((map)e).m.keySet().toArray()[k]).s + " and " + ((string)((map)e).m.values().toArray()[k]).s + ")"); 
								ret.put(condition, tempv);  
							}
    						
    					}
    					
    				}
    				else{
						boolean returnMap = false;
						HashMap<object, HashMap<String, object>> newret=null;
						
						for(int k=0; k< ret.size();k++){
							
							e = ((assumeStatement)s).e.eval_exe(((HashMap<String, object>) ret.values().toArray()[k]), agentTemplate,this, existsVar, forallVar);
							string condition = null;
	    					if(!e.type.equals("map")){
	    						condition = new string("(" + ((string)ret.keySet().toArray()[k]).s + " and " + ((string)e).s + ")");
	    						ret.put(condition, ((HashMap<String, object>)ret.values().toArray()[k]));
	    						ret.remove((object)ret.keySet().toArray()[k]);
	    					}
	    					
	    					else if(e.type.equals("map")){
	    						if(!returnMap)
	    							returnMap = true;
								newret = new HashMap<object, HashMap<String,object>>();
    							HashMap<String,object> addv;
    							HashMap<String, object> tempv;
        						for(int m=0; m<((map)e).m.size(); m++){
    								addv = new HashMap<String,object>();
    								tempv = (HashMap<String, object>)ret.values().toArray()[k]; 
    								for(int n=0; n<tempv.size(); n++){
    									if(((object)tempv.values().toArray()[n]).type.equals("tuple"))
            								addv.put((String)tempv.keySet().toArray()[n], ((tuple)tempv.values().toArray()[n]).copy());
            							else if(((object)tempv.values().toArray()[n]).type.equals("list"))
            								addv.put((String)tempv.keySet().toArray()[n], ((list)tempv.values().toArray()[n]).copy());
            							else
            								addv.put((String)tempv.keySet().toArray()[n], (object)tempv.values().toArray()[n]);
    								}
    								condition = new string("(("+((string)ret.keySet().toArray()[k]).s + " and " + ((string)((map)e).m.keySet().toArray()[k]).s 
    										+ ") and (" + ((string)((map)e).m.values().toArray()[k]).s+"))");
        							newret.put(condition, addv);
    							}
							}
						}
						if(returnMap)
							ret = newret;
    				}
    			} 				
    			else{
    				System.out.println("error");
    			}
    		}
    		
    		if(hasMap){
    			for(int j=0; j<ret.size(); j++){
    				if(((HashMap<String, object>)ret.values().toArray()[j]).containsKey(this.output.name) &&
    						((HashMap<String, object>)ret.values().toArray()[j]).get(this.output.name)!=null){	
    					// add input assumption
    					out.put((object)ret.keySet().toArray()[j], ((HashMap<String, object>)ret.values().toArray()[j]).get(this.output.name));
    				}
    			}
    		}
    		else{
    			if(knownVars.containsKey(this.output.name) && (knownVars.get(this.output.name)!=null)){
    				out.put(new string("true"), knownVars.get(this.output.name));
    			}
    		}
    	}

		
	
	
		if( out.size() == 1 && ((object)out.keySet().toArray()[0]).type.equals("string") && ((string)out.keySet().toArray()[0]).s.equals("true"))
			return (object)out.values().toArray()[0];
		else
			return new map(out);
    }
    
   
}
    
  
   

