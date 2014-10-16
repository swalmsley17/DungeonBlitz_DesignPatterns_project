package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.JRadioButton;

import AnimatingImage.Animation;
import Character.Player;
import Character.PlayerFactory;

public class ChooseCharacterState extends GameState {
	private Rectangle ranger = new Rectangle((PANEL_WIDTH/2)-(MENU_ICON_WIDTH/2),PANEL_HEIGHT/4,MENU_ICON_WIDTH,MENU_ICON_HEIGHT);	
	private Rectangle warrior = new Rectangle((PANEL_WIDTH/2)-(MENU_ICON_WIDTH/2),ranger.y + 80,MENU_ICON_WIDTH,MENU_ICON_HEIGHT);
	private Rectangle wizard = new Rectangle((PANEL_WIDTH/2)-(MENU_ICON_WIDTH/2),warrior.y + 80,MENU_ICON_WIDTH,MENU_ICON_HEIGHT);
		
	/*hover states for start menu*/
	private boolean onRanger = false;
	private boolean onWarrior = false;
	private boolean onWizard = false;
	
	private GameContext context;
	private MazeCanvas canvas;
	private Player rangerImg,knightImg,wizardImg;
	private Animation standing;
	
	public ChooseCharacterState(MazeCanvas canvas) {
		this.canvas = canvas;
		
		PlayerFactory playerFactory = new PlayerFactory();	
		rangerImg = playerFactory.createPlayer("Ranger");
		knightImg = playerFactory.createPlayer("Knight");
		wizardImg = playerFactory.createPlayer("Wizard");
	}
	
	@Override
	protected void context(GameContext context) {
		this.context = context;		
	}
	
	public void draw(Graphics2D g) {
		super.draw(g);
		
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(new Color(255,255 ,255,215));
		g.drawString("Please pick a character",(ranger.width/2)+40, 35);

		
		standing = rangerImg.getStanding();
		standing.pause();
		standing.draw(g, ranger.x - 60, ranger.y - 20, 50, 50);		

		
		standing = knightImg.getStanding();
		standing.pause();
		standing.draw(g, warrior.x - 60, warrior.y - 20, 50, 50);		
		
		standing = wizardImg.getStanding();
		standing.pause();
		standing.draw(g, wizard.x - 60, wizard.y - 10, 50, 50);
		
		
		Color newGameColour;	
		if(onRanger){
			newGameColour = new Color(255, 128,128,200);
	    }
		else{

			newGameColour = new Color(255, 128,128,128); 
		}
			
		
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(newGameColour);
        g.fillRect(ranger.x, ranger.y, ranger.width, ranger.height);
        g.setColor(Color.BLACK);
        g.drawString("Ranger", ranger.x+(ranger.width/4), ranger.y + ranger.height-5);
        
		if(onWarrior){
			newGameColour = new Color(255, 128,128,200);
	    }
		else{

			newGameColour = new Color(255, 128,128,128); 
		}
		
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(newGameColour);
        g.fillRect(warrior.x, warrior.y, warrior.width, warrior.height);
        g.setColor(Color.BLACK);
        g.drawString("Knight", warrior.x+(warrior.width/4), warrior.y + warrior.height-5);
        
		if(onWizard){
			newGameColour = new Color(255, 128,128,200);
	    }
		else{

			newGameColour = new Color(255, 128,128,128); 
		}
		
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(newGameColour);
        g.fillRect(wizard.x, wizard.y, wizard.width, wizard.height);
        g.setColor(Color.BLACK);
        g.drawString("Wizard", wizard.x+(wizard.width/4), wizard.y + wizard.height-5);

	}
	
	public void mouseMoved(MouseEvent e) {
    	if((ranger.x < e.getX() &&  e.getX() < ranger.x + ranger.width) && (ranger.y < e.getY() &&  e.getY() < ranger.y + ranger.height)){
    		onRanger = true;
    	}
    	else{
    		onRanger = false;
    	}
    	
    	if((warrior.x < e.getX() &&  e.getX() < warrior.x + warrior.width) && (warrior.y < e.getY() &&  e.getY() < warrior.y + warrior.height)){		
    		onWarrior = true;
    	}
    	else{
    		onWarrior = false;
    	}
    	
    	if((wizard.x < e.getX() &&  e.getX() < wizard.x + wizard.width) && (wizard.y < e.getY() &&  e.getY() < wizard.y + wizard.height)){		
    		onWizard = true;
    	}
    	else{
    		onWizard = false;
    	}
	}
	
	public void mousePressed(MouseEvent e) {
		if((ranger.x < e.getX() &&  e.getX() < ranger.x + ranger.width) && (ranger.y < e.getY() &&  e.getY() < ranger.y + ranger.height)) {
    		// Create an instance of ranger character and pass it to the canvas -----> HOW ?
			this.context.setState(new PlayingGameState(canvas,rangerImg));
    		canvas.removeMouseEvent(this);
		}
		
		if((warrior.x < e.getX() &&  e.getX() < warrior.x + warrior.width) && (warrior.y < e.getY() &&  e.getY() < warrior.y + warrior.height)){
    		// Create an instance of warrior character
			this.context.setState(new PlayingGameState(canvas,knightImg));
    		canvas.removeMouseEvent(this);
		}
		
		if((wizard.x < e.getX() &&  e.getX() < wizard.x + wizard.width) && (wizard.y < e.getY() &&  e.getY() < wizard.y + wizard.height)){
    		// Create an instance of wizard character
			this.context.setState(new PlayingGameState(canvas,wizardImg));
    		canvas.removeMouseEvent(this);
		}
	}
	
}
