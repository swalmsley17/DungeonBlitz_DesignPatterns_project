package AnimatingImage;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class Animation {

	
	private BufferedImage animImage;


	private final int totalFrames;
	private final int frameDistance;
	private int currentDistance;
	private int currentFrameNumber;
	
	private long frameTime;
	private long startingFrameTime;
	private long timeForNextFrame;

	private Point dimension;
	private Point spriteStart;
	private Point spriteEnd;
	private int x,y;
	
	private boolean pause;
	private boolean reflect = false;
		

	public Animation(BufferedImage animImage, Point dimension,
			int totalFrames, long frameTime,
			Point spriteStart, Point spriteEnd, int frameDistance) {

		this.animImage = animImage;
		this.dimension = dimension;
		this.totalFrames = totalFrames;
		this.frameTime = frameTime;
		this.spriteStart = spriteStart;
		this.spriteEnd = spriteEnd;
		this.frameDistance = frameDistance;
		

		x = 0;
		y = 0;
		

		startingFrameTime = System.currentTimeMillis();
		
		timeForNextFrame = startingFrameTime + this.frameTime;
		currentFrameNumber = 0;
	}

	public int getWidth(){
		return this.dimension.x;
	}

	public int getHeight(){
		return this.dimension.y;
	}
		
	
	public void pause(){
		pause = true;
	}

	public void resume(){
		pause = false;
	}
	
	public void setReflect(boolean reflect){
		this.reflect = reflect;
	}
	
	private void setPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}

	private void Update() {
		if ((timeForNextFrame <= System.currentTimeMillis()) && !pause) {
			

			if (currentFrameNumber >= totalFrames) {
				currentFrameNumber = 0;
			}

			currentDistance = frameDistance*currentFrameNumber;
			currentFrameNumber++;
			
			
			startingFrameTime = System.currentTimeMillis();
			timeForNextFrame = startingFrameTime + frameTime;
		}
	}

	public void draw(Graphics2D g, int x, int y,int width,int height) {
		//I do not know if this is bad
		setPoint(x,y);
		this.dimension.x = width;
		this.dimension.y = height;
		Update();



		if(reflect){
			//display mirror image of characters 
			g.drawImage(animImage, x, y, x+dimension.x, y+ dimension.y, 
					(frameDistance*(totalFrames-1)+(spriteEnd.x-spriteStart.x))-currentDistance,spriteStart.y,
					(frameDistance*(totalFrames-1)+(spriteEnd.x-spriteStart.x))-currentDistance -(spriteEnd.x-spriteStart.x),
					spriteEnd.y,null);	
		}
		else{
			g.drawImage(animImage, x, y, x
					+ dimension.x, y + dimension.y, spriteStart.x+currentDistance,
					spriteStart.y, spriteEnd.x+currentDistance, spriteEnd.y, null);			
		}
	}
}