package term;

public class var extends term {
	public String s;  // place holder for real number

	public var(String s){
		this.s = s;
		this.type = "var";
	}
}
