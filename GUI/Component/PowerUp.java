package component;
import java.awt.geom.Point2D;

public class PowerUp extends GameObject{
	protected Power myPower;
	public PowerUp(Point2D.Double loc, Power power, Map amap){
		super(loc, 0, amap);
		myPower = power;
	}
}
