package View;

import java.awt.Graphics2D;


public class ExitGameState extends GameState{
	@Override
	public void draw(Graphics2D g) { 
		//Stub
		System.exit(1);
	}

	@Override
	protected void context(GameContext context) {
		// TODO Auto-generated method stub
		
	}
}
