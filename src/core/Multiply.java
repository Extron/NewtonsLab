package core;

/**
 * The multiply operator node, which (obviously) multiplies two nodes together.
 * 
 * @author Trystan Binkley-Jones
 *
 */
public final class Multiply extends Operator
{
	/**
	 * Creates a new multiplication node.
	 * @param left - The left sub-node.
	 * @param right - The right sub-node.
	 */
	public Multiply(ASTNode left, ASTNode right)
	{
		super(left, right);
		
		name = "Multiply";
	}
	
	/**
	 * Creates a blank multiplication node with null nodes for sub-nodes.
	 */
	public Multiply()
	{
		this(new NullNode(), new NullNode());
	}
	
	
	@Override
	/**
	 * Performs a multiply operation between the two values.
	 */
	protected double Operate(double left, double right)
	{
		return left * right;
	}
}
