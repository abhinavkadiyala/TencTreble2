package component;

import java.awt.geom.Point2D;

public class Wall extends GameObject{
  
  public Wall(Point2D.Double loc, double dir, Maze aMap)
  {
    super(loc, dir, aMap);
  }

}
