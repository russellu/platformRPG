package platform_physics;

public class LowLvl {
	
	public static boolean compareBoolean(boolean[]x,boolean[]y){
		
		if(x.length!=y.length)
			return false ; 
		
		for(int i=0;i<x.length;i++)
			if(x[i] != y[i])
				return false ;
		
		return true ;
	}
	
	
	
	

}
