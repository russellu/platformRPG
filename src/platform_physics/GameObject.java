package platform_physics;

import java.util.ArrayList;

public interface GameObject {

	
	public void drawSelf(double[][][] background) ; 
	public void eraseSelf(double[][][] background) ;
	public void changeState() ; 
	public void changeVelocity(double xincr, double yincr) ; 
	public void updateVelocity(double xincr, double yincr) ; 
	public int getTag() ; // get the identifier of the game object
	public void setTag(int newtag) ;  // set the tag of the game object
	public void drawNormals() ; 
	
}
