package gui;

import static java.awt.event.KeyEvent.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import game.Game;

@SuppressWarnings("serial")
public class ControlPanel extends JDialog {

	static final Map<Integer, String> keyMap;
	public int[][] binds = new int[3][5];
	private JButton[][] buttons = new JButton[3][5];
	private GamePanel game;

	static {
		keyMap = new TreeMap<>();
		Field[] fields = KeyEvent.class.getDeclaredFields();
		for (Field f : fields) {
			if (Modifier.isStatic(f.getModifiers()) && Modifier.isPublic(f.getModifiers())) {
				int value = 0;
				try {
					value = f.getInt(KeyEvent.class);
				} catch (IllegalArgumentException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				}
				try {
					keyMap.put(value, f.getName().replaceAll("(VK_)", "").replaceAll("NUMPAD", "#"));
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}
			}
		}
		// Short descriptions
		keyMap.put(VK_BACK_SLASH, "\\");
		keyMap.put(VK_BACK_QUOTE, "`");
		keyMap.put(VK_BRACELEFT, "{");
		keyMap.put(VK_BRACERIGHT, "}");
		keyMap.put(VK_CLOSE_BRACKET, "]");
		keyMap.put(VK_COMMA, ",");
		keyMap.put(VK_DELETE, "DEL");
		keyMap.put(VK_DOWN, "\u2193");
		keyMap.put(VK_ENTER, "\u23ce");
		keyMap.put(VK_ESCAPE, "ESC");
		keyMap.put(VK_EQUALS, "=");
		keyMap.put(VK_INSERT, "INS");
		keyMap.put(VK_LEFT, "\u2190");
		keyMap.put(VK_MINUS, "-");
		keyMap.put(VK_OPEN_BRACKET, "[");
		keyMap.put(VK_PAGE_DOWN, "PG\u2193");
		keyMap.put(VK_PAGE_UP, "PG\u2191");
		keyMap.put(VK_PERIOD, ".");
		keyMap.put(VK_PLUS, "+");
		keyMap.put(VK_QUOTE, "'");
		keyMap.put(VK_RIGHT, "\u2192");
		keyMap.put(VK_SEMICOLON, ";");
		keyMap.put(VK_SHIFT, "\u21e7");
		keyMap.put(VK_SLASH, "/");
		keyMap.put(VK_UP, "\u2191");
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			// Arrays.fill(Game.dKey, 0);
			ControlPanel dialog = new ControlPanel(Game.dKey);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ControlPanel(GamePanel g) {
		this(g.game != null ? Game.dKey : g.game.getBinds());
		game = g;
	}

	public ControlPanel(int[][] dbs) {
		for (int i = 0; i < 3; i++) {
			binds[i] = dbs[i].clone();
		}

		// binds = dbs;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				/* okButton.setActionCommand("OK"); */
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (game.game != null)
							game.game.setBinds(binds);
						dispose();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton resetButton = new JButton("Defaults");
				resetButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						binds = Game.dKey;
						repaint();
					}
				});
				buttonPane.add(resetButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				// cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
		{
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

			getContentPane().add(tabbedPane, BorderLayout.CENTER);
			JPanel[] p = new JPanel[3];
			for (int i = 0; i < 3; i++) {
				p[i] = new JPanel();
				p[i].setBorder(new EmptyBorder(5, 5, 5, 5));
				tabbedPane.addTab("Player " + (i + 1), null, p[i], null);
				p[i].setLayout(new GridLayout(2, 4, 1, 1));
				buttons[i][0] = new JButton("Forward: " + keyMap.get(binds[i][0]));
				buttons[i][0].addActionListener(new KeyLogger(i, 0) {
					public void actionPerformed(ActionEvent e) {
						ControlWindow cw = new ControlWindow(binds[x][y]);
						cw.addKeyListener(cw);
						cw.setVisible(true);
						cw.addWindowListener(new WindowAdapter() {
							@Override
							public void windowClosed(WindowEvent e) {
								fors: for (int m = 0; m < 3; m++)
									for (int n = 0; n < 5; n++)
										if (binds[m][n] == cw.getKey()) {
											binds[m][n] = binds[x][y];
											break fors;
										}
								binds[x][y] = cw.getKey();
								repaint();
							}
						});
						cw.addWindowFocusListener(new WindowAdapter() {

							@Override
							public void windowLostFocus(WindowEvent e) {
								e.getWindow().dispose();
							}
						});
					}
				});
				buttons[i][1] = new JButton("Back: " + keyMap.get(binds[i][1]));
				buttons[i][1].addActionListener(new KeyLogger(i, 1) {
					public void actionPerformed(ActionEvent e) {
						ControlWindow cw = new ControlWindow(binds[x][y]);
						cw.addKeyListener(cw);
						cw.setVisible(true);
						cw.addWindowListener(new WindowAdapter() {
							@Override
							public void windowClosed(WindowEvent e) {
								fors: for (int m = 0; m < 3; m++)
									for (int n = 0; n < 5; n++)
										if (binds[m][n] == cw.getKey()) {
											binds[m][n] = binds[x][y];
											break fors;
										}
								binds[x][y] = cw.getKey();
								repaint();
							}
						});
						cw.addWindowFocusListener(new WindowAdapter() {

							@Override
							public void windowLostFocus(WindowEvent e) {
								e.getWindow().dispose();
							}
						});
					}
				});
				buttons[i][2] = new JButton("Left: " + keyMap.get(binds[i][2]));
				buttons[i][2].addActionListener(new KeyLogger(i, 2) {
					public void actionPerformed(ActionEvent e) {
						ControlWindow cw = new ControlWindow(binds[x][y]);
						cw.addKeyListener(cw);
						cw.setVisible(true);
						cw.addWindowListener(new WindowAdapter() {
							@Override
							public void windowClosed(WindowEvent e) {
								fors: for (int m = 0; m < 3; m++)
									for (int n = 0; n < 5; n++)
										if (binds[m][n] == cw.getKey()) {
											binds[m][n] = binds[x][y];
											break fors;
										}
								binds[x][y] = cw.getKey();
								repaint();
							}
						});
						cw.addWindowFocusListener(new WindowAdapter() {

							@Override
							public void windowLostFocus(WindowEvent e) {
								e.getWindow().dispose();
							}
						});
					}
				});
				buttons[i][3] = new JButton("Right: " + keyMap.get(binds[i][3]));
				buttons[i][3].addActionListener(new KeyLogger(i, 3) {
					public void actionPerformed(ActionEvent e) {
						ControlWindow cw = new ControlWindow(binds[x][y]);
						cw.addKeyListener(cw);
						cw.setVisible(true);
						cw.addWindowListener(new WindowAdapter() {
							@Override
							public void windowClosed(WindowEvent e) {
								fors: for (int m = 0; m < 3; m++)
									for (int n = 0; n < 5; n++)
										if (binds[m][n] == cw.getKey()) {
											binds[m][n] = binds[x][y];
											break fors;
										}
								binds[x][y] = cw.getKey();
								repaint();
							}
						});
						cw.addWindowFocusListener(new WindowAdapter() {

							@Override
							public void windowLostFocus(WindowEvent e) {
								e.getWindow().dispose();
							}
						});
					}
				});
				buttons[i][4] = new JButton("Fire: " + keyMap.get(binds[i][4]));
				buttons[i][4].addActionListener(new KeyLogger(i, 4) {
					public void actionPerformed(ActionEvent e) {
						ControlWindow cw = new ControlWindow(binds[x][y]);
						cw.addKeyListener(cw);
						cw.setVisible(true);
						cw.addWindowListener(new WindowAdapter() {
							@Override
							public void windowClosed(WindowEvent e) {
								fors: for (int m = 0; m < 3; m++)
									for (int n = 0; n < 5; n++)
										if (binds[m][n] == cw.getKey()) {
											binds[m][n] = binds[x][y];
											break fors;
										}
								binds[x][y] = cw.getKey();
								repaint();
							}
						});
						cw.addWindowFocusListener(new WindowAdapter() {

							@Override
							public void windowLostFocus(WindowEvent e) {
								e.getWindow().dispose();
							}
						});
					}
				});
				p[i].add(buttons[i][4]);
				p[i].add(new JLabel(""));
				p[i].add(buttons[i][0]);
				p[i].add(new JLabel(""));
				p[i].add(new JLabel(""));
				p[i].add(buttons[i][2]);
				p[i].add(buttons[i][1]);
				p[i].add(buttons[i][3]);
			}
		}

	}

	@Override
	public void repaint() {
		for (int i = 0; i < 3; i++) {
			buttons[i][0].setText("Forward: " + keyMap.get(binds[i][0]));
			buttons[i][1].setText("Back: " + keyMap.get(binds[i][1]));
			buttons[i][2].setText("Left: " + keyMap.get(binds[i][2]));
			buttons[i][3].setText("Right: " + keyMap.get(binds[i][3]));
			buttons[i][4].setText("Fire: " + keyMap.get(binds[i][4]));
		}
		super.repaint();
	}

}

abstract class KeyLogger implements ActionListener {
	int x, y;

	public KeyLogger(int i, int j) {
		x = i;
		y = j;
	}
}

@SuppressWarnings("serial")
class ControlWindow extends JFrame implements KeyListener {
	int val;
	JPanel panel;
	final static Set<Integer> bindingDisabled;

	static {
		bindingDisabled = new TreeSet<Integer>();
		bindingDisabled.add(VK_ALT);
		bindingDisabled.add(VK_CAPS_LOCK);
		bindingDisabled.add(VK_CONTROL);
		bindingDisabled.add(VK_META);
		bindingDisabled.add(VK_WINDOWS);
	}

	public ControlWindow(int key) {
		val = key;
		this.setBounds(0, 0, 0, 0);
		this.setFocusable(true);
		this.setAlwaysOnTop(true);
		panel = new JPanel();
		panel.add(new JLabel(ControlPanel.keyMap.get(val)));
		getContentPane().add(panel);
	}

	public int close() {
		dispose();
		return val;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		int kc = arg0.getKeyCode();
		if (kc != VK_ESCAPE && !bindingDisabled.contains(kc))
			val = arg0.getKeyCode();
		if (!bindingDisabled.contains(kc))
			close();
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	public int getKey() {
		return val;
	}
}