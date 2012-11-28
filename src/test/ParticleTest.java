package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import core.ASTNode;
import core.Particle;
import core.Puzzle;
import core.Vector2;


public class ParticleTest
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
		
		Particle particle = new Particle(puzzle);
		
		particle.Tick(1);
		assertEquals(particle.GetMomentum().y, 10.0, 0.000001);
		assertEquals(particle.GetPosition().y, 10.0, 0.000001);
		
		particle.Tick(1);
		assertEquals(particle.GetMomentum().y, 20.0, 0.000001);
		assertEquals(particle.GetPosition().y, 30.0, 0.000001);
		
		particle.Tick(1);
		assertEquals(particle.GetMomentum().y, 30.0, 0.000001);
		assertEquals(particle.GetPosition().y, 60.0, 0.000001);
		
		particle.AddInstantaneousForce(new Vector2(10, 0), 1);
		assertEquals(particle.GetMomentum().x, 10, 0.000001);
		
		particle.Tick(1);
		assertEquals(particle.GetMomentum().y, 40.0, 0.000001);
		assertEquals(particle.GetPosition().y, 100.0, 0.000001);
		assertEquals(particle.GetPosition().x, 10.0, 0.000001);
	}
}
