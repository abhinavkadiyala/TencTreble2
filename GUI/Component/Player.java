package component;


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
		fv = 1.9; bv = 1.5; ts = 1;
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
		return null;    // TODO: make this actually create+return a bullet
	}
	public Tank getTank() {
		return tank;
	}
	public Tank makeTank(Point2D.Double loc, Map m) {
		tank = new Tank(loc, m);
	}

	public Player(String n, int f, int b, int l, int r, int s) {
		name = n; fb = f; bb = b; lb = l; rb = r; sb = s;
	}
}
