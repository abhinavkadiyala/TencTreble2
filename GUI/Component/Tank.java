import java.awt.geom.*;
import java.util.*;

public class Tank extends GameObject
{
    int health = 1;     //default;
    // TODO: implement health if we plan on doing so.
    PowerUp power;
    double angle;
    double move, turn; //positive for forward/right, negative for backward/left
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
        // TODO: movement
    }
    public void move(double amt) {
        move += amt;
    }
    public void turn(double amt) {
        turn += amt;
    }
    
    public Tank(Point2D.Double l, Maze m) {
        super(l, 0, m);
        power = null;
        move = turn = 0;
        bullets = new LinkedList<Bullet>();
    }
}
