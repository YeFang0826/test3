package statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.text.html.HTMLDocument.Iterator;

import expression.*;

public class ifStatement extends statement{
	
	public ArrayList<expression> ifs;
	public ArrayList<ArrayList<statement>> thens;
	public ArrayList<statement> elses;
	public ArrayList<ArrayList<statement>> bp; 
	
	public ifStatement(){
		this.type="ifStatement";
		this.ifs = new ArrayList<expression>(1);
		this.thens = new ArrayList<ArrayList<statement>>(1);
		this.bp = new ArrayList<ArrayList<statement>>();
	}
	
	public void addCondition(expression c){
		this.ifs.add(c);
	}
	public void addConsq(ArrayList<statement> consq){
		this.thens.add(consq);
	}
	public void addElse(ArrayList<statement> e){
		this.elses = e;
	}
	
	public void eval_0(HashMap<String, expression> globalVar){
		ArrayList<expression> newl = new ArrayList<expression>();
		expression tempe;
		
		for(int i=0; i<ifs.size(); i++){
			 tempe = this.ifs.get(i).eval_0(globalVar);
			 newl.add(tempe);
		}
		if(this.ifs.size()>0){
			this.ifs = newl;
		}
		for(int i =0; i<this.thens.size(); i++){
			for(int j=0; j<this.thens.get(i).size(); j++){
				this.thens.get(i).get(j).eval_0(globalVar);
			}
		}
		for(int i=0; i<this.elses.size(); i++){
			this.elses.get(i).eval_0(globalVar);
		}
	}
	
	public void replace_hole(String iterator){
		statement s;
		for(int i=0; i<this.thens.size(); i++){
			for(int j=0; j<this.thens.get(i).size(); j++){
				s = this.thens.get(i).get(j);
				if(s.type.equals("defVarStatement") && ((defVarStatement)s).init!=null)
					((defVarStatement)s).init = ((defVarStatement)s).init.replace_hole(iterator);
				else if(s.type.equals("assignStatement") && ((assignStatement)s).assignment!=null)
					((assignStatement)s).assignment = ((assignStatement)s).assignment.replace_hole(iterator);
				else if(s.type.equals("ifStatement")){
					((ifStatement)s).replace_hole(iterator);
				}
				else if(s.type.equals("forStatement")){
					((forStatement)s).replace_hole();
					statement s1;
					for(int k=0; k<((forStatement)s).body.size();k++){
						s1 = ((forStatement)s).body.get(k);
						if(s1.type.equals("defVarStatement") && ((defVarStatement)s1).init!=null)
							((defVarStatement)s1).init = ((defVarStatement)s1).init.replace_hole(iterator);
						else if(s1.type.equals("assignStatement") && ((assignStatement)s1).assignment!=null)
							((assignStatement)s1).assignment = ((assignStatement)s1).assignment.replace_hole(iterator);
						else if(s1.type.equals("ifStatement")){
							((ifStatement)s1).replace_hole(iterator);
						}
					}
				}
				else{
					System.out.println("statement not supported in ifStatement!");
				}
			}
		}
	
	}
	
	public ArrayList<ArrayList<statement>> basicPath(HashMap<String, expression> globalVar){
		ArrayList<statement> l;
		ArrayList<statement> templ;
		ArrayList<ArrayList<statement>> templl = null;
		ArrayList<ArrayList<statement>> tempRet =null;
		ArrayList<ArrayList<statement>> ret = new ArrayList<ArrayList<statement>>();
		boolean hasIf=false;
		expression notAll=null;
		ArrayList<statement> temp;
		statement s;
		
		if(this.elses.size()>0){
			for(int i=0; i< ifs.size(); i++){
				if(i==0)
					notAll = new expression(ifs.get(i), "~");
				else
					notAll = new expression(notAll, new expression(ifs.get(i), "~"), "and");
			}
			
		   this.ifs.add(notAll);
		   this.thens.add(this.elses);
		}
		
		for(int i=0; i<ifs.size(); i++){
			tempRet = null;
			temp= thens.get(i);
			hasIf = false;
			l = new ArrayList<statement>();
			l.add(new assumeStatement(ifs.get(i)));
			
			for(int j=0; j<temp.size(); j++){
				s = temp.get(j);
				if(!hasIf && !s.type.equals("ifStatement")){
					if(!s.type.equals("forStatement"))
						l.add(s);
					else{
						((forStatement)s).replace_hole();
						ArrayList<statement> temps = ((forStatement)s).unroll(globalVar);
						if(temps!=null){
							for(int r=0; r<temps.size(); r++)
								l.add(temps.get(r));
						}
					}
				}
				else if (!hasIf && s.type.equals("ifStatement")){
					hasIf = true;
					templl = new ArrayList<ArrayList<statement>>();
					ArrayList<ArrayList<statement>> tempret = ((ifStatement)s).basicPath(globalVar);
					for(int k=0; k<tempret.size(); k++){
						templ = new ArrayList<statement>();
						for(int m=0; m<l.size();m++){
							templ.add(l.get(m));
						}
						for(int m=0; m<tempret.get(k).size(); m++){
							templ.add(tempret.get(k).get(m));
						}
						templl.add(templ);
					}
					tempRet = templl;
				}
				else if(hasIf && !s.type.equals("ifStatement")){
					for(int k=0; k<tempRet.size();k++){
						if(!s.type.equals("forStatement"))
							tempRet.get(k).add(s);
						else{
							((forStatement)s).replace_hole();
							ArrayList<statement> temps = ((forStatement)s).unroll(globalVar);
							if(temps!=null){
								for(int r=0; r<temps.size(); r++)
									tempRet.get(k).add(temps.get(r));
							}
						}
					}
				}
				else {
					templl = new ArrayList<ArrayList<statement>>();
					ArrayList<ArrayList<statement>> tempret;
					for(int k=0; k<ret.size(); k++){
						tempret = ((ifStatement)s).basicPath(globalVar);
						for(int m=0; m<tempret.size(); k++){
							templ = new ArrayList<statement>();
							for(int n=0; n<tempRet.get(k).size();n++){
								templ.add(tempRet.get(k).get(n));
							}
							for(int n=0; n<tempret.get(k).size(); n++){
								templ.add(tempret.get(k).get(n));
							}
							templl.add(templ);
						}
					}
					tempRet = templl;
				}
			}
			if(tempRet!=null){
				for(int j=0; j<tempRet.size(); j++){
					ret.add(tempRet.get(j));
				}
			}
			else{
				ret.add(l);
			}
		}
		return ret;
	}
}

	
