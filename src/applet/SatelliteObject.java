package applet;

import java.awt.Color;
import java.awt.Graphics;

import core.Particle;
import core.PuzzleElement;
import core.Satellite;
import core.Vector2;

public class SatelliteObject extends PuzzleObject
{

	public SatelliteObject(PuzzleElement element)
	{
		super(element);
		
		if (!(element instanceof Satellite))
			throw new IllegalArgumentException("Puzzle element is not of type Satellite.");
	}

	@Override
	public void Create(PuzzleElement element)
	{
	}

	@Override
	public void Draw(Graphics graphics, Vector2 viewport, double scale)
	{
		Vector2 pos = Vector2.Scale(Vector2.Add(Vector2.Scale(((Satellite)element).GetPosition(), 1 / 10.0), viewport), scale);
		
		graphics.setColor(Color.blue);
		graphics.fillOval((int)pos.x, (int)pos.y, (int)(25 * scale), (int)(25 * scale));
	}

}
