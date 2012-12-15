package core;

/**
 * A satellite is a particle that responds to central force motion.
 * 
 * @author Trystan Binkley-Jones
 *
 */
public class Satellite implements PuzzleElement
{
	/**
	 * The parent puzzle that this mass is a part of.
	 */
	Puzzle parent;
	
	/**
	 * The position of the mass.
	 */
	Vector2 position;
	
	/**
	 * The momentum of the mass.
	 */
	Vector2 momentum;
	
	/** 
	 * The mass of the object.
	 */
	double mass;

	
	public Satellite(Puzzle parent, Vector2 position, double mass)
	{
		this.parent = parent;
		this.position = position;
		this.mass = mass;
		
		momentum = new Vector2();
	}
	
	
	/**
	 * Gets the particle's mass.
	 * 
	 * @return Returns the mass.
	 */
	public double GetMass()
	{
		return mass;
	}

	/**
	 * Sets the particle's mass.
	 * 
	 * @param mass - The mass to set.
	 */
	public void SetMass(double mass)
	{
		this.mass = mass;
	}
	
	/**
	 * Gets the position of the particle.
	 * 
	 * @return Return's the particle's position.
	 */
	public Vector2 GetPosition()
	{
		return position;
	}

	/**
	 * Sets the particle's position.
	 * 
	 * @param position - The position to set.
	 */
	public void SetPosition(Vector2 position)
	{
		this.position = position;
	}

	/**
	 * Gets the particle's momentum.
	 * 
	 * @return Returns the particle's momentum.
	 */
	public Vector2 GetMomentum()
	{
		return momentum;
	}

	/**
	 * Sets the particle's momentum.
	 * 
	 * @param momentum - The momentum to set.
	 */
	public void SetMomentum(Vector2 momentum)
	{
		this.momentum = momentum;
	}
	
	
	@Override
	public void Tick(double dt)
	{
		double r = position.LengthSquared();
		
		Vector2 accel = Vector2.Scale(Vector2.Negative(Vector2.Normalized(position)), 1 / r);
		momentum.Add(Vector2.Scale(accel, dt * mass));
		position.Add(Vector2.Scale(momentum, dt));
	}
}
