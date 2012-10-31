package core;

/**
 * Implements the natural logarithm function.
 * 
 * @author Trystan Binkley-Jones
 *
 */
public final class Log extends Function
{
	/**
	 * Creates a new natural logarithm function.
	 * 
	 * @param node - The node to compute the log of.
	 */
	public Log(ASTNode node)
	{
		super(node);
		
		name = "ln";
	}
	
	/**
	 * Creates a new natural logarithm function with a blank node.
	 */
	public Log()
	{
		super(new NullNode());
		
		name = "ln";
	}
	
	
	@Override
	/**
	 * Computes the log of the parameter.
	 */
	public double Compute(double[] values)
	{
		if (values.length != 1)
			return 0;
		else
			return Math.log(values[0]);
	}

}
