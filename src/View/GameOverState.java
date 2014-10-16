package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class GameOverState extends GameState {
	private Rectangle playAgain = new Rectangle((PANEL_WIDTH/2)-(MENU_ICON_WIDTH/2),PANEL_HEIGHT/4,MENU_ICON_WIDTH,MENU_ICON_HEIGHT);
	private Rectangle exitGame = new Rectangle((PANEL_WIDTH/2)-(MENU_ICON_WIDTH/2),playAgain.y + 100,MENU_ICON_WIDTH,MENU_ICON_HEIGHT);
	private Rectangle newGame = new Rectangle((PANEL_WIDTH/2)-(MENU_ICON_WIDTH/2),PANEL_HEIGHT/4,MENU_ICON_WIDTH,MENU_ICON_HEIGHT);//testing
	
	private boolean onPlayAgain = false;
	private boolean onExitGame  = false;
	
	private GameContext context;
	private MazeCanvas canvas;  
	
	private String title = "Game Over";
	
	public GameOverState(MazeCanvas canvas) {
		this.canvas = canvas;
	}
	
	@Override
	protected void context(GameContext context) {
		this.context = context;
		
	}
	
	@Override
	public void draw( Graphics2D g) {
			super.draw(g);
			g.setFont(new Font("Arial", Font.BOLD, 40));
			g.setColor(new Color(255,255 ,255,215));
			g.drawString(title,(playAgain.width/2)+40, 35);
			
			Color newGameColour;
			 
			if(onPlayAgain ){
				newGameColour = new Color(255, 128,128,200);
		    }
			else{
	
				newGameColour = new Color(255, 128,128,128); 
			}
			
	        g.setFont(new Font("Arial", Font.BOLD, 20));
	        g.setColor(newGameColour);
	        g.fillRect(playAgain.x, playAgain.y, playAgain.width, playAgain.height);
	        g.setColor(Color.BLACK);
	        g.drawString("Play Again", playAgain.x+(playAgain.width/4), playAgain.y+playAgain.height-5);

			if(onExitGame ){
				newGameColour = new Color(255, 128,128,200);
		    }
			else{
	
				newGameColour = new Color(255, 128,128,128); 
			}
			
	        g.setColor(newGameColour);
	        g.fillRect(exitGame.x, exitGame.y, exitGame.width, exitGame.height);
	        g.setColor(Color.BLACK);
	        g.drawString("Exit Game", exitGame.x+(exitGame.width/4), exitGame.y+exitGame.height-5); 
	        
	        
	        g.setFont(new Font("Arial", Font.BOLD, 12));
			g.setColor(Color.WHITE);
			g.drawString("By:",(newGame.width/2)+40, PANEL_HEIGHT-86);
			g.drawString("Tuan Nguyen",(newGame.width/2)+40, PANEL_HEIGHT-74);
			g.drawString("Stephen Walmsley",(newGame.width/2)+40, PANEL_HEIGHT-62);
			g.drawString("Michelet Chery",(newGame.width/2)+40, PANEL_HEIGHT-50);
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void mouseMoved(MouseEvent e){
		       	if((playAgain.x < e.getX() &&  e.getX() < playAgain.x+playAgain.width) && (playAgain.y < e.getY() &&  e.getY() < playAgain.y+playAgain.height)){
		    		onPlayAgain = true;
		    	}
		    	else{
		    		onPlayAgain = false;
		    	}
		    	
		    	
		    	
		    	if((exitGame.x < e.getX() &&  e.getX() < exitGame.x+exitGame.width) && (exitGame.y < e.getY() &&  e.getY() < exitGame.y+exitGame.height)){
		    		onExitGame = true;
		    	}
		    	else{
		    		onExitGame = false;
		    	}

		    }
		    @Override
		    public void mousePressed(MouseEvent e){
		    	if((playAgain.x < e.getX() &&  e.getX() < playAgain.x+playAgain.width) && (playAgain.y < e.getY() &&  e.getY() < playAgain.y+playAgain.height)){
		    		this.context.setState(new ChooseCharacterState(canvas));
		    		canvas.removeMouseEvent(this);
		    	}
		    	
		    	
		    	if((exitGame.x < e.getX() &&  e.getX() < exitGame.x+exitGame.width) && (exitGame.y < e.getY() &&  e.getY() < exitGame.y+exitGame.height)){
		    		this.context.setState(new ExitGameState());
		    	}
		    	
		    }

}
