import java.awt.geo.Point2D;
public class Game implements KeyListener{
  private Player[] players;
  private HashSet<GameObject> obj;
  // STILL NEED TO IMPLEMENT TIME
  public Game(){
    players = new Player[];
    obj = new HashSet<GameObject>();
  }
  public GameObject isValid(Point2D.Double loc){
    if (obj.get(loc) != null){
      return obj.get(loc);
    }
    else {
      return null;
    }
  }
    
}