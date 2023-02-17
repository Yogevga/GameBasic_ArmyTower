package main;

import java.awt.Color;

import javax.swing.text.ChangedCharSetException;

import game.Game;
import game.GameContent;
import gui.GameCanvas;
import my_game.Pokimon;
import my_game.Score;
import my_game.Soldier;
import my_game.Stairs;
import my_game.GamePlay;
import my_game.MyCharacter;
import my_game.MyPolygon;
import my_game.Point;

public class MyContent extends GameContent {
	private Pokimon pokimon;
	private MyPolygon myPolygon;
	private MyCharacter pokeball;
	private Soldier soldier;
	private Stairs stairs;
	private Score score;
	private GamePlay gameplay;

	@Override
	public void initContent() {
		//pokimon = new Pokimon();
		//pokimon.setLocation(new Point(100, 100));
		//  Point[] points = {
		//  		new Point(100, 100),
		//  		new Point(200, 100),
		//  		new Point(250, 100),
		//  		new Point(300, 100),
		//  		new Point(150, 100),
		//  		new Point(100, 400),
		//  		new Point(400, 400)
		//  };
		 Point[] points = new Point[6];
		 for (int i = 0; i < points.length; i++) {
			int x = (int) (850*Math.random());
			points[i] = new Point(x, 100 + 100*i);
		 }
		 
		stairs = new Stairs(points);
		score = new Score();
		soldier = new Soldier();
		soldier.setLocation(new Point(660, 550));
		gameplay = new GamePlay();
	}

	public Pokimon pokimon() {
		return pokimon;
	}

	public Soldier soldier() {
		return soldier;
	}

	public MyCharacter pokeball() {
		return pokeball;
	}

	public MyPolygon polygon() {
		return myPolygon;
	}

	public Stairs stairs(){
		return stairs;
	}

	public GamePlay gamePlay(){
		return gameplay;
	}

	public MyCharacter myCharacter() {
		return pokeball;
	}

	public Score score(){
		return score;
	}

	public void addCharacter() {
		pokeball = new MyCharacter();
		// TODO
		// Add your character visual representation to the canvas using its
		// addToCanvas() method.
		pokeball.addToCanvas();
	}

	// TODO
	// create a changeCharacter method and change inside all the properties you
	// like.
	public void changeCharacter() {
		Point randpoint = new Point((int) (100 * (Math.random() - 0.5)), (int) (100 * (Math.random() - 0.5)));
		pokeball.setLocation(randpoint);
	}
}
