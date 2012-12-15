package applet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.event.MouseInputListener;

import core.*;
import core.Deliminator.DelimType;

/**
 * This component manages the function designer that players use to create parameters for the puzzles.
 * 
 * @author Trystan Binkley-Jones
 *
 */
public class ASTBuilderComponent extends JComponent implements MouseInputListener, ActionListener
{
	/**
	 * A list of GUI objects to draw to represent the function.
	 */
	ArrayList<ASTNodeObject> nodes;
	
	/**
	 * When drag-dropping nodes into the function builder, initially they will not have 
	 * any parent node, or be attached to the final AST tree.  So we must keep track of 
	 * them here.
	 */
	ArrayList<ASTNode> orphans;
	
	/**
	 * The puzzle that the function belongs to.
	 */
	Puzzle puzzle;
	
	/**
	 * The plugin that plugs the function into the puzzle.
	 */
	EqualsObject equals;
	
	/**
	 * The root node of the AST function to draw and manipulate.
	 */
	ASTNode root;
	
	/**
	 * The selected object that is being clicked on in th builder.
	 */
	ASTNodeObject selectedObj;
	
	/**
	 * The positional offset between the mouse's cursor and the selected object's position.
	 */
	Vector2 selObjOffset;
	
	/**
	 * The name of the function currently being edited.
	 */
	String functionName;
	
	/**
	 * This determines the strength of the node object magnetism 
	 */
	float magnetPower;
	
	
	/**
	 * Creates a new AST function builder tool.
	 * 
	 * @param root - The root of the AST function to manipulate.
	 */
	public ASTBuilderComponent(Puzzle puzzle, String functionName)
	{
		this.puzzle = puzzle;
		this.functionName = functionName;
		
		root = puzzle.GetFunction(functionName);
		
		nodes = new ArrayList<ASTNodeObject>();
		orphans = new ArrayList<ASTNode>();
		equals = new EqualsObject();
		magnetPower = 50;
		
		equals.position = new Vector2(0, 50);
		
		nodes.add(equals);
		
		setSize(500, 500);
		
		InitNode(root);
		
		addMouseListener(this);
		addMouseMotionListener(this);
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
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		if (e.getClickCount() == 2)
		{
			Iterator<ASTNodeObject> iter = nodes.iterator();
			
			while (iter.hasNext())
			{
				ASTNodeObject next = iter.next();
				
				if (next instanceof ValueObject)
				{
					if (next.MouseOver(e.getX(), e.getY()))
					{
						String valueStr = (String)JOptionPane.showInputDialog(this, "Enter a value:", "New Value", JOptionPane.PLAIN_MESSAGE, null, null, "0.0");
						
						try
						{
							double value = Double.parseDouble(valueStr);
							
							((ValueNode)next.node).SetValue(value);
							
							repaint();
						}
						catch (NumberFormatException exception)
						{
						}
						finally
						{
						}
						
						break;
					}
				}
			}
		}
	}


	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e)
	{
	}


	@Override
	public void mouseReleased(MouseEvent e)
	{
		selectedObj = null;	
	}


	@Override
	public void mouseDragged(MouseEvent e)
	{
	}


	@Override
	public void mouseMoved(MouseEvent e)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String command = e.getActionCommand();
		ASTNode node;
		ASTNodeObject nodeObj;
		
		// TODO Auto-generated method stub
		if (command.contains("Add"))
			node = new Add(new NullNode(), new NullNode());
		else if (command.contains("Subtract"))
			node = new Subtract(new NullNode(), new NullNode());
		else if (command.contains("Multiply"))
			node = new Multiply(new NullNode(), new NullNode());
		else if (command.contains("Divide"))
			node = new Divide(new NullNode(), new NullNode());
		else if (command.contains("Power"))
			node = new Power(new NullNode(), new NullNode());
		else if (command.contains("Square Root"))
			node = Power.SquareRoot(new NullNode());
		else if (command.contains("Square"))
			node = Power.Square(new NullNode());
		else if (command.contains("Cube"))
			node = Power.Cube(new NullNode());
		else if (command.contains("Sine"))
			node = new Sine(new NullNode());
		else if (command.contains("Cosine"))
			node = new Cosine(new NullNode());
		else if (command.contains("Tangent"))
			node = new Tangent(new NullNode());
		else if (command.contains("Narutal Log"))
			node = new Log(new NullNode());
		else if (command.contains("Custom"))
			node = new ValueNode();
		else if (command.contains("Pi/4"))
			node = ValueNode.PiOverFour();
		else if (command.contains("Pi/2"))
			node = ValueNode.PiOverTwo();
		else if (command.contains("2Pi"))
			node = ValueNode.TwoPi();
		else if (command.contains("Pi"))
			node = ValueNode.Pi();
		else if (command.contains("e") && command.length() == 1)
			node = ValueNode.e();
		else if (command.contains("g") && command.length() == 1)
			node = ValueNode.g();
		else if (command.contains("Open Deliminator"))
			node = new Deliminator(DelimType.open);
		else if (command.contains("Close Deliminator"))
			node = new Deliminator(DelimType.closed);
		else if (command.contains("Remove"))
		{
			if (nodes.size() > 1)
			{
				nodes.remove(nodes.size() - 1);
				repaint();
			}
			
			return;
		}
		else
			node = new ParameterNode(puzzle, command);
		
		ASTNodeObject lastNode = null;
		
		if (nodes.size() > 0)
			lastNode = nodes.get(nodes.size() - 1);
		
		if (node instanceof Operator)
		{
			
			if (!(lastNode.node instanceof ValueNode) && !(lastNode.node instanceof ParameterNode))
			{
				if (lastNode.node instanceof Deliminator)
				{
					if (((Deliminator)lastNode.node).GetType() != DelimType.closed)
						return;
				}
				else
				{
					return;
				}
			}
		}
		else if (node instanceof ValueNode || node instanceof ParameterNode)
		{
			if (!(lastNode.node instanceof Operator) && !(lastNode.node instanceof Function) && !(lastNode instanceof EqualsObject))
			{
				if (lastNode.node instanceof Deliminator)
				{
					if (((Deliminator)lastNode.node).GetType() != DelimType.open)
						return;
				}
				else
				{
					return;
				}
			}
		}
		else if (node instanceof Function)
		{
			if (!(lastNode.node instanceof Operator) && !(lastNode.node instanceof Function) && !(lastNode instanceof EqualsObject))
			{
				if (lastNode.node instanceof Deliminator)
				{
					if (((Deliminator)lastNode.node).GetType() != DelimType.open)
						return;
				}
				else
				{
					return;
				}
			}
		}
		else if (node instanceof Deliminator)
		{
			if (((Deliminator)node).GetType() == DelimType.open)
			{
				if (!(lastNode.node instanceof Operator) && !(lastNode.node instanceof Function) && !(lastNode instanceof EqualsObject))
					return;
			}
			else
			{
				if (!(lastNode.node instanceof ValueNode) && !(lastNode.node instanceof ParameterNode))
					return;
				
				int count = 0;
				
				for (int i = nodes.size() - 1; i >= 0; i--)
				{
					if (nodes.get(i) instanceof DelimObject)
					{
						if (((Deliminator)nodes.get(i).node).GetType() == DelimType.open)
							count += 1;
						else
							count -= 1;
					}
				}
				
				if (count <= 0)
					return;
			}
		}
				
		nodeObj = ASTObjectFactory.CreateASTObject(node);
		
		double x = lastNode.GetPosition().x + lastNode.GetSize().width;
		double y = lastNode.GetPosition().y + 0.5 * (lastNode.GetSize().height - nodeObj.GetSize().height);
		nodeObj.position = new Vector2(x, y);
		
		nodes.add(nodeObj);

		if (command == "Square" || command == "Cube" || command == "Square Root")
		{
			lastNode = nodes.get(nodes.size() - 1);
			ASTNode child = node.GetChildren().get(1);
			
			ASTNodeObject childObj = ASTObjectFactory.CreateASTObject(child);
			
			x = lastNode.GetPosition().x + lastNode.GetSize().width;
			y = lastNode.GetPosition().y + 0.5 * (lastNode.GetSize().height - childObj.GetSize().height);
			childObj.position = new Vector2(x, y);
			
			nodes.add(childObj);
		}
		
		repaint();
	}
	
	/**
	 * Sets the current AST function.
	 * 
	 * @param name - The name of the function.
	 */
	public void SetAST(String name)
	{
		this.functionName = name;
		
		ASTNode node = puzzle.GetFunction(name);
		
		while (node.GetParent() != null)
			node = node.GetParent();
		
		this.root = node;
		
		nodes.clear();
		nodes.add(equals);
		
		InitNode(this.root);
	}
	
	/**
	 * Gets the name of the function being edited.
	 * 
	 * @return Returns the function's name.
	 */
	public String GetFunctionName()
	{
		return functionName;
	}
	
	/**
	 * Gets the current AST node.
	 * 
	 * @return Returns the root of the AST.
	 */
	public ASTNode GetAST()
	{
		ArrayList<ASTNode> nodeList = new ArrayList<ASTNode>(nodes.size());
		
		Iterator<ASTNodeObject> iter = nodes.iterator();
		
		while (iter.hasNext())
			nodeList.add(iter.next().node);
		
		ASTNode root = ASTNode.BuildTree(nodeList);
		
		return root;
	}
	
	public ASTNodeObject GetNodeObject(ASTNode node)
	{
		if (node == null)
			return null;
		
		Iterator<ASTNodeObject> iter = nodes.iterator();
		
		while (iter.hasNext())
		{
			ASTNodeObject nodeObj = iter.next();
			
			if (nodeObj.node == node)
				return nodeObj;
		}
		
		return null;
	}
	
	/**
	 * Initializes the GUI object used to draw the specified node.
	 * 
	 * @param node - The node to create a GUI object for.
	 */
	private void InitNode(ASTNode node)
	{
		if (node != null && !(node instanceof NullNode))
		{
			ArrayList<ASTNode> flattenedTree = new ArrayList<ASTNode>();
			
			node.Flatten(flattenedTree);
			
			for (ASTNode n : flattenedTree)
			{
				ASTNodeObject nodeObj = ASTObjectFactory.CreateASTObject(n);			
				ASTNodeObject lastNode = nodes.get(nodes.size() - 1);
				
				double x = lastNode.GetPosition().x + lastNode.GetSize().width;
				double y = lastNode.GetPosition().y + 0.5 * (lastNode.GetSize().height - nodeObj.GetSize().height);
				
				nodeObj.position = new Vector2(x, y);
				nodes.add(nodeObj);
			}
		}
	}
}
