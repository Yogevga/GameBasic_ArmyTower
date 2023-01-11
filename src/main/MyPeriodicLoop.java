package main;

import game.Game;
import game.PeriodicLoop;
import gui.GameCanvas;
import gui.Sleeper;
import my_game.MyCharacter;
import my_game.Point;
import my_game.Pokimon;
import my_game.Soldier;
import my_game.Stairs;
import shapes.Shape;

public class MyPeriodicLoop extends PeriodicLoop {

	private MyContent content;
	private boolean overlap_flag = false;
	private int counter = 0;

	public void setContent(MyContent content) {
		this.content = content;
	}

	@Override
	public void execute() {
		// Let the super class do its work first
		super.execute();

		// You can comment this line if you don't want the pokimon to move.
		redrawSoldier();
		//redrawCharacter();
		
	
		// TODO
		// Redraw your character periodically by calling the correct method

	}

	private boolean checkoverlap() {
		Point p1 = content.pokeball().getLocation();
		Point p2 = content.pokimon().getLocation();
		double point_dist = p1.calcDist(p2);
		// System.out.println(point_dist);
		return point_dist < 50;
	}

	private void redrawSoldier() {
		content.soldier().move();
		GameCanvas canvas = Game.UI().canvas();
		int maxHeight = canvas.getBounds().height;
		int maxWidth = canvas.getBounds().width;
		if (content.soldier().getLocation().x >= maxWidth || content.soldier().getLocation().x < 0) {
			content.soldier().switchDirectionPolicy();
		} else if (content.soldier().getLocation().y >= maxHeight || content.soldier().getLocation().y < 100) {
			content.soldier().moveLocation(0,20);
			content.stairs().updateStairs(20);
			content.stairs().printXY();
			
		}
		// check on stairs
		int cur_x = content.soldier().getLocation().x ;
		int cur_y = content.soldier().getLocation().y;
		int stairNum = content.stairs().checkPointOnStairs( new Point(cur_x,cur_y) );
		if ( stairNum >= 0)
		{
			content.soldier().setOnStair(true);
			content.score().updateScore(stairNum);
		}
		else content.soldier().setOnStair(false);
		

	}

	// TODO
	// change to common method
	private void checkOutofBounds() {
		GameCanvas canvas = Game.UI().canvas();
		int maxHeight = canvas.getBounds().height;
		int maxWidth = canvas.getBounds().width;
		if (content.pokimon().getLocation().x >= maxWidth || content.pokimon().getLocation().x < 0) {
			content.pokimon().switchDirectionPolicy();
		} else if (content.pokimon().getLocation().y >= maxHeight || content.pokimon().getLocation().y < 0) {
			content.pokimon().switchDirectionPolicy();
		}
		if (content.pokeball().getLocation().x >= maxWidth || content.pokeball().getLocation().x < 0) {
			content.pokeball().switchDirectionPolicy();
		} else if (content.pokeball().getLocation().y >= maxHeight || content.pokeball().getLocation().y < 0) {
			content.pokeball().switchDirectionPolicy();
		}
	}

	private void redrawCharacter() {

		int max_flips = 10;
		GameCanvas canvas = Game.UI().canvas();

		// TODO
		// Remove the comment from the next line so you can easily
		// access your character

		MyCharacter pokeball = content.pokeball();

		// Since this function is called every interval, it will also be called
		// before the character is created. Therefore, we check if the character
		// exists and if not, we return without doing anything.

		if (pokeball == null)
			return;
		pokeball.move();
		if (!overlap_flag && checkoverlap()) {
			content.pokimon().setLocation(content.pokeball().getLocation());
			content.pokimon().stopMoving();
			canvas.hide(content.pokimon().getImageID());
			Sleeper.sleep(500);
			pokeball.setImage(2);
			pokeball.stopMoving();
			overlap_flag = true;
		}
		if (overlap_flag && counter < max_flips) {
			// flicker
			canvas.flipStatus(pokeball.getImageID());
			counter++;
			if (counter == max_flips) {
				canvas.show(pokeball.getImageID());
			}
		}

		int maxHeight = canvas.getBounds().height;
		int maxWidth = canvas.getBounds().width;
		if (content.pokeball().getLocation().x >= maxWidth || content.pokeball().getLocation().x < 0) {
			content.pokeball().switchDirectionPolicy();
		} else if (content.pokeball().getLocation().y >= maxHeight || content.pokeball().getLocation().y < 0) {
			content.pokeball().switchDirectionPolicy();
		}
		// TODO
		// Call the canvas to change the shape properties according to
		// its current property values
		// You can get the shape using canvas.getShape(id) with the id of your character
		// Then you can cast it so you can refer to its specific properties.
		// For example, if your shape is a Circle you can use:
		// Circle circle = (Circle) canvas.getShape(id)
		// and then change the specific Circle properties.
	}
}
