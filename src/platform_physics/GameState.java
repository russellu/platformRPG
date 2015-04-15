package platform_physics;

import java.util.ArrayList;
import java.util.HashMap;

import platform_controls.ControlFrame;
import platform_drawing.DrawDouble;
import platform_players.Player;

/**
 * controls the state of the game ie objects within the game and physics updates
 * 
 * @author russ
 *
 */

public class GameState implements Runnable{

	Background bg ; 
	int[][] occupied ; //occupied array that is accessed by the bodies
	ArrayList<GameObject> objs = new ArrayList<GameObject>() ; 
	HashMap<Integer,GameObject> hm = new HashMap<Integer,GameObject>() ; 
	boolean paused = false ; 
	public ControlFrame cf = null ; 
	public Player p ; 
	
	public GameState(){
		bg = new Background() ; 
		occupied = new int[bg.getDim()[0]][bg.getDim()[1]] ; 
		initState() ; 	
	}
	
	public void unPause(){ this.paused = false ; }
	public boolean isPaused(){ return paused ; }
	public void pause(){ paused = true ; }
	
	public void addEntity(){
		int id = 0 ; 
		
		Body bod1 = new Body(this,100,100,0,0,++id) ; bod1.setMass(1);
		p = new Player(bod1) ; 
		
		Body bod2 = new Body(this,130,200,-1,-1,++id) ; bod2.setMass(1);
	//	Body bod3 = new Body(this,300,150,-2,1,++id) ; bod3.setMass(5);
	//	Body bod4 = new Body(this,240,200,-2,1,++id) ; bod4.setMass(30);
	//	Body bod5 = new Body(this,60,300,-2,1,++id) ; bod5.setMass(1);

		ImmovableObject ob1 = new ImmovableObject(this,0,0,50,400,++id) ;
		ImmovableObject ob2 = new ImmovableObject(this,0,350,400,50,++id) ; 
		ImmovableObject ob3 = new ImmovableObject(this,350,0,50,400,++id) ; 
		ImmovableObject ob4 = new ImmovableObject(this,50,0,300,50,++id) ; 
		hm.put(bod1.getTag(), bod1) ; 
		
		hm.put(bod2.getTag(), bod2) ;
	//	hm.put(bod3.getTag(), bod3) ;
	//	hm.put(bod4.getTag(), bod4) ;
	//	hm.put(bod5.getTag(), bod5) ;

		hm.put(ob1.getTag(), ob1) ; 
		hm.put(ob2.getTag(), ob2) ; 
		hm.put(ob3.getTag(), ob3) ; 
		hm.put(ob4.getTag(), ob4) ;

	}
	
	public void updateAll(){
		bg.refresh() ; 

		applyGravity() ; 
		//int[] p1 = {50,50} ; 
		//int[] p2 = {50,60} ; 
		
		
		for(int i=0;i<hm.size();i++){
			hm.get(i+1).eraseSelf(bg.getBG()) ; 
			hm.get(i+1).changeState() ; 
			hm.get(i+1).drawSelf(bg.getBG()) ;

			
		}
		//DrawDouble.drawLine(bg.getBG(), p1, p2);

	}
	
	public void applyGravity(){ //in the +y direction
		for(int i=0;i<hm.size();i++)
			if(hm.get(i+1).getClass().equals(Body.class))
				hm.get(i+1).updateVelocity(0, .2);
				
			
		
		
	}
	
	public void run(){
		while(true){
			if(!paused){
				if(cf != null)
					if(cf.moving())
						p.move(cf.get2dDirectionVector());
				
				updateAll() ; 
			}
			try{Thread.sleep(1);}catch(Exception e){}
			
			
			
		}
		
	}
	
	public void initState(){
		addEntity() ; 
		
	}
	
	public static void main(String[] args){
		GameState gs = new GameState() ; 
		new Thread(gs).start() ;
		
		
	}
}
