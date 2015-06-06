package component;
/**
 * 
 */


import java.awt.*;
import java.awt.geom.*;
import java.util.*;


public class Maze extends GameObject {
	MazeGenerator mg;
	Set<Wall> walls;
	private static final int XMIN = 2, YMIN = 2;
	
	public Maze(String code, Map mp) {
		super(new Point2D.Double(),0,mp);
		mg = MazeGenerator.encoded(new Long(code));
	}
	public Maze(int xmax, int ymax, Map mp) {
		super(new Point2D.Double(),0,mp);
		walls = new HashSet<Wall>();
		int x = (int) (Math.random() * (xmax-XMIN) + XMIN);
		int y = (int) (Math.random() * (ymax-YMIN) + YMIN);
		long seed = System.currentTimeMillis();
		mg = new MazeGenerator(x, y, seed);
		int[][] maze = mg.maze();
		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {
				if((maze[j][i] & 1) == 0) walls.add(new Wall(new Point2D.Double(j*Game.CELL_SIDE, i*Game.CELL_SIDE),0,mp));
			}
			for (int j = 0; j < x; j++) {
				if((maze[j][i] & 8) == 0) walls.add(new Wall(new Point2D.Double(j*Game.CELL_SIDE, i*Game.CELL_SIDE),Math.PI/2,mp));
			}	
			walls.add(new Wall(new Point2D.Double(x*Game.CELL_SIDE, i*Game.CELL_SIDE),Math.PI/2,mp));
		}
		for (int j = 0; j < x; j++) {
			walls.add(new Wall(new Point2D.Double(j*Game.CELL_SIDE, y*Game.CELL_SIDE),0,mp));
		}
	}
	
	public int width() {
		return mg.maze()[0].length;
	}
	public int height() {
		return mg.maze().length;
	}
	
	public long code() {
		return mg.encode();
	}

	/* (non-Javadoc)
	 * @see component.GameObject#conflict(component.GameObject)
	 */
	@Override
	public void conflict(GameObject other) {}

	/* (non-Javadoc)
	 * @see component.GameObject#update()
	 */
	@Override
	public void update() {}

	/* (non-Javadoc)
	 * @see component.GameObject#paint(java.awt.Graphics2D)
	 */
	@Override
	public void paint(Graphics2D g) {
		for (Wall w : walls)
			w.paint(g);
	}

	public Shape getBounds() {
		Area a = new Area();
		for (Wall w : walls)
			a.add(new Area(w.getBounds()));
		return a;
	}
}
