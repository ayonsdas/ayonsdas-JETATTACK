import java.awt.*;
import java.util.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Queue;
import java.util.LinkedList;
import java.awt.geom.Ellipse2D;

public class Player {

	private int dangle;
	private int angle; //0 is east, 90 is north
	private int hp;
	private Polygon p;
	private Polygon lWing;
	private Polygon rWing;
	private Polygon lTurr;
	private Polygon rTurr;
	private double x;
	private double y;
	private int spd;
	private int shp;
	private int[] xpoints;
	private int[] ypoints;
	private String name;
	private Color color;
	public final static int DANGLE = 5;
	public final static int MAX_BULLETS = 3;
	private Player enemy;
	private Set<Bullet> b;
	private Queue<String> specials;
	private boolean hasShield;
	private boolean hasLJoust;
	private boolean hasRJoust;
	private int jCount;
	private int[] xPLJ;
	private int[] yPLJ;
	private int[] xPRJ;
	private int[] yPRJ;
	private Polygon lJ;
	private Polygon rJ;
	private boolean hasStack;
	private int counter;
	private int counterFlash;
	private static final Color LOWSHIELD1 = new Color(255, 20, 10, 120);
	private static final Color LOWSHIELD2 = new Color(255, 40, 20, 150);
	private static final Color MIDSHIELD1 = new Color(255, 180, 80, 160);
	private static final Color MIDSHIELD2 = new Color(255, 240, 120, 200);
	private static final Color FULLSHIELD1 = new Color(120, 255, 80, 160);
	private static final Color FULLSHIELD2 = new Color(180, 255, 120, 200);
	private static final Color JCOLOR = new Color(0, 150, 50);
	public final static int MAX_SPECIALS = 5;
	
	public Player(int x, int y, int angle, Player e, String n)
	{
		counter = 0;
		shp = 0;
		this.x = x;
		this.y = y;
		this.hp = 30;
		dangle = 0;
		this.angle = angle;
		spd = 4;
		name = n;
		enemy = e;
		hasLJoust = false;
		hasRJoust = false;
		jCount = 0;
		b = new HashSet<Bullet>();
		specials = new LinkedList<>();
		double a1 = Math.PI / 180 * angle;
		double a2 = Math.PI / 180 * angle + Math.PI * 1 / 12;
		double a3 = Math.PI / 180 * angle + Math.PI * 5 / 6;
		double a4 = Math.PI / 180 * angle + Math.PI * 7 / 6;
		double a5 = Math.PI / 180 * angle + Math.PI * 23 / 12;
		double aR = Math.PI / 180 * angle + Math.PI * 1 / 2;
		xpoints = new int[5];
		xpoints[0] = (int) (x + (25 * Math.cos(a1)));
		xpoints[1] = (int) (x + (20 * Math.cos(a2)));
		xpoints[2] = (int) (x + (15 * Math.cos(a3)));
		xpoints[3] = (int) (x + (15 * Math.cos(a4)));
		xpoints[4] = (int) (x + (20 * Math.cos(a5)));
		ypoints = new int[5];
		ypoints[0] = (int) (y + (25 * Math.sin(a1)));
		ypoints[1] = (int) (y + (20 * Math.sin(a2)));
		ypoints[2] = (int) (y + (15 * Math.sin(a3)));
		ypoints[3] = (int) (y + (15 * Math.sin(a4)));
		ypoints[4] = (int) (y + (20 * Math.sin(a5)));
		double a1LW = Math.PI / 180 * angle + Math.PI * 8 / 5;
		double a2LW = Math.PI / 180 * angle + Math.PI * 4 / 3;
		double a3LW = Math.PI / 180 * angle + Math.PI * 5 / 6;
		int[] xPLWing = new int[3];
		xPLWing[0] = (int) (x + (7.5 * Math.cos(a1LW) * Math.sqrt(2) / 2));
		xPLWing[1] = (int) (x + (30 * Math.cos(a2LW)));
		xPLWing[2] = (int) (x + (15 * Math.cos(a3LW)));
		int[] yPLWing = new int[3];
		yPLWing[0] = (int) (y + (7.5 * Math.sin(a1LW) * Math.sqrt(2) / 2));
		yPLWing[1] = (int) (y + (30 * Math.sin(a2LW)));
		yPLWing[2] = (int) (y + (15 * Math.sin(a3LW)));
		double a1RW = Math.PI / 180 * angle + Math.PI * 2 / 5;
		double a2RW = Math.PI / 180 * angle + Math.PI * 2 / 3;
		double a3RW = Math.PI / 180 * angle + Math.PI * 7 / 6;
		int[] xPRWing = new int[3];
		xPRWing[0] = (int) (x + (7.5 * Math.cos(a1RW) * Math.sqrt(2) / 2));
		xPRWing[1] = (int) (x + (30 * Math.cos(a2RW)));
		xPRWing[2] = (int) (x + (15 * Math.cos(a3RW)));
		int[] yPRWing = new int[3];
		yPRWing[0] = (int) (y + (7.5 * Math.sin(a1RW) * Math.sqrt(2) / 2));
		yPRWing[1] = (int) (y + (30 * Math.sin(a2RW)));
		yPRWing[2] = (int) (y + (15 * Math.sin(a3RW)));
		int[] xPLTurr = new int[5];
		xPLTurr[0] = (int) (x + (20 * Math.cos(a1) * 5 / 6) - (6 * Math.cos(a1)) - (8 * Math.cos(aR)));
		xPLTurr[1] = (int) (x + (16 * Math.cos(a2) * 5 / 6) - (6 * Math.cos(a1)) - (8 * Math.cos(aR)));
		xPLTurr[2] = (int) (x + (12 * Math.cos(a3) * 5 / 6) - (6 * Math.cos(a1)) - (8 * Math.cos(aR)));
		xPLTurr[3] = (int) (x + (12 * Math.cos(a4) * 5 / 6) - (6 * Math.cos(a1)) - (8 * Math.cos(aR)));
		xPLTurr[4] = (int) (x + (16 * Math.cos(a5) * 5 / 6) - (6 * Math.cos(a1)) - (8 * Math.cos(aR)));
		int[] yPLTurr = new int[5];
		yPLTurr[0] = (int) (y + (20 * Math.sin(a1) * 5 / 6) - (6 * Math.sin(a1)) - (8 * Math.sin(aR)));
		yPLTurr[1] = (int) (y + (16 * Math.sin(a2) * 5 / 6) - (6 * Math.sin(a1)) - (8 * Math.sin(aR)));
		yPLTurr[2] = (int) (y + (12 * Math.sin(a3) * 5 / 6) - (6 * Math.sin(a1)) - (8 * Math.sin(aR)));
		yPLTurr[3] = (int) (y + (12 * Math.sin(a4) * 5 / 6) - (6 * Math.sin(a1)) - (8 * Math.sin(aR)));
		yPLTurr[4] = (int) (y + (16 * Math.sin(a5) * 5 / 6) - (6 * Math.sin(a1)) - (8 * Math.sin(aR)));
		int[] xPRTurr = new int[5];
		xPRTurr[0] = (int) (x + (20 * Math.cos(a1) * 5 / 6) - (6 * Math.cos(a1)) + (8 * Math.cos(aR)));
		xPRTurr[1] = (int) (x + (16 * Math.cos(a2) * 5 / 6) - (6 * Math.cos(a1)) + (8 * Math.cos(aR)));
		xPRTurr[2] = (int) (x + (12 * Math.cos(a3) * 5 / 6) - (6 * Math.cos(a1)) + (8 * Math.cos(aR)));
		xPRTurr[3] = (int) (x + (12 * Math.cos(a4) * 5 / 6) - (6 * Math.cos(a1)) + (8 * Math.cos(aR)));
		xPRTurr[4] = (int) (x + (16 * Math.cos(a5) * 5 / 6) - (6 * Math.cos(a1)) + (8 * Math.cos(aR)));
		int[] yPRTurr = new int[5];
		yPRTurr[0] = (int) (y + (20 * Math.sin(a1) * 5 / 6) - (6 * Math.sin(a1)) + (8 * Math.sin(aR)));
		yPRTurr[1] = (int) (y + (16 * Math.sin(a2) * 5 / 6) - (6 * Math.sin(a1)) + (8 * Math.sin(aR)));
		yPRTurr[2] = (int) (y + (12 * Math.sin(a3) * 5 / 6) - (6 * Math.sin(a1)) + (8 * Math.sin(aR)));
		yPRTurr[3] = (int) (y + (12 * Math.sin(a4) * 5 / 6) - (6 * Math.sin(a1)) + (8 * Math.sin(aR)));
		yPRTurr[4] = (int) (y + (16 * Math.sin(a5) * 5 / 6) - (6 * Math.sin(a1)) + (8 * Math.sin(aR)));
		p = new Polygon(xpoints, ypoints, 5);
		lWing = new Polygon(xPLWing, yPLWing, 3);
		rWing = new Polygon(xPRWing, yPRWing, 3);
		lTurr = new Polygon(xPLTurr, yPLTurr, 5);
		rTurr = new Polygon(xPRTurr, yPRTurr, 5);
		color = new Color((int) (Math.random() * 120), (int) (Math.random() * 120), (int) (Math.random() * 120));
	}
	
	public void tick()
	{
		counter += 1;
		counter = counter % 6;
		counterFlash += 1;
		counterFlash %= 8;
		if((hasLJoust || hasRJoust) && jCount < 100)
		{
			jCount += 1;
			spd = 6;
		}
		else
			spd = 4;
		x += (Math.cos(angle * Math.PI / 180) * spd);
		y += (Math.sin(angle * Math.PI / 180) * spd);
		double a1 = Math.PI / 180 * angle;
		double a2 = Math.PI / 180 * angle + Math.PI * 1 / 12;
		double a3 = Math.PI / 180 * angle + Math.PI * 5 / 6;
		double a4 = Math.PI / 180 * angle + Math.PI * 7 / 6;
		double a5 = Math.PI / 180 * angle + Math.PI * 23 / 12;
		double aR = Math.PI / 180 * angle + Math.PI * 1 / 2;
		xpoints = new int[5];
		xpoints[0] = (int) (x + (25 * Math.cos(a1)));
		xpoints[1] = (int) (x + (20 * Math.cos(a2)));
		xpoints[2] = (int) (x + (15 * Math.cos(a3)));
		xpoints[3] = (int) (x + (15 * Math.cos(a4)));
		xpoints[4] = (int) (x + (20 * Math.cos(a5)));
		ypoints = new int[5];
		ypoints[0] = (int) (y + (25 * Math.sin(a1)));
		ypoints[1] = (int) (y + (20 * Math.sin(a2)));
		ypoints[2] = (int) (y + (15 * Math.sin(a3)));
		ypoints[3] = (int) (y + (15 * Math.sin(a4)));
		ypoints[4] = (int) (y + (20 * Math.sin(a5)));
		double a1LW = Math.PI / 180 * angle + Math.PI * 8 / 5;
		double a2LW = Math.PI / 180 * angle + Math.PI * 4 / 3;
		double a3LW = Math.PI / 180 * angle + Math.PI * 5 / 6;
		int[] xPLWing = new int[3];
		xPLWing[0] = (int) (x + (7.5 * Math.cos(a1LW) * Math.sqrt(2) / 2));
		xPLWing[1] = (int) (x + (30 * Math.cos(a2LW)));
		xPLWing[2] = (int) (x + (15 * Math.cos(a3LW)));
		int[] yPLWing = new int[3];
		yPLWing[0] = (int) (y + (7.5 * Math.sin(a1LW) * Math.sqrt(2) / 2));
		yPLWing[1] = (int) (y + (30 * Math.sin(a2LW)));
		yPLWing[2] = (int) (y + (15 * Math.sin(a3LW)));
		double a1RW = Math.PI / 180 * angle + Math.PI * 2 / 5;
		double a2RW = Math.PI / 180 * angle + Math.PI * 2 / 3;
		double a3RW = Math.PI / 180 * angle + Math.PI * 7 / 6;
		int[] xPRWing = new int[3];
		xPRWing[0] = (int) (x + (7.5 * Math.cos(a1RW) * Math.sqrt(2) / 2));
		xPRWing[1] = (int) (x + (30 * Math.cos(a2RW)));
		xPRWing[2] = (int) (x + (15 * Math.cos(a3RW)));
		int[] yPRWing = new int[3];
		yPRWing[0] = (int) (y + (7.5 * Math.sin(a1RW) * Math.sqrt(2) / 2));
		yPRWing[1] = (int) (y + (30 * Math.sin(a2RW)));
		yPRWing[2] = (int) (y + (15 * Math.sin(a3RW)));
		int[] xPLTurr = new int[5];
		xPLTurr[0] = (int) (x + (20 * Math.cos(a1) * 5 / 6) - (6 * Math.cos(a1)) - (8 * Math.cos(aR)));
		xPLTurr[1] = (int) (x + (16 * Math.cos(a2) * 5 / 6) - (6 * Math.cos(a1)) - (8 * Math.cos(aR)));
		xPLTurr[2] = (int) (x + (12 * Math.cos(a3) * 5 / 6) - (6 * Math.cos(a1)) - (8 * Math.cos(aR)));
		xPLTurr[3] = (int) (x + (12 * Math.cos(a4) * 5 / 6) - (6 * Math.cos(a1)) - (8 * Math.cos(aR)));
		xPLTurr[4] = (int) (x + (16 * Math.cos(a5) * 5 / 6) - (6 * Math.cos(a1)) - (8 * Math.cos(aR)));
		int[] yPLTurr = new int[5];
		yPLTurr[0] = (int) (y + (20 * Math.sin(a1) * 5 / 6) - (6 * Math.sin(a1)) - (8 * Math.sin(aR)));
		yPLTurr[1] = (int) (y + (16 * Math.sin(a2) * 5 / 6) - (6 * Math.sin(a1)) - (8 * Math.sin(aR)));
		yPLTurr[2] = (int) (y + (12 * Math.sin(a3) * 5 / 6) - (6 * Math.sin(a1)) - (8 * Math.sin(aR)));
		yPLTurr[3] = (int) (y + (12 * Math.sin(a4) * 5 / 6) - (6 * Math.sin(a1)) - (8 * Math.sin(aR)));
		yPLTurr[4] = (int) (y + (16 * Math.sin(a5) * 5 / 6) - (6 * Math.sin(a1)) - (8 * Math.sin(aR)));
		int[] xPRTurr = new int[5];
		xPRTurr[0] = (int) (x + (20 * Math.cos(a1) * 5 / 6) - (6 * Math.cos(a1)) + (8 * Math.cos(aR)));
		xPRTurr[1] = (int) (x + (16 * Math.cos(a2) * 5 / 6) - (6 * Math.cos(a1)) + (8 * Math.cos(aR)));
		xPRTurr[2] = (int) (x + (12 * Math.cos(a3) * 5 / 6) - (6 * Math.cos(a1)) + (8 * Math.cos(aR)));
		xPRTurr[3] = (int) (x + (12 * Math.cos(a4) * 5 / 6) - (6 * Math.cos(a1)) + (8 * Math.cos(aR)));
		xPRTurr[4] = (int) (x + (16 * Math.cos(a5) * 5 / 6) - (6 * Math.cos(a1)) + (8 * Math.cos(aR)));
		int[] yPRTurr = new int[5];
		yPRTurr[0] = (int) (y + (20 * Math.sin(a1) * 5 / 6) - (6 * Math.sin(a1)) + (8 * Math.sin(aR)));
		yPRTurr[1] = (int) (y + (16 * Math.sin(a2) * 5 / 6) - (6 * Math.sin(a1)) + (8 * Math.sin(aR)));
		yPRTurr[2] = (int) (y + (12 * Math.sin(a3) * 5 / 6) - (6 * Math.sin(a1)) + (8 * Math.sin(aR)));
		yPRTurr[3] = (int) (y + (12 * Math.sin(a4) * 5 / 6) - (6 * Math.sin(a1)) + (8 * Math.sin(aR)));
		yPRTurr[4] = (int) (y + (16 * Math.sin(a5) * 5 / 6) - (6 * Math.sin(a1)) + (8 * Math.sin(aR)));
		p = new Polygon(xpoints, ypoints, 5);
		lWing = new Polygon(xPLWing, yPLWing, 3);
		rWing = new Polygon(xPRWing, yPRWing, 3);
		lTurr = new Polygon(xPLTurr, yPLTurr, 5);
		rTurr = new Polygon(xPRTurr, yPRTurr, 5);
		if(hasLJoust)
		{
			xPLJ = new int[4];
			xPLJ[0] = (int) x + (int) (12 * Math.cos(a4) * 5 / 6) - (int) (12 * Math.cos(a1)) - (int) (15 * Math.cos(aR));
			xPLJ[1] = (int) x + (int) (12 * Math.cos(a4) * 5 / 6) - (int) (12 * Math.cos(a1)) - (int) (10 * Math.cos(aR));
			xPLJ[2] = xPLJ[1] + (int) (Math.min(50, jCount) * Math.cos(a1));
			xPLJ[3] = xPLJ[0] + (int) (Math.min(50, jCount) * Math.cos(a1));
			yPLJ = new int[4];
			yPLJ[0] = (int) y + (int) (12 * Math.sin(a4) * 5 / 6) - (int) (12 * Math.sin(a1)) - (int) (15 * Math.sin(aR));
			yPLJ[1] = (int) y + (int) (12 * Math.sin(a4) * 5 / 6) - (int) (12 * Math.sin(a1)) - (int) (10 * Math.sin(aR));
			yPLJ[2] = yPLJ[1] + (int) (Math.min(50, jCount) * Math.sin(a1));
			yPLJ[3] = yPLJ[0] + (int) (Math.min(50, jCount) * Math.sin(a1));
			lJ = new Polygon(xPLJ, yPLJ, 4);
			
		}
		if(hasRJoust)
		{
			xPRJ = new int[4];
			xPRJ[0] = (int) x + (int) (12 * Math.cos(a3) * 5 / 6) - (int) (12 * Math.cos(a1)) + (int) (15 * Math.cos(aR));
			xPRJ[1] = (int) x + (int) (12 * Math.cos(a3) * 5 / 6) - (int) (12 * Math.cos(a1)) + (int) (10 * Math.cos(aR));
			xPRJ[2] = xPRJ[1] + (int) (Math.min(50, jCount) * Math.cos(a1));
			xPRJ[3] = xPRJ[0] + (int) (Math.min(50, jCount) * Math.cos(a1));
			yPRJ = new int[4];
			yPRJ[0] = (int) y + (int) (12 * Math.sin(a3) * 5 / 6) - (int) (12 * Math.sin(a1)) + (int) (15 * Math.sin(aR));
			yPRJ[1] = (int) y + (int) (12 * Math.sin(a3) * 5 / 6) - (int) (12 * Math.sin(a1)) + (int) (10 * Math.sin(aR));
			yPRJ[2] = yPRJ[1] + (int) (Math.min(50, jCount) * Math.sin(a1));
			yPRJ[3] = yPRJ[0] + (int) (Math.min(50, jCount) * Math.sin(a1));
			rJ = new Polygon(xPRJ, yPRJ, 4);
		}
		if(hasLJoust || hasRJoust)
			jousterHit();
		if(hp == 30 && (shp == 30 || !hasShield))
		{
			hasStack = true;
			if(specials.size() != 5)
				hasStack = false;
			else
			{
				Queue<String> temp = new LinkedList<>();
				while(!specials.isEmpty())
				{
					String a = specials.poll();
					if(!(a.equals("Shield") || a.equals("Medkit")))
						hasStack = false;
					temp.offer(a);
				}
				while(!temp.isEmpty())
					specials.offer(temp.poll());
			}
		}
		else
			hasStack = false;
		angle -= dangle;
		angle %= 360;
		//if(angle < 0) angle += 360;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setEnemy(Player e)
	{
		enemy = e;
	}
	
	public void draw(Graphics2D g, Set<Pickup> pII, boolean maintain)
	{
		if(!maintain)
			tick();
		searchCollisions();
		searchPickups(pII);
		if(hasStack && counterFlash < 4)
			g.setColor(new Color(255, 255, 255));
		else
			g.setColor(new Color(225, 220, 230));
		g.fill(p);
		g.fill(lWing);
		g.fill(rWing);
		if(hasStack && counterFlash < 4)
			g.setColor(new Color(255, 255, 255));
		else
			g.setColor(color);
		g.fill(lTurr);
		g.fill(rTurr);
		g.setColor(JCOLOR);
		if(hasLJoust)
			g.fill(lJ);
		if(hasRJoust)
			g.fill(rJ);
		g.setFont(new Font("SansSerif", Font.ITALIC, 12));
		g.setColor(Color.WHITE);
		g.drawString(name, (int) x - 30, (int) y - 40);
		Rectangle hbar = new Rectangle((int) x - 15, (int) y + 30, getHealth(), 5);
		if(getHealth() <= 10)
			g.setColor(Color.RED);
		else if(getHealth() <= 20)
			g.setColor(Color.YELLOW);
		else
			g.setColor(Color.GREEN);
		g.fill(hbar);
	}
	
	public void drawShield(Graphics2D g)
	{
		if(hasShield)
		{
			if(counter < 3)
			{
				if(shp <= 10)
					g.setColor(LOWSHIELD1);
				else if(shp <= 20)
					g.setColor(MIDSHIELD1);
				else
					g.setColor(FULLSHIELD1);
			}
			else
			{
				if(shp <= 10)
					g.setColor(LOWSHIELD2);
				else if(shp <= 20)
					g.setColor(MIDSHIELD2);
				else
					g.setColor(FULLSHIELD2);
			}
			g.fill(new Ellipse2D.Double(x - 35, y - 30, 70, 70));
		}
	}
	
	public void lReleased()
	{
		dangle = 0;
	}
	
	public void rReleased()
	{
		dangle = 0;
	}
	
	public void lPressed()
	{
		dangle = DANGLE;
	}
	
	public void rPressed()
	{
		dangle = -DANGLE;
	}
	
	public Bullet fire()
	{
		Bullet bullet = new StandardBullet(angle, (int) x, (int) y);
		if(b.size() < MAX_BULLETS) {
			b.add(bullet);
		}
		return bullet;
	}
	
	public String specialFire(GraphicsComponent g)
	{
		if(specials.size() == 0 || b.size() >= MAX_BULLETS)
			return "None";
		
		if(hasStack)
		{
			specials = new LinkedList<>();
			return "UltiAttack";
		}
		
		String action = specials.poll();
		
		if(action.equals("Laser"))
		{
			Bullet bullet = new LaserBullet(angle, (int) x, (int) y);
			b.add(bullet);
			return "Beam";
		}
		if(action.equals("Shield"))
		{
			if(!hasShield || shp < 30)
			{
				hasShield = true;
				shp = 30;
				return "Shield";
			}
			Queue<String> temp = new LinkedList<>();
			temp.offer(action);
			while(!specials.isEmpty() && specials.peek().equals("Shield"))
				temp.offer(specials.poll());
			String s = specialFire(g);
			while(!specials.isEmpty())
				temp.offer(specials.poll());
			while(!temp.isEmpty())
				specials.offer(temp.poll());
			return s;
		}
		if(action.equals("Medkit"))
		{
			if(hp < 30)
			{
				hp = 30;
				return "Heal";
			}
			Queue<String> temp = new LinkedList<>();
			temp.offer(action);
			while(!specials.isEmpty() && specials.peek().equals("Medkit"))
				temp.offer(specials.poll());
			String s = specialFire(g);
			while(!specials.isEmpty())
				temp.offer(specials.poll());
			while(!temp.isEmpty())
				specials.offer(temp.poll());
			return s;
		}
		if(action.equals("Jouster"))
		{
			if(!hasLJoust || !hasRJoust)
			{
				hasLJoust = true;
				hasRJoust = true;
				jCount = 0;
				return "Joust";
			}
			Queue<String> temp = new LinkedList<>();
			temp.offer(action);
			while(!specials.isEmpty() && specials.peek().equals("Joust"))
				temp.offer(specials.poll());
			String s = specialFire(g);
			while(!specials.isEmpty())
				temp.offer(specials.poll());
			while(!temp.isEmpty())
				specials.offer(temp.poll());
			return s;
		}
		if(action.equals("Bomb2"))
		{
			g.addBomb((int) x, (int) y, this);
			Queue<String> temp = new LinkedList<>();
			temp.offer("Bomb1");
			while(!specials.isEmpty())
				temp.offer(specials.poll());
			while(!temp.isEmpty())
				specials.offer(temp.poll());
			return "Bomb";
		}
		if(action.equals("Bomb1"))
		{
			g.addBomb((int) x, (int) y, this);
			return "Bomb";
		}
		return "None";
	}
	
	public Set<Bullet> getBullets()
	{
		return b;
	}
	
	public Queue<String> getSpecials()
	{
		return specials;
	}
	
	public void searchPickups(Set<Pickup> p)
	{
		Iterator<Pickup> pIter = p.iterator();
		while(pIter.hasNext())
		{
			Pickup pI = pIter.next();
			if(hasCollidedP(pI))
			{
				if(specials.size() < MAX_SPECIALS)
				{
					if(pI instanceof LaserPickup)
						specials.offer("Laser");
					else if(pI instanceof ShieldPickup)
						specials.offer("Shield");
					else if(pI instanceof MedkitPickup)
						specials.offer("Medkit");
					else if(pI instanceof BombPickup)
						specials.offer("Bomb2");
					else if(pI instanceof JousterPickup)
						specials.offer("Jouster");
				}
				pIter.remove();
			}
		}
	}
	
	public boolean shieldUse()
	{
		return hasShield;
	}
	
	public void searchCollisions()
	{
		Iterator<Bullet> temp = enemy.b.iterator();
		while(temp.hasNext()) {
			Bullet bs = temp.next();
			if(hasCollided(bs))
			{
				dealDamage(bs.getDamage());
				if(!(bs instanceof LaserBullet))
					temp.remove();
				else
					((LaserBullet) bs).nullify();
			}
		}
	}
	
	public void jousterHit()
	{
		if(hasLJoust)
		{
			if(lJ.intersects(enemy.p.getBounds()))
			{
				hasLJoust = false;
				enemy.dealDamage(15);
			}
		}
		if(hasRJoust)
		{
			if(rJ.intersects(enemy.p.getBounds()))
			{
				hasRJoust = false;
				enemy.dealDamage(15);
			}
		}
	}
	
	public void dealDamage(int dmg)
	{
		if(hasShield)
		{
			shp -= dmg;
			if(shp <= 0)
			{
				hp += shp;
				shp = 0;
				hasShield = false;
			}
		}
		else
			hp -= dmg;
	}
	
	public int getHealth()
	{
		return hp;
	}
	
	public int getShealth()
	{
		return shp;
	}
	
	public boolean isDead()
	{
		return hp <= 0;
	}
	
	public int getX()
	{
		return (int) x;
	}
	
	public int getY()
	{
		return (int) y;
	}
	
	public void adjust(int xMax, int yMax)
	{
		if(x < 0)
			x = xMax;
		else if(x > xMax)
			x = 0;
		
		if(y < 0)
			y = yMax;
		else if(y > yMax)
			y = 0;
	}
	
	public boolean hasCollided(Bullet b) {
		return b.getShape().intersects(p.getBounds());
	}
	
	public boolean hasCollidedP(Pickup b) {
		return b.getShape().intersects(p.getBounds());
	}
	
}