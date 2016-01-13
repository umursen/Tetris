
public class Coordinates {
	double x;
	double y;
	
	public Coordinates() {
		super();
		this.x = 0;
		this.y = 0;
	}
	
	public Coordinates(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	boolean equals(Coordinates point){
		if(point.getX() == this.x && point.getY() == this.y)
			return true;
		return false;
	}
}
