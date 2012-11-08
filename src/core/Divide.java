package core;

/**
 * The divide operator node, which (obviously) divides two nodes.
 * 
 * @author Trystan Binkley-Jones
 *
 */
public final class Divide extends Operator
{
	/**
	 * Creates a new division node.
	 * @param left - The left sub-node.
	 * @param right - The right sub-node.
	 */
	public Divide(ASTNode left, ASTNode right)
	{
		super(left, right);
		
		name = "Divide";
	}
	
	/**
	 * Creates a blank addition node with null nodes for sub-nodes.
	 */
	public Divide()
	{
		this(new NullNode(), new NullNode());
	}
	
	
	@Override
	/**
	 * Performs a division operation between the two values.
	 */
	protected double Operate(double left, double right)
	{
		return left / right;
	}
}
