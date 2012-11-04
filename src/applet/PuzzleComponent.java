package applet;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JComponent;

import core.Puzzle;
import core.PuzzleElement;

/**
 * This is where we will display a puzzle.  By extending the <code>JComponent</code> class, we can
 * plug this directly into the Swing GUI.
 * 
 * @author Trystan Binkley-Jones
 *
 */
public class PuzzleComponent extends JComponent
{
	/**
	 * A list of the objects in the puzzle to draw.
	 */
	ArrayList<PuzzleObject> objects;
	
	/**
	 * The current puzzle that is being drawn.
	 */
	Puzzle puzzle;
	
	
	/**
	 * Creates a new puzzle component.
	 * 
	 * @param puzzle - The current puzzle to display.
	 */
	public PuzzleComponent(Puzzle puzzle)
	{
		this.puzzle = puzzle;
		
		objects = new ArrayList<PuzzleObject>();
		
		InitElements();
	}
	
	
	/**
	 * Allows the component to be drawn to the screen.
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		//Draw all of the puzzle objects.
		Iterator<PuzzleObject> iter = objects.iterator();
		
		while (iter.hasNext())
			iter.next().Draw(g);
	}
	
	/**
	 * Activates the puzzle, setting it to begin simulating.
	 */
	public void ActivatePuzzle()
	{
		puzzle.ActivatePuzzle();
	}
	
	/**
	 * Initializes all of the puzzle objects to display from the puzzle's elements.
	 */
	private void InitElements()
	{
		ArrayList<PuzzleElement> elements = puzzle.GetElements();
		
		Iterator<PuzzleElement> iter = elements.iterator();
		
		while (iter.hasNext())
			objects.add(PuzzleObjectFactory.CreateObject(iter.next()));
	}
}
