package Component;

import java.awt.geom.Point2D;

public abstract class Bullet extends GameObject
{
protected final int myDamage;
protected double mySpeed;
protected Player myPlayer;
protected Long startTime;
protected final long LIFETIME = 10000;
protected boolean expire = false;

  public class Bullet(Player player)
  {
    myPlayer = player;
    startTime = System.currentTimeMillis();
  }
  public double getSpeed()
  {
    return mySpeed;
  }
  
  public Player getPlayer()
  {
    return myPlayer;
  }
  
  public boolean expired (){
    return expire;
  }
  
  public long time()
  {
    return System.currentTimeMillis - startTime;
  }
  
  public void update()
  {
    if(this.time() >= LIFETIME)
    {
      this.destroy();
      expire = true;
    }else
    {
      //move
    }
  }
  
  public void destroy()
  {
    //needs implementation
  }

}
