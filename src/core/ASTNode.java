package core;

import java.util.ArrayList;

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
	protected String name;
	
	/**
	 * The parent node of this node.
	 */
	protected ASTNode parent;
	
	
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
	 * Gets the parent node of the current node.  Will be null if node is root.
	 * 
	 * @return Return's the parent node.
	 */
	public ASTNode GetParent()
	{
		return parent;
	}

	/**
	 * Sets the parent node of the current node.
	 * 
	 * @param parent - The parent node to set.
	 */
	protected void SetParent(ASTNode parent)
	{
		this.parent = parent;
	}

	/**
	 * Recursively solves the node.
	 * @return Returns the computed value of the node.
	 */
	public abstract double Solve();
	
	/**
	 * Returns a list of all the children of the node.
	 * 
	 * @return Returns a list of child nodes.
	 */
	public abstract ArrayList<ASTNode> GetChildren();
	
	
	@Override
	public String toString()
	{
		return name;
	}
}
