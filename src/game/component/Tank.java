package game.component;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.ListIterator;

import game.Game;
import game.Map;
import game.component.field.Maze;
import game.component.field.Wall;


public class Tank extends GameObject
{
    int health = 1;     //default;
    // TODO: implement health if we plan on doing so.
    double move = 0, turn = 0; //positive for forward/left, negative for backward/right
    private final static int MAX_BULLETS = 5;
    LinkedList<Bullet> bullets;
    public static final double width = 21, height = 13;
    private final int swidth = 4;
    private final int sheight = 2;
    private final double CROSSHAIR_DIM = 11;
    Color color;
   
    public boolean fire(Bullet bullet) {
        if (bullets.size() >= MAX_BULLETS) return false;
        // TODO: bullet-specific stuff
        bullet.setMap(getMap());
        bullets.add(bullet);
        if (map != null)
        	map.add(bullet);
        return true;
    }
    public void update() {
    	if (!bullets.isEmpty() && bullets.getFirst().expired())
    		bullets.removeFirst();
        double dir = getDirection();
        setDirection(dir+turn);
        this.translate(move * Math.cos(dir), move * Math.sin(dir));
        // TODO: movement
    }
    public void move(double amt) {
		if (Math.signum(move) != Math.signum(amt))
			move += amt;
    }
    public double move() {
    	return move;
    }
    public void turn(double amt) {
		if (Math.signum(turn) != Math.signum(amt))
			turn += amt;
    }
    public double turn() {
    	return turn;
    }
    
    public void conflict(GameObject other){
        if (other instanceof Bullet){
        	((Bullet)other).destroy();
            if (other instanceof KillBullet){
                this.setMap(null);
            }
        }
//        else if (other instanceof Tank){
//        	 double dir = this.getDirection();
//             this.translate(-move*Math.cos(dir), -move*Math.sin(dir));
//        }
        else if (other instanceof Wall || other instanceof Maze) {
            double dir = this.getDirection();
            this.translate(-2*move*Math.cos(dir), -2*move*Math.sin(dir));
            this.setDirection(this.getDirection() - 2*turn);
            // FILL THIS IN
            /* expected behavior:
             * move away from the wall
             * turn an amount relative to its angle of incidence from the wall (angle from the normal)
             */
        }
    }
	public Shape getBounds() {
		Point2D.Double loc = getLocation();
		return Game.rotation(loc, getDirection()).createTransformedShape(
				new Rectangle2D.Double(loc.x-width/2,loc.y-height/2,width,height));
	}
	public Tank(Point2D.Double l, Map m, Color c) {
		super(l, 0, m);
		move = turn = 0;
		bullets = new LinkedList<Bullet>();
		color = c;
	}
	@Override
	public void paint(Graphics2D g) {
		super.paint(g);
		g.setColor(getColor().darker().darker().darker().darker().darker());
		g.draw(getBounds());
		Point2D.Double loc = getLocation();
		double dir = getDirection();
		Point2D.Double cl = new Point2D.Double(loc.x+(width/2+1)*Math.cos(dir), loc.y+(width/2+1)*Math.sin(dir));
		Ellipse2D.Double el = new Ellipse2D.Double(cl.x-CROSSHAIR_DIM/2,cl.y-CROSSHAIR_DIM/2,CROSSHAIR_DIM,CROSSHAIR_DIM);
		Line2D horiz = new Line2D.Double(cl.x-CROSSHAIR_DIM,cl.y, cl.x+CROSSHAIR_DIM,cl.y),
		       vert  = new Line2D.Double(cl.x,cl.y-CROSSHAIR_DIM, cl.x,cl.y+CROSSHAIR_DIM);
		g.setColor(getColor().darker().darker().darker().darker());
		g.draw(el);
		g.draw(horiz);
		g.draw(vert);
		// extra stuff so we know orientation, etc.
	}
	@Override
	public Color getColor() {
		return color;
	}
}
