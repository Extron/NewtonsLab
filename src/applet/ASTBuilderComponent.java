package applet;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JComponent;

import core.ASTNode;

/**
 * This component manages the function designer that players use to create parameters for the puzzles.
 * 
 * @author Trystan Binkley-Jones
 *
 */
public class ASTBuilderComponent extends JComponent
{
	/**
	 * A list of GUI objects to draw to represent the function.
	 */
	ArrayList<ASTNodeObject> nodes;
	
	/**
	 * The root node of the AST function to draw and manipulate.
	 */
	ASTNode root;
	
	
	/**
	 * Creates a new AST function builder tool.
	 * 
	 * @param root - The root of the AST function to manipulate.
	 */
	public ASTBuilderComponent(ASTNode root)
	{
		this.root = root;
		
		nodes = new ArrayList<ASTNodeObject>();
		
		setSize(500, 500);
		
		InitNode(root);
	}
	
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	
		//Fill the background.  TODO: Make this customizable to draw things like pictures.
		int x = getInsets().left;
		int y = getInsets().top;
		int w = getWidth() - getInsets().left - getInsets().right;
		int h = getHeight() - getInsets().top - getInsets().bottom;
		
		g.setColor(Color.white);
		
		g.fillRect(x, y, w, h);
		
		//Draw all of the puzzle objects.
		Iterator<ASTNodeObject> iter = nodes.iterator();
		
		while (iter.hasNext())
			iter.next().Draw(g);
	}
	
	
	/**
	 * Sets the current AST function.
	 * 
	 * @param root - The root (does not have to be the root, since the root will be found) of the function.
	 */
	public void SetAST(ASTNode root)
	{
		ASTNode node = root;
		
		while (node.GetParent() != null)
			node = node.GetParent();
		
		this.root = node;
		
		InitNode(this.root);
	}
	
	/**
	 * Initializes the GUI object used to draw the specified node.
	 * 
	 * @param node - The node to create a GUI object for.
	 */
	private void InitNode(ASTNode node)
	{
		if (node != null)
			nodes.add(ASTObjectFactory.CreateASTObject(node));
		
		Iterator<ASTNode> iter = node.GetChildren().iterator();
		
		while (iter.hasNext())
			InitNode(iter.next());
	}
}
