package core;

public abstract class Puzzle
{
	/**
	 * Initializes the puzzle, setting up all objects and relations that need to be set up.
	 */
	public abstract void InitializePuzzle();
	
	/**
	 * Runs the puzzle for one time step.
	 * 
	 * @param dt - The length of the time step.
	 */
	public abstract void Tick(float dt);
	
	/**
	 * Determines if the puzzle is in a winning state.
	 * 
	 * @return Returns true if the puzzle has been won, false if not.
	 */
	public abstract Boolean HasWon();
	
	/**
	 * Gets the value of a parameter in the puzzle.
	 * 
	 * @param parameter - The name of the parameter to get.
	 * @return Returns the value of the parameter.
	 */
	public abstract double GetParameter(String parameter);
}
