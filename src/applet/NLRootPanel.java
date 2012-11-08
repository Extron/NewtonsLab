package applet;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import core.*;

/**
 * The main panel component that holds all of the GUI components for the applet.
 * 
 * @author Trystan Binkley-Jones
 *
 */
public class NLRootPanel extends JPanel implements ActionListener
{
	/**
	 * The puzzle component that is being used for the applet.
	 */
	PuzzleComponent puzzle;
	
	/**
	 * The AST builder component to use to for creating parameter functions.
	 */
	ASTBuilderComponent astBuilder;
	
	/**
	 * The button used to activate the puzzle.
	 */
	JButton activate;
	
	/**
	 * The button used to reset the puzzle.
	 */
	JButton reset;
	
	
	/**
	 * This is the default constructor for the panel, and it is here where the GUI will be created.
	 */
	public NLRootPanel()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//Note that a JLabel can have an icon attached to it.  We may want one.
		JLabel title = new JLabel("Newton's Lab");
		title.setFont(new Font("Arial", Font.BOLD, 36));
	
		Puzzle test = new TestPuzzle();	
		
		JTree nodeTree = new JTree(BuildNodeTree(test));
		JScrollPane nodeTreeView = new JScrollPane(nodeTree);
		
		puzzle = new PuzzleComponent(test);
		astBuilder = new ASTBuilderComponent(test.GetFunction("impulse"));
		
		puzzle.setMinimumSize(new Dimension(500, 500));
		astBuilder.setMinimumSize(new Dimension(300, 100));
		
		JSplitPane builderPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, astBuilder, nodeTreeView);
		builderPane.setDividerLocation(600);
		
		JSplitPane mainPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, puzzle, builderPane);
		mainPane.setDividerLocation(400);
		
		activate = new JButton("Activate puzzle.");
		activate.setActionCommand("activate");
		activate.addActionListener(this);
		
		reset = new JButton("Reset puzzle.");
		reset.setActionCommand("reset");
		reset.addActionListener(this);
		
		//builderPane.setVisible(false);
		
		add(title);
		add(mainPane);
		add(activate);
		add(reset);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand() == "activate")
		{
			puzzle.ActivatePuzzle();
			activate.setEnabled(false);
		}
		else if (e.getActionCommand() == "reset")
		{
			puzzle.ResetPuzzle();
			activate.setEnabled(true);
		}
	}
	
	private DefaultMutableTreeNode BuildNodeTree(Puzzle puzzle)
	{
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Function Building Nodes");
		DefaultMutableTreeNode operatorCategory = new DefaultMutableTreeNode("Operators");
		DefaultMutableTreeNode functionCategory = new DefaultMutableTreeNode("Functions");
		DefaultMutableTreeNode valueCategory = new DefaultMutableTreeNode("Constants");
		DefaultMutableTreeNode parameterCategory = new DefaultMutableTreeNode("Parameters");
		
		top.add(operatorCategory);
		top.add(functionCategory);
		top.add(valueCategory);
		top.add(parameterCategory);
		
		operatorCategory.add(new DefaultMutableTreeNode(new Add(new NullNode(), new NullNode())));
		operatorCategory.add(new DefaultMutableTreeNode(new Subtract(new NullNode(), new NullNode())));
		operatorCategory.add(new DefaultMutableTreeNode(new Multiply(new NullNode(), new NullNode())));
		operatorCategory.add(new DefaultMutableTreeNode(new Divide(new NullNode(), new NullNode())));
		operatorCategory.add(new DefaultMutableTreeNode(new Power(new NullNode(), new NullNode())));
		
		functionCategory.add(new DefaultMutableTreeNode(Power.Square(new NullNode())));
		functionCategory.add(new DefaultMutableTreeNode(Power.Cube(new NullNode())));
		functionCategory.add(new DefaultMutableTreeNode(new Sine(new NullNode())));
		functionCategory.add(new DefaultMutableTreeNode(new Cosine(new NullNode())));
		functionCategory.add(new DefaultMutableTreeNode(new Tangent(new NullNode())));
		functionCategory.add(new DefaultMutableTreeNode(new Log(new NullNode())));
		
		ValueNode customNode = new ValueNode(0);
		customNode.SetName("Custom");
		
		valueCategory.add(new DefaultMutableTreeNode(customNode));
		valueCategory.add(new DefaultMutableTreeNode(ValueNode.Pi()));
		valueCategory.add(new DefaultMutableTreeNode(ValueNode.TwoPi()));
		valueCategory.add(new DefaultMutableTreeNode(ValueNode.PiOverTwo()));
		valueCategory.add(new DefaultMutableTreeNode(ValueNode.PiOverFour()));
		valueCategory.add(new DefaultMutableTreeNode(ValueNode.g()));
		valueCategory.add(new DefaultMutableTreeNode(ValueNode.e()));
		
		
		Iterator<String> iter = puzzle.GetValueParameters().iterator();
		
		while (iter.hasNext())
			parameterCategory.add(new DefaultMutableTreeNode(new ParameterNode(puzzle, iter.next())));
		
		return top;
	}
}
