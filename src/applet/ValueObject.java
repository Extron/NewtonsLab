package applet;

import java.awt.Color;
import java.awt.Graphics;

import core.ValueNode;

public class ValueObject extends ASTNodeObject
{
	/**
	 * Creates a new GUI object for a value node.
	 * 
	 * @param v The value node to create from.
	 */
	public ValueObject(ValueNode v)
	{
		this.node = v;
	}
	
	@Override
	public void Draw(Graphics graphics)
	{
		graphics.setColor(Color.black);
		graphics.drawString(node.GetName(), (int)position.x, (int)position.y - 5);
		
		graphics.setColor(Color.green);
		graphics.fillRect((int)position.x, (int)position.y, 50, 50);
		
		graphics.setColor(Color.white);
		graphics.drawString("" + ((ValueNode)node).GetValue(), (int)position.x + 12, (int)position.y + 12);
	}

}
