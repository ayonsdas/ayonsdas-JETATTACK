import java.awt.*;

public class StandardBullet implements Bullet{
	
	double speed;
	double angle; //0 is east, 90 is north
	int damage;
	int x;
	int y;
	int[] xpoints;
	int[] ypoints;
	Polygon p;
	
	public StandardBullet(double ang, int x, int y) {
		speed = 15;
		angle = -ang;
		damage = 10;
		this.x = x;
		this.y = y;
		double a1 = Math.PI / 180 * angle;
		double a2 = Math.PI / 180 * angle + Math.PI * 1 / 4;
		double a3 = Math.PI / 180 * angle + Math.PI * 3 / 4;
		double a4 = Math.PI / 180 * angle + Math.PI * 5 / 4;
		double a5 = Math.PI / 180 * angle + Math.PI * 7 / 4;
		xpoints = new int[5]; 
		xpoints[0] = x + (int) (6 * Math.cos(a1));
		xpoints[1] = x + (int) (4 * Math.cos(a2));
		xpoints[2] = x + (int) (4 * Math.cos(a3));
		xpoints[3] = x + (int) (4 * Math.cos(a4));
		xpoints[4] = x + (int) (4 * Math.cos(a5));
		ypoints = new int[5]; 
		ypoints[0] = y - (int) (6 * Math.sin(a1));
		ypoints[1] = y - (int) (4 * Math.sin(a2));
		ypoints[2] = y - (int) (4 * Math.sin(a3));
		ypoints[3] = y - (int) (4 * Math.sin(a4));
		ypoints[4] = y - (int) (4 * Math.sin(a5));
		p = new Polygon(xpoints, ypoints, 5);
	}
	
	public void draw(Graphics2D gr, boolean maintain)
	{
		if(!maintain)
			tick();
		gr.setColor(new Color(255, 255, 0));
		Polygon p = getShape();
		gr.fill(p);
	}
	
	public void tick()
	{
		x += (int) (Math.cos(angle * Math.PI / 180) * speed); 
		y -= (int) (Math.sin(angle * Math.PI / 180) * speed); 
		for(int i = 0; i < xpoints.length; i++) 
			xpoints[i] += (int) (Math.cos(angle * Math.PI / 180) * speed); 	
		for(int i = 0; i < ypoints.length; i++) 
			ypoints[i] -= (int) (Math.sin(angle * Math.PI / 180) * speed); 
		p = new Polygon(xpoints, ypoints, 5);
	}
	   
	public boolean inFrame(int width, int height) {
		return (x > 0 && x < width && y > 0 && y < height);
	}
	    
	public Polygon getShape()
	{
		Polygon p = new Polygon(xpoints, ypoints, 5);
    	return p;
	}
	
	public int getDamage() {
		return damage;
	}
}