package term;
import expression.expression;

public class expressionT extends term {
	
	public expression expr;
	public expressionT(expression expr){
		this.expr = expr;
		this.type = expr.type;
	}
}
