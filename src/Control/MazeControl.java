package Control;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Bonus.AttackBonus;
import Bonus.BlockBonus;
import Bonus.HealthBonus;
import Character.Player;
import Maze.MazeState;
import Model.ModelInterface;
import View.GameContext;
import View.GameWonState;
import View.MazeCanvas;
import View.PlayingGameState;


public class MazeControl implements ControlInterface,KeyListener {
	
	 private ModelInterface model;
	 private PlayingGameState view;//Need to get this to point to PlayingGameState

	public MazeControl(ModelInterface model,PlayingGameState view) {
		this.model = model;
		this.view = view;//passing for observers();
		

		this.view.addKeyListener(this);
	}

	//Code smells
	public void updateModel(){
		int playerFoundSomething = 0;//false
		Player player = model.getPlayer();
		
		if(!view.getHeroPointInMapValue().equals(model.getPlayerPoint())){
			playerFoundSomething = model.setPlayerPoint(view.getHeroPointInMapValue());
			
			if(playerFoundSomething== MazeState.EXIT) // have found exit
			{
				view.updateMaze();
			}			
			
			
			if(playerFoundSomething == MazeState.ENEMY)
			{
		//		System.out.println("Need to create some enemy and return to playingGameState");
				view.setEnemyInstance(model.randomEnemy());
				view.changeStateToBattle();
			}
			
			
			
			if(playerFoundSomething == MazeState.HEALTH_BONUS)
			{
				player.useBonus(new HealthBonus(100));
				view.updateMaze();
				
			}
			
			if(playerFoundSomething == MazeState.ATTACK_BONUS)
			{
				player.useBonus(new AttackBonus(100));
				view.updateMaze();
			}
			
			if(playerFoundSomething == MazeState.BLOCK_BONUS)
			{
				player.useBonus(new BlockBonus(.1));
				view.updateMaze();
			}	
			
			if(playerFoundSomething == MazeState.GAME_WON)
			{
				MazeCanvas can = view.getCanvs();
				GameContext context = view.getContext();
				context.setState(new GameWonState(can));
				can.removeAll();
			}	
		}
		

	}

	
	public void goNorth(){
		
		this.view.goNorth();
		updateModel();
	}

	public void goSouth(){
		this.view.goSouth();
		updateModel();
	}	
	
	public void goEast(){
		this.view.goEast();
		model.lookEast();
		updateModel();		
	}
	
	public void goWest(){
		this.view.goWest();
		model.lookWest();
		updateModel();
	}

	
	@Override
	public void keyPressed(KeyEvent e) {
		
		switch(e.getKeyCode()){
			case KeyEvent.VK_UP: 
				goNorth();
				break;
			
			case KeyEvent.VK_LEFT:
				goWest();
				break;
			
			case KeyEvent.VK_RIGHT:
				goEast();
				break;
			
			case KeyEvent.VK_DOWN:
				goSouth();
				break;
		}
		
		model.resume();
//		model.getPlayer().getWalking();
		
		
//		model.printMap();//testing
//		System.out.println("Pressed");	//testing
//		System.out.println("In the control area");//testing
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
	//	System.out.println("Released");
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	//	System.out.println("Typed");
	}
}
