package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import core.ASTNode;
import core.Puzzle;
import core.Satellite;
import core.Vector2;

public class SatelliteTest
{
	class EmptyPuzzle extends Puzzle
	{
		public EmptyPuzzle()
		{
			gravity = new Vector2(0, 10);
		}
		
		
		@Override
		public void InitializePuzzle()
		{			
		}

		@Override
		public void ActivatePuzzle()
		{
		}

		@Override
		public void Tick(double dt)
		{
		}

		@Override
		public void SetFunction(String parameter, ASTNode root)
		{
		}

		@Override
		public boolean HasWon()
		{
			return false;
		}

		@Override
		public double GetParameter(String parameter)
		{
			return 0;
		}

		@Override
		public boolean CanActivate()
		{
			return false;
		}


		@Override
		public void ResetPuzzle()
		{
			// TODO Auto-generated method stub
			
		}


		@Override
		public ArrayList<String> GetValueParameters()
		{
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public boolean HasFailed()
		{
			// TODO Auto-generated method stub
			return false;
		}


		@Override
		public void DeactivatePuzzle()
		{
			// TODO Auto-generated method stub
			
		}
		
	}
	
	@Test
	public void testTick()
	{
		EmptyPuzzle puzzle = new EmptyPuzzle();
		
		Satellite sat = new Satellite(puzzle, new Vector2(100, 0), 1);
		
		double d = sat.GetPosition().x;
		sat.Tick(1);
		double x = sat.GetPosition().x;
		
		assertEquals(x, d - 1.0 / (d * d), 0.00001);
		
		
		d = x;
		sat.Tick(1);
		x = sat.GetPosition().x;
		
		assertEquals(x, d - 1.0 / (d * d), 0.0001);
		
		
		d = x;
		sat.Tick(1);
		x = sat.GetPosition().x;
		
		assertEquals(x, d - 1.0 / (d * d), 0.001);
		
		sat.SetPosition(new Vector2(100, 0));
		sat.SetMomentum(new Vector2(0, 10));
		
		d = x;
		sat.Tick(1);
		x = sat.GetPosition().x;
		double y = sat.GetPosition().y;
		
		assertEquals(x, 100 - 1.0 / (100.0 * 100.0), 0.0001);
		assertEquals(y, 10, 0.0001);
		
	}

}
