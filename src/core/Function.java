package core;

import java.util.ArrayList;

import core.Deliminator.DelimType;

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
	
	
	/**
	 * Sets a parameter of the function at the specified index.
	 * 
	 * @param index - The index to set.
	 * @param child - The parameter to set.
	 */
	public void SetParameter(int index, ASTNode child)
	{
		parameters.set(index, child);
		child.SetParent(this);
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
	/**
	 * Flattens this node by first adding itself to the list, then recursively flattening each child branch.
	 */
	public void Flatten(ArrayList<ASTNode> list)
	{
		list.add(this);
		list.add(new Deliminator(DelimType.open));
		
		for(ASTNode parameter : parameters)
			parameter.Flatten(list);
		
		list.add(new Deliminator(DelimType.closed));
	}
	
	@Override
	public ArrayList<ASTNode> GetChildren()
	{
		ArrayList<ASTNode> children = new ArrayList<ASTNode>();
		
		for (int i = 0; i < parameters.size(); i++)
			children.add(parameters.get(i));
		
		return children;
	}
	
	@Override
	public void ClearChild(ASTNode child)
	{
		if (parameters.remove(child))
		{
			child.SetParent(null);
		}
	}
	
	/**
	 * Computes the value of the function using the values passed in as parameters.
	 * 
	 * @param values - The parameters to the function.
	 * @return Returns the computed value of the function.
	 */
	public abstract double Compute(double[] values);
}
