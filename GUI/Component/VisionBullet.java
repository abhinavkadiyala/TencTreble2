package Component;

import java.awt.geom.Point2D;

public class VisionBullet extends Bullet
{
  protected final int myRadius;
  public VisionBullet(Player player)
  {
    myDamage = 0;
    mySpeed = 1; //change value as needed
    myPlayer = player;
    myRadius = 5; //change value as needed
  }
}
