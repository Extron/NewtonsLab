package applet;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.TransferHandler;

public class ASTNodeTransferHandler extends TransferHandler
{
	@Override
	public boolean canImport(TransferSupport supp)
	{
		if (supp.getComponent() instanceof ASTBuilderComponent)
			return true;
		else
			return false;
	}
	
	@Override
	public boolean importData(TransferSupport supp)
	{
		if (!canImport(supp))
	        return false;
		
		ASTBuilderComponent comp = (ASTBuilderComponent) supp.getComponent();
		Transferable t = supp.getTransferable();	    
	    DropLocation loc = supp.getDropLocation();
		String data;
		
	    try
		{
			data = (String)t.getTransferData(DataFlavor.stringFlavor);
		} 
	    catch (UnsupportedFlavorException e)
		{
			return false;
		} 
	    catch (IOException e)
		{
			return false;
		}

	    if (comp != null)
	    {
	    	comp.DropNode(data, loc.getDropPoint());
	    	return true;
	    }
	    else
	    {
	    	return false;
	    }
	    
	}
}
