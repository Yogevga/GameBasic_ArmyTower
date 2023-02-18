package main;

import game.GameContent;
import my_game.Score;
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
	private GamePlay gameplay;

	@Override
	public void initContent() {
		Point[] points = new Point[6];
		for (int i = 0; i < points.length; i++) {
			int x = (int) (850 * Math.random());
			points[i] = new Point(x, 100 + 100 * i);
		}

		stairs = new Stairs(points);
		score = new Score();
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
}
