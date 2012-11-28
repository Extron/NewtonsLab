package applet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JComponent;
import javax.swing.event.MouseInputListener;

import core.*;

/**
 * This component manages the function designer that players use to create parameters for the puzzles.
 * 
 * @author Trystan Binkley-Jones
 *
 */
public class ASTBuilderComponent extends JComponent implements MouseInputListener
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
	 * This determines the strength of the node object magnetism 
	 */
	float magnetPower;
	
	
	/**
	 * Creates a new AST function builder tool.
	 * 
	 * @param root - The root of the AST function to manipulate.
	 */
	public ASTBuilderComponent(Puzzle puzzle, ASTNode root)
	{
		this.puzzle = puzzle;
		this.root = root;
		
		nodes = new ArrayList<ASTNodeObject>();
		orphans = new ArrayList<ASTNode>();
		magnetPower = 50;
		
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
		// TODO Auto-generated method stub
		
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
		Iterator<ASTNodeObject> iter = nodes.iterator();
		
		while (iter.hasNext())
		{
			ASTNodeObject next = iter.next();
			
			if (next.MouseOver(e.getX(), e.getY()))
			{
				selectedObj = next;
				selObjOffset = Vector2.Subtract(selectedObj.position, new Vector2(e.getX(), e.getY()));
				break;
			}
		}
	}


	@Override
	public void mouseReleased(MouseEvent e)
	{
		selectedObj = null;	
	}


	@Override
	public void mouseDragged(MouseEvent e)
	{
		if (selectedObj != null)
		{
			//If we are a child, then we are forced to a position.  So if we have moved
			//the mouse far enough away, break the connection.
			if (selectedObj.node.GetParent() != null)
			{
				if (Vector2.Distance(new Vector2(e.getX() + selObjOffset.x, e.getY() + selObjOffset.y), selectedObj.position) > magnetPower)
				{
					selectedObj.node.ClearParent();
					orphans.add(selectedObj.node);
				}
			}
			
			selectedObj.position.x = e.getX() + selObjOffset.x;
			selectedObj.position.y = e.getY() + selObjOffset.y;
			
			if (selectedObj.node.GetParent() == null)
			{
				Iterator<ASTNodeObject> iter = nodes.iterator();
				
				while (iter.hasNext())
				{
					ASTNodeObject nodeObj = iter.next();
					
					if (nodeObj == selectedObj || selectedObj.node.GetChildren().contains(nodeObj.node))
						continue;
					
					Iterator<ASTNodeObject.MagnetPoint> magIter = selectedObj.magnetPoints.iterator();
					
					while (magIter.hasNext())
					{
						ASTNodeObject.MagnetPoint iterMag = magIter.next();
						
						if (nodeObj.IsNearMagnet(iterMag, magnetPower))
						{
							ASTNodeObject.MagnetPoint magnet = nodeObj.GetMagnetPoint(iterMag);
							
							if (magnet != null)
							{
								selectedObj.SnapToMagnet(iterMag, magnet);
								selectedObj.ConnectNode(nodeObj, iterMag);
								break;
							}
						}
					}
				}
			}
			
			PositionTree();
			repaint();
		}
	}


	@Override
	public void mouseMoved(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
	}
	
	public void DropNode(String nodeStr, Point point)
	{
		ASTNode node = null;
		ASTNodeObject nodeObj;
		
		if (nodeStr.contains("Add"))
			node = new Add(new NullNode(), new NullNode());
		else if (nodeStr.contains("Subtract"))
			node = new Subtract(new NullNode(), new NullNode());
		else if (nodeStr.contains("Multiply"))
			node = new Multiply(new NullNode(), new NullNode());
		else if (nodeStr.contains("Divide"))
			node = new Divide(new NullNode(), new NullNode());
		else if (nodeStr.contains("Power"))
			node = new Power(new NullNode(), new NullNode());
		else if (nodeStr.contains("Square"))
			node = Power.Square(new NullNode());
		else if (nodeStr.contains("Cube"))
			node = Power.Cube(new NullNode());
		else if (nodeStr.contains("Sine"))
			node = new Sine(new NullNode());
		else if (nodeStr.contains("Cosine"))
			node = new Cosine(new NullNode());
		else if (nodeStr.contains("Tangent"))
			node = new Tangent(new NullNode());
		else if (nodeStr.contains("Narutal Log"))
			node = new Log(new NullNode());
		else if (nodeStr.contains("Custom"))
			node = new ValueNode();
		else if (nodeStr.contains("\u03C0"))
			node = ValueNode.Pi();
		else if (nodeStr.contains("\u03C0 / 2"))
			node = ValueNode.PiOverTwo();
		else if (nodeStr.contains("\u03C0 / 4"))
			node = ValueNode.PiOverFour();
		else if (nodeStr.contains("2\u03C0"))
			node = ValueNode.TwoPi();
		else if (nodeStr.contains("e") && nodeStr.length() == 1)
			node = ValueNode.e();
		else if (nodeStr.contains("g") && nodeStr.length() == 1)
			node = ValueNode.g();
		else
			node = new ParameterNode(puzzle, nodeStr);
		
		if (node == null)
			return;
			
		orphans.add(node);
			
		nodeObj = ASTObjectFactory.CreateASTObject(node);
		nodeObj.position = new Vector2(point.x, point.y);
		
		nodes.add(nodeObj);
		
		Iterator<ASTNode> iter = node.GetChildren().iterator();
		
		while (iter.hasNext())
			InitNode(iter.next());
		
		PositionTree();
		repaint();
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
			nodes.add(ASTObjectFactory.CreateASTObject(node));
		
		Iterator<ASTNode> iter = node.GetChildren().iterator();
		
		while (iter.hasNext())
			InitNode(iter.next());
	}	
	
	private void PositionTree()
	{
		ASTNodeObject rootObj = GetNodeObject(root);
		
		if (rootObj != null)
			PositionNodeObject(rootObj);
		
		Iterator<ASTNode> iter = orphans.iterator();
		
		while (iter.hasNext())
		{
			ASTNodeObject nodeObj = GetNodeObject(iter.next());
			
			if (nodeObj != null)
				PositionNodeObject(nodeObj);
		}
	}
	
	private void PositionNodeObject(ASTNodeObject nodeObj)
	{			
		Iterator<ASTNode> iter = nodeObj.node.GetChildren().iterator();
			
		while (iter.hasNext())
		{
			ASTNode child = iter.next();
			ASTNodeObject childObj = GetNodeObject(child);
			
			if (childObj != null)
			{
				ForceChildPosition(nodeObj, childObj);
				PositionNodeObject(childObj);
			}
		}
	}
	
	private void ForceChildPosition(ASTNodeObject parent, ASTNodeObject child)
	{
		if (parent instanceof OperatorObject)
		{
			//The child is the left node of the operator.
			if (parent.node.GetChildren().get(0) == child.node)
			{
				child.position.x = parent.position.x - child.size.width;
				child.position.y = parent.position.y + parent.size.height / 2.0 - child.size.height / 2.0;
			}
			else
			{
				child.position.x = parent.position.x + parent.size.width;
				child.position.y = parent.position.y + parent.size.height / 2.0 - child.size.height / 2.0;
			}
		}
	}
}
