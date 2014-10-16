package Maze;

public class MazeStateContext {
    private MazeState state;

    public MazeStateContext()
    {
    	 nextState(new MazeOne());//Always start at first Level
    }
    
    public void nextState(MazeState state)
    {
        this.state = state;
    }
    
    public MazeState getState()
    {
        return this.state;
    }
    
    public void nextMap(){
    	state.reachedExit(this);
    }
    
}
