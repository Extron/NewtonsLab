package core;

/**
 * A standard hoop that a particle can go through.
 * 
 * @author Trystan Binkley-Jones
 *
 */
public class Hoop implements PuzzleElement
{
	/**
	 * The position of the top of the hoop.
	 */
	Vector2 topPos;
	
	/**
	 * The position of the bottom of the hoop.
	 */
	Vector2 bottomPos;
	
	
	/**
	 * Creates a new hoop.
	 * 
	 * @param top - The position of the top of the hoop.
	 * @param bottom - The position of the bottom of the hoop.
	 */
	public Hoop(Vector2 top, Vector2 bottom)
	{
		this.topPos = top;
		this.bottomPos = bottom;
	}
	
	@Override
	public void Tick(double dt)
	{
	}
	
	/**
	 * Determines if a particle has crossed through the hoop.
	 * 
	 * @param pos1 - The position of the particle at t1.
	 * @param pos2 - The position of the particle at t2.
	 * @return Returns true of the particle crossed through the hoop, false if not.
	 */
	public boolean HasParticleCrossed(Vector2 pos1, Vector2 pos2)
	{
		double det = (pos1.x - pos2.x) * (topPos.y - bottomPos.y) - (topPos.x - bottomPos.x) * (pos1.y - pos2.y);
		
		if (det != 0)
		{
			double x = (pos1.x * pos2.y - pos1.y * pos2.x) * (topPos.x - bottomPos.x) - 
					   (topPos.x * bottomPos.y - topPos.y * bottomPos.x) * (pos1.x - pos2.x);
			
			double y = (pos1.x * pos2.y - pos1.y * pos2.x) * (topPos.y - bottomPos.y) - 
			   (topPos.x * bottomPos.y - topPos.y * bottomPos.x) * (pos1.y - pos2.y);
			
			Vector2 p = new Vector2(x / det, y / det);
			
			if (Vector2.Distance(p, pos1) > Vector2.Distance(pos1, pos2))
				return false;
			else if (Vector2.Distance(p, topPos) > Vector2.Distance(topPos, bottomPos))
				return false;
			
			if (Vector2.Dot(Vector2.Subtract(pos2, p), Vector2.Subtract(pos2, pos1)) < 0)
				return false;
			else if (Vector2.Dot(Vector2.Subtract(bottomPos, p), Vector2.Subtract(bottomPos, topPos)) < 0)
				return false;
			
			return true;
		}
		
		return false;
	}

	public Vector2 GetTopPos()
	{
		return topPos;
	}

	public Vector2 GetBottomPos()
	{
		return bottomPos;
	}
}
