package Maze;

import java.awt.Point;

import View.GameWonState;
import View.MazeCanvas;

public class MazeThree extends MazeState {

	private  int[][] mapThree = {
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,0,0,0,0,1,0,0,0,0,1,1,1,1},
			{1,1,1,1,1,6,0,0,0,0,1,0,0,0,0,1,1,1,1},
			{1,2,0,0,0,5,0,0,0,0,3,0,0,0,8,1,1,1,1},
			{1,1,1,1,1,4,0,0,0,0,1,0,0,0,0,1,1,1,1},
			{1,1,1,1,1,1,0,0,0,0,1,0,0,0,0,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
	};
	
	
		
	public MazeThree() {
		this.map = mapThree;
	
		
		this.actor = new Point(1,4);//Starting location
		this.exit =  new Point(15,4);//ExitPoint  zero base array Point(colum,row)	zero base
				

		setWallTop("Animated/mapThreeWall.jpg");
		setWallBottom("Animated/mapThreeWallBottom.jpg");
		
		System.out.println("MazeThree----------------------------");
	}



	@Override
	public void reachedExit(MazeStateContext exit) {
		//exit.nextState(new GameWonState(new MazeCanvas()))
	}

}
