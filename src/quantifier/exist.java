package quantifier;

import expression.expression;

public class exist extends quantifier{
	public String i;
	public expression lbound;
	public expression ubound;
	
	public exist(String i, expression l, expression u){
		this.type = "exist";
		this.i = i;
		this.lbound = l;
		this.ubound = u;
	}
	
}
