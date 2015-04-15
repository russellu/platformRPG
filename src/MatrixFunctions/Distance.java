package MatrixFunctions;

import java.util.ArrayList;
import java.util.Arrays;

public class Distance {
	
	
	// find the location of the nMins smallest points in the input array
	public static int[] minLoc(double[] input, int nMins){
		int[] sortInds = indexArray(input.length) ; 
		double[] sortInput = new double[input.length] ;
		System.arraycopy(input, 0, sortInput, 0, input.length) ;
		Sort.quicksort(sortInput,sortInds) ; 
		int[] retMins = Arrays.copyOfRange(sortInds,0,nMins) ; 
		return retMins ;
	}
	
	// find the location of the nMaxes largest points in the input array
	//public static int maxLoc(double[] input, int nMaxes){
		
		
	//}
	
	public static int[] indexArray(int size){
		int[] arr = new int[size] ; 
		for(int i=0;i<size;i++)
			arr[i] = i ; 
		return arr ; 
	}
	
	public static double[] points2Vec(double[] p1, double[] p2){
		double[] retVec = {p1[0]-p2[0], p1[1]-p2[1]} ;
		return retVec ; 		
	}
	
	public static void normalizeVec(double[] vec){
		double length = Math.sqrt(Math.pow(vec[0],2) + Math.pow(vec[1],2)) ; 
		vec[0] = vec[0]/length ;
		vec[1] = vec[1]/length ;
	}
	public static double[] calculateNormal(double[] vec){
		double angle = Math.atan(vec[1]/vec[0]) + Math.PI/2 ;
		double normx = Math.cos(angle) ;
		double normy = Math.sin(angle) ; 
		double[] normal = {normx,normy} ;
		return normal ; 	
	}
	
	/**
	 * rotate a 2d vector
	 * 
	 * @param input vector to be rotated
	 * @param theta angle to rotate by
	 * @return
	 */
	public static double[] rotate2D(double[] input, double theta){
		double[][] rotmat = {{Math.cos(theta), -Math.sin(theta)}, {Math.sin(theta), Math.cos(theta)}} ; // rotation matrix 2d
		return multiplyVec2D(input,rotmat) ; 	
	}
	
	
	public static double[] multiplyVec2D(double[] vec, double[][] matrix){
		// multiply all rows in matrix by vec 
		// number of columns of matrix must equal number of rows of vec, number of columns of vec = 1;
		double[] rotatedVec = new double[vec.length] ;
		
		for(int i=0;i<matrix.length;i++){
			double rowSum = 0 ; 
			for(int j=0;j<vec.length;j++){
				rowSum += matrix[i][j] * vec[j]  ;
			}
			rotatedVec[i] = rowSum ; 
		}
		
		return rotatedVec ;
	}
	
	/**
	 * 
	 * dot product between two 1d vectors
	 * @param a
	 * @param b
	 * @return
	 */
	public static double dotProduct(double[] a, double[] b){
		double sum = 0 ; 
		for(int i=0;i<a.length;i++)
			sum += a[i]*b[i] ; 
		
		return sum ; 	
	}
	
	public static double[] minIndex(double[] inputPoint, ArrayList<double[]> allPoints){
		double[] min = new double[2] ; 
		double cmin = Double.MAX_VALUE ; 
		for(int i=0;i<allPoints.size();i++){
			double curr = Math.sqrt(Math.pow(inputPoint[0]-allPoints.get(i)[0],2) + Math.pow(inputPoint[1]-allPoints.get(i)[1],2)) ; 
			if(curr < cmin){
				cmin = curr  ;
				min = allPoints.get(i) ; 
			}	
		}
		return min ;
	}
	
	/**
	 * get the mean of a list of 2d vectors (the mean vector)
	 * @param vecList
	 * @return
	 */
	public static double[] listMean(ArrayList<double[]> vecList){
		double n = vecList.size() ; 
		double xSum = 0 ; 
		double ySum = 0 ; 
		for(int i=0;i<vecList.size();i++){
			xSum += vecList.get(i)[0] ; 
			ySum += vecList.get(i)[1] ; 		
		}
		double[] meanVec = {xSum/n,ySum/n} ;
		return meanVec ;
	}
	
	public static double magnitude(double[] input){
		
		return Math.sqrt(Math.pow(input[0], 2) + Math.pow(input[1], 2)) ;
		
	}
	// the problem is the body is colliding twice or rather, it collides again before it has had a chance to move away
	
	
}
