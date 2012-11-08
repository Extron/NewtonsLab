package core;

public final class Power extends Operator
{
	/**
	 * Creates a squaring operation.
	 * 
	 * @param node - The node to square.
	 * @return Returns the squaring operator node.
	 */
	public static ASTNode Square(ASTNode node)
	{
		ASTNode square = new Power(node, new ValueNode(2));
		square.name = "Square";
		return square;
	}
	
	/**
	 * Creates a cubing operation.
	 * 
	 * @param node - The node to cube.
	 * @return Returns the cubing operator node.
	 */
	public static ASTNode Cube(ASTNode node)
	{
		ASTNode cube = new Power(node, new ValueNode(3));
		cube.name = "Cube";
		return cube;
	}
	
	/**
	 * Creates a exponentiation operation.
	 * 
	 * @param node - The node to raise e to.
	 * @return Returns the exponentiation operator node.
	 */
	public static ASTNode Exponent(ASTNode node)
	{
		ASTNode exp = new Power(ValueNode.e(), node);
		exp.name = "Exponent";
		return exp;
	}
	
	
	/**
	 * Creates a new power node.
	 * @param left - The left sub-node.
	 * @param right - The right sub-node.
	 */
	public Power(ASTNode left, ASTNode right)
	{
		super(left, right);
		
		name = "Power";
	}
	
	/**
	 * Creates a blank power node with null nodes for sub-nodes.
	 */
	public Power()
	{
		this(new NullNode(), new NullNode());
	}
	
	
	@Override
	/**
	 * Performs an power operation between the two values.
	 */
	protected double Operate(double left, double right)
	{
		return Math.pow(left, right);
	}
}

