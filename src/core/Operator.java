package core;

import java.util.ArrayList;

/**
 * This is an operator function node, and it manages a binary operation (plus, minus, add, subtract, power).
 * 
 * @author Trystan Binkley-Jones
 *
 */
public abstract class Operator extends ASTNode
{
	/**
	 * The left node in the operator.
	 */
	ASTNode left;
	
	/**
	 * The right node in the operator.
	 */
	ASTNode right;
	
	
	/**
	 * Creates a new operator node.
	 * 
	 * @param left - The left operand.
	 * @param right - The right operand.
	 */
	public Operator(ASTNode left, ASTNode right)
	{
		this.left = left;
		this.right = right;
		
		this.left.SetParent(this);
		this.right.SetParent(this);
	}
	
	
	@Override
	/**
	 * Solves the binary operator be first recursively solving each sub-node, then performing the operation.
	 */
	public double Solve()
	{
		return Operate(left.Solve(), right.Solve());
	}

	/**
	 * Sets the right node.
	 * 
	 * @param right - The node to set to.
	 */
	public void SetRightNode(ASTNode right)
	{
		this.right = right;
		right.SetParent(this);
	}
	
	/**
	 * Sets the left node.
	 * 
	 * @param left - The node to set to.
	 */
	public void SetLeftNode(ASTNode left)
	{
		this.left = left;
		left.SetParent(this);
	}
	
	/**
	 * Performs a binary operation on two values, which are obtained from recursively solving the left and
	 * right nodes.
	 * @param left - The value of the left node.
	 * @param right - The value of the right node.
	 * @return Returns the result of the operation.
	 */
	protected abstract double Operate(double left, double right);
	
	@Override
	public ArrayList<ASTNode> GetChildren()
	{
		ArrayList<ASTNode> children = new ArrayList<ASTNode>();
		
		children.add(left);
		children.add(right);
		
		return children;
	}
	
	@Override
	public void ClearChild(ASTNode child)
	{
		if (left == child)
		{
			left.SetParent(null);
			left = new NullNode();
		}
		else if (right == child)
		{
			right.SetParent(null);
			right = new NullNode();
		}
	}
}
