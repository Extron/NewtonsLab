package applet;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import core.Deliminator;
import core.Deliminator.DelimType;

public class DelimObject extends ASTNodeObject
{
	public DelimObject(Deliminator d)
	{
		node = d;
		
		size = new Dimension(25, 75);
	}
	
	@Override
	public void Draw(Graphics graphics)
	{
		graphics.setColor(Color.red);
		
		int a1 = 0;
		int a2 = 0;
		
		if (((Deliminator)node).GetType() == DelimType.open)
		{
			a1 = 90;
			a2 = 180;
		}
		else
		{
			a1 = 90;
			a2 = -180;
		}
		
		((Graphics2D)graphics).setStroke(new BasicStroke(4));
		
		graphics.drawArc((int)position.x + 2, (int)position.y, size.width, size.height, a1, a2);
	}

	@Override
	public void ConnectNode(ASTNodeObject obj, MagnetPoint point)
	{
		// TODO Auto-generated method stub

	}

}
