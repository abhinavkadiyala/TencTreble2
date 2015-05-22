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
	public double getDirection()
	{
		return myDirection;
	}
	public void setDirection(double newDirection)
	{
		myDirection = newDirection
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
	public void moveTo(Point2D.Double newLocation)
	{
		if(map == null)
			throw new IllegalStateException("This actor is not in a grid.");
		if(map.get(location) != this)
			throw new IllegalStateException("The grid contains a diefferent actor at location " + myLoc + ".");
		if(!map.isValid(newLocation))
			throw new IllegalArgumentexception("Location " + newLocation + " is not valid.");
		
		if (newLocation.equals(myLoc))
			return;
		map.remove(location);
		GameObject other = map.get(newLocation);
		if(other != null)
			this.conflict(other);
		myLoc = newLocation;
		map.put(myLoc, this);
	}
	public abstract void conflict(GameObject other);
	public abstract void update();
	
	
}
