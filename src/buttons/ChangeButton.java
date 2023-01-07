package buttons;

import game.Game;
import gui.GameCanvas;
import main.MyContent;
import my_game.Point;

public class ChangeButton extends GameButton{
	public int state = 0;
	
	public ChangeButton(String id, String name, int posX, int posY) {
		super(id, name, 100, 40, posX, posY);
	}

	@Override
	public void buttonAction() {
		// The basic buttonAction prints the id of the button to the console.
		// Keep the call to super to preserve this behavior or remove it if you don't want the printing.
		super.buttonAction();
		
		MyContent content = (MyContent) Game.Content();
		//content.polygon().exitEditMode();
		//content.polygon().getVisualPolygon().rotate(45);
		content.pokeball().switchDirectionPolicy();
		content.pokeball().switchImage();
		Point point = content.pokeball().getPointByIndex(state);
		content.pokeball().setLocation(point);
		state++;
		state = state % content.pokeball().getNumOfinitPoints();
		System.out.println(state);
}
}
