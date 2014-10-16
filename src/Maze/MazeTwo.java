package Maze;

import java.awt.Point;

public class MazeTwo extends MazeState {

	private int[][] mapTwo = {
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 1, 1, 0, 0, 0, 1, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1},
			{1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1},
			{1, 9, 1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0, 3, 0, 0, 1, 0, 0, 3, 1, 1, 1},
			{1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1},
			{1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1},
			{1, 3, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1},
			{1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1},
			{1, 0, 1, 0, 1, 0, 1, 0, 0, 3, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1},
			{1, 0, 3, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 3, 1, 0, 1, 0, 1, 1, 1, 1, 1},
			{1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 6, 1, 0, 0, 0, 1, 5, 1, 1, 1},
			{1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1},
			{1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1},
			{1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 4, 0, 0, 1, 0, 3, 0, 1, 1, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			
	        };
	
	public MazeTwo() {
		this.map =mapTwo;

		this.actor = new Point(8,1);//Starting location
		this.exit =  new Point(1,3);//ExitPoint  zero base array Point(colum,row)	zero base
		
		setWallTop("Animated/redWall.jpg");
		setWallBottom("Animated/redWallBottom.jpg");
		
		System.out.println("MazeTwo----------------------------");
	}

	@Override
	public void reachedExit(MazeStateContext exit) {
		exit.nextState(new MazeThree());
	}

}
