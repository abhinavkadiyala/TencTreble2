package gui;

import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements ActionListener, KeyListener {

	Game game;
	
	/**
	 * Create the panel.
	 */
	public GamePanel(int pct) {
		setBackground(Color.LIGHT_GRAY);
		game = new Game(pct);
	}
	public GamePanel() {
		setBackground(Color.LIGHT_GRAY);
		game = new Game(0);
	}
	public void newGame(int pct) {
		game = new Game(pct);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		game.paint((Graphics2D) g);
	}

}
