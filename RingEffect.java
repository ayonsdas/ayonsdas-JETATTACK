import java.awt.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class RingEffect implements Effect{

	private int x;
	private int y;
	private int counter;
	private Color effectColor;
	private boolean inEffect;
	private int radius;
	private Player spawner;
	
	public RingEffect(Player spawn)
	{
		counter = 0;
		radius = 10;
		spawner = spawn;
		inEffect = true;
		this.x = spawner.getX();
		this.y = spawner.getY();
		effectColor = new Color(255, 255, 255, 255 - 15 * counter);
	}
	
	public void tick()
	{
		counter += 1;
		radius += 10;
		if(counter <= 17)
			effectColor = new Color(255, 255, 255, 255 - 15 * counter);
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
		g.setColor(effectColor);
		g.setStroke(new BasicStroke(20.f));
		g.draw(new Ellipse2D.Double(x - radius, y - radius, 2 * radius, 2 * radius));
	}
	
}