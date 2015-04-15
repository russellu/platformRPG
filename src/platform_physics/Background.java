package platform_physics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
* the background is the thing on which we draw
*
 * @author russ
 *
 */


public class Background extends JPanel {
	
	private BufferedImage bim = new BufferedImage(400,400,BufferedImage.TYPE_INT_ARGB) ; 
	private Graphics2D g2 = bim.createGraphics() ; 
	private double[][][] background ; 
	private JFrame jf ; 
	
	public Background(){
		initFrame() ;
		setBackground() ;
		
	}
	
	public void render(){
		WritableRaster rast = bim.getRaster() ; 
		for(int i=0;i<background.length;i++)
			for(int j=0;j<background[i].length;j++){
				rast.setSample(i, j, 0, background[i][j][0]);
				rast.setSample(i, j, 1, background[i][j][1]);
				rast.setSample(i, j, 2, background[i][j][2]);

				rast.setSample(i, j, 3, 255);
				
				
			}
		
		bim.setData(rast) ;
	}
	
	public double[][][] getBG(){
		return background ; 
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g ; 
		//g2.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON));
		g2.drawImage(bim, 0, 0, null) ; 
	}
	
	public void refresh(){
		render() ; 
		repaint() ; 
		//System.out.println("Refreshing") ; 
	}
	
	/*public void run(){
		while(true){
			try{Thread.sleep(100);}catch(Exception e){}
			render() ; 
			repaint() ; 
			
		}
		
		
	}
	*/
	
	public int[] getDim(){
		int[] dim = {bim.getWidth(),bim.getHeight()} ;
		return dim ; 
	}
	
	public void setBackground(){
		background = new double[bim.getWidth()][bim.getHeight()][4] ; 
		for(int i=0;i<bim.getWidth();i++)
			for(int j=0;j<bim.getHeight();j++){
				background[i][j][0] = 0 ; 
				background[i][j][1] = 0 ; 
				background[i][j][2] = 0 ; 
				background[i][j][3] = 0 ;			
			}	
	}
	
	public void initFrame(){
		jf = new JFrame("lulz") ; 
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(this) ; 
		jf.setPreferredSize(new Dimension(bim.getWidth()+40,bim.getHeight()+50));
		jf.pack() ; 
		jf.setVisible(true);
		
		
	}
	
	public static void main(String[]args){
		
		Background bg = new Background() ; 
		
		
		
	}

}
