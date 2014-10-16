package View;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.net.URL;

public abstract class GameState extends MouseAdapter {
	/*Set Panel size*/
	protected final int PANEL_WIDTH = 500;
	protected final int PANEL_HEIGHT = 500;
	
	/*Menu buttons Dimension*/
	protected final int MENU_ICON_WIDTH = 200;
	protected final int MENU_ICON_HEIGHT = 25;
		
	
	/*Default Back ground image for start menu*/
	protected final URL dungeon = this.getClass().getClassLoader().getResource("Animated/dungeon.jpg");
	
	/*Default Back ground image for start menu*/
	protected final Image dungeonIm = Toolkit.getDefaultToolkit().createImage(dungeon);	
	
	/*Next state*/
	protected boolean nextState = false;
	
	protected abstract void context(GameContext context);
	
	protected  void draw(Graphics2D g){ //Will remove GameContext from draw and put in own method.
		g.drawImage(dungeonIm, 0, 0, PANEL_WIDTH, PANEL_HEIGHT, null);
	}
	
	
	
}
