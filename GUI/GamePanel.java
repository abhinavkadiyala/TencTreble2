
import javax.swing.*;

import component.*;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements ActionListener, KeyListener {

	Game game;
	Timer timer;
	private static final int DELAY_MS = 20;

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
		game = new Game(pct);
		if (timer != null) timer.stop();
		timer = new Timer(DELAY_MS, this);
		timer.start();
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
		game.update();
		repaint();
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.translate(Game.CELL_SIZE, Game.CELL_SIZE);
		game.paint((Graphics2D) g);
	}

}
