package my_game;

import java.awt.Color;

import game.Game;
import gui.GameCanvas;

public class Stairs {

    private int MAX_WIDTH = 400;
    private final int MIN_WIDTH = 150;
    private Floor visStairs[];
    private int stairsCount = 0;
    private final int STAIR_HEIGHT = 20;
    private Color[] stairColors = {
        Color.CYAN,Color.BLUE,Color.BLACK,Color.GREEN,Color.MAGENTA
    };
    private int currColor = 0;

    public Stairs(Point[] points) {
        visStairs = new Floor[points.length];
        Color c = getColor();
        for (int i = 0; i < points.length; i++) {
            int x = points[i].getX();
            int y = points[i].getY();
            int width = generateWidth();
            int height = STAIR_HEIGHT;
            String id = Integer.toString(i);
            visStairs[i] = (Floor) new Floor(id, x, y, width, height);
            visStairs[i].setIsFilled(true);
            visStairs[i].setFillColor(c);
            stairsCount++;
        }
    }

    private Color getColor() {
        currColor = (currColor+1) % stairColors.length;
        return stairColors[currColor];
    }

    public void updateColor(){
        Color c = getColor();
        for (int i = 0; i < visStairs.length; i++) {
            visStairs[i].setFillColor(c);;
        }
    }

    public void changeStairsColor(){
        for (int i = 0; i < visStairs.length; i++) {
            visStairs[i].setColor(getNextColor());
        }
    }

    private int generateWidth() {
        int width = MIN_WIDTH + (int)( (MAX_WIDTH - MIN_WIDTH) * Math.random() );
        return width;
    }

    public void decreaseMaxStairWidth(){
        this.MAX_WIDTH -= 20;
        if (this.MAX_WIDTH < MIN_WIDTH) MAX_WIDTH = MIN_WIDTH;
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
                int x = (int) (950 * Math.random());
                int y = maxStairHeight() - 100;
                Point point = new Point(x, y);
                visStairs[i].moveToLocation(x, y);
                visStairs[i].setWidth(generateWidth());
                //System.out.println("Moved Stair " + id + " to " + point.toString());
                stairsCount++;
            }
        }
    }

    private int maxStairHeight(){
        int minStairYpos = Integer.MAX_VALUE;
        for (int i = 0; i < visStairs.length; i++) {
            if (visStairs[i].getPosY() < minStairYpos) minStairYpos = visStairs[i].getPosY();
        }
        return minStairYpos;
    }

    private Color getNextColor() {
        return null;
    }

    public void addToCanvas() {
        GameCanvas canvas = Game.UI().canvas();
        for (int i = 0; i < visStairs.length; i++) {
            canvas.addShape(visStairs[i]);
        }
    }

    public int checkPointOnStairs(Point point) {
        int stairNum = -1;
        for (int index = 0; index < visStairs.length; index++) {
            Floor tmp = visStairs[index];
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
            Floor r = (Floor) (canvas.getShape(id));
            str += Integer.toString(r.getPosX());
            str += " ,";
            str += Integer.toString(r.getPosY());
            //System.out.println(str);
        }
        //System.out.println("--");
    }
}