package component;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public class Tank extends GameObject
{
    int health = 1;     //default;
    // TODO: implement health if we plan on doing so.
    double move, turn; //positive for forward/left, negative for backward/right
    private final static int MAX_BULLETS = 5;
    LinkedList<Bullet> bullets;
    private final int width = 20;
    private final int height = 10;
    
    public boolean fire(Bullet bullet) {
        if (bullets.size() <= MAX_BULLETS) return false;
        // TODO: bullet-specific stuff
        bullets.add(bullet);
        return true;
    }
    public void update() {
        ListIterator<Bullet> biter = bullets.listIterator();
        while (biter.hasNext())
            if (biter.next().expired()) biter.remove();
        double dir = getDirection();
        this.translate(move * Math.cos(dir), move * Math.sin(dir));
        // TODO: movement
    }
    public void move(double amt) {
        move += amt;
    }
    public void turn(double amt) {
        turn += amt;
    }
	public double[] getXCoords() {
		double dir = this.getDirection();
		Point2D.Double loc = this.getLocation();
		double[] xCoordinates = {
			loc.x + Math.cos(dir) * width / 2 + Math.sin(dir) * height / 2,
			loc.x + Math.cos(dir) * width / 2 - Math.sin(dir) * height / 2,
			loc.x - Math.cos(dir) * width / 2 - Math.sin(dir) * height / 2,
			loc.x - Math.cos(dir) * width / 2 + Math.sin(dir) * height / 2
		};
		// I still get a NullPointerException here
		return xCoordinates;
	}
    public double[] getYCoords(){
		double dir = this.getDirection();
		Point2D.Double loc = this.getLocation();
        double[] yCoordinates = {
        	loc.y + Math.sin(dir) * width / 2 + Math.cos(dir) * height / 2,
        	loc.y + Math.sin(dir) * width / 2 - Math.cos(dir) * height / 2,
        	loc.y - Math.sin(dir) * width / 2 - Math.cos(dir) * height / 2,
        	loc.y - Math.sin(dir) * width / 2 + Math.cos(dir) * height / 2
        };
        return yCoordinates;
    }
    
    public void conflict(GameObject other){
        if (other instanceof Bullet){
            other.setMap(null);
            if (other instanceof KillBullet){
                this.setMap(null);
            }
        }
        else if (other instanceof Wall){
            double dir = this.getDirection();
            this.translate(-Math.cos(dir), -Math.sin(dir));
            
            // FILL THIS IN
            /* expected behavior:
             * move away from the wall
             * turn an amount relative to its angle of incidence from the wall (angle from the normal)
             */
        }
    }
	public Shape getBounds() {
		//return new Polygon(getXCoords(), getYCoords(),4);
		double[] xCoords = getXCoords(), yCoords = getYCoords();
		Path2D p2d = new Path2D();
		for (int i = 0; i < 4; i++)
			p2D.moveTo(xCoords[i], yCoords[i]);
		p2D.moveTo(xCoords[0], yCoords[0]);
		return p2d;
	}
	public Tank(Point2D.Double l, Map m) {
		super(l, 0, m);
		move = turn = 0;
		bullets = new LinkedList<Bullet>();
	}
	@Override
	public void paint(Graphics2D g) {
		g.fillPolygon(this.getXCoords(), this.getYCoords(), 4);
		// TODO Auto-generated method stub
		
	}
}
