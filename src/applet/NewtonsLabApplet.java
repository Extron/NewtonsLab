package applet;

import javax.swing.JApplet;
import javax.swing.SwingUtilities;

/**
 * The main applet class that manages the program and enables browser and Swing support.
 * 
 * @author Trystan Binkley-Jones
 *
 */
public class NewtonsLabApplet extends JApplet
{	
	/**
	 * This is called when the applet is loaded in a browser, and allows us to initialize the applet.
	 */
	public void init()
	{
		setSize(500, 500);
		
		try 
		{
			//This is a bit complicated, but what it is essentially doing is invoking a Runnable class object,
			//which we dynamically define here.  Any Runnable object has to have a run() method, where we actually run 
			//the main pane (which holds all GUI components).  Here, we are dynamically defining what the run method
			//for our Runnable object looks like.
            SwingUtilities.invokeAndWait(new Runnable() 
            {
                public void run() {
                	NLRootPanel pane = new NLRootPanel();
                	pane.setOpaque(true); 
                    setContentPane(pane); 
                }
            });
        } 
		catch (Exception e) 
		{ 
            System.err.println("Could not create Swing GUI for the applet.");
        }
	}
	
	/**
	 * This is where the actual applet is started.  
	 */
	public void start()
	{
		
	}
	
	/**
	 * This is called when the applet needs to be stopped.
	 */
	public void stop()
	{
		
	}
	
	/**
	 * This is where all of the applet unloading should take place.
	 */
	public void destroy()
	{
		
	}
}