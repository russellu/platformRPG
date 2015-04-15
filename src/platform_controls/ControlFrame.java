package platform_controls;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import platform_physics.GameState;
import platform_physics.LowLvl;

public class ControlFrame implements MouseMotionListener,MouseListener,MouseWheelListener,KeyListener{

	JFrame jf ;
	GameState gs ;
	boolean up ; 
	boolean down ; 
	boolean left ; 
	boolean right ; 
	int moveStep = 3 ;
		
	public boolean moving(){
		return up||down||left||right ;
	}
	
	//directions clockwise starting in top left corner
	private static int[] upLeft = {-1,-1} ;
	private static int[] upStraight = {0,-1} ;
	private static int[] upRight = {1,-1} ; 
	private static int[] straightRight = {1,0} ; 
	private static int[] downRight = {1,1} ;
	private static int[] downStraight = {0,1} ;
	private static int[] downLeft = {-1,1} ;
	private static int[] straightLeft = {-1,0} ;
	private static int[] zeroDirection = {0,0} ;
	
	// the mapping of the state array to the direction.
	static boolean[] upLeftBool = {true,false,true,false} ; 	
	static boolean[] upStraightBool = {true,false,false,false} ; 	
	static boolean[] upRightBool = {true,false,false,true} ; 	
	static boolean[] straightRightBool = {false,false,false,true} ; 	
	static boolean[] downRightBool = {false,true,false,true} ; 	
	static boolean[] downStraightBool = {false,true,false,false} ; 	
	static boolean[] downLeftBool = {false,true,true,false} ; 
	static boolean[] straightLeftBool = {false,false,true,false} ; 
	
	
	public ControlFrame(GameState gs){
		initFrame() ; 
		addButtons() ; 
		this.gs = gs ;
	}
	
	
	public void addButtons(){
		jf.addMouseListener(this) ;
		jf.addKeyListener(this) ;
		jf.addMouseMotionListener(this) ;
		jf.addMouseWheelListener(this) ;
		
		JButton pause = new JButton("pause") ;
		pause.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(!gs.isPaused())
					gs.pause() ; 
				else gs.unPause() ; 
				
			}	
		});
		jf.add(pause) ; 
		
		
		
		
		
		
		
		
		jf.validate(); 
		
		
		
	}
	
	public void initFrame(){
		
		jf = new JFrame() ; 
		jf.setLocation(new Point(0,500));
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ; 
		jf.setPreferredSize(new Dimension(300,200)) ; 
		jf.setVisible(true);
		jf.pack();
		
		
	}


	
	public void keyTyped(KeyEvent e) {
if(e.getKeyCode() == KeyEvent.VK_P){
			if(!gs.isPaused())
				gs.pause() ; 
			else gs.unPause() ; 
		}
	
	}


	public void keyPressed(KeyEvent e) {
	//	System.out.println(e.getKeyCode()) ; 
		
	    if(e.getKeyCode()==KeyEvent.VK_LEFT){
			left = true ; 
		//	System.out.println("key Event Triggered (left)") ; 
		
		}
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			right = true ; 
		//	System.out.println("key Event Triggered (right)") ; 

		
		}
		else if(e.getKeyCode()==KeyEvent.VK_UP){
			up = true ; 
			//System.out.println("key Event Triggered (up) ") ; 

			
		}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN){
			down = true ;
		//	System.out.println("key Event Triggered (down)") ; 

			}
	
	
	}


	public void keyReleased(KeyEvent e) {
		

		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			left = false ;
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			right = false ; 
		}
		if(e.getKeyCode()==KeyEvent.VK_UP){
			up = false ; 
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN){
			down = false ; 
		}

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public int[] get2dDirectionVector(){ 
		boolean[] state = {up,down,left,right} ;
		if(LowLvl.compareBoolean(state, upLeftBool))
			return upLeft ;
		else if(LowLvl.compareBoolean(state, upStraightBool))
			return upStraight ;

		else if(LowLvl.compareBoolean(state, upRightBool))
			return upRight ;

		else if(LowLvl.compareBoolean(state, straightRightBool))
			return straightRight ;

		else if(LowLvl.compareBoolean(state, downRightBool))
			return downRight ;

		else if(LowLvl.compareBoolean(state, downStraightBool))
			return downStraight ;

		else if(LowLvl.compareBoolean(state, downLeftBool))
			return downLeft ;

		else if(LowLvl.compareBoolean(state, straightLeftBool))
			return straightLeft ;

		else return zeroDirection ; 

	}
	
	
	
}
