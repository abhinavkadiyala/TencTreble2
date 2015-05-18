Package Component;
impot java.awt.geom.Point2D;

public class PowerUp extends GameObject{
  protected Power myPower;
  public PowerUp(Point2D.Double() loc, Power power){
    myLoc = loc;
    myPower = power;
    myDirection = 0;
  }
}
