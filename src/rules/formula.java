package rules;

public class formula{
	formula parent;
	formula c1;
	formula c2;
	String singlton;
	//c1 implies c2, c1 or c2, c1 and c2, not(singlton), singlton
	String connect; // and|or|not|implies|equiv 
	int id;
	boolean notNegated=true;
	
	public formula(){ 
		this.parent = null;
		this.c1 = null;
		this.c2 = null;
		this.singlton = null;
		this.connect=null; // boolean logic operator
		this.notNegated=true;
		this.id = -1;
	}
	
	public void adds(String c){
		this.singlton=c;
	}
	public void addc(formula c, int x){
		if(x==0){ this.c1=c;}
		else{ this.c2=c;}
	}
	public void addcon(String c){
		this.connect=c;
	}
	public void addp(formula p){
		this.parent=p;
	}
	public void negate(){
		this.notNegated = false;
	}
	
}
