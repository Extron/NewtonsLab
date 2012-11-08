package applet;

import java.awt.Color;
import java.awt.Graphics;

import core.Add;
import core.Divide;
import core.Multiply;
import core.Operator;
import core.Power;
import core.Subtract;
import core.Vector2;

public class OperatorObject extends ASTNodeObject
{
	/**
	 * Creates a GUI object to use to draw operators.
	 * 
	 * @param o - The operator node to draw.
	 */
	public OperatorObject(Operator o)
	{
		node = o;
	}
	
	
	@Override
	public void Draw(Graphics graphics)
	{
		graphics.setColor(Color.black);
		graphics.drawString(node.GetName(), (int)position.x, (int)position.y - 5);
		
		graphics.setColor(Color.blue);
		graphics.fillRect((int)position.x, (int)position.y, 25, 25);
		
		graphics.setColor(Color.white);
		
		String op = "";
		
		if (node instanceof Add)
			op = "+";
		else if (node instanceof Subtract)
			op = "-";
		if (node instanceof Multiply)
			op = "*";
		if (node instanceof Divide)
			op = "/";
		if (node instanceof Power)
			op = "^";
		
		graphics.drawString(op, (int)position.x + 12, (int)position.y + 12);
	}
}
