import java.awt.*;
import java.awt.geom.*;

import javax.swing.JComponent;
import java.lang.Math;
import java.util.*;
import java.util.Iterator;
import java.io.*;

import javax.swing.JFrame;

public class GraphicsComponent extends JComponent{

	private static final long serialVersionUID = 0;
	private Player p1;
	private Player p2;
	private Graphics2D g2;
	private JFrame frame;
	private static final int MAX_STARS = 50;
	private Ellipse2D.Double[] stars;
	private int pauseState;
	private Stats sPage;
	private String checkPerson;
	private Set<Effect> effects;
	private Set<Bomb> bombs;
	private Set<UltiAttack> winners;
	
	private static final int B_R = 5;
	private static final int B_G = 0;
	private static final int B_B = 30;
	private static final int S_R = 230;
	private static final int S_G = 200;
	private static final int S_B = 140;
	private static final Color BACK_COLOR = new Color(B_R, B_G, B_B);
	private static final Color STAR_COLOR = new Color(S_R, S_G, S_B);
	private static final Color SHIELD_COLOR = new Color(180, 255, 120, 255);
	private static final Color OUTER_LASER = new Color(0, 120, 255, 150);
	private static final Color INNER_LASER = new Color(255, 255, 255, 255);
	private static final Color GREY_OUT = new Color(0, 0, 0, 120);
	private boolean canAddWin;
	
	private int counter;
	
	private Set<Pickup> pi;
	
	public GraphicsComponent(JFrame frame, String n1, String n2)
	{
		start(frame, n1, n2);
	}
	
	public void paintComponent(Graphics gr)
    {
		if(pauseState == 0)
		{
			counter += 1;
			counter %= 600;
			if(counter == 468 && pi.size() < 5)
			{
				int xAdd = (int) (Math.random() * frame.getWidth());
				int yAdd = (int) (Math.random() * frame.getHeight());
				int rChoice = (int) (Math.random() * 75);
				if(rChoice < 15)
					pi.add(new LaserPickup(xAdd, yAdd));
				else if (rChoice < 30)
					pi.add(new ShieldPickup(xAdd, yAdd));
				else if (rChoice < 45)
					pi.add(new MedkitPickup(xAdd, yAdd));
				else if (rChoice < 60)
					pi.add(new JousterPickup(xAdd, yAdd));
				else
					pi.add(new BombPickup(xAdd, yAdd));
			}
		}
		g2 = (Graphics2D) gr;
    	g2.setColor(BACK_COLOR);
    	g2.fill(new Rectangle(0, 0, getWidth(), getHeight()));
    	if(counter % 8 < 4)
    		g2.setColor(STAR_COLOR);
    	else
    		g2.setColor(Color.WHITE);
    	for(int i = 0; i < stars.length; i++)
    		g2.fill(stars[i]);
    	g2.setColor(Color.WHITE);
		g2.setFont(new Font("SansSerif", Font.ITALIC, 12));
		
		if(pauseState == 0)
			g2.drawString("Press SPACE to pause and view options", getWidth() / 2 - 110, 15);
		
		int pY1 = 0;
		int pY2 = 0;
		drawIcons(g2, p1, 6 + 7, pY1 + 12);
		drawIcons(g2, p2, getWidth() - 106 + 7, pY2 + 12);
		Iterator<UltiAttack> uI = winners.iterator();
		while(uI.hasNext())
		{
			UltiAttack uA = uI.next();
			if(uA.hasContacted(p1))
				p1.dealDamage(60);
			else if(uA.hasContacted(p2))
				p2.dealDamage(60);
			if(!uA.inUse())
				uI.remove();
		}
		for(UltiAttack uA : winners)
			uA.draw(g2, pauseState != 0);
		Iterator<Bomb> bI = bombs.iterator();
		while(bI.hasNext())
		{
			Bomb bN = bI.next();
			if(bN.canRemove())
			{
				bI.remove();
				continue;
			}
			if(bN.canHarm())
			{
				bN.doHarm(p1);
				bN.doHarm(p2);
				continue;
			}
			Iterator<Bullet> b1 = p1.getBullets().iterator();
			Iterator<Bullet> b2 = p2.getBullets().iterator();
			while(b1.hasNext())
			{
				Bullet bX = b1.next();
				if(bN.hasCollidedB(bX))
				{
					bN.start();
					if(bX instanceof StandardBullet)
						b1.remove();
				}
			}
			while(b2.hasNext())
			{
				Bullet bX = b2.next();
				if(bN.hasCollidedB(bX))
				{
					bN.start();
					if(bX instanceof StandardBullet)
						b2.remove();
				}
			}
		}
		for(Bomb x : bombs)
			if(x.hasActivated() && (x.inRange(p1) || x.inRange(p2)))
				x.start();
    	for(Bullet b : p1.getBullets())
    		b.draw(g2, pauseState != 0);
    	for(Bullet b : p2.getBullets())
    		b.draw(g2, pauseState != 0);
    	for(Bomb x : bombs)
    		x.draw(g2, pauseState != 0);
    	for(Pickup ps : pi)
    		ps.draw(g2, pauseState != 0);
    	if(!p1.isDead())
			p1.draw(g2, pi, pauseState != 0);
    	if(!p2.isDead())
    		p2.draw(g2, pi, pauseState != 0);
    	p1.drawShield(g2);
    	p2.drawShield(g2);
    	p1.adjust(getWidth(), getHeight());
    	p2.adjust(getWidth(), getHeight());
    	Iterator<Effect> eI = effects.iterator();
    	while(eI.hasNext())
    	{
    		Effect e = eI.next();
    		if(!e.inUse())
    			eI.remove();
    		else
    			e.draw(g2, pauseState != 0);
    	}
    	if(isGameOver())
    	{
    		g2.setColor(GREY_OUT);
        	g2.fill(new Rectangle(0, 0, getWidth(), getHeight()));
    		g2.setColor(Color.WHITE);
			g2.setFont(new Font("SansSerif", Font.ITALIC, 50));
			g2.drawString(getWinner() + " is the winner!", getWidth() / 4, getHeight() / 2);
			g2.drawString("Press R to restart and create a new game", getWidth() / 2 - 500, getHeight() - 30);
			pauseState = 1;
			if(canAddWin)
			{
				sPage.newPlay(p1.getName(), p2.getName());
				sPage.newPlay(p2.getName(), p1.getName());
				sPage.newKill(getWinner(), getLoser());
				sPage.newDeath(getLoser(), getWinner());
				canAddWin = false;
				try
				{
					sPage.update();
				}
				catch(Exception e)
				{
					System.out.println("BRUH");
				}
			}
			return;
    	}
    	if(pauseState == 1)
    	{
    		g2.setColor(GREY_OUT);
        	g2.fill(new Rectangle(0, 0, getWidth(), getHeight()));
        	g2.setColor(Color.RED);
        	g2.setFont(new Font("Impact", Font.BOLD, 80));
        	g2.drawString("JET ATTACK", getWidth() / 2 - 200, 80);
        	g2.setColor(Color.WHITE);
        	Rectangle lPause;
        	Rectangle rPause;
        	int[] tl = new int[] {getWidth() / 2 - 90, getHeight() / 2 - 100};
        	lPause = new Rectangle(tl[0], tl[1], 60, 200);
        	rPause = new Rectangle(tl[0] + 120, tl[1], 60, 200);
        	g2.fill(lPause);
        	g2.fill(rPause);
        	g2.setFont(new Font("SansSerif", Font.ITALIC, 30));
        	g2.drawString("Press SPACE to unpause and continue the game", getWidth() / 2 - 330, 140);
        	g2.drawString("Press R to restart the current game", getWidth() / 2 - 230, 190);
        	g2.drawString("Press L to view leaderboards and statistics", getWidth() / 2 - 280, getHeight() - 90);
        	g2.drawString("Press U to see the rules and controls", getWidth() / 2 - 240, getHeight() - 30);
    	}
    	else if(pauseState == 2)
    	{
    		g2.setColor(GREY_OUT);
        	g2.fill(new Rectangle(0, 0, getWidth(), getHeight()));
        	g2.setColor(new Color(50, 50, 60));
        	g2.fill(new Rectangle(40, 80, getWidth() - 80, getHeight() - 160));
        	Set<RankedPlayer> rp = new TreeSet<>();
        	for(String play : sPage.getPlayers())
        	{
        		double kdN = sPage.getKD(play);
        		int timP = sPage.numTimesPlayed(play);
        		rp.add(new RankedPlayer(play, kdN, timP));
        	}
        	Map<Integer, RankedPlayer> ranks = new HashMap<>();
        	Map<RankedPlayer, Integer> order = new HashMap<>();
        	int count = 1;
        	for(RankedPlayer rps : rp)
        	{
        		ranks.put(count, rps);
        		order.put(rps, count);
        		count += 1;
        	}
        	g2.setColor(Color.WHITE);
        	g2.setFont(new Font("SansSerif", Font.ITALIC, 30));
        	g2.drawString("Rank 1: " + ranks.get(1), getWidth() / 2 - 130, getHeight() / 2 - 200);
        	g2.drawString("Rank 2: " + ranks.get(2), getWidth() / 2 - 130, getHeight() / 2 - 150);
        	if(ranks.containsKey(3))
        		g2.drawString("Rank 3: " + ranks.get(3), getWidth() / 2 - 130, getHeight() / 2 - 100);
        	if(ranks.containsKey(4))
        		g2.drawString("Rank 4: " + ranks.get(4), getWidth() / 2 - 130, getHeight() / 2 - 50);
        	if(ranks.containsKey(5))
        		g2.drawString("Rank 5: " + ranks.get(5), getWidth() / 2 - 130, getHeight() / 2 );
        	RankedPlayer toCheck = ranks.get(1);
        	for(RankedPlayer rps : rp)
        		if(rps.getName().equals(checkPerson))
        			toCheck = rps;
        	int rank = order.get(toCheck);
        	g2.drawString("Your Player, " + checkPerson + ", is Rank " + rank + ", with the stats " + toCheck, getWidth() / 2 - 550, getHeight() / 2 + 250);
        	g2.drawString("STATS AND LEADERBOARD", getWidth() / 2 - 200, 40);
        	g2.drawString("Press L to exit and return to the pause menu", getWidth() / 2 - 300, getHeight() - 30);
    	}
    	else if(pauseState == 3)
    	{
    		g2.setColor(GREY_OUT);
        	g2.fill(new Rectangle(0, 0, getWidth(), getHeight()));
        	g2.setColor(Color.WHITE);
        	g2.setFont(new Font("SansSerif", Font.ITALIC, 30));
        	g2.drawString("RULES", getWidth() / 2 - 50, 40);
        	g2.drawString("Press U to exit and return to the pause menu", getWidth() / 2 - 300, getHeight() - 30);
        	g2.drawString("PLAYER 1", 70, getHeight() / 2 - 90);
        	g2.drawString("PLAYER 2", getWidth() - 210, getHeight() / 2 - 90);
        	g2.drawString("Shoot down your opponent to win the game!", getWidth() / 2 - 270, 100);
        	g2.drawString("Look out for special items on the board!", getWidth() / 2 - 250, 150);
        	g2.drawString("Keep track of your health, shield, and special items up top!", getWidth() / 2 - 380, 200);
        	g2.drawString("Special items are collected and used in a Queue manner", getWidth() / 2 - 366, 250);
        	
        	g2.drawString("Specials:", getWidth() / 2 - 50, getHeight() - 300);
        	g2.drawString("Laser: A faster and more powerful shot", getWidth() / 2 - 250, getHeight() - 250);
        	g2.drawString("Shield: Restore your shield to take extra hits", getWidth() / 2 - 290, getHeight() - 200);
        	
        	g2.fill(new Rectangle(68, getHeight() / 2 - 20, 44, 40));
        	g2.fill(new Rectangle(118, getHeight() / 2 - 20, 44, 40));
        	g2.fill(new Rectangle(168, getHeight() / 2 - 20, 44, 40));
        	g2.fill(new Rectangle(118, getHeight() / 2 - 70, 44, 40));
        	g2.fill(new Rectangle(getWidth() - 212, getHeight() / 2 - 20, 44, 40));
        	g2.fill(new Rectangle(getWidth() - 162, getHeight() / 2 - 20, 44, 40));
        	g2.fill(new Rectangle(getWidth() - 112, getHeight() / 2 - 20, 44, 40));
        	g2.fill(new Rectangle(getWidth() - 162, getHeight() / 2 - 70, 44, 40));
        	g2.setColor(Color.BLACK);
        	g2.setFont(new Font("SansSerif", Font.BOLD, 8));
        	g2.drawString("W", 137, getHeight() / 2 - 49);
        	g2.drawString("(SHOOT)", 124, getHeight() / 2 - 39);
        	g2.drawString("A", 87, getHeight() / 2 + 1);
        	g2.drawString("(LEFT)", 78, getHeight() / 2 + 11);
        	g2.drawString("S", 137, getHeight() / 2 + 1);
        	g2.drawString("(SPECIAL)", 120, getHeight() / 2 + 11);
        	g2.drawString("D", 187, getHeight() / 2 + 1);
        	g2.drawString("(RIGHT)", 176, getHeight() / 2 + 11);
        	int xs = getWidth() - 140;
        	int ys = getHeight() / 2 - 49;
        	g2.fill(new Polygon(new int[] {xs, xs - 2, xs - 7, xs, xs + 7, xs + 2}, new int[] {ys, ys - 7, ys - 5, ys - 12, ys - 5, ys - 7}, 6));
        	g2.drawString("(SHOOT)", getWidth() - 156, getHeight() / 2 - 39);
        	xs = getWidth() - 184;
        	ys = getHeight() / 2 - 5;
        	g2.fill(new Polygon(new int[] {xs, xs - 7, xs - 5, xs - 12, xs - 5, xs - 7}, new int[] {ys, ys + 2, ys + 5, ys, ys - 5, ys - 2}, 6));
        	g2.drawString("(LEFT)", getWidth() - 202, getHeight() / 2 + 11);
        	xs = getWidth() - 140;
        	ys = getHeight() / 2 - 11;
        	g2.fill(new Polygon(new int[] {xs, xs - 2, xs - 7, xs, xs + 7, xs + 2}, new int[] {ys, ys + 7, ys + 5, ys + 12, ys + 5, ys + 7}, 6));
        	g2.drawString("(SPECIAL)", getWidth() - 160, getHeight() / 2 + 11);
        	xs = getWidth() - 90;
        	ys = getHeight() / 2 - 5;
        	g2.fill(new Polygon(new int[] {xs, xs + 7, xs + 5, xs + 12, xs + 5, xs + 7}, new int[] {ys, ys + 2, ys + 5, ys, ys - 5, ys - 2}, 6));
        	g2.drawString("(RIGHT)", getWidth() - 105, getHeight() / 2 + 11);
    	}
    }
	
	public boolean isGameOver()
	{
		return p1.isDead() || p2.isDead();
	}
	
	public String getWinner()
	{
		return p2.isDead() ? p1.getName() : p1.isDead() ? p2.getName() : "Nobody";
	}
	
	public String getLoser()
	{
		return p2.isDead() ? p2.getName() : p1.isDead() ? p1.getName() : "Nobody";
	}
	
	public void fire(Player p) {
		if(pauseState == 0)
			p.fire();
	}
	
	public void specialFire(Player p) {
		if(pauseState == 0)
		{
			String effect = p.specialFire(this);
			if(effect.equals("UltiAttack"))
				winners.add(new UltiAttack(p));
			if(effect.equals("Shield"))
				effects.add(new ShieldEffect(p));
			if(effect.equals("Heal"))
				effects.add(new HealEffect(p));
		}
	}
	
	public void cleanBullets() {
		Iterator<Bullet> iter1 = p1.getBullets().iterator();
		Iterator<Bullet> iter2 = p2.getBullets().iterator();
		while(iter1.hasNext()) {
			Bullet bullet = iter1.next();
			if(!bullet.inFrame(frame.getWidth(), frame.getHeight())) {
				iter1.remove();
			}
		}
		while(iter2.hasNext()) {
			Bullet bullet = iter2.next();
			if(!bullet.inFrame(frame.getWidth(), frame.getHeight())) {
				iter2.remove();
			}
		}
	}
	
	public Player get1() {
		return p1;
	}
	
	public Player get2() {
		return p2;
	}
	
	public Graphics2D getGraphics() {
		return g2;
	}
	
	public Set<Pickup> pickups() {
		return pi;
	}
	
	public void pauseSwap()
	{
		if(pauseState < 2)
			pauseState = 1 - pauseState;
	}
	
	public void setState(int state)
	{
		pauseState = state;
	}
	
	public void start(JFrame frame, String n1, String n2)
	{
		pi = new HashSet<>();
		bombs = new HashSet<>();
		winners = new HashSet<>();
		canAddWin = true;
		try
		{
			sPage = new Stats("JETATTACK.txt");
		}
		catch(FileNotFoundException e)
		{
			
		}
		effects = new HashSet<Effect>();
		this.frame = frame;
		int xMax = this.frame.getWidth();
		int yMax = this.frame.getHeight();
		p1 = new Player((int) (Math.random() * xMax), (int) (Math.random() * yMax), (int) (Math.random() * 360), null, n1);
		p2 = new Player((int) (Math.random() * xMax), (int) (Math.random() * yMax), (int) (Math.random() * 360), null, n2);
		pauseState = 0;
		
		stars = new Ellipse2D.Double[MAX_STARS];
		for(int i = 0; i < stars.length; i++)
		{
			int w = (int) (Math.random() * (frame.getWidth() - 5));
			int h = (int) (Math.random() * (frame.getHeight() - 5));
			stars[i] = new Ellipse2D.Double(w, h, 5, 5);
		}
		
		p1.setEnemy(p2);
		p2.setEnemy(p1);
		
		counter = 0;
		
		repaint();
	}
	
	public void drawIcons(Graphics2D g, Player p, int x, int y)
	{
		Queue<String> q = p.getSpecials();
		Queue<String> temp = new LinkedList<>();
		while(!q.isEmpty())
		{
			String spec = q.poll();
			if(spec.equals("Shield"))
				drawShieldIcon(g, x, y);
			else if(spec.equals("Laser"))
				drawLaserIcon(g, x, y);
			else if(spec.equals("Medkit"))
				drawMedkitIcon(g, x, y);
			else if(spec.equals("Jouster"))
				drawJousterIcon(g, x, y);
			else if(spec.equals("Bomb2"))
				drawBomb2Icon(g, x, y);
			else if(spec.equals("Bomb1"))
				drawBomb1Icon(g, x, y);
			x += 20;
			temp.offer(spec);
		}
		while(!temp.isEmpty())
			q.offer(temp.poll());
	}
	
	public void drawShieldIcon(Graphics2D g, int x, int y)
	{
		Ellipse2D.Double circ = new Ellipse2D.Double(x - 7, y - 7, 14, 14);
		g.setColor(SHIELD_COLOR);
		g.fill(circ);
	}
	
	public void drawLaserIcon(Graphics2D g, int x, int y)
	{
		int[] xpoints = new int[6];
		int[] ypoints = new int[6];
		int[] xpoints2 = new int[4];
		int[] ypoints2 = new int[4];
		
		xpoints[0] = x - 7;
		ypoints[0] = y + 7;
		xpoints[1] = x;
		ypoints[1] = y - 2;
		xpoints[2] = x - 4;
		ypoints[2] = y - 2;
		xpoints[3] = x + 7;
		ypoints[3] = y - 7;
		xpoints[4] = x + 2;
		ypoints[4] = y + 4;
		xpoints[5] = x + 2;
		ypoints[5] = y;
		
		xpoints2[0] = x - 7;
		ypoints2[0] = y + 7;
		xpoints2[1] = x + 2;
		ypoints2[1] = y - 4;
		xpoints2[2] = x + 4;
		ypoints2[2] = y - 4;
		xpoints2[3] = x + 4;
		ypoints2[3] = y - 2;
		
		Polygon p = new Polygon(xpoints, ypoints, 6);
		Polygon p2 = new Polygon(xpoints2, ypoints2, 4);
		
		g.setColor(INNER_LASER);
		g.fill(p2);
		g.setColor(OUTER_LASER);
		g.fill(p);
	}
	
	public void drawMedkitIcon(Graphics2D g, int x, int y)
	{
		g.setColor(Color.WHITE);
		g.fill(new Rectangle(x - 7, y - 7, 14, 14));
		g.setColor(Color.RED);
		g.fill(new Rectangle(x - 5, y - 2, 10, 4));
		g.fill(new Rectangle(x - 2, y - 5, 4, 10));
	}
	
	public void drawJousterIcon(Graphics2D g, int x, int y)
	{
		g.setColor(new Color(0, 150, 50));
		int angle = 45;
		double A1 = (angle + 10) * Math.PI / 180;
		double A2 = (angle + 170) * Math.PI / 180;
		double A3 = (angle + 190) * Math.PI / 180;
		double A4 = (angle + 350) * Math.PI / 180;
		double aR = (angle + 270) * Math.PI / 180;
		int[] xpoints = new int[4]; 
		xpoints[0] = x + (int) (8 * Math.cos(A1)) + (int) (4 * Math.cos(aR));
		xpoints[1] = x + (int) (8 * Math.cos(A2)) + (int) (4 * Math.cos(aR));
		xpoints[2] = x + (int) (8 * Math.cos(A3)) + (int) (4 * Math.cos(aR));
		xpoints[3] = x + (int) (8 * Math.cos(A4)) + (int) (4 * Math.cos(aR));
		int[] ypoints = new int[4]; 
		ypoints[0] = y - (int) (8 * Math.sin(A1)) - (int) (4 * Math.sin(aR));
		ypoints[1] = y - (int) (8 * Math.sin(A2)) - (int) (4 * Math.sin(aR));
		ypoints[2] = y - (int) (8 * Math.sin(A3)) - (int) (4 * Math.sin(aR));
		ypoints[3] = y - (int) (8 * Math.sin(A4)) - (int) (4 * Math.sin(aR));
		int[] xpoints2 = new int[4]; 
		xpoints2[0] = x + (int) (8 * Math.cos(A1)) - (int) (4 * Math.cos(aR));
		xpoints2[1] = x + (int) (8 * Math.cos(A2)) - (int) (4 * Math.cos(aR));
		xpoints2[2] = x + (int) (8 * Math.cos(A3)) - (int) (4 * Math.cos(aR));
		xpoints2[3] = x + (int) (8 * Math.cos(A4)) - (int) (4 * Math.cos(aR));
		int[] ypoints2 = new int[4]; 
		ypoints2[0] = y - (int) (8 * Math.sin(A1)) + (int) (4 * Math.sin(aR));
		ypoints2[1] = y - (int) (8 * Math.sin(A2)) + (int) (4 * Math.sin(aR));
		ypoints2[2] = y - (int) (8 * Math.sin(A3)) + (int) (4 * Math.sin(aR));
		ypoints2[3] = y - (int) (8 * Math.sin(A4)) + (int) (4 * Math.sin(aR));
		Polygon pJL = new Polygon(xpoints, ypoints, 4);
		Polygon pJR = new Polygon(xpoints2, ypoints2, 4);
		g.fill(pJL);
		g.fill(pJR);
	}
	
	public void drawBomb2Icon(Graphics2D g, int x, int y)
	{
		g.setColor(new Color(150, 150, 150));
		g.fill(new Rectangle(x - 7, y - 7, 14, 14));
		g.setColor(new Color(60, 60, 60));
		g.fill(new Rectangle(x - 5, y - 5, 10, 10));
		g.setColor(new Color(71, 20, 0));
		g.fill(new Rectangle(x - 2, y - 2, 4, 4));
		g.setColor(Color.WHITE);
    	g.setFont(new Font("SansSerif", Font.BOLD, 12));
    	g.drawString("2", x + 5, y + 10);
	}
	
	public void drawBomb1Icon(Graphics2D g, int x, int y)
	{
		g.setColor(new Color(150, 150, 150));
		g.fill(new Rectangle(x - 7, y - 7, 14, 14));
		g.setColor(new Color(60, 60, 60));
		g.fill(new Rectangle(x - 5, y - 5, 10, 10));
		g.setColor(new Color(71, 20, 0));
		g.fill(new Rectangle(x - 2, y - 2, 4, 4));
		g.setColor(Color.WHITE);
    	g.setFont(new Font("SansSerif", Font.BOLD, 12));
    	g.drawString("1", x + 5, y + 10);
	}
	
	public boolean hasRecords()
	{
		return sPage.hasPlays();
	}
	
	public Stats getRecords()
	{
		return sPage;
	}
	
	public void addBomb(int x, int y, Player p)
	{
		bombs.add(new Bomb(x, y, p));
	}
	
	public void updateCurrentChecker(String newCheck)
	{
		checkPerson = newCheck;
	}
	
	public boolean isPaused()
	{
		return pauseState != 0;
	}
	
	public void advance()
	{
		cleanBullets();
		repaint();
	}
}