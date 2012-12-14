package core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import core.Deliminator.DelimType;

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
	 * The symbol used to represent the node.
	 */
	protected String symbol;
	
	/**
	 * The parent node of this node.
	 */
	protected ASTNode parent;
	
	
	/**
	 * Builds a tree from a flattened AST node list.
	 * 
	 * @param list - The list to build from.
	 * 
	 * @return Returns the root of the AST.
	 */
	public static ASTNode BuildTree(List<ASTNode> list)
	{
		Iterator<ASTNode> iter = list.iterator();
		
		ASTNode root = null;
		
		int rootDelimCount = -1;
		
		int delimCount = 0;
		
		while (iter.hasNext())
		{
			ASTNode node = iter.next();

			if (node == null)
				continue;
			
			if (node instanceof Deliminator)
			{
				if (((Deliminator)node).GetType() == DelimType.open)
					delimCount++;
				else
					delimCount--;
				
				continue;
			}
			else
			{				
				int nodePriority = GetNodePriority(node);
				int rootPriority = GetNodePriority(root);
				
				if (rootDelimCount == -1 || rootDelimCount > delimCount || (rootDelimCount == delimCount && rootPriority < nodePriority))
				{
					root = node;
					rootDelimCount = delimCount;
				}
			}
		}
		
		int rootIndex = list.indexOf(root);
		
		if (root instanceof Operator)
		{
			ASTNode left = BuildTree(list.subList(0, rootIndex));
			ASTNode right = BuildTree(list.subList(rootIndex + 1, list.size()));
			
			((Operator) root).SetLeftNode(left);
			((Operator) root).SetRightNode(right);
		}
		else if (root instanceof Function)
		{
			ASTNode parameter = BuildTree(list.subList(rootIndex + 1, list.size()));
			
			((Function) root).SetParameter(0, parameter);
		}
		
		return root;
	}
	
	/**
	 * Gets the priority of an AST node.  This determines order of operations in the tree.
	 * 
	 * @param node - The node whose priority needs to be returned.
	 * @return Returns the priority of the node.
	 */
	public static int GetNodePriority(ASTNode node)
	{
		if (node instanceof Power)
			return 3;
		else if (node instanceof Multiply || node instanceof Divide)
			return 4;
		else if (node instanceof Add || node instanceof Subtract)
			return 5;
		else if (node instanceof Function)
			return 2;
		else if (node instanceof ValueNode || node instanceof ParameterNode)
			return 1;
		
		return 0;
	}
	
	/**
	 * Clears the parent from this node.  This function ensures that the parent also clears out
	 * the child.
	 */
	public void ClearParent()
	{
		if (parent != null)
			parent.ClearChild(this);
	}
	
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
	public void SetParent(ASTNode parent)
	{
		this.parent = parent;
	}

	/**
	 * Gets the symbol of the node.
	 * @return Returns the node's symbol.
	 */
	public String GetSymbol()
	{
		return symbol;
	}
	
	/**
	 * Recursively solves the node.
	 * @return Returns the computed value of the node.
	 */
	public abstract double Solve();
	
	/**
	 * Flattens the AST into a list. 
	 * 
	 * @param list - The list to flatten the tree into.
	 */
	public abstract void Flatten(ArrayList<ASTNode> list);
	
	/**
	 * Returns a list of all the children of the node.
	 * 
	 * @return Returns a list of child nodes.
	 */
	public abstract ArrayList<ASTNode> GetChildren();
	
	/**
	 * Clears the specified child from the tree.
	 * 
	 * @param child - The child to clear.
	 */
	public abstract void ClearChild(ASTNode child);
	
	@Override
	public String toString()
	{
		return name;
	}
}
