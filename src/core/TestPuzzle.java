package core;

import java.util.ArrayList;

public final class TestPuzzle extends Puzzle
{
	/**
	 * The particle of the test puzzle.
	 */
	Particle particle;
	
	/**
	 * The impulse function of the particle.
	 */
	ASTNode impulse;
	
	
	@Override
	public void InitializePuzzle()
	{
		particle = new Particle(this);
		gravity.Scale(5e-8);
		
		elements.add(particle);
		impulse = new ValueNode(0.01);
		SetFunction("impulse", impulse);
	}

	@Override
	public void ResetPuzzle()
	{
		particle.SetPosition(new Vector2(0, 0));
		particle.SetMomentum(new Vector2(0, 0));
	}
	
	@Override
	public void ActivatePuzzle()
	{
		active = true;
		
		particle.SetMomentum(new Vector2(GetFunction("impulse").Solve(), -0.02));
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
			particle.Tick(dt);
	}
	
	@Override
	public boolean HasWon()
	{
		return false;
	}

	@Override 
	public boolean HasFailed()
	{
		return particle.position.y > 1000;
	}
	
	@Override
	public boolean CanActivate()
	{
		return impulse != null;
	}
	
	@Override
	public double GetParameter(String parameter)
	{
		if (parameter == "mass")
			return particle.GetMass();
		else 
			return 0;
	}

	@Override
	public ArrayList<String> GetValueParameters()
	{
		ArrayList<String> list = new ArrayList<String>(1);
		list.add("mass");
		
		return list;
	}
}
