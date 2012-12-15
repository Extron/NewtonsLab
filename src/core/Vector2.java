package core;

/**
 * Represents a 2D vector.
 * 
 * @author Trystan Binkley-Jones
 *
 */
public final class Vector2
{
	/**
	 * Computes the sum of two vectors.
	 * @param a - The first vector.
	 * @param b - The second vector.
	 * @return Returns the sum of the two vectors.
	 */
	public static Vector2 Add(Vector2 a, Vector2 b)
	{
		return new Vector2(a.x + b.x, a.y + b.y);
	}
	
	/**
	 * Computes the difference between two vectors.
	 * @param a - The first vector.
	 * @param b - The second vector.
	 * @return Returns the difference between the two vectors.
	 */
	public static Vector2 Subtract(Vector2 a, Vector2 b)
	{
		return new Vector2(a.x - b.x, a.y - b.y);
	}
	
	/**
	 * Computes the scalar product between a vector and a scalar.
	 * @param v - The vector.
	 * @param s - The scalar.
	 * @return Returns the scalar product of the vector and the scalar.
	 */
	public static Vector2 Scale(Vector2 v, double s)
	{
		return new Vector2(v.x * s, v.y * s);
	}
	
	/**
	 * Computes the negative of the vector.
	 * 
	 * @param v - The vector.
	 * @return Returns the negative of the vector.
	 */
	public static Vector2 Negative(Vector2 v)
	{
		return Scale(v, -1);
	}
	
	/**
	 * Computes the normalized vector of the specified vector.
	 * 
	 * @param v - The vector.
	 * @return Returns the normalized vector.
	 */
	public static Vector2 Normalized(Vector2 v)
	{
		double l = v.Length();
		
		if (l > 0)
			return Scale(v, 1 / v.Length());
		
		return new Vector2(0, 0);
	}
	
	/**
	 * Computes the dot product between two vectors.
	 * @param a - The first vector.
	 * @param b - The second vector.
	 * @return Returns the dot product of the two vectors.
	 */
	public static double Dot(Vector2 a, Vector2 b)
	{
		return a.x * b.x + a.y * b.y;
	}
	
	/**
	 * Computes the distance squared between two vectors.
	 * @param a - The first vector.
	 * @param b - The second vector.
	 * @return Returns the distance squared between the vectors.
	 */
	public static double DistanceSquared(Vector2 a, Vector2 b)
	{
		return (b.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y);
	}
	
	/**
	 * Computes the distance between two vectors.
	 * @param a - The first vector.
	 * @param b - The second vector.
	 * @return Returns the distance between the vectors.
	 */
	public static double Distance(Vector2 a, Vector2 b)
	{
		return Math.sqrt(DistanceSquared(a, b));
	}
	
	
	/**
	 * The x component of the vector.
	 */
	public double x;
	
	/**
	 * The y component of the vector.
	 */
	public double y;
	
	
	/**
	 * Creates a new vector.
	 * 
	 * @param x - The x component of the vector.
	 * @param y - The y component of the vector.
	 */
	public Vector2(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Creates a new vector.
	 * 
	 * @param a - The value to set both components to.
	 */
	public Vector2(double a)
	{
		this(a, a);
	}
	
	/**
	 * Creates a new vector.
	 */
	public Vector2()
	{
		this(0);
	}
	
	
	/**
	 * Scales the vector by a scalar.
	 * 
	 * @param s - The value to scale by.
	 */
	public void Scale(double s)
	{
		x *= s;
		y *= s;
	}
	
	/**
	 * Adds the component of a specified vector to this vector.
	 * 
	 * @param vec - The vector to add to.
	 */
	public void Add(Vector2 vec)
	{
		x += vec.x;
		y += vec.y;
	}
	
	/**
	 * Subtracts the component of a specified vector from this vector.
	 * 
	 * @param vec - The vector to subtract.
	 */
	public void Subtract(Vector2 vec)
	{
		x -= vec.x;
		y -= vec.y;
	}
	
	/**
	 * Negates the vector.
	 */
	public void Negate()
	{
		x = -x;
		y = -y;
	}
	
	/**
	 * Normalizes the vector.
	 */
	public void Normalize()
	{
		double l = Length();
		
		if (l > 0)
		{
			x /= l;
			y /= l;
		}
		else
		{
			x = 0;
			y = 0;
		}
	}
	
	/**
	 * Gets the length squared of the vector.
	 * @return Returns the length squared of the vector.
	 */
	public double LengthSquared()
	{
		return Dot(this, this);
	}
	
	/**
	 * Returns the length of the vector.
	 * @return Returns the length of the vector.
	 */
	public double Length()
	{
		return Math.sqrt(LengthSquared());
	}
}
