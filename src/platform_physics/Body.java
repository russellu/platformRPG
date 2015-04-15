package platform_physics;

import java.util.ArrayList;

import MatrixFunctions.Distance;
import platform_drawing.DrawDouble;
import platform_players.Skeleton;
import platform_players.Skin;

/**
 * Body has spatial extension, mass, velocity
 * Body's spatial dimension is defined by its skeleton
 * appearence defined by skin
 * 
 * make it so the body can break apart into smaller bodies.
 * the body has to hit other bodies and act upon them
 * can two bodies occupy the same space at once? NEVER. 
 * 
 * when one body impacts another it transfers energy E = mc^2
 * a body should also have a center of mass.
 * 
 * elastic collision: v1 = (u1*(m1-m2)+2*m2*u2) / (m1+m2) 
 * 
 * need to make it logically impossible for two bodies to occupy the same space
 * 
 * something is wrong with the bodies when they get too big. they stick together
 * is it because of two collisions in one episode?
 * 
 * @author russ
 *
 */

public class Body implements GameObject{
	
	protected double xloc = 0 ;
	protected double yloc = 0 ;
	protected double nextXloc ; 
	protected double nextYloc ; 
	protected double xvel = 1 ; 
	protected double yvel = 1 ; 
	protected double xacc = 0 ; 
	protected double yacc = 0 ; 
	double[][][] skin ; // rgba skin   (2d+Rgba Appearance)
	Skeleton skeleton ; // int skeleton (2d collision frame)
	boolean drawSkeleton = true ; 
	boolean gotHit = false ; 
	int tag ; 
	GameState gs ; 
	protected double mass = 0 ; 
	int collidecount = 0 ; 
	
	public Body(){}
	
	public Body(GameState gs, double x, double y,int tag){
		this.gs = gs ; 
		this.xloc = x ; 
		this.yloc = y ; 
		this.skin = Skin.getRandomSkin(50,50) ;
		this.skeleton = new Skeleton(50, 50) ; 
		this.mass = 1 ; 
		this.tag = tag ;		
	}
	
	public Body(GameState gs, double x, double y,double xvel,double yvel,int tag){ // second constructor where you can specify velocity
		this.gs = gs ; 
		this.xloc = x ; 
		this.yloc = y ; 
		this.xvel = xvel ;
		this.yvel = yvel ; 
		this.skin = Skin.getRandomSkin(50,50) ;
		this.skeleton = new Skeleton(50, 50) ; 
		this.mass = 1 ; 
		this.tag = tag ; 
	}
	
	
	/**
	 * 
	 * the collision angle between two bodies
	 * gets the collision between the movement of the first body and the surface of the second
	 * ie the direction vector of the first with the surface normal of the second
	 * 
	 * reflection vector r = v-2*N*(VdotN)
	 * 
	 * @param a first body
	 * @param b second body
	 * @param apos position on body a of the collision
	 * @param bpos position on body b of the collision
	 */
	public double[] collisionAngle(Body a, Body b,int[] apos, int[] bpos){
		double[] vA = {a.xvel,a.yvel} ; 
		double[] bpos2 = {bpos[0],bpos[1]} ; 
		double[] bLoc = Distance.minIndex(bpos2, b.skeleton.skelePoints) ;
		double[] normB = b.skeleton.normals[(int)bLoc[0]][(int)bLoc[1]] ; 
		 //reflection vector r = v-2*N*(VdotN)
		double vdotn = Distance.dotProduct(vA,normB) ; 
		double[] newVel = {a.xvel - 2*normB[0]*vdotn, a.yvel - 2*normB[1]*vdotn};
		return newVel ; 	
	}
	
	public double[] colNormal(Body a,Body b, int[] apos, int[] bpos){
		double[] bpos2 = {bpos[0],bpos[1]} ; 
		double[] bLoc = Distance.minIndex(bpos2, b.skeleton.skelePoints) ;
		double[] normB = b.skeleton.normals[(int)bLoc[0]][(int)bLoc[1]] ; 
		return normB ; 
		
	}
	
	public void setMass(double mass){
		this.mass = mass ;
	}
	
	public void checkCollisions(double newx,double newy){ // check for collision and send message if collided
		if(gotHit == true ){
			gotHit = false ; 
			return ; 
		}
		Body b = null ;
		ArrayList<double[]> dirVecs = new ArrayList<double[]>() ; 
		ArrayList<double[]> normVecs = new ArrayList<double[]>() ; 
		for(int i=(int)newx;i<(int)newx+skin.length;i++)
			for(int j=(int)newy;j<(int)newy+skin[0].length;j++)
				if(j<gs.bg.getBG().length && i>=0 && j>=0 && i < gs.bg.getBG().length && j<gs.bg.getBG()[j].length)
					if(gs.occupied[i][j] !=0 && gs.occupied[i][j] != tag){
						b = (Body)gs.hm.get(gs.occupied[i][j]) ; 
						int[] cPoint1 = {(int)(i-newx), (int)(j-newy)} ; // collision point on this body
						int[] cPoint2 = {(int)(i-b.xloc), (int)(j-b.yloc)} ; // collision point on colliding body
						dirVecs.add(collisionAngle(this,b,cPoint1,cPoint2)) ; 
						normVecs.add(colNormal(this,b,cPoint1,cPoint2)) ; 
					}
		
		// get the average of all the collision vectors, replace velocity with that average
		//Va = Va – (1+e)*N*((Vb-Va) • N)*(Mb / (Ma+Mb))
		//Vb = Vb – (1+e)*-N*((Vb-Va) • -N)*(Ma / (Ma+Mb))
		
		if(dirVecs.size() > 0){	
			System.out.println("BODY  " + this.tag + " colliding") ; 
			collidecount = 20 ; 
			//double[] thisVec = {this.xvel, this.yvel} ;
			//double mag = Distance.magnitude(thisVec) ; 
			double[] meanVec = Distance.listMean(dirVecs) ; 
			Distance.normalizeVec(meanVec) ; // normalized meanVec is the new direction vector
			
			double[] meanNorm = Distance.listMean(normVecs) ; 
			Distance.normalizeVec(meanNorm);
			double[] vr = {this.xvel-b.xvel,this.yvel-b.yvel} ;
			//I = (1+e)*N*(Vr • N)*(Ma*Mb)/( Ma+Mb)
			double ix = 2*meanNorm[0]*Distance.dotProduct(vr, meanNorm)*((this.mass*b.mass)/(this.mass+b.mass)) ;
			double iy = 2*meanNorm[1]*Distance.dotProduct(vr, meanNorm)*((this.mass*b.mass)/(this.mass+b.mass)) ; 

			this.xvel -= ix/this.mass ; 
			this.yvel -= iy/this.mass ; 
			b.xvel += ix/b.mass ; 
			b.yvel += iy/b.mass ; 
			b.gotHit = true ; 
			this.gotHit = true ; 
			//System.out.println("subvec[0] = " + subvec[0] + " subvec[1] = " + subvec[1]) ;
			System.out.println("meanNorm[0] = " + meanNorm[0] + " meanNorm[1] = " + meanNorm[1]) ; 
			System.out.println(" newxvel = " + this.xvel + " newyvel = " + this.yvel);
			//double vxa = this.xvel - 2*meanNorm[0]*(Distance.dotProduct(subvec, meanNorm))*(b.mass/(this.mass + b.mass)) ;
			//double vya = this.yvel - 2*meanNorm[0]*(Distance.dotProduct(subvec, meanNorm))*(b.mass/(this.mass + b.mass)) ;
			
		//	this.xvel = vxa ; this.yvel = vya ; 
		//	System.out.println("colliding, new vx = " + vxa + " new vy = " + vya) ; 
		//	System.out.println("mean norm x = " + meanNorm[0] + " meanNorm y = " + meanNorm[1]) ; 
			
			//meanVec[0] = meanVec[0]*mag ; 
			//meanVec[1] = meanVec[1]*mag ;
			//this.xvel = meanVec[0] ; 
			//this.yvel = meanVec[1] ;
			//System.out.println("body " + this.tag + " collided") ;
		}
		
		//if(b!= null)
		//	if(b.getClass().equals(Body.class))
		//		b.checkCollisions(b.xloc,b.yloc);
	}
	
	public void changeVelocity(double xincr, double yincr){		
		this.xvel =  xincr ; 
		this.yvel =  yincr ;

	}
	
	public void updateVelocity(double xincr, double yincr){
		
		this.xvel += xincr ; 
		this.yvel += yincr ; 
	}
	
	public void changeState(){ // change position and velocity based on acceleration
		collidecount -- ; 
		double newxloc = xloc + xvel ; 
		double newyloc = yloc + yvel ; 
		
		checkCollisions(newxloc,newyloc) ; 

		// final check to make sure the body isn't overlying another body
		// if it is, reverse the operation
		
		for(int i=(int)newxloc;i<(int)newxloc+skin.length;i++)
			for(int j=(int)newyloc;j<(int)newyloc+skin[0].length;j++)			
				if(j<gs.bg.getBG().length && i>=0 && j>=0 && i < gs.bg.getBG().length && j<gs.bg.getBG()[j].length)
					if(gs.occupied[i][j]!=0 && gs.occupied[i][j]!=tag){
						newxloc = this.xloc ; 
						newyloc = this.yloc ;
					}
						
		this.xloc = newxloc ; 
		this.yloc = newyloc ;
		//this.xvel = xvel + xacc ; 
		//this.yvel = yvel + yacc ; 		
	}
	

	public int getTag(){
		return tag ;
	}
	public void setTag(int newtag){
		this.tag = newtag ;
	}
	
	public void eraseSelf(double[][][]background){ // erase the body's skin from the background
		for(int i=(int)xloc;i<(int)xloc+skin.length;i++)
			for(int j=(int)yloc;j<yloc+skin[i-(int)xloc].length;j++)
				if(j<background.length && i>=0 && j>=0 && i < background.length && j<background[j].length
					&& i-(int)xloc < skin.length && j-(int)yloc < skin[0].length){
					background[i][j][0] = 0 ;
					background[i][j][1] = 0 ;
					background[i][j][2] = 0 ;
					background[i][j][3] = 255 ; 	
					gs.occupied[i][j] = 0 ; 
				}	
	}
	
	public void drawSelf(double[][][] background) {// draw the body's skin on the background array
		for(int i=(int)xloc;i<(int)xloc+skin.length;i++)
			for(int j=(int)yloc;j<yloc+skin[i-(int)xloc].length;j++)
				if(j<background.length && i>=0 && j>=0 && i < background.length && j<background[j].length 
					&& i-(int)xloc < skin.length && j-(int)yloc < skin[0].length){
					
					background[i][j][0] = skin[i-(int)xloc][j-(int)yloc][0] ;
					background[i][j][1] = skin[i-(int)xloc][j-(int)yloc][1] ;
					background[i][j][2] = skin[i-(int)xloc][j-(int)yloc][2] ;
					background[i][j][3] = skin[i-(int)xloc][j-(int)yloc][3] ;
					gs.occupied[i][j] = tag ; // update the occupied array of the game state for future collisions
					if(drawSkeleton && skeleton.skeleFrame[i-(int)xloc][j-(int)yloc]!=0){
						
						
						background[i][j][0] = 255 ;
						background[i][j][1] = 0 ;
						background[i][j][2] = 0 ;
						background[i][j][3] = 255 ;
						
					}
					if(collidecount > 0){
						background[i][j][0] = 0 ;
						background[i][j][1] = 255 ;
						background[i][j][2] = 0 ;
						background[i][j][3] = 255 ;
						
					}
				}	
		//drawNormals() ; 
	}
	
	/**
	 * draw normals to the body's skeleton (for visualization of 2d collision detection)
	 */	
	public void drawNormals() { 
		double[][][] normals = skeleton.normals ; 
		int normLength = 3;
		for(int i=0;i<normals.length;i++)
			for(int j=0;j<normals[0].length;j++)
				if(!(normals[i][j][0]==0 && normals[i][j][1]==0)){
					//int[] p1 = {i+(int)this.xloc,j+(int)this.yloc} ;
					//int[] p2 = {i+2,j} ; 
					//DrawDouble.drawLine(gs.bg.getBG(), p1, p2);
					
					
				//	System.out.println("normals [i][j][0]=" + normals[i][j][0] + " normals[i][j][1]=" + normals[i][j][1]) ;
					int[] p1 = {(int)(i-normals[i][j][0]*normLength)+(int)this.xloc,(int)(j-normals[i][j][1]*normLength)+(int)this.yloc} ; 
					int[] p2 = {(int)(i+normals[i][j][0]*normLength)+(int)this.xloc,(int)(j+normals[i][j][1]*normLength)+(int)this.yloc} ; 
				//	System.out.println("x = " + (p2[0]-p1[0]) + " y = " + (p2[1]-p1[1])) ; 
					DrawDouble.drawLine(gs.bg.getBG(), p1, p2) ;
					
			}
	}	

}
