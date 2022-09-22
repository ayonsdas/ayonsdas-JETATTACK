import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class BombPickup implements Pickup{
	
	int x;
	int y;
	private int angle;
	int[] xpoints;
	int[] ypoints;
	int[] xpoints2;
	int[] ypoints2;
	int[] xpoints3;
	int[] ypoints3;
	Polygon p;
	Polygon p2;
	Polygon p3;
	private double A1;
	private double A2;
	private double A3;
	private double A4;
	
	public BombPickup(int x, int y) {
		this.x = x;
		this.y = y;
		angle = 0;
		A1 = (angle + 45) * Math.PI / 180;
		A2 = (angle + 135) * Math.PI / 180;
		A3 = (angle + 225) * Math.PI / 180;
		A4 = (angle + 315) * Math.PI / 180;
		xpoints = new int[4]; 
		xpoints[0] = x + (int) (10 * Math.cos(A1));
		xpoints[1] = x + (int) (10 * Math.cos(A2));
		xpoints[2] = x + (int) (10 * Math.cos(A3));
		xpoints[3] = x + (int) (10 * Math.cos(A4));
		ypoints = new int[4]; 
		ypoints[0] = y + (int) (10 * Math.sin(A1));
		ypoints[1] = y + (int) (10 * Math.sin(A2));
		ypoints[2] = y + (int) (10 * Math.sin(A3));
		ypoints[3] = y + (int) (10 * Math.sin(A4));
		xpoints2 = new int[4]; 
		xpoints2[0] = x + (int) (8 * Math.cos(A1));
		xpoints2[1] = x + (int) (8 * Math.cos(A2));
		xpoints2[2] = x + (int) (8 * Math.cos(A3));
		xpoints2[3] = x + (int) (8 * Math.cos(A4));
		ypoints2 = new int[4]; 
		ypoints2[0] = y + (int) (8 * Math.sin(A1));
		ypoints2[1] = y + (int) (8 * Math.sin(A2));
		ypoints2[2] = y + (int) (8 * Math.sin(A3));
		ypoints2[3] = y + (int) (8 * Math.sin(A4));
		xpoints3 = new int[4]; 
		xpoints3[0] = x + (int) (3 * Math.cos(A1));
		xpoints3[1] = x + (int) (3 * Math.cos(A2));
		xpoints3[2] = x + (int) (3 * Math.cos(A3));
		xpoints3[3] = x + (int) (3 * Math.cos(A4));
		ypoints3 = new int[4]; 
		ypoints3[0] = y + (int) (3 * Math.sin(A1));
		ypoints3[1] = y + (int) (3 * Math.sin(A2));
		ypoints3[2] = y + (int) (3 * Math.sin(A3));
		ypoints3[3] = y + (int) (3 * Math.sin(A4));
		p = new Polygon(xpoints, ypoints, 4);
		p2 = new Polygon(xpoints2, ypoints2, 4);
		p3 = new Polygon(xpoints3, ypoints3, 4);
	}
	
	public void draw(Graphics2D gr, boolean maintain)
	{
		if(!maintain)
			tick();
		gr.setColor(Color.WHITE);
		gr.fill(p);
		gr.setColor(new Color(60, 60, 60));
		gr.fill(p2);
		gr.setColor(new Color(142, 40, 0));
		gr.fill(p3);
	}
	
	public void tick()
	{
		angle -= 2;
		angle %= 360;
		A1 = (angle + 45) * Math.PI / 180;
		A2 = (angle + 135) * Math.PI / 180;
		A3 = (angle + 225) * Math.PI / 180;
		A4 = (angle + 315) * Math.PI / 180;
		xpoints = new int[4]; 
		xpoints[0] = x + (int) (10 * Math.cos(A1));
		xpoints[1] = x + (int) (10 * Math.cos(A2));
		xpoints[2] = x + (int) (10 * Math.cos(A3));
		xpoints[3] = x + (int) (10 * Math.cos(A4));
		ypoints = new int[4]; 
		ypoints[0] = y + (int) (10 * Math.sin(A1));
		ypoints[1] = y + (int) (10 * Math.sin(A2));
		ypoints[2] = y + (int) (10 * Math.sin(A3));
		ypoints[3] = y + (int) (10 * Math.sin(A4));
		xpoints2 = new int[4]; 
		xpoints2[0] = x + (int) (8 * Math.cos(A1));
		xpoints2[1] = x + (int) (8 * Math.cos(A2));
		xpoints2[2] = x + (int) (8 * Math.cos(A3));
		xpoints2[3] = x + (int) (8 * Math.cos(A4));
		ypoints2 = new int[4]; 
		ypoints2[0] = y + (int) (8 * Math.sin(A1));
		ypoints2[1] = y + (int) (8 * Math.sin(A2));
		ypoints2[2] = y + (int) (8 * Math.sin(A3));
		ypoints2[3] = y + (int) (8 * Math.sin(A4));
		xpoints3 = new int[4]; 
		xpoints3[0] = x + (int) (3 * Math.cos(A1));
		xpoints3[1] = x + (int) (3 * Math.cos(A2));
		xpoints3[2] = x + (int) (3 * Math.cos(A3));
		xpoints3[3] = x + (int) (3 * Math.cos(A4));
		ypoints3 = new int[4]; 
		ypoints3[0] = y + (int) (3 * Math.sin(A1));
		ypoints3[1] = y + (int) (3 * Math.sin(A2));
		ypoints3[2] = y + (int) (3 * Math.sin(A3));
		ypoints3[3] = y + (int) (3 * Math.sin(A4));
		p = new Polygon(xpoints, ypoints, 4);
		p2 = new Polygon(xpoints2, ypoints2, 4);
		p3 = new Polygon(xpoints3, ypoints3, 4);
	}
	    
	public Polygon getShape()
	{
		Polygon p = new Polygon(xpoints, ypoints, 4);
    	return p;
	}

}
