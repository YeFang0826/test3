package deparen;

public class deparen {
	public String l;
	public String r;
	public String op;
	
	public deparen(String l, String r, String op){
		this.l = l;
		this.r = r;
		this.op = op;
	}
	public String print(){
		if(this.op.equals("+"))
			return (this.l + this.op + this.r);
		else if(this.op.equals("-")){
			StringBuffer sb = new StringBuffer(this.r);
			for(int i=0; i<sb.length(); i++){
				if(sb.charAt(i) =='+')
					sb.replace(i, (i+1), "-");
				else if(sb.charAt(i) =='-')
					sb.replace(i, (i+1), "+");
			}
			return (this.l + "-"+sb.toString());
		}
		else if(this.op.equals("*") || this.op.equals("/")){
			StringBuffer sb = new StringBuffer(this.r);
			String s="";
			String ret="";
			boolean f = true;
			
			for(int i=0; i<this.l.length(); i++){
				if(this.l.charAt(i) == '+' || this.l.charAt(i) == '-' || i== (this.l.length()-1)){
					if(i== (this.l.length()-1))
						s = s+ this.l.charAt(i);
					
					for(int j=0; j<sb.length(); j++){
						if((sb.charAt(j) =='+') || (sb.charAt(j) =='-')){
							if(!f){
								if(sb.charAt(j) =='+')
									sb.replace(j, (j+1), "-");
								else if(sb.charAt(j) =='-')
									sb.replace(j, (j+1), "+");
							}
							sb.insert((j+1), (s + this.op));
						}
					}
					
					ret = ret+ s+ this.op + sb.toString();
					
					if(this.l.charAt(i) == '+' || this.l.charAt(i) == '-' ){
						if(this.l.charAt(i) == '+')
							f = true;
						else
							f = false;
						ret = ret + this.l.charAt(i);
					}
					
					sb = new StringBuffer(this.r);
					s="";
				}
				else{
					s = s+ this.l.charAt(i);
				}
			}
			return ret;
		}
		else if(this.op.equals("and") || this.op.equals("or") || this.op.equals("implies")){
			return ("(" + this.l + " " + this.op + " " + this.r +  ")");
		}
		else 
			return ("(" + this.l+this.op +this.r +  ")");
			
	}
}
