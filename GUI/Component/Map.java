package component;


import java.util.*;
import java.awt.*;


public class Map {
	int size;
	Set<GameObject> obj;
	Set<Tank> tanks;
	Maze walls;
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
		for (GameObject go: ad) {
			if (go instanceof Tank) tanks.add(go);
			else if (go instanceof Wall) continue;
			else if (go instanceof Bullet) bullets.add(go);
			else if (go instanceof Maze) walls = go;
			else obj.add(go);
		}
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
		walls.paint();
		for (Tank t : tanks) t.paint();
		for (Bullet b : bullets) b.paint();
	}
}
