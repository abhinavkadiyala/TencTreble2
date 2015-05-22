package component;

public class Map{
  HashSet<GameObject> obj;
  
  public Map (){
    obj = new HashSet<GameObject> obj;
  }
  
  public void add (GameObject gObj){
      obj.add(gObj);
  }
  public void remove (GameObject obj){
      obj.remove(gObj);
    }

}
