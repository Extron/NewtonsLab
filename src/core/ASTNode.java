package core;

/**
 * A function node is the most basic unit of a function.  A function is built like an AST, 
 * where each node contains pointers to nodes beneath it that will be computed first, and 
 * will be solved recursively.  Think PL (Regardless of whether the class was good or not,
 * this is actually an extremely useful pattern).
 * 
 * @author Trystan Binkley-Jones
 *
 */
public abstract class ASTNode
{
	/**
	 * The name of the node.  This allows the nodes to be identifiable to the GUI.
	 */
	String name;
	
	
	/**
	 * Gets the name of the node.
	 * @return Returns the node's name.
	 */
	public String GetName()
	{
		return name;
	}

	/**
	 * Sets the name of the node.
	 * @param name - The name to set for the name of the node.
	 */
	public void SetName(String name)
	{
		this.name = name;
	}


	/**
	 * Recursively solves the node.
	 * @return Returns the computed value of the node.
	 */
	public abstract double Solve();
}
