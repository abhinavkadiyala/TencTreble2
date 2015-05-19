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
	protected Maze maze;
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
		mz.put(loc, this);
		maze = mz;
		myLoc = loc;
	}
	public void removeSelfFromGrid()
	{
		if(maze == null)
			throw new IllegalStateException("This actor is not contained in a grid.");
		if(maze.get(location) != this)
			throw new IllegalStateException("The grid conatins a different actor at location" + location + ".");
		maze.remove(location);
		maze = null;
		myLoc = null;
	}
	public abstract void update();
	
	
}
