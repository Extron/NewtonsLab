package applet;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import core.Hoop;
import core.Particle;
import core.PuzzleElement;
import core.Vector2;

public class HoopObject extends PuzzleObject
{

	public HoopObject(PuzzleElement element)
	{
		super(element);
		
		if (!(element instanceof Hoop))
			throw new IllegalArgumentException("Puzzle element is not of type Hoop.");
	}
	@Override
	public void Create(PuzzleElement element)
	{
	}

	@Override
	public void Draw(Graphics graphics, Vector2 viewport, double scale)
	{
		Vector2 pos1 = Vector2.Scale(Vector2.Add(Vector2.Scale(((Hoop)element).GetTopPos(), 1 / 10.0), viewport), scale);
		Vector2 pos2 = Vector2.Scale(Vector2.Add(Vector2.Scale(((Hoop)element).GetBottomPos(), 1 / 10.0), viewport), scale);
		
		graphics.setColor(Color.black);
		((Graphics2D)graphics).setStroke(new BasicStroke((int)Math.max(7.5 * scale, 1)));
		
		graphics.drawLine((int)pos1.x, (int)pos1.y, (int)pos2.x, (int)pos2.y);
	}

}
