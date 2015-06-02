package component;


import java.awt.*;
import java.awt.geom.*;

public abstract class Bullet extends GameObject
{
	private static final double BULLET_RADIUS = 2;
	protected final int myDamage = 1;
	protected double mySpeed = 1.2;
	protected Player myPlayer;
	protected Long startTime;
	protected final long LIFETIME = 10000;
	protected boolean expire = false;

	public Bullet(Player player) {
		super(player.getTank().getLocation(), player.getTank().getDirection(), player.getTank().getMap());
		myPlayer = player;
		startTime = System.currentTimeMillis();
	}
	public Bullet(Point2D.Double loc, double dir, Map mp, Player p) {
		super(loc,dir,mp);
		myPlayer = p;
		startTime = System.currentTimeMillis();
	}
	public double getSpeed() {
		return mySpeed;
	}
	
	public Shape getBounds() {
		
		return new Ellipse2D.Double(getLocation().x-BULLET_RADIUS,getLocation().y-BULLET_RADIUS,2*BULLET_RADIUS,2*BULLET_RADIUS);	
	}
  
	public Player getPlayer() {
		return myPlayer;
	}
  
	public boolean expired () {
		return expire;
	}
  
	public long time() {
		return System.currentTimeMillis() - startTime;
	}
	
	public void conflict(GameObject other) {
		if(other instanceof Wall) {
			this.setDirection(2*other.getDirection() - this.getDirection());
		} else if (other instanceof Tank) {
			// already resolved by the Tank
		}
		else if (other instanceof Bullet){
			// not sure if this should do anything;
		}
	}
  
	public void update() {
		if(this.time() >= LIFETIME) {
			this.destroy();
			expire = true;
		} else {
			this.translate(mySpeed*Math.cos(getDirection()), mySpeed*Math.sin(getDirection()));	//move
		}
	}
  
	public void destroy() {
		setMap(null);
		//needs implementation
	}
	
	public void paint(Graphics2D g) {
		g.fillOval((int)(getLocation().x-BULLET_RADIUS),(int)(getLocation().y-BULLET_RADIUS),2*(int)BULLET_RADIUS,2*(int)BULLET_RADIUS);
	}

}
