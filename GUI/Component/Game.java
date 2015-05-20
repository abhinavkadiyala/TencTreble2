import java.awt.event.*;
import java.util.*;

public class Game implements KeyListener
{
    Player[] players;
    int level;      //what is this?
    Map map;
    HashSet<GameObject> obj;
    
    public void keyPressed(KeyEvent key) {
        for (Player p : players) p.keyPressed(key.getKeyCode());
    }
    public void keyReleased(KeyEvent key) {
        for (Player p : players) p.keyReleased(key.getKeyCode());
    }
    public void keyTyped(KeyEvent key) {return;}
    public void update() {
        for (GameObject go : obj)
            go.update();
    }
    public void add (GameObject gObj){
        obj.add(gObj);
    }
    public void remove (GameObject obj){
        obj.remove(gObj);
    }
}
