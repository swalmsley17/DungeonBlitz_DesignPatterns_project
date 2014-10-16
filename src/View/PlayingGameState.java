package View;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import AnimatingImage.Animation;
import Character.Enemy;
import Character.Player;
import Control.ControlInterface;
import Control.MazeControl;
import FightTester.FightSimulator;
import Maze.MazeState;
import Model.MazeModel;
import Model.ModelInterface;


public class PlayingGameState extends GameState{

	/** Padding from borders */
	private final int PADDING = 10;

	/** The glyph size in pixels for the characters */
	private  final int GLYPH_SIZE = 50;

	private final int velocity = 8; // Will be in character	
	
	/* Will have to fix those once I have characters */
	private Image topWall;
	private Image BottomWall;
	private Image floorWall;
	
	private Image healthBonus; 
	private Image attackBonus; 
	private Image blockBonus;	
	
	/* Will have to fix those once I have characters */
	private Point hero;
	private Point scroll;

	private final int WALLWIDTH = 75;
	private final int WALLHEIGHT = 90;
		
	private int[][] map;
	private Player player;
	private Animation walking;
	
	
	 private ModelInterface model;
	 private ControlInterface control;
	 private MazeCanvas canvas;
	private GameContext context;
	
	 private FightSimulator fight;
	 public static  enum STATE{
			IN_MAZE,
			FIGHTING,
			GAMEOVER}
	 
	private STATE activeState;
	
	public PlayingGameState(MazeCanvas canvas,Player selectedplayer) {					
		this.canvas = canvas;
		canvas.getMouseListeners();
		
		/* MVC */
		this.model = new MazeModel(selectedplayer);
		this.control = new MazeControl(model, this);
		
		this.activeState = STATE.IN_MAZE; //Testing current state in maze
		
		
		this.fight = new FightSimulator(selectedplayer);
		
		
		this.player = model.getPlayer(); 
		walking = this.player.getWalking();//might change 
		
		hero = player.getPoint();
		scroll = player.getPoint();

		updateMaze();
		
	}
	
	
	public void updateMaze(){
		this.setMap(model.getMap());
		this.setHeroPoint(model.getPlayerPoint());
		
		topWall = model.getTopImage();
		BottomWall = model.getBottomImage();
		
		this.attackBonus = model.getAttackBonus();
		this.healthBonus = model.getHealthBonus();
		this.blockBonus = model.getBlockBonus();
	}
	
	
	public void setEnemyInstance(ArrayList<Enemy> enemy){
		fight.setEnemyInstance(enemy);
	}
	
	public void changeStateToBattle(){
		this.activeState = STATE.FIGHTING;
		fight.readyToFight(STATE.FIGHTING);
	}

	public void changeStateToMaze(){
		this.activeState = STATE.IN_MAZE;	
//		System.out.println("activeState "+this.activeState);
	}	
	
	
	
	public void addKeyListener(KeyListener keyPress){
		this.canvas.addKeyListener(keyPress);
	}

	
	@Override
	public void draw(Graphics2D g) { 
		
		switch(activeState){
		case IN_MAZE:
//			renderFloor(g);
			render3DMaze(g);
			renderPlayer(g);
			scroll();
			break;
		case FIGHTING:
			fight.draw(g);
			activeState = fight.backToMaze();
			break;
		case GAMEOVER:
			gameOver();
			break;
		}
	}

	private void gameOver(){
		this.context.setState(context.getGameOverState());
		this.canvas.removeAll();
	}
	
	private void renderFloor(final Graphics2D g){
	//	g.setBackground(Color.green);
	//	g.drawImage(img4,scroll.x,scroll.y,WALLWIDTH*map.length, WALLHEIGHT*map.length, null);

	}
	

	private void renderPlayer(final Graphics2D graphics) {
		walking.draw(graphics,hero.x + scroll.x,hero.y +scroll.y,GLYPH_SIZE,GLYPH_SIZE);
	}

	
	private void render3DMaze(final Graphics2D g){
		 bottomMazeLayer(g);
		 topMazeLayer(g);
	}
	
	//need refractor
	private void bottomMazeLayer(final Graphics2D g) {
		g.setColor(Color.DARK_GRAY);
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == MazeState.WALL && map[(i+1)% map.length][j] != MazeState.WALL) {//wild code
				
					g.drawImage(BottomWall, j * this.WALLWIDTH + scroll.x, i*this.WALLHEIGHT + scroll.y, this.WALLWIDTH,this.WALLHEIGHT+15, null);
				}
				
			}
		}
	}
	
	
	//need refractor
	private void topMazeLayer(final Graphics2D g) {
		g.setColor(Color.RED);
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == MazeState.WALL ) {
					
					if(map[(i+1)% map.length][j] != MazeState.WALL){

						g.drawImage(topWall, j * this.WALLWIDTH + scroll.x, i
							* this.WALLHEIGHT + scroll.y, this.WALLWIDTH,
							this.WALLHEIGHT, null);					
					}
					else{
						g.drawImage(topWall, j * this.WALLWIDTH + scroll.x, i
								* this.WALLHEIGHT + scroll.y, this.WALLWIDTH,
								this.WALLHEIGHT, null);
					}
				}
				else{
					if (map[i][j] == MazeState.ATTACK_BONUS){
						g.drawImage(this.attackBonus, j * this.WALLWIDTH + scroll.x, i
								* this.WALLHEIGHT + scroll.y, this.GLYPH_SIZE,
								this.GLYPH_SIZE, null);						
					}
					
					
					if (map[i][j] == MazeState.BLOCK_BONUS){
						g.drawImage(this.blockBonus, j * this.WALLWIDTH + scroll.x, i
								* this.WALLHEIGHT + scroll.y, this.GLYPH_SIZE,
								this.GLYPH_SIZE, null);						
					}
					
					
					if (map[i][j] == MazeState.HEALTH_BONUS){
						g.drawImage(this.healthBonus, j * this.WALLWIDTH + scroll.x, i
								* this.WALLHEIGHT + scroll.y, this.GLYPH_SIZE,
								this.GLYPH_SIZE, null);						
					}
					
				}
				
			}
		}
	}	
	
	
	public void setMap(int[][] map) {
		this.map = new int[map.length][];

		for (int i = 0; i < map.length; i++)
			this.map[i] = map[i].clone();
	}

	
	public void setHeroPoint(Point position) {
		hero.x = (WALLWIDTH * position.x) + PADDING;
		hero.y = (WALLHEIGHT * position.y) + PADDING;
	}

	public Point getHeroPoint() {
		//return player.getPoint(0;
		return hero;
	}	
	

	public Point convertToMapPoint(Point pos) {
		Point mapCellValue = new Point();

		mapCellValue.x = (int) Math.floor(pos.x / this.WALLWIDTH);
		mapCellValue.y = (int) Math.floor(pos.y / this.WALLHEIGHT);

		return mapCellValue;
	}

	public Point getHeroPointInMapValue(){
		return convertToMapPoint(getHeroPoint());
	}
	
	
	// Think about refactoring
	private boolean xCollision(Point pos) {
		Point mapPoint = convertToMapPoint(pos);

		int row2 = (int) Math.floor((pos.y + GLYPH_SIZE) / this.WALLHEIGHT);

		// System.out.println("x-axis collision");//testing
		return map[mapPoint.y][mapPoint.x] != MazeState.WALL
				&& map[row2][mapPoint.x] != MazeState.WALL;// testing will be passed from
													// model
	}

	
	//Think about refactoring
	private boolean yCollision(Point pos) {
		Point mapPoint = convertToMapPoint(pos);

		int col2 = (int) Math.floor((pos.x + GLYPH_SIZE) / this.WALLWIDTH);

		// System.out.println("y-axis collision");//testing

		return map[mapPoint.y][mapPoint.x] != MazeState.WALL
				&& map[mapPoint.y][col2] != MazeState.WALL;// testing will be passed from
													// model
	}	
	
	
	private void scroll() {
		// will need to clean this method
		// This only works for square maze shapes the way the logic is setup.
		double xRatio = (map.length * this.WALLWIDTH) / (PANEL_WIDTH);
		int xHelp = (int) xRatio;

		// This only works for square maze shapes the way the logic is setup.
		double yRatio = (map.length * this.WALLHEIGHT) / (PANEL_HEIGHT);
		int yHelp = (int) yRatio;

		int xBound = map.length * this.WALLWIDTH
				- (xHelp - this.WALLWIDTH - GLYPH_SIZE);
		int yBound = map.length * this.WALLHEIGHT
				- (yHelp + this.WALLHEIGHT + GLYPH_SIZE);

		if (xBound > hero.x) {
			this.scroll.x = -hero.x + (this.WALLWIDTH);
		}

		if (yBound > hero.y + 300) {// need to find out why y is off
			this.scroll.y = -hero.y + this.WALLHEIGHT;
		}
	}	
	
	
	// ----------------------------------------------------------------------------------------------------------------------------------------------------------

	public void goNorth() {
		Point p = (Point) hero.clone();
		
		p.y -= velocity;

		if (yCollision(p)) {
			hero.y -= velocity;
		}
	}

	
	public void goWest() {
		Point p = (Point) hero.clone();
		
		p.x -= velocity;

		if (xCollision(p)) {
			hero.x -= velocity;
		}
	}

	public void goEast() {
		Point p = (Point) hero.clone();
		
		
		p.x += GLYPH_SIZE + velocity;

		if (xCollision(p)) {
			hero.x += velocity;
		}
	}

	public void goSouth() {
		Point p = (Point) hero.clone();
	
		
		p.y += GLYPH_SIZE + velocity;

		if (yCollision(p)) {
			hero.y += velocity;
		}
	}	
	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
	public void mouseMoved(MouseEvent e){
		if(activeState == STATE.FIGHTING){
			fight.showSelectedTarget(e);
		}
	}
	
    @Override
    public void mousePressed(MouseEvent e){
    	if(activeState == STATE.FIGHTING){
    		fight.playerActionOnClick(e);
    	}
    }
    
	@Override
	protected void context(GameContext context){
		this.context = context;
	}  
	
	
	public GameContext getContext(){
		return this.context;
	}
	
	public  MazeCanvas getCanvs(){
		return this.canvas;
	}
}
