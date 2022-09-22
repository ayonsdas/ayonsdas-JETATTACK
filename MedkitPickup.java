import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class MedkitPickup implements Pickup{
	
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
	private double A5;
	private double A6;
	private double A7;
	private double A8;
	private double A9;
	private double A10;
	private double A11;
	private double A12;
	
	public MedkitPickup(int x, int y) {
		this.x = x;
		this.y = y;
		angle = 0;
		A1 = (angle + 15) * Math.PI / 180;
		A2 = (angle + 45) * Math.PI / 180;
		A3 = (angle + 75) * Math.PI / 180;
		A4 = (angle + 105) * Math.PI / 180;
		A5 = (angle + 135) * Math.PI / 180;
		A6 = (angle + 165) * Math.PI / 180;
		A7 = (angle + 195) * Math.PI / 180;
		A8 = (angle + 225) * Math.PI / 180;
		A9 = (angle + 255) * Math.PI / 180;
		A10 = (angle + 285) * Math.PI / 180;
		A11 = (angle + 315) * Math.PI / 180;
		A12 = (angle + 345) * Math.PI / 180;
		xpoints = new int[12]; 
		xpoints[0] = x + (int) (10 * Math.cos(A1));
		xpoints[1] = x + (int) (3 * Math.cos(A2));
		xpoints[2] = x + (int) (10 * Math.cos(A3));
		xpoints[3] = x + (int) (10 * Math.cos(A4));
		xpoints[4] = x + (int) (3 * Math.cos(A5));
		xpoints[5] = x + (int) (10 * Math.cos(A6));
		xpoints[6] = x + (int) (10 * Math.cos(A7));
		xpoints[7] = x + (int) (3 * Math.cos(A8));
		xpoints[8] = x + (int) (10 * Math.cos(A9));
		xpoints[9] = x + (int) (10 * Math.cos(A10));
		xpoints[10] = x + (int) (3 * Math.cos(A11));
		xpoints[11] = x + (int) (10 * Math.cos(A12));
		ypoints = new int[12]; 
		ypoints[0] = y + (int) (10 * Math.sin(A1));
		ypoints[1] = y + (int) (3 * Math.sin(A2));
		ypoints[2] = y + (int) (10 * Math.sin(A3));
		ypoints[3] = y + (int) (10 * Math.sin(A4));
		ypoints[4] = y + (int) (3 * Math.sin(A5));
		ypoints[5] = y + (int) (10 * Math.sin(A6));
		ypoints[6] = y + (int) (10 * Math.sin(A7));
		ypoints[7] = y + (int) (3 * Math.sin(A8));
		ypoints[8] = y + (int) (10 * Math.sin(A9));
		ypoints[9] = y + (int) (10 * Math.sin(A10));
		ypoints[10] = y + (int) (3 * Math.sin(A11));
		ypoints[11] = y + (int) (10 * Math.sin(A12));
		p = new Polygon(xpoints, ypoints, 12);
	}
	
	public void draw(Graphics2D gr, boolean maintain)
	{
		if(!maintain)
			tick();
		gr.setColor(Color.RED);
		Polygon p = getShape();
		gr.fill(p);
	}
	
	public void tick()
	{
		angle -= 2;
		angle %= 360;
		A1 = (angle + 15) * Math.PI / 180;
		A2 = (angle + 45) * Math.PI / 180;
		A3 = (angle + 75) * Math.PI / 180;
		A4 = (angle + 105) * Math.PI / 180;
		A5 = (angle + 135) * Math.PI / 180;
		A6 = (angle + 165) * Math.PI / 180;
		A7 = (angle + 195) * Math.PI / 180;
		A8 = (angle + 225) * Math.PI / 180;
		A9 = (angle + 255) * Math.PI / 180;
		A10 = (angle + 285) * Math.PI / 180;
		A11 = (angle + 315) * Math.PI / 180;
		A12 = (angle + 345) * Math.PI / 180;
		xpoints = new int[12]; 
		xpoints[0] = x + (int) (10 * Math.cos(A1));
		xpoints[1] = x + (int) (3 * Math.cos(A2));
		xpoints[2] = x + (int) (10 * Math.cos(A3));
		xpoints[3] = x + (int) (10 * Math.cos(A4));
		xpoints[4] = x + (int) (3 * Math.cos(A5));
		xpoints[5] = x + (int) (10 * Math.cos(A6));
		xpoints[6] = x + (int) (10 * Math.cos(A7));
		xpoints[7] = x + (int) (3 * Math.cos(A8));
		xpoints[8] = x + (int) (10 * Math.cos(A9));
		xpoints[9] = x + (int) (10 * Math.cos(A10));
		xpoints[10] = x + (int) (3 * Math.cos(A11));
		xpoints[11] = x + (int) (10 * Math.cos(A12));
		ypoints = new int[12]; 
		ypoints[0] = y + (int) (10 * Math.sin(A1));
		ypoints[1] = y + (int) (3 * Math.sin(A2));
		ypoints[2] = y + (int) (10 * Math.sin(A3));
		ypoints[3] = y + (int) (10 * Math.sin(A4));
		ypoints[4] = y + (int) (3 * Math.sin(A5));
		ypoints[5] = y + (int) (10 * Math.sin(A6));
		ypoints[6] = y + (int) (10 * Math.sin(A7));
		ypoints[7] = y + (int) (3 * Math.sin(A8));
		ypoints[8] = y + (int) (10 * Math.sin(A9));
		ypoints[9] = y + (int) (10 * Math.sin(A10));
		ypoints[10] = y + (int) (3 * Math.sin(A11));
		ypoints[11] = y + (int) (10 * Math.sin(A12));
		p = new Polygon(xpoints, ypoints, 12);
	}
	    
	public Polygon getShape()
	{
		Polygon p = new Polygon(xpoints, ypoints, 12);
    	return p;
	}

}
