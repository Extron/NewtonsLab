package applet;

import java.awt.Color;
import java.awt.Graphics;

import core.Particle;
import core.PuzzleElement;
import core.Vector2;

public class ParticleObject extends PuzzleObject
{
	/**
	 * Creates a new particle object from a particle element.
	 * 
	 * @param element - The particle element to create from.
	 */
	public ParticleObject(PuzzleElement element)
	{
		super(element);
		
		if (!(element instanceof Particle))
			throw new IllegalArgumentException("Puzzle element is not of type Particle.");
	}

	@Override
	/**
	 * Creates the particle object.
	 */
	public void Create(PuzzleElement element)
	{
	}

	@Override
	/**
	 * Draws the particle object.
	 */
	public void Draw(Graphics graphics)
	{
		Vector2 pos = ((Particle)element).GetPosition();
		graphics.setColor(Color.red);
		graphics.fillOval((int)pos.x, (int)pos.y, 2, 2);
	}
}