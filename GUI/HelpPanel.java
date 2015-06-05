import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


@SuppressWarnings("serial")
public class HelpPanel extends JPanel {

	    public HelpPanel() {
		// TODO Auto-generated constructor stub
		super();
		//setBackground(Color.BLUE);
		//setBounds(new Rectangle(500, 200));
		
		JOptionPane helpText = new JOptionPane("Use arrow keys to move and press m to fire!");
		helpText.setVisible(true);
		add(helpText);
		setVisible(true);
	}
}
