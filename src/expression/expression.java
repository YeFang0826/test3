package expression;

import java.util.ArrayList;
import java.util.HashMap;

import accessField.accessField;
import agent.agentTemplate;

import deparen.deparen;
import execute.executeCode;
import functionCall.*;
import statement.defineFunStatement;
import term.*;
import object.*;
public class expression {
	
		public String op;
		public expression left;
		public expression right;
		public term leaf;
		
		
		public String type;
		
		public expression(term t){
			this.leaf = t;
		}
		
		public expression(expression t, String op){
			this.op = op;
			this.right = t;
		}
		
		public expression(expression left, expression right, String op){
			this.left = left;
			this.right = right;
			this.op = op;
		}
		public object eval_agent_expr(HashMap<String, object> knownVar){ // no hole in agents
			object ret = null;		
			if(this.leaf!=null){
				if(this.leaf.type.equals("var")){
					if(knownVar.containsKey(((var)this.leaf).s))
						ret = knownVar.get(((var)this.leaf).s);
					else
						ret = new string(((var)this.leaf).s);
				}
				else if(this.leaf.type.equals("num")){
					ret = new number(((num)this.leaf).value);
				}
				else if(this.leaf.type.equals("expression")){
					ret = (((expressionT)this.leaf).expr).eval_agent_expr(knownVar);
				}
				else if(this.leaf.type.equals("tlaccess")){
					ret = ((tl)this.leaf).access_in_agent(knownVar);
				}
				else if(this.leaf.type.equals("newVar")){
					ret = ((newVar)this.leaf).eval_in_agent(knownVar);
				}
				else if(this.leaf.type.equals("tupleCall") || this.leaf.type.equals("listCall")){
					
					functionCall call = ((functionCallT)this.leaf).call;
					ArrayList<expression> inExpr = ((functionCallT)this.leaf).call.inputs;
					ArrayList<object> inValue = new ArrayList<object>();
					
					for(int i=0; i< inExpr.size(); i++){ 
						inValue.add(inExpr.get(i).eval_agent_expr(knownVar));
					}
					
					
					boolean hasMap=false;
					ArrayList<object> inputList = new ArrayList<object>();
					
					for(int i=0; i< inValue.size(); i++){
						if(!hasMap && !inValue.get(i).type.equals("map")) 
							inputList.add(inValue.get(i));
						else{
							System.out.println("no return value of type map is allowed in agentTemplate");
						}
					}
					
					object y = null;
					
					if(call.type.equals("tupleCall")){
						// the field in tuple must have type of String (primitive)
						if(((tupleCall)call).method.equals("construct")){
							y=((tupleCall)call).construct(inputList);
						}
						else if(((tupleCall)call).method.equals("size")){
							int size = ((tupleCall)call).size(inputList.get(0), knownVar);
							y= new number(size);
						}
						else{
							System.out.println("error");
							return null;
							}
						}
						
					else if(call.type.equals("listCall")){
						//field in list can be tuple or primitive
						if(((listCall)call).method.equals("construct")){
							y = ((listCall)call).construct(inputList);
						}
						else if(((listCall)call).method.equals("length")){
							y = new number(((listCall)call).length(inputList.get(0), knownVar));
						}
								//insert-sorted
						else if(((listCall)call).method.equals("insert-sorted")){
							y =  ((listCall)call).InsertSorted(inputList);	
						}
						else{
							System.out.println("error");
							return null;
						}
					}
					return y;
				}
			}
			else if (this.left!=null && !this.op.equals("") && this.right!=null){
				object l = this.left.eval_agent_expr(knownVar);
				object r = this.right.eval_agent_expr(knownVar);
				String le=null;
				String re=null;
				
				if((!l.type.equals("map") && !r.type.equals("map"))){
					if(l.type.equals("string"))
						le = ((string)l).s;
					else if(l.type.equals("number"))
						le = Double.toString(((number)l).n);
					
					if(right.type.equals("string"))
						re = ((string)r).s;
					else if(right.type.equals("number"))
						re = Double.toString(((number)r).n);
					
					if(this.op.equals("==") && le.equals(re))
						return new string("true");
					else if(this.op.equals("==") && !le.equals(re))
						return new string("false");
					else if((le.equals("true")||le.equals("true")) && (re.equals("true")||re.equals("true"))){
						if(this.op.equals("and") && le.equals("true") && re.equals("true"))
							return new string("true");
						else
							return new string("false");
					}
					else{
						return new string("("+le + " " + this.op + " " + re+")");
					}
				}
			}
			else if(this.left==null && !this.op.equals("") && this.right!=null){
				object r=this.right.eval_agent_expr(knownVar);
					
				if(r.type.equals("string") && ((string)r).s.equals("true"))
					return new string("false");
				else if(r.type.equals("string") && ((string)r).s.equals("false"))
					return new string("true");
				else if(r.type.equals("string"))
					return new string("(not "+((string)r).s+")");
			}
			return ret;
		}
	
		
		public expression eval_0(HashMap<String, expression> globalVar){ //replace all the global constants
			expression ret = null;
			
			if(this.leaf!=null){
				if(this.leaf.type.equals("var")){
					if(globalVar.containsKey(((var)this.leaf).s) && globalVar.get(((var)this.leaf).s)!= null){
						ret = globalVar.get(((var)this.leaf).s);
					}
					else
						return this;
				}
				else if(this.leaf.type.equals("num")){
					return this;
				}
				else if(this.leaf.type.equals("agent")){
					ret = new expression(((agentT)this.leaf).eval_0(globalVar));
					return ret;
				}
				else if(this.leaf.type.equals("hole")){
					ret  = new expression(((holeT)this.leaf).eval_0(globalVar));
					return ret;
				}
				else if(this.leaf.type.equals("newVar")){
					ret = new expression(((newVar)this.leaf).eval_0(globalVar));
					return ret;
				}
				else if(this.leaf.type.equals("accessField")){
					ret = new expression(new accessT(((accessT)this.leaf).af.eval_0(globalVar)));
					return ret;
				}
				else if(this.leaf.type.equals("tlaccess")){
					ret = new expression(((tl)(this.leaf)).eval_0(globalVar));
					return ret;
				}
				else if(this.leaf.type.equals("setExclude")){
					ret = new expression(((setExclude)this.leaf).eval_0(globalVar));
					return ret;
				}
				else if(this.leaf.type.equals("hole")){
					ret = new expression(((holeT)this.leaf).eval_0(globalVar));
					return ret;
				}
				else if(this.leaf.type.equals("sortedList")){
					ret = new expression(((sortedList)this.leaf).eval_0(globalVar));
					return ret;
				}
				else if(this.leaf.type.equals("indirect") || this.leaf.type.equals("direct") || this.leaf.type.equals("tupleCall") || this.leaf.type.equals("listCall")){
					if(this.leaf.type.equals("indirect")){
						ret = ((indirectCall)((functionCallT)this.leaf).call).eval_0(globalVar);
					}
					else if(this.leaf.type.equals("direct")){
						ret = ((directCall)((functionCallT)this.leaf).call).eval_0(globalVar);
					}
					else if(this.leaf.type.equals("tupleCall")){
						ret = new expression( new functionCallT(((tupleCall)((functionCallT)this.leaf).call).eval_0(globalVar)));
					}
					else if(this.leaf.type.equals("listCall")){
						ret = new expression( new functionCallT(((listCall)((functionCallT)this.leaf).call).eval_0(globalVar)));
					}
					return ret;
				}
				else 
					return this;
			}
			else if (this.left!=null && !this.op.equals("") && this.right!=null){
				expression l = this.left.eval_0(globalVar);
				expression r = this.right.eval_0(globalVar);
				if(l.leaf!=null && l.leaf.type.equals("num") && r.leaf!=null && r.leaf.type.equals("num") ){ // no div operation
					
					double d=0;
					
					if(this.op.equals("+")){
						d = ((num)l.leaf).value + ((num)r.leaf).value;
						return new expression(new num(d));
					}
					else if(this.op.equals("-")){
						d = ((num)l.leaf).value - ((num)r.leaf).value;
						return new expression(new num(d));
					}
					else if(this.op.equals("*")){
						d = ((num)l.leaf).value * ((num)r.leaf).value;
						return new expression(new num(d));
					}
				}
				
				return new expression(this.left.eval_0(globalVar), this.right.eval_0(globalVar), this.op);
				
			}
			else if (this.right!=null && !this.op.equals("")){
				return new expression(this.right.eval_0(globalVar),this.op);
			}
			
			return ret;
		}
	/*	
		public expression eval_1(String index, int value){ // replace index with real value in forloop
			if(this.leaf!=null){ // access field, functioncall, var, setExclude 
				double test;
				if(this.leaf.type.equals("var")){
					if(((var)this.leaf).s.equals(index)){
						//System.out.println(((var)this.leaf).s + " " + value);
						return new expression(new num(value));
					}
				}
				else if(this.leaf.type.equals("accessField")){
					if(((accessT)this.leaf).af.objectl!=null){
						return new expression(new accessT(((accessT)this.leaf).af.eval_1(index, value)));
					}
					else
						return this;
				}
				else if(this.leaf.type.equals("tupleCall")){
					return new expression(new functionCallT(((tupleCall)((functionCallT)this.leaf).call).eval_1(index, value)));
				}
				else if(this.leaf.type.equals("listCall")){  // result = result + p[][]
					return new expression(new functionCallT(((listCall)(((functionCallT)this.leaf).call)).eval_1(index, value)));
				}
				else if(this.leaf.type.equals("direct")){
					return ((directCall)(((functionCallT)this.leaf).call)).eval_1(index, value);
				}
				else if(this.leaf.type.equals("indirect")){
					return ((indirectCall)((functionCallT)this.leaf).call).eval_1(index, value);
				}
				else if(this.leaf.type.equals("agent")){
					return ((agentT)this.leaf).eval_1(index, value);
				}
				else if(this.leaf.type.equals("tlaccess")){
					return new expression(((tl)this.leaf).eval_1(index, value));
				}
				else if(this.leaf.type.equals("setExclude")){
					return new expression(((setExclude)this.leaf).eval_1(index, value));
				}
				else if(this.leaf.type.equals("sortedList")){
					return new expression(((sortedList)this.leaf).eval_1(index, value));
				}
				else if(this.leaf.type.equals("hole")){
					return new expression(((holeT)this.leaf).eval_1(index, value));
				}
				else if(this.leaf.type.equals("newVar")){
					return new expression(((newVar)this.leaf).eval_1(index, value));
				}
				
				return this;
			}
			else if(this.left!=null && !this.op.equals("") && this.right!=null){
				expression l = this.left.eval_1(index, value);
				expression r = this.right.eval_1(index, value);
				return new expression(l,r, this.op);
			}
			else if(this.right!=null && !this.op.equals("")){
				return new expression(this.right.eval_1(index, value), this.op);
				
			}
			else{
				return this;
			}
			
		}
		*/
		
		public object eval_exe(HashMap<String, object> knownVars, HashMap<String, agentTemplate> agentTemplate,
				defineFunStatement mechanism, ArrayList<String> existsVar, ArrayList<String> forallVar){
			object ret = null;			
			if(this.leaf!=null){
				if(this.leaf.type.equals("var")){
					if(knownVars.containsKey(((var)this.leaf).s))
						ret = knownVars.get(((var)this.leaf).s);
					else
						ret = new string(((var)this.leaf).s);
				}
				else if(this.leaf.type.equals("num")){
					ret = new number(((num)this.leaf).value);
				}
				else if(this.leaf.type.equals("expression")){
					ret = (((expressionT)this.leaf).expr).eval_exe(knownVars, agentTemplate,mechanism, existsVar, forallVar);
				}
				else if(this.leaf.type.equals("tlaccess")){
					ret = ((tl)this.leaf).access(knownVars, agentTemplate, mechanism, existsVar, forallVar);
				}
				else if(this.leaf.type.equals("agent")){
					if(agentTemplate.containsKey(((agentT)this.leaf).atype)){
						ret = ((agentT)this.leaf).exe(agentTemplate, knownVars, mechanism, existsVar, forallVar);
					}
				}
				else if(this.leaf.type.equals("hole")){
					ret =((holeT)this.leaf).eval_exe(knownVars, agentTemplate, mechanism, existsVar, forallVar);
				}
				else if(this.leaf.type.equals("setExclude")){
					ret = ((setExclude)this.leaf).eval_exe(knownVars, agentTemplate,mechanism, existsVar, forallVar);
				}
				else if(this.leaf.type.equals("sortedList")){
					ret = new string(((sortedList)this.leaf).eval_exe(knownVars, agentTemplate,mechanism, existsVar, forallVar));
				}
				else if(this.leaf.type.equals("accessField")){
					ret = ((accessT)this.leaf).af.exe(knownVars, agentTemplate, mechanism, existsVar, forallVar);
				}
				else if(this.leaf.type.equals("direct")|| this.leaf.type.equals("indirect") || this.leaf.type.equals("tupleCall") 
						|| this.leaf.type.equals("listCall")){
					
					
					functionCall call = ((functionCallT)this.leaf).call;
					ArrayList<expression> inExpr = ((functionCallT)this.leaf).call.inputs;
					ArrayList<object> inValue = new ArrayList<object>();
					
					for(int i=0; i< inExpr.size(); i++){ 
						inValue.add(inExpr.get(i).eval_exe(knownVars, agentTemplate, mechanism, existsVar, forallVar));
					}
					
					
					boolean hasMap=false;
					ArrayList<object> inputList = new ArrayList<object>();
					HashMap<object, ArrayList<object>> inputMap = new HashMap<object, ArrayList<object>>(); // assume expression, functionCall(inputs)
					
					
					for(int i=0; i< inValue.size(); i++){
						if(!hasMap && !inValue.get(i).type.equals("map")) 
							inputList.add(inValue.get(i));
						else if(inValue.get(i).type.equals("map") && !hasMap){
							hasMap=true;
							ArrayList<object> tempList;
							
								for(int j=0; j< ((map)inValue.get(i)).m.size(); j++){
									tempList = new ArrayList<object>();
									for(int k=0; k<inputList.size(); k++){
										tempList.add(inputList.get(k));
									}
									tempList.add((object) ((map)inValue.get(i)).m.values().toArray()[j]);
									inputMap.put((object) ((map)inValue.get(i)).m.keySet().toArray()[j], tempList);
								}
						}
						else if(inValue.get(i).type.equals("map") && hasMap){

							ArrayList<object> tempList;
							HashMap<object, ArrayList<object>> tempMap = new HashMap<object, ArrayList<object>>();
							String condition = null;
							for(int j=0; j< inputMap.size(); j++){
								for(int k=0; k< ((map)inValue.get(i)).m.size(); k++){
										 tempList = new ArrayList<object>();
										 
									for(int m=0; m< ((ArrayList<object>)inputMap.values().toArray()[j]).size(); m++){
										tempList.add(((ArrayList<object>)inputMap.values().toArray()[j]).get(m));
									}
									tempList.add((object) ((map)inValue.get(i)).m.values().toArray()[k]);
									condition = ((string)inputMap.keySet().toArray()[j]).s + " and "+ ((string)((map)inValue.get(i)).m.keySet().toArray()[k]).s;
									tempMap.put(new string(condition), tempList);
								}
							}
							
							inputMap = tempMap;
						}
						else if(!inValue.get(i).type.equals("map") && hasMap){
							for(int j=0; j< inputMap.size(); j++){
								((ArrayList<object>)inputMap.values().toArray()[j]).add(inValue.get(i));
							}
						}
					}
					
					if(!hasMap){
						inputMap.put(new string("true"), inputList);
					}
					
					HashMap<object, object> outputMap = new HashMap<object, object>();
					ArrayList<object> in;
					object y = null;
					for(int i=0; i<inputMap.size(); i++){
						in = (ArrayList<object>) inputMap.values().toArray()[i];
					 	if(call.type.equals("tupleCall")){
							// the field in tuple must have type of String (primitive)
								if(((tupleCall)call).method.equals("construct")){
									y=((tupleCall)call).construct(in);
								}
								else if(((tupleCall)call).method.equals("size")){
									int size = ((tupleCall)call).size(in.get(0), knownVars);
									y= new number(size);
								}
								else{
									System.out.println("error");
									return null;
								}
							}
						
							else if(call.type.equals("listCall")){
							//field in list can be tuple or primitive
								if(((listCall)call).method.equals("construct")){
									y = ((listCall)call).construct(in);
								}
								else if(((listCall)call).method.equals("length")){
									y = new number(((listCall)call).length(in.get(0), knownVars));
								}
								//insert-sorted
								else if(((listCall)call).method.equals("insert-sorted")){
									y =  ((listCall)call).InsertSorted(in);
									
								}
								else{
									System.out.println("error");
									return null;
								}
							}
							else if(call.type.equals("direct")){
								if(((directCall)call).name.equals(mechanism.name))
									y = mechanism.eval_exe(agentTemplate, mechanism, (ArrayList<object>)inputMap.values().toArray()[i], existsVar, forallVar);
							}
							else if(call.type.equals("indirect")){
								 object function = ((indirectCall)call).af.exe(knownVars, agentTemplate, mechanism, existsVar, forallVar);
								 if(function.type.equals("function")){
									 y = ((functionO)function).fun.eval_exe(agentTemplate, mechanism, in, existsVar, forallVar);
								 }
								
							}
					 	if(y.type.equals("map")){
								for(int j=0; j<((map)y).m.size(); j++){
									//System.out.println("test point: " + ((string)inputMap.keySet().toArray()[i]).s.equals("true"));
									if((((string)inputMap.keySet().toArray()[i]).s.equals("true") || ((string)inputMap.keySet().toArray()[i]).s.equals("false")) &&
											(((string)((map)y).m.keySet().toArray()[j]).s.equals("true") || ((string)((map)y).m.keySet().toArray()[j]).s.equals("false"))){
										if(((string)inputMap.keySet().toArray()[i]).s.equals("true") && ((string)((map)y).m.keySet().toArray()[j]).s.equals("true"))
											outputMap.put(new string("true"), (object)((map)y).m.values().toArray()[j]);
										else
											outputMap.put(new string("false"), (object)((map)y).m.values().toArray()[j]);
											
									}
									else if(((string)((map)y).m.keySet().toArray()[j]).s.equals("true") && !((string)inputMap.keySet().toArray()[i]).s.equals("true"))
										outputMap.put((string)inputMap.keySet().toArray()[i],(object)((map)y).m.values().toArray()[j]);
										
									else if(!((string)((map)y).m.keySet().toArray()[j]).s.equals("true") && ((string)inputMap.keySet().toArray()[i]).s.equals("true"))
										outputMap.put((object)((map)y).m.keySet().toArray()[j],(object)((map)y).m.values().toArray()[j]);
										
									else if(((string)((map)y).m.keySet().toArray()[j]).s.equals(((string)inputMap.keySet().toArray()[i]).s))
										outputMap.put((string)inputMap.keySet().toArray()[i],(object)((map)y).m.values().toArray()[j]);
										
									else if(!((string)((map)y).m.keySet().toArray()[j]).s.equals("false") && !((string)inputMap.keySet().toArray()[i]).s.equals("false"))
										outputMap.put(new string("("+((string)inputMap.keySet().toArray()[i]).s + " and " + ((string)((map)y).m.keySet().toArray()[j]).s+")"),
												(object)((map)y).m.values().toArray()[j]);
								}
					 		}
					 		else{
					 			outputMap.put((object)inputMap.keySet().toArray()[i], y);
					 		}
					 	
						}
					
						if(outputMap.size()==1 && (((string)outputMap.keySet().toArray()[0]).s.equals("true") ||((string)outputMap.keySet().toArray()[0]).s.equals("false")))
							return (object)outputMap.values().toArray()[0];
						else
							return new map(outputMap);
					}
			
			}
			
			else if(this.left!=null && this.right != null){
				object left = this.left.eval_exe(knownVars, agentTemplate, mechanism, existsVar, forallVar);
				object right = this.right.eval_exe(knownVars, agentTemplate, mechanism, existsVar, forallVar);
				String le=null;
				String re=null;
				
				if(!left.type.equals("map") && !right.type.equals("map")){
					
					if(left.type.equals("string"))
						le = ((string)left).s;
					else if(left.type.equals("number"))
						le = Double.toString(((number)left).n);
					
					if(right.type.equals("string"))
						re = ((string)right).s;
					else if(right.type.equals("number"))
						re = Double.toString(((number)right).n);
					
					if(this.op.equals("==") && le.equals(re))
						return new string("true");
					else if(this.op.equals("==") && !le.equals(re))
						return new string("false");
					else if((le.equals("true")||le.equals("false")) && (re.equals("true")||re.equals("false"))){
						if(this.op.equals("and") && le.equals("true") && re.equals("true"))
							return new string("true");
						else
							return new string("false");
					}
					else{
						deparen retString = new deparen(le, re, this.op);
						return new string(retString.print());
					}
				}
				
				else if(left.type.equals("map")){
					HashMap<object, object> retMap = new HashMap<object, object>();
					object condition1;
					object tempM1;
					String c = null;
					for(int x=0; x< ((map)left).m.size(); x++){
						condition1 = (object) ((map)left).m.keySet().toArray()[x];
						tempM1 = (object) ((map)left).m.values().toArray()[x];
						
							if(right.type.equals("map")){
								object condition2;
								object tempM2;
									
									for(int y=0; y< ((map)right).m.size(); y++){
										condition2 = (object) ((map)right).m.keySet().toArray()[y];
										tempM2 = (object) ((map)right).m.values().toArray()[y];
									
										if((!tempM1.type.equals("map") && !tempM2.type.equals("map"))){
											
											if(tempM1.type.equals("string"))
												le = ((string)tempM1).s;
											else if(tempM1.type.equals("number"))
												le = Double.toString(((number)tempM1).n);
											
											if(tempM2.type.equals("string"))
												re = ((string)tempM2).s;
											else if(tempM2.type.equals("number"))
												re = Double.toString(((number)tempM2).n);
											
											if((((string)condition1).s.equals("true") || ((string)condition1).s.equals("false")) 
													&& (((string)condition2).s.equals("true") || ((string)condition2).s.equals("false")) ){
												if(((string)condition1).s.equals("true") && ((string)condition2).s.equals("true"))
														c ="true";
												else
													c="false";
											}
											else if(((string)condition1).s.equals("true") && !((string)condition2).s.equals("true"))
												c = ((string)condition2).s;
											
											else if(!((string)condition1).s.equals("true") && ((string)condition2).s.equals("true"))
												c = ((string)condition1).s;
											
											else if(((string)condition1).s.equals(((string)condition2).s))
												c = ((string)condition1).s;
											
											else if(!((string)condition1).s.equals("false") && !((string)condition2).s.equals("false"))
												c = "("+((string)condition1).s + " and "+((string)condition2).s+")";
											else 
												c = "false";
											
											
											if(this.op.equals("==") && le.equals(re))
												retMap.put(new string(c), new string("true"));
											else if(this.op.equals("==") && !le.equals(re))
												retMap.put(new string(c), new string("false"));
											else if((le.equals("true")||le.equals("true")) && (re.equals("true")||re.equals("true"))){
												if(this.op.equals("and") && le.equals("true") && re.equals("true"))
													retMap.put(new string(c), new string("true"));
												else
													retMap.put(new string(c), new string("false"));
											}
											else if(c!=null && !c.equals("false")){
												if(this.op.equals("and"))
													retMap.put(new string(c), new string("("+le + " " + this.op + " " + re+")"));
												else
													retMap.put(new string(c), new string("("+le + this.op + re+")"));
											}
										}
										else{
											System.out.println("error");
											return null;
										}
								}
							}
							else{
								if((!tempM1.type.equals("map") && !right.type.equals("map"))){
										
										if(tempM1.type.equals("string"))
											le = ((string)tempM1).s;
										else if(tempM1.type.equals("number"))
											le = Double.toString(((number)tempM1).n);
										
										if(right.type.equals("string"))
											re = ((string)right).s;
										else if(right.type.equals("number"))
											re = Double.toString(((number)right).n);
										
										if(this.op.equals("==") && le.equals(re))
											retMap.put(condition1, new string("true"));
										else if(this.op.equals("==") && !le.equals(re))
											retMap.put(condition1, new string("false"));
										else if((le.equals("true")||le.equals("true")) && (re.equals("true")||re.equals("true"))){
											if(this.op.equals("and") && le.equals("true") && re.equals("true"))
												retMap.put(condition1, new string("true"));
											else
												retMap.put(condition1, new string("false"));
										}
										else{
											if(this.op.equals("and"))
												retMap.put(condition1, new string("("+le + " " + this.op + " " + re+")"));
											else
												retMap.put(condition1, new string("("+le + this.op + re+")"));
										}
								}
								else{
									System.out.println("error");
									return null;
								}
						}
					}
					return new map(retMap);
				}
						
					
				else if (!left.type.equals("map") && right.type.equals("map")){
					HashMap<object, object> retMap = new HashMap<object, object>();
					object condition1;
					object tempM1;
				
					for(int x=0; x< ((map)right).m.size(); x++){
						condition1 = (object) ((map)right).m.keySet().toArray()[x];
						tempM1 = (object) ((map)right).m.values().toArray()[x];
						
						if((!tempM1.type.equals("map") && !left.type.equals("map"))){
							
							if(tempM1.type.equals("string"))
								re = ((string)tempM1).s;
							else if(tempM1.type.equals("number"))
								re = Double.toString(((number)tempM1).n);
							
							if(left.type.equals("string"))
								le = ((string)left).s;
							else if(right.type.equals("number"))
								le = Double.toString(((number)left).n);
							
							if(this.op.equals("==") && le.equals(re))
								retMap.put(condition1, new string("true"));
							else if(this.op.equals("==") && !le.equals(re))
								retMap.put(condition1, new string("false"));
							else if((le.equals("true")||le.equals("true")) && (re.equals("true")||re.equals("true"))){
								if(this.op.equals("and") && le.equals("true") && re.equals("true"))
									retMap.put(condition1, new string("true"));
								else
									retMap.put(condition1, new string("false"));
							}
							else{
								if(this.op.equals("and"))
									retMap.put(condition1, new string("("+le + " " + this.op + " " + re+")"));
								else
									retMap.put(condition1, new string("("+le + this.op + re+")"));
							}
						
						}
						else{
							System.out.println("error");
							return null;
						}
					}

					return new map(retMap);
				}
				
				else{
					System.out.println("error");
					return null;
				}
			}
			else if (this.left == null && this.right!=null && this.op.equals("~")){
				//System.out.println("In expression evaluation: not op");
				
				object r=this.right.eval_exe(knownVars, agentTemplate, mechanism, existsVar, forallVar);
				
				if(r.type.equals("string") && ((string)r).s.equals("true"))
					return new string("false");
				else if(r.type.equals("string") && ((string)r).s.equals("false"))
					return new string("true");
				else if(r.type.equals("string"))
					return new string("(not "+((string)r).s+")");
			
				else if(r.type.equals("map")){
					object rx = null;
					for(int i=0; i<((map)r).m.size();i++){
						if(((object)((map)r).m.values().toArray()[i]).type.equals("string"))
							rx = (object) ((map)r).m.values().toArray()[i];
						if(rx.type.equals("string") && ((string)rx).s.equals("true"))
							((map)r).m.values().toArray()[i] = new string("false");
						else if(rx.type.equals("string") && ((string)rx).s.equals("false"))
							((map)r).m.values().toArray()[i] =  new string("true");
						else if(rx.type.equals("string"))
							((map)r).m.values().toArray()[i] = new string("(not "+((string)rx).s+")");
						else{
							System.out.println("negation can only apply to expressions");
							return null;
						}
					}
					return r;
				}
				else{
					System.out.println("negation can only apply to expressions");
					return null;
				}
			}
			return ret;
		}
		
}


				