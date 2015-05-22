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
	public abstract void conflict(GameObject other);
	public abstract void update();
	
	
}
