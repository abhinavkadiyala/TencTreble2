import java.awt.*;
import javax.swing.*;


@SuppressWarnings("serial")
public class HelpPanel extends JFrame {

	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;

	public HelpPanel() {
		// TODO Auto-generated constructor stub
		super("Tank Trouble Help");

		Container container = getContentPane();
		container.setLayout( new BoxLayout(container, 1) );

		label1 = new JLabel("Player 1: Use \"E\" to move forward, \"D\" to move backward, \"S\" to turn left, \"F\" to turn right, and \"Q\" to shoot bullets(a max of 5 at a time)");
		label2 = new JLabel("Player 2: Use the up arrow to move forward, the down arrow to move backward, the left arrow to turn left, the right arrow to turn right, and \"/\" to shoot bullets(a max of 5 at a time)");
		label3 = new JLabel("Player 3: Use the numpad 5 key to move forward, numpad 2 to move backward, numpad 1 to turn left, numpad 3 to turn right, and numpad 7 to shoot bullets. (a max of 5 at a time)");
		label4 = new JLabel("The goal of the game is to kill the other tank. The cross indicates where the bullet will spawn. (Yes, you can shoot across walls if you are close enough)");

		container.add(label1);
		container.add(label2);
		container.add(label3);
		container.add(label4);

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


		pack();
		setVisible(true);

	}
	//setBackground(Color.BLUE);
	//setBounds(new Rectangle(500, 200));

	//JOptionPane helpText = new JOptionPane("Player 1: Use arrow keys to move and press \"/\" to fire!\n Player 2: Use esdf to move and press q to fire!");
	//add(helpText);
}

