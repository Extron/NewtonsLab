package test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Hoop;
import core.Vector2;

public class HoopTest
{

	@Test
	public void testHasParticleCrossed()
	{
		Hoop hoop = new Hoop(new Vector2(0, 1), new Vector2(0, -1));
		
		//Test a simple perpendicular crossing.
		assertEquals(hoop.HasParticleCrossed(new Vector2(-1, 0), new Vector2(1, 0)), true);
		
		//Test a simple perpendicular crossing in reverse direction.
		assertEquals(hoop.HasParticleCrossed(new Vector2(1, 0), new Vector2(-1, 0)), true);
		
		//Test a simple non-perpendicular crossing.
		assertEquals(hoop.HasParticleCrossed(new Vector2(-1, 1), new Vector2(1, -1)), true);
		
		//Test a simple non-perpendicular crossing in reverse direction.
		assertEquals(hoop.HasParticleCrossed(new Vector2(1, 1), new Vector2(-1, -1)), true);
		
		//Test edge case for hoop.
		assertEquals(hoop.HasParticleCrossed(new Vector2(-1, 1), new Vector2(1, 1)), true);
		assertEquals(hoop.HasParticleCrossed(new Vector2(-1, -1), new Vector2(1, -1)), true);
		
		//Test edge case for particle.
		assertEquals(hoop.HasParticleCrossed(new Vector2(0, 1), new Vector2(1, 1)), true);
		assertEquals(hoop.HasParticleCrossed(new Vector2(0, -1), new Vector2(1, -1)), true);
		
		//Test co-linear intersection (these fail due to the nature of the test.  It is ideal).
		assertEquals(hoop.HasParticleCrossed(new Vector2(0, 1), new Vector2(0, -1)), false);
		assertEquals(hoop.HasParticleCrossed(new Vector2(0, 1), new Vector2(0, 0)), false);
		assertEquals(hoop.HasParticleCrossed(new Vector2(0, 1), new Vector2(0, -2)), false);
		assertEquals(hoop.HasParticleCrossed(new Vector2(0, 0), new Vector2(0, -1)), false);
		assertEquals(hoop.HasParticleCrossed(new Vector2(0, 0), new Vector2(0, -2)), false);
		
		//Now check that we get false for non-intersections.
		assertEquals(hoop.HasParticleCrossed(new Vector2(-2, 0), new Vector2(-1, 0)), false);
		assertEquals(hoop.HasParticleCrossed(new Vector2(1, 0), new Vector2(2, 0)), false);
		assertEquals(hoop.HasParticleCrossed(new Vector2(-1, 2), new Vector2(1, 2)), false);
		assertEquals(hoop.HasParticleCrossed(new Vector2(-1, -2), new Vector2(1, -1.5)), false);
		assertEquals(hoop.HasParticleCrossed(new Vector2(-1, 0), new Vector2(-1, 1)), false);
		assertEquals(hoop.HasParticleCrossed(new Vector2(1, 1), new Vector2(1, -1)), false);
		
	}

}
