package applet;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
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
public class PuzzleComponent extends JComponent implements MouseInputListener, MouseWheelListener
{
	/**
	 * The listener for teh puzzle failed event.
	 */
	ActionListener failedListener;
	
	/**
	 * A list of the objects in the puzzle to draw.
	 */
	ArrayList<PuzzleObject> objects;
	
	/**
	 * The background image for the puzzle.
	 */
	Image background;
	
	/**
	 * This is the location of the center of the viewport of this component.
	 */
	Vector2 viewport;
	
	/**
	 * The current puzzle that is being drawn.
	 */
	Puzzle puzzle;
	
	/**
	 * The thread worker that ticks the puzzle when it is activated.
	 */
	PuzzleTickWorker tick;
	
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
	public PuzzleComponent(Applet applet, Puzzle puzzle)
	{
		this.puzzle = puzzle;
		
		String dir = System.getProperty("user.dir");
		
		background = applet.getImage(applet.getCodeBase(), puzzle.GetBackgroundImage());
		
		setSize(500, 500);
		viewport = new Vector2(250, 250);
		scale = 1;
		dt = 1.0 / 30.0;
		
		objects = new ArrayList<PuzzleObject>();
		
		InitElements();
		
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
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
		//g.drawImage(background, x, y, null);
		
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
		
		viewport.x += dx / scale;
		viewport.y += dy / scale;
		
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

	@Override
	public void mouseWheelMoved(MouseWheelEvent e)
	{
		if (e.getWheelRotation() < 0)
			scale *= e.getScrollAmount() * 0.25;
		else
			scale /= e.getScrollAmount() * 0.25;
	
		repaint();
	}
	
	public void SetFailedListener(ActionListener listener)
	{
		failedListener = listener;
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
		
			tick = new PuzzleTickWorker(this);
			tick.execute();
		}
	}

	/**
	 * Deactivates the puzzle, setting it to begin simulating.
	 */
	public void DeactivatePuzzle()
	{
		puzzle.DeactivatePuzzle();
	
		tick.cancel(false);
		
		repaint();
	}
	
	/**
	 * Resets the puzzle, stopping it from ticking.
	 */
	public void ResetPuzzle()
	{
		tick.cancel(false);
		puzzle.ResetPuzzle();
		
		repaint();
	}
	
	/**
	 * Ticks the puzzle one frame.
	 */
	public void TickPuzzle()
	{
		puzzle.Tick(dt);
		
		if (puzzle.HasFailed())
			failedListener.actionPerformed(new ActionEvent(this, 0, "failed"));
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
