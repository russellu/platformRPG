package platform_physics;

public class Collision {

	
	
	/**
	This function calulates the velocities after a 2D collision vaf, vbf, waf and wbf from information about the colliding bodies
	@param double e coefficient of restitution which depends on the nature of the two colliding materials
	@param double ma total mass of body a
	@param double mb total mass of body b
	@param double Ia inertia for body a.
	@param double Ib inertia for body b.
	@param vector ra position of collision point relative to centre of mass of body a in absolute coordinates (if this is
	                 known in local body coordinates it must be converted before this is called).
	@param vector rb position of collision point relative to centre of mass of body b in absolute coordinates (if this is
	                 known in local body coordinates it must be converted before this is called).
	@param vector n normal to collision point, the line along which the impulse acts.
	@param vector vai initial velocity of centre of mass on object a
	@param vector vbi initial velocity of centre of mass on object b
	@param vector wai initial angular velocity of object a
	@param vector wbi initial angular velocity of object b
	@param vector vaf final velocity of centre of mass on object a
	@param vector vbf final velocity of centre of mass on object a
	@param vector waf final angular velocity of object a
	@param vector wbf final angular velocity of object b
	*/
	/*
	CollisionResponce(double e,double ma,double mb,matrix Ia,matrix Ib,vector ra,vector rb,vector n,
	    vector vai, vector vbi, vector wai, vector wbi, vector vaf, vector vbf, vector waf, vector wbf) {
		  double k=1/(ma*ma)+ 2/(ma*mb) +1/(mb*mb) - ra.x*ra.x/(ma*Ia) - rb.x*rb.x/(ma*Ib)  - ra.y*ra.y/(ma*Ia)
		    - ra.y*ra.y/(mb*Ia) - ra.x*ra.x/(mb*Ia) - rb.x*rb.x/(mb*Ib) - rb.y*rb.y/(ma*Ib)
		    - rb.y*rb.y/(mb*Ib) + ra.y*ra.y*rb.x*rb.x/(Ia*Ib) + ra.x*ra.x*rb.y*rb.y/(Ia*Ib) - 2*ra.x*ra.y*rb.x*rb.y/(Ia*Ib);
		  double Jx = (e+1)/k * (Vai.x - Vbi.x)( 1/ma - ra.x*ra.x/Ia + 1/mb - rb.x*rb.x/Ib)
		     - (e+1)/k * (Vai.y - Vbi.y) (ra.x*ra.y / Ia + rb.x*rb.y / Ib);
		  double Jy = - (e+1)/k * (Vai.x - Vbi.x) (ra.x*ra.y / Ia + rb.x*rb.y / Ib)
		     + (e+1)/k  * (Vai.y - Vbi.y) ( 1/ma - ra.y*ra.y/Ia + 1/mb - rb.y*rb.y/Ib);
		  Vaf.x = Vai.x - Jx/Ma;
		  Vaf.y = Vai.y - Jy/Ma;
		  Vbf.x = Vbi.x - Jx/Mb;
		  Vbf.y = Vbi.y - Jy/Mb;
		  waf.x = wai.x - (Jx*ra.y - Jy*ra.x) /Ia;
		  waf.y = wai.y - (Jx*ra.y - Jy*ra.x) /Ia;
		  wbf.x = wbi.x - (Jx*rb.y - Jy*rb.x) /Ib;
		  wbf.y = wbi.y - (Jx*rb.y - Jy*rb.x) /Ib;
	}
	*/
}
