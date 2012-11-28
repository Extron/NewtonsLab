package core;

/**
 * Implements the sine function.
 * 
 * @author Trystan Binkley-Jones
 *
 */
public final class Sine extends Function
{
	/**
	 * Creates a new sine function.
	 * 
	 * @param node - The node to compute the sine of.
	 */
	public Sine(ASTNode node)
	{
		super(node);
		
		name = "Sine";
		symbol = "sin";
	}
	
	/**
	 * Creates a new sine function with a blank node.
	 */
	public Sine()
	{
		super(new NullNode());
		
		name = "Sine";
		symbol = "sin";
	}
	
	
	@Override
	/**
	 * Computes the sine of the parameter.
	 */
	public double Compute(double[] values)
	{
		if (values.length != 1)
			return 0;
		else
			return Math.sin(values[0]);
	}
}
