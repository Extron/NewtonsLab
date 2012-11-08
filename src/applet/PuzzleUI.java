package applet;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;

public class PuzzleUI extends ComponentUI
{
	@Override
	public void paint(Graphics g, JComponent c)
	{
		g.setColor(Color.green);
		g.drawRect(0, 0, 100, 100);
	}
}
