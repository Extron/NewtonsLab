package applet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JComponent;
import javax.swing.event.MouseInputListener;

import core.Puzzle;
import core.PuzzleElement;
import core.Vector2;

/**
 * This is where we will display a puzzle.  By extending the <code>JComponent</code> class, we can
 * plug this directly into the Swing GUI.
 * 
 * @author Trystan Binkley-Jones
 *
 */
public class PuzzleComponent extends JComponent implements MouseInputListener
{
	/**
	 * A list of the objects in the puzzle to draw.
	 */
	ArrayList<PuzzleObject> objects;
	
	/**
	 * This is the location of the center of the viewport of this component.
	 */
	Vector2 viewport;
	
	/**
	 * The current puzzle that is being drawn.
	 */
	Puzzle puzzle;
	
	/**
	 * The scale of the viewport.
	 */
	double scale;
	
	/**
	 * The delta time step to use for puzzle ticking.
	 */
	double dt;
	
	/**
	 * The old mouse coordinates.
	 */
	int mX, mY;
	
	
	/**
	 * Creates a new puzzle component.
	 * 
	 * @param puzzle - The current puzzle to display.
	 */
	public PuzzleComponent(Puzzle puzzle)
	{
		this.puzzle = puzzle;
		
		setSize(500, 500);
		viewport = new Vector2(250, 250);
		scale = 1;
		dt = 1.0 / 16.0;
		
		objects = new ArrayList<PuzzleObject>();
		
		InitElements();
		
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	
	/**
	 * Allows the component to be drawn to the screen.
	 */
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
		Iterator<PuzzleObject> iter = objects.iterator();
		
		while (iter.hasNext())
			iter.next().Draw(g, viewport, scale);
	}
	
	@Override
	public void mouseMoved(MouseEvent e) 
	{
		mX = e.getX();
		mY = e.getY();
		
		//System.out.println("Mouse Pos:" + mX + " " + mY);
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		// TODO Auto-generated method stub
		double dx = e.getX() - mX;
		double dy = e.getY() - mY;
		
		viewport.x += dx;
		viewport.y += dy;
		
		mX = e.getX();
		mY = e.getY();
		
		repaint();
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
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	
	
	/**
	 * Gets the puzzle that the puzzle component is drawing.
	 * 
	 * @return Returns the puzzle component's current puzzle.
	 */
	public Puzzle GetPuzzle()
	{
		return puzzle;
	}
	
	/**
	 * Activates the puzzle, setting it to begin simulating.
	 */
	public void ActivatePuzzle()
	{
		if (puzzle.CanActivate())
		{
			puzzle.ActivatePuzzle();
		
			PuzzleTickWorker tick = new PuzzleTickWorker(this);
			tick.execute();
		}
	}
	
	/**
	 * Ticks the puzzle one frame.
	 */
	public void TickPuzzle()
	{
		puzzle.Tick(dt);
	}
	
	/**
	 * Initializes all of the puzzle objects to display from the puzzle's elements.
	 */
	private void InitElements()
	{
		puzzle.InitializePuzzle();
		
		ArrayList<PuzzleElement> elements = puzzle.GetElements();
		
		Iterator<PuzzleElement> iter = elements.iterator();
		
		while (iter.hasNext())
			objects.add(PuzzleObjectFactory.CreateObject(iter.next()));
	}
}
