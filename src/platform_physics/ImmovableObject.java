package platform_physics;

import platform_players.Skeleton;
import platform_players.Skin;

public class ImmovableObject extends Body{
	
	
	public ImmovableObject(){
		super() ;
	}
	
	public ImmovableObject(GameState gs, double x, double y,int tag){
		this.gs = gs ; 
		this.xloc = x ; 
		this.yloc = y ; 
		this.skin = Skin.getRandomSkin(30,30) ;
		this.skeleton = new Skeleton(30, 30) ; 

		this.mass = 9999999; 
		this.tag = tag ;		
		this.xvel = 0 ; 
		this.yvel = 0 ; 
	}

	public ImmovableObject(GameState gs, double x, double y, int xwidth, int ywidth, int tag){
		this.gs = gs ; 
		this.xloc = x ; 
		this.yloc = y ; 
		this.skin = Skin.getRandomSkin(xwidth,ywidth) ;
		this.skeleton = new Skeleton(xwidth, ywidth) ; 

		this.mass = 9999999 ; 
		this.tag = tag ;		
		this.xvel = 0 ; 
		this.yvel = 0 ; 
	}
	
	public void changeState(){ // the immovable object does not change state
		
		
	}

}
