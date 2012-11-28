package applet;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import applet.ASTNodeObject.MagnetPoint;

import core.Operator;
import core.ValueNode;
import core.Vector2;

public class ValueObject extends ASTNodeObject
{
	boolean clicked = false;
	
	/**
	 * Creates a new GUI object for a value node.
	 * 
	 * @param v The value node to create from.
	 */
	public ValueObject(ValueNode v)
	{
		this.node = v;
		
		size = new Dimension(75, 75);
		
		magnetPoints.add(new MagnetPoint(new Vector2(0, 37.5), -2));
		magnetPoints.add(new MagnetPoint(new Vector2(75, 37.5), -1));
	}
	
	@Override
	public void Draw(Graphics graphics)
	{
		graphics.setColor(Color.black);
		graphics.drawString(node.GetName(), (int)position.x, (int)position.y - 5);
		
		graphics.setColor(Color.blue);
		
		graphics.fillRect((int)position.x, (int)position.y, size.width, size.height);
		
		graphics.setColor(Color.white);
		
		String s = "" + ((ValueNode)node).GetValue();
		graphics.drawString(s.substring(0, Math.min(s.length(), 9)), (int)position.x + 12, (int)position.y + 12);
	}
	
	public void ConnectNode(ASTNodeObject obj, MagnetPoint point)
	{
		if (obj instanceof OperatorObject)
		{
			obj.ConnectNode(this, point);
		}
	}
}
