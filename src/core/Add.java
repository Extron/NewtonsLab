package core;

/**
 * The add operator node, which (obviously) adds two nodes together.
 * 
 * @author Trystan Binkley-Jones
 *
 */
public final class Add extends Operator
{
	/**
	 * Creates a new addition node.
	 * @param left - The left sub-node.
	 * @param right - The right sub-node.
	 */
	public Add(ASTNode left, ASTNode right)
	{
		super(left, right);
		
		name = "Add";
	}
	
	/**
	 * Creates a blank addition node with null nodes for sub-nodes.
	 */
	public Add()
	{
		this(new NullNode(), new NullNode());
	}
	
	
	@Override
	/**
	 * Performs an add operation between the two values.
	 */
	protected double Operate(double left, double right)
	{
		return left + right;
	}
}
