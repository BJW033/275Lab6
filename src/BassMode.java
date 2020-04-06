// Used to determine the Bass animation to use
// Brandon Wu
public enum BassMode {

    DEFAULT("default"), CONFUSE("confuse"), ATTAC("attac");

	private String name = null;
	
	private BassMode(String s){
		name = s;
	}

	public String getName() {
		return name;
	}
};
