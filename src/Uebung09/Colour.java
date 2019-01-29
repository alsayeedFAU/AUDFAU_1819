public enum Colour {
	WHITE, BLACK;

	private Colour() {
		
	}
	
	public Colour flip() {
		if(this == WHITE) {
			return BLACK;
		}else {
			return WHITE;
		}
	}
	
	

}