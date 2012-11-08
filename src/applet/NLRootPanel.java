package applet;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.TestPuzzle;

/**
 * The main panel component that holds all of the GUI components for the applet.
 * 
 * @author Trystan Binkley-Jones
 *
 */
public class NLRootPanel extends JPanel implements ActionListener
{
	/**
	 * The puzzle component that is being used for the applet.
	 */
	PuzzleComponent puzzle;
	
	/**
	 * This is the default constructor for the panel, and it is here where the GUI will be created.
	 */
	public NLRootPanel()
	{
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		//Note that a JLabel can have an icon attached to it.  We may want one.
		JLabel title = new JLabel("Newton's Lab");
		title.setFont(new Font("Arial", Font.BOLD, 36));
		
		puzzle = new PuzzleComponent(new TestPuzzle());
		
		JButton activate = new JButton("Activate puzzle.");
		activate.setActionCommand("activate");
		activate.addActionListener(this);
		
		add(title);
		add(puzzle);
		add(activate);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand() == "activate")
			puzzle.ActivatePuzzle();
	}
}
