package applet;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The main panel component that holds all of the GUI components for the applet.
 * 
 * @author Trystan Binkley-Jones
 *
 */
public class NLRootPanel extends JPanel
{
	/**
	 * This is the default constructor for the panel, and it is here where the GUI will be created.
	 */
	public NLRootPanel()
	{
		//Note that a JLabel can have an icon attached to it.  We may want one.
		JLabel title = new JLabel("Newton's Lab");
		title.setFont(new Font("Arial", Font.BOLD, 36));
		
		add(title);
	}
}
