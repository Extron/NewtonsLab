package applet;

import java.awt.Graphics;

import core.PuzzleElement;

/**
 * This manages the graphical display of puzzle objects, like masses. As this is an abstract base class, 
 * each different object (mass, spring, etc.) will override this class to allow it to be drawn.
 * 
 * @author Trystan Binkley-Jones
 *
 */
public abstract class PuzzleObject
{
	/**
	 * The corresponding element of the object.
	 */
	PuzzleElement element;
	
	
	/**
	 * Creates a new puzzle object from a corresponding element.
	 * 
	 * @param element - The element to create the object from.
	 */
	public PuzzleObject(PuzzleElement element)
	{
		this.element = element;
		
		Create(element);
	}
	
	/**
	 * Creates the puzzle object from a specified element, like a particle or spring.
	 * 
	 * @param element - The element to create from.
	 */
	public abstract void Create(PuzzleElement element);
	
	/**
	 * Draws the puzzle object to the screen.
	 * 
	 * @param graphics - The graphics object to draw with.
	 */
	public abstract void Draw(Graphics graphics);
}
