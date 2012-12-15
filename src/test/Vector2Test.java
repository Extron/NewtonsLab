package test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Vector2;

public class Vector2Test
{

	@Test
	public void testVectorOperations()
	{
		//Addition
		Vector2 v1 = new Vector2(1, 1);
		Vector2 v2 = new Vector2(2, 2);
		
		Vector2 v3 = Vector2.Add(v1, v2);
		
		assertEquals(v3.x, 3.0, 0.00001);
		assertEquals(v3.y, 3.0, 0.00001);
		
		v1.Add(v2);
		
		assertEquals(v1.x, 3.0, 0.00001);
		assertEquals(v1.y, 3.0, 0.00001);
		
		
		v1 = new Vector2(-1, 1);
		v2 = new Vector2(2, 2);
		
		v3 = Vector2.Add(v1, v2);
		
		assertEquals(v3.x, 1.0, 0.00001);
		assertEquals(v3.y, 3.0, 0.00001);
		
		v1.Add(v2);
		
		assertEquals(v1.x, 1.0, 0.00001);
		assertEquals(v1.y, 3.0, 0.00001);
		
		
		//Subtraction
		v1 = new Vector2(1, 1);
		v2 = new Vector2(2, 2);
		
		v3 = Vector2.Subtract(v1, v2);
		
		assertEquals(v3.x, -1.0, 0.00001);
		assertEquals(v3.y, -1.0, 0.00001);
		
		v1.Subtract(v2);
		
		assertEquals(v1.x, -1.0, 0.00001);
		assertEquals(v1.y, -1.0, 0.00001);
		
		v1 = new Vector2(1, 1);
		v2 = new Vector2(2, 2);
		
		v3 = Vector2.Subtract(v2, v1);
		
		assertEquals(v3.x, 1.0, 0.00001);
		assertEquals(v3.y, 1.0, 0.00001);
		
		v2.Subtract(v1);
		
		assertEquals(v2.x, 1.0, 0.00001);
		assertEquals(v2.y, 1.0, 0.00001);
		
		
		//Scalar Multiplication
		v1 = new Vector2(3, 3);
		double s1 = 4;
		
		v3 = Vector2.Scale(v1, s1);
		
		assertEquals(v3.x, 12, 0.00001);
		assertEquals(v3.y, 12, 0.00001);
		
		v1.Scale(s1);
		
		assertEquals(v1.x, 12, 0.00001);
		assertEquals(v1.y, 12, 0.00001);
		
		
		//Dot product
		v1 = new Vector2(1, 1);
		v2 = new Vector2(2, 2);
		
		double d1 = Vector2.Dot(v2, v1);
		assertEquals(d1, 4, 0.00001);
		
		d1 = Vector2.Dot(v1, v2);
		assertEquals(d1, 4, 0.00001);
		
		v1 = new Vector2(1, 0);
		v2 = new Vector2(0, 1);
		
		d1 = Vector2.Dot(v1, v2);
		
		assertEquals(d1, 0, 0.00001);
		
		//Length and Length squared
		v1 = new Vector2(1, 1);
		
		double l = v1.Length();
		double l2 = v1.LengthSquared();
		
		assertEquals(l, Math.sqrt(2), 0.00001);
		assertEquals(l2, 2, 0.00001);
		
		v1 = new Vector2(1, 0);
		
		l = v1.Length();
		l2 = v1.LengthSquared();
		
		assertEquals(l, 1, 0.00001);
		assertEquals(l2, 1, 0.00001);
		
		v1 = new Vector2(0, 0);
		
		l = v1.Length();
		l2 = v1.LengthSquared();
		
		assertEquals(l, 0, 0.00001);
		assertEquals(l2, 0, 0.00001);
		
		
		//Distance and Distance squared.
		v1 = new Vector2(1, 1);
		v2 = new Vector2(-1, -1);
		
		l = Vector2.Distance(v1, v2);
		l2 = Vector2.DistanceSquared(v1, v2);
		
		assertEquals(l, Math.sqrt(8), 0.00001);
		assertEquals(l2, 8, 0.00001);
		
		v1 = new Vector2(1, 1);
		v2 = new Vector2(1, 1);
		
		l = Vector2.Distance(v1, v2);
		l2 = Vector2.DistanceSquared(v1, v2);
		
		assertEquals(l, 0, 0.00001);
		assertEquals(l2, 0, 0.00001);
		
		
		//Negative
		v1 = new Vector2(1, 1);
		
		v2 = Vector2.Negative(v1);
		
		assertEquals(v2.x, -1, 0.00001);
		assertEquals(v2.y, -1, 0.00001);
		
		v1.Negate();
		
		assertEquals(v1.x, -1, 0.00001);
		assertEquals(v1.y, -1, 0.00001);
		
		
		//Normalization
		v1 = new Vector2(1, 1);
		
		v2 = Vector2.Normalized(v1);
		
		assertEquals(v2.x, Math.sqrt(2) / 2.0, 0.00001);
		assertEquals(v2.y, Math.sqrt(2) / 2.0, 0.00001);
		
		v1.Normalize();
		
		assertEquals(v1.x, Math.sqrt(2) / 2.0, 0.00001);
		assertEquals(v1.y, Math.sqrt(2) / 2.0, 0.00001);
		
		
		//The zero vector, when "normalized" (it can't really be normalized) should be zero.
		v1 = new Vector2(0, 0);
		
		v2 = Vector2.Normalized(v1);
		
		assertEquals(v2.x, 0, 0.00001);
		assertEquals(v2.y, 0, 0.00001);
		
		v1.Normalize();
		
		assertEquals(v1.x, 0, 0.00001);
		assertEquals(v1.y, 0, 0.00001);
	}
}
