package my_game;

public class Point {
	
	//Although not recommended to use public variables, allows easy access through p.x
	public int x, y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public double calcDist(Point point){
		double a = Math.pow(point.getX() - this.x,2);
		double b = Math.pow(point.getY() - this.y,2);
		return Math.sqrt(a+b);
	}

	public String toString(){
		return (x + "," + y);
	}
}

