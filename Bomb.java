import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.*;
import java.util.*;

public class Bomb{
	
	private int counter;
	private int angle;
	private int spinner;
	private int explC;
	private Player spawner;
	private int x;
	private int y;
	private boolean hasActivated;
	private boolean finished;
	private boolean started;
	private boolean canRemove;
	int[] xpoints;
	int[] ypoints;
	int[] xpoints2;
	int[] ypoints2;
	int[] xpoints3;
	int[] ypoints3;
	int[] xpointsTL;
	int[] ypointsTL;
	int[] xpointsT;
	int[] ypointsT;
	int[] xpointsTR;
	int[] ypointsTR;
	int[] xpointsR;
	int[] ypointsR;
	int[] xpointsBR;
	int[] ypointsBR;
	int[] xpointsB;
	int[] ypointsB;
	int[] xpointsBL;
	int[] ypointsBL;
	int[] xpointsL;
	int[] ypointsL;
	Polygon p2;
	Polygon p3;
	Polygon pL;
	Polygon pR;
	Polygon pTL;
	Polygon pTR;
	Polygon pBL;
	Polygon pBR;
	Polygon pT;
	Polygon pB;
	private Set<Player> cantHarm;
	
	public Bomb(int x, int y, Player spawn)
	{
		spawner = spawn;
		this.x = x;
		this.y = y;
		angle = 0;
		counter = 0;
		explC = 0;
		cantHarm = new HashSet<>();
		
		double A1 = (angle + 45) * Math.PI / 180;
		double A2 = (angle + 135) * Math.PI / 180;
		double A3 = (angle + 225) * Math.PI / 180;
		double A4 = (angle + 315) * Math.PI / 180;
		double A5 = (angle + 90) * Math.PI / 180;
		double A8 = (angle + 360) * Math.PI / 180;
		
		double A9 = (angle + 10) * Math.PI / 180;
		double A10 = (angle + 170) * Math.PI / 180;
		double A11 = (angle + 190) * Math.PI / 180;
		double A12 = (angle + 350) * Math.PI / 180;
		double A13 = (angle + 55) * Math.PI / 180;
		double A14 = (angle + 215) * Math.PI / 180;
		double A15 = (angle + 235) * Math.PI / 180;
		double A16 = (angle + 395) * Math.PI / 180;
		double A17 = (angle + 100) * Math.PI / 180;
		double A18 = (angle + 260) * Math.PI / 180;
		double A19 = (angle + 280) * Math.PI / 180;
		double A20 = (angle + 440) * Math.PI / 180;
		double A21 = (angle + 145) * Math.PI / 180;
		double A22 = (angle + 305) * Math.PI / 180;
		double A23 = (angle + 325) * Math.PI / 180;
		double A24 = (angle + 485) * Math.PI / 180;
		
		xpoints2 = new int[4]; 
		xpoints2[0] = x + (int) (9 * Math.cos(A1));
		xpoints2[1] = x + (int) (9 * Math.cos(A2));
		xpoints2[2] = x + (int) (9 * Math.cos(A3));
		xpoints2[3] = x + (int) (9 * Math.cos(A4));
		ypoints2 = new int[4]; 
		ypoints2[0] = y + (int) (9 * Math.sin(A1));
		ypoints2[1] = y + (int) (9 * Math.sin(A2));
		ypoints2[2] = y + (int) (9 * Math.sin(A3));
		ypoints2[3] = y + (int) (9 * Math.sin(A4));
		xpoints3 = new int[4]; 
		xpoints3[0] = x + (int) (4 * Math.cos(A1));
		xpoints3[1] = x + (int) (4 * Math.cos(A2));
		xpoints3[2] = x + (int) (4 * Math.cos(A3));
		xpoints3[3] = x + (int) (4 * Math.cos(A4));
		ypoints3 = new int[4]; 
		ypoints3[0] = y + (int) (4 * Math.sin(A1));
		ypoints3[1] = y + (int) (4 * Math.sin(A2));
		ypoints3[2] = y + (int) (4 * Math.sin(A3));
		ypoints3[3] = y + (int) (4 * Math.sin(A4));
		
		xpointsR = new int[4]; 
		xpointsR[0] = x + (int) (6 * Math.cos(A9)) + (int) (10 * Math.cos(A8));
		xpointsR[1] = x + (int) (6 * Math.cos(A10)) + (int) (10 * Math.cos(A8));
		xpointsR[2] = x + (int) (6 * Math.cos(A11)) + (int) (10 * Math.cos(A8));
		xpointsR[3] = x + (int) (6 * Math.cos(A12)) + (int) (10 * Math.cos(A8));
		ypointsR = new int[4]; 
		ypointsR[0] = y + (int) (6 * Math.sin(A9)) + (int) (10 * Math.sin(A8));
		ypointsR[1] = y + (int) (6 * Math.sin(A10)) + (int) (10 * Math.sin(A8));
		ypointsR[2] = y + (int) (6 * Math.sin(A11)) + (int) (10 * Math.sin(A8));
		ypointsR[3] = y + (int) (6 * Math.sin(A12)) + (int) (10 * Math.sin(A8));
		
		xpointsL = new int[4]; 
		xpointsL[0] = x + (int) (6 * Math.cos(A9)) - (int) (10 * Math.cos(A8));
		xpointsL[1] = x + (int) (6 * Math.cos(A10)) - (int) (10 * Math.cos(A8));
		xpointsL[2] = x + (int) (6 * Math.cos(A11)) - (int) (10 * Math.cos(A8));
		xpointsL[3] = x + (int) (6 * Math.cos(A12)) - (int) (10 * Math.cos(A8));
		ypointsL = new int[4]; 
		ypointsL[0] = y + (int) (6 * Math.sin(A9)) - (int) (10 * Math.sin(A8));
		ypointsL[1] = y + (int) (6 * Math.sin(A10)) - (int) (10 * Math.sin(A8));
		ypointsL[2] = y + (int) (6 * Math.sin(A11)) - (int) (10 * Math.sin(A8));
		ypointsL[3] = y + (int) (6 * Math.sin(A12)) - (int) (10 * Math.sin(A8));
		
		xpointsTR = new int[4]; 
		xpointsTR[0] = x + (int) (6 * Math.cos(A13)) + (int) (10 * Math.cos(A1));
		xpointsTR[1] = x + (int) (6 * Math.cos(A14)) + (int) (10 * Math.cos(A1));
		xpointsTR[2] = x + (int) (6 * Math.cos(A15)) + (int) (10 * Math.cos(A1));
		xpointsTR[3] = x + (int) (6 * Math.cos(A16)) + (int) (10 * Math.cos(A1));
		ypointsTR = new int[4]; 
		ypointsTR[0] = y + (int) (6 * Math.sin(A13)) + (int) (10 * Math.sin(A1));
		ypointsTR[1] = y + (int) (6 * Math.sin(A14)) + (int) (10 * Math.sin(A1));
		ypointsTR[2] = y + (int) (6 * Math.sin(A15)) + (int) (10 * Math.sin(A1));
		ypointsTR[3] = y + (int) (6 * Math.sin(A16)) + (int) (10 * Math.sin(A1));
		
		xpointsBL = new int[4]; 
		xpointsBL[0] = x + (int) (6 * Math.cos(A13)) - (int) (10 * Math.cos(A1));
		xpointsBL[1] = x + (int) (6 * Math.cos(A14)) - (int) (10 * Math.cos(A1));
		xpointsBL[2] = x + (int) (6 * Math.cos(A15)) - (int) (10 * Math.cos(A1));
		xpointsBL[3] = x + (int) (6 * Math.cos(A16)) - (int) (10 * Math.cos(A1));
		ypointsBL = new int[4]; 
		ypointsBL[0] = y + (int) (6 * Math.sin(A13)) - (int) (10 * Math.sin(A1));
		ypointsBL[1] = y + (int) (6 * Math.sin(A14)) - (int) (10 * Math.sin(A1));
		ypointsBL[2] = y + (int) (6 * Math.sin(A15)) - (int) (10 * Math.sin(A1));
		ypointsBL[3] = y + (int) (6 * Math.sin(A16)) - (int) (10 * Math.sin(A1));
		
		xpointsT = new int[4]; 
		xpointsT[0] = x + (int) (6 * Math.cos(A17)) + (int) (10 * Math.cos(A5));
		xpointsT[1] = x + (int) (6 * Math.cos(A18)) + (int) (10 * Math.cos(A5));
		xpointsT[2] = x + (int) (6 * Math.cos(A19)) + (int) (10 * Math.cos(A5));
		xpointsT[3] = x + (int) (6 * Math.cos(A20)) + (int) (10 * Math.cos(A5));
		ypointsT = new int[4]; 
		ypointsT[0] = y + (int) (6 * Math.sin(A17)) + (int) (10 * Math.sin(A5));
		ypointsT[1] = y + (int) (6 * Math.sin(A18)) + (int) (10 * Math.sin(A5));
		ypointsT[2] = y + (int) (6 * Math.sin(A19)) + (int) (10 * Math.sin(A5));
		ypointsT[3] = y + (int) (6 * Math.sin(A20)) + (int) (10 * Math.sin(A5));
		
		xpointsB = new int[4]; 
		xpointsB[0] = x + (int) (6 * Math.cos(A17)) - (int) (10 * Math.cos(A5));
		xpointsB[1] = x + (int) (6 * Math.cos(A18)) - (int) (10 * Math.cos(A5));
		xpointsB[2] = x + (int) (6 * Math.cos(A19)) - (int) (10 * Math.cos(A5));
		xpointsB[3] = x + (int) (6 * Math.cos(A20)) - (int) (10 * Math.cos(A5));
		ypointsB = new int[4]; 
		ypointsB[0] = y + (int) (6 * Math.sin(A17)) - (int) (10 * Math.sin(A5));
		ypointsB[1] = y + (int) (6 * Math.sin(A18)) - (int) (10 * Math.sin(A5));
		ypointsB[2] = y + (int) (6 * Math.sin(A19)) - (int) (10 * Math.sin(A5));
		ypointsB[3] = y + (int) (6 * Math.sin(A20)) - (int) (10 * Math.sin(A5));
		
		xpointsTL = new int[4]; 
		xpointsTL[0] = x + (int) (6 * Math.cos(A21)) + (int) (10 * Math.cos(A2));
		xpointsTL[1] = x + (int) (6 * Math.cos(A22)) + (int) (10 * Math.cos(A2));
		xpointsTL[2] = x + (int) (6 * Math.cos(A23)) + (int) (10 * Math.cos(A2));
		xpointsTL[3] = x + (int) (6 * Math.cos(A24)) + (int) (10 * Math.cos(A2));
		ypointsTL = new int[4]; 
		ypointsTL[0] = y + (int) (6 * Math.sin(A21)) + (int) (10 * Math.sin(A2));
		ypointsTL[1] = y + (int) (6 * Math.sin(A22)) + (int) (10 * Math.sin(A2));
		ypointsTL[2] = y + (int) (6 * Math.sin(A23)) + (int) (10 * Math.sin(A2));
		ypointsTL[3] = y + (int) (6 * Math.sin(A24)) + (int) (10 * Math.sin(A2));
		
		xpointsBR = new int[4]; 
		xpointsBR[0] = x + (int) (6 * Math.cos(A21)) - (int) (10 * Math.cos(A2));
		xpointsBR[1] = x + (int) (6 * Math.cos(A22)) - (int) (10 * Math.cos(A2));
		xpointsBR[2] = x + (int) (6 * Math.cos(A23)) - (int) (10 * Math.cos(A2));
		xpointsBR[3] = x + (int) (6 * Math.cos(A24)) - (int) (10 * Math.cos(A2));
		ypointsBR = new int[4]; 
		ypointsBR[0] = y + (int) (6 * Math.sin(A21)) - (int) (10 * Math.sin(A2));
		ypointsBR[1] = y + (int) (6 * Math.sin(A22)) - (int) (10 * Math.sin(A2));
		ypointsBR[2] = y + (int) (6 * Math.sin(A23)) - (int) (10 * Math.sin(A2));
		ypointsBR[3] = y + (int) (6 * Math.sin(A24)) - (int) (10 * Math.sin(A2));
	
		p2 = new Polygon(xpoints2, ypoints2, 4);
		p3 = new Polygon(xpoints3, ypoints3, 4);
		pL = new Polygon(xpointsL, ypointsL, 4);
		pR = new Polygon(xpointsR, ypointsR, 4);
		pBL = new Polygon(xpointsBL, ypointsBL, 4);
		pTR = new Polygon(xpointsTR, ypointsTR, 4);
		pBR = new Polygon(xpointsBR, ypointsBR, 4);
		pTL = new Polygon(xpointsTL, ypointsTL, 4);
		pB = new Polygon(xpointsB, ypointsB, 4);
		pT = new Polygon(xpointsT, ypointsT, 4);
	}
	
	public void draw(Graphics2D gr, boolean maintain)
	{
		if(!maintain)
			tick();
		if(!finished)
		{
			gr.setColor(new Color(60, 60, 60));
			gr.fill(p2);
			if(!hasActivated)
				gr.setColor(new Color(142, 40, 0));
			else
				gr.setColor(new Color(255, 100, 0));
			gr.fill(p3);
			gr.setColor(Color.WHITE);
	
			gr.fill(pBL);
			gr.fill(pTR);
			gr.fill(pBR);
			gr.fill(pTL);
		}
		else
		{
			gr.setColor(new Color(255, 187 - 11 * explC, 0, 255 - 15 * explC));
			gr.fill(new Ellipse2D.Double(x - 17 * explC, y - 17 * explC, 34 * explC, 34 * explC));
		}
	}
	
	public void tick()
	{
		counter += 1;
		if(counter > 20)
			hasActivated = true;
		if(started && !finished)
			spinner += 1;
		if(spinner >= 40)
			finished = true;
		if(finished)
			explC += 1;
		if(explC >= 17)
			canRemove = true;
		angle = (int) (Math.pow(spinner, 2) / 2);
		
		double A1 = (angle + 45) * Math.PI / 180;
		double A2 = (angle + 135) * Math.PI / 180;
		double A3 = (angle + 225) * Math.PI / 180;
		double A4 = (angle + 315) * Math.PI / 180;
		double A5 = (angle + 90) * Math.PI / 180;
		double A8 = (angle + 360) * Math.PI / 180;
		
		double A9 = (angle + 10) * Math.PI / 180;
		double A10 = (angle + 170) * Math.PI / 180;
		double A11 = (angle + 190) * Math.PI / 180;
		double A12 = (angle + 350) * Math.PI / 180;
		double A13 = (angle + 55) * Math.PI / 180;
		double A14 = (angle + 215) * Math.PI / 180;
		double A15 = (angle + 235) * Math.PI / 180;
		double A16 = (angle + 395) * Math.PI / 180;
		double A17 = (angle + 100) * Math.PI / 180;
		double A18 = (angle + 260) * Math.PI / 180;
		double A19 = (angle + 280) * Math.PI / 180;
		double A20 = (angle + 440) * Math.PI / 180;
		double A21 = (angle + 145) * Math.PI / 180;
		double A22 = (angle + 305) * Math.PI / 180;
		double A23 = (angle + 325) * Math.PI / 180;
		double A24 = (angle + 485) * Math.PI / 180;
		
		xpoints2 = new int[4]; 
		xpoints2[0] = x + (int) (9 * Math.cos(A1));
		xpoints2[1] = x + (int) (9 * Math.cos(A2));
		xpoints2[2] = x + (int) (9 * Math.cos(A3));
		xpoints2[3] = x + (int) (9 * Math.cos(A4));
		ypoints2 = new int[4]; 
		ypoints2[0] = y + (int) (9 * Math.sin(A1));
		ypoints2[1] = y + (int) (9 * Math.sin(A2));
		ypoints2[2] = y + (int) (9 * Math.sin(A3));
		ypoints2[3] = y + (int) (9 * Math.sin(A4));
		xpoints3 = new int[4]; 
		xpoints3[0] = x + (int) (4 * Math.cos(A1));
		xpoints3[1] = x + (int) (4 * Math.cos(A2));
		xpoints3[2] = x + (int) (4 * Math.cos(A3));
		xpoints3[3] = x + (int) (4 * Math.cos(A4));
		ypoints3 = new int[4]; 
		ypoints3[0] = y + (int) (4 * Math.sin(A1));
		ypoints3[1] = y + (int) (4 * Math.sin(A2));
		ypoints3[2] = y + (int) (4 * Math.sin(A3));
		ypoints3[3] = y + (int) (4 * Math.sin(A4));
		
		xpointsR = new int[4]; 
		xpointsR[0] = x + (int) (6 * Math.cos(A9)) + (int) (10 * Math.cos(A8));
		xpointsR[1] = x + (int) (6 * Math.cos(A10)) + (int) (10 * Math.cos(A8));
		xpointsR[2] = x + (int) (6 * Math.cos(A11)) + (int) (10 * Math.cos(A8));
		xpointsR[3] = x + (int) (6 * Math.cos(A12)) + (int) (10 * Math.cos(A8));
		ypointsR = new int[4]; 
		ypointsR[0] = y + (int) (6 * Math.sin(A9)) + (int) (10 * Math.sin(A8));
		ypointsR[1] = y + (int) (6 * Math.sin(A10)) + (int) (10 * Math.sin(A8));
		ypointsR[2] = y + (int) (6 * Math.sin(A11)) + (int) (10 * Math.sin(A8));
		ypointsR[3] = y + (int) (6 * Math.sin(A12)) + (int) (10 * Math.sin(A8));
		
		xpointsL = new int[4]; 
		xpointsL[0] = x + (int) (6 * Math.cos(A9)) - (int) (10 * Math.cos(A8));
		xpointsL[1] = x + (int) (6 * Math.cos(A10)) - (int) (10 * Math.cos(A8));
		xpointsL[2] = x + (int) (6 * Math.cos(A11)) - (int) (10 * Math.cos(A8));
		xpointsL[3] = x + (int) (6 * Math.cos(A12)) - (int) (10 * Math.cos(A8));
		ypointsL = new int[4]; 
		ypointsL[0] = y + (int) (6 * Math.sin(A9)) - (int) (10 * Math.sin(A8));
		ypointsL[1] = y + (int) (6 * Math.sin(A10)) - (int) (10 * Math.sin(A8));
		ypointsL[2] = y + (int) (6 * Math.sin(A11)) - (int) (10 * Math.sin(A8));
		ypointsL[3] = y + (int) (6 * Math.sin(A12)) - (int) (10 * Math.sin(A8));
		
		xpointsTR = new int[4]; 
		xpointsTR[0] = x + (int) (6 * Math.cos(A13)) + (int) (10 * Math.cos(A1));
		xpointsTR[1] = x + (int) (6 * Math.cos(A14)) + (int) (10 * Math.cos(A1));
		xpointsTR[2] = x + (int) (6 * Math.cos(A15)) + (int) (10 * Math.cos(A1));
		xpointsTR[3] = x + (int) (6 * Math.cos(A16)) + (int) (10 * Math.cos(A1));
		ypointsTR = new int[4]; 
		ypointsTR[0] = y + (int) (6 * Math.sin(A13)) + (int) (10 * Math.sin(A1));
		ypointsTR[1] = y + (int) (6 * Math.sin(A14)) + (int) (10 * Math.sin(A1));
		ypointsTR[2] = y + (int) (6 * Math.sin(A15)) + (int) (10 * Math.sin(A1));
		ypointsTR[3] = y + (int) (6 * Math.sin(A16)) + (int) (10 * Math.sin(A1));
		
		xpointsBL = new int[4]; 
		xpointsBL[0] = x + (int) (6 * Math.cos(A13)) - (int) (10 * Math.cos(A1));
		xpointsBL[1] = x + (int) (6 * Math.cos(A14)) - (int) (10 * Math.cos(A1));
		xpointsBL[2] = x + (int) (6 * Math.cos(A15)) - (int) (10 * Math.cos(A1));
		xpointsBL[3] = x + (int) (6 * Math.cos(A16)) - (int) (10 * Math.cos(A1));
		ypointsBL = new int[4]; 
		ypointsBL[0] = y + (int) (6 * Math.sin(A13)) - (int) (10 * Math.sin(A1));
		ypointsBL[1] = y + (int) (6 * Math.sin(A14)) - (int) (10 * Math.sin(A1));
		ypointsBL[2] = y + (int) (6 * Math.sin(A15)) - (int) (10 * Math.sin(A1));
		ypointsBL[3] = y + (int) (6 * Math.sin(A16)) - (int) (10 * Math.sin(A1));
		
		xpointsT = new int[4]; 
		xpointsT[0] = x + (int) (6 * Math.cos(A17)) + (int) (10 * Math.cos(A5));
		xpointsT[1] = x + (int) (6 * Math.cos(A18)) + (int) (10 * Math.cos(A5));
		xpointsT[2] = x + (int) (6 * Math.cos(A19)) + (int) (10 * Math.cos(A5));
		xpointsT[3] = x + (int) (6 * Math.cos(A20)) + (int) (10 * Math.cos(A5));
		ypointsT = new int[4]; 
		ypointsT[0] = y + (int) (6 * Math.sin(A17)) + (int) (10 * Math.sin(A5));
		ypointsT[1] = y + (int) (6 * Math.sin(A18)) + (int) (10 * Math.sin(A5));
		ypointsT[2] = y + (int) (6 * Math.sin(A19)) + (int) (10 * Math.sin(A5));
		ypointsT[3] = y + (int) (6 * Math.sin(A20)) + (int) (10 * Math.sin(A5));
		
		xpointsB = new int[4]; 
		xpointsB[0] = x + (int) (6 * Math.cos(A17)) - (int) (10 * Math.cos(A5));
		xpointsB[1] = x + (int) (6 * Math.cos(A18)) - (int) (10 * Math.cos(A5));
		xpointsB[2] = x + (int) (6 * Math.cos(A19)) - (int) (10 * Math.cos(A5));
		xpointsB[3] = x + (int) (6 * Math.cos(A20)) - (int) (10 * Math.cos(A5));
		ypointsB = new int[4]; 
		ypointsB[0] = y + (int) (6 * Math.sin(A17)) - (int) (10 * Math.sin(A5));
		ypointsB[1] = y + (int) (6 * Math.sin(A18)) - (int) (10 * Math.sin(A5));
		ypointsB[2] = y + (int) (6 * Math.sin(A19)) - (int) (10 * Math.sin(A5));
		ypointsB[3] = y + (int) (6 * Math.sin(A20)) - (int) (10 * Math.sin(A5));
		
		xpointsTL = new int[4]; 
		xpointsTL[0] = x + (int) (6 * Math.cos(A21)) + (int) (10 * Math.cos(A2));
		xpointsTL[1] = x + (int) (6 * Math.cos(A22)) + (int) (10 * Math.cos(A2));
		xpointsTL[2] = x + (int) (6 * Math.cos(A23)) + (int) (10 * Math.cos(A2));
		xpointsTL[3] = x + (int) (6 * Math.cos(A24)) + (int) (10 * Math.cos(A2));
		ypointsTL = new int[4]; 
		ypointsTL[0] = y + (int) (6 * Math.sin(A21)) + (int) (10 * Math.sin(A2));
		ypointsTL[1] = y + (int) (6 * Math.sin(A22)) + (int) (10 * Math.sin(A2));
		ypointsTL[2] = y + (int) (6 * Math.sin(A23)) + (int) (10 * Math.sin(A2));
		ypointsTL[3] = y + (int) (6 * Math.sin(A24)) + (int) (10 * Math.sin(A2));
		
		xpointsBR = new int[4]; 
		xpointsBR[0] = x + (int) (6 * Math.cos(A21)) - (int) (10 * Math.cos(A2));
		xpointsBR[1] = x + (int) (6 * Math.cos(A22)) - (int) (10 * Math.cos(A2));
		xpointsBR[2] = x + (int) (6 * Math.cos(A23)) - (int) (10 * Math.cos(A2));
		xpointsBR[3] = x + (int) (6 * Math.cos(A24)) - (int) (10 * Math.cos(A2));
		ypointsBR = new int[4]; 
		ypointsBR[0] = y + (int) (6 * Math.sin(A21)) - (int) (10 * Math.sin(A2));
		ypointsBR[1] = y + (int) (6 * Math.sin(A22)) - (int) (10 * Math.sin(A2));
		ypointsBR[2] = y + (int) (6 * Math.sin(A23)) - (int) (10 * Math.sin(A2));
		ypointsBR[3] = y + (int) (6 * Math.sin(A24)) - (int) (10 * Math.sin(A2));
	
		p2 = new Polygon(xpoints2, ypoints2, 4);
		p3 = new Polygon(xpoints3, ypoints3, 4);
		pL = new Polygon(xpointsL, ypointsL, 4);
		pR = new Polygon(xpointsR, ypointsR, 4);
		pBL = new Polygon(xpointsBL, ypointsBL, 4);
		pTR = new Polygon(xpointsTR, ypointsTR, 4);
		pBR = new Polygon(xpointsBR, ypointsBR, 4);
		pTL = new Polygon(xpointsTL, ypointsTL, 4);
		pB = new Polygon(xpointsB, ypointsB, 4);
		pT = new Polygon(xpointsT, ypointsT, 4);
	}
	   
	public boolean inFrame(int width, int height) {
		return (x > 0 && x < width && y > 0 && y < height);
	}
	
	public void start()
	{
		started = true;
	}
	
	public boolean hasActivated()
	{
		return hasActivated;
	}
	
	public boolean canRemove()
	{
		return canRemove;
	}
	
	public boolean canHarm()
	{
		return finished;
	}
	
	public void doHarm(Player p)
	{
		double dist = Math.pow(p.getX() - x, 2) + Math.pow(p.getY() - y, 2);
		boolean iR = dist < Math.pow(explC * 17, 2);
		if(!cantHarm.contains(p))
		{
			if(iR)
			{
				cantHarm.add(p);
				if(explC < 6)
					p.dealDamage(40);
				else if(explC < 11)
					p.dealDamage(20);
				else
					p.dealDamage(10);
			}
		}
	}
	
	public boolean inRange(Player p)
	{
		if(p.equals(spawner))
			return false;
		double dist = Math.pow(p.getX() - x, 2) + Math.pow(p.getY() - y, 2);
		boolean iR = dist < 10000;
		return iR;
	}
	
	public boolean hasCollidedB(Bullet b)
	{
		return b.getShape().intersects(p2.getBounds());
	}
	
	public Polygon getShape()
	{
    	return p2;
	}
	
}
