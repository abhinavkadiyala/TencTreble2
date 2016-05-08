package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.Random;

import game.component.GameObject;
import game.component.field.Maze;

public class Game implements KeyListener
{
	public static int CELL_SIDE = 50;
	public static final int[][] dKey = {
		{KeyEvent.VK_UP     , KeyEvent.VK_DOWN   , KeyEvent.VK_LEFT   , KeyEvent.VK_RIGHT  , KeyEvent.VK_SLASH  },
		{KeyEvent.VK_E      , KeyEvent.VK_D      , KeyEvent.VK_S      , KeyEvent.VK_F      , KeyEvent.VK_Q      },
		{KeyEvent.VK_NUMPAD5, KeyEvent.VK_NUMPAD2, KeyEvent.VK_NUMPAD1, KeyEvent.VK_NUMPAD3, KeyEvent.VK_NUMPAD7}
	};
	Player[] players;
	Map map;
	Maze mz;
	long opt;
	public boolean paused = false;
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
					CELL_SIDE/2+CELL_SIDE*r.nextInt(mz.height()),
					CELL_SIDE/2+CELL_SIDE*r.nextInt(mz.width())),	map, colors[i]);
	}
	public Game() {
		players = new Player[0];
		map = new Map();
	}
	public Game(int pct) {
		players = new Player[pct];
		map = new Map();
		mz = new Maze(20,11,map);
		initPlayers();
	}
	public Game(String code, int pct) {
		players = new Player[pct];
		map = new Map();
		mz = new Maze(code, map);
		initPlayers();
	}
	public Game(String code) {
		map = new Map();
		mz = new Maze(code, map);
	}
	public Game(Player... ps) {
		this(new Map(), ps);
	}
	public Game(String code, Player... ps) {
		this(new Map(), code, ps);
	}
	public Game(Map mp, Player... ps) {
		players = ps;
		map = mp;
		mz = new Maze(25,11,map);
		initPlayerTanks();
	}
	public Game(Map mp, String code, Player... ps) {
		players = ps;
		map = mp;
		mz = new Maze(code, map);
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
	
	public void setMaze(String code) {
		mz = new Maze(code, map);
	}
	public String code() {
		return mz.code();
	}
	
	public void setBinds(int[][] binds) {
		for (int i = 0; i < players.length; i++)
			players[i].controls(binds[i][0],binds[i][1],binds[i][2],binds[i][3],binds[i][4]);
	}
	public int[][] getBinds() {
		int[][] binds = new int[3][5];
		for (int i = 0; i < playerCt(); i++) {
			binds[i][0] = players[i].fb;
			binds[i][1] = players[i].bb;
			binds[i][2] = players[i].lb;
			binds[i][3] = players[i].rb;
			binds[i][4] = players[i].sb;
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 5; j++){
				System.out.println(binds[i][j]);
			}
		}
		return binds;
	}
	public void pause() {
		paused = true;
	}
	public void unpause() {
		paused = false;
	}
}
