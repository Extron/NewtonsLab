package core;

/**
 * Provides a common interface for puzzle elements, like particles, rigid bodies, springs, etc.
 * 
 * @author Trystan Binkley-Jones
 *
 */
public interface PuzzleElement
{
	public void Tick(double dt);
}
