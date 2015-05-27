package Component;

import java.awt.geom.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public abstract class GameObject {
	protected Point2D.Double myLoc;
	protected double myDirection;
	protected Map map;
	protected Image image;
	
	private GameObject()
	{
		myLoc = new Point2D.Double();
		myDirection = 0;
		map = null;
		image = null;
	}
	
	public GameObject(Point2D.Double loc, double dir, Map aMap)
	{
		myLoc = loc;
		myDirection = dir;
		map = aMap;
	}
<<<<<<< HEAD
	
	public void putSelfInGrid(Maze mz, Point2D.Double loc)
=======
	public Map getMap()
	{
		return map;
	}
	public Point2D.Double getLocation()
>>>>>>> origin/master
	{
		return myLoc;
	}
	public Point2D.Double setLocation(Point2D.Double loc) {
		Point2D.Double ol = myLoc;
		myLoc = loc;
		return ol;
	}
<<<<<<< HEAD
	
	public void removeSelfFromGrid()
=======
	public double getDirection()
>>>>>>> origin/master
	{
		return myDirection;
	}
<<<<<<< HEAD
	
	public void update()
=======
	public double setDirection(double newDirection)
>>>>>>> origin/master
	{
		double od = myDirection;
		myDirection = newDirection;
		return od;
	}
	public void setMap(Map mp)
	{
		if (mp == null) map.remove(this);
		else mp.add(this);
		map = mp;
	}
	public abstract void conflict(GameObject other);
	public abstract void update(); //apparently it took in an int before
}
