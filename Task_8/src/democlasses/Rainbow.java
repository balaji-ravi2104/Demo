package democlasses;

public enum Rainbow {
	VIOLET(1),INDIGO(2),BLUE(3),GREEN(4),YELLOW(5),ORANGE(6),RED(7);
	
	private int value;
	private Rainbow(int value) {
		this.value = value;
	}
	
	 public int getValue() {
	        return value;
	 }
}
