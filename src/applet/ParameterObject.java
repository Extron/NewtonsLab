package applet;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import core.ParameterNode;
import core.ValueNode;

public class ParameterObject extends ASTNodeObject
{
	/**
	 * Creates a new GUI object for a value node.
	 * 
	 * @param v The value node to create from.
	 */
	public ParameterObject(ParameterNode p)
	{
		this.node = p;
		
		size = new Dimension(75, 75);
	}
	
	@Override
	public void Draw(Graphics graphics)
	{
		graphics.setColor(Color.black);
		graphics.drawString(node.GetName(), (int)position.x, (int)position.y - 5);
		
		graphics.setColor(Color.orange);
		
		graphics.fillRect((int)position.x, (int)position.y, size.width, size.height);
		
		graphics.setColor(Color.white);
		
		String s = "" + ((ParameterNode)node).GetValue();
		graphics.drawString(s.substring(0, Math.min(s.length(), 9)), (int)position.x + 12, (int)position.y + 12);
	}
	
	public void ConnectNode(ASTNodeObject obj, MagnetPoint point)
	{
	}
}
