import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


@SuppressWarnings("serial")
public class HelpPanel extends JFrame {
	
	private JLabel label1;
	private JLabel label2;

	    public HelpPanel() {
		// TODO Auto-generated constructor stub
		super("Tank Trouble Help");
		
		Container container = getContentPane();
        	container.setLayout( new BorderLayout() );
        	
        	label1 = new JLabel("Player 1: Use \"E\" to move forward, \"D\" to move backward, \"S\" to turn left, \"F\" to turn right, and \"Q\" to shoot bullets(a max of 5 at a time)\nPlayer 1: Use the up arrow to move forward, the down arrow to move backward, the left arrow to turn left, the right arrow to turn right, and \"/\" to shoot bullets(a max of 5 at a time)");
        	label2 = new JLabel("The cross indicates where the bullet will spawn. (Yes, you can shoot across walls if you are close enough)");
        	
        	container.add(label1, BorderLayout.CENTER);
        	container.add(label1, BorderLayout.SOUTH);
        	
        	addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                System.exit(0);
            }
        });
        
        setSize( 275, 275);
        setVisible(true);
        
	    }
	    
         public static void main(String[] args) {
        HelpPanel application = new HelpPanel();
    }
		//setBackground(Color.BLUE);
		//setBounds(new Rectangle(500, 200));
		
		//JOptionPane helpText = new JOptionPane("Player 1: Use arrow keys to move and press \"/\" to fire!\n Player 2: Use esdf to move and press q to fire!");
		//add(helpText);
	}

