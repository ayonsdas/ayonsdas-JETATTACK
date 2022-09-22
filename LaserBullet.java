import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class LaserBullet implements Bullet{
	
	double speed;
	double angle; //0 is east, 90 is north
	int damage;
	int counter;
	int x;
	int y;
	int[] xpoints;
	int[] ypoints;
	int[] xpoints2;
	int[] ypoints2;
	Polygon p;
	Polygon p2;
	private static final Color OUTER = new Color(0, 120, 255, 150);
	private static final Color INNER = new Color(255, 255, 255, 255);
	
	public LaserBullet(double ang, int x, int y) {
		speed = 200;
		angle = -ang;
		damage = 30;
		counter = 0;
		this.x = x;
		this.y = y;
		double a1 = Math.PI / 180 * angle;
		double a2 = Math.PI / 180 * angle + Math.PI * 1 / 16;
		double a5 = Math.PI / 180 * angle + Math.PI * 31 / 16;
		xpoints = new int[5]; 
		xpoints[0] = x + (int) (20 * Math.cos(a1));
		xpoints[1] = x + (int) (10 * Math.cos(a2));
		xpoints[2] = x;
		xpoints[3] = x;
		xpoints[4] = x + (int) (20 * Math.cos(a5));
		ypoints = new int[5]; 
		ypoints[0] = y - (int) (20 * Math.sin(a1));
		ypoints[1] = y - (int) (10 * Math.sin(a2));
		ypoints[2] = y;
		ypoints[3] = y;
		ypoints[4] = y - (int) (20 * Math.sin(a5));
		xpoints2 = new int[5]; 
		xpoints2[0] = x + (int) (10 * Math.cos(a1));
		xpoints2[1] = x + (int) (5 * Math.cos(a2));
		xpoints2[2] = x;
		xpoints2[3] = x;
		xpoints2[4] = x + (int) (10 * Math.cos(a5));
		ypoints2 = new int[5]; 
		ypoints2[0] = y - (int) (10 * Math.sin(a1));
		ypoints2[1] = y - (int) (5 * Math.sin(a2));
		ypoints2[2] = y;
		ypoints2[3] = y;
		ypoints2[4] = y - (int) (10 * Math.sin(a5));
		p = new Polygon(xpoints, ypoints, 5);
		p2 = new Polygon(xpoints2, ypoints2, 5);
	}
	
	public void draw(Graphics2D gr, boolean maintain)
	{
		if(!maintain)
			tick();
		gr.setColor(INNER);
		gr.fill(p2);
		gr.setColor(OUTER);
		gr.fill(p);
	}
	
	public void tick()
	{
		counter += 1;
		x += (int) (Math.cos(angle * Math.PI / 180) * speed); 
		y -= (int) (Math.sin(angle * Math.PI / 180) * speed);
		for(int i = 0; i < xpoints.length; i++)
			if(!(i == 2 || i == 3))
				xpoints[i] += (int) (Math.cos(angle * Math.PI / 180) * speed);
			else
				xpoints[i] += (int) (Math.cos(angle * Math.PI / 180) * speed / 10);
		for(int i = 0; i < ypoints.length; i++) 
			if(!(i == 2 || i == 3))
				ypoints[i] -= (int) (Math.sin(angle * Math.PI / 180) * speed);
			else
				ypoints[i] -= (int) (Math.sin(angle * Math.PI / 180) * speed / 10);
		for(int i = 0; i < xpoints2.length; i++)
			if(!(i == 2 || i == 3))
				xpoints2[i] += (int) (Math.cos(angle * Math.PI / 180) * speed);
			else
				xpoints2[i] += (int) (Math.cos(angle * Math.PI / 180) * speed / 10);
		for(int i = 0; i < ypoints2.length; i++) 
			if(!(i == 2 || i == 3))
				ypoints2[i] -= (int) (Math.sin(angle * Math.PI / 180) * speed);
			else
				ypoints2[i] -= (int) (Math.sin(angle * Math.PI / 180) * speed / 10);
		p = new Polygon(xpoints, ypoints, 5);
		p2 = new Polygon(xpoints2, ypoints2, 5);
	}
	   
	public boolean inFrame(int width, int height) {
		return counter <= 20;
	}
	    
	public Polygon getShape()
	{
    	return p;
	}
	
	public void nullify()
	{
		damage = 0;
	}
	
	public int getDamage()
	{
		return damage;
	}
}
