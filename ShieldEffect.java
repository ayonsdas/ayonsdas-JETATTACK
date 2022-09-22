import java.awt.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class ShieldEffect implements Effect{

	private int x;
	private int y;
	private int counter;
	private Color effectColorO;
	private Color effectColorI;
	private boolean inEffect;
	private int radius;
	private Player spawner;
	
	public ShieldEffect(Player spawn)
	{
		counter = 0;
		radius = 10;
		spawner = spawn;
		inEffect = true;
		this.x = spawner.getX();
		this.y = spawner.getY();
		effectColorO = new Color(0, 120, 0, 255);
		effectColorI = new Color(0, 255, 0, 255);
	}
	
	public void tick()
	{
		counter += 1;
		radius += 10;
		if(counter <= 17)
		{
			effectColorO = new Color(0, 120, 0, 255 - 15 * counter);
			effectColorI = new Color(0, 255, 0, 255 - 15 * counter);
		}
		else
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
		g.setStroke(new BasicStroke(10.f));
		g.draw(new Ellipse2D.Double(x - radius, y - radius, 2 * radius, 2 * radius));
		g.setColor(effectColorI);
		g.setStroke(new BasicStroke(3.0f));
		g.draw(new Ellipse2D.Double(x - radius, y - radius, 2 * radius, 2 * radius));
	}
	
}