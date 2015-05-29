package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class AppWindow implements ActionListener {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppWindow window = new AppWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AppWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Tank Trouble");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnGame = new JMenu("Game");
		mnGame.setMnemonic('g');
		menuBar.add(mnGame);
		
		JMenuItem mntmNewGame = new JMenuItem("New Game");
		mntmNewGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mntmNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newGame();
			}
		});
		mnGame.add(mntmNewGame);
		
		frame.setContentPane(new GamePanel());
		frame.getContentPane().setLayout(null);
		frame.addKeyListener((KeyListener) frame.getContentPane());
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	public void newGame() {
		Object[] sv = {2};
<<<<<<< HEAD
		Integer p = (Integer) JOptionPane.showInputDialog(frame, "How many players?", "New Game", JOptionPane.PLAIN_MESSAGE, null, sv, 2);
		if (p == null) return;
		frame.getContentPane().removeAll();
		GamePanel gp = new GamePanel(p);
		frame.getContentPane().add(gp);
		frame.getContentPane().addKeyListener(gp);
=======
		int p = (int) JOptionPane.showInputDialog(frame, "How many players?", "New Game", JOptionPane.PLAIN_MESSAGE, null, sv, 2);
		((GamePanel) frame.getContentPane()).newGame(p);
		
>>>>>>> origin/master
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
