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
    super(player.getTank().getLocation(), player.getTank().getDirection(), player.getTank().getMap());
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
  
  public void conflict(GameObject other)
  {
    if(other instanceof Wall)
    {
      this.setDirection(2*other.getDirection() - this.getDirection());
      //physically bounce somehow
    }
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
