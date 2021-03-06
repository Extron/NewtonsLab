package applet;

import core.Hoop;
import core.Particle;
import core.PuzzleElement;
import core.Satellite;

/**
 * A factory class that easily creates puzzle objects from corresponding elements.
 * 
 * @author Trystan Binkley-Jones
 *
 */
public class PuzzleObjectFactory
{
	public static PuzzleObject CreateObject(PuzzleElement element)
	{
		PuzzleObject object;
		
		if (element instanceof Particle)
			object = new ParticleObject(element);
		else if (element instanceof Hoop)
			object = new HoopObject(element);
		else if (element instanceof Satellite)
			object = new SatelliteObject(element);
		else 
			throw new IllegalArgumentException("The element type is not regognized.");
		
		return object;
	}
}
