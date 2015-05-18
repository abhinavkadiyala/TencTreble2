package Graphics;

import java.awt.Image;
import java.awt.event.*;

import javax.swing.ImageIcon;


public class Imager {
	
	public static Image getImage(String fileName, ClassLoader loader)
    {
        Image image;
        image = new ImageIcon(loader.getResource("res" + "/" + fileName)).getImage();
        return image;
    }
}
