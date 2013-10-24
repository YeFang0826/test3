package quantifier;

import expression.expression;

public class forall extends quantifier{
	public String i;
	public expression lbound;
	public expression ubound;
	
	public forall(String i, expression l, expression u){
		this.type = "forall";
		this.i = i;
		this.lbound = l;
		this.ubound = u;
	}
}
