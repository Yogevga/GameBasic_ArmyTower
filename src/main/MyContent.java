package main;

import game.GameContent;
import my_game.Score;
import my_game.Rank;
import my_game.Soldier;
import my_game.Stairs;
import my_game.GamePlay;
import my_game.MyPolygon;
import my_game.Point;

public class MyContent extends GameContent {
	private MyPolygon myPolygon;
	private Soldier soldier;
	private Stairs stairs;
	private Score score;
	private Rank rank;
		private GamePlay gameplay;
		

	@Override
	public void initContent() {
		stairs = new Stairs();
		score = new Score();
		rank = new Rank();
		rank.setLocation(new Point(660, 550));
		soldier = new Soldier();
		soldier.setLocation(new Point(660, 550));
		gameplay = new GamePlay();
	}

	public Soldier soldier() {
		return soldier;
	}

	public MyPolygon polygon() {
		return myPolygon;
	}

	public Stairs stairs() {
		return stairs;
	}

	public GamePlay gamePlay() {
		return gameplay;
	}

	public Score score() {
		return score;
	}
	public Rank rank() {
		return rank;
	}
}
