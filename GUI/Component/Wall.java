package Component;

import java.awt.geom.Point2D;

public class Wall extends GameObject{
  
  public Wall(int direction, Point2D.Double loc)
  {
    myDirection = direction;
    myLoc = loc;
  }

}
