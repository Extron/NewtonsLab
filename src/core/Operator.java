package core;

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
	
	
	@Override
	/**
	 * Solves the binary operator be first recursively solving each sub-node, then performing the operation.
	 */
	public double Solve()
	{
		return Operate(left.Solve(), right.Solve());
	}

	
	/**
	 * Performs a binary operation on two values, which are obtained from recursively solving the left and
	 * right nodes.
	 * @param left - The value of the left node.
	 * @param right - The value of the right node.
	 * @return Returns the result of the operation.
	 */
	protected abstract double Operate(double left, double right);
}
