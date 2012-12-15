package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import core.*;
import core.Deliminator.DelimType;

public class ASTNodeTest
{

	class ASTTestPuzzle extends Puzzle
	{
		public ASTTestPuzzle()
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
			if (parameter == "test parameter")
				return 10;
			
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
	public void testSolve()
	{
		ASTNode root;
		
		//Test that a value returns the correct value.
		root = new ValueNode(42);
		assertEquals(root.Solve(), 42.0, 0.00001);
		
		//Test all the basic binary operators.
		root = new Add(new ValueNode(2), new ValueNode(3));
		assertEquals(root.Solve(), 5.0, 0.00001);
		
		root = new Subtract(new ValueNode(4), new ValueNode(2));
		assertEquals(root.Solve(), 2.0, 0.00001);
		
		root = new Multiply(new ValueNode(4), new ValueNode(3));
		assertEquals(root.Solve(), 12.0, 0.00001);
		
		root = new Divide(new ValueNode(3), new ValueNode(2));
		assertEquals(root.Solve(), 3.0 / 2.0, 0.00001);
		
		root = new Power(new ValueNode(2), new ValueNode(3));
		assertEquals(root.Solve(), 8.0, 0.00001);
		
		//Now test all of the functions.
		root = new Sine(ValueNode.PiOverTwo());
		assertEquals(root.Solve(), 1.0, 0.00001);

		root = new Cosine(ValueNode.Pi());
		assertEquals(root.Solve(), -1.0, 0.00001);

		root = new Tangent(ValueNode.PiOverFour());
		assertEquals(root.Solve(), 1.0, 0.00001);
		
		root = new Log(new ValueNode(2));
		assertEquals(root.Solve(), Math.log(2), 0.00001);
		
		//Now let's test larger trees.
		root = new Add(new Multiply(new ValueNode(3), new ValueNode(4)), new Divide(new ValueNode(3), new ValueNode(2)));
		assertEquals(root.Solve(), 3.0 * 4.0 + 3.0 / 2.0, 0.00001);
		
		root = new Sine(new Multiply(ValueNode.Pi(), new ValueNode(1.5)));
		assertEquals(root.Solve(), Math.sin(1.5 * Math.PI), 0.00001);
		
		root = new Log(new Divide(new Add(new ValueNode(1), new ValueNode(1)), new Subtract(new ValueNode(4), new ValueNode(1))));
		assertEquals(root.Solve(), Math.log((1.0 + 1.0) / (4.0 - 1.0)), 0.00001);
		
		//Now lets try some boundary cases, like log(0).
		root = new Log(new ValueNode(0));
		assertEquals(root.Solve(), Double.NEGATIVE_INFINITY, 0.00001);
		
		root = new Divide(new ValueNode(1), new ValueNode(0));
		assertEquals(root.Solve(), Double.POSITIVE_INFINITY, 0.00001);
		
		//Now lets try values outside the domains of our functions.
		root = new Log(new ValueNode(-1));
		assertEquals(root.Solve(), Double.NaN, 0.00001);
		
		root = new Power(new ValueNode(-1), new ValueNode(0.5));
		assertEquals(root.Solve(), Double.NaN, 0.00001);
	}
	
	@Test
	public void testFlatten()
	{
		ASTNode root = new Add(new ValueNode(1.0), new ValueNode(2.0));
		ArrayList<ASTNode> list = new ArrayList<ASTNode>();
		
		root.Flatten(list);
		
		assertTrue(list.get(0) instanceof Deliminator);
		assertTrue(list.get(1) instanceof ValueNode);
		assertTrue(list.get(2) instanceof Add);
		assertTrue(list.get(3) instanceof ValueNode);
		assertTrue(list.get(4) instanceof Deliminator);
		
		assertEquals(((ValueNode)list.get(1)).GetValue(), 1.0, 0.00001);
		assertEquals(((ValueNode)list.get(3)).GetValue(), 2.0, 0.00001);
		
		root = new Add(new Multiply(new ValueNode(1.0), new ValueNode(2.0)), new ValueNode(3.0));
		
		list.clear();
		
		root.Flatten(list);
		
		assertTrue(list.get(0) instanceof Deliminator);
		assertTrue(list.get(1) instanceof Deliminator);
		assertTrue(list.get(2) instanceof ValueNode);
		assertTrue(list.get(3) instanceof Multiply);
		assertTrue(list.get(4) instanceof ValueNode);
		assertTrue(list.get(5) instanceof Deliminator);
		assertTrue(list.get(6) instanceof Add);
		assertTrue(list.get(7) instanceof ValueNode);
		assertTrue(list.get(8) instanceof Deliminator);
		
		assertEquals(((ValueNode)list.get(2)).GetValue(), 1.0, 0.00001);
		assertEquals(((ValueNode)list.get(4)).GetValue(), 2.0, 0.00001);
		assertEquals(((ValueNode)list.get(7)).GetValue(), 3.0, 0.00001);
		
		
		root = new Sine(new Multiply(new ValueNode(1.0), new ValueNode(2.0)));
		
		list.clear();
		
		root.Flatten(list);
		
		assertTrue(list.get(0) instanceof Sine);
		assertTrue(list.get(1) instanceof Deliminator);
		assertTrue(list.get(2) instanceof Deliminator);
		assertTrue(list.get(3) instanceof ValueNode);
		assertTrue(list.get(4) instanceof Multiply);
		assertTrue(list.get(5) instanceof ValueNode);
		assertTrue(list.get(6) instanceof Deliminator);
		assertTrue(list.get(7) instanceof Deliminator);
		
		assertEquals(((ValueNode)list.get(3)).GetValue(), 1.0, 0.00001);
		assertEquals(((ValueNode)list.get(5)).GetValue(), 2.0, 0.00001);
	}
	
	@Test
	public void testBuildTree()
	{
		ArrayList<ASTNode> list = new ArrayList<ASTNode>();
	
		ASTTestPuzzle puzzle = new ASTTestPuzzle();
		
		//Test that building a simple tree works.
		list.add(ValueNode.Pi());
		list.add(new Add());
		list.add(ValueNode.Pi());
		
		ASTNode tree = ASTNode.BuildTree(list);
		
		double solution = tree.Solve();
		assertEquals(solution, 2 * Math.PI, 0.00001);
		
		
		//Test that multiple operations are correctly handled.
		list.clear();
		
		list.add(new ValueNode(5));
		list.add(new Multiply());
		list.add(new ValueNode(3));
		list.add(new Add());
		list.add(new ValueNode(2));
		
		tree = ASTNode.BuildTree(list);
		
		solution = tree.Solve();
		assertEquals(solution, 17, 0.00001);
		
		
		//Ensure order of operations is retained.
		list.clear();
		
		list.add(new ValueNode(5));
		list.add(new Add());
		list.add(new ValueNode(3));
		list.add(new Multiply());
		list.add(new ValueNode(2));
		
		tree = ASTNode.BuildTree(list);
		
		solution = tree.Solve();
		assertEquals(solution, 11, 0.00001);
		
		
		//Ensure order of operations is retained, again.
		list.clear();
		
		list.add(new ValueNode(5));
		list.add(new Add());
		list.add(new ValueNode(3));
		list.add(new Multiply());
		list.add(new ValueNode(2));
		list.add(new Power());
		list.add(new ValueNode(2));
		
		tree = ASTNode.BuildTree(list);
		
		solution = tree.Solve();
		assertEquals(solution, 17, 0.00001);
		
		
		//Test that putting deliminators in works correctly.
		list.clear();
		
		list.add(new Deliminator(DelimType.open));
		list.add(new ValueNode(5));
		list.add(new Add());
		list.add(new ValueNode(3));
		list.add(new Deliminator(DelimType.closed));
		list.add(new Multiply());
		list.add(new ValueNode(2));
		
		tree = ASTNode.BuildTree(list);
		
		solution = tree.Solve();
		assertEquals(solution, 16, 0.00001);
		
		
		//Test that functions work correctly.
		list.clear();
		
		list.add(new Sine());
		list.add(new Deliminator(DelimType.open));
		list.add(ValueNode.PiOverTwo());
		list.add(new Deliminator(DelimType.closed));
		
		tree = ASTNode.BuildTree(list);
		
		solution = tree.Solve();
		assertEquals(solution, 1, 0.00001);
		

		//Test that functions work correctly with operators outside the function.
		list.clear();
		
		list.add(new Sine());
		list.add(new Deliminator(DelimType.open));
		list.add(ValueNode.PiOverTwo());
		list.add(new Deliminator(DelimType.closed));
		list.add(new Multiply());
		list.add(new ValueNode(5));
		
		tree = ASTNode.BuildTree(list);
		
		solution = tree.Solve();
		assertEquals(solution, 5, 0.00001);
		
		
		//Test that functions work correctly with operators inside the function.
		list.clear();
		
		list.add(new Sine());
		list.add(new Deliminator(DelimType.open));
		list.add(ValueNode.PiOverTwo());
		list.add(new Multiply());
		list.add(new ValueNode(5));
		list.add(new Deliminator(DelimType.closed));
		
		tree = ASTNode.BuildTree(list);
		
		solution = tree.Solve();
		assertEquals(solution, Math.sin(5 * Math.PI / 2), 0.00001);
		
		
		//Test that functions work correctly with operators inside and outside the function.
		list.clear();
		
		list.add(new Sine());
		list.add(new Deliminator(DelimType.open));
		list.add(ValueNode.PiOverTwo());
		list.add(new Multiply());
		list.add(new ValueNode(5));
		list.add(new Deliminator(DelimType.closed));
		list.add(new Multiply());
		list.add(new ValueNode(2));
		
		tree = ASTNode.BuildTree(list);
		
		solution = tree.Solve();
		assertEquals(solution, Math.sin(5 * Math.PI / 2) * 2, 0.00001);
		
		
		//Test that parameter nodes work.
		list.clear();
		
		list.add(new ParameterNode(puzzle, "test parameter"));
		list.add(new Divide());
		list.add(new ValueNode(2));
		
		tree = ASTNode.BuildTree(list);
		
		solution = tree.Solve();
		assertEquals(solution, 5, 0.00001);
		
		
		//Test that weird deliminators work.
		list.clear();
		
		list.add(new Deliminator(DelimType.open));
		list.add(new ValueNode(12));
		list.add(new Divide());
		list.add(new ValueNode(2));
		list.add(new Deliminator(DelimType.closed));
		
		tree = ASTNode.BuildTree(list);
		
		solution = tree.Solve();
		assertEquals(solution, 6, 0.00001);
		
		list.clear();
		
		list.add(new Deliminator(DelimType.open));
		list.add(new Deliminator(DelimType.open));
		list.add(new Deliminator(DelimType.open));
		list.add(new ValueNode(12));
		list.add(new Divide());
		list.add(new ValueNode(2));
		list.add(new Deliminator(DelimType.closed));
		list.add(new Deliminator(DelimType.closed));
		list.add(new Deliminator(DelimType.closed));
		
		tree = ASTNode.BuildTree(list);
		
		solution = tree.Solve();
		assertEquals(solution, 6, 0.00001);
		
		list.clear();
		
		list.add(new Deliminator(DelimType.open));
		list.add(new ValueNode(12));
		list.add(new Deliminator(DelimType.closed));
		list.add(new Divide());
		list.add(new Deliminator(DelimType.open));
		list.add(new ValueNode(2));
		list.add(new Deliminator(DelimType.closed));
		
		tree = ASTNode.BuildTree(list);
		
		solution = tree.Solve();
		assertEquals(solution, 6, 0.00001);
		
		list.clear();
		
		list.add(new Deliminator(DelimType.open));
		list.add(new ValueNode(12));
		list.add(new Divide());
		list.add(new ValueNode(2));
		list.add(new Deliminator(DelimType.closed));
		list.add(new Add());
		list.add(new Deliminator(DelimType.open));
		list.add(new ValueNode(16));
		list.add(new Divide());
		list.add(new ValueNode(2));
		list.add(new Deliminator(DelimType.closed));
		
		tree = ASTNode.BuildTree(list);
		
		solution = tree.Solve();
		assertEquals(solution, 14, 0.00001);
		
	}

}
