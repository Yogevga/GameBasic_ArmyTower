package my_game;

//import DB.ExcelTable;
import game.ShapeListener;
import gui.GameCanvas;
import game.Game;
import game.PeriodicLoop;
import shapes.Image;

public class Soldier implements ShapeListener {

	public enum Direction {
		RIGHT(10, 10),
		LEFT(-10, 10),
		UP(0, -10),
		DOWN(0, 10);

		private final int xVec, yVec;

		private Direction(int xVec, int yVec) {
			this.xVec = xVec;
			this.yVec = yVec;
		}

		public int xVec() {
			return xVec;
		}

		public int yVec() {
			return yVec;
		}
	}

	private Point location;
	private Direction directionPolicy = Direction.DOWN;
	private Direction direction = Direction.RIGHT;
	private int velocity = 0;
	private int velocityPolicy = 0;
	private int friction = 3;

	private final String[] images = { "resources/soldier1.png" };
	private int imageIndex = 0;
	private final String imageID = "soldier";
	private boolean isMoving = true;
	private boolean isOnStair = false;
	private boolean isOnFloor = true;
	private int rotation = 0; // In degrees

	public void addToCanvas() {
		GameCanvas canvas = Game.UI().canvas();
		Image image = new Image(getImageID(), getImageName(), 220, 200, 600, 600);
		image.setShapeListener(this);
		image.setzOrder(3);
		canvas.addShape(image);
	}

	public Point getLocation() {
		return this.location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public void moveLocation(int dx, int dy) {
		this.location.x += dx;
		this.location.y += dy;
	}

	public void setDirectionPolicy(Direction direction) {
		directionPolicy = direction;
	}

	public void switchDirectionPolicy() {
		if (directionPolicy == Direction.RIGHT) {
			directionPolicy = Direction.LEFT;
		} else if (directionPolicy == Direction.LEFT) {
			directionPolicy = Direction.RIGHT;
		}
		//System.out.println("curr" + velocity);
		velocity = -velocity;
		//System.out.println("after" + velocity);
	}

	public Direction getDirection() {
		return direction;
	}

	public Direction getPolicy() {
		return directionPolicy;
	}

	public String getImageName() {
		return images[imageIndex];
	}

	public String getImageID() {
		return this.imageID;
	}

	public int getRotation() {
		return rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
		Image i = (Image) (Game.UI().canvas().getShape(imageID));
		i.setRotation(rotation);
	}

	public void setImage(int index) {
		this.imageIndex = index;
		if (imageIndex == 0) {
			Game.UI().canvas().changeImage(imageID, getImageName(), 220, 200);
		} else {
			Game.UI().canvas().changeImage(imageID, getImageName(), 260, 195);
		}
	}

	public void stopMoving() {
		isMoving = false;
	}

	public void resumeMoving() {
		isMoving = true;
	}

	public void setOnStair(boolean tf) {
		isOnStair = tf;
	}

	public boolean getOnStair() {
		return isOnStair;
	}

	public void updateVelocity(String v){
		//this.velocity += dv;
		if (v=="goLEFT") {
			this.velocityPolicy = -5;
		}
		else if (v=="goRIGHT") {
			this.velocityPolicy = 5;
		} else if (v=="abort"){
			this.velocityPolicy = 0;
		}
		System.out.println(velocity);
	}

	public void move_new() {
		// Move according to Velocity
		velocity = velocity + velocityPolicy;
		int dx = 0;
		int maxVelocity = 50;
		if (velocity > friction) { //Move right, Friction to the Left, friction is positive sign
			velocity -= friction;
			velocity = Math.min(velocity,maxVelocity);
			dx = velocity - friction;
		} else if  (velocity < -friction) // 
		{
			velocity += friction;
			velocity = -Math.min(-velocity,maxVelocity);
			dx = velocity + friction;
		} else dx = 0;
		
		Point desired = new Point(location.x + dx, location.y + directionPolicy.yVec());
		
		if (!isOnStair) {
			location.y = desired.y;
		}	

		if (location.y >= 500) {
			location.y = 500;
			isOnFloor = true;
		} else isOnFloor = false;

		location.x = desired.x;
		Game.UI().canvas().moveToLocation(imageID, location.x, location.y);
	}

	public void jump(){
		int k = 300;
		// check if can jump
		if (! (isOnStair || isOnFloor) ) return;
		// calc jump height from velocity
		double tmp = 100*Math.abs(velocity);
		if (Math.abs(tmp) > k) tmp = k; 
		tmp = -tmp;
		System.out.println(tmp);
		int dy = (int) (Math.ceil(tmp));
		System.out.println("dy is: " + dy);
		moveLocation(0, dy);
	}

	public void move() {

		if (isMoving) {
			// Move according to policy
			Point desired = new Point(location.x + directionPolicy.xVec(), location.y + directionPolicy.yVec());
			// if move is possible, i.e., maze does not block
			direction = directionPolicy;
			location.x = desired.x;
			if (!isOnStair) {
				location.y = desired.y;
			}
			//System.out.println(location.y);
			if (location.y >= 500) {
				location.y = 500;
				isOnStair = true;
			}
			// After changing the soldier self location, move also its image in the canvas
			// accordingly.
			Game.UI().canvas().moveToLocation(imageID, location.x, location.y);
		}
	}

	@Override
	public void shapeMoved(String shapeID, int dx, int dy) {
		moveLocation(dx, dy);
	}

	@Override
	public void shapeStartDrag(String shapeID) {
		// TODO Auto-generated method stub

	}

	@Override
	public void shapeEndDrag(String shapeID) {
		// TODO Auto-generated method stub

	}

	@Override
	public void shapeClicked(String shapeID, int x, int y) {
		stopMoving();

	}

	@Override
	public void shapeRightClicked(String shapeID, int x, int y) {
		resumeMoving();
	}

	@Override
	public void mouseEnterShape(String shapeID, int x, int y) {
	}

	@Override
	public void mouseExitShape(String shapeID, int x, int y) {
	}

}
