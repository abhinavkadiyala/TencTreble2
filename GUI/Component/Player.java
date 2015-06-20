package component;


import java.awt.Color;
import java.awt.geom.*;


/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
	Tank tank;      //TODO: create a tank somewhere
	String name;
	int score = 0;
	int fb, bb, lb, rb, sb;         //ForwardButton, BackButton, LeftButton, RightButton, ShootButton
	private static final double fv, bv, ts;    //ForwardVel, BackVel, TurnSpd
	static {
		fv = 1.8; bv = 1.2; ts = Math.PI/32;
	}
	
	public int getScore(){
		return score;
	}
	public String getName() {
		return name;
	}
	
	public void incrementScore(){
		score++;
	}

	public boolean keyPressed(int key) {
		if (key == fb)
			tank.move(fv);
		else if (key == bb)
			tank.move(-bv);
		else if (key == lb)
			tank.turn(-ts);
		else if (key == rb)
			tank.turn(ts);
		else return false;
		return true;
	}
	public boolean keyReleased(int key) {
		if (key == fb)
			tank.move(-fv);
		else if (key == bb)
			tank.move(bv);
		else if (key == lb)
			tank.turn(ts);
		else if (key == rb)
			tank.turn(-ts);
		else if (key == sb)
			tank.fire(getBullet());
		else return false;
		return true;
	}
	public Bullet getBullet() {
		Point2D.Double loc = this.getTank().getLocation();
		double dir = this.getTank().getDirection();
		Point2D.Double newLoc = new Point2D.Double(loc.x + 20 * Math.cos(dir), loc.y + 20 * Math.sin(dir));
		Bullet hej = new KillBullet(newLoc ,dir, null, this);
		return hej;    // TODO: make this actually create+return a bullet
	}
	public Tank getTank() {
		return tank;
	}
	public Tank makeTank(Point2D.Double loc, Map m, Color c) {
		Tank ot = tank;
		tank = new Tank(loc, m, c);
		return ot;
	}

	public Player(String n, int f, int b, int l, int r, int s) {
		name = n; fb = f; bb = b; lb = l; rb = r; sb = s;
	}
}
