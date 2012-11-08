package test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.*;

public class ASTNodeTest
{

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

}
