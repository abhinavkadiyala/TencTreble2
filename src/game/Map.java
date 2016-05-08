package game;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import game.component.Bullet;
import game.component.GameObject;
import game.component.Tank;
import game.component.field.Maze;
import game.component.field.Wall;


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
		tanks = new HashSet<Tank>();
		bullets = new HashSet<Bullet>();
	}
	public Maze walls() {
		return walls;
	}
	
	public void update() throws Exception {
		for (GameObject go : rm) {
			obj.remove(go);
			try {
				tanks.remove((Tank)go);
			} catch (ClassCastException e) {}
			try {
				bullets.remove((Bullet)go);
			} catch (ClassCastException e) {}
		}
		rm = new ArrayList<GameObject>();
		for (GameObject go: ad) {
			if (go instanceof Tank) tanks.add((Tank) go);
			else if (go instanceof Wall) continue;
			else if (go instanceof Bullet) bullets.add((Bullet) go);
			else if (go instanceof Maze) walls = (Maze) go;
			else obj.add(go);
		}
		ad = new ArrayList<GameObject>();
		//return obj;
		for (Bullet b : bullets) {
			if (b.dt < Bullet.getSpeed()) {
				b.update();
				for (Wall w : walls.walls())
					if (b.getBounds().intersects((Rectangle2D) w.getBounds())) b.conflict(w);
			}
			b.update();
			for (Wall w : walls.walls())
				if (b.getBounds().intersects((Rectangle2D) w.getBounds())) b.conflict(w);
		}
		for (Tank t : tanks) {
			t.update();
			for (Bullet b : bullets)
				if (GameObject.intersect(t.getBounds(), b.getBounds())) t.conflict(b);
			if (GameObject.intersect(t.getBounds(), walls.getBounds())) t.conflict(walls);
		}
		if (tanks.size() < 2) throw new Exception();
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
		if (walls != null) walls.paint(g);
		for (Tank t : tanks) t.paint(g);
		for (Bullet b : bullets) b.paint(g);
	}
}
