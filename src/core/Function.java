package core;

import java.util.ArrayList;

/**
 * A function node stores a function with an variable amount of parameters, such as sin or log.
 * 
 * @author Trystan Binkley-Jones
 *
 */
public abstract class Function extends ASTNode
{
	/**
	 * The parameters of the function.
	 */
	ArrayList<ASTNode> parameters;
	
	
	/**
	 * Creates a new function node with the specified parameters.
	 * 
	 * @param parameters - The parameters of the function.
	 */
	public Function(ASTNode... parameters)
	{
		this.parameters = new ArrayList<ASTNode>(parameters.length);
		
		for (ASTNode parameter : parameters)
		{
			parameter.SetParent(this);
			this.parameters.add(parameter);
		}
	}
	
	
	@Override
	/**
	 * Recursively solves each parameter of the function, then computes the value of the function.
	 */
	public double Solve()
	{
		double[] values = new double[parameters.size()];
		int i = 0;
		
		for(ASTNode parameter : parameters)
			values[i++] = parameter.Solve();
		
		return Compute(values);
	}
	
	@Override
	public ArrayList<ASTNode> GetChildren()
	{
		ArrayList<ASTNode> children = new ArrayList<ASTNode>();
		
		for (int i = 0; i < parameters.size(); i++)
			children.add(parameters.get(i));
		
		return children;
	}
	
	/**
	 * Computes the value of the function using the values passed in as parameters.
	 * 
	 * @param values - The parameters to the function.
	 * @return Returns the computed value of the function.
	 */
	public abstract double Compute(double[] values);
}
