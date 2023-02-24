package my_game;
import game.ShapeListener;
import gui.GameCanvas;
import game.Game;
import game.PeriodicLoop;
import shapes.*;
import java.awt.Color;


public class Rank implements ShapeListener {
    private Point location;
	private int rankImageWidth = 220;
	private int rankImageHeight = 220;

	private final String[] images = {"resources/SegenMishne.png"};
    //"resources/Segen.png", "resources/Seren.png","resources/RavSeren.png","resources/SganAluf.png","resources/AlufMishne.png","resources/TatAluf.png","resources/Aluf.png", "resources/RavAluf.png"
	private int imageIndex = 0;
	private final String imageID = "rank";
    private Image rank;
	
    public void addToCanvas() {
		GameCanvas canvas = Game.UI().canvas();
		Image image = new Image(getImageID(), getImageName(), rankImageWidth, rankImageHeight, 930, 90);
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
    
    public String getImageName() {
		return images[imageIndex];
	}

	public String getImageID() {
		return this.imageID;
	}
    public void setImage(int index) {
		this.imageIndex = index;
		if (imageIndex == 0) {
			Game.UI().canvas().changeImage(imageID, getImageName(), 220, 200);
		} else {
			Game.UI().canvas().changeImage(imageID, getImageName(), 220, 200);
		}
	}

    public String getRankImage(){
        return images[0];
    }

    //public void updateRank(int score){
       // this.rank = rank;
      //  rankImage.setImage(getScoreString());
    //}

    @Override
    public void shapeMoved(String shapeID, int dx, int dy) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shapeMoved'");
    }

    @Override
    public void shapeStartDrag(String shapeID) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shapeStartDrag'");
    }

    @Override
    public void shapeEndDrag(String shapeID) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shapeEndDrag'");
    }

    @Override
    public void shapeClicked(String shapeID, int x, int y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shapeClicked'");
    }

    @Override
    public void shapeRightClicked(String shapeID, int x, int y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shapeRightClicked'");
    }

    @Override
    public void mouseEnterShape(String shapeID, int x, int y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseEnterShape'");
    }

    @Override
    public void mouseExitShape(String shapeID, int x, int y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExitShape'");
    }
    
}
