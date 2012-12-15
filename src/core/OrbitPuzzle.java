package core;

import java.util.ArrayList;

/**
 * An orbital mechanics/central force puzzle.  The goal here is to supply the satellite with
 * the proper initial momentum to generate a circular orbit.
 * 
 * @author Trystan Binkley-Jones
 *
 */
public class OrbitPuzzle extends Puzzle
{
	/**
	 * The satellite to use for the puzzle.
	 */
	Satellite satellite;
	
	/**
	 * The tolerance of how close the orbit must be to circular.
	 */
	double tolerance;
	
	/**
	 * Indicates whether or not the satellite left a circular orbit during its loop.
	 */
	boolean withinCircle;
	
	/**
	 * Indicates that the satellite has made a full orbit.
	 */
	boolean fullOrbit;
	
	/**
	 * Indicates that the satellite has made a half orbit.
	 */
	boolean halfOrbit;
	
	
	@Override
	public void InitializePuzzle()
	{
		satellite = new Satellite(this, new Vector2(1000, 0), 5);
		tolerance = 10;
		
		elements.add(satellite);
		
		withinCircle = true;
		fullOrbit = false;
		halfOrbit = false;
		
		tip = "This is a central force problem where one of the bodies does not move, much like the Earth\n" +
		      "orbiting around the sun.  Using Lagrangian mechanics and Newton's Law of Gravity, we can discover\n" +
		      "that the radius of an orbiting body is related to its angular momentum and an eccentricity value:\n\n" +
		      "r(\u03b8) = l\u00b2 / g * m * (1 + \u03f5 * cos(\u03b8)\n\n" + 
		      "For this case, we want \u03f5 to be zero when \u03b8 is zero.";
		
		SetFunction("Angular Momentum", new ValueNode());
	}

	@Override
	public void ResetPuzzle()
	{
		satellite.SetPosition(new Vector2(1000, 0));
		satellite.SetMomentum(new Vector2(0, 0));
		withinCircle = true;
		fullOrbit = false;
		halfOrbit = false;
	}

	@Override
	public void ActivatePuzzle()
	{
		active = true;
		
		double ang = GetFunction("Angular Momentum").Solve();
		double lin = ang / 1000;
		
		satellite.SetMomentum(new Vector2(0, lin));
	}

	@Override
	public void DeactivatePuzzle()
	{
		active = false;
		
		ResetPuzzle();
	}

	@Override
	public void Tick(double dt)
	{
		if (active)
		{
			satellite.Tick(dt);
			
			double distance = satellite.GetPosition().Length();
			
			if (Math.abs(distance - 1000) > tolerance)
				withinCircle = false;
			
			if (Math.abs(satellite.GetPosition().x - 1000) <= tolerance &&
				Math.abs(satellite.GetPosition().y) <= tolerance && halfOrbit)
				fullOrbit = true;
			
			if (satellite.GetPosition().x < 0 && Math.abs(satellite.GetPosition().y) <= tolerance)
				halfOrbit = true;
		}
	}

	@Override
	public boolean HasWon()
	{
		return withinCircle && fullOrbit;
	}

	@Override
	public boolean HasFailed()
	{
		return false;
	}

	@Override
	public boolean CanActivate()
	{
		return GetFunction("Angular Momentum") != null;
	}

	@Override
	public double GetParameter(String parameter)
	{
		if (parameter == "radius")
			return 1000;
		else if (parameter == "mass")
			return satellite.GetMass();
		
		return 0;
	}

	@Override
	public ArrayList<String> GetValueParameters()
	{
		ArrayList<String> list = new ArrayList<String>();
		
		list.add("radius");
		list.add("mass");
		
		return list;
	}

}
