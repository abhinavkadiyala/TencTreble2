
import javax.swing.JPanel;

import component.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements ActionListener, KeyListener {

	Game game;
	
	/**
	 * Create the panel.
	 */
	public GamePanel() {

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
		Graphics2D g2d = (Graphics2D) g;
		for (GameObject obj : game.getMap().objects()) {
			obj.paint(g2d);
		}
	}

}
