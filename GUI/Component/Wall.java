package Component;

public class Wall extends GameObject{
  int myDirection;
  Point2D.Double myLoc;
  
  public Wall(int direction, Point2D.Double loc)
  {
    myDirection = direction;
    myLoc = loc;
  }
  
  public int getDirection()
  {
    return myDirection;
  }
  
  public Point2D.Double getLocation()
  {
    return myLoc;
  }

}
