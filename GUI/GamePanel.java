
import javax.swing.*;
import javax.swing.Timer;

import component.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements ActionListener, KeyListener {

	Game game;
	Timer timer;
	private static final int DELAY_MS = 20;
	boolean waitNewGame;
	long opTime;
	ScoreDisplay scoreDisp;

	/**
	 * Create the panel.
	 */
	public GamePanel(int pct) {
		game = new Game(pct);
	}
	public GamePanel() {
		game = new Game(0);
	}
	public void newGame(int pct) {
		this.setLayout(new BorderLayout());
		if (game != null && game.getPlayers() != null && game.getPlayers().length == pct && waitNewGame) {
			game = new Game(game.getPlayers());
		}
		else {
			game = new Game(pct);
			scoreDisp = new ScoreDisplay(game.getPlayers());
			this.add(scoreDisp, BorderLayout.SOUTH);
		}
		scoreDisp.update();
		repaint();
		if (timer != null) timer.stop();
		timer = new Timer(DELAY_MS, this);
		timer.start();
		waitNewGame = false;
		opTime = Long.MAX_VALUE;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		game.keyPressed(arg0);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		game.keyReleased(arg0);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		game.keyTyped(arg0);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		long t = System.currentTimeMillis() - opTime;
		//if (t > 0) System.out.println(t);
		if (t > 3000) {
			Player[] players = game.getPlayers();
			java.util.List<Player> winner = new ArrayList<>();
			for (Player play: players){
				if (!(play.getTank().getMap() == null)){
					winner.add(play);
				}
			}
			if (winner.size() == 1){
				winner.get(0).incrementScore();
			}
			newGame(game.playerCt());
			return;
		}
		try {
			game.update();
		} catch (Exception e) {
			if (!waitNewGame) opTime = System.currentTimeMillis();
			waitNewGame = true;
		}
		repaint();
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.translate(Game.CELL_SIDE, Game.CELL_SIDE);
		game.paint((Graphics2D) g);
	}
	
	public String code() {
		return Game.code()
	}
	public void setCode(String code) {
		game = new Game(code);
		newGame();
	}

}

@SuppressWarnings("serial")
class ScoreDisplay extends JPanel {
	Player[] players;
	public ScoreDisplay(Player... ps) {
		players = ps;
		for (Player p : players) {
			this.add(new JLabel(p.getName() + ": " + p.getScore()));
		}
	}
	public void update() {
		Component[] cs = getComponents();
		for (int i = 0; i < cs.length; i++)
			if (cs[i] instanceof JLabel) ((JLabel) cs[i]).setText(players[i].getName()+": "+players[i].getScore());
	}
	
}
