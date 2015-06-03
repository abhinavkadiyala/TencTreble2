package component;


import java.util.*;
import java.awt.*;


public class Map {
	int size;
	Set<GameObject> obj;
	ArrayList<GameObject> rm = new ArrayList<GameObject>();
	public Map() {
		// STILL NEED SOME WAY TO USE MAZE GENERATOR
		obj = new HashSet<GameObject>();
	}
	
	public Set<GameObject> getObjects(){
		for (GameObject go : rm) obj.remove(go);
		rm = new ArrayList<GameObject>();
		return obj;
	}
	public void add(GameObject gObj) {
		obj.add(gObj);
	}
	public void remove(GameObject gObj) {
		//obj.remove(gObj);
		rm.add(gObj);
	}
	
	public void paint(Graphics2D g) {
		for (GameObject go : obj) {
			go.paint(g);
		}
	}
}
