package guiLayer;

import javax.swing.JOptionPane;

import logicLayer.ExportException;
import logicLayer.LogicLayer;
import logicLayer.LogicLayerException;
import logicLayer.XMLSettingsSerializer;

/**
 * Main class.
 */
public class Main
{
	public static LogicLayer ll;
	
	/**
	 * Main method, used to launch calendar.
	 * @param args array of string.
	 */
	public static void main(String[] args)
	{
		ll = new LogicLayer();
		
		try
		{
			ll.setSettings(XMLSettingsSerializer.importData());
		}
		catch (LogicLayerException e)
		{
			ll.setDefaultSettings();
			try
			{
				XMLSettingsSerializer.exportData(ll.getSettings());
			}
			catch (ExportException e1)
			{
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
		
		try
		{
			ll.setDataService(ll.getSerializer().importData(ll.getFinalFileName()));
		}
		catch (LogicLayerException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		finally
		{			
			MainWindow.openWindow(null);
		}
		
	}

}
