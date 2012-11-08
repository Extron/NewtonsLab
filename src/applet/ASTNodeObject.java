package applet;

import java.awt.Graphics;

import core.ASTNode;
import core.Vector2;

/**
 * This class stores logic to draw the AST nodes to the screen.
 * 
 * @author Trystan Binkley-Jones
 *
 */
public abstract class ASTNodeObject
{
	/**
	 * The node to draw.
	 */
	protected ASTNode node;
	
	/**
	 * The position of the object.
	 */
	protected Vector2 position;
	
	
	/**
	 * Creates a new GUI object for an AST node.
	 */
	public ASTNodeObject()
	{
		position = new Vector2();
	}
	
	
	/**
	 * Draws the AST node to the screen.
	 * 
	 * @param graphics - The GUI's graphics helper.
	 */
	public abstract void Draw(Graphics graphics);
}
