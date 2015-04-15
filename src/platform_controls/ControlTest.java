package platform_controls;

import javax.swing.JFrame;

import platform_physics.GameState;

/**
 * controls: what should the player be able to do? run, jump, attack, defend
 * ranged attack and sword attack...switch weapons?
 * want to use the fewest keys possible, but have the widest range of abilities.
 * space bar to jump, what about typing words to bring up weapons? wouldn't work so well 
 * on the touchpad
 * want very basic combat building blocks but can build up into combos using a few keys
 * the faster you type, the better you are
 * 
 * 
 * @author russ
 *
 */

public class ControlTest {
	
	ControlFrame cf ;
	GameState gs ; 
	
	public ControlTest(){
		gs = new GameState() ; 
		new Thread(gs).start();
		cf = new ControlFrame(gs) ; 
		gs.cf = cf ; 
		
	}
	
	
	
	
	public static void main(String[]args){
		new ControlTest() ; 
	}

	
	
}
