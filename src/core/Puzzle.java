package core;

import java.util.ArrayList;

/**
 * A puzzle contains a set of physics objects that can be manipulated through functions and parameters.
 * 
 * @author Trystan Binkley-Jones
 *
 */
public abstract class Puzzle
{
	/**
	 * A list of the elements in the puzzle.
	 */
	protected ArrayList<PuzzleElement> elements;
	
	

	/**
	 * The direction and magnitude of gravity for the puzzle.
	 */
	protected Vector2 gravity;
	
	/**
	 * Indicates whether the puzzle is currently simulating or not.
	 */
	protected boolean active;
	
	
	/**
	 * Creates a new puzzle.
	 */
	public Puzzle()
	{
		gravity = new Vector2(0, -9.8);
	}
	
	
	/**
	 * Gets the list of elements in the puzzle.
	 * 
	 * @return - Returns the element list.
	 */
	public ArrayList<PuzzleElement> GetElements()
	{
		return elements;
	}
	
	/**
	 * Gets the puzzle's gravity.
	 * 
	 * @return Returns the puzzle's gravity.
	 */
	public Vector2 GetGravity()
	{
		return gravity;
	}
	
	
	/**
	 * Initializes the puzzle, setting up all objects and relations that need to be set up.
	 */
	public abstract void InitializePuzzle();
	
	/**
	 * Activates the puzzle, setting it to begin simulating.
	 */
	public abstract void ActivatePuzzle();
	
	/**
	 * Runs the puzzle for one time step.
	 * 
	 * @param dt - The length of the time step.
	 */
	public abstract void Tick(float dt);
	
	/**
	 * Sets a function parameter in the puzzle to a specified function.
	 * 
	 * @param parameter - The parameter name to set.
	 * @param root - The root of the function AST.
	 */
	public abstract void SetFunction(String parameter, ASTNode root);
	
	/**
	 * Determines if the puzzle is in a winning state.
	 * 
	 * @return Returns true if the puzzle has been won, false if not.
	 */
	public abstract boolean HasWon();
	
	/**
	 * Gets the value of a parameter in the puzzle.
	 * 
	 * @param parameter - The name of the parameter to get.
	 * @return Returns the value of the parameter.
	 */
	public abstract double GetParameter(String parameter);
}
