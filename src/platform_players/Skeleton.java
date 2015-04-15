package platform_players;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import MatrixFunctions.Display;
import MatrixFunctions.Distance;

/**
 * 
 * the normals must face to the outside of the body
 * how to ensure this? not really dependent on the tangent, because it's parallel
 * find the closest point inside the skeleton, if it faces towards that point, invert the vector,
 * if it faces away, leave it as it is. 
 * step1) get the surrounding points
 * step2) isolate the surrounding points that are not skeleton or outside skeleton
 * step3) get the closest one of those points, invert normal accordingly. 
 * 
 * @author russ
 *
 */

public class Skeleton {

	public int[][] skeleFrame ; 
	public double[][][] normals ; 
	public ArrayList<double[]> skelePoints ; 
	
	public Skeleton(int w, int h){
		skeleFrame = getRectangularSkeleton(w,h) ;
		alignNormals() ; 
	}
	
	public int[][] getRectangularSkeleton(int w, int h){ // make a rectanglular frame
		
		int[][] skeleton = new int[w][h] ; 
		
		for(int i=0;i<w;i++){
			skeleton[i][0] = 1 ; 
			skeleton[i][h-1] = 1 ; 
		}
		for(int j=0;j<h;j++){
			skeleton[0][j] = 1 ;
			skeleton[w-1][j] = 1 ;
		}			
		calculateNormals(skeleton) ; 
		return skeleton ; 	
		
	}
	
	/**
	 * arraylistdouble calculate normals
	 * make normals from the skeleton
	 * need to find the other points close to the normal we are calculating to get the average vector
	 * @return
	 */
	public void calculateNormals(int[][] skeleton){
		// to hold all points which are part of the skeleton and hence have normals (normPoints)
		ArrayList<double[]> normPoints = new ArrayList<double[]>() ; 
		normals = new double[skeleton.length][skeleton[0].length][2] ; 
		
		for(int i=0;i<skeleton.length;i++){
			for(int j=0;j<skeleton[0].length;j++){				
				if(skeleton[i][j]==1){
					double[] newPoint = {i,j} ; 
					normPoints.add(newPoint) ; 
				}			
			}
		}	
		this.skelePoints = normPoints ; 
	
		System.out.println("normPoints.size  = " + normPoints.size()) ; 
		//calculate the distance matrix between each point and all other points
		double[][] distanceMatrix = new double[normPoints.size()][normPoints.size()] ;
		for(int i=0;i<normPoints.size();i++)
			for(int j=0;j<normPoints.size();j++){
				distanceMatrix[i][j] = Math.sqrt(Math.pow(normPoints.get(i)[0]-normPoints.get(j)[0],2) + // x distance
												 Math.pow(normPoints.get(i)[1]-normPoints.get(j)[1],2)) ; // y distance
			}
		
		// calculate the normal at each point based on the nearest neighbours.
		for(int i=0;i<normPoints.size();i++){
			int[] topInds = Distance.minLoc(distanceMatrix[i],normPoints.size()) ; 
		//	System.out.println("npx = "+normPoints.get(i)[0] + " npy = " + normPoints.get(i)[1] + 
		//					   "topinds[0] = " + normPoints.get(topInds[0])[0] + " topinds[1] = " + normPoints.get(topInds[0])[1]) ; 
			
			// get the tangent, mean of surrounding points
			double[] tangent = Distance.points2Vec(normPoints.get(topInds[1]),normPoints.get(topInds[2])) ; 
			tangent[0] = (tangent[0]) ;
			tangent[1] = (tangent[1]) ; 
			Distance.normalizeVec(tangent) ;
			//System.out.println("tx = " + tangent[0] + " ty = " + tangent[1]) ;
			double[] normal = Distance.calculateNormal(tangent) ; 
			normal[0] = (normal[0]) ; normal[1] = (normal[1]) ; 
			//System.out.println("nx = " + normal[0] + " ny = " + normal[1]) ;	
			
			// put the x,y coordinates of the normal in their proper places in the normal array
			
			normals[(int)normPoints.get(i)[0]][(int)normPoints.get(i)[1]][0] = normal[0] ;
			normals[(int)normPoints.get(i)[0]][(int)normPoints.get(i)[1]][1] = normal[1] ; 
			//normals[(int)normPoints.get(topInds[0])[0]][(int)normPoints.get(topInds[0])[1]][0] = normal[0] ;
			//normals[(int)normPoints.get(topInds[0])[0]][(int)normPoints.get(topInds[0])[1]][1] = normal[1] ; 
		}			
		
	}
	
	/**
	 * align the normals so that they are pointing to the exterior of the body
	 * the skeleFrame has to be max 1 pixel in width
	 * 
	 */
	public void alignNormals(){
		
		for(int i=0;i<skeleFrame.length;i++)
			for(int j=0;j<skeleFrame[0].length;j++){
				if(skeleFrame[i][j]==1){
					ArrayList<double[]> closePoints = new ArrayList<double[]>() ; 
					for(int x=-1;x<2;x++)
						for(int y=-1;y<2;y++){
							int cx = i+x ; 
							int cy = j+y ; 
							if(cx>=0 && cx<skeleFrame.length && cy>=0 && cy<skeleFrame[0].length && skeleFrame[cx][cy] == 0){
								double[] newPoint = {cx,cy} ;
								closePoints.add(newPoint) ; 	
							}
						}
					double[] closest = Distance.minIndex(normals[i][j], closePoints) ; 
					double[] closeVec = {i-closest[0],j-closest[1]} ;
					if(closeVec[0]*normals[i][j][0] < 0)
						normals[i][j][0] = -normals[i][j][0] ;
					if(closeVec[1]*normals[i][j][1] < 0)
						normals[i][j][1] = -normals[i][j][1] ;
						//if(Distance.dotProduct(closeVec, normals[i][j]) > 0){
					//	normals[i][j][0] = - normals[i][j][0] ; 
					//	normals[i][j][1] = - normals[i][j][1] ; 
					//}
					//System.out.println("closest x = " + closeVec[0] + " closest y = " + closeVec[1] + " px = " + normals[i][j][0] + " py = " + normals[i][j][1]) ; 
				}
			}		
	}
	
	
}
