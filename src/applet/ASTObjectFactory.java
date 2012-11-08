package applet;

import core.ASTNode;
import core.Operator;
import core.ValueNode;

public class ASTObjectFactory
{
	/**
	 * Creates a new AST node GUI object.
	 * 
	 * @param node - The node to create from.
	 * @return Returns the newly created GUI object.
	 */
	public static ASTNodeObject CreateASTObject(ASTNode node)
	{
		ASTNodeObject object;
		
		if (node instanceof Operator)
			object = new OperatorObject((Operator) node);
		else if (node instanceof ValueNode)
			object = new ValueObject((ValueNode)node);
		else
			throw new IllegalArgumentException("The node is not an instance of a drawable AST node type.");
		
		return object;
	}
}