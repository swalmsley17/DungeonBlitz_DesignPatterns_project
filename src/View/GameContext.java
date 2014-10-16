package View;

import java.awt.Graphics2D;


public class GameContext{

	private GameState ChooseCharacterState;
	private GameState ExitGameState;
	private GameState GameOverState;
	private GameState GameWonState;
	private GameState MainMenuState;

	private GameState state;
	
	
	private MazeCanvas canvas; //Could be code smell

	
	public GameContext(MazeCanvas canvas){
		this.canvas = canvas;
		
		ChooseCharacterState = new ChooseCharacterState(this.canvas); 
		ChooseCharacterState.context(this);
		ExitGameState = new ExitGameState();
		ExitGameState.context(this);
		GameOverState = new GameOverState(this.canvas);
		GameOverState.context(this);
		GameWonState = new GameWonState(this.canvas);
		GameWonState.context(this);
		MainMenuState = new MainMenuState(this.canvas);
		MainMenuState.context(this);
//		PlayingGameState = new PlayingGameState(this.canvas,null);
//		PlayingGameState.context(this);
		
		setState(new MainMenuState(canvas));//Game always start in the main manu state
	}

	public void setState(GameState state){
		this.state = state;
		canvas.addMouseEvent(this.state);
	
	}
	
	public void draw(Graphics2D g){
		state.draw(g);
		state.context(this);
	}
	
	
	public GameState getState(){
		return state;
	}
	
	public GameState getChooseCharacterState() {
		return ChooseCharacterState;
	}

	public GameState getExitGameState() {
		return ExitGameState;
	}

	public GameState getGameOverState() {
		return GameOverState;
	}

	public GameState getGameWonState() {
		return GameWonState;
	}

	public GameState getMainMenuState() {
		return MainMenuState;
	}
}
