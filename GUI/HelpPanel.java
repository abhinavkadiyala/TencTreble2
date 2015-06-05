import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


@SuppressWarnings("serial")
public class HelpPanel extends JFrame {
	
	private JLabel label1;

	    public HelpPanel() {
		// TODO Auto-generated constructor stub
		super("Tank Trouble Help");
		
		Container container = getContentPane();
        	container.setLayout( new BorderLayout() );
        	
        	label1 = new JLabel("This is where the help info goes");
        	
        	container.add(label1, BorderLayout.CENTER);
        	
        	addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                System.exit(0);
            }
        });
        
        setSize( 275, 275);
        setVisible(true);
        
	    }
	    
         public static void main(String[] args) {
        Gui application = new Gui();
    }
		//setBackground(Color.BLUE);
		//setBounds(new Rectangle(500, 200));
		
		//JOptionPane helpText = new JOptionPane("Player 1: Use arrow keys to move and press \"/\" to fire!\n Player 2: Use esdf to move and press q to fire!");
		//add(helpText);
	}
}
