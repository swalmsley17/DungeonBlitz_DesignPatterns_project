package View;

public class GameWonState extends GameOverState{

	public GameWonState(MazeCanvas canvas) {
		super(canvas);
		super.setTitle("You've Won!");
	}
}
