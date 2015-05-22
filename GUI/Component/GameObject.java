package component;

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
	public Map getMap()
	{
		return map;
	}
	public Point2D.Double getLocation()
	{
		return myLoc;
	}
	public Point2D.Double setLocation(Point2D.Double loc) {
		Point2D.Double ol = myLoc;
		myLoc = loc;
		return ol;
	}
	public double getDirection()
	{
		return myDirection;
	}
	public double setDirection(double newDirection)
	{
		double od = myDirection;
		myDirection = newDirection
		return od;
	}
	public void putSelfInGrid(Map mp, Point2D.Double loc)
	{
		if(map != null)
			throw new IllegalStateException("This actor is already contained in a grid.");
		
		GameObject gameObject = mp.get(loc);
		if(gameObject != null)
			gameObject.removeSelfFromGrid();
		mp.put(this);
		map = mp;
		myLoc = loc;
	}
	public void removeSelfFromGrid()
	{
		if(map == null)
			throw new IllegalStateException("This actor is not contained in a grid.");
		if(map.get(location) != this)
			throw new IllegalStateException("The grid conatins a different actor at location" + location + ".");
		map.remove(this);
		map = null;
		myLoc = null;
	}
	public abstract void conflict(GameObject other);
	public abstract void update();
	
	
}
