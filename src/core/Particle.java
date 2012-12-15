package core;

/**
 * Provides a basic massive particle that can move through space under the influence of gravity.
 * 
 * @author Trystan Binkley-Jones
 *
 */
public class Particle implements PuzzleElement
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


	/**
	 * Creates a new mass.
	 * 
	 * @param parent - The puzzle that owns the mass.
	 * @param position - The position of the mass.
	 * @param mass - The mass of the mass.
	 */
	public Particle(Puzzle parent, Vector2 position, double mass)
	{
		this.parent = parent;
		this.position = position;
		this.mass = mass;
		
		momentum = new Vector2();
	}
	
	/**
	 * Creates a new mass.
	 * 
	 * @param parent - The puzzle that owns the mass.
	 */
	public Particle(Puzzle parent)
	{
		this(parent, new Vector2(), 1);
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
	

	/**
	 * Advances the mass one time step.
	 * 
	 * @param dt - The time step to advance by.
	 */
	@Override
	public void Tick(double dt)
	{
		//For now, this uses an Euler DE solver, but for more accurate solutions,
		//it would be better to use the Runge-Kutta DE solver.
		momentum.Add(Vector2.Scale(parent.GetGravity(), dt * mass));
		position.Add(Vector2.Scale(momentum, dt / mass));
	}
	
	/**
	 * Adds a force to the particle over one time step.
	 * 
	 * @param force - The force to add to the particle.
	 * @param dt - The time step to add the force over.
	 */
	public void AddInstantaneousForce(Vector2 force, double dt)
	{
		//For now, this uses an Euler DE solver, but for more accurate solutions,
		//it would be better to use the Runge-Kutta DE solver.
		momentum.Add(Vector2.Scale(force, dt));
	}
}
