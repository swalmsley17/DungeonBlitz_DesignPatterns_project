
package Model;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

import AnimatingImage.Animation;
import Character.Enemy;
import Character.EnemyFactory;
import Character.LevelOneEnemyFactory;
import Character.Player;
import Maze.MazeState;
import Maze.MazeStateContext;


public class MazeModel implements ModelInterface{
	private MazeStateContext maze;
	private MazeState currentMap;
	
	private Player player; 
	private Animation walking;

	
	public MazeModel(Player player) {
		this.maze = new MazeStateContext();//States for the maze
		currentMap = this.maze.getState();
		
		this.player = player;
		player.setVelocity(8);
		walking = player.getWalking();
		
				
		player.setPoint(currentMap.getActorLocation());//For now, the default starting point.
	}
	
	private void nextLevel(){
		maze.nextMap();
		currentMap = maze.getState();
		player.setPoint(currentMap.getActorLocation());
	}
	
	//Code smells
	public int setPlayerPoint(Point hero)
	{
		if(hero.equals(currentMap.getExit()))
		{
			this.nextLevel();
			return MazeState.EXIT;// return number 9 found exit 
		}

		
		if(getMapValue(hero.y,hero.x) == MazeState.ENEMY){
			
			//refactor
			currentMap.setActorLocation(hero);
			player.setPoint(hero);			
			
			return MazeState.ENEMY; //return number 3 for enemy
		}

		if(getMapValue(hero.y,hero.x) == MazeState.ATTACK_BONUS){
			
			//refactor
			currentMap.setActorLocation(hero);
			player.setPoint(hero);			
			
			return MazeState.ATTACK_BONUS; //return number 5
		}
		
		
		if(getMapValue(hero.y,hero.x) == MazeState.BLOCK_BONUS){
			
			//refactor
			currentMap.setActorLocation(hero);
			player.setPoint(hero);			
			
			return MazeState.BLOCK_BONUS; //return number 6
		}		
		
		if(getMapValue(hero.y,hero.x) == MazeState.HEALTH_BONUS){
			
			//refactor
			currentMap.setActorLocation(hero);
			player.setPoint(hero);			
			
			return MazeState.HEALTH_BONUS; //return number 4
		}		
		
		if(getMapValue(hero.y,hero.x) == MazeState.GAME_WON){
			
			//refactor
			currentMap.setActorLocation(hero);
			player.setPoint(hero);			
			
			return MazeState.GAME_WON; //return number 8
		}
		
		
		currentMap.setActorLocation(hero);
		player.setPoint(hero);		
		
		return 0;
	}
	
	public ArrayList<Enemy> randomEnemy(){
		
		ArrayList<Enemy> enemy = new ArrayList<Enemy>();
		ArrayList<String> names = new ArrayList<String>();
		EnemyFactory factory = new LevelOneEnemyFactory();		
		int variousEnemy = 0;
		
		names.add("Imp");
		names.add("Goblin");
		names.add("Skeleton");
				
		int numberOfEnemy = 1 + (int)(Math.random() * names.size());//number of enmey on battle field
		
		for (int i = 0; i < numberOfEnemy; i++) {
			variousEnemy = (int)(Math.random()*names.size());
			Enemy temp = factory.createEnemy( names.get(variousEnemy));
			enemy.add(temp );
		}
		
		return enemy;
	}
	
	public void lookEast(){
		walking.setReflect(false);
	}
	
	public void lookWest(){
		walking.setReflect(true);
	}
	
	public Image getTopImage(){
		return currentMap.getTopWall();
	}
	
	public Image getBottomImage(){
		return currentMap.getBottomWall();
	}	
	
	
	public int[][] getMap(){
		return currentMap.getMap();
	}
	
	public void printMap(){
		this.currentMap.printMap();
	}
	
	public Point getPlayerPoint(){
		return this.player.getPoint();
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public int getMapValue(int row, int column){//return -1 if range is not valid
		return currentMap.getMapValue(row, column);
	}

	@Override
	public void resume() {
		walking.resume();		
	}

	@Override
	public void pause() {
		walking.pause();
	}

	@Override
	public Image getHealthBonus() {
		return currentMap.getHealthBonus();
	}

	@Override
	public Image getAttackBonus() {
		return currentMap.getAttackBonus();
	}

	@Override
	public Image getBlockBonus() {
		return currentMap.getBlockBonus();
	}


}