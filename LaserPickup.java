import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class LaserPickup implements Pickup{
	
	int x;
	int y;
	private int angle;
	int[] xpoints;
	int[] ypoints;
	Polygon p;
	private double A1;
	private double A2;
	private double A3;
	private double A4;
	
	public LaserPickup(int x, int y) {
		this.x = x;
		this.y = y;
		angle = 0;
		A1 = (angle + 10) * Math.PI / 180;
		A2 = (angle + 170) * Math.PI / 180;
		A3 = (angle + 190) * Math.PI / 180;
		A4 = (angle + 350) * Math.PI / 180;
		xpoints = new int[4]; 
		xpoints[0] = x + (int) (10 * Math.cos(A1));
		xpoints[1] = x + (int) (10 * Math.cos(A2));
		xpoints[2] = x + (int) (10 * Math.cos(A3));
		xpoints[3] = x + (int) (10 * Math.cos(A4));
		ypoints = new int[4]; 
		ypoints[0] = y - (int) (10 * Math.sin(A1));
		ypoints[1] = y - (int) (10 * Math.sin(A2));
		ypoints[2] = y - (int) (10 * Math.sin(A3));
		ypoints[3] = y - (int) (10 * Math.sin(A4));
		p = new Polygon(xpoints, ypoints, 4);
	}
	
	public void draw(Graphics2D gr, boolean maintain)
	{
		if(!maintain)
			tick();
		gr.setColor(new Color(0, 80, 240));
		Polygon p = getShape();
		gr.fill(p);
	}
	
	public void tick()
	{
		angle -= 2;
		angle %= 360;
		A1 = (angle + 10) * Math.PI / 180;
		A2 = (angle + 170) * Math.PI / 180;
		A3 = (angle + 190) * Math.PI / 180;
		A4 = (angle + 350) * Math.PI / 180;
		xpoints = new int[4]; 
		xpoints[0] = x + (int) (10 * Math.cos(A1));
		xpoints[1] = x + (int) (10 * Math.cos(A2));
		xpoints[2] = x + (int) (10 * Math.cos(A3));
		xpoints[3] = x + (int) (10 * Math.cos(A4));
		ypoints = new int[4]; 
		ypoints[0] = y - (int) (10 * Math.sin(A1));
		ypoints[1] = y - (int) (10 * Math.sin(A2));
		ypoints[2] = y - (int) (10 * Math.sin(A3));
		ypoints[3] = y - (int) (10 * Math.sin(A4));
		p = new Polygon(xpoints, ypoints, 4);
	}
	    
	public Polygon getShape()
	{
		Polygon p = new Polygon(xpoints, ypoints, 4);
    	return p;
	}

}
