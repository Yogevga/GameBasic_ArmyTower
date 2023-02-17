package my_game;

import game.Game;
import gui.GameCanvas;
import main.MyContent;
import shapes.Text;
import java.awt.Color;

public class GamePlay {
	private int stage = 0;
	private MyContent content;

	public void handleGamePlay(MyContent content) {
		this.content = content;
		handleSoldier();
		handleStairs();
		handleStage();
		handleScore();
		isGameOver();
	}

	private void handleStage() {
		stage = content.score().getScore() / 10;
	}

	private void handleSoldier() {
		GameCanvas canvas = Game.UI().canvas();
		int maxHeight = canvas.getBounds().height;
		int maxWidth = canvas.getBounds().width;
		if (content.soldier().getLocation().x >= maxWidth || content.soldier().getLocation().x < 0) {
			content.soldier().switchDirectionPolicy();
		} else if (content.soldier().getLocation().y >= maxHeight || content.soldier().getLocation().y < 100) {
			content.soldier().moveLocation(0, 20);
			content.stairs().updateStairs(20);
			// content.stairs().printXY();
		}
		// check on stairs
		int stairNum = content.stairs().checkPointOnStairs(content.soldier().getCenterLocation());
		if (stairNum >= 0) {
			content.soldier().setOnStair(true);
		} else
			content.soldier().setOnStair(false);
		content.soldier().setAddedYvec(stage);
		content.soldier().move_new();
	}

	public void handleStairs() {
		content.stairs().updateStairs(stage);
	};

	public void handleScore() {
		content.score().updateScore(content.stairs().getStairsCount());
		content.score().updateTime();
	};

	private void isGameOver() {
		int scoreThres = 8; // after getting to stair #8, it will be possible to GameOver
		int y_thres = 500;
		int soldier_y = content.soldier().getLocation().y;
		int curr_score = content.score().getScore();
		if (curr_score >= scoreThres) {
			if (soldier_y >= y_thres) {
				GameCanvas canvas = Game.UI().canvas();
				Text t1 = new Text("gameover", "GAME_OVER", 300, 500);
				t1.setColor(Color.RED);
				t1.setFontName("Helvetica");
				t1.setFontSize(100);
				canvas.addShape(t1);
			}
		}
	}

}
