package core;

import java.util.ArrayList;

/**
 * This is used to deliminate operations and functions when the tree is flattened.  
 * This class should never be used in an actual tree.
 * 
 * @author Trystan Binkley-Jones
 *
 */
public class Deliminator extends ASTNode
{
	/**
	 * Allows deliminators to specify a type, either open or closed.
	 * 
	 * @author Trystan Binkley
	 *
	 */
	public enum DelimType
	{
		open,
		closed
	}
	
	
	/**
	 * The type of this deliminator.
	 */
	DelimType type;
	
	
	/**
	 * Creates a new deliminator.
	 * 
	 * @param type - The type of the deliminator.
	 */
	public Deliminator(DelimType type)
	{
		this.type = type;
	}
	
	@Override
	public double Solve()
	{
		return 0;
	}

	@Override
	public void Flatten(ArrayList<ASTNode> list)
	{
		list.add(this);
	}

	@Override
	public ArrayList<ASTNode> GetChildren()
	{
		return null;
	}

	@Override
	public void ClearChild(ASTNode child)
	{
	}

	
	/**
	 * Gets the type of the deliminator.
	 * 
	 * @return Returns the type.
	 */
	public DelimType GetType()
	{
		return type;
	}

}
