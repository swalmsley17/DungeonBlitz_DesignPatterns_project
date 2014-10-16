package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;



public class MazeCanvas extends JPanel {

	/*Runs smoother with thread*/
	private Thread mazeThread;

	/*Set Panel size*/
	public static final int PANEL_WIDTH = 500;
	public static final int PANEL_HEIGHT = 500;
	
	private boolean rungame = true;
	
	/*State pattern strategy for game states*/
	GameContext gameStateContext;
	
	
	/*This fps code by Kevin Glass*/
	private long lastLoopTime = System.nanoTime();
	private final int TARGET_FPS = 60;
	private final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;  
	private long lastFpsTime = 0;
	private int fps = 0;
	private long updateLength;
	private double delta;
	private long now;
	
	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Creates a new display.
	 * 
	 */
	public MazeCanvas() {
		
		this.gameStateContext = new GameContext(this);//passing for observers
		
		this.setSize(PANEL_WIDTH, PANEL_HEIGHT);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.setBackground(Color.black);
		
		
		mazeThread = new Thread() {
			public void run() {
				gamePlay();
			}
		};

		mazeThread.start();
	}

	/**
	 * Handles all painting in the program.
	 * 
	 * @param graphics
	 *            - the graphics context
	 */
	@Override
	public void paint(final Graphics graphics) {
		super.paint(graphics);
		Graphics2D g = (Graphics2D) graphics;
		this.gameStateContext.draw(g);
	}
	
	
	private void gamePlay() {

		
		
		while (rungame) {
  	      now = System.nanoTime();
  	      updateLength = now - lastLoopTime;
  	      lastLoopTime = now;
  	      delta = updateLength / ((double)OPTIMAL_TIME);
  	      
  	      
  	      lastFpsTime += updateLength;
  	      fps++;
  	      
  	      if (lastFpsTime >= 1000000000)
  	      {
  	    //     System.out.println("(FPS: "+fps+")");
  	         lastFpsTime = 0;
  	         fps = 0;
  	      }			
			
			
			
			repaint();
			
	/*		
    		try {
				Thread.sleep( (lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000 );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	*/		
			
		}
	}
	
	public void addMouseEvent(GameState state){
		addMouseMotionListener(state);
		addMouseListener(state);
	}
	
	public void removeMouseEvent(GameState state){
		this.removeMouseListener(state);
		this.removeMouseMotionListener(state);
	}
}
