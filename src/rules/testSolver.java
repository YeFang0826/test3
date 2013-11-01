package rules;

import java.util.*;
import java.io.*;
import java.lang.*;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;

import program.program;

import com.microsoft.z3.*;
//f = exist{},forall{},f



public class testSolver {
   public static void main(String args[]) throws IOException, Z3Exception, RecognitionException{

	// the input formula is an intermediate result, so we can assume that there is no grammar in the input formula
	// 1.(F) 2.Spaces only between and|or|implies|not 3.each singlton needs to be in parentheses. 
	   
	   Scanner sc= new Scanner(new File("src/input_vcg.txt"));
		String input = "";
		String temp;
		while(sc.hasNext()){
			temp = sc.next();
			input = input + temp + " ";
		}
		
		CharStream charStream = new ANTLRStringStream(input);
		progLexer lexer = new progLexer(charStream);
		TokenStream tokenStream = new CommonTokenStream(lexer);
		progParser parser = new progParser(tokenStream);
		program result = parser.prog();
		String f= result.playGame();
		//System.out.println(f);
		
	   
		String filename = "src/frontEnd.txt";
		FileWriter tosolve = new FileWriter(new File(filename));
		tosolve.write(f);
		tosolve.close();
		
	   ArrayList<String> existVar = new ArrayList<String>();
	   HashMap<String, Integer> forallVar= new HashMap<String, Integer>();
	   ArrayList<formula> fList = new ArrayList<formula>(100); //insert when a whole formula is recovered 
	   ArrayList<formula> fdecompose = new ArrayList<formula>(10);
	   ArrayList<ArrayList<formula>> dnf;
	   ArrayList<ArrayList<String>> LPPs = new ArrayList<ArrayList<String>>();
	  
	   
	   
	   int index =0;
	   index = scanInput(filename,fList,index,existVar,forallVar);
	   //printTree(fList.get(fList.size()-1));
	   convertImplies(fList,index);
	   pushNotIn(fList.get(fList.size()-1));
	   shrink(fList.get(fList.size()-1),fList);
	   
	   decompose(fList.get(fList.size()-1), fdecompose);
	 
	   ArrayList<formula> subProblems = new ArrayList<formula>();
	   subProblems = problemSet(fdecompose);
	   
	   
	   for(int i =0; i<subProblems.size(); i++){
		   dnf=dnfConvertor(subProblems.get(i)); 
		   FarkerTranslation(dnf,existVar,forallVar,LPPs); 
	  }
	 
	   if(LPPs.size()>0){
			 for(int i =0; i<LPPs.size(); i++){
				 System.out.println("Sub-problem:"+i);
				 if(sat(existVar,LPPs.get(i)).equals("SAT")){
					 //System.out.println(sat(existVar,LPPs.get(i)));
					 break;
				 }
				 System.out.println("========================================================");
		   }	   
	   }  
   
	   System.out.println("There are "+ LPPs.size()+ " subproblems.");
	   
   }
   
   public static ArrayList<formula> problemSet(ArrayList<formula> fdecompose){
	   int size = fdecompose.size();
	   ArrayList<ArrayList<Integer>> s = new ArrayList<ArrayList<Integer>>(2^size);
	     s = superSet((size-1), s);
	     //System.out.println();
	     ArrayList<formula> ret = new ArrayList<formula>(2^size);
	    
	     for(int i = 1; i<= size; i++){
	    	 for(int j = 0; j< s.size(); j++){
	    		 if(s.get(j).size()==i){
	    			    ret.add(createF((i-1), s.get(j), fdecompose));
	    		 }
	    	 }
	     }
	     
	     return ret;
   }
  public static formula createF(int n, ArrayList<Integer> l, ArrayList<formula> f){
	  if(n==0){
		  return f.get(l.get(n));
	  }
	  else{
		  formula temp = new formula();
		  temp.addcon("and");
		  temp.addc(f.get(l.get(n)), 0);
		  temp.addc(createF((n-1),l,f), 1);
		  return temp;
	  }
	  
  }
   public static ArrayList<ArrayList<Integer>> superSet(int n, ArrayList<ArrayList<Integer>> s){ 
   		int size = 0;
   		if(n== 0){
   			ArrayList<Integer> base1 = new ArrayList<Integer>(1);
   			ArrayList<Integer> base2 = new ArrayList<Integer>(0);
   			base1.add(n);
   			s.add(base1);
   			s.add(base2);
   			return s;
   		}
   	
   		else{
   			ArrayList<ArrayList<Integer>> tempS = superSet(n-1, s);
   			size = tempS.size();
   		
   		for(int i=0; i< size; i++){
   			ArrayList<Integer> temp = new ArrayList<Integer>(10);
   			for(int j=0; j< tempS.get(i).size(); j++){
   				temp.add(tempS.get(i).get(j));
   				//System.out.print(tempS.get(i).get(j) + ", ");
   				}
   			temp.add(n);
   			//System.out.println(n);
   			s.add(temp);
   			}
   		}
   		return s;
   }
   
	static String sat(ArrayList<String> existVar, ArrayList<String> lp) throws Z3Exception{ 
		
		   HashMap<String, String> cfg = new HashMap<String, String>();
	       cfg.put("model", "true");
	       Context ctx = new Context(cfg);
	       //Model m;
	      // Expr c1= ctx.MkConst(ctx.MkSymbol("c1"),ctx.MkRealSort());
	       
		   String s0="";
		   String s1="";
		   String s2="";
		   String formula;
		   String formula1;
		   String r;
		   
		   for(int i =0; i< existVar.size(); i++){
			   s0 = s0 + "(" + existVar.get(i)+ " Real)";
		   }
		   s0 ="(benchmark b :extrafuns (" + s0;
		   
		   for(int i=0; i< lp.size(); i++){
			   //System.out.println(lp.get(i));
			   if(lp.get(i).contains("Real"))
				   s1 = s1 + lp.get(i);
			   else{
				   s2 =s2 + " :formula "+lp.get(i);
			   }
		   }
		   s1 = s1+")";
		   formula = s0+s1+s2+")";
		   //System.out.println("formula " + formula);
		   ctx.ParseSMTLIBString(formula, null, null, null, null);
		   
	       //for (BoolExpr f : ctx.SMTLIBFormulas())
	          // System.out.println("formula " + f);
	       
		   String solverRet=Check(ctx, ctx.MkAnd(ctx.SMTLIBFormulas()), Status.SATISFIABLE);
		   
		   System.out.println("*********"+solverRet+"*********");
		   return solverRet;
		   	//System.out.println("==============================================================================");

	}
   	
	static String Check(Context ctx, BoolExpr f, Status sat) throws Z3Exception
   	{
	   Solver s = ctx.MkSolver();
	   	s.Assert(f);
	   Status ret = s.Check();
	   
	   		if (ret == Status.SATISFIABLE)
	   			return "SAT";
	   		else if( ret == Status.UNKNOWN)
	   			return "UNKNOWN";
	   		else 
	   			return "UNSAT";
	   	
   	}
   	
	public static int scanInput(String filename, ArrayList<formula> fList, int index, ArrayList<String> existVar, HashMap<String, Integer> forallVar) throws FileNotFoundException{   
		
		Scanner sc = new Scanner(new File(filename)); 

		   String temp="";
		   boolean start1 = false;
		   boolean start2 = false;
		   int varIndex=0;
		   
		   while(sc.hasNext()){
			   temp = sc.next();
			   if(temp.equals("Exists{")){
				   start1 = true;
			   }
			   else if(temp.equals("ForAll{")){
				   start2 = true;
			   }
			   else if(start1 && !temp.equals("},")){
				   existVar.add(temp);
			   }
			   else if(start2 && !temp.equals("},")){
				   forallVar.put(temp, varIndex);
				   varIndex++;
			   }
			   else if(start1 && temp.equals("},")){
				   start1=false;
			   }
			   else if(start2 && temp.equals("},")){
				   break;
			   }
		   }
		 
		   int i =0; 
		   formula parentf = null;
		   String single=""; 
		   formula toadd;
		   Stack<formula> fstack= new Stack<formula>();
	//===============  FIRST LINE ==============================//	
		  
		   if(sc.hasNext()){
			   temp = sc.next();
			   //System.out.println(temp);
			   
			  if(temp.charAt(0)=='('){ 
				  formula f=new formula();
				  f.id = index; 
				  index++;
				  f.addp(parentf);//outermost formula <=> root in a syntax tree
				  fstack.push(f); //unfinished formula will be in stack
				  i++;
				  
					  while (i<temp.length() && temp.charAt(i)=='('){
						  //println(temp.charAt(i));
						  formula f1=new formula();
						  f1.id = index; index++;
						  parentf= fstack.peek();
						  f1.addp(parentf);
						  fstack.push(f1);
						  parentf = f1;
						  i=i+1;
					  }
					  
					 
					  if(i<temp.length() && temp.charAt(i)!='(' && temp.charAt(i)!=')'){
						  single = "";
						  single = single + temp.charAt(i);
						  i=i+1;
						  while(i<temp.length() && temp.charAt(i)!='(' && temp.charAt(i)!=')'){
							  single = single + temp.charAt(i); i=i+1;
						  }
						  //System.out.println(single);
					  }
					  
					  
					  if(i<temp.length() && temp.charAt(i)==')'){
						  
						 toadd=fstack.pop();
						 toadd.adds(single);
						 single="";
						 fList.add(toadd);
						 
						 if(toadd.parent!=null){
							 if(toadd.parent.c1==null){
								 toadd.parent.addc(toadd, 0);
								 //System.out.println("add left child");
							 }
							 else if(toadd.parent.c2==null){
								 toadd.parent.addc(toadd, 1);
								 //System.out.println("add right child");
							 }
						 }
						 
						 while(i<(temp.length()-1)){
							i++;
							if(temp.charAt(i)==')'){
								//System.out.println(temp.charAt(i));
								 toadd=fstack.pop();
								 fList.add(toadd);
								 if(toadd.parent!=null){
									 if(toadd.parent.c1==null){
										 toadd.parent.addc(toadd, 0);
										 //System.out.println("add left child");
									 }
									 else if(toadd.parent.c2==null){
										 toadd.parent.addc(toadd, 1);
										 //System.out.println("add right child");
									 }
								 }
							}
						 }
				      }
			   }
			  
		    else{ System.out.println("error!");}
			  
		   }
		   

		   while(sc.hasNext()){
			 
			 temp = sc.next();
			 //System.out.println(temp);
			 i=0;
			
			 if(temp.equals("and") || temp.equals("or")||temp.equals("implies")||temp.equals("not")){
				 fstack.peek().addcon(temp);
			 }
			 
			 else if(temp.charAt(0)=='('){
				 //System.out.println(temp.charAt(0));
				  formula f=new formula();
				  f.id = index; index++;
				  parentf= fstack.peek();
				  f.addp(parentf);
				  fstack.push(f);
				  i++;
				  
					  while (i<temp.length() && temp.charAt(i)=='('){
						  //System.out.println(temp.charAt(i));
						  formula f1=new formula();
						  f1.id = index; index++;
						  parentf= fstack.peek();
						  f1.addp(parentf);
						  fstack.push(f1);
						  i=i+1;
					  }
					  
					 
					  if(i<temp.length() && temp.charAt(i)!='(' && temp.charAt(i)!=')'){
						  single = "";
						  single = single + temp.charAt(i);
						  i=i+1;
						  while(i<temp.length() && temp.charAt(i)!='(' && temp.charAt(i)!=')'){
							  single = single + temp.charAt(i); i=i+1;
						  }
						  //System.out.println(single);
					  }
					  
					  
					  if(i<temp.length() && temp.charAt(i)==')'){
						  //System.out.println(")");
						  
						  toadd=fstack.pop();
						  toadd.adds(single);
						  single="";
						  fList.add(toadd);
						 //System.out.println(toadd.connect);
						 
							 if(toadd.parent!=null){
								 if(toadd.parent.c1==null){
									 toadd.parent.addc(toadd, 0);
									 //System.out.println("add left child");
								 }
								 else if(toadd.parent.c2==null){
									 toadd.parent.addc(toadd, 1);
									 //System.out.println("add right child");
								 }
							 }
							 
							 while(i<(temp.length()-1)){
								i++;
								if(temp.charAt(i)==')'){
									
									 toadd=fstack.pop();
									 fList.add(toadd);
									 
									 if(toadd.parent!=null){
										 if(toadd.parent.c1==null){
											 toadd.parent.addc(toadd, 0);
											 //System.out.println("add left child");
										 }
										 else if(toadd.parent.c2==null){
											 toadd.parent.addc(toadd, 1);
											 //System.out.println("add right child");
										 }
									 }
								}
							 }
					   }
			   }	
			// System.out.println(fList.size());
			 //System.out.println(fstack.size());
			 }
		   formula root = new formula();
		   root.connect = "not";
		   root.c1= fList.get(fList.size()-1);
		   fList.get(fList.size()-1).parent=root;
		   fList.add(root);  
		return index;
	}
	   
	public static void convertImplies(ArrayList<formula> fList,int index){  
		//System.out.println("Convert implies to not-or");
		   formula tempf;
	  
		   for(int i=0;i<fList.size();i++){
			  
		      tempf = fList.get(i);
		      
		      if(tempf.connect!= null && tempf.connect.equals("implies")){
			   formula insertf = new formula();
			   insertf.id = index;
			   index++;
			   tempf.connect="or";
			   insertf.parent=tempf;
			   insertf.connect="not";
			   insertf.c1=tempf.c1;
			   tempf.c1=insertf;
		       }
	        }
		   
	}
	   
	public static void printTree(formula root)  {
		   //System.out.println("Print syntax tree!");
		   Queue<formula> q = new LinkedList<formula>();
		   
		   if(root.c1!=null || root.c2!= null){
			   q.add(root);
		   }
		   else if(root.singlton!=null){
			  // System.out.println("("+root.singlton+")"); 
		   }
		   
		   while(!q.isEmpty()){
			   
			   root = q.poll();
			   //if(root.connect!=null && root.connect.equals("not")){
			//   root = root.c1;
			  // }
			   
			  //System.out.println("       o");
			  //System.out.println("       ||    ");
			   if(root.c1!=null){
				   q.add(root.c1);
				   //System.out.print("c1   ");
			   }
			   if(root.connect!=null){
				   //System.out.print(root.connect);
			   }
			   if(root.c2!=null){
				   q.add(root.c2);
				   //System.out.println("   c2");
			   }
		   }
	   	}
	
	public static void pushNotIn(formula root){
		//System.out.println("Push not in!");
		formula tempf;
		Queue<formula> q = new LinkedList<formula>();
		q.add(root);
		
		while(!q.isEmpty()){
			tempf = q.poll();
			
			if(tempf.connect!=null && tempf.connect.equals("not")){ // formula is f = not c1
				 tempf.connect=null;
				if(tempf.c1!=null){
					if(tempf.c1.connect!=null && tempf.c1.connect.equals("and")){
						//System.out.println("AND to OR");
						tempf.c1.connect="or";
						if(tempf.c1.c1.connect!= null && tempf.c1.c1.connect.equals("not"))
							tempf.c1.c1.connect=null;
						else
							tempf.c1.c1.notNegated=false;
						if(tempf.c1.c2.connect!= null && tempf.c1.c2.connect.equals("not"))
							tempf.c1.c2.connect=null;
						else
							tempf.c1.c2.notNegated=false;
					}
					else if(tempf.c1.connect!=null && tempf.c1.connect.equals("or")){
						//System.out.println("OR to AND");
						tempf.c1.connect="and";
						if(tempf.c1.c1.connect!= null && tempf.c1.c1.connect.equals("not"))
							tempf.c1.c1.connect=null;
						else
							tempf.c1.c1.notNegated=false;
						if(tempf.c1.c2.connect!= null && tempf.c1.c2.connect.equals("not"))
							tempf.c1.c2.connect=null;
						else
							tempf.c1.c2.notNegated=false;
					}
					else if(tempf.c1.connect!=null && tempf.c1.connect.equals("not")){
						tempf.c1.connect=null;
					}
					else if(tempf.c1.connect==null && tempf.c1.singlton!=null){
						tempf.c1.notNegated=false;
					}
				}
			}
			else if (!tempf.notNegated && tempf.connect!=null && tempf.singlton==null){
				tempf.notNegated=true;
				if(tempf.connect.equals("and")){
						//System.out.println("2: and to or");
						tempf.connect="or";
						
						if(tempf.c1.connect!= null && tempf.c1.connect.equals("not"))
							tempf.c1.connect=null;
						else
							tempf.c1.notNegated=false;
						
						if(tempf.c2.connect!= null && tempf.c2.connect.equals("not"))
							tempf.c2.connect=null;
						else
							tempf.c2.notNegated=false;
					}
				else if(tempf.connect.equals("or")){
						//System.out.println("2: or to and");
						tempf.connect="and";
						
						if(tempf.c1.connect!= null && tempf.c1.connect.equals("not"))
							tempf.c1.connect=null;
						else
							tempf.c1.notNegated=false;
						
						if(tempf.c2.connect!= null && tempf.c2.connect.equals("not"))
							tempf.c2.connect=null;
						else
							tempf.c2.notNegated=false;
					}
				else if(tempf.connect.equals("not")){
						tempf.connect=null;
					}
				}
			
			
			if(tempf.c1!=null){
				q.add(tempf.c1);
				//System.out.println("ADD c1");
				}
			if(tempf.c2!=null){
				q.add(tempf.c2);
				//System.out.println("ADD c2");
				}
			}
		}
	   
	public static void shrink(formula root, ArrayList<formula> fList){
		//System.out.println("shrink");
		formula tempf;
		formula addf;
		Queue<formula> q = new LinkedList<formula>();
		q.add(root);
		
		//System.out.println(fList.size());
		
		while(!q.isEmpty()){
			
			tempf = q.poll();
			
			
			if(tempf.connect==null && tempf.singlton==null && tempf.c1!=null){
				addf = tempf.c1;
				if(tempf.parent==null){
					//System.out.println("root");
				        addf.parent=null;
						fList.remove(tempf);
						q.add(addf);
				  }
			    else{
					if(tempf.parent.c1.id== tempf.id){
						tempf.parent.c1 = addf;
						addf.parent = tempf.parent;
						//System.out.println("Parent's left child");
					}
					if(tempf.parent.c2.id== tempf.id){
						tempf.parent.c2 = addf;
						addf.parent = tempf.parent;
						//System.out.println("Parent's right child");
					}
					fList.remove(tempf);
					q.add(addf);
				}
			}
			else{
				if(tempf.c1!=null)
				q.add(tempf.c1);
				if(tempf.c2!=null)
				q.add(tempf.c2);
				
				//System.out.println("no need to remove");
			}
		 }
	}

	public static void decompose(formula root, ArrayList<formula> fdecompose){
		ArrayList<formula> temp = new ArrayList<formula>();
		boolean d=false;
		formula tempf;
		Queue<formula> q = new LinkedList<formula>();
		q.add(root);
		
		if(root.connect!= null){
			
			if(root.connect.equals("and")){
				
				while(!q.isEmpty()){
					
					//System.out.println("decompose");
					
					tempf=q.poll();
					
					if(tempf.connect!=null && tempf.connect.equals("and")){
						q.add(tempf.c1); q.add(tempf.c2);
					}
					else{
						temp.add(tempf);
						if(tempf.singlton!=null && !d)
							d = true;
					}
				}
				
				if(d){
					for(int i=0;i<temp.size();i++){
						fdecompose.add(temp.get(i));
						//System.out.println(i);
					}
				}
				else{
					fdecompose.add(root);
				}
				
			}
			
			else{
				fdecompose.add(root);
			}
		}
		
		else{
			fdecompose.add(root);
		}
		
		
	}
	
	public static ArrayList<ArrayList<formula>> dnfConvertor(formula f){
		
		   if(f.connect==null && f.c1==null && f.c2== null && f.singlton!=null){
			   ArrayList<formula> single = new ArrayList<formula>(1);
			   single.add(f);
			   ArrayList<ArrayList<formula>> singleA = new ArrayList<ArrayList<formula>>(1);
			   singleA.add(single);
			  // System.out.println(f.singlton);
			   return singleA;
		   }
		   
		  
		   ArrayList<ArrayList<formula>> left = new ArrayList<ArrayList<formula>>(1);
			   left =  dnfConvertor(f.c1);
		  
		   
		   ArrayList<ArrayList<formula>> right = new ArrayList<ArrayList<formula>>(1);
			   right = dnfConvertor(f.c2);
		  
		   //System.out.println("In dnfConvertor: "+f.connect);
		   return merge(left,right, f.connect);
	}
	
	public static ArrayList<ArrayList<formula>> merge(ArrayList<ArrayList<formula>> left, ArrayList<ArrayList<formula>> right, String operator){
		
		ArrayList<ArrayList<formula>> mergeA;
		
		if(operator.equals("or")){
			mergeA = new ArrayList<ArrayList<formula>>(left.size()+right.size());
			//System.out.println("In merge: operator is OR: left size"+ left.size()+"  right size  "+ right.size());
			for(int i=0; i<left.size();i++){
				mergeA.add(left.get(i));
			}
			for(int i=0; i<right.size();i++){
				mergeA.add(right.get(i));
			}
		}
		else {// and 
			mergeA = new ArrayList<ArrayList<formula>>(left.size()*right.size());
			//System.out.println("In merge: operator is AND: left size"+ left.size()+"  right size  "+ right.size());
			
			for(int i =0; i<left.size();i++){
				for(int j=0; j<right.size(); j++){
					ArrayList<formula> mergeAND = new ArrayList<formula>(left.get(i).size() + right.get(j).size());
					for(int k=0; k<left.get(i).size();k++){
						mergeAND.add(left.get(i).get(k));
						//System.out.println("In merge: add to mergeAND:"+ left.get(i).get(k).singlton);
					}
					for(int k=0; k<right.get(j).size();k++){
						mergeAND.add(right.get(j).get(k));
						//System.out.println("In merge: add to mergeAND:"+ left.get(i).get(k).singlton);
					}
					//System.out.println("In merge: operator is AND: mergeAND size:" + mergeAND.size());
					mergeA.add(mergeAND);
				}
			}
			
		}
        //System.out.println(mergeA.size());
		return mergeA;	
	}
	
	public static String[] parse(String ineq, HashMap<String, Integer> forallVar){
		String[] coefficients = new String[forallVar.size()+1];
		for(int i=0; i<forallVar.size()+1 ; i++){
			coefficients[i]="";
		}
		// * + - .
		char c;
		String s="";
		String tempe="";
		ArrayList<String> e = null;
		boolean newT = false;
		boolean rhs=false; //rhs
		boolean gthan=false;
		boolean minusf = false;
		int position=-1;
		for(int i = 0; i< ineq.length(); i++){
			c = ineq.charAt(i);
			
			if(((c>=65 && c<=90)||(c>=97 && c<=122)||(c>=48 && c<=57) || (c== 95) || c=='.') && (i!= ineq.length()-1)){
				s=s+c;
				if(!newT){
					e = new ArrayList<String>();
					newT = true;
				}
			}
			else if(c=='*'){
				if(!s.equals("")){
					//System.out.println("s="+s);
					if(compare(s, forallVar)==-1)
							e.add(s);
					else
						position = compare(s, forallVar);
				}
				s="";
			}
			else if(c=='+' || c=='-' || c=='<'|| c=='>' || i == ineq.length()-1){
				if(i==ineq.length()-1)
					s=s+c;
				if(!s.equals("")){
					//System.out.println("s="+s);
					if(compare(s, forallVar)==-1)
							e.add(s);
					else
						position = compare(s, forallVar);
				}
				
				if(i!=0 && e!=null && e.size()>0){
					for(int j=0; j<e.size(); j++){
						if(j==0)
							tempe = e.get(j);
						else
							tempe = "(* "+tempe + " "+e.get(j)+")";
					}
					if(minusf)
						tempe = "(- "+tempe+")"; 
					
					if(!rhs){
						if(position==-1){
							if(coefficients[coefficients.length-1].equals(""))
								coefficients[coefficients.length-1] = "(- "+tempe+")";
							else
								coefficients[coefficients.length-1] = "(- "+ coefficients[coefficients.length-1]+ " "+tempe+")";
						}
						else{
							if(coefficients[position].equals(""))
								coefficients[position] = tempe;
							else
								coefficients[position] = "(+ "+ coefficients[position]+ " "+tempe+")";
						}
					}
					else{
						if(position==-1){
							if(coefficients[coefficients.length-1].equals(""))
								coefficients[coefficients.length-1] = tempe;
							else
								coefficients[coefficients.length-1] = "(+ "+ coefficients[coefficients.length-1]+ " " +tempe+")";
						}
						else{
							if(coefficients[position].equals(""))
								coefficients[position] = "(- "+tempe+")";
							else
								coefficients[position] = "(- "+ coefficients[position]+ " " +tempe+")";
						}
					}
				}
				else if(i!=0){
					if(position!=-1){
						if(minusf){
							if(rhs){
								if(coefficients[position].equals(""))
									coefficients[position] = "1";
								else
									coefficients[position] = "(+ 1 "+ coefficients[position]+")";
							}
							else{
								if(coefficients[position].equals(""))
									coefficients[position] = "(-  1)";
								else
									coefficients[position] = "(- "+ coefficients[position]+ " 1)";
							}
						}
						else{
							if(rhs){
								if(coefficients[position].equals(""))
									coefficients[position] = "(- 1)";
								else
									coefficients[position] = "(- "+ coefficients[position]+" 1)";
							}
							else{
								if(coefficients[position].equals(""))
									coefficients[position] = "1";
								else
									coefficients[position] = "(+ 1 "+ coefficients[position]+ ")";
							}
						}
					}
				}
				
				
				if(c=='<')
					rhs=true;
				
				else if(c=='>'){
					gthan = true;
					rhs = true;
				}
				
				if(c=='-')
					minusf=true;
				else
					minusf = false;
				
				
				

				newT=false;	
				e=null;
				s="";
				position=-1;
			}
		}
		for(int i=0; i<coefficients.length; i++){
			if(!coefficients[i].equals("") && gthan)
				coefficients[i]= "(- "+coefficients[i]+")";
			else if(coefficients[i].equals(""))
				coefficients[i] = "0";
		}
		return coefficients;
	}

	public static int compare(String s,HashMap<String, Integer> forallVar){ // store variable names
		 
				if(forallVar.get(s)!=null)
					return forallVar.get(s);
				else
					return -1;
			}
	
	public static String[] preform(String[] before){
		String[] after = new String[before.length];
		String s="";
		String var="";
		char op='n';
		char tempc;
		boolean first=true;
		boolean first1=true;
		
		for(int i=0; i<before.length; i++){
			
			first=true;
			first1=true;
			s="";
			var ="";
			after[i] = "";	
			op='n';
			
			if(before[i].length()==1){
				after[i] = after[i]+ before[i];
			}
			
			else{
				for(int j=0; j<before[i].length();j++){
					tempc=before[i].charAt(j);
					if(first && (tempc=='+'||tempc=='-')){
						op=tempc;
						first = false;
					}
					else if(((tempc>=65 && tempc<=90)||(tempc>=97 && tempc<=122)||(tempc==95)||(tempc>=48 && tempc<=57)) && (j<(before[i].length()-1))){
						first = false;
						var = var + tempc;
					}
					else if(!first && (tempc=='+'||tempc=='-'|| j==(before[i].length()-1))){
						if(j==(before[i].length()-1))
							var = var + tempc;
						
						if(first1){
							first1 = false;
							if(op=='-')
								s = "(-"+ " "+var+")";
							else
								s = var;
						}
						else{
							s= "(" + op + " "+ s+ " " + var +")";
						}
						var = "";
						op = tempc;
					}
				}
				after[i]=after[i] + s;
			}
		}
		return after;
	}
	
	public static void FarkerTranslation(ArrayList<ArrayList<formula>> dnf, ArrayList<String> existVar, HashMap<String, Integer> forallVar, ArrayList<ArrayList<String>> LPPs) throws IOException, Z3Exception{
		
		//System.out.println("Farkas Translation!");


		//System.out.println("Farkas Translation!");

		   ArrayList<String> tempLPP = new ArrayList<String>();
		   String[] tempk;
		   String ineq;
		   String nono = "";
		   String common = "";
		   String tempc = "";
		   String towrite = "";
		   String and1 = "";
		   String and2 = "";
		   String and3 = "";
		   String and4 = "";
		   boolean canAdd=false;
		   boolean first1=true;
		   boolean first2=true;
		   
		   for(int i =0; i<dnf.size();i++){
			   //System.out.println("LPP"+i);
			   String[][] coefficients = new String[dnf.get(i).size()][forallVar.size() + 1];
			   ArrayList<String> addvarTemp = new ArrayList<String>(dnf.get(i).size());	
			   ArrayList<String> geq = new ArrayList<String>();
			   ArrayList<String> gr1 = new ArrayList<String>();
			   ArrayList<String> gr2 = new ArrayList<String>();
			   ArrayList<String> gr3 = new ArrayList<String>();
			   nono="";
			   common = "";
			   and1 = "";
			   and2 = "";
			   and3 = "";
			   and4 = "";
			   
			   first1=true; first2=true;
			   
			   for(int j=0; j<dnf.get(i).size();j++){
				   String addy = "y" + Integer.toString(i) + Integer.toString(j);
					  addvarTemp.add(addy);
			     }
			   
			   for(int j=0; j<dnf.get(i).size();j++){
				   tempLPP.add("("+addvarTemp.get(j)+ " Real)");
				   
				   //System.out.print("{");
				  if(dnf.get(i).get(j).singlton!=null){
					  ineq = dnf.get(i).get(j).singlton;// get coefficient A&b
					  //System.out.println("Before == Ineq:"+ineq);
				    	  //================== change to <= | < b =================================//
					  tempc="";
					  boolean rhs = false;
					  
				    	  if(!dnf.get(i).get(j).notNegated){
				    		  
				    		  for(int p=0;p<ineq.length();p++){
				    			  if(((p==0 && !rhs) || (rhs))&& (ineq.charAt(p)!='+')&&(ineq.charAt(p)!='-')&&(ineq.charAt(p)!='=')){
				    				  if(p==0 && !rhs)
				    					  tempc = "-"+ tempc + ineq.charAt(p);
				    				  if(rhs){
				    					  tempc = tempc + "-"+ ineq.charAt(p);
				    					  rhs = false;
				    				  }
				    			  }
				    			  else if(ineq.charAt(p)=='<'){
				    				  tempc = tempc + ineq.charAt(p);
				    				  rhs = true;
				    			  }
				    			  else if(ineq.charAt(p)=='+'){
				    				  tempc = tempc + "-";
				    			  }
				    			  else if(ineq.charAt(p)=='-'){
				    				  tempc = tempc + "+";
				    			  }
				    			  else{
				    				  tempc = tempc + ineq.charAt(p);
				    			  }
				    		  }
				    	  }
				    	  
				    	  if(tempc.equals("")){
				    		  //System.out.println("After==Ineq:"+ineq);
				    		  coefficients[j] = parse(ineq,forallVar);
				    	  }
				    	  else{
				    		  //System.out.println("After==tempc:"+tempc);
				    		  coefficients[j] = parse(tempc,forallVar);
				    	  }
				    	  
				    	  //================== constrains 1 =======================================//
				    	  if(dnf.get(i).get(j).notNegated){
							  if(ineq.indexOf('<')!=-1 && ineq.indexOf('=')==-1){
								  gr1.add("(= "+addvarTemp.get(j)+" 0)");
								  gr2.add("(>= "+addvarTemp.get(j)+" 0)");
								  //if(!coefficients[j][forallVar.size()].equals("0")){
									  if(first2){
										first2=false; 
										nono ="(* " + addvarTemp.get(j)+ " "+ coefficients[j][forallVar.size()]+")";
									  }
									  else{
									  nono = "(+ " + nono + "(* " + addvarTemp.get(j)+ " "+ coefficients[j][forallVar.size()]+"))";
									  }
								  //}
							  }
							  else if(ineq.indexOf('<')!=-1 && ineq.indexOf('=')!=-1){
								  gr3.add("(>= " + addvarTemp.get(j)+" 0)");
								//  if(!coefficients[j][forallVar.size()].equals("0")){
									  if(first1){
											first1=false; 
											common ="(* " + addvarTemp.get(j)+ " "+ coefficients[j][forallVar.size()]+")";
										  }
										  else{
											  common = "(+ " + common + "(* " + addvarTemp.get(j)+ " "+ coefficients[j][forallVar.size()]+"))";
										  }
								 // }
							  }
						    }
				    	  if(!dnf.get(i).get(j).notNegated){
							  if(ineq.indexOf('<')!=-1 && ineq.indexOf('=')==-1){
								  gr3.add("(>= " + addvarTemp.get(j)+" 0)");
								  //if(!coefficients[j][forallVar.size()].equals("0")){
									  if(first1){
											first1=false; 
											common ="(* " + addvarTemp.get(j)+ " "+ coefficients[j][forallVar.size()]+")";
										  }
										  else{
											  common = "(+ " + common + "(* " + addvarTemp.get(j)+ " "+ coefficients[j][forallVar.size()]+"))";
										  }
								 // }
							  }
							  else if(ineq.indexOf('<')!=-1 && ineq.indexOf('=')!=-1){
								  gr1.add("(= " + addvarTemp.get(j)+" 0)");
								  gr2.add("(>= " + addvarTemp.get(j)+" 0)");
								  //if(!coefficients[j][forallVar.size()].equals("0")){
									  if(first2){
											first2=false; 
											nono ="(* " + addvarTemp.get(j)+ " "+ coefficients[j][forallVar.size()]+")";
										  }
										  else{
										  nono = "(+ " + nono + "(* " + addvarTemp.get(j)+ " "+ coefficients[j][forallVar.size()]+"))";
										  }
								  //}
							  }
						    }
					  
				  }
				//  System.out.println("}");
					  
			  }
			   
			  
				if(gr1.size()>0){   
					for(int m=0; m<gr1.size(); m++){
						if(m==0){
							and1=gr1.get(m);
						}
						else{
							and1 = "(and "+gr1.get(m)+" "+and1+")";
						}
					}	
				}
				
			   
			   if(gr2.size()>0){   
					for(int m=0; m<gr2.size(); m++){
						if(m==0){
							and2=gr2.get(m);
						}
						else{
							and2 = "(and "+gr2.get(m)+" "+and2+")";
						}
					}
			   }
			   
			   if(gr3.size()>0){   
					for(int m=0; m<gr3.size(); m++){
						if(m==0){
							and3=gr3.get(m);
						}
						else{
							and3 = "(and "+gr3.get(m)+" "+and3+")";
						}
					}
			   }
			 
		   first2=true;
		   for(int j=0; j< forallVar.size(); j++){
			   first1=true;
			   towrite="";
			   for(int k =0; k<addvarTemp.size();k++){
				   if(!coefficients[k][j].equals("0")){
					   if(first1){
						   towrite = "(* "+coefficients[k][j]+ " "+addvarTemp.get(k)+")";
						   first1=false;
					   }
					   else{  
						   towrite = "(+ "+towrite+" (* "+coefficients[k][j]+ " "+addvarTemp.get(k)+"))";
					   }
				   }
			   }
			   if(!towrite.equals("")){
				   canAdd=true;
				   towrite = "(= "+towrite + " 0)";
				   if(first2){
					   and4 = towrite;
					   first2=false;
				   }
				   else{
					   and4 = "(and "+ towrite + and4+")"; 
				   }
			   }
		   }
		   
		   
		   if(!and3.equals("")){
			   canAdd=true;
			   if(!and1.equals("")){
				   geq.add("(or (and (and (and (and "+ and2 + "(not "+and1+"))" + and3 +")" + and4 + ")"+ "(<= "+common+nono+" 0))" + 
			   "(and (and (and "+ and2 + and3 +")" + and4 + ")"+ "(< "+common+nono+" 0)))");
			   }
			   else{
				   geq.add("(and (and " + and3 + and4+")"+"(< "+common+nono+" 0))");
			   }
		   }
		   else if(!and1.equals("")){
			   geq.add("(or (and (and " + and2+ and4 + ")" + "(< "+common+nono+" 0))"+
					   "(and (and (and" + and2 + "(not "+and1+"))" + and4 + ")" + "(<= "+common+nono+" 0)))");
		   }
		   
		   if(canAdd){ 
			   for(int m=0; m<geq.size(); m++){
				   //System.out.println(geq.get(m));
				   tempLPP.add(geq.get(m));
			   }
		   }
				
				//System.out.println();
					   	
		 }
		  
		   if(!tempLPP.isEmpty() && canAdd){
				   LPPs.add(tempLPP);
		   }
		  
	}
 }
	
/*
public static void FarkerTranslation(ArrayList<ArrayList<formula>> dnf, ArrayList<String> existVar, HashMap<String, Integer> forallVar, ArrayList<ArrayList<String>> LPPs) throws IOException, Z3Exception{
		
		   ArrayList<String> tempLPP = new ArrayList<String>();
		   String[] tempk;
		   String ineq;
		   String nono = "";
		   String common = "";
		   String tempc = "";
		   String towrite = "";
		   String and1 = "";
		   String and2 = "";
		   boolean canAdd=false;
		   boolean first1=true;
		   boolean first2=true;
		   
		   for(int i =0; i<dnf.size();i++){
			   
			   String[][] coefficients = new String[dnf.get(i).size()][forallVar.size() + 1];
			   ArrayList<String> addvarTemp = new ArrayList<String>(dnf.get(i).size());	
			   ArrayList<String> geq = new ArrayList<String>();
			   ArrayList<String> gr1 = new ArrayList<String>();
			   ArrayList<String> gr2 = new ArrayList<String>();
			   nono="";
			   common = "";
			   and1 = "";
			   and2 = "";
			   first1=true; first2=true;
			   
			   for(int j=0; j<dnf.get(i).size();j++){
				   String addy = "y" + Integer.toString(i) + Integer.toString(j);
					  addvarTemp.add(addy);
			     }
			   
			   for(int j=0; j<dnf.get(i).size();j++){
				   
				   tempLPP.add("("+addvarTemp.get(j)+ " Real)");
				   
				   //System.out.print("{");
				  if(dnf.get(i).get(j).singlton!=null){
					  
				    	  ineq = dnf.get(i).get(j).singlton;
				    	  coefficients[j] = parseRe(ineq,forallVar); // get coefficient A&b
				    	 
				    	  //================== change to <= | < b =================================//
				    	  if(!dnf.get(i).get(j).notNegated){
				    		  for(int k=0; k< coefficients[j].length; k++){
				    			  tempc = "";
				    			  for(int p=0;p<coefficients[j][k].length();p++){
				    				  
				    				  if((p==0)&&((coefficients[j][k].charAt(p)!='+')&&(coefficients[j][k].charAt(p)!='-'))){
				    					  if(coefficients[j][k].charAt(p)!='0')
				    						  tempc = "-"+tempc;
				    					  
				    					  tempc = tempc + coefficients[j][k].charAt(p);
				    				  }
				    				  else if(coefficients[j][k].charAt(p)=='+'){
				    					  tempc = tempc + "-";
				    				  }
				    				  else if(coefficients[j][k].charAt(p)=='-'){
				    					  tempc = tempc + "+";
				    				  }
				    				  else{
				    					  tempc = tempc + coefficients[j][k].charAt(p);
				    				  }
				    			  }
				    			  coefficients[j][k] = tempc; 
				    		  }  
				    	  }
				    	 // System.out.print("test1:");
				    	  for(int k=0; k< coefficients[j].length; k++){
							   if(k==coefficients[j].length-1){
								   //System.out.print("   b=");
							   }
							   //System.out.print(coefficients[j][k]+ " , ");
					 		}
				    	  
				    	 // System.out.println("Ineq:"+ineq);
				    	  tempk = preform(coefficients[j]);
				    	  //System.out.print("test2:");
				    	  for(int k=0; k< coefficients[j].length; k++){
							   if(k==coefficients[j].length-1){
								  // System.out.print("   b=");
							   }
							  // System.out.print(coefficients[j][k]+ " , " + tempk[k]+ " ||");
					 		}
				    	 // System.out.println();
				    	 // System.out.println();
				    	   //tempk = preform(coefficients[j]);
				    	  
				    	  
						   for(int k=0; k< coefficients[j].length; k++){
							   if(k==coefficients[j].length-1){
								   //System.out.print("   b=");
							   }
							   //System.out.print(tempk[k]+ ", ");
							   coefficients[j][k]=tempk[k];
					 		}
						   
						  // System.out.println();
						  // System.out.println();
				    	  //================== constrains 1 =======================================//
				    	  if(dnf.get(i).get(j).notNegated){
							  if(ineq.indexOf('<')!=-1 && ineq.indexOf('=')==-1){
								  gr1.add("(= "+addvarTemp.get(j)+" 0)");
								  gr2.add("(>= "+addvarTemp.get(j)+" 0)");
								  if(!coefficients[j][forallVar.size()].equals("0")){
									  if(first2){
										first2=false; 
										nono ="(* " + addvarTemp.get(j)+ " "+ coefficients[j][forallVar.size()]+")";
									  }
									  else{
									  nono = "(+ " + nono + "(* " + addvarTemp.get(j)+ " "+ coefficients[j][forallVar.size()]+"))";
									  }
								  }
							  }
							  else if(ineq.indexOf('<')!=-1 && ineq.indexOf('=')!=-1){
								  geq.add("(>= " + addvarTemp.get(j)+" 0)");
								  if(!coefficients[j][forallVar.size()].equals("0")){
									  if(first1){
											first1=false; 
											common ="(* " + addvarTemp.get(j)+ " "+ coefficients[j][forallVar.size()]+")";
										  }
										  else{
											  common = "(+ " + common + "(* " + addvarTemp.get(j)+ " "+ coefficients[j][forallVar.size()]+"))";
										  }
								  }
							  }
						    }
				    	  if(!dnf.get(i).get(j).notNegated){
							  if(ineq.indexOf('<')!=-1 && ineq.indexOf('=')==-1){
								  geq.add("(>= " + addvarTemp.get(j)+" 0)");
								  if(!coefficients[j][forallVar.size()].equals("0")){
									  if(first1){
											first1=false; 
											common ="(* " + addvarTemp.get(j)+ " "+ coefficients[j][forallVar.size()]+")";
										  }
										  else{
											  common = "(+ " + common + "(* " + addvarTemp.get(j)+ " "+ coefficients[j][forallVar.size()]+"))";
										  }
								  }
							  }
							  else if(ineq.indexOf('<')!=-1 && ineq.indexOf('=')!=-1){
								  gr1.add("(= " + addvarTemp.get(j)+" 0)");
								  gr2.add("(>= " + addvarTemp.get(j)+" 0)");
								  if(!coefficients[j][forallVar.size()].equals("0")){
									  if(first2){
											first2=false; 
											nono ="(* " + addvarTemp.get(j)+ " "+ coefficients[j][forallVar.size()]+")";
										  }
										  else{
										  nono = "(+ " + nono + "(* " + addvarTemp.get(j)+ " "+ coefficients[j][forallVar.size()]+"))";
										  }
								  }
							  }
						    }
					  
				  }
				//  System.out.println("}");
					  
			  }
			   
			   
			 //System.out.println("common = " + common);  
			   
			  
				if(gr1.size()>0){   
					for(int m=0; m<gr1.size(); m++){
						if(m==0){
							and1=gr1.get(m);
						}
						else{
							and1 = "(and "+gr1.get(m)+" "+and1+")";
						}
					}	
				}
				
			   
			   if(gr2.size()>0){   
					for(int m=0; m<gr2.size(); m++){
						if(m==0){
							and2=gr2.get(m);
						}
						else{
							and2 = "(and "+gr2.get(m)+" "+and2+")";
						}
					}
			   }
					
		   if(!nono.equals("")){
			   canAdd= true;
			   if(common.equals(""))
				   geq.add("(or (and (and "+and2+" (not "+and1+")) (<= "+nono+" 0)) "+and1+")");
			   else
				   geq.add("(or (and (and "+and2+" (not "+and1+")) (<= "+common+nono+" 0)) "+"(and "+and1+" (< "+common+" 0)))"); 
			}
		   else{
			   if(gr1.size()>0)
				   geq.add("(and "+and2+" (not "+and1+"))");
			   if(!common.equals("")){
				   canAdd= true;
				   geq.add("(< "+common + " 0)");
				   }
		   }
		   
		   
		   for(int j=0; j< forallVar.size(); j++){
			   first1=true;
			   towrite="";
			   for(int k =0; k<addvarTemp.size();k++){
				   if(!coefficients[k][j].equals("0")){
					   if(first1){
						   towrite = "(* "+coefficients[k][j]+ " "+addvarTemp.get(k)+")";
						   first1=false;
					   }
					   else{  
						   towrite = "(+ "+towrite+" (* "+coefficients[k][j]+ " "+addvarTemp.get(k)+"))";
					   }
				   }
			   }
			   if(!towrite.equals("")){
				   canAdd=true;
				   towrite = "(= "+towrite + " 0)";
				   //println("towrite: "+ towrite);
				   geq.add(towrite);
			   }
		   }
		   
		   if(canAdd){ 
			  // System.out.println("canAdd:::::"+ geq.size());
			   for(int m=0; m<geq.size(); m++){
				  // System.out.println(geq.get(m));
				   tempLPP.add(geq.get(m));
			   }
		   }
				
				//System.out.println();
				//System.out.println();
					   	
		 }
		  
		   if(!tempLPP.isEmpty() && canAdd){
				   LPPs.add(tempLPP);
		   }
		*/  
	
	
	
