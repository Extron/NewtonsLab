package core;

/**
 * A node that stores a value.  This will always be a leaf in an AST.
 * 
 * @author Trystan Binkley-Jones
 *
 */
public final class ValueNode extends ASTNode
{	
	/**
	 * Creates a constant node with the value set to pi.
	 * 
	 * @return Returns a pi constant node.
	 */
	public static ASTNode Pi()
	{
		return new ValueNode("\u03C0", Math.PI);
	}
	
	/**
	 * Creates a constant node with the value set to pi / 2.
	 * 
	 * @return Returns a pi / 2 constant node.
	 */
	public static ASTNode PiOverTwo()
	{
		return new ValueNode("\u03C0 / 2", Math.PI / 2);
	}
	
	/**
	 * Creates a constant node with the value set to 2 pi.
	 * 
	 * @return Returns a 2 pi constant node.
	 */
	public static ASTNode TwoPi()
	{
		return new ValueNode("2\u03C0", 2 * Math.PI);
	}
	
	/**
	 * Creates a constant node with the value set to e.
	 * 
	 * @return Returns a e constant node.
	 */
	public static ASTNode e()
	{
		return new ValueNode("e", Math.E);
	}
	
	/**
	 * Creates a constant node with the value set to g, gravitational acceleration.
	 * 
	 * @return Returns a g constant node.
	 */
	public static ASTNode g()
	{
		return new ValueNode("g", 9.81);
	}
	
	
	/**
	 * The value stored in the node.
	 */
	double value;
	
	
	/**
	 * Creates a new value node.
	 * 
	 * @param name - The name of the node.
	 * @param value - The value to store in the node.
	 */
	public ValueNode(String name, double value)
	{
		this.name = name;
		this.value = value;
	}
	
	/**
	 * Creates a new value node, with the name is set to "Constant".
	 * 
	 * @param value The value to store in the node.
	 */
	public ValueNode(float value)
	{
		this("Constant", value);
	}
	
	/**
	 * Creates a new value node, with the name set to "Constant" and the value set to 0.
	 */
	public ValueNode()
	{
		this(0);
	}
	
	
	@Override
	/**
	 * Returns the value stored at the current node.
	 */
	public double Solve()
	{
		return value;
	}
}
