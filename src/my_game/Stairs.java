package my_game;

import java.awt.Color;

import game.Game;
import gui.GameCanvas;
import shapes.Rectangle;

public class Stairs {
    private Rectangle visStairs[];
    // private int stairsCount = 10;
    private int stairsHeight = 20;

    public Stairs(Point[] points) {
        visStairs = new Rectangle[points.length];
        for (int i = 0; i < points.length; i++) {
            int x = points[i].getX();
            int y = points[i].getY();
            int width = (int) Math.max(400 * Math.random(), 150);
            width = Math.min(width, 200);
            int height = stairsHeight;
            String id = Integer.toString(i);
            visStairs[i] = (Rectangle) new Rectangle(id, x, y, width, height);
            visStairs[i].setIsFilled(true);
            visStairs[i].setFillColor(Color.blue);
        }
    }

    public void addToCanvas() {
        GameCanvas canvas = Game.UI().canvas();
        // Image image = new Image(getImageID(), getImageName(), 200, 200, location.x,
        // location.y);
        // image.setShapeListener(this);
        // image.setzOrder(4);
        for (int i = 0; i < visStairs.length; i++) {
            canvas.addShape(visStairs[i]);
        }

    }

    public int checkPointOnStairs(Point point) {
        int stairNum = 0;
        for (int index = 0; index < visStairs.length; index++) {
            Rectangle tmp = visStairs[index];
            if (tmp.getPosX() < (point.x + 110) && tmp.getPosX() + tmp.getWidth() > (point.x + 110)) // check on X
            {
                System.out.println( "tmp.y = " + tmp.getPosY() + ","+ (point.y+200) + " = point y, ");
                if (tmp.getPosY() > (point.y + 200) && (tmp.getPosY() - tmp.getHeight() - 10) < (point.y+200) ) 
                {
                    stairNum = index;
                    return stairNum;
                }
            }
        }
        return stairNum;
    }

}