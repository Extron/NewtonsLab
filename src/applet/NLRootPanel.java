package applet;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import core.*;

/**
 * The main panel component that holds all of the GUI components for the applet.
 * 
 * @author Trystan Binkley-Jones
 *
 */
public class NLRootPanel extends JPanel implements ActionListener, ListSelectionListener
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
	
	JSplitPane mainPane;
	
	/**
	 * The button used to reset the puzzle.
	 */
	JButton reset;
	
	JLabel failed;
	
	/**
	 * The current puzzle.
	 */
	Puzzle currPuzzle;
	
	/**
	 * The current score for the puzzle.
	 */
	double score;
	
	//This is used to tell the applet that the puzzle is finished
	boolean finished;
	JLabel title;
	String pname, uname, scoreStr;
	
	/**
	 * This is the default constructor for the panel, and it is here where the GUI will be created.
	 */
	public NLRootPanel(Applet parent, String p, String u, String s)
	{
		pname = p;
		uname = u;
		
		try
		{
			score = Double.parseDouble(s);
		}
		catch (NumberFormatException ex)
		{
			score = 1000;
		}
		catch (NullPointerException ex)
		{
			score = 1000;
		}
		
			
		currPuzzle = PuzzleFactory.CreatePuzzle(pname);
		
		if (currPuzzle == null)
			currPuzzle = new CannonPuzzle();
		
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//Note that a JLabel can have an icon attached to it.  We may want one.
		//JLabel title = new JLabel("Newton's Lab");
		title = new JLabel(pname+"    "+uname+"'s Score: "+score);
		title.setFont(new Font("Arial", Font.BOLD, 36));
	
		
		failed = new JLabel("Puzzle Failed");
		failed.setFont(new Font("Arial", Font.BOLD, 36));
		failed.setForeground(Color.red);
		failed.setVisible(false);

		puzzle = new PuzzleComponent(parent, currPuzzle);
		puzzle.SetFailedListener(this);
		
		JList functionList = new JList(currPuzzle.GetFunctionParameters().toArray());
		functionList.addListSelectionListener(this);
		
		JSplitPane puzzlePane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, puzzle, functionList);
		
		astBuilder = new ASTBuilderComponent(currPuzzle, "Cannon Angle");
		
		JButton acceptButton = new JButton("Accept");
		acceptButton.setActionCommand("Accept");
		acceptButton.addActionListener(this);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		cancelButton.addActionListener(this);
		
		JPanel astPane = new JPanel();
		
		astPane.setLayout(new BoxLayout(astPane, BoxLayout.Y_AXIS));
		astPane.add(astBuilder);
		astPane.add(acceptButton);
		astPane.add(cancelButton);
		
		puzzle.setMinimumSize(new Dimension(500, 500));
		astBuilder.setMinimumSize(new Dimension(300, 100));
		
		JSplitPane builderPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, astPane, BuildButtonGrid(currPuzzle));
		builderPane.setDividerLocation(500);
		
		mainPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, puzzlePane, builderPane);
		mainPane.setDividerLocation(475);
		
		activate = new JButton("Activate puzzle.");
		activate.setActionCommand("activate");
		activate.addActionListener(this);
		
		reset = new JButton("Reset puzzle.");
		reset.setActionCommand("reset");
		reset.addActionListener(this);
		
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
		else if (e.getActionCommand() == "failed")
		{
			puzzle.DeactivatePuzzle();
			failed.setVisible(true);
			activate.setEnabled(true);
			score -= 100;
			
			title.setText(pname + "    " + uname + "'s Score: " + score);
		}
		else if (e.getActionCommand() == "Accept")
		{
			currPuzzle.SetFunction(astBuilder.GetFunctionName(), astBuilder.GetAST());
			
			mainPane.setDividerLocation(1.0);
		}
		else if (e.getActionCommand() == "Cancel")
		{
			mainPane.setDividerLocation(1.0);
		}
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e)
	{
		if (!e.getValueIsAdjusting())
		{
			JList list = (JList)e.getSource();
			
			int index = list.getSelectedIndex();
			
			if (index > -1)
			{
				String selection = (String)list.getModel().getElementAt(list.getSelectedIndex());
				
				astBuilder.SetAST(selection);
				mainPane.setDividerLocation(0.75);
				
				list.clearSelection();
			}
		}
	}
	
	private JComponent BuildButtonGrid(Puzzle puzzle)
	{
		JTabbedPane pane = new JTabbedPane();
		
		GridBagLayout layout = new GridBagLayout();
		
		String[] opButtons = { "Add", "Subtract", "Multiply", "Divide", "Power", "Square", "Cube" };
		JPanel operatorPanel = new JPanel(layout);
		
		for (int i = 0; i < opButtons.length; i++)
		{
			JButton button = new JButton(opButtons[i]);
			button.setBackground(Color.red);
			button.setActionCommand(opButtons[i]);
			button.addActionListener(astBuilder);
			
			GridBagConstraints c = new GridBagConstraints();
			c.gridx = i % 3;
			c.gridy = i / 3;
			c.fill = GridBagConstraints.HORIZONTAL;
			
			operatorPanel.add(button, c);
		}

		pane.addTab("Operations", operatorPanel);
		
		String[] funcButtons = { "Sine", "Cosine", "Tangent", "Nat. Log" };
		JPanel functionPanel = new JPanel(layout);
		
		for (int i = 0; i < funcButtons.length; i++)
		{
			JButton button = new JButton(funcButtons[i]);
			button.setBackground(Color.green);
			button.setActionCommand(funcButtons[i]);
			button.addActionListener(astBuilder);
			

			GridBagConstraints c = new GridBagConstraints();
			c.gridx = i % 3;
			c.gridy = i / 3;
			c.fill = GridBagConstraints.HORIZONTAL;
			
			functionPanel.add(button, c);
		}
		
		pane.addTab("Functions", functionPanel);
		
		
		String[] valueButtons = { "Custom", "Pi", "2Pi", "Pi/2", "Pi/4", "g", "e" };
		JPanel valuePanel = new JPanel(layout);
		
		for (int i = 0; i < valueButtons.length; i++)
		{
			JButton button = new JButton(valueButtons[i]);
			button.setBackground(Color.blue);
			button.setActionCommand(valueButtons[i]);
			button.addActionListener(astBuilder);
			
			GridBagConstraints c = new GridBagConstraints();
			c.gridx = i % 3;
			c.gridy = i / 3;
			c.fill = GridBagConstraints.HORIZONTAL;
			
			valuePanel.add(button, c);
		}

		pane.addTab("Values", valuePanel);
		
		
		Iterator<String> iter = puzzle.GetValueParameters().iterator();
		JPanel parameterPanel = new JPanel(layout);
		
		int i = 0;
		
		while (iter.hasNext())
		{
			String parameter = iter.next();
			
			JButton button = new JButton(parameter);
			button.setBackground(Color.orange);
			button.setActionCommand(parameter);
			button.addActionListener(astBuilder);
			
			GridBagConstraints c = new GridBagConstraints();
			c.gridx = i % 3;
			c.gridy = i / 3;
			c.fill = GridBagConstraints.HORIZONTAL;
			
			i++;
			
			parameterPanel.add(button, c);
		}
		
		pane.addTab("Parameters", parameterPanel);
		
		JPanel panel = new JPanel(new GridLayout(1, 4));
		
		JButton delim1 = new JButton("(");
		delim1.setActionCommand("Open Deliminator");
		delim1.addActionListener(astBuilder);
		
		panel.add(delim1);
		
		JButton delim2 = new JButton(")");
		delim2.setActionCommand("Close Deliminator");
		delim2.addActionListener(astBuilder);
		
		panel.add(delim2);
		
		JButton remove = new JButton("\u2190");
		remove.setActionCommand("Remove");
		remove.addActionListener(astBuilder);
		
		panel.add(remove);
		
		JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pane, panel);
		split.setDividerLocation(175);
		
		return split;
	}

	public DefaultMutableTreeNode BuildPuzzleFunctionTree(Puzzle puzzle)
	{
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Functions");
		
		Iterator<String> iter = puzzle.GetValueParameters().iterator();
		
		while (iter.hasNext())
		{
			String parameter = iter.next();
			
			root.add(new DefaultMutableTreeNode(parameter));
		}
		
		return root;
	}
	
	public boolean GetFinished() {
		return currPuzzle.HasWon();
	}
	
	public String getScore(){
		return "" + score;
	}

}
