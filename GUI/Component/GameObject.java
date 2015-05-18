package Component;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;

import javax.swing.*;

public class GameObject {
	protected Double myLoc;
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
	
	public void act()
	{}
	public abstract void update();
	
	
}
