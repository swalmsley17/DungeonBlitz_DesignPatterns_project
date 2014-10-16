package Maze;

import java.awt.Point;
import java.awt.Toolkit;
import java.net.URL;

public class MazeOne extends MazeState {

	private  int[][] mapOne = {
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1},
			{1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 1, 1},
			{1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
			{1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 5, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 9, 1, 1},
			{1, 1, 3, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1},
			{1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 6, 0, 1, 1},
			{1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 3, 0, 0, 0, 1, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
	        };		
	
		
	public MazeOne() {
		this.map = mapOne;
	
		
		this.actor = new Point(1,2);//Starting location
		this.exit =  new Point(15,5);//ExitPoint arrays[exit.y][exit.x] or [row][column]
		
				

		setWallTop("Animated/ugleWall.jpg");
		setWallBottom("Animated/ugleWallBottom.jpg");
		
		System.out.println("MazeOne----------------------------");
	}


	@Override
	public void reachedExit(MazeStateContext exit) {
		exit.nextState(new MazeTwo());

	}

}
