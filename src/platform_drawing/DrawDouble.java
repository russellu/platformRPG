package platform_drawing;

/**
 * functions for drawing on a double[][][] array
 * 
 * 
 * @author russ
 *
 */


public class DrawDouble{

	
	/**
	 * draw a line from p1 to p2
	 * 
	 * @param canvas
	 * @param x
	 * @param y
	 */
	public static void drawLine(double[][][] canvas,int[] p1, int[] p2 ){
		double xdiff = p2[0]-p1[0] ; 
		double ydiff = p2[1]-p1[1] ;
		double angle = Math.abs(Math.atan(ydiff/xdiff)) ; 
		double xstep = Math.cos(angle) ; 
		double ystep = Math.sin(angle) ; 
		double xloc = p1[0] ; 
		double yloc = p1[1] ; 
				
		for(int i=0;i<4;i++){			
			if((int)xloc>=0 && (int)xloc<canvas.length && (int)yloc>=0 && (int)yloc<canvas[0].length){
				if (i<2){
					canvas[(int)xloc][(int)yloc][0] = 255 ; 
					canvas[(int)xloc][(int)yloc][1] = 255 ; 
					canvas[(int)xloc][(int)yloc][2] = 255 ; 
					canvas[(int)xloc][(int)yloc][3] = 255 ; 	
				}
				else {
					canvas[(int)xloc][(int)yloc][0] = 255 ; 
					canvas[(int)xloc][(int)yloc][1] = 0 ; 
					canvas[(int)xloc][(int)yloc][2] = 255 ; 
					canvas[(int)xloc][(int)yloc][3] = 255 ; 
					//normals are always draw from left to right, magenta more right
				}
			}
			xloc += xstep ; 
			yloc += ystep ; 
			
		}
		
		
		/*
		double x = p1[0] ; double y = p1[1] ; 
	
		if(x>0 && y>0 && x<canvas.length && y<canvas[0].length){
			canvas[(int)x][(int)y][0] = 255 ;
			canvas[(int)x][(int)y][1] = 255 ;
			canvas[(int)x][(int)y][2] = 255 ;
			canvas[(int)x][(int)y][3] = 255 ;
		}
		
		//System.out.println(xstep) ; 
		
		double startx = 0, endx = 0 ; 
		double starty = 0, endy = 0 ; 
		if(p1[0] > p2[0]){
			startx = p2[0] ; 
			endx = p1[0] ; 
		}
		else{
			startx = p1[0] ; 
			endx = p2[0] ; 
		}
		if(p1[1] > p2[1]){
			starty = p2[1] ; 
			endy = p1[1] ; 
		}
		else{
			starty = p1[1] ; 
			endy = p2[1] ; 
		}
		while((startx) < (endx) || (starty) < (endy)){
			int xind = (int)startx ; 
			int yind = (int)starty ; 
			if(xind>0 && xind<canvas.length && yind>0 && yind<canvas[0].length){
				canvas[xind][yind][0] = 255 ;
				canvas[xind][yind][1] = 255 ;
				canvas[xind][yind][2] = 255 ;
				canvas[xind][yind][3] = 255 ;
			}
			startx += xstep ; 
			starty += ystep ; 
		//	System.out.println("Drawing startx = " + startx + " stary = " + starty +" endx = " + endx + "endy = " + endy) ; 
		}
		*/
	}
}
