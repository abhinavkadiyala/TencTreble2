package game.component;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import game.Map;
import game.Player;
import game.component.field.Wall;

public abstract class Bullet extends GameObject
{
	public static final double BULLET_RADIUS = 2;
	protected final int myDamage = 1;
	protected static double mySpeed = 2.4;
	protected Player myPlayer;
	protected static final int LIFEDIST = 1250;
	public int dt = 0;	//distance traveled
	protected boolean expire = false;

	public Bullet(Player player) {
		super(player.getTank().getLocation(), player.getTank().getDirection(), player.getTank().getMap());
		myPlayer = player;
	}
	public Bullet(Point2D.Double loc, double dir, Map mp, Player p) {
		super(loc,dir,mp);
		myPlayer = p;
	}
	public static double getSpeed() {
		return mySpeed;
	}
	
	public Shape getBounds() {
		
		return new Ellipse2D.Double(getLocation().x-BULLET_RADIUS,getLocation().y-BULLET_RADIUS,2*BULLET_RADIUS,2*BULLET_RADIUS);	
	}
  
	public Player getPlayer() {
		return myPlayer;
	}
  
	public boolean expired() {
		return expire;
	}
	
	public void conflict(GameObject other) {
		if(other instanceof Wall) {
			this.setDirection(2*other.getDirection() - this.getDirection());
			move();		//try to fix glitch of bullet trapped in wall
		} else if (other instanceof Tank) {
			// already resolved by the Tank
		}
		else if (other instanceof Bullet){
			// not sure if this should do anything;
		}
	}
  
	public void update() {
		if(dt >= LIFEDIST) {
			this.destroy();
			expire = true;
		} else {
			move();
		}
	}
	
	public void move() {
		this.translate(mySpeed*Math.cos(getDirection()), mySpeed*Math.sin(getDirection()));	//move
		dt += mySpeed;
	}
  
	public void destroy() {
		setMap(null);
		expire = true;
		//needs implementation
	}

}
