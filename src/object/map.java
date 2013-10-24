package object;

import java.util.HashMap;

public class map extends object{
	public HashMap<object, object> m;
	
	public map(HashMap<object, object> m){
		this.type = "map";
		this.m = m;
	}
}
