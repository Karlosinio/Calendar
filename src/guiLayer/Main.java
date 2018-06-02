package guiLayer;

import logicLayer.ExportException;
import logicLayer.LogicLayer;
import logicLayer.LogicLayerException;
import logicLayer.XMLSettingsSerializer;

public class Main
{
	public static LogicLayer ll;
	
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
				ExceptionWindow.openWindow(e1.getMessage());
			}
		}
		
		try
		{
			ll.setDataService(ll.getSerializer().importData(ll.getFinalFileName()));
		}
		catch (LogicLayerException e)
		{
			ExceptionWindow.openWindow(e.getMessage());
		}
		finally
		{			
			MainWindow.openWindow(null);
		}
		
	}

}
