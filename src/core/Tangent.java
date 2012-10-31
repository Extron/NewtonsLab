package core;

public class Tangent extends Function
{
	/**
	 * Creates a new tangent function.
	 * 
	 * @param node - The node to compute the tangent of.
	 */
	public Tangent(ASTNode node)
	{
		super(node);
		
		name = "tan";
	}
	
	/**
	 * Creates a new tangent function with a blank node.
	 */
	public Tangent()
	{
		super(new NullNode());
		
		name = "tan";
	}
	
	
	@Override
	/**
	 * Computes the tangent of the parameter.
	 */
	public double Compute(double[] values)
	{
		if (values.length != 1)
			return 0;
		else
			return Math.tan(values[0]);
	}
}
