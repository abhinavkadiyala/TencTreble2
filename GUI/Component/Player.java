
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    Tank tank;
    String name;
    int score;
    int fb, bb, lb, rb, sb;         //ForwardButton, BackButton, LeftButton, RightButton, ShootButton
    private static final double fv, bv, ts;    //ForwardVel, BackVel, TurnSpd
    static {
        fv = 1.1; bv = 1; ts = 1;
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
    public void update() {
        // TODO: fill this in
    }
    public Bullet getBullet() {
        return null;    // TODO: make this actually create+return a bullet
    }
    
    public Player(String n, int f, int b, int l, int r) {
        name = n; fb = f; bb = b; lb = l; rb = r;
    }
}
