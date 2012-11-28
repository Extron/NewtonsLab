package core;

import java.util.ArrayList;

/**
 * This is a blank filler node, allowing non-complete function node trees to remain "legal".
 * 
 * @author Trystan Binkley-Jones
 *
 */
public final class NullNode extends ASTNode
{
	/**
	 * Creates a new null node.
	 */
	public NullNode()
	{
		name = "<Null>";
	}
	
	
	@Override
	/**
	 * Returns a simple 0.
	 */
	public double Solve()
	{
		return 0;
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
