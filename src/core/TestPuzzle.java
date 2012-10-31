package core;

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
	}

	@Override
	public void ActivatePuzzle()
	{
		active = true;
		
		particle.SetMomentum(new Vector2(impulse.Solve(), 0));
	}
	
	@Override
	public void Tick(float dt)
	{
		if (active)
			particle.Tick(dt);
	}

	@Override
	public void SetFunction(String parameter, ASTNode root)
	{
		if (parameter == "impulse")
			impulse = root;
	}
	
	@Override
	public boolean HasWon()
	{
		return false;
	}

	@Override
	public double GetParameter(String parameter)
	{
		if (parameter == "mass")
			return particle.GetMass();
		else 
			return 0;
	}

}
