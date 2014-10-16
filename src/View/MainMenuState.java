package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;


public class MainMenuState extends GameState{
	/*Main menu Buttons aids */
	private Rectangle newGame = new Rectangle((PANEL_WIDTH/2)-(MENU_ICON_WIDTH/2),PANEL_HEIGHT/4,MENU_ICON_WIDTH,MENU_ICON_HEIGHT);//testing
	private Rectangle exitGame = new Rectangle((PANEL_WIDTH/2)-(MENU_ICON_WIDTH/2),PANEL_HEIGHT-(PANEL_HEIGHT/4),MENU_ICON_WIDTH,MENU_ICON_HEIGHT);//testing
	
	/*hover states for start menu*/
	private boolean onNewGame = false;
	private boolean onExitGame  = false;

	
	private GameContext context;
	private MazeCanvas canvas;  //Could be code smell
		
	public MainMenuState(MazeCanvas canvas){
		this.canvas = canvas;
	}
	
	@Override
	public void context(GameContext context) {
		this.context = context;
	}	
	
	
	@Override
	public void draw( Graphics2D g) {
			super.draw(g);
		
			g.setFont(new Font("Arial", Font.BOLD, 40));
			g.setColor(new Color(255,255 ,255,215));
			g.drawString("Dungeon Blitz",(newGame.width/2)+40, 35);
			
			
			Color newGameColour;
			 
			if(onNewGame ){
				newGameColour = new Color(255, 128,128,200);
		    }
			else{
	
				newGameColour = new Color(255, 128,128,128); 
			}
	
		    /*New game button*/    
	        g.setFont(new Font("Arial", Font.BOLD, 20));
	        g.setColor(newGameColour);
	        g.fillRect(newGame.x, newGame.y, newGame.width, newGame.height);
	        g.setColor(Color.BLACK);
	        g.drawString("New Game", newGame.x+(newGame.width/4), newGame.y+newGame.height-5);
	
	        
	        
			if(onExitGame ){
				newGameColour = new Color(255, 128,128,200);
		    }
			else{
	
				newGameColour = new Color(255, 128,128,128); 
			}
			
	        /*Exit button*/
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
	

	

    public void mouseMoved(MouseEvent e){
    //	System.out.println("I am inside Main Menu");
    	
    	if((newGame.x < e.getX() &&  e.getX() < newGame.x+newGame.width) && (newGame.y < e.getY() &&  e.getY() < newGame.y+newGame.height)){
    		onNewGame = true;
    	}
    	else{
    		onNewGame = false;
    	}
    	
    	
    	
    	if((exitGame.x < e.getX() &&  e.getX() < exitGame.x+exitGame.width) && (exitGame.y < e.getY() &&  e.getY() < exitGame.y+exitGame.height)){
    		onExitGame = true;
    	}
    	else{
    		onExitGame = false;
    	}
    	
      //  System.out.println(newGame.width);
    }
    @Override
    public void mousePressed(MouseEvent e){
    	if((newGame.x < e.getX() &&  e.getX() < newGame.x+newGame.width) && (newGame.y < e.getY() &&  e.getY() < newGame.y+newGame.height)){
    		this.context.setState(new ChooseCharacterState(canvas));
    		canvas.removeMouseEvent(this);
    	}
    	
    	
    	if((exitGame.x < e.getX() &&  e.getX() < exitGame.x+exitGame.width) && (exitGame.y < e.getY() &&  e.getY() < exitGame.y+exitGame.height)){
    		this.context.setState(new ExitGameState());
    	}
    	
    }




	

}
