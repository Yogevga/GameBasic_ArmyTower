package main;

import game.Game;
import game.PeriodicLoop;
import gui.GameCanvas;
import gui.Sleeper;
import my_game.GamePlay;
import my_game.MyCharacter;
import my_game.Point;
import my_game.Pokimon;
import my_game.Soldier;
import my_game.Stairs;
import my_game.Score;
import shapes.Shape;
import shapes.Text;
import java.awt.Color;
import java.lang.Math;


public class MyPeriodicLoop extends PeriodicLoop {

	private MyContent content;
	private GamePlay gameplay;
	private boolean overlap_flag = false;
	private int counter = 0;

	public void setContent(MyContent content) {
		this.content = content;
	}

	public void setGamePlay(GamePlay gameplay){
		this.gameplay = gameplay;
	}

	@Override
	public void execute() {
		// Let the super class do its work first
		super.execute();
		// Call GamePlay periodically
		content.gamePlay().handleGamePlay(content);
	}
}
