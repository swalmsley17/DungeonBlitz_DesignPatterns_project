package FightTester;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import View.MazeCanvas;
import View.PlayingGameState.STATE;
import AnimatingImage.Animation;
import Character.Enemy;
import Character.Player;



public class FightSimulator{
	
	private Player player; 
	private Rectangle playerTargetArea;
	private ArrayList<Enemy> enemy;
	private Animation playerAction;
	private Rectangle attackVictim;
	private boolean selectedVictim = false;

	private enum BATTLE_STATE{
		SELECT_TARGET,
		PLAYER,
		PLAYER_AFTER_BATTLE,
		ENEMEY,
		ENEMY_AFTER_BATTLE,
		NOTHING} //later will change to state pattern
	
	private BATTLE_STATE activeTurn;
	
	private double addx, addy;//need better name
	private ArrayList<Rectangle> targetArea; 
	private int currentEnemyAttacking;
	private int targetStopNum;//which target hero is heading toward
	private long battleStart;  
	private long battleEnd; 	
	private STATE activeState;


    public FightSimulator(Player player){
   
        targetArea = new ArrayList<Rectangle>();
        enemy = new ArrayList<Enemy>();
 
		this.player = player; 
		this.player.setVelocity(8);
		this.player.setPoint(0.0, MazeCanvas.PANEL_HEIGHT/2); //will fix
		playerAction = this.player.getStanding();

		
		this.activeState = STATE.FIGHTING;
		this.activeTurn = BATTLE_STATE.SELECT_TARGET;		
    }
  
  public void setEnemyInstance(ArrayList<Enemy> enemy){
	  this.enemy = enemy;
  }
  
  public STATE backToMaze(){
	  
	  return this.activeState;
  }
  
  public void readyToFight(STATE ready){
	  this.activeState = ready;
	  this.activeTurn = BATTLE_STATE.SELECT_TARGET;
	  this.targetArea.clear();
	  	  
  }
    
   private void setEnemyTargetArea(){
	   this.targetArea.clear();//very important
	   for(Enemy group: enemy){
		   this.targetArea.add(new Rectangle((int)group.getX(),(int)group.getY(),group.getStanding().getWidth(),group.getStanding().getHeight()));
		}	  
   }

   
   private void setPlayerTargetArea(){
		this.playerTargetArea = new Rectangle((int)player.getX(),(int)player.getY(),player.getStanding().getWidth(),player.getStanding().getHeight());	  
   }  
    
    
    public void draw(Graphics2D g){

    	switch(activeTurn){
		case SELECT_TARGET:
	    	enemyStartPosition();
			renderSelectedCharacter(g);	
			break;
		case PLAYER:
			playerToTarget();
			break;
		case PLAYER_AFTER_BATTLE:
			playerAfterBattle();
			break;
		case ENEMEY:
			 EnemyToTarget();
			break;	
		case ENEMY_AFTER_BATTLE:
			enemyAfterBattle();
			break;
		case NOTHING:
			//nothing
			break;
		}
		

		renderEnemy(g);
		setPlayerTargetArea();
		setEnemyTargetArea();
		renderPlayer(g);
    	health(g);
 

    }
    
    
    public void renderSelectedCharacter(Graphics2D g){
		if(selectedVictim){
	        g.setColor(new Color(255, 0,0,255));
			g.fillOval(attackVictim.x, attackVictim.y+attackVictim.height-5, attackVictim.width, attackVictim.height/4);
		}
    }

    public void health(Graphics2D g){
    	int i=1;
    	String health = "Hero: "+player.getHealth();
    	
    	g.setColor(new Color(255, 0,0,255));
    	g.drawString(health, 10, 10);
    	
    	  
	   	for(Enemy group: enemy){
	   		health = group.getName()+": "+group.getHealth();
	   		g.drawString(health,100,10*i); 
	   		i++;
	   	}	
    }
      
    
    public void enemyHealth(Graphics2D g){
    	int i=1;   
    	String health = null;
    	g.setColor(new Color(255, 0,0,255));
	   	for(Enemy group: enemy){
	   		health = group.getName()+": "+group.getHealth();
	   		g.drawString(health,30,10*i); 
			i = i*100;
	   	}
    }
    
   private void enemyStartPosition(){
		int i=1;   
	   	for(Enemy group: enemy){
			group.setX(MazeCanvas.PANEL_WIDTH-100);
			group.setY(100*i);	
			i++;
	   	}
   }
    
    
    public void renderEnemy(Graphics2D g){
    	for(Enemy group: enemy){ 		
			Animation currEnemy = group.activeAnimation();
			currEnemy.draw(g, (int)group.getX(), (int)group.getY(), 50, 50);
    	}
    }

    public void renderPlayer(Graphics2D g){
    	playerAction.draw(g,(int)player.getX(),(int)player.getY(),50,50); 
    }   
  
	public void getDistanceToTarget(double startX,double startY, double endX,double endY){
        //Create position vector from points
        double xComponent =  endX - startX ;
        double yComponent =  endY - startY;
        
        double magnitude = Math.sqrt(xComponent * xComponent + yComponent * yComponent);
        
        //Unit vectors of x and y component
        double xUnit = xComponent / magnitude; 
        double yUnit = yComponent / magnitude; 
       
        //need to scale the unit vector by player velocity
        addx = xUnit;
        addy = yUnit;	
	}

	
	private void playerAfterBattle(){
		
		if((int)player.getX() > 0){
			player.setPoint(player.getX() + addx, player.getY() + addy);  
			this.battleStart = System.currentTimeMillis();
			this.battleEnd = battleStart + 1000;//three seconds 
		}
		else
		{
			playerAction = player.getStanding();
			playerAction.setReflect(false);
			
			if(battleEnd <= System.currentTimeMillis()){
			
				if(enemy.size() >0 ){
					activeTurn = BATTLE_STATE.ENEMEY;
				}
				else{
					this.activeState = STATE.IN_MAZE;
				}
			}
		}
	}

	
	
	private void enemyAfterBattle(){
		Enemy enemyPos = enemy.get(currentEnemyAttacking);
		
		if((int)enemyPos.getX() < 300){
			enemyPos.setPoint(enemyPos.getX() + addx, enemyPos.getY() + addy);
		}
		else
		{
			currentEnemyAttacking = (currentEnemyAttacking+1) % enemy.size(); //Thread safe
	
			enemyPos.setAnimation("standing");
			playerAction.setReflect(false);
			activeTurn = BATTLE_STATE.SELECT_TARGET; //Testing
		}
	}	
	
	
    private void EnemyToTarget(){	
    	Rectangle curr = playerTargetArea;

    	if(currentEnemyAttacking == enemy.size()){//hacking
    		currentEnemyAttacking = 0;
    	}
    	 
    	Enemy enemyPos = enemy.get(currentEnemyAttacking);
    	
    	if(!curr.contains(enemyPos.getX(),enemyPos.getY()+(curr.getHeight()/2))){
    		enemyPos.setAnimation("walking");
    		enemyPos.activeAnimation().setReflect(true);
    		
    		getDistanceToTarget(enemyPos.getX(),enemyPos.getY(),player.getX(),player.getY());
    		enemyPos.setPoint(enemyPos.getX() + addx, enemyPos.getY() + addy);
    		
    		this.battleStart = System.currentTimeMillis();
    		this.battleEnd = battleStart + 4000;//three seconds    
    	}
    	
    	else {
    		//attack for a number of seconds
    		if(battleEnd >= System.currentTimeMillis()){
    			enemyPos.setAnimation("fighting");
    			enemyPos.activeAnimation().setReflect(true);
	    		playerAction = player.getGotHit();
    		}
    		else{
    			enemyPos.attack(player);//Testing
    			
    			if(player.getHealth() <=0){
    	//			System.out.println("Hero is Dead.....");	/////////////////////////////////////////////////////////
    				this.activeState = STATE.GAMEOVER;
    			}
    			
    			playerAction = player.getStanding();
    			enemyPos.setAnimation("walking");
    			enemyPos.activeAnimation().setReflect(false);
    			getDistanceToTarget(enemyPos.getX(),enemyPos.getY(),MazeCanvas.PANEL_WIDTH,150*currentEnemyAttacking); 			
    			activeTurn = BATTLE_STATE.ENEMY_AFTER_BATTLE;
    		}
    	}
    }	
    
    
    private void playerToTarget(){	
    	
 //   	System.out.println(targetStopNum);/////////////////////////////////////////Delete
    	
    	if(targetStopNum >= enemy.size()) System.out.println("WE HAVE A MASIVE ERROR"); ///////////
    	
    	Rectangle curr = targetArea.get(targetStopNum);
    	Enemy currEnemy = enemy.get(targetStopNum);
    	
    	if(!curr.contains(player.getX()+curr.width,player.getY()+(curr.height/2))){
    		playerAction.setReflect(false);//A fix for walking backward
    		player.setPoint(player.getX() + addx, player.getY() + addy);
    		
    		this.battleStart = System.currentTimeMillis();
    		this.battleEnd = battleStart + 4000;//three seconds    
    		
    	}
    	else {
    		//attack for a number of seconds
    		if(battleEnd >= System.currentTimeMillis()){
	    		playerAction = player.getFighting();
	    		currEnemy.activeAnimation().setReflect(true);
	    		currEnemy.setAnimation("gotHit");
    		}
    		else{
    			player.attack(currEnemy);
    			
    			if(currEnemy.getHealth() <=0){
    				enemy.remove(targetStopNum);
    				targetArea.remove(targetStopNum);
    				
    			}
    			
    			playerAction = player.getWalking();
    			playerAction.setReflect(true);
    			getDistanceToTarget(player.getX(),player.getY(),0,MazeCanvas.PANEL_HEIGHT/2); 
    			currEnemy.setAnimation("standing");    			
    			activeTurn = BATTLE_STATE.PLAYER_AFTER_BATTLE; 
    		}
    	}
    }	
	
	
	private void walkToTarget(MouseEvent e){
		int count =0;
		for(Rectangle target:targetArea){
			if(target.contains(e.getX(), e.getY())){
				getDistanceToTarget(player.getX(),player.getY(),target.getX(),target.getY());	
				targetStopNum = count;
				activeTurn = BATTLE_STATE.PLAYER;
				break;
			}
			count++;
		}
	}

	public void showSelectedTarget(MouseEvent e){
	
		for(Enemy curr:enemy){		
			//Set to correspond with current active sprite
			attackVictim = new Rectangle((int)curr.getX(),(int)curr.getY(),curr.getStanding().getWidth(),curr.getStanding().getHeight());
		
			if( attackVictim.contains(new Point(e.getX(),e.getY())) ){			
				selectedVictim = true;
				break;//break if mouse is on enemy we wanted to select
			}
			else{
				selectedVictim = false;
			}
		}
	}
	
	
	public void playerActionOnClick(MouseEvent e){
		
		if(enemy.size() <=0){ //Hacking
			this.activeTurn = activeTurn.NOTHING;
		}
		
		if(selectedVictim && this.activeTurn == this.activeTurn.SELECT_TARGET){
			walkToTarget(e);    
			playerAction = player.getWalking();
	        playerAction.resume(); //player walking 
		}		
	}
}