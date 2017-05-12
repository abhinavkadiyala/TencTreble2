package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import game.Game;
import game.Player;


@SuppressWarnings("serial")
public class GamePanel extends JPanel implements ActionListener, KeyListener {

	Game game;
	Timer timer;
	private static final int DELAY_MS = 20;
	boolean waitNewGame;
	long opTime;
	ScoreDisplay scoreDisp;
	static int[] pauseDim = { 2, 2, 4};
	static int[][] playDim = {
			{ 2, 2, 12 },
			{ 2, 12, 7 }
	};

	/**
	 * Create the panel.
	 */
	public GamePanel(int pct) {
		setLayout(new BorderLayout(0, 0));
		game = new Game(pct);
		scoreDisp = new ScoreDisplay(this);
		this.add(scoreDisp, BorderLayout.NORTH);
		repaint();
	}

	public GamePanel() {
		setLayout(new BorderLayout(0, 0));
		game = new Game(0);
		scoreDisp = new ScoreDisplay(this);
		FlowLayout flowLayout = (FlowLayout) scoreDisp.getLayout();
		flowLayout.setHgap(25);
		this.add(scoreDisp, BorderLayout.NORTH);
		repaint();
	}

	public void newGame(int pct) {
		this.setLayout(new BorderLayout());
		if (game != null && game.getPlayers() != null
				&& game.getPlayers().length == pct && waitNewGame) {
			game = new Game(game.getPlayers());
		} else {
			game = new Game(pct);
		}
		scoreDisp.update();
		repaint();
		if (timer != null)
			timer.stop();
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
		// if (t > 0) System.out.println(t);
		if (t > 3000) {
			Player[] players = game.getPlayers();
			java.util.List<Player> winner = new ArrayList<>();
			for (Player play : players) {
				if (!(play.getTank().getMap() == null)) {
					winner.add(play);
				}
			}
			if (winner.size() == 1) {
				winner.get(0).incrementScore();
			}
			newGame(game.playerCt());
			return;
		}
		if (!game.paused)
		try {
			game.update();
		} catch (Exception e) {
			/*
			 * Suggestion:
			 * Add a special exception class so that regular
			 * exceptions won't cause unintended behavior
			 */
			if (!waitNewGame)
				opTime = System.currentTimeMillis();
			waitNewGame = true;
		}
		repaint();
	}

	public void paint(Graphics g) {
		super.paint(g);
		if (paused()) {
			g.drawPolygon(playDim[0], playDim[1], 3);
		} else {
			g.drawRect(pauseDim[0], pauseDim[1],
				pauseDim[2], pauseDim[2]*3);
			g.drawRect(pauseDim[0] + 2*pauseDim[2], pauseDim[1],
				pauseDim[2], pauseDim[2]*3);
		}
		g.translate(Game.CELL_SIDE, Game.CELL_SIDE);
		game.paint((Graphics2D) g);
		// scoreDisp.paint(g);
	}

	public String code() {
		return game.code();
	}

	public void newGame(String code, int pct) {
		this.setLayout(new BorderLayout());
		if (game != null && game.getPlayers() != null
				&& game.getPlayers().length == pct && waitNewGame) {
			game = new Game(code, game.getPlayers());
		} else {
			game = new Game(code, pct);
		}
		scoreDisp.update();
		repaint();
		if (timer != null)
			timer.stop();
		timer = new Timer(DELAY_MS, this);
		timer.start();
		waitNewGame = false;
		opTime = Long.MAX_VALUE;
	}
	
	public void pause() {
		game.pause();
	}
	public void unpause() {
		game.unpause();
	}
	public boolean paused() {
		return game.paused;
	}

}

@SuppressWarnings("serial")
class ScoreDisplay extends JPanel {
	GamePanel game;
	static final String[] title = { "Tank", " ", "Trouble" };

	public ScoreDisplay() {
		for (int i = 0; i < 3; i++)
			this.add(new JLabel(title[i]));
	}

	public ScoreDisplay(GamePanel gp) {
		game = gp;
		for (int i = 0; i < 3; i++) {
			JLabel fd = new JLabel(title[i]);
			fd.setFont(new Font("tahoma", Font.BOLD, 15));
			this.add(fd);
		}
	}

	public void update() {
		Component[] cs = getComponents();
		Player[] players = new Player[0];
		if (game != null && game.game != null && game.game.getPlayers() != null)
			players = game.game.getPlayers();
		for (int i = 0; i < cs.length; i++)
			try {
				((JLabel) cs[i]).setText(players[i].getName() + ": "
						+ players[i].getScore());
				((JLabel) cs[i]).setForeground(players[i].getTank().getColor()
						.darker());
			} catch (IndexOutOfBoundsException e) {
				((JLabel) cs[i]).setText("");
			}
	}

}