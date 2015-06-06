package component;


import java.util.*;
import java.awt.*;


public class Map {
	int size;
	Set<GameObject> obj;
	Set<Tank> tanks;
	Set<Wall> walls;
	Set<Bullet> bullets;
	ArrayList<GameObject> rm = new ArrayList<GameObject>();
	ArrayList<GameObject> ad = new ArrayList<GameObject>();
	public Map() {
		// STILL NEED SOME WAY TO USE MAZE GENERATOR
		obj = new HashSet<GameObject>();
	}
	
	public Set<GameObject> getObjects(){
		for (GameObject go : rm) obj.remove(go);
		rm = new ArrayList<GameObject>();
		for (GameObject go: ad) obj.add(go);
		ad = new ArrayList<GameObject>();
		return obj;
	}
	public void add(GameObject gObj) {
		//obj.add(gObj);
		ad.add(gObj);
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
