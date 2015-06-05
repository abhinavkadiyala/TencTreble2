import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


@SuppressWarnings("serial")
public class HelpPanel extends JFrame {

	    public HelpPanel() {
		// TODO Auto-generated constructor stub
		super();
		//setBackground(Color.BLUE);
		//setBounds(new Rectangle(500, 200));
		
		JOptionPane helpText = new JOptionPane("Player 1: Use arrow keys to move and press \"/\" to fire!\n Player 2: Use esdf to move and press q to fire!");
		add(helpText);
	}
}
