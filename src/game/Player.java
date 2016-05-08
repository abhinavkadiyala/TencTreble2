package game;

import java.awt.Color;
import java.awt.geom.Point2D;

import game.component.Bullet;
import game.component.KillBullet;
import game.component.Tank;


/**
 * A player object that represents an in-game player.
 * processes inputs, and tracks score
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
	Tank tank;      //TODO: create a tank somewhere
	String name;
	int score = 0;
	public int fb, bb, lb, rb, sb;         //ForwardButton, BackButton, LeftButton, RightButton, ShootButton
	boolean ctrl, alt, meta, win;
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
		if (key == fb && tank.move() > 0)
			tank.move(-fv);
		else if (key == bb && tank.move() < 0)
			tank.move(bv);
		if (key == lb && tank.turn() < 0)
			tank.turn(ts);
		else if (key == rb && tank.turn() > 0)
			tank.turn(-ts);
		else if (key == sb)
			tank.fire(getBullet());
		else return false;	
		return true;
	}
	public Bullet getBullet() {
		Point2D.Double loc = this.getTank().getLocation();
		double dir = this.getTank().getDirection();
		Point2D.Double newLoc = new Point2D.Double(
				loc.x + (Bullet.BULLET_RADIUS-(Bullet.getSpeed() - fv) + .001 + Tank.width/2) * Math.cos(dir),
				loc.y + (Bullet.BULLET_RADIUS-(Bullet.getSpeed() - fv) + .001 + Tank.width/2) * Math.sin(dir)
			);
		Bullet hej = new KillBullet(newLoc ,dir, null, this);
		return hej;
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
	public void controls(int f, int b, int l, int r, int s) {
		fb = f; bb = b; lb = l; rb = r; sb = s;
	}
}
