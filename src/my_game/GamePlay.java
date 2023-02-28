package my_game;

import game.Game;
import gui.GameCanvas;
import main.MyContent;
import shapes.Text;
import java.awt.Color;

public class GamePlay {
	private int stage = 0;
	private MyContent content;
	private final String[] backGroudImages = { "resources/wall2.jpg", "resources/wall1.jpg" };
	private int imageIndex = 0;
	//private final String imageID = "backgroundimage";

	public void handleGamePlay(MyContent content) {
		this.content = content;
		handleSoldier();
		handleStairs();
		handleStage();
		handleScore();
		handleRank();
		isGameOver();
		isGameWin();
		// music();
	}

	private void handleStage() {
		if (!(stage == content.score().getScore() / 10)) {
			stage = content.score().getScore() / 10;
			content.stairs().decreaseMaxStairWidth();
		}
		if (content.score().getScore() > 0 && content.score().getScore() % 10 == 0)
			content.stairs().updateColor();
		;
	}

	private void handleSoldier() {
		GameCanvas canvas = Game.UI().canvas();
		int maxHeight = canvas.getBounds().height;
		int maxWidth = canvas.getBounds().width;
		if (content.soldier().getCenterLocation().x >= maxWidth || content.soldier().getCenterLocation().x < 0) {
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

	public void handleRank() {
		content.rank().updateRank(stage);
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
				t1.setFontName("Palatino");
				t1.setFontSize(100);
				canvas.addShape(t1);
				Game.audioPlayer().stop();
			}
		}
	}

	private void isGameWin() {
		if (content.rank().isMaxRank()){
			GameCanvas canvas = Game.UI().canvas();
			Text t1 = new Text("gamewin", "YOU_WIN", 300, 500);
			t1.setColor(Color.GREEN);
			t1.setFontName("Palatino");
			t1.setFontSize(100);
			canvas.addShape(t1);
			Game.audioPlayer().stop();
		}

	}

	public void changSetUp()
	{
		imageIndex++;
		imageIndex = imageIndex % backGroudImages.length;
		Game.UI().canvas().setBackgroundImage(backGroudImages[imageIndex]);
		content.soldier().nextImage();
		content.stairs().updateColor();
	}
}
