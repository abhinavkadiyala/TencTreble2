package component;

import java.awt.event.*;
import java.util.*;

public class Game implements KeyListener
{
    Player[] players;
    Map map;
    
    public Game(int pct) {
        players = new Player[pct];
        map = new Map();
    }
    public Game(Map mp, Player... ps) {
        players = ps;
        map = mp;
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
}
