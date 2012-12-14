package applet;

import core.CannonPuzzle;
import core.Puzzle;

public class PuzzleFactory
{
	public static Puzzle CreatePuzzle(String puzzleName)
	{
		if (puzzleName == "Cannon Puzzle")
			return new CannonPuzzle();
		
		return null;
	}
}
