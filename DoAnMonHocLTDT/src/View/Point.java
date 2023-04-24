package View;

public class Point {
	private int x;
	private int y;
	private String lable;
	public Point(int x, int y, String lable) {
		super();
		this.x = x;
		this.y = y;
		this.lable = lable;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getLable() {
		return lable;
	}
	public void setLable(String lable) {
		this.lable = lable;
	}
	
	

}
