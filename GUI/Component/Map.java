package component;

import java.util.*;


public class Map {
	Set<GameObject> obj;
	public Map() {
		obj = new HashSet<GameObject>();
	}
	
	public Set<GameObject> getObjects(){
		return obj;
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
	public void paint(Graphics2D g) {
		for (GameObject go : obj) {
			go.paint(g);
		}
	}
}
