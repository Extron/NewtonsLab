package applet;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import core.ASTNode;
import core.Vector2;

/**
 * This is a technical node that allows users to specify what actually gets plugged into the puzzle.
 * 
 * @author Trystan Binkley-Jones
 *
 */
public class EqualsObject extends ASTNodeObject
{
	/**
	 * The connected node.  This will be delivered to the function to be used in the puzzle.
	 */
	ASTNode node;
	
	/**
	 * Creates a GUI object to use to draw the input node.
	 */
	public EqualsObject()
	{
		size = new Dimension(50, 50);
		
		magnetPoints.add(new MagnetPoint(new Vector2(50, 25), 2));
	} 
	
	
	@Override
	public void Draw(Graphics graphics)
	{
		graphics.setColor(new Color(0.0f, 0.5f, 0.0f));
		graphics.fillRect((int)position.x, (int)position.y, size.width, size.height);
		
		graphics.setColor(Color.white);
		
		graphics.drawString("=", (int)position.x + 20, (int)position.y + 20);
	}
	
	@Override
	public void ConnectNode(ASTNodeObject obj, MagnetPoint point)
	{
		node = obj.node;
		
		while (node.GetParent() != null)
			node = node.GetParent();
	}
}
