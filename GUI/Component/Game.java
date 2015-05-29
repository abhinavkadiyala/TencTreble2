package component;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Game implements KeyListener
{
	public static int CELL_SIDE = 20;
	private static final int[][] dKey = {
		{KeyEvent.VK_UP     , KeyEvent.VK_DOWN   , KeyEvent.VK_LEFT   , KeyEvent.VK_RIGHT  , KeyEvent.VK_SLASH  },
		{KeyEvent.VK_E      , KeyEvent.VK_D      , KeyEvent.VK_S      , KeyEvent.VK_F      , KeyEvent.VK_Q      },
		{KeyEvent.VK_NUMPAD5, KeyEvent.VK_NUMPAD2, KeyEvent.VK_NUMPAD1, KeyEvent.VK_NUMPAD3, KeyEvent.VK_NUMPAD7}
	};
	Player[] players;
	Map map;

	private void initPlayers() {
		for (int i = 0; i < players.length; i++) {
			players[i] = new Player("Player "+(i-1),dKey[i][0],dKey[i][1],dKey[i][2],dKey[i][3],dKey[i][4]);
			players[i].makeTank(null, map);	//location = null until a maze is created
		}
	}
	public Game(int pct) {
		players = new Player[pct];
		map = new Map();
		initPlayers();
	}
	public Game(Map mp, Player... ps) {
		players = ps;
		map = mp;
		initPlayers();
	}

	public void keyPressed(KeyEvent key) {
		for (Player p : players) p.keyPressed(key.getKeyCode());
	}
	public void keyReleased(KeyEvent key) {
		for (Player p : players) p.keyReleased(key.getKeyCode());
	}
	public void keyTyped(KeyEvent key) {return;}
	public void update() {
		Set<GameObject> obj = map.getObjects();
		for (GameObject go : obj)
			go.update();
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
}
