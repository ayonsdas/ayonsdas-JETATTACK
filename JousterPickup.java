import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class JousterPickup implements Pickup{
	
	int x;
	int y;
	int angle;
	int[] xpoints;
	int[] ypoints;
	int[] xpoints2;
	int[] ypoints2;
	private double A1;
	private double A2;
	private double A3;
	private double A4;
	private static final Color color = new Color(0, 150, 50);
	Polygon p;
	Polygon p2;
	
	public JousterPickup(int x, int y) {
		this.x = x;
		this.y = y;
		angle = 0;
		A1 = (angle + 10) * Math.PI / 180;
		A2 = (angle + 170) * Math.PI / 180;
		A3 = (angle + 190) * Math.PI / 180;
		A4 = (angle + 350) * Math.PI / 180;
		double aR = (angle + 270) * Math.PI / 180;
		xpoints = new int[4]; 
		xpoints[0] = x + (int) (10 * Math.cos(A1)) + (int) (5 * Math.cos(aR));
		xpoints[1] = x + (int) (10 * Math.cos(A2)) + (int) (5 * Math.cos(aR));
		xpoints[2] = x + (int) (10 * Math.cos(A3)) + (int) (5 * Math.cos(aR));
		xpoints[3] = x + (int) (10 * Math.cos(A4)) + (int) (5 * Math.cos(aR));
		ypoints = new int[4]; 
		ypoints[0] = y - (int) (10 * Math.sin(A1)) - (int) (5 * Math.sin(aR));
		ypoints[1] = y - (int) (10 * Math.sin(A2)) - (int) (5 * Math.sin(aR));
		ypoints[2] = y - (int) (10 * Math.sin(A3)) - (int) (5 * Math.sin(aR));
		ypoints[3] = y - (int) (10 * Math.sin(A4)) - (int) (5 * Math.sin(aR));
		xpoints2 = new int[4]; 
		xpoints2[0] = x + (int) (10 * Math.cos(A1)) - (int) (5 * Math.cos(aR));
		xpoints2[1] = x + (int) (10 * Math.cos(A2)) - (int) (5 * Math.cos(aR));
		xpoints2[2] = x + (int) (10 * Math.cos(A3)) - (int) (5 * Math.cos(aR));
		xpoints2[3] = x + (int) (10 * Math.cos(A4)) - (int) (5 * Math.cos(aR));
		ypoints2 = new int[4]; 
		ypoints2[0] = y - (int) (10 * Math.sin(A1)) + (int) (5 * Math.sin(aR));
		ypoints2[1] = y - (int) (10 * Math.sin(A2)) + (int) (5 * Math.sin(aR));
		ypoints2[2] = y - (int) (10 * Math.sin(A3)) + (int) (5 * Math.sin(aR));
		ypoints2[3] = y - (int) (10 * Math.sin(A4)) + (int) (5 * Math.sin(aR));
		p = new Polygon(xpoints, ypoints, 4);
		p2 = new Polygon(xpoints2, ypoints2, 4);
	}
	
	public void draw(Graphics2D gr, boolean maintain)
	{
		if(!maintain)
			tick();
		gr.setColor(color);
		gr.fill(p);
		gr.fill(p2);
	}
	
	public void tick()
	{
		angle -= 2;
		angle %= 360;
		A1 = (angle + 10) * Math.PI / 180;
		A2 = (angle + 170) * Math.PI / 180;
		A3 = (angle + 190) * Math.PI / 180;
		A4 = (angle + 350) * Math.PI / 180;
		double aR = (angle + 270) * Math.PI / 180;
		xpoints = new int[4]; 
		xpoints[0] = x + (int) (10 * Math.cos(A1)) + (int) (5 * Math.cos(aR));
		xpoints[1] = x + (int) (10 * Math.cos(A2)) + (int) (5 * Math.cos(aR));
		xpoints[2] = x + (int) (10 * Math.cos(A3)) + (int) (5 * Math.cos(aR));
		xpoints[3] = x + (int) (10 * Math.cos(A4)) + (int) (5 * Math.cos(aR));
		ypoints = new int[4]; 
		ypoints[0] = y - (int) (10 * Math.sin(A1)) - (int) (5 * Math.sin(aR));
		ypoints[1] = y - (int) (10 * Math.sin(A2)) - (int) (5 * Math.sin(aR));
		ypoints[2] = y - (int) (10 * Math.sin(A3)) - (int) (5 * Math.sin(aR));
		ypoints[3] = y - (int) (10 * Math.sin(A4)) - (int) (5 * Math.sin(aR));
		xpoints2 = new int[4]; 
		xpoints2[0] = x + (int) (10 * Math.cos(A1)) - (int) (5 * Math.cos(aR));
		xpoints2[1] = x + (int) (10 * Math.cos(A2)) - (int) (5 * Math.cos(aR));
		xpoints2[2] = x + (int) (10 * Math.cos(A3)) - (int) (5 * Math.cos(aR));
		xpoints2[3] = x + (int) (10 * Math.cos(A4)) - (int) (5 * Math.cos(aR));
		ypoints2 = new int[4]; 
		ypoints2[0] = y - (int) (10 * Math.sin(A1)) + (int) (5 * Math.sin(aR));
		ypoints2[1] = y - (int) (10 * Math.sin(A2)) + (int) (5 * Math.sin(aR));
		ypoints2[2] = y - (int) (10 * Math.sin(A3)) + (int) (5 * Math.sin(aR));
		ypoints2[3] = y - (int) (10 * Math.sin(A4)) + (int) (5 * Math.sin(aR));
		p = new Polygon(xpoints, ypoints, 4);
		p2 = new Polygon(xpoints2, ypoints2, 4);
	}
	    
	public Polygon getShape()
	{
    	return p;
	}

}
