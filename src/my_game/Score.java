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
    private Text scoreText, timeText;
    private String scoreId, timeId;
    private long currTime = 0;

    public void addToCanvas() {
		GameCanvas canvas = Game.UI().canvas();
        scoreId = "scorid";
		scoreText = new Text(scoreId, getScoreString(), posX, posY);
		scoreText.setColor(Color.GREEN);
		scoreText.setFontName("Helvetica");
		scoreText.setFontSize(30);
		canvas.addShape(scoreText);

        timeId = "timeid";
        timeText = new Text(timeId, getTimeString(), posX, posY + posY);
		timeText.setColor(Color.GREEN);
		timeText.setFontName("Helvetica");
		timeText.setFontSize(30);
		canvas.addShape(timeText);

	}

    public String getScoreString(){
        return "Score : " + Integer.toString(score);
    }

    public String getTimeString(){
        System.out.println("Time : " + Long.toString(currTime/1000));
        return "Time : " + Long.toString(currTime/1000);
    }

    public int getScore(){
        return this.score;
    }

    public void updateScore(int score){
        this.score = score;
        scoreText.setText(getScoreString());
    }

    public void updateTime(){
        this.currTime = PeriodicLoop.elapsedTime();
        timeText.setText(getTimeString());
        //System.out.println(this.currTime);
    }
    
}
