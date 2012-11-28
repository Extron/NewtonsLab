package applet;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import applet.ASTNodeObject.MagnetPoint;

import core.Function;
import core.Operator;

public class FunctionObject extends ASTNodeObject
{
	/**
	 * Creates a GUI object to use to draw operators.
	 * 
	 * @param o - The operator node to draw.
	 */
	public FunctionObject(Function f)
	{
		node = f;
		
		size = new Dimension(25, 35);
	}
	
	@Override
	public void Draw(Graphics graphics)
	{
		graphics.setColor(Color.black);
		graphics.drawString(node.GetName(), (int)position.x, (int)position.y - 5);
		
		graphics.setColor(Color.red);
		graphics.fillRect((int)position.x, (int)position.y, size.width, size.height);
		
		graphics.setColor(Color.white);
		
		graphics.drawString(node.GetSymbol(), (int)position.x + 12, (int)position.y + 12);
	}

	public void ConnectNode(ASTNodeObject obj, MagnetPoint point)
	{
	}
}
