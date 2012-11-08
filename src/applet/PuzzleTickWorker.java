package applet;

import java.util.List;

import javax.swing.SwingWorker;

import core.Puzzle;

/**
 * This class manages the ticking of the puzzle on the worker thread in Swing.
 * 
 * @author Trystan Binkley-Jones
 *
 */
public class PuzzleTickWorker extends SwingWorker<Void, Void>
{
	/**
	 * The component that we are ticking for.
	 */
	PuzzleComponent component;
	
	/**
	 * The minimum amount of time that should pass for one tick.
	 */
	double tickSpeed;
	
	
	/**
	 * Creates a new puzzle tick worker to run on the worker thread.
	 * 
	 * @param component - The puzzle component to tick.
	 */
	public PuzzleTickWorker(PuzzleComponent component)
	{
		this.component = component;
		
		tickSpeed = 1 / 16.0;
	}
	
	@Override
	/**
	 * Ticks the puzzle at an interval specified by the tickSpeed variable, or as fast as it can.
	 */
	protected Void doInBackground() throws Exception
	{
		Puzzle puzzle = component.GetPuzzle();
		
		while (!puzzle.HasWon() || isCancelled())
		{
			long start = System.nanoTime();
			
			component.TickPuzzle();
			
			long stop = System.nanoTime();
			
			double dt = (stop - start) / 1000000000;
			
			if (tickSpeed > dt)
				Thread.sleep((long)(tickSpeed - dt) * 1000000);
			
			publish();
		}
		
		return null;
	}

	@Override
	/**
	 * This is overridden to force the component to repaint.
	 */
	protected void process(List<Void> list)
	{
		component.repaint();
	}
}
