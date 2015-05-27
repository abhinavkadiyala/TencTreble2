// IMPORT the following classes to access GUI functionality:
import java.awt.*;          // access to Container
import java.awt.event.*;    // access to WindowAdapter, WindowEvent
import javax.swing.*;       // access to JFrame and JComponents

// The class needs to extend JFrame; it also implements ActionListener to
// support use of the timer
public class Gui extends JFrame implements ActionListener{
	
	// instance variables
	private Image tank;
	
	private int counter;		// counts seconds
	private int tankX, tankY;	// location of tank on screen
	
	/** Creates a new instance of gui_test - sets up GUI */
    public Gui() {
        // STEP 1: must call super() first
        super("Tank Trouble");
    
    // STEPS 2-5: not needed here because this example does not
    // include any GUI "components"...
        // STEP 2: get content pane and set its layout
        // STEP 3: construct component(s), such as:     
        // STEP 4: add all components to the Container;
        // STEP 5: register any needed event handlers 

		// need to load up "tank" image used below
		ImageIcon tankIcon = new ImageIcon("BlueTank.gif");
		tank = tankIcon.getImage();
		
		int load = tankIcon.getImageLoadStatus();
				// 2 = aborted; 8 = complete; 4 = errored
		
        // DON'T FORGET TO INCLUDE THIS CODE - otherwise you will not
        // be able to close your application!!!
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                System.exit(0);
            }
        });
        
        // initial location of tank
        tankX = 300;	
        tankY = 40;
        
        // STEP 6: set window size and show window
        setSize( 400, 170);
        setVisible(true);
 
       	// construction a Swing timer that goes off every 1000 msec (1 sec)
        Timer timer = new javax.swing.Timer(1000, this);
        timer.start();		// timer starts here
                
    }
    
    /*
     * This is the graphics demo section.  When you want to use
     * any methods of the Graphics class, you need to call the
     * Component method paint(), which takes a Graphics object
     * as an argument.  The run-time system is in control of when
     * paint() executes, not the programmer, because drawing graphics
     * is "event-driven" (e.g. when a window is covered or uncovered).
     * If the programmer needs to call paint(), the way to do that is by calling the method
     * repaint() instead.  (See the use of repaint() in GuiTest3.)
     *
     * Here are just some of the Graphics methods:
     *  - public void setColor (Color c)
     *			// sets the current color - see Color class for
     *			// available predefined colors
     *	- public void drawLine(int x1, int y1, int x2, int y2)
     *			// draws line between (x1, y1) and (x2, y2)
     *  - public void drawRect (int x, int y, int width, int height)
     *			// draws rectangle with top left corner at (x,y)
     *  - public void fillRect (int x, int y, int width, int height)
     *			// draws a solid rectangle with top left corner at (x,y)
     *  - public void drawOval (int x, int y, int width, int height)
     *			// draws oval in current color with specified width
     *			// and height; bounding rectangle's top left corner
     *			// is at (x,y) and oval touches all sides of bounding
     *			// rectangle
     *	- public void fillOval (int x, int y, int width, int height)
     *			// see drawOval
     *	- public void drawImage (Image img, int x, int y, ImageObserver observer)
     *			// draws image loaded from a GIF, JPEG or PNG file - upper
     *			// left corner at (x, y) - usually make 'observer' be 'this'
     */
     public void paint (Graphics g )
     {
     	// call superclass's paint method
     	super.paint(g);
     	
     	// draw a red line; 2 blue rectangles, one filled and one
     	// not; and 2 magenta ovals, one filled and one not
     	
     	g.setColor(Color.red);
     	g.drawLine(5, 30, 350, 30);
     	
     	g.setColor(Color.blue);
     	g.drawRect(5, 40, 90, 55);
     	g.fillRect(100, 40, 90, 55);
     	
     	g.setColor(Color.magenta);
     	g.drawOval(195, 100, 90, 55);
     	g.fillOval(290, 100, 90, 55);
     	
		// location of tank changes each time the timer goes off
     	g.drawImage(tank, tankX, tankY, this);	// 'tank' was loaded in constructor
     }

	// this method is called each time the timer goes off     
	public void actionPerformed(ActionEvent evt) 
	{
		// move tank across and down screen
		if (tankX > 0)
			tankX -= 50;	// move tank to left a bit
		else
		{
			tankX = 300;	// move tank far to right and
			tankY +=30;		// down a bit
			if (tankY > 100)
				tankY = 40;	// put back closer to top
		}
		// need to tell the Repaint Manager that the tank has moved:
		repaint();
		
		// counting the seconds	
		counter++;
	}
     	
    public static void main(String[] args) {
        Gui application = new Gui();
    }
    	
}
