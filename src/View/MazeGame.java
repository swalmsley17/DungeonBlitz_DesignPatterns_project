package View;


import javax.swing.JFrame;


// ============================================================================================================================================================
/**
 * 
 * 
 * @author Michelet Chery
 */
public class MazeGame 
{
   
   private JFrame frame = new JFrame(); 
   private MazeCanvas mazeCanvas;
  
   
   public MazeGame()
   {
	   mazeCanvas = new MazeCanvas();  
	   
	   	frame.add(this.mazeCanvas);
	   	frame.setSize(500, 500); //Will create variable
		//frame.setSize((), (DISPLAY_SIZE + );
	   	frame.setResizable(false);
	   	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   	frame.setVisible(true);      
   }


  
   /*
   public void north(){
	   this.mazePanel.goNorth();
   }
   
   public void south(){
	   this.mazePanel.goSouth();
   }
   
   public void east(){
	   this.mazePanel.goEast();
   }
   
   public void west(){
	   this.mazePanel.goWest();
   }
   
	public void addKeyListener(KeyListener keyPress){
		this.mazePanel.addKeyListener(keyPress); 
	}
*/	

}
