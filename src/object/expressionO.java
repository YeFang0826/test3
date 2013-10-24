package object;

public class expressionO extends object{
	public expressionO left;
	public expressionO right;
	public object leaf;
	public String operator;
	
	public expressionO(){
		this.type = "expression";
	}
	
}
