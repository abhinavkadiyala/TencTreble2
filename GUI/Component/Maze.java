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
	Shape bounds;
	
	public Maze(String code, Map mp) {
		super(new Point2D.Double(),0,mp);
		mg = MazeGenerator.encoded(code);
		walls = new HashSet<Wall>();
		System.out.println(mg.maze()[0].length+" "+mg.maze().length);
		initBounds(mp);
		System.out.println(mg.encode());
	}
	public Maze(int xmax, int ymax, Map mp) {
		super(new Point2D.Double(),0,mp);
		walls = new HashSet<Wall>();
		int x = (int) (Math.random() * (xmax-XMIN) + XMIN);
		int y = (int) (Math.random() * (ymax-YMIN) + YMIN);
		long seed = System.currentTimeMillis();
		mg = new MazeGenerator(x, y, seed);
		initBounds(mp);
	}
	private void initBounds(Map mp) {
		int[][] maze = mg.maze();
		int x = maze[0].length;
		int y = maze.length;
		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {
				if((maze[i][j] & 1) == 0) walls.add(new Wall(new Point2D.Double(i*Game.CELL_SIDE, j*Game.CELL_SIDE),0,mp));
			}
			for (int j = 0; j < x; j++) {
				if((maze[i][j] & 8) == 0) walls.add(new Wall(new Point2D.Double(i*Game.CELL_SIDE, j*Game.CELL_SIDE),Math.PI/2,mp));
			}	
			walls.add(new Wall(new Point2D.Double(i*Game.CELL_SIDE, x*Game.CELL_SIDE),0,mp));
		}
		for (int j = 0; j < x; j++) {
			walls.add(new Wall(new Point2D.Double(y*Game.CELL_SIDE, j*Game.CELL_SIDE),Math.PI/2,mp));
		}
		Area a = new Area();
		for (Wall w : walls)
			a.add(new Area(w.getBounds()));
		bounds = a;
	}
	@SuppressWarnings("unused")
	private void init(int x, int y, Map mp) {	//not sure if it works
		Area a = new Area();
		int[][] maze = mg.maze();
		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {
				if((maze[j][i] & 1) == 0)
					a.add(new Area(new Wall(new Point2D.Double(j*Game.CELL_SIDE, i*Game.CELL_SIDE),0,mp).getBounds()));
			}
			for (int j = 0; j < x; j++) {
				if((maze[j][i] & 8) == 0)
					a.add(new Area(new Wall(new Point2D.Double(j*Game.CELL_SIDE, i*Game.CELL_SIDE),Math.PI/2,mp).getBounds()));
			}	
			a.add(new Area(new Wall(new Point2D.Double(x*Game.CELL_SIDE, i*Game.CELL_SIDE),Math.PI/2,mp).getBounds()));
		}
		for (int j = 0; j < x; j++) {
			a.add(new Area(new Wall(new Point2D.Double(j*Game.CELL_SIDE, y*Game.CELL_SIDE),0,mp).getBounds()));
		}
		bounds = a;
	}
	
	public int width() {
		return mg.maze()[0].length;
	}
	public int height() {
		return mg.maze().length;
	}
	
	public String code() {
		return mg.encode2();
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

	public Shape getBounds() {
		return bounds;
	}
	public Set<Wall> walls() {
		return walls;
	}
}
