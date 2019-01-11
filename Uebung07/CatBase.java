
public abstract class CatBase {
	protected final int height;
	protected final int width;
	
	public CatBase(int height, int width) {
		if(height <= 0 || width <= 0){
			throw new IllegalArgumentException();
		}
		this.height = height;
		this.width = width;
	}
	
	public int getHeight(){
		return height;
	}
	public int getWidth(){
		return width;
	}

}
