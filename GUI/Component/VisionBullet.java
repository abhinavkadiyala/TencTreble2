package Component;

import java.awt.geom.Point2D;

public class VisionBullet extends Bullet
{
  protected final int myRadius;
  public VisionBullet(Player player)
  {
    super(player);
    myDamage = 0;
    myRadius = 5; //change value as needed
  }
}
