package main;

import game.PeriodicLoop;


public class MyPeriodicLoop extends PeriodicLoop {

	private MyContent content;
	
	public void setContent(MyContent content) {
		this.content = content;
	}

	@Override
	public void execute() {
		// Let the super class do its work first
		super.execute();
		// Call GamePlay periodically
		content.gamePlay().handleGamePlay(content);
	}
}
