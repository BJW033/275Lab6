
public enum StartStop {
	START("start"), STOP("stop");
	private String name = null;
	
	private StartStop(String s){
		name = s;
	}
	public String getName() {
		return name;
	}
}
