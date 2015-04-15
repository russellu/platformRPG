package MatrixFunctions;

import java.util.ArrayList;

public class Display {

	
	public static void dispList(ArrayList input){
		for(int i=0;i<input.size();i++){
			if(input.get(0).getClass().equals(double[].class)) {
				double[] vals = (double[])input.get(i) ; 
				System.out.println("i="+i+" valx="+vals[0]+" valy="+vals[1]) ; 	
			}
			
		}
		
	}
	
	public static void dispArr1D(Object[][] input){
		for(int i=0;i<input.length;i++)
			for(int j=0;j<input[0].length;j++)
				System.out.println("i="+i+" j="+j+" val="+input[i][j]) ; 
		
		
	}
		
}
