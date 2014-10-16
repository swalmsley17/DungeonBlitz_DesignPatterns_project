package Maze;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.net.URL;

public abstract class MazeState{
	public static final int PATH = 0;
	public static final int WALL = 1;
	public static final int PLAYER = 2;
	public static final int ENEMY = 3;
	public static final int HEALTH_BONUS = 4; 
	public static final int ATTACK_BONUS = 5;
	public static final int BLOCK_BONUS = 6;
	public static final int GAME_WON = 8;
	public static final int EXIT = 9;
	
	protected int[][] map;  
	protected Image wallTop; 
	protected Image wallBottom; 
	protected Image mazeFloor;
	
	protected Image healthBonus; 
	protected Image attackBonus; 
	protected Image blockBonus;	
	
	protected Point actor;
	protected Point exit;

	protected MazeState(){
		setBounus();
	}
	
	private void setBounus(){
		
		URL health = this.getClass().getClassLoader().getResource("Animated/heart.gif");
		URL attack = this.getClass().getClassLoader().getResource("Animated/ball.gif");
		URL block = this.getClass().getClassLoader().getResource("Animated/block.gif");
		
		this.healthBonus = Toolkit.getDefaultToolkit().createImage(health);
		this.attackBonus = Toolkit.getDefaultToolkit().createImage(attack);
		this.blockBonus = Toolkit.getDefaultToolkit().createImage(block);
	}
	
	public Image getHealthBonus(){
		return this.healthBonus;
	}
	
	public Image getAttackBonus(){
		return this.attackBonus;
	}
	
	public Image getBlockBonus(){
		return this.blockBonus;
	}
	
	public void setActorLocation(Point actor){
		this.map[this.actor.y][this.actor.x] = this.PATH;
		this.actor.setLocation(actor);
		this.map[actor.y][actor.x] = PLAYER;
	}
	
	public Point getExit()
	{
		return exit;
	}
	
	public int[][] getMap(){
		return map;
	}
	
	public Point getActorLocation(){
		return actor; 
	}
	
	public void setWallTop(String location){
		URL wall = this.getClass().getClassLoader()
				.getResource(location);
		this.wallTop = Toolkit.getDefaultToolkit().createImage(wall);	
	}

	
	public void setWallBottom(String location){
		URL wallShadow = this.getClass().getClassLoader()
				.getResource(location);
		
		this.wallBottom = Toolkit.getDefaultToolkit().createImage(wallShadow);		
	}	

	public void setFloor(String location){
		URL floor = this.getClass().getClassLoader()
				.getResource("Animated/dirtfloor.jpg");
		mazeFloor = Toolkit.getDefaultToolkit().createImage(floor);	
	}
	
	public Image getMazeFloor(){
		return this.mazeFloor;
	}	
	
	public Image getTopWall(){
		return this.wallTop;
	}
	
	public Image getBottomWall(){
		return this.wallBottom;
	}	
	
	
	public void printMap(){
		for(int i=0; i<this.map.length; i++){
			for(int j=0; j<this.map[i].length; j++){
				System.out.print(this.map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("\n");
	}	
	
	
	public int getMapValue(int row, int column){//return -1 if range is not valid
		return ((-1<row && row < this.map.length) && (-1<column  && column < this.map[row].length)) ? this.map[row][column] : -1;
	}
	
	protected abstract void reachedExit(MazeStateContext exit);
	
}
