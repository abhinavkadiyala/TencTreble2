package component;



import java.awt.*;
import java.awt.geom.*;
import java.util.*;


public class Tank extends GameObject
{
    int health = 1;     //default;
    // TODO: implement health if we plan on doing so.
    double move = 0, turn = 0; //positive for forward/left, negative for backward/right
    private final static int MAX_BULLETS = 5;
    LinkedList<Bullet> bullets;
    private final int width = 20;
    private final int height = 10;
    private final int swidth = 4;
    private final int sheight = 2;
    Color color;
    final static Color[] col = {
    	Color.red, Color.green, Color.blue,
    	Color.black
    };
    static int colind = 0;
   
    public boolean fire(Bullet bullet) {
        if (bullets.size() > MAX_BULLETS) return false;
        // TODO: bullet-specific stuff
        bullet.setMap(getMap());
        bullets.add(bullet);
        map.add(bullet);
        return true;
    }
    public void update() {
        ListIterator<Bullet> biter = bullets.listIterator();
		while (biter.hasNext()){
			if (biter.next().expired()){
				biter.remove();
			}
        }
        double dir = getDirection();
        setDirection(dir+turn);
        this.translate(move * Math.cos(dir), move * Math.sin(dir));
        // TODO: movement
    }
    public void move(double amt) {
		if (Math.signum(move) != Math.signum(amt))
			move += amt;
    }
    public void turn(double amt) {
		if (Math.signum(turn) != Math.signum(amt))
			turn += amt;
    }
	public double[] getXCoords() {
		double dir = this.getDirection();
		Point2D.Double loc = this.getLocation();
		double[] xCoordinates = {
			loc.x + Math.cos(dir) * width / 2 + Math.sin(dir) * height / 2,
			//beginning of shooting component
			loc.x + Math.cos(dir) * width / 2 + Math.sin(dir) * sheight / 2,
			loc.x + Math.cos(dir) * width / 2 + Math.sin(dir) * sheight / 2 + Math.cos(dir) * swidth,
			loc.x + Math.cos(dir) * width / 2 - Math.sin(dir) * sheight / 2 + Math.cos(dir) * swidth,
			loc.x + Math.cos(dir) * width / 2 - Math.sin(dir) * sheight / 2,
			//end of shooting component
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
			loc.y + Math.sin(dir) * width / 2 - Math.cos(dir) * height / 2, //for parallelogram, change to +
			//beginning of shooting component
			loc.y + Math.sin(dir) * width / 2 - Math.cos(dir) * sheight / 2,
			loc.y + Math.sin(dir) * width / 2 - Math.cos(dir) * sheight / 2 + Math.sin(dir) * swidth,
			loc.y + Math.sin(dir) * width / 2 + Math.cos(dir) * sheight / 2 + Math.sin(dir) * swidth,
			loc.y + Math.sin(dir) * width / 2 + Math.cos(dir) * sheight / 2,
			//end of shooting component
			loc.y + Math.sin(dir) * width / 2 + Math.cos(dir) * height / 2,//-
			loc.y - Math.sin(dir) * width / 2 + Math.cos(dir) * height / 2,//-
			loc.y - Math.sin(dir) * width / 2 - Math.cos(dir) * height / 2//+
		};
        return yCoordinates;
    }
    
    public void conflict(GameObject other){
        if (other instanceof Bullet){
        	((Bullet)other).destroy();
            if (other instanceof KillBullet){
                this.setMap(null);
            }
        }
        else if (other instanceof Tank){
        	 double dir = this.getDirection();
             this.translate(-move*Math.cos(dir), -move*Math.sin(dir));
        }
        else if (other instanceof Wall){
            double dir = this.getDirection();
            this.translate(-move*Math.cos(dir), -move*Math.sin(dir));
            
            // FILL THIS IN
            /* expected behavior:
             * move away from the wall
             * turn an amount relative to its angle of incidence from the wall (angle from the normal)
             */
        }
    }
	public Shape getBounds() {	//needs to be improved so it actually does something.
		//return new Polygon(getXCoords(), getYCoords(),4);
		/*double[] xCoords = getXCoords(), yCoords = getYCoords();
		Path2D.Double p2d = new Path2D.Double();
		for (int i = 0; i < 4; i++)
			p2d.moveTo(xCoords[i], yCoords[i]);
		p2d.moveTo(xCoords[0], yCoords[0]);
		return p2d;*/
		Point2D.Double loc = getLocation();
		return Game.rotation(loc, getDirection()).createTransformedShape(
				new Rectangle2D.Double(loc.x-width/2,loc.y-height/2,width,height));
		// this is currently disfunctional
	}
	public Tank(Point2D.Double l, Map m) {
		super(l, 0, m);
		move = turn = 0;
		bullets = new LinkedList<Bullet>();
		color = col[colind];
		colind = (colind + 1) % col.length;
	}
	@Override
	public void paint(Graphics2D g) {
		/*double[] dxc = getXCoords(), dyc = getYCoords();
		int[] ixc = new int[dxc.length], iyc = new int[dyc.length];
		for (int i = 0; i < 4; i++) {
			ixc[i] = (int) dxc[i];
			iyc[i] = (int) dyc[i];
		}
		g.fillPolygon(ixc, iyc, 4);*/
		super.paint(g);
		// extra stuff so we know orientation, etc.
	}
	@Override
	public Color getColor() {
		return color;
	}
}
