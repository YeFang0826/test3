package statement;
import java.util.HashMap;

import expression.*;

public class assumeStatement extends statement{
	public expression  e;
	
	public assumeStatement(expression e){
		this.type = "assumeStatement";
		this.e = e;
	}
	public void eval_0(HashMap<String, expression> globalVar){
		this.e  = this.e.eval_0(globalVar);
	}
}
