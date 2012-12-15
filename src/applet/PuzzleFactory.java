package applet;

import core.CannonPuzzle;
import core.OrbitPuzzle;
import core.Puzzle;

public class PuzzleFactory
{
	public static Puzzle CreatePuzzle(String puzzleName)
	{
		if (puzzleName == "Cannon Puzzle")
			return new CannonPuzzle();
		else if (puzzleName == "Orbit Puzzle")
			return new OrbitPuzzle();
		
		return null;
	}
}
