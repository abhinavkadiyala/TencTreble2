/**
 * 
 */
package component;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;


public class Maze extends GameObject {
	MazeGenerator mg;
	Set<Wall> walls;
	
	public Maze(int xmax, int ymax, Map mp) {
		super(new Point2D.Double(),0,mp);
		int x = (int) (Math.random() * (xmax-3) + 3);
		int y = (int) (Math.random() * (ymax-3) + 3);
		long seed = System.currentTimeMillis();
		mg = new MazeGenerator(x, y, seed);
		int[][] maze = mg.maze();
		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {
				if((maze[j][i] & 1) == 0) walls.add(new Wall(new Point2D.Double(j*Game.CELL_SIDE, i*Game.CELL_SIDE),0,mp));
			}
			for (int j = 0; j < x; j++) {
				if((maze[j][i] & 8) == 0) walls.add(new Wall(new Point2D.Double(j*Game.CELL_SIDE, i*Game.CELL_SIDE),0,mp));
			}	
			walls.add(new Wall(new Point2D.Double(x*Game.CELL_SIDE, i*Game.CELL_SIDE),0,mp));
		}
		for (int j = 0; j < x; j++) {
			walls.add(new Wall(new Point2D.Double(j*Game.CELL_SIDE, y*Game.CELL_SIDE),0,mp));
		}
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

}
