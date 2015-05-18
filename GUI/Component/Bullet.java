package Component;

import java.awt.geom.Point2D;

public abstract class Bullet extends GameObject
{
protected final int myDamage;
protected double mySpeed;
protected Player myPlayer;
protected Long startTime;
protected final long LIFETIME = 10000;

  public class Bullet(Player player)
  {
    myPlayer = player;
    startTime = System.currentTimeMillis();
  }
  public int getSpeed()
  {
    return mySpeed;
  }
  
  public Player getPlayer()
  {
    return myPlayer;
  }
  
  public boolean expired (){
    return (System.currentTimeMillis() - startTime >= LIFETIME);
  }
  
  public long time()
  {
    return System.currentTimeMillis - startTime;
  }
  
  public void act()
  {
    if(this.time() >= LIFETIME)
    {
      this.destroy();
    }
  }
  
  public void destroy()
  {
    //needs implementation
  }

}
