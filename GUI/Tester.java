import java.awt.event.KeyEvent;

import javax.swing.JPanel;

public class Tester {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		System.out.println(new KeyEvent(new JPanel(),0,1,0,KeyEvent.VK_NUMPAD2));
	}

}
