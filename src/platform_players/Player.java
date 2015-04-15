package platform_players;

import platform_physics.Body;

/**
 * player class each player has an associated body
 * the player receives input from the keyboard and moves the body accordingly
 * ie the player is the link between the control and the body on the screen
 * 
 * the player starting location is determined by the location of the body.
 * 
 * @author russ
 *
 */

public class Player {

	Body b ; 
	
	
	public Player(Body b){
		this.b = b ; 
		
		
	}
	
	public void move(int[] directionVector){
		b.changeVelocity(directionVector[0], directionVector[1]);
		
	}
	
	
}
