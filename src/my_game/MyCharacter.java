package my_game;

import game.Game;
import game.ShapeListener;
import gui.GameCanvas;
import shapes.Image;

//TODO
//Decide if you want to implemet the ShapeListener interface to handle drag and maouse events.
//If so, add it to the class definition and implement the methods you want.
public class MyCharacter implements ShapeListener {

	private Point location = new Point(0, 0);
	public Point[] points = {
		new Point(100, 100),
		new Point(300, 100),
		new Point(300, 300),
		new Point(100, 300),
		new Point(250, 100),
		new Point(50, 400),
		new Point(200, 400),
		new Point(200, 240)
};

	public enum Direction {
		RIGHT(10, 0),
		LEFT(-10, 0),
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

	private Direction directionPolicy = Direction.RIGHT;
	private Direction direction = Direction.RIGHT;

	private final String[] images = { "resources/Open Pokadur.png", "resources/Closed Pokadur.png", "resources/Activated Pokadur.png" };
	private int imageIndex = 0;
	private final String imageID = "pokeball";
	private boolean isMoving = true;
	private int rotation = 0; // In degrees

	public int getNumOfinitPoints(){
		return points.length;
	}

	public Point getPointByIndex(int i){
		return points[i];
	}
	public Point getLocation() {
		return this.location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public void setDirectionPolicy(Direction down) {
		directionPolicy = down;
	}

	public void switchDirectionPolicy() {
		if (directionPolicy == Direction.RIGHT) {
			directionPolicy = Direction.LEFT;
		} else if (directionPolicy == Direction.LEFT) {
			directionPolicy = Direction.RIGHT;
		} else if (directionPolicy == Direction.UP) {
			directionPolicy = Direction.DOWN;
		} else if (directionPolicy == Direction.DOWN) {
			directionPolicy = Direction.UP;
		}
	}

	public void switchImage() {
		setImage(1 - imageIndex);
	}

	public void setImage(int index) {
		this.imageIndex = index;
		if (imageIndex == 0) {
			Game.UI().canvas().changeImage(imageID, getImageName(), 200, 200);
		}
		else {
			Game.UI().canvas().changeImage(imageID, getImageName(), 200, 200);
		}
	}

	public String getImageID() {
		return this.imageID;
	}

	public void moveLocation(int dx, int dy) {
		this.location.x += dx;
		this.location.y += dy;
	}

	public void stopMoving() {
		isMoving = false;
	}

	public void resumeMoving() {
		isMoving = true;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
		Image i = (Image) (Game.UI().canvas().getShape(imageID));
		i.setRotation(rotation);
	}

	public void addToCanvas() {
		GameCanvas canvas = Game.UI().canvas();
		Image image = new Image(getImageID(), getImageName(), 200, 200, location.x, location.y);
		image.setShapeListener(this);
		image.setzOrder(4);
		canvas.addShape(image);
	}

	public void move() {

		if (isMoving) {
			// Move according to policy
			Point desired = new Point(location.x + directionPolicy.xVec(), location.y + directionPolicy.yVec());
			// if move is possible, i.e., maze does not block
			direction = directionPolicy;
			location.x = desired.x;
			location.y = desired.y;
			// After changing the pokeball self location, move also its image in the canvas accordingly.
			Game.UI().canvas().moveToLocation(imageID, location.x, location.y);
		}
	}

	public String getImageName() {
		return images[imageIndex];
	}

	@Override
	public void shapeMoved(String shapeID, int dx, int dy) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		stopMoving();

	}

	@Override
	public void shapeRightClicked(String shapeID, int x, int y) {
		// TODO Auto-generated method stub
		resumeMoving();

	}

	@Override
	public void mouseEnterShape(String shapeID, int x, int y) {
		// TODO Auto-generated method stub
		setRotation(90);
	}

	@Override
	public void mouseExitShape(String shapeID, int x, int y) {
		// TODO Auto-generated method stub
		setRotation(0);
	}

}
