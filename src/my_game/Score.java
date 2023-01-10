package my_game;
import gui.GameCanvas;
import game.Game;
import game.PeriodicLoop;
import shapes.*;
import java.awt.Color;


public class Score {
    private int score = 0;
    private int posX = 50;
	private int posY = 50;
    private Text t1;
    private String id;

    public void addToCanvas() {
		GameCanvas canvas = Game.UI().canvas();
        id = "t1";
		t1 = new Text(id, getScoreString(), posX, posY);
		t1.setColor(Color.GREEN);
		t1.setFontName("Helvetica");
		t1.setFontSize(30);
		canvas.addShape(t1);
	}

    public String getScoreString(){
        return "Score : " + Integer.toString(score);
    }

    public void updateScore(int score){
        this.score = score;
        t1.setText(getScoreString());
    }
    
}
