package Component;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;

import javax.swing.*;

public class GameObject {
	protected Point2D.Double myLoc;
	protected double myDirection;
	protected Map map;
	protected Image image;
	
	public GameObject()
	{
		myLoc = new Point2D.Double();
		myDirection = 0;
		map = null;
		image = null;
	}
	
	public GameObject(Point2D.Double loc, int dir, Map aMap)
	{
		myLoc = loc;
		myDirection = dir;
		map = aMap;
	}
	
	public void putSelfInGrid(Maze mz, Point2D.Double loc)
	{
		if(grid != null)
			throw new IllegalStateException("This actor is already contained in a grid.");
		
		GameObject gameObject = mz.get(loc);
		if(gameObject != null)
			gameObject.removeSelfFromGrid();
		mz.put(this);
		map = mz;
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
	
	public void update()
	{
		
	}
	
	
}
