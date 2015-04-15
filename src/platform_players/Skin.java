package platform_players;

/**
 * the skin is just the appearance. a double[][][] array rgba
 * 
 * @author russ
 *
 */

public class Skin {
	
	
	public static double[][][] getRandomSkin(int width, int height){ // create a rand rgba skin
		double[][][]rndSkin = new double[width][height][4] ;
		for(int i =0;i<width;i++)
			for(int j=0;j<height;j++){
				rndSkin[i][j][0] = Math.random()*255 ;  
				rndSkin[i][j][1] = Math.random()*255 ;  
				rndSkin[i][j][2] = Math.random()*255 ; 
				rndSkin[i][j][3] = 255 ; 

			}	
		return rndSkin ; 		
	}

}
