package Component;

import java.awt.geom.Point2D;

public abstract class Bullet extends GameObject
{
protected final int myDamage;
protected double mySpeed;
protected Player myPlayer;
protected Long startTime;

  public int getSpeed()
  {
    return mySpeed;
  }
  
  public Player getPlayer()
  {
    return myPlayer;
  }

}