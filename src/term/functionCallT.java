package term;
import functionCall.functionCall;

public class functionCallT extends term{
	
	public functionCall call;
	
	public functionCallT(functionCall call){
		this.call = call;
		this.type = call.type; //return type
	}

	
}
