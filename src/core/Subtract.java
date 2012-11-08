package core;

/**
 * The subtraction operator node, which (obviously) subtracts two nodes.
 * 
 * @author Trystan Binkley-Jones
 *
 */
public final class Subtract extends Operator
{
	/**
	 * Creates a new subtraction node.
	 * @param left - The left sub-node.
	 * @param right - The right sub-node.
	 */
	public Subtract(ASTNode left, ASTNode right)
	{
		super(left, right);
		
		name = "Subtract";
	}
	
	/**
	 * Creates a blank subtraction node with null nodes for sub-nodes.
	 */
	public Subtract()
	{
		this(new NullNode(), new NullNode());
	}
	
	
	@Override
	/**
	 * Performs an subtraction operation between the two values.
	 */
	protected double Operate(double left, double right)
	{
		return left - right;
	}
}
