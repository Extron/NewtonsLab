package core;

import java.util.ArrayList;
import java.util.HashMap;

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
	 * A list of parameter functions that the puzzle has.
	 */
	protected HashMap<String, ASTNode> functions;
	
	/**
	 * The direction and magnitude of gravity for the puzzle.
	 */
	protected Vector2 gravity;
	
	/**
	 * The file path and name of a background image for this puzzle.
	 */
	protected String backgroundImage;
	
	/**
	 * A tip that users can use to help them solve the puzzle.  These tips will generally
	 * contain an overview of the physics behind the puzzle.
	 */
	protected String tip;
	
	/**
	 * Indicates whether the puzzle is currently simulating or not.
	 */
	protected boolean active;
	
	
	/**
	 * Creates a new puzzle.
	 */
	public Puzzle()
	{
		gravity = new Vector2(0, 9.8);
		elements = new ArrayList<PuzzleElement>();
		functions = new HashMap<String, ASTNode>();
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
	 * Sets a function parameter in the puzzle to a specified function.
	 * 
	 * @param parameter - The parameter name to set.
	 * @param root - The root of the function AST.
	 */
	public void SetFunction(String parameter, ASTNode root)
	{
		functions.put(parameter, root);
	}

	/**
	 * Gets a function parameter in the puzzle.
	 * 
	 * @param parameter - The name of the parameter.
	 * @return Returns the root of the function.
	 */
	public ASTNode GetFunction(String parameter)
	{
		return functions.get(parameter);
	}
	
	/**
	 * Gets a list of function parameters in the puzzle.
	 * 
	 * @return Returns the list of function parameters.
	 */
	public ArrayList<String> GetFunctionParameters()
	{
		return new ArrayList<String>(functions.keySet());
	}

	
	
	/**
	 * Initializes the puzzle, setting up all objects and relations that need to be set up.
	 */
	public abstract void InitializePuzzle();
	
	/**
	 * Resets the puzzle to its initial state.
	 */
	public abstract void ResetPuzzle();
	
	/**
	 * Activates the puzzle, setting it to begin simulating.
	 */
	public abstract void ActivatePuzzle();
	
	/**
	 * Deactivates the puzzle, stopping the simulation.
	 */
	public abstract void DeactivatePuzzle();
	
	/**
	 * Runs the puzzle for one time step.
	 * 
	 * @param dt - The length of the time step.
	 */
	public abstract void Tick(double dt);

	/**
	 * Determines if the puzzle is in a winning state.
	 * 
	 * @return Returns true if the puzzle has been won, false if not.
	 */
	public abstract boolean HasWon();
	
	/**
	 * Determines if the puzzle has been failed.
	 * 
	 * @return Returns true if the puzzle has failed.
	 */
	public abstract boolean HasFailed();
	
	/**
	 * Determines if the puzzle can currently be activated.
	 * @return
	 */
	public abstract boolean CanActivate();
	
	/**
	 * Gets the value of a parameter in the puzzle.
	 * 
	 * @param parameter - The name of the parameter to get.
	 * @return Returns the value of the parameter.
	 */
	public abstract double GetParameter(String parameter);	
	
	/**
	 * Gets a list of function parameters in the puzzle.
	 * 
	 * @return Returns the list of function parameters.
	 */
	public abstract ArrayList<String> GetValueParameters();


	public String GetBackgroundImage()
	{
		return backgroundImage;
	}


	public boolean IsActive()
	{
		return active;
	}


	public String GetTip()
	{
		return tip;
	}
}
