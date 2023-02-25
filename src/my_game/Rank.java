package my_game;
import game.ShapeListener;
import gui.GameCanvas;
import game.Game;
import game.PeriodicLoop;
import shapes.*;
import java.awt.Color;


public class Rank {
    private Point location;
	private int rankImageWidth = 220;
	private int rankImageHeight = 220;

	private final String[] images = {"resources/SegenMishne.png","resources/Segen.png", "resources/Seren.png","resources/RavSeren.png","resources/SganAluf.png","resources/AlufMishne.png","resources/TatAluf.png","resources/Aluf.png", "resources/RavAluf.png"};
	private int imageIndex = 0;
	private final String imageID = "rank";
    private int rank = 0;
	
    public void addToCanvas() {
		GameCanvas canvas = Game.UI().canvas();
		Image image = new Image(getImageID(), getImageName(), rankImageWidth, rankImageHeight, 930, 90);
		//image.setShapeListener(this);
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
        Game.UI().canvas().hide(imageID);
        Game.UI().canvas().changeImage(imageID, getImageName(), 220, 200);
        Game.UI().canvas().show(imageID);
	}

    public String getRankImage(){
        return images[0];
    }

    public void updateRank(int rank){
        //System.out.println("curr rank: " + rank + "internal rank : " + this.rank);
        if (this.rank == rank) return;
        this.rank = rank;
        setImage(this.rank);
    }
}
