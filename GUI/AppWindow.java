
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;


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
		// DON'T FORGET TO INCLUDE THIS CODE - otherwise you will not
        // be able to close your application!!!
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                System.exit(0);
            }
        });
        // construction a Swing timer that goes off every 1000 msec (1 sec)
        Timer timer = new javax.swing.Timer(1000, this);
        timer.start();		// timer starts here
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
		mnGame.add(mntmNewGame);
		frame.getContentPane().setLayout(null);
		
	}
	
	public void newGame() {
		Object[] sv = {2};
		int p = (int) JOptionPane.showInputDialog(frame, "How many players?", "New Game", JOptionPane.PLAIN_MESSAGE, null, sv, 2);
		frame.getContentPane().removeAll();
		GamePanel gp = new GamePanel(p);
		frame.getContentPane().add(gp);
		frame.getContentPane().addKeyListener(gp);
	}
	
	public void paint (Graphics g )
     {
     	// call superclass's paint method
     	super.paint(g);
     	
     	// draw a red line; 2 blue rectangles, one filled and one
     	// not; and 2 magenta ovals, one filled and one not
     	
     	g.setColor(Color.red);
     	g.drawLine(5, 30, 350, 30);
     	
     	g.setColor(Color.blue);
     	g.drawRect(5, 40, 90, 55);
     	g.fillRect(100, 40, 90, 55);
     	
     	g.setColor(Color.magenta);
     	g.drawOval(195, 100, 90, 55);
     	g.fillOval(290, 100, 90, 55);
     	
		// location of fish changes each time the timer goes off
     	//g.drawImage(fish, fishX, fishY, this);	// 'fish' was loaded in constructor
     }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
