package my_game;

import java.awt.Color;

import game.Game;
import gui.GameCanvas;
import shapes.Rectangle;

public class Stairs {
    private Rectangle visStairs[];
    private int stairsCount = 0;
    private int stairsHeight = 20;

    public Stairs(Point[] points) {
        visStairs = new Rectangle[points.length];
        for (int i = 0; i < points.length; i++) {
            int x = points[i].getX();
            int y = points[i].getY();
            int width = (int) Math.max(400 * Math.random(), 150);
            width = Math.min(width, 400);
            int height = stairsHeight;
            String id = Integer.toString(i);
            visStairs[i] = (Rectangle) new Rectangle(id, x, y, width, height);
            visStairs[i].setIsFilled(true);
            visStairs[i].setFillColor(Color.blue);
            stairsCount++;
        }
    }

    public void updateStairs(int dy) {
        updateStairsHeight(dy);
        removeOldStairs();
    }

    public void updateStairsHeight(int dy) {
        GameCanvas canvas = Game.UI().canvas();
        for (int i = 0; i < visStairs.length; i++) {
            visStairs[i].move(0, dy);
            // String id = Integer.toString(i);
            // canvas.moveShape(id, 0, dy);
        }
    }

    public int getStairsCount() {
        return stairsCount;
    }

    public void removeOldStairs() {
        GameCanvas canvas = Game.UI().canvas();
        for (int i = 0; i < visStairs.length; i++) {
            if (visStairs[i].getPosY() > 600) {
                String id = Integer.toString(i);
                int x = (int) (850 * Math.random());
                int y = maxStairHeight() - 100;
                Point point = new Point(x, y);
                visStairs[i].moveToLocation(x, y);
                System.out.println("Moved Stair " + id + " to " + point.toString());
                stairsCount++;
            }
        }
    }

    private int maxStairHeight(){
        int minHeight = Integer.MAX_VALUE;
        for (int i = 0; i < visStairs.length; i++) {
            if (visStairs[i].getPosY() < minHeight) minHeight = visStairs[i].getPosY();
        }
        return minHeight;
    }

    public void updateToCanvas() {
        GameCanvas canvas = Game.UI().canvas();
        for (int i = 0; i < visStairs.length; i++) {
            String id = Integer.toString(i);
            canvas.deleteShape(id);
            canvas.addShape(visStairs[i]);
        }
    }

    public void addToCanvas() {
        GameCanvas canvas = Game.UI().canvas();
        for (int i = 0; i < visStairs.length; i++) {
            canvas.addShape(visStairs[i]);
        }
    }

    public int checkPointOnStairs2(Point point) {
        int stairNum = -1;
        for (int index = 0; index < visStairs.length; index++) {
            Rectangle tmp = visStairs[index];
            if (tmp.getPosX() < (point.x) && tmp.getPosX() + tmp.getWidth() > (point.x)) // check on X
            {
                // System.out.println( "tmp.y = " + tmp.getPosY() + ","+ (point.y+200) + " =
                // point y, ");
                boolean b = (tmp.getPosY() > (point.y + 200) && (tmp.getPosY() - tmp.getHeight()) < (point.y + 200));
                System.out.println(b + " " + tmp.isInArea(point.x, point.y));
                if (b) {
                    stairNum = index;
                    return stairNum;
                }
            }
        }
        return stairNum;
    }

    public int checkPointOnStairs(Point point) {
        int stairNum = -1;
        for (int index = 0; index < visStairs.length; index++) {
            Rectangle tmp = visStairs[index];
            if (tmp.isInArea(point.x, point.y)) {
                stairNum = index;
                return stairNum;
            }
        }
        return stairNum;
    }

    public void printXY() {
        GameCanvas canvas = Game.UI().canvas();
        for (int i = 0; i < visStairs.length; i++) {
            String str = Integer.toString(visStairs[i].getPosX());
            str += " ,";
            str += Integer.toString(visStairs[i].getPosY());
            str += " , Canvas: ";
            String id = Integer.toString(i);
            Rectangle r = (Rectangle) (canvas.getShape(id));
            str += Integer.toString(r.getPosX());
            str += " ,";
            str += Integer.toString(r.getPosY());
            System.out.println(str);
        }
        System.out.println("--");
    }

}