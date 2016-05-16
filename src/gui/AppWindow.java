package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.JCheckBoxMenuItem;

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
		/*
		 * mntmClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,
		 * InputEvent.ALT_MASK));
		 */

		JMenu mnMaze = new JMenu("Maze");
		mnGame.add(mnMaze);

		JMenuItem mntmImportMaze = new JMenuItem("Import Maze");
		mntmImportMaze
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mntmImportMaze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				importMaze();
			}
		});
		mnMaze.add(mntmImportMaze);

		JMenuItem mntmExportMaze = new JMenuItem("Export Maze");
		mntmExportMaze
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mntmExportMaze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportMaze();
			}
		});
		mnMaze.add(mntmExportMaze);

		JMenuItem mntmPause = new JMenuItem("Pause");
		mntmPause.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0));
		mntmPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (game().paused()) {
					game().unpause();
					mntmPause.setText("Pause");
				} else {
					game().pause();
					mntmPause.setText("Unpause");
				}
			}
		});
		mnGame.add(mntmPause);

		JMenuItem mntmClose = new JMenuItem("Close");
		mntmClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnGame.add(mntmClose);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmControls = new JMenuItem("Controls");
		mntmControls
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mnHelp.add(mntmControls);

		JMenuItem mntmMazeDetails = new JMenuItem("Maze Details");
		mntmMazeDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game().pause();
				JOptionPane.showMessageDialog(frame,
						"" + game().game.getMap().walls().width() + "x" + game().game.getMap().walls().height());
				game().unpause();
			}
		});
		mnHelp.add(mntmMazeDetails);

		mntmControls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controls();
			}
		});

		frame.setContentPane(new GamePanel());
		frame.addKeyListener(game());

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public void newGame() {
		game().pause();
		Object[] sv = { 2, 3 };
		Integer p = (Integer) JOptionPane.showInputDialog(frame, "How many players?", "New Game",
				JOptionPane.PLAIN_MESSAGE, null, sv, 2);
		game().unpause();
		if (p == null)
			return;
		game().newGame(p);
	}

	public void controls() {
		game().pause();
		ControlPanel cp = new ControlPanel(game());
		cp.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				game().unpause();
			}

			@Override
			public void windowClosed(WindowEvent e) {
				game().unpause();
			}
		});
		cp.setVisible(true);
	}

	public void importMaze() {
		game().pause();
		Integer p = game().game.getPlayers().length;
		if (p < 2) {
			Object[] sv = { 2, 3 };
			p = (Integer) JOptionPane.showInputDialog(frame, "How many players?", "New Game", JOptionPane.PLAIN_MESSAGE,
					null, sv, 2);
			if (p == null)
				return;
		}
		String c = (String) JOptionPane.showInputDialog(frame, "Maze code:", "Import Maze", JOptionPane.PLAIN_MESSAGE);
		if (c == null) {
			game().unpause();
			return;
		}
		try {
			game().newGame(c, p);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Your code is invalid.");
		} catch (StackOverflowError e) {
			JOptionPane.showMessageDialog(frame, "Your code gives an invalid maze");
		} finally {
			game().unpause();
		}
	}

	public void exportMaze() {
		game().pause();
		JOptionPane.showInputDialog(frame, "Maze Code", "Export Maze", JOptionPane.INFORMATION_MESSAGE, null, null,
				game().code());
		game().unpause();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		game().actionPerformed(e);
	}

	public GamePanel game() {
		return (GamePanel) frame.getContentPane();
	}
}
