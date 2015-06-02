import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.codemodel.internal.JCommentPart;


public class HelpPanel extends JPanel implements ActionListener, KeyListener{

	    public HelpPanel() {
		// TODO Auto-generated constructor stub
		super();
		setBackground(Color.BLUE);
		setBounds(new Rectangle(50, 50));
		
		JTextField helpText = new JTextField("Use arrow keys to move and press m to fire!");
		
		add(helpText);
		setVisible(true);
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
}
