package main;

import java.awt.Color;

import javax.swing.text.ChangedCharSetException;

import game.Game;
import game.GameContent;
import gui.GameCanvas;
import my_game.Pokimon;
import my_game.Soldier;
import my_game.MyCharacter;
import my_game.MyPolygon;
import my_game.Point;

public class MyContent extends GameContent {
	private Pokimon pokimon;
	private MyPolygon myPolygon;
	private MyCharacter pokeball;
	private Soldier soldier;

	@Override
	public void initContent() {
		//pokimon = new Pokimon();
		//pokimon.setLocation(new Point(100, 100));
		// Point[] points = {
		// 		new Point(100, 100),
		// 		new Point(130, 50),
		// 		new Point(170, 50),
		// 		new Point(200, 100),
		// 		new Point(220, 170),
		// 		new Point(170, 150),
		// 		new Point(130, 150)
		// };
		// myPolygon = new MyPolygon(points);
		soldier = new Soldier();
		soldier.setLocation(new Point(660, 550));
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

	// DONE
	// create a method with the name myCharacter which returns
	// your character for others to use.
	public MyCharacter myCharacter() {
		return pokeball;
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
