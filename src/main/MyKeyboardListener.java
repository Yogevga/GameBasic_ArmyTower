package main;
import game.Game;
import my_game.Soldier;
import java.awt.event.KeyEvent;

import game.KeyboardListener;

public class MyKeyboardListener extends KeyboardListener{

	private MyContent myContent;
	
	public MyKeyboardListener() {
		super();
		myContent = (MyContent) this.content;
	}

	@Override
	public void directionalKeyPressed(Direction direction) {
		switch (direction) {
		  case RIGHT:
			  //myContent.pokimon().setDirectionPolicy(Pokimon.Direction.RIGHT);
			  myContent.soldier().setDirectionPolicy(Soldier.Direction.RIGHT);
			  break;
		  case LEFT:
			  //myContent.pokimon().setDirectionPolicy(Pokimon.Direction.LEFT);
			  myContent.soldier().setDirectionPolicy(Soldier.Direction.LEFT);
			  break;
		  case UP:
			  //myContent.pokimon().setDirectionPolicy(Pokimon.Direction.UP);
			  //myContent.pokimon().setRotation(myContent.pokimon().getRotation() + 20);
			  myContent.soldier().setDirectionPolicy(Soldier.Direction.UP);
			  break;
		  case DOWN:
			  //myContent.pokimon().setDirectionPolicy(Pokimon.Direction.DOWN);
			  //myContent.pokimon().setRotation(myContent.pokimon().getRotation() - 20);
			  myContent.soldier().setDirectionPolicy(Soldier.Direction.DOWN);
			  break;
		}
	}
	
	@Override
	public void characterTyped(char c) {
		//System.out.println("key character = '" + c + "'" + " pressed.");
	}
	
	@Override
	public void aKeyPressed() {
		//Game.audioPlayer().play("resources/audio/Zahal.wav", 0);
		myContent.gamePlay().changSetUp();
		System.out.println("a key pressed.");
	}

	@Override
	public void dKeyPressed() {
		//myContent.pokeball().setDirectionPolicy(MyCharacter.Direction.RIGHT);
		myContent.soldier().setDirectionPolicy(Soldier.Direction.RIGHT);
		System.out.println("d key pressed.");
	}

	@Override
	public void wKeyPressed() {
		//myContent.soldier().moveLocation(0, -50);
		myContent.soldier().jump();
		//System.out.println("w key pressed.");
	}

	@Override
	public void sKeyPressed() {
		//myContent.pokeball().setDirectionPolicy(MyCharacter.Direction.DOWN);
		myContent.soldier().setDirectionPolicy(Soldier.Direction.UP);
		System.out.println("s key pressed.");
	}
	
	@Override
	public void backSpaceKeyPressed() {
		System.out.println("backSpace key pressed.");
	}
	
	@Override
	public void spaceKeyPressed() {
		myContent.soldier().setDirectionPolicy(Soldier.Direction.UP);
		System.out.println("space key pressed.");
	}
	
	public void otherKeyPressed(KeyEvent e) {
		System.out.println("other key pressed. type:" + e);
	}

	@Override
	public void otherKeyReleased(KeyEvent e) {
		System.out.println("other key Released. type:" + e.getKeyChar());
	}

	@Override
	public void kKeyPressed() {
		myContent.soldier().updateVelocity("goLEFT");
		//System.out.println("K key pressed");
	}

	@Override
	public void lKeyPressed() {
		myContent.soldier().updateVelocity("goRIGHT");
		//System.out.println("L key pressed");
	}
	

	@Override
	public void lKeyReleased(){
		myContent.soldier().updateVelocity("abort");
	}

	@Override
	public void kKeyReleased(){
		myContent.soldier().updateVelocity("abort");
	}
}
