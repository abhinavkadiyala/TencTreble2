package component;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class VisionBullet extends Bullet
{
  protected final int myRadius;
  public VisionBullet(Player player)
  {
    super(player);
    myRadius = 5; //change value as needed
  }
@Override
public void paint(Graphics2D g) {
	// TODO Auto-generated method stub
	
}
}
