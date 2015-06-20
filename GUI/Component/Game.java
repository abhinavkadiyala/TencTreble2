package component;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

public class Game implements KeyListener
{
	public static int CELL_SIDE = 50;
	private static final int[][] dKey = {
		{KeyEvent.VK_UP     , KeyEvent.VK_DOWN   , KeyEvent.VK_LEFT   , KeyEvent.VK_RIGHT  , KeyEvent.VK_SLASH  },
		{KeyEvent.VK_E      , KeyEvent.VK_D      , KeyEvent.VK_S      , KeyEvent.VK_F      , KeyEvent.VK_Q      },
		{KeyEvent.VK_NUMPAD5, KeyEvent.VK_NUMPAD2, KeyEvent.VK_NUMPAD1, KeyEvent.VK_NUMPAD3, KeyEvent.VK_NUMPAD7}
	};
	Player[] players;
	Map map;
	Maze mz;
	long opt;
	public static final Color[] colors = {
		Color.red, Color.green, Color.blue, Color.yellow
	};

	private void initPlayers() {
		for (int i = 0; i < players.length; i++) {
			players[i] = new Player("Player "+(i+1),dKey[i][0],dKey[i][1],dKey[i][2],dKey[i][3],dKey[i][4]);
		}
		initPlayerTanks();
	}
	private void initPlayerTanks() {
		Random r = new Random();
		for (int i = 0; i < players.length; i++)
			players[i].makeTank(new java.awt.geom.Point2D.Double(
					CELL_SIDE/2+CELL_SIDE*r.nextInt(mz.height()-1),
					CELL_SIDE/2+CELL_SIDE*r.nextInt(mz.width()-1)),	map, colors[i]);
	}
	public Game() {
		players = new Player[0];
		map = new Map();
	}
	public Game(int pct) {
		//players = new Player[pct];
		players = new Player[pct];
		map = new Map();
		mz = new Maze(20,11,map);
		initPlayers();
//		add(new KillBullet(new java.awt.geom.Point2D.Double(35,72),1,map,/*players[0]*/null));
	}
	public Game(String code) {
		map = new Map();
		mz = new Maze(code, map);
	}
	public Game(Player... ps) {
		this(new Map(), ps);
	}
	public Game(Map mp, Player... ps) {
		players = ps;
		map = mp;
		mz = new Maze(25,11,map);
		initPlayerTanks();
	}
	
	public int playerCt() {
		return players.length;
	}
	public Player[] getPlayers(){
		return players;
	}

	public void keyPressed(KeyEvent key) {
		for (Player p : players) p.keyPressed(key.getKeyCode());
	}
	public void keyReleased(KeyEvent key) {
		for (Player p : players) p.keyReleased(key.getKeyCode());
	}
	public void keyTyped(KeyEvent key) {return;}
	public void update() throws Exception {
		/*Set<GameObject> obj = map.getObjects();
		obj.remove(null);
		java.util.List<Tank> lt = new ArrayList<>(playerCt());
		for (GameObject go : obj) {
			go.update();
			if (go instanceof Tank) {
				if (go.getMap() != null) lt.add((Tank)go); 
				for (GameObject go2 : obj)
					if (GameObject.intersect(go.getBounds(), go2.getBounds()))
						go.conflict(go2);
			} else if (go instanceof Bullet) {
				for (GameObject go2 : obj)
					if (go2 instanceof Wall && GameObject.intersect(go.getBounds(), go2.getBounds()))
						go.conflict(go2);
			}
		}
		if (lt.size() < 2)
			throw new Exception();*/
		map.update();
	}
	public static AffineTransform rotation(Point2D.Double anchor, double radians) {
		AffineTransform r = new AffineTransform();
		r.rotate(radians, anchor.x, anchor.y);
		return r;
	}
	public void add (GameObject gObj){
		map.add(gObj);
	}
	public void remove (GameObject gObj){
		map.remove(gObj);
	}
	public Map getMap() {
		return map;
	}
	public void paint(Graphics2D g) {
		map.paint(g);
	}
	
	public Shape getBounds()
	{
		return mz.getBounds();
	}
	public void setMaze(String code) {
		mz = new Maze(code, map);
	}
	public String code() {
		return mz.code();
	}
}
