package core;

import java.util.ArrayList;

/**
 * The typical cannon puzzle.  The user must set the cannon's angle and
 * the initial velocity to get the cannon ball through a hoop.
 * 
 * @author Trystan Binkley-Jones
 *
 */
public class CannonPuzzle extends Puzzle
{
	/**
	 * The cannon ball to fling.
	 */
	Particle cannonBall;
	
	/**
	 * The hoop that the particle must go through.
	 */
	Hoop hoop;
	
	/**
	 * The previous position of the cannon ball.
	 */
	Vector2 oldPos;
	
	
	@Override
	public void InitializePuzzle()
	{
		cannonBall = new Particle(this);
		hoop = new Hoop(new Vector2(10000, -1000), new Vector2(10000, -900));
		
		gravity.Scale(5e-8);
		
		elements.add(cannonBall);
		elements.add(hoop);
		
		backgroundImage = "cannontest.png";
		SetFunction("Cannon Angle", new ValueNode(0));
		SetFunction("Initial Velocity", new ValueNode(0));
	}

	@Override
	public void ResetPuzzle()
	{
		cannonBall.SetPosition(new Vector2(0, 0));
		cannonBall.SetMomentum(new Vector2(0, 0));
	}

	@Override
	public void ActivatePuzzle()
	{
		active = true;
		
		double angle = GetFunction("Cannon Angle").Solve();
		double velocity = GetFunction("Initial Velocity").Solve();
		
		Vector2 velVec = new Vector2(velocity * Math.cos(angle), velocity * -Math.sin(angle));
		
		cannonBall.SetMomentum(Vector2.Scale(velVec, cannonBall.GetMass()));
		oldPos = new Vector2(0, 0);
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
			oldPos.x = cannonBall.GetPosition().x;
			oldPos.y = cannonBall.GetPosition().y;
			
			cannonBall.Tick(dt);
		}
	}

	@Override
	public boolean HasWon()
	{
		return hoop.HasParticleCrossed(oldPos, cannonBall.GetPosition());
	}

	@Override
	public boolean HasFailed()
	{
		return cannonBall.GetPosition().y > 1000;
	}

	@Override
	public boolean CanActivate()
	{
		return GetFunction("Cannon Angle") != null && GetFunction("Initial Velocity") != null;
	}

	@Override
	public double GetParameter(String parameter)
	{
		if (parameter == "mass")
			return cannonBall.GetMass();
		else if (parameter == "gravity")
			return gravity.y;
		
		return 0;
	}

	@Override
	public ArrayList<String> GetValueParameters()
	{
		ArrayList<String> list = new ArrayList<String>();
		
		list.add("mass");
		list.add("gravity");
		
		return list;
	}

}
