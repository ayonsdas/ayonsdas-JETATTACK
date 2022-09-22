import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.*;

public class ShieldPickup implements Pickup{
	
	int x;
	int y;
	Ellipse2D.Double s;
	int count;
	int[] xpoints;
	int[] ypoints;
	Color currColor;
	Polygon p;
	
	public ShieldPickup(int x, int y) {
		count = 0;
		this.x = x;
		this.y = y;
		xpoints = new int[4]; 
		xpoints[0] = x - 10;
		xpoints[1] = x - 10;
		xpoints[2] = x + 10;
		xpoints[3] = x + 10;
		ypoints = new int[4]; 
		ypoints[0] = y - 10;
		ypoints[1] = y + 10;
		ypoints[2] = y - 10;
		ypoints[3] = y + 10;
		s = new Ellipse2D.Double(x - 10, y - 10, 20, 20);
		p = new Polygon(xpoints, ypoints, 4);
	}
	
	public void draw(Graphics2D gr, boolean maintain)
	{
		if(!maintain)
			tick();
		gr.setColor(currColor);
		gr.fill(s);
	}
	
	public void tick()
	{
		count += 1;
		count %= 1000;
		if(count % 10 < 5)
			currColor = new Color(80, 240, 0);
		else
			currColor = new Color(130, 255, 50);
	}
	    
	public Polygon getShape()
	{
    	return p;
	}

}
