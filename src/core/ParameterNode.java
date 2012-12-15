package core;

import java.util.ArrayList;

/**
 * This in likely going to be the most common node used in the AST.  This node stores a 
 * parameter identifier for a puzzle, and will look up the current value of the parameter
 * when solving the node.
 * 
 * @author Trystan Binkley-Jones
 *
 */
public final class ParameterNode extends ASTNode
{
	/**
	 * The puzzle to look up the parameter in.
	 */
	Puzzle puzzle;
	
	/**
	 * The name of the parameter to look up.
	 */
	String parameter;
	
	
	/**
	 * Creates a new parameter node.
	 * 
	 * @param puzzle - The puzzle to take the parameter from.
	 * @param parameter - The name of the parameter to use.
	 */
	public ParameterNode(Puzzle puzzle, String parameter)
	{
		this.puzzle = puzzle;
		this.parameter = parameter;
		
		name = parameter;
	}
	
	
	/**
	 * Gets the value of the parameter.
	 * 
	 * @return Returns the value.
	 */
	public double GetValue()
	{
		return puzzle.GetParameter(parameter);
	}
	
	@Override
	/**
	 * Returns the current value of the parameter of the puzzle.
	 */
	public double Solve()
	{
		return puzzle.GetParameter(parameter);
	}

	@Override
	/**
	 * Flattens this node by adding it to the list.
	 */
	public void Flatten(ArrayList<ASTNode> list)
	{
		list.add(this);
	}
	
	@Override
	public ArrayList<ASTNode> GetChildren()
	{
		ArrayList<ASTNode> children = new ArrayList<ASTNode>();
		
		return children;
	}
	
	@Override
	public void ClearChild(ASTNode child)
	{
	}
}
