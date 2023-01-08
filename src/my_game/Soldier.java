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

	private final String[] images = { "resources/soldier1.png" };
	private int imageIndex = 0;
	private final String imageID = "soldier";
	private boolean isMoving = true;
	private boolean isOnStair = false;
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
