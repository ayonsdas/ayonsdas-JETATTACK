import java.awt.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class UltiAttack{

	private int x;
	private int y;
	private int counter;
	private Color effectColorO;
	private boolean inEffect;
	private int radius;
	private Player spawner;
	
	public UltiAttack(Player spawn)
	{
		counter = 0;
		radius = 10;
		spawner = spawn;
		inEffect = true;
		this.x = spawner.getX();
		this.y = spawner.getY();
		effectColorO = new Color(255, 0, 0, 255);
	}
	
	public void tick()
	{
		counter += 1;
		radius += 40;
		if(counter > 50)
			inEffect = false;
		x = spawner.getX();
		y = spawner.getY();
	}
	
	public boolean inUse()
	{
		return inEffect;
	}
	
	public void draw(Graphics2D g, boolean maintain)
	{
		if(!maintain)
			tick();
		g.setColor(effectColorO);
		g.setStroke(new BasicStroke(30.f));
		g.draw(new Ellipse2D.Double(x - radius, y - radius, 2 * radius, 2 * radius));
	}
	
	public boolean hasContacted(Player p)
	{
		if(!p.equals(spawner))
		{
			double dist = Math.pow(p.getX() - x, 2) + Math.pow(p.getY() - y, 2);
			boolean iR = dist < Math.pow(radius, 2);
			if(iR)
				return true;
		}
		return false;
	}
	
}