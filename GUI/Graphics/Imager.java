package graphics;

import java.awt.Image;
import javax.swing.ImageIcon;


public class Imager {
	public static Image getImage(String fileName, ClassLoader loader)
	{
		Image image;
		image = new ImageIcon(loader.getResource("res" + "/" + fileName)).getImage();
		return image;
	}
}
