
package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener {
	public enum Direction {
		RIGHT, LEFT, UP, DOWN;
	}

	public KeyListener keyListener = new KeyListener() {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			characterTyped(e.getKeyChar());
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
				directionalKeyPressed(Direction.RIGHT);
				break;
			case KeyEvent.VK_LEFT:
				directionalKeyPressed(Direction.LEFT);
				break;
			case KeyEvent.VK_UP:
				directionalKeyPressed(Direction.UP);
				break;
			case KeyEvent.VK_DOWN:
				directionalKeyPressed(Direction.DOWN);
				break;
			case KeyEvent.VK_A:
				aKeyPressed();
				break;
			case KeyEvent.VK_D:
				dKeyPressed();
				break;		
			case KeyEvent.VK_W:
				wKeyPressed();
				break;		
			case KeyEvent.VK_S:
				sKeyPressed();
				break;
			default:
				// TODO: add more keys
				break;
			}
		}
	};

	protected GameContent content;

	public KeyboardListener() {
		this.content = Game.Content();
	}

	// This function is a placeholder and should be overridden in derived specific
	// buttons
	public void directionalKeyPressed(Direction direction) {
	}

	// This function is a placeholder and should be overridden in derived specific
	// buttons
	public void characterTyped(char c) {
	}
	
	// This function is a placeholder and should be overridden in derived specific
	// buttons
	public void aKeyPressed() {
	}

	public void dKeyPressed() {
	}

	public void wKeyPressed() {
	}

	public void sKeyPressed() {
	}
	
	// This function is a placeholder and should be overridden in derived specific
	// buttons
	public void backSpaceKeyPressed() {
	}
	
	// This function is a placeholder and should be overridden in derived specific
	// buttons
	public void spaceKeyPressed() {
	}
	
	// This function is a placeholder and should be overridden in derived specific
		// buttons
	public void otherKeyPressed(KeyEvent e) {
	}
	
}
