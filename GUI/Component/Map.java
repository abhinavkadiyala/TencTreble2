package component;

public class Map {
	Set<GameObject> obj;
	public Map() {
		obj = new HashSet<GameObject> obj;
	}
  
	public void add(GameObject gObj) {
		obj.add(gObj);
	}
	public void remove(GameObject gObj) {
		obj.remove(gObj);
	}
	public Set<GameObject> objects() {
		return obj;
	}
}
