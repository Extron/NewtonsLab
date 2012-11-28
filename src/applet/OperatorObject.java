package applet;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import sun.org.mozilla.javascript.internal.ast.AstNode;

import applet.ASTNodeObject.MagnetPoint;

import core.ASTNode;
import core.Operator;
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
		
		size = new Dimension(50, 65);
		
		magnetPoints.add(new MagnetPoint(new Vector2(0, 32.5), 1));
		magnetPoints.add(new MagnetPoint(new Vector2(50, 32.5), 2));
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
	
	@Override
	public void ConnectNode(ASTNodeObject obj, MagnetPoint point)
	{
		if (obj.node.GetParent() == null)
		{
			if (point.pos == magnetPoints.get(0).pos)
				((Operator)node).SetLeftNode(obj.node);
			else if (point.pos == magnetPoints.get(1).pos)
				((Operator)node).SetRightNode(obj.node);
		}
		else
		{
			ASTNode parent = obj.node.GetParent();
			
			while (parent.GetParent() != null)
				parent = parent.GetParent();
			
			if (point.pos == magnetPoints.get(0).pos)
				((Operator)node).SetLeftNode(parent);
			else if (point.pos == magnetPoints.get(1).pos)
				((Operator)node).SetRightNode(parent);
		}
	}
}
