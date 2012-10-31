package core;

public class Cosine extends Function
{
	/**
	 * Creates a new cosine function.
	 * 
	 * @param node - The node to compute the cosine of.
	 */
	public Cosine(ASTNode node)
	{
		super(node);
		
		name = "cos";
	}
	
	/**
	 * Creates a new cosine function with a blank node.
	 */
	public Cosine()
	{
		super(new NullNode());
		
		name = "cos";
	}
	
	
	@Override
	/**
	 * Computes the cosine of the parameter.
	 */
	public double Compute(double[] values)
	{
		if (values.length != 1)
			return 0;
		else
			return Math.cos(values[0]);
	}
}
