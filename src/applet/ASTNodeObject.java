package applet;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import core.ASTNode;
import core.Vector2;

/**
 * This class stores logic to draw the AST nodes to the screen.
 * 
 * @author Trystan Binkley-Jones
 *
 */
public abstract class ASTNodeObject
{
	/**
	 * A magnet point allows node objects to snap together in the correct places.  Only magnets
	 * with identical and opposite channels can snap together.
	 * 
	 * @author Trystan Binkley-Jones
	 *
	 */
	class MagnetPoint
	{
		MagnetPoint connector;
		Vector2 pos;
		boolean filled;
		int channel;
		
		public MagnetPoint(Vector2 position, int channel)
		{
			this.pos = position;
			this.channel = channel;
		}
		
		public double Distance(ASTNodeObject obj, MagnetPoint magnet)
		{
			Vector2 pa = Vector2.Add(position, pos);
			Vector2 pb = Vector2.Add(obj.position, magnet.pos);
			
			return Vector2.Distance(pa, pb);
		}
		
		public Vector2 GetWorldPos()
		{
			return Vector2.Add(pos, position);
		}
	}
	
	
	/**
	 * A list of magnet points that various nodes can be attached to.
	 */
	protected ArrayList<MagnetPoint> magnetPoints;
	
	/**
	 * A list of child node objects.  This is for convenience mostly, so that we do
	 * not have to iterate across a list of ASTNodeObjects to find the children 
	 * of <code>node</code>.
	 */
	protected ArrayList<ASTNodeObject> children;
	
	/**
	 * The node to draw.
	 */
	protected ASTNode node;
	
	/**
	 * The position of the object.
	 */
	protected Vector2 position;
	
	/**
	 *  The size of the object.
	 */
	protected Dimension size;
	
	
	/**
	 * Creates a new GUI object for an AST node.
	 */
	public ASTNodeObject()
	{
		position = new Vector2();
		
		magnetPoints = new ArrayList<MagnetPoint>();
	}
	
	
	/**
	 * Draws the AST node to the screen.
	 * 
	 * @param graphics - The GUI's graphics helper.
	 */
	public abstract void Draw(Graphics graphics);
	
	/**
	 * Connects an AST node to this AST node.
	 * 
	 * @param obj - The object to connect to.
	 * @param point - The magnet point at which to connect.
	 */
	public abstract void ConnectNode(ASTNodeObject obj, MagnetPoint point);
	
	
	/**
	 * Sets the position of this node object to align with a magnet point.
	 * @param source - The magnet point on this object to connect.
	 * @param destination - The magnet point on the destination object to connect to.
	 */
	public void SnapToMagnet(MagnetPoint source, MagnetPoint destination)
	{
		if (magnetPoints.contains(source))
		{
			position = Vector2.Subtract(destination.GetWorldPos(), source.pos);
			source.filled = destination.filled = true;
			source.connector = destination;
			destination.connector = source;
		}
	}

	/**
	 * Re-opens all magnets within this AST Node object.
	 */
	public void ClearMagnets()
	{
		Iterator<MagnetPoint> iter = magnetPoints.iterator();
		
		while (iter.hasNext())
		{
			MagnetPoint iterMag = iter.next();
			iterMag.filled = false;
			
			if (iterMag.connector != null)
			{
				iterMag.connector.filled = false;
				iterMag.connector.connector = null;
			}
			
			iterMag.connector = null;
		}
	}
	
	/**
	 * Gets the nearest magnet point on the node to the designated magnet point.
	 * 
	 * @param position - The magnet to find the nearest magnet point from.
	 * @return - Returns the nearest magnet point.
	 */
	public MagnetPoint GetMagnetPoint(MagnetPoint magnet)
	{
		Iterator<MagnetPoint> iter = magnetPoints.iterator();
		MagnetPoint currMag = magnetPoints.get(0);
		
		while (iter.hasNext())
		{
			MagnetPoint iterMag = iter.next();
			
			if (magnet.Distance(this, iterMag) <= magnet.Distance(this, currMag) &&
				magnet.channel == -iterMag.channel && 
			    (!magnet.filled && !iterMag.filled))
				currMag = iterMag;
		}
		
		if (currMag.channel != -magnet.channel)
			return null;
		
		return currMag;
	}
	
	/**
	 * Determines if a position is near a magnet point.
	 * @param position - The position to test.
	 * @param magnetPower - The magnet power.
	 * @return - Returns if the position is near a magnet point or not.
	 */
	public boolean IsNearMagnet(MagnetPoint magnet, float magnetPower)
	{
		Iterator<MagnetPoint> iter = magnetPoints.iterator();
		
		while (iter.hasNext())
		{
			MagnetPoint iterMag = iter.next();
			
			if (magnet.Distance(this, iterMag) < magnetPower &&
			    magnet.channel == -iterMag.channel && 
			    (!magnet.filled && !iterMag.filled))
				return true;
		}
		
		return false;
	}
	
	public boolean MouseOver(int x, int y)
	{
		return x > position.x && x < position.x + size.width &&
			   y > position.y && y < position.y + size.height;
	}


	/**
	 * Gets the position of the node object.
	 * 
	 * @return Return's the node object's position.
	 */
	public Vector2 GetPosition()
	{
		return position;
	}

	/**
	 * Gets the size of the node object.
	 * 
	 * @return Returns the size.
	 */
	public Dimension GetSize()
	{
		return size;
	}
}
