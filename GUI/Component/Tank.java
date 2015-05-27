package Component;

import java.awt.geom.*;
import java.util.*;

public class Tank extends GameObject
{
    int health = 1;     //default;
    // TODO: implement health if we plan on doing so.
    PowerUp power;
    double move, turn; //positive for forward/left, negative for backward/right
    private final static int MAX_BULLETS = 5;
    LinkedList<Bullet> bullets;
    
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
        Point2D.Double current = this.getLocation();
        this.setLocation(new Point2D.Double(current.getX() + move * Math.cos(this.getDirection()), current.getY() + move * Math.sin(this.getDirection())));
        // TODO: movement
    }
    public void move(double amt) {
        move += amt;
    }
    public void turn(double amt) {
        turn += amt;
    }
    public void conflict (GameObject other){
        if (other instanceof Bullet){
            other.setMap(null);
            if (other instanceof KillBullet){
                this.setMap(null);
            }
        }
        else if (other instanceof Wall){
            double direction = this.getDirection();
            Point2D.Double current = this.getLocation();
            this.setLocation(new Point2D.Double(current.getX() - move * Math.cos(this.getDirection()), current.getY() - move * Math.sin(this.getDirection())));
            
            // FILL THIS IN
            /* expected behavior:
             * move away from the wall
             * turn an amount relative to its angle of incidence from the wall (angle from the normal)
             */
        }
    }
 
    public Tank(Point2D.Double l, Map m) {
        super(l, 0, m);
        power = null;
        move = turn = 0;
        bullets = new LinkedList<Bullet>();
    }
}
